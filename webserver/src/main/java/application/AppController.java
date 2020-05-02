package application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.io.File;
import java.io.IOException;
import java.util.Map;

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
    
    @KafkaListener(topics = "value", containerFactory="kafkaListenerContainerFactory", groupId = "values_consumers")
    private void listener(Value value){
        Parish targetParish = parishRepo.findParishContainingEntity(value.getLocation().getCoords());
        value.setParish(targetParish.getId());
        value.setEntity(value.getId());
        value.setId(null);
        value = entityRepo.save(value);
        //System.out.println("Receiving value " + value);
        this.template.convertAndSend("/topic/value", value);
    }

    @KafkaListener(topics = "alert", containerFactory="kafkaListenerContainerFactory", groupId = "alert_consumers")
    private void listener(Map<String,Object> alert){
        System.out.println("Receiving alert " + alert);
    }
    
}
