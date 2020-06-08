package breatheasy.alerts.alerts;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Value {

    @Id
    private String id;
    private String entity;
    private String parish;
    private long timestamp;
    private Location location;
    
    private double temperature;
    private double humidity;
    private double pressure;
    private double pm10;

    List<Value> aggList = new ArrayList<>();

    public Value() {
        this.timestamp = (new Date()).getTime();
    }

    public Value(String id, String entity, String parish, long timestamp, Location location, double temperature, double humidity, double pressure, double pm10) {
        this.id = id;
        this.entity = entity;
        this.parish = parish;
        this.timestamp = timestamp;
        this.location = location;
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        this.pm10 = pm10;
    }

    public Value(String parish, long timestamp, double temperature, double humidity, double pressure, double pm10) {
        this.id = null;
        this.entity = null;
        this.parish = parish;
        this.timestamp = timestamp;
        this.location = null;
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        this.pm10 = pm10;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEntity() {
        return this.entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getParish() {
        return this.parish;
    }

    public Value setParish(String parish) {
        this.parish = parish;
        return this;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public Location getLocation() {
        return this.location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public double getTemperature() {
        return this.temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return this.humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getPressure() {
        return this.pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getPm10() {
        return this.pm10;
    }

    public void setPm10(double pm10) {
        this.pm10 = pm10;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", entity='" + getEntity() + "'" +
            ", parish='" + getParish() + "'" +
            ", timestamp='" + getTimestamp() + "'" +
            ", location='" + getLocation() + "'" +
            ", temperature='" + getTemperature() + "'" +
            ", humidity='" + getHumidity() + "'" +
            ", pressure='" + getPressure() + "'" +
            ", pm10='" + getPm10() + "'" +
            "}";
    }

    public static Value fromJson(String jsonString, long count) {
        Value entry = new Gson().fromJson(jsonString, Value.class);
        return entry;
    }

    public String asJsonString() {
        return new Gson().toJson(this);
    }
}