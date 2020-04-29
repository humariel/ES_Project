package application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class AppController {

    @Autowired
    private SimpMessagingTemplate template;

    private static final Logger log = LoggerFactory.getLogger(AppController.class);
    private ArrayList<Entity> darksky = new ArrayList<>();
    
    @KafkaListener(topics = "entity", containerFactory="kafkaListenerContainerFactory", groupId = "entities_consumers")
    private void darksky_listener(Entity entity){
        log.info("Received message at topic darksky: " + entity);
        darksky.add(entity);
        this.template.convertAndSend("/topic/darksky", entity);
    }

    @GetMapping("/api/darksky/all")
    public ArrayList<Entity> getDarkskyAll(){
        return this.darksky;
    }

    @GetMapping("/api/darksky/time")
    public ArrayList<Entity> getDarkskyByTime(@RequestParam long start, @RequestParam long end){
        return this.darksky.stream()
                .filter(entity -> entity.getTimestamp() > start && entity.getTimestamp() < end)
                .collect(Collectors.toCollection(ArrayList<Entity>::new));
    }
}
