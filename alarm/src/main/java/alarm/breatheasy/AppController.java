package alarm.breatheasy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Component
public class AppController {

    @Autowired
    private AlarmRepository alarmRepo;
    
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    
    private static final Logger logger = LoggerFactory.getLogger(AppController.class);
    
    private ObjectMapper mapper = new ObjectMapper();

    @PostConstruct
    public void init() {
        logger.info("STARTED");
    }
    
    @KafkaListener(topics = "alarm", containerFactory="kafkaListenerContainerFactory", groupId = "breathe_consumers")
    private void listener(String message) throws Exception {
        System.out.println("HERE");
        Alarm alarm = mapper.readValue(message, Alarm.class);
        alarmRepo.save(alarm);
        logger.info("SAVED ALARM");
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
