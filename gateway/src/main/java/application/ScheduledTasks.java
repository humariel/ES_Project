package application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

@Component
public class ScheduledTasks {

    @Autowired
    private AlarmRepository alarmRepo;

    @Autowired
    private TriggerRepository triggerRepo;

    @Autowired
    private ValueRepository valueRepo;

    @Autowired
    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

    @Autowired
    private KafkaTemplate<String, Value> kafkaTemplate;

    @Autowired
    private KafkaTemplate<String, Trigger> kafkaTemplateTrigger;

    private int time = 0;
    private final Double[][] coords = new Double[][] { { 40.633084, -8.660537 }, { 40.635691, -8.659498 },
            { 40.627348, -8.653447 }, { 40.631554, -8.650752 }, { 40.639417, -8.646243 }, { 40.623084, -8.658191 },
            { 40.614370, -8.639945 }, { 40.607973, -8.627285 }, { 40.622814, -8.640426 }, { 40.606837, -8.628787 },
            { 40.637649, -8.618011 }, { 40.633883, -8.608838 }, { 40.628412, -8.606859 }, { 40.619797, -8.608511 },
            { 40.628216, -8.617392 }, { 40.624584, -8.628796 }, { 40.623402, -8.623879 }, { 40.615590, -8.628901 },
            { 40.612623, -8.616170 }, { 40.613307, -8.623369 }, { 40.600206, -8.600228 }, { 40.605274, -8.595435 },
            { 40.602856, -8.603787 }, { 40.599854, -8.600715 }, { 40.603839, -8.602905 }, { 40.577787, -8.578555 },
            { 40.576442, -8.576356 }, { 40.576238, -8.580648 }, { 40.572755, -8.573448 }, { 40.569960, -8.567665 },
            { 40.624765, -8.564652 }, { 40.629211, -8.570452 }, { 40.622241, -8.549117 }, { 40.622245, -8.566591 },
            { 40.616955, -8.565777 }, { 40.649415, -8.623272 }, { 40.648634, -8.614002 }, { 40.649687, -8.629945 },
            { 40.647815, -8.631747 }, { 40.655780, -8.614240 }, { 40.665585, -8.612773 }, { 40.701724, -8.620130 },
            { 40.693423, -8.593055 }, { 40.680955, -8.582951 }, { 40.685136, -8.589633 }, { 40.666293, -8.743672 },
            { 40.675970, -8.720291 }, { 40.694182, -8.732699 }, { 40.656854, -8.741412 }, { 40.664041, -8.729213 }, };
    private HashMap<Double[], Simulator> simsMap = new HashMap<>();

    @PostConstruct
    public void init() {
        for (int i = 0; i < coords.length; i++) {
            String id = UUID.randomUUID().toString();
            simsMap.put(coords[i], new Simulator(id, new Location("Point", coords[i][0], coords[i][1])));
        }
    }

    @Scheduled(fixedRate = 10000)
    public void reportCurrentTime() {

        for (int i = 0; i < coords.length; i++) {
            Value v = simsMap.get(coords[i]).simulate();
            sendKafkaMessage("value", v);
        }

    }

    @Scheduled(fixedRate = 60000, initialDelay = 0)
    public void checkAlarms() {
        List<Alarm> alarmList = alarmRepo.findAll();
        for (Alarm a : alarmList) {
            if (time % a.getTime() == 0) {
                System.out.println("ALARM LIST" + alarmList);
                long timestamp = (new Date()).getTime();
                // get values from alarm parish from alarm time frame
                List<Value> valueList = valueRepo.getValuesFromParish(a.getParish(), timestamp - (60 * 1000 * time));
                // if alarm conditions are true create, save and send trigger
                if(verifyAlarmTrigger(a.getConditions(), valueList)){
                    Trigger trigger = new Trigger(UUID.randomUUID().toString(),a.getId(),timestamp);
                    sendKafkaMessageTrigger("trigger", trigger);
                    //trigger = triggerRepo.save(trigger);
                }
            }
        }
        time++;
    }

    public boolean verifyAlarmTrigger(AlarmCondition[] condList, List<Value> valueList) {
        boolean trigger = false;

        for (AlarmCondition a : condList) {
            Double avgVal = 0.0;
            switch (a.getType()) {
                case "temperature":
                    avgVal = valueList.stream().collect(Collectors.averagingDouble(v -> v.getTemperature()));
                    System.out.println("AVG TEMPERATURE: " + avgVal);
                    break;

                case "humidity":
                    avgVal = valueList.stream().collect(Collectors.averagingDouble(v -> v.getHumidity()));
                    System.out.println("AVG HUMIDITY: " + avgVal);
                    break;

                case "pressure":
                    avgVal = valueList.stream().collect(Collectors.averagingDouble(v -> v.getPressure()));
                    break;

                case "pm10":
                    avgVal = valueList.stream().collect(Collectors.averagingDouble(v -> v.getPm10()));
                    break;

                default:
                    trigger = false;
            }

            if (a.getOperation() == "<") {
                trigger = avgVal < a.getValue();
            } else {
                trigger = avgVal > a.getValue();
            }
        }
        return trigger;
    }

    public void sendKafkaMessageTrigger(String topic, Trigger entity) {
        ListenableFuture<SendResult<String, Trigger>> future = kafkaTemplateTrigger.send(topic, entity);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Trigger>>() {
            @Override
            public void onFailure(Throwable ex) {
                logger.info("Unable to send message = [" + entity.toString() + "] due to : " + ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, Trigger> result) {
                logger.info("Kafka: Sent message to topic " + topic + " = [" + entity.toString() + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }
        });
    }


    public void sendKafkaMessage(String topic, Value entity) {
        ListenableFuture<SendResult<String, Value>> future = kafkaTemplate.send(topic, entity);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Value>>() {
            @Override
            public void onFailure(Throwable ex) {
                logger.info("Unable to send message = [" + entity.toString() + "] due to : " + ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, Value> result) {
                logger.info("Kafka: Sent message to topic " + topic + " = [" + entity.toString() + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }
        });
    }
}