package application;

import java.io.Serializable;
import com.google.gson.JsonObject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Entity {

    private long id;
    private double latitude;
    private double longitude;
    private String timezone;
    private RegularDataDict currently;
    private int offset;

    public String toJsonKafka(){
        JsonObject obj = new JsonObject();
        obj.addProperty("entity_id", id);
        obj.addProperty("latitude", latitude);
        obj.addProperty("longitude", longitude);
        obj.addProperty("timezone", timezone);
        obj.addProperty("temperature", currently.getTemperature());
        obj.addProperty("apparentTemperature", currently.getApparentTemperature());
        obj.addProperty("dewPoint", currently.getDewPoint());
        obj.addProperty("humidity", currently.getHumidity());
        obj.addProperty("pressure", currently.getPressure());
        obj.addProperty("ozone", currently.getOzone());
        obj.addProperty("precipIntensity", currently.getPrecipIntensity());
        obj.addProperty("precipProbability", currently.getPrecipProbability());
        obj.addProperty("cloudCover", currently.getCloudCover());
        obj.addProperty("uvIndex", currently.getUvIndex());
        obj.addProperty("visibility", currently.getVisibility());
        return obj.toString();
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

    public String getTimezone() {
        return this.timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public RegularDataDict getCurrently() {
        return this.currently;
    }

    public void setCurrently(RegularDataDict currently) {
        this.currently = currently;
    }

    public int getOffset() {
        return this.offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    @Override
    public String toString() {
        return "{" +
            " latitude='" + getLatitude() + "'" +
            ", longitude='" + getLongitude() + "'" +
            ", timezone='" + getTimezone() + "'" +
            ", currently='" + getCurrently() + "'" +
            ", offset='" + getOffset() + "'" +
            "}";
    }
    

}