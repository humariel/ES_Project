package application;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Trigger {

    @Id
    private String id;
    private String alarm;
    private String parish;
    private long timestamp;
    private AlarmCondition[] values;

    public Trigger() {
    }

    public Trigger(String id, String alarm, String parish, long timestamp, AlarmCondition[] values) {
        this.id = id;
        this.alarm = alarm;
        this.parish = parish;
        this.timestamp = timestamp;
        this.values = values;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAlarm() {
        return this.alarm;
    }

    public void setAlarm(String alarm) {
        this.alarm = alarm;
    }

    public String getParish() {
        return this.parish;
    }

    public void setParish(String parish) {
        this.parish = parish;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public AlarmCondition[] getValues() {
        return this.values;
    }

    public void setValues(AlarmCondition[] values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", alarm='" + getAlarm() + "'" +
            ", parish='" + getParish() + "'" +
            ", timestamp='" + getTimestamp() + "'" +
            ", values='" + getValues() + "'" +
            "}";
    }
    
    
}