package application.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Alarm {

    @Id
    private String id;
    private int time;
    private AlarmCondition[] conditions;

    public Alarm() {
    }

    public Alarm(String id, int time, AlarmCondition[] conditions) {
        this.id = id;
        this.time = time;
        this.conditions = conditions;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTime() {
        return this.time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public AlarmCondition[] getConditions() {
        return this.conditions;
    }

    public void setConditions(AlarmCondition[] conditions) {
        this.conditions = conditions;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", time='" + getTime() + "'" +
            ", conditions='" + getConditions() + "'" +
            "}";
    }

    
}