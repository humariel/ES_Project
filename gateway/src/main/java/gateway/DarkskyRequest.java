package gateway;

import gateway.gateway.RegularDataDict;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class DarkskyRequest {

    private double latitude;
    private double longitude;
    private String timezone;
    private RegularDataDict currently;
    private int offset;

    public DarkskyRequest() {
        super();
    }

    public DarkskyRequest(double latitude, double longitude, String timezone, RegularDataDict currently,
    int offset) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.timezone = timezone;
        this.currently = currently;
        this.offset = offset;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getTimezone() {
        return timezone;
    }

    public RegularDataDict getCurrently() {
        return currently;
    }

    @Override
    public String toString() {
        return "DarkSkyRequest{" +
        "latitude=" + latitude +
        ", longitude=" + longitude +
        ", timezone='" + timezone + '\'' +
        ", currently=" + currently +
        ", offset=" + offset +
        '}';
    }
}