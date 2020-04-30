package application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class AppController {

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private EntityRepository entityRepo;

    @Autowired
    private ParishRepository parishRepo;

    private static final Logger log = LoggerFactory.getLogger(AppController.class);

    @PostConstruct
    public void init() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        Parish[] parishes = objectMapper.readValue(new File("aveiro.json"), Parish[].class);
        for(Parish parish : parishes)
            parishRepo.save(parish);

    }
    
    @KafkaListener(topics = "entity", containerFactory="kafkaListenerContainerFactory", groupId = "entities_consumers")
    private void listener(Entity entity){
        Parish targetParish = parishRepo.findParishContainingEntity(entity.getLocation().getCoords());
        entity.setParish(targetParish.getId());
        System.out.println("Receiving entity " + entity);
        entityRepo.save(entity);
        this.template.convertAndSend("/topic/entity", entity);
    }
    
}
