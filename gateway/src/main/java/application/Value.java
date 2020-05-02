package application;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Value {

    //metadata
    private String id;
    private Location location;
    private long timestamp;
    //weather related
    private double temperature;
    private double humidity;
    private double pressure;
    private int precipIntensity;
    //pollution focused
    private double o3;
    private double co;
    private double no2;
    private double pm10;
    private double pm25;
    private double so2;

    public Value() {
        this.timestamp = (new Date()).getTime();
    }

    public Value(String id, Location location, double temperature, double humidity, double pressure, int precipIntensity, double o3, double co, double no2, double pm10, double pm25, double so2) {
        this.id = id;
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        this.precipIntensity = precipIntensity;
        this.o3 = o3;
        this.co = co;
        this.no2 = no2;
        this.pm10 = pm10;
        this.pm25 = pm25;
        this.so2 = so2;

        this.timestamp = (new Date()).getTime();
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Location getLocation() {
        return this.location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
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

    public int getPrecipIntensity() {
        return this.precipIntensity;
    }

    public void setPrecipIntensity(int precipIntensity) {
        this.precipIntensity = precipIntensity;
    }

    public double getO3() {
        return this.o3;
    }

    public void setO3(double o3) {
        this.o3 = o3;
    }

    public double getCo() {
        return this.co;
    }

    public void setCo(double co) {
        this.co = co;
    }

    public double getNo2() {
        return this.no2;
    }

    public void setNo2(double no2) {
        this.no2 = no2;
    }

    public double getPm10() {
        return this.pm10;
    }

    public void setPm10(double pm10) {
        this.pm10 = pm10;
    }

    public double getPm25() {
        return this.pm25;
    }

    public void setPm25(double pm25) {
        this.pm25 = pm25;
    }

    public double getSo2() {
        return this.so2;
    }

    public void setSo2(double so2) {
        this.so2 = so2;
    }

    public Map<String,Object> verifyDanger(){
        Map<String,Object> values = new HashMap<>();
        values.put("id", this.id);
        values.put("location", this.location);
        values.put("timestamp", this.timestamp);

        if (this.temperature > 43)
            values.put("temperature",this.temperature);

        if (this.o3 > 43)
            values.put("o3",this.o3);

        if (this.co > 230)
            values.put("co",this.co);

        if (this.no2 > 27)
            values.put("no2",this.no2);

        if (this.pm10 > 27)
            values.put("pm10",this.pm10);

        if (this.pm25 > 27)
            values.put("pm25",this.pm25);

        if (this.so2 > 6)
            values.put("so2",this.so2);

        return values;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" + 
            " location='" + getLocation() + "'" +
            ", timestamp='" + getTimestamp() + "'" +
            ", temperature='" + getTemperature() + "'" +
            ", humidity='" + getHumidity() + "'" +
            ", pressure='" + getPressure() + "'" +
            ", precipIntensity='" + getPrecipIntensity() + "'" +
            ", o3='" + getO3() + "'" +
            ", co='" + getCo() + "'" +
            ", no2='" + getNo2() + "'" +
            ", pm10='" + getPm10() + "'" +
            ", pm25='" + getPm25() + "'" +
            ", so2='" + getSo2() + "'" +
            "}";
    }

}