package gateway.gateway;

import java.util.Properties;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class KProducer {
	
	private Properties properties = new Properties();
	private Producer<String, String> producer;
	private static final Logger logger = LoggerFactory.getLogger(KProducer.class);


	public KProducer() {
		this("localhost:9092");
	}

	public KProducer(String bootstrapServer) {
		properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
		properties.put(ProducerConfig.ACKS_CONFIG, "all");
		properties.put(ProducerConfig.RETRIES_CONFIG, "1");
		properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		producer = new KafkaProducer<>(properties);
	}

	public void send(String topic, String data) {
		producer.send(new ProducerRecord<String,String>(topic, data), new Callback(){
		
			@Override
			public void onCompletion(RecordMetadata metadata, Exception exception) {
				if (exception == null) {
					logger.info("Record sent successfully");
				} else {
					logger.warn("Record was not sent - TOPIC: " + metadata.topic());				
				}
			}
		});
		producer.flush();
	}

	public void close() {
		producer.close();
	}
}