package application;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Trigger {

    @Id
    private String id;
    private String alarmID;
    private long timestamp;

    public Trigger(String id, String alarmID, long timestamp) {
        this.id = id;
        this.alarmID = alarmID;
        this.timestamp = timestamp;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAlarmID() {
        return this.alarmID;
    }

    public void setAlarmID(String alarmID) {
        this.alarmID = alarmID;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", alarmID='" + getAlarmID() + "'" +
            ", time='" + getTimestamp() + "'" +
            "}";
    }    
    
}