package application;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class AppController {

    private static final Logger log = LoggerFactory.getLogger(AppController.class);
    
    @KafkaListener(topics = "entity", containerFactory="kafkaListenerContainerFactory", groupId = "entities_consumers")
    private void entities(Entity entity){
        log.info("Received message in group entities_consumers: " + entity);
    }

}
