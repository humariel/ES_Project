package application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class AppController {

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private EntityRepository repository;

    private static final Logger log = LoggerFactory.getLogger(AppController.class);
    
    @KafkaListener(topics = "entity", containerFactory="kafkaListenerContainerFactory", groupId = "entities_consumers")
    private void darksky_listener(Entity entity){
        log.info("Received message at topic entity: " + entity);
        repository.save(entity);
        this.template.convertAndSend("/topic/darksky", entity);
    }
    
}
