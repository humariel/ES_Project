package breatheasy.alerts.alerts;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KConsumer extends Thread {

	@Autowired
    private AlarmRepository repository;
    
    @Autowired
    private TriggerRepository triggerRepository;

    private TriggerProducer triggerProducer;
	private Properties properties = new Properties();
	private Consumer<String, String> consumer;
	private CountDownLatch latch;
    private Map<String,List<Value>> calculations;

	private static final ObjectMapper fromJsonParser = new ObjectMapper();
    private static final Logger logger = LoggerFactory.getLogger(KConsumer.class);
    

	public KConsumer() {
		this("localhost:9092");
	}

	public KConsumer(String bootstrapServer) {
        this.latch = new CountDownLatch(1);
        this.triggerProducer = new TriggerProducer();
		properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
		properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.put(ConsumerConfig.GROUP_ID_CONFIG, "breatheasy");
		properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		this.consumer = new KafkaConsumer<>(properties);
		this.consumer.subscribe(Collections.singleton("newData"));
        calculations = new HashMap<>();
        this.start();
	}

	@Override
	public void run() {
		try {
			while (true) {
				ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));

				for (ConsumerRecord<String, String> record : records) {
					logger.info("Got data from topic " + record.topic() + ": " + record.value());
					// store in db
					try {
                        double temp, hum, press, pm;
                        temp = hum = press = pm = 0;
                        long timestamp = (new Date()).getTime();
						Value value = fromJsonParser.readValue(record.value(), Value.class);
                        List<Value> nl;
                        if (calculations.containsKey(value.getParish())) {
                            nl = calculations.get(value.getParish());
                            nl.add(value);
                            while(((Value) ((LinkedList<Value>) nl).peek()).getTimestamp() <= value.getTimestamp()-10000){
                                ((LinkedList<Value>) nl).removeFirst();
                            }
                        } else {
                            nl = new LinkedList<Value>();
                            nl.add(value);
                            calculations.put(value.getParish(), nl);
                        }
                        for(Value val: nl){
                            temp += val.getTemperature()/nl.size();
                            hum += val.getHumidity()/nl.size();
                            pm += val.getPm10()/nl.size();
                            press += val.getPressure()/nl.size();
                        }
                        List<Alarm> alarmList = repository.findAll();
                        for (Alarm a : alarmList) {
                            if (a.getParish().equals(value.getParish())){
                                boolean trigger=true;
                                double threshold=0;
                                for (AlarmCondition condition: a.getConditions()) {
                                    switch (condition.getType()) {
                                        case "temperature":
                                            threshold = temp;
                                            break;
                                        case "humidity":
                                            threshold=hum;
                                            break;
                                        case "pressure":
                                            threshold=press;
                                            break;
                                        case "pm10":
                                            threshold=pm;
                                            break;
                                        default:
                                            break;
                                    }
                                    if (condition.getOperation().equals("<")) {
                                        trigger &= threshold < condition.getThreshold();
                                    } else {
                                        trigger &= threshold > condition.getThreshold();
                                    }
                                    condition.setValue(threshold);
                                }
                                if (trigger) {
                                    Trigger trig = new Trigger(UUID.randomUUID().toString(), a.getId(), a.getParish(), timestamp, a.getConditions());
                                    triggerRepository.save(trig);
                                    triggerProducer.send("trigger", fromJsonParser.writeValueAsString(trig));
                                }
                            }
                        }
                    } catch (IOException e) {
                        logger.warn("Couldn't save data in repo for some reason");
                        e.printStackTrace();
					} catch (Exception e) {
						logger.error("Error found");
						e.printStackTrace();
					}				
				}
			}
		} catch (WakeupException e) {
			logger.info("Received shutdown signal, terminating consumer");
		} finally {
			this.consumer.close();
			this.latch.countDown();
		}
	}
}