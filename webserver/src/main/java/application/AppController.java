package application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.util.concurrent.ListenableFuture;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.LinkedList;

import javax.annotation.PostConstruct;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin(origins = "*")
public class AppController {

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private ValueRepository valueRepo;

    @Autowired
    private ParishRepository parishRepo;

    @Autowired
    private AlarmRepository alarmRepo;

    @Autowired
    private TriggerRepository triggerRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private Map<String, List<Value>> calculations;

    private static final Logger logger = LoggerFactory.getLogger(AppController.class);

    private ObjectMapper mapper = new ObjectMapper();

    @PostConstruct
    public void init() throws IOException {

        System.out.println("HERE");

        ObjectMapper objectMapper = new ObjectMapper();
        calculations = new HashMap<>();
        Parish[] parishes = objectMapper.readValue(new File("aveiro.json"), Parish[].class);
        for (Parish parish : parishes)
            parishRepo.save(parish);

    }

    @PostMapping(value = "alarm")
    public void postAlarm(@RequestBody Alarm alarm) throws Exception {
        alarmRepo.save(alarm);
        sendKafkaMessage("alarm", mapper.writeValueAsString(alarm));
        /*
         * Alarm newAlarm = alarmRepo.save(alarm); return newAlarm;
         */
    }

    @GetMapping(value = "alarms")
    public List<Alarm> getAlarms() throws Exception {
        return alarmRepo.findAll();
    }

    @DeleteMapping(value = "alarm")
    public void deleteAlarm(@RequestBody String id) throws Exception {
        logger.info("Deleting alarm " + id);
        alarmRepo.deleteById(id);
    }

    @PostMapping(value = "value")
    public Value postValue(@RequestBody Value value) throws Exception {
        Value newValue = valueRepo.save(value);
        checkAlarmTrigger(value);
        return value;
    }

    @KafkaListener(topics = "value", containerFactory = "kafkaListenerContainerFactory", groupId = "breathe_consumers")
    private void listener(String message) throws Exception {
        Value value = mapper.readValue(message, Value.class);
        Parish targetParish = parishRepo.findParishContainingEntity(value.getLocation().getCoords());
        value.setParish(targetParish.getId());
        value.setEntity(value.getId());
        value.setId(null);
        value = valueRepo.save(value);
        checkAlarmTrigger(value);
        // System.out.println("Receiving value " + value);
        template.convertAndSend("/topic/value", value);
    }

    @KafkaListener(topics = "parish", containerFactory = "kafkaListenerContainerFactory", groupId = "breathe_consumers")
    private void listenerParish(String message) throws Exception {
        sendKafkaMessage("trigger", message);
    }

    @KafkaListener(topics = "trigger", containerFactory = "kafkaListenerContainerFactory", groupId = "breathe_consumers")
    private void listenTrigger(String message) throws Exception {
        Trigger trigger = mapper.readValue(message, Trigger.class);
        System.out.println(trigger);
        template.convertAndSend("/topic/trigger", trigger);
    }

    @KafkaListener(topics = "averages", containerFactory = "kafkaListenerContainerFactory", groupId = "breathe_consumers")
    private void listenAverages(String message) throws Exception {
        Value trigger = mapper.readValue(message, Value.class);
        System.out.print("\u001B[0m\u001B[32m NEW VALUE ");
        System.out.print(trigger);
        System.out.println("\u001B[0m");
        template.convertAndSend("/topic/averages", trigger);
    }

    public void sendKafkaMessage(String topic, String entity) {
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, entity);
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable ex) {
                logger.info("Unable to send message = [" + entity.toString() + "] due to : " + ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, String> result) {
                logger.info("Kafka: Sent message to topic " + topic + " = [" + entity.toString() + "] with offset=["
                        + result.getRecordMetadata().offset() + "]");
            }
        });
    }

    private void checkAlarmTrigger(Value value) throws JsonProcessingException {
        double temp, hum, press, pm;
        temp = press = hum = pm = 0;
        long timestamp = (new Date()).getTime();
        List<Value> nl;
        if (calculations.containsKey(value.getParish())) {
            nl = calculations.get(value.getParish());
            nl.add(value);
            while(((Value) ((LinkedList<Value>) nl).peek()).getTimestamp() <= value.getTimestamp()-10000){
                ((LinkedList<Value>) nl).removeFirst();
            }
        } else {
            nl = new LinkedList<Value>();
            nl.add(value);
            calculations.put(value.getParish(), nl);
        }
        for(Value val: nl){
            temp += val.getTemperature()/nl.size();
            hum += val.getHumidity()/nl.size();
            pm += val.getPm10()/nl.size();
            press += val.getPressure()/nl.size();
        }
        Value val = new Value(value.getParish(), timestamp, temp, hum, press, pm);
        //template.convertAndSend("/topic/averages", val);
        sendKafkaMessage("averages", mapper.writeValueAsString(val));
        List<Alarm> alarmList = alarmRepo.findAll();
        alarmList.forEach(System.out::println);
        for (Alarm a : alarmList) {
            if (a.getParish().equals(value.getParish())){                
                boolean trigger=true;
                double threshold=0;
                for (AlarmCondition condition: a.getConditions()) {
                    switch (condition.getType()) {
                        case "temperature":
                            threshold = temp;
                            break;
                        case "humidity":
                            threshold=hum;
                            break;
                        case "pressure":
                            threshold=press;
                            break;
                        case "pm10":
                            threshold=pm;
                            break;
                        default:
                            break;
                    }
                    if (condition.getOperation().equals("<")) {
                        trigger &= threshold < condition.getThreshold();
                    } else {
                        trigger &= threshold > condition.getThreshold();
                    }
                    condition.setValue(threshold);
                }
                // get last trigger
                List<Trigger> trigs = triggerRepository.findAllByAlarm(a.getId());
                //trigs.forEach(System.out::println);
                Trigger lastTrig;
                if (trigs.size() > 0) {
                    lastTrig = trigs.get(trigs.size()-1);
                    if (trigger && (lastTrig.getTimestamp()+60*1000 <= timestamp)) {
                        System.out.println("SAVED");
                        Trigger trig = new Trigger(UUID.randomUUID().toString(), a.getId(), a.getParish(), timestamp, a.getConditions());
                        triggerRepository.save(trig);
                        sendKafkaMessage("trigger", mapper.writeValueAsString(trig));
                    }
                } else {
                    if (trigger) {
                        Trigger trig = new Trigger(UUID.randomUUID().toString(), a.getId(), a.getParish(), timestamp, a.getConditions());
                        triggerRepository.save(trig);
                        sendKafkaMessage("trigger", mapper.writeValueAsString(trig));
                    }
                }
                
            }
        }
    }
}
