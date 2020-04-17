package breatheeasy.app.Kafka;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import breatheeasy.app.Repository.BreathData;
import breatheeasy.app.Repository.BreathRepo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class KConsumer {

    @Autowired
    private BreathRepo repository;

    @Autowired
    private SimpMessagingTemplate stompTemplate;

    private final Logger logger = LoggerFactory.getLogger(KConsumer.class);
    private static final ObjectMapper fromJsonParser = new ObjectMapper();

    @KafkaListener(topics = "entity", groupId = "breezez")
    public void consume(String message) throws IOException {
        logger.info(String.format("#### -> Consumed message -> %s", message));
        try {
            BreathData breathData = fromJsonParser.readValue(message, BreathData.class);
            this.stompTemplate.convertAndSend("/topic/weather");
            repository.save(breathData);
        } catch (IOException e) {
            logger.warn("Couldn't save");
        } catch (Exception e) {
            logger.error("Error on incoming data");
            e.printStackTrace();
        }
    }
}