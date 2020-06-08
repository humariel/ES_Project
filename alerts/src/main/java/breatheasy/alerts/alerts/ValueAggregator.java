package breatheasy.alerts.alerts;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ValueAggregator {
    
    ArrayList<Value> values = new ArrayList<>();
    Gson gson = new Gson();

    public ValueAggregator() {
    }

    public ValueAggregator(ValueAggregator v1, ValueAggregator v2) {
        values.addAll(v1.values);
        values.addAll(v2.values);
    }

    public ValueAggregator(byte[] bytes) {
        this(new String(bytes));
    }

    public ValueAggregator(String jsonString) {
        ArrayList<Value> entries = gson.fromJson(jsonString, new TypeToken<List<Value>>(){}.getType());
        values.addAll(entries);
    }

    public ValueAggregator add(String val) {
        Value entry = gson.fromJson(val, Value.class);
        values.add(entry);
        return this;
    }

    public ValueAggregator add(Value val) {
        values.add(val);
        return this;
    }

    public String asJsonString() {
        return gson.toJson(values);
    }

    public byte[] asByteArray() {
        return asJsonString().getBytes(StandardCharsets.UTF_8);
    }

    public String groupedLimitedBy(Integer limitSize) {
        Map<String, Long> counted = values.stream()
            .map(entry -> entry.asJsonString())
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        ArrayList<Value> listSubset = new ArrayList<>();

        counted.entrySet().stream()
            .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
            .limit(limitSize)
            .forEachOrdered(e -> {
            Value logEntry = Value.fromJson(e.getKey(), e.getValue());
            listSubset.add(logEntry);
            });

        return gson.toJson(listSubset);
    }

    public Value getAggregatedValue() {
        double temp, hum, pm10, press, counter;
        temp = press = hum = pm10 = counter = 0;
        String parish ="";
        for (Value value : values) {
            counter++;
            temp+=value.getTemperature();
            hum+=value.getHumidity();
            press+=value.getPressure();
            pm10+=value.getPm10();
            parish = value.getParish();
        }
        return new Value(null,null,parish,(new Date()).getTime(),null,temp,hum,press,pm10);
    }
    
}