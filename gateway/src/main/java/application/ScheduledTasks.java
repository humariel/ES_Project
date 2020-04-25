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
    private KafkaTemplate<String, Darksky_Entity> kafkaTemplate;

    private Double[][] coords = new Double[][]{
        { 40.628883, -8.6590908 },
        { 40.2, -8.2 },
        { 41.2, -8.0 },
        { 40.0, -8.9 },
        { 40.5, -8.1 },
        { 40.1, -9.0 },
        { 41.1, -8.3 },
        { 40.4, -8.9 },
        { 40.7, -8.4 }
    };
    private int index = 0;
    
    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {

        Darksky_Entity req = restTemplate.getForObject(
            "https://api.darksky.net/forecast/438151d66be4ce981bc94398c2428874/" + coords[index][0] + "," + coords[index][1] + "?exclude=hourly,minutely,daily,alerts,flags",
        Darksky_Entity.class);
        sendKafkaMessage("darksky", req);
        index = (index + 1) % coords.length;

        //do same for breezo. send to topic "breezo"

    }

    public void sendKafkaMessage(String topic, Darksky_Entity entity) {
        ListenableFuture<SendResult<String, Darksky_Entity>> future = kafkaTemplate.send(topic, entity);

        future.addCallback(new ListenableFutureCallback<SendResult<String, Darksky_Entity>>() {
            @Override
            public void onFailure(Throwable ex) {
                logger.info("Unable to send message = [" + entity.toString() + "] due to : " + ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, Darksky_Entity> result) {
                logger.info("Kafka: Sent message to topic " + topic + " = [" + entity.toString() + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }
        });
    }
}