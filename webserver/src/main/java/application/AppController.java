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
import java.util.List;

import javax.annotation.PostConstruct;

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
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final Logger logger = LoggerFactory.getLogger(AppController.class);

    private ObjectMapper mapper = new ObjectMapper();

    @PostConstruct
    public void init() throws IOException {

        System.out.println("HERE");

        ObjectMapper objectMapper = new ObjectMapper();
        Parish[] parishes = objectMapper.readValue(new File("aveiro.json"), Parish[].class);
        for(Parish parish : parishes)
            parishRepo.save(parish);

    }

    @PostMapping(value="alarm")
    public void postAlarm(@RequestBody Alarm alarm) throws Exception {
        sendKafkaMessage("alarm", mapper.writeValueAsString(alarm));
        /* Alarm newAlarm = alarmRepo.save(alarm);
        return newAlarm; */
    }

    @GetMapping(value="alarms")
    public List<Alarm> getAlarms() throws Exception {
        return alarmRepo.findAll();
    }

    @DeleteMapping(value="alarm")
    public void deleteAlarm(@RequestBody String id) throws Exception {
        logger.info("Deleting alarm "+id);
        alarmRepo.deleteById(id);
    }

    @PostMapping(value="value")
    public Value postValue(@RequestBody Value value) throws Exception {
        Value newValue = valueRepo.save(value);
        return value;
    }

    @KafkaListener(topics = "esp31_value", containerFactory="kafkaListenerContainerFactory", groupId = "breathe_consumers")
    private void listener(String message) throws Exception {
        Value value = mapper.readValue(message, Value.class);
        Parish targetParish = parishRepo.findParishContainingEntity(value.getLocation().getCoords());
        value.setParish(targetParish.getId());
        value.setEntity(value.getId());
        value.setId(null);
        value = valueRepo.save(value);
        //System.out.println("Receiving value " + value);
        template.convertAndSend("/topic/value", value);
    }

    @KafkaListener(topics = "esp31_trigger", containerFactory="kafkaListenerContainerFactory", groupId = "breathe_consumers")
    private void listenTrigger(String message) throws Exception {
        Trigger trigger = mapper.readValue(message, Trigger.class);
        System.out.println(trigger);
        template.convertAndSend("/topic/trigger", trigger);
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
                logger.info("Kafka: Sent message to topic " + topic + " = [" + entity.toString() + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }
        });
    }
    
}
