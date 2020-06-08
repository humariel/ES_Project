package breatheasy.alerts.alerts;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Alarm {

    @Id
    private String id;
    private String parish;
    private int time;
    private AlarmCondition[] conditions;


    public Alarm() {
    }

    public Alarm(String id, String parish, int time, AlarmCondition[] conditions) {
        this.id = id;
        this.parish = parish;
        this.time = time;
        this.conditions = conditions;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParish() {
        return this.parish;
    }

    public void setParish(String parish) {
        this.parish = parish;
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
            ", parish='" + getParish() + "'" +
            ", time='" + getTime() + "'" +
            ", conditions='" + getConditions() + "'" +
            "}";
    }    
    
}