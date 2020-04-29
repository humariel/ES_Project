package application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class AppController {

    @Autowired
    private SimpMessagingTemplate template;

    private static final Logger log = LoggerFactory.getLogger(AppController.class);
    
    @KafkaListener(topics = "entity", containerFactory="kafkaListenerContainerFactory", groupId = "entities_consumers")
    private void darksky_listener(Entity entity){
        log.info("Received message at topic entity: " + entity);
        this.template.convertAndSend("/topic/darksky", entity);
    }

}
