package application;

import com.google.gson.JsonObject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Darksky_Entity {

    private long id;
    private double latitude;
    private double longitude;
    private String timezone;
    private Darksky_Values currently;
    private int offset;

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

    public Darksky_Values getCurrently() {
        return this.currently;
    }

    public void setCurrently(Darksky_Values currently) {
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