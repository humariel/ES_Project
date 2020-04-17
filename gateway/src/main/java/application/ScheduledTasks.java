package application;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.client.RestTemplate;


@Component
public class ScheduledTasks {

    @Autowired
    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
	private static final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private KafkaTemplate<String, Entity> kafkaTemplate;
    
    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {

        Entity req = restTemplate.getForObject(
           "https://api.darksky.net/forecast/438151d66be4ce981bc94398c2428874/40.628883,-8.6590908?exclude=hourly,minutely,daily,alerts,flags",
        Entity.class);
        sendKafkaMessage("entity", req);

    }

    public void sendKafkaMessage(String topic, Entity entity) {
        ListenableFuture<SendResult<String, Entity>> future = kafkaTemplate.send(topic, entity);

        future.addCallback(new ListenableFutureCallback<SendResult<String, Entity>>() {
            @Override
            public void onFailure(Throwable ex) {
                logger.info("Unable to send message = [" + entity.toString() + "] due to : " + ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, Entity> result) {
                logger.info("Kafka: Sent message to topic " + topic + " = [" + entity.toString() + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }
        });
    }
}