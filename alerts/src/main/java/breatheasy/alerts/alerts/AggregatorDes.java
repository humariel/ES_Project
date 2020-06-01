package breatheasy.alerts.alerts;

import java.util.Map;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

public class AggregatorDes implements Deserializer<ValueAggregator>{
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }
  
    @Override
    public void close() {
    }
  
    @Override
    public ValueAggregator deserialize(String topic, byte[] bytes) {
        if (bytes == null) {
            return null;
        }
    
        try {
            return new ValueAggregator(bytes);
        } catch (RuntimeException e) {
            throw new SerializationException("Error deserializing value", e);
        }
    }
}