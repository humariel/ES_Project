package gateway;

import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.ArrayList;
import java.util.Arrays;

import java.time.Instant;
import java.util.Comparator;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.client.RestTemplate;


import gateway.Entity;


@Component
public class ScheduledTasks {

    @Autowired
    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	private static final RestTemplate restTemplate = new RestTemplate();
    private static final ObjectMapper toJsonParser = new ObjectMapper();

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    
    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {

        Entity req = restTemplate.getForObject(
            "https://api.darksky.net/forecast/438151d66be4ce981bc94398c2428874/40.628883,-8.6590908?exclude=hourly,minutely,daily,alerts,flags",
        Entity.class);

        System.out.println(req);
            
        sendKafkaMessage("device", req.toJsonKafka());
        
        // Quote quote = restTemplate.getForObject(
        //         "http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
        // cQuote = quote;

        // log.info(quote.toString());
        // log.info("The time is now {}", dateFormat.format(new Date()));

    }

    public void sendKafkaMessage(String topic, String entity) {
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, entity);

        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable ex) {
                logger.info("Unable to send message=[" + entity.toString() + "] due to : " + ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, String> result) {
                logger.info("Kafka: Sent message=[" + entity.toString() + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }
        });
    }
}