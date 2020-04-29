package application;

import java.util.Date;
import java.sql.Timestamp;

public class Entity {

    //metadata
    private String id;
    private double latitude;
    private double longitude;
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

    public Entity() {
        Date date = new Date();
        this.timestamp = new Timestamp(date.getTime()).getTime();
    }

    public Entity(String id, double latitude, double longitude, double temperature, double humidity, double pressure, int precipIntensity, double o3, double co, double no2, double pm10, double pm25, double so2) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
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

        Date date = new Date();
        this.timestamp = new Timestamp(date.getTime()).getTime();
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
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

    public Entity latitude(double latitude) {
        this.latitude = latitude;
        return this;
    }

    public Entity longitude(double longitude) {
        this.longitude = longitude;
        return this;
    }

    public Entity timestamp(long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public Entity temperature(double temperature) {
        this.temperature = temperature;
        return this;
    }

    public Entity humidity(double humidity) {
        this.humidity = humidity;
        return this;
    }

    public Entity pressure(double pressure) {
        this.pressure = pressure;
        return this;
    }

    public Entity precipIntensity(int precipIntensity) {
        this.precipIntensity = precipIntensity;
        return this;
    }

    public Entity o3(double o3) {
        this.o3 = o3;
        return this;
    }

    public Entity co(double co) {
        this.co = co;
        return this;
    }

    public Entity no2(double no2) {
        this.no2 = no2;
        return this;
    }

    public Entity pm10(double pm10) {
        this.pm10 = pm10;
        return this;
    }

    public Entity pm25(double pm25) {
        this.pm25 = pm25;
        return this;
    }

    public Entity so2(double so2) {
        this.so2 = so2;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " latitude='" + getLatitude() + "'" +
            ", longitude='" + getLongitude() + "'" +
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