package application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;

public class KafkaConsumer {

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private ParishRepository parishRepo;

    @Autowired
    private ValueRepository valueRepo;
    
    @KafkaListener(topics = "value", containerFactory="kafkaListenerContainerFactory", groupId = "values_consumers")
    private void listener(Value value){
        Parish targetParish = parishRepo.findParishContainingEntity(value.getLocation().getCoords());
        value.setParish(targetParish.getId());
        value.setEntity(value.getId());
        value.setId(null);
        value = valueRepo.save(value);
        System.out.println("Receiving value " + value);
        template.convertAndSend("/topic/value", value);
    }

}