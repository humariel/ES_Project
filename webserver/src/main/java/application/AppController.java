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
    private ArrayList<Darksky_Entity> darksky = new ArrayList<>();
    private ArrayList<Breezo_Entity> breezo = new ArrayList<>();
    
    @KafkaListener(topics = "darksky", containerFactory="kafkaListenerContainerFactory", groupId = "entities_consumers")
    private void darksky_listener(Darksky_Entity entity){
        log.info("Received message at topic darksky: " + entity);
        darksky.add(entity);
        this.template.convertAndSend("/topic/darksky", entity);
    }

    @GetMapping("/api/darksky")
    public ArrayList<Darksky_Entity> getDarksky(){
        return this.darksky;
    }

    @KafkaListener(topics = "breezo", containerFactory="kafkaListenerContainerFactory", groupId = "entities_consumers")
    private void breezo_listener(Breezo_Entity entity){
        log.info("Received message at topic breezo: " + entity);
        breezo.add(entity);
        this.template.convertAndSend("/topic/breezo", entity);
    }

    @GetMapping("/api/breezo")
    public ArrayList<Breezo_Entity> getBreezo(){
        return this.breezo;
    }

}
