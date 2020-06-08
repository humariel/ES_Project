package breatheasy.alerts.alerts;

import java.util.Arrays;
import java.util.function.Function;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AlertsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlertsApplication.class, args);
	}

	public static class KafkaStreamAlertAnalyzer {

		@Autowired
		private ParishRepository parishRepository;

		ObjectMapper mapper = new ObjectMapper();

		@Bean
		public Function<KStream<String, String>, KStream<String, String>> process() {
			return input -> input.flatMapValues(value -> {
													try {
														return Arrays.asList(mapper.readValue(value, Value.class));
													} catch (JsonProcessingException e) {
														// TODO Auto-generated catch block
														e.printStackTrace();
													}
													return null;
												})
								.map((key, value) -> new KeyValue<>(parishRepository.findParishContainingEntity(value.getLocation().getCoords()),
																	value.setParish(parishRepository.findParishContainingEntity(value.getLocation().getCoords()).getName())))
								.map((key, value) -> {
									try {
										return new KeyValue<>(key.toString(), mapper.writeValueAsString(value));
									} catch (JsonProcessingException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									return null;
								});
		}
	}
}
