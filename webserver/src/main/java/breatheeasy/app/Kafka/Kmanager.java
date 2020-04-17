package breatheeasy.app.Kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class Kmanager {
     
    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;
 
    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1");
        return new KafkaAdmin(configs);
    }
     
    @Bean
    public NewTopic genTopic(String topicName) {
         return new NewTopic(topicName, 1, (short) 1);
    }
}