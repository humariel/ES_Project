package breatheasy.alerts.alerts;

import java.util.Map;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

public class AggregatorSer implements Serializer<ValueAggregator> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public void close() {
    }

    @Override
    public byte[] serialize(String topic, ValueAggregator logAgg) {
        if (logAgg == null) {
            return null;
        }

        try {
            return logAgg.asByteArray();
        } catch (RuntimeException e) {
            throw new SerializationException("Error serializing value", e);
        }
    }
}