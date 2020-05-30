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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@CrossOrigin(origins = "*")
public class AppController {

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private ValueRepository valueRepo;

    @Autowired
    private ParishRepository parishRepo;

    @Autowired
    private AlarmRepository alarmRepo;

    private static final Logger log = LoggerFactory.getLogger(AppController.class);

    private ObjectMapper mapper = new ObjectMapper();

    @PostConstruct
    public void init() throws IOException {

        System.out.println("HERE");

        ObjectMapper objectMapper = new ObjectMapper();
        Parish[] parishes = objectMapper.readValue(new File("aveiro.json"), Parish[].class);
        for(Parish parish : parishes)
            parishRepo.save(parish);

    }

    @PostMapping(value="alarm")
    public Alarm postAlarm(@RequestBody Alarm alarm) throws Exception {
        Alarm newAlarm = alarmRepo.save(alarm);
        return newAlarm;
    }

    @PostMapping(value="value")
    public Value postValue(@RequestBody Value value) throws Exception {
        Value newValue = valueRepo.save(value);
        return value;
    }

    @KafkaListener(topics = "esp31_value", containerFactory="kafkaListenerContainerFactory", groupId = "breathe_consumers")
    private void listener(String message) throws Exception {
        Value value = mapper.readValue(message, Value.class);
        Parish targetParish = parishRepo.findParishContainingEntity(value.getLocation().getCoords());
        value.setParish(targetParish.getId());
        value.setEntity(value.getId());
        value.setId(null);
        value = valueRepo.save(value);
        //System.out.println("Receiving value " + value);
        template.convertAndSend("/topic/value", value);
    }

    @KafkaListener(topics = "esp31_trigger", containerFactory="kafkaListenerContainerFactory", groupId = "breathe_consumers")
    private void listenTrigger(String message) throws Exception {
        Trigger trigger = mapper.readValue(message, Trigger.class);
        System.out.println(trigger);
        template.convertAndSend("/topic/trigger", trigger);
    }
    
}
