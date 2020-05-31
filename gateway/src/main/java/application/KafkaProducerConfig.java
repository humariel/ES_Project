package application;

import org.apache.commons.lang.StringUtils;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ser.std.MapSerializer;

@Configuration
public class KafkaProducerConfig {

    @org.springframework.beans.factory.annotation.Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    @Bean
    public ProducerFactory<String, String> producerFactoryValue() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    /* @Bean
    public ProducerFactory<String, Trigger> producerFactoryTrigger() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    } */

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactoryValue());
    }

    /* @Bean
    public KafkaTemplate<String, Trigger> kafkaTemplateTrigger() {
        return new KafkaTemplate<>(producerFactoryTrigger());
    
    } */

    @Bean
    public KafkaAdmin admin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic topic1() {
        return new NewTopic("entity", 5, (short) 2);
    }

    @Bean
    public NewTopic topic2() {
        return new NewTopic("value", 5, (short) 2);
    }

    @Bean
    public NewTopic topic3() {
        return new NewTopic("parish", 5, (short) 2);
    }

    @Bean
    public NewTopic topic4() {
        return new NewTopic("alarm", 5, (short) 2);
    }

    @Bean
    public NewTopic topic5() {
        return new NewTopic("trigger", 5, (short) 2);
    }


}