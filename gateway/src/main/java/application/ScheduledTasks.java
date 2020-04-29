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

        //do same for breezo. send to topic "breezo"
        /* try {
            Breezo_Entity bre = restTemplate.getForObject(
                "https://api.breezometer.com/air-quality/v2/current-conditions?lat=" + coords[index][0] + "&lon=" + coords[index][1] + "&key=755455f352dc419aa091647a6b9f4caf&features=pollutants_concentrations", 
                Breezo_Entity.class);
            sendKafkaMessage("breezo", bre);
        } catch(Error e) {

        } */

        index = (index + 1) % coords.length;

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