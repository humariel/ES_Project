package breatheeasy.app.Kafka;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KConsumer {

    private final Logger logger = LoggerFactory.getLogger(KConsumer.class);

    @KafkaListener(topics = "breezez", groupId = "breezez")
    public void consume(String message) throws IOException {
        logger.info(String.format("#### -> Consumed message -> %s", message));
    }
}