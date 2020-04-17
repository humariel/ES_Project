package application;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RegularDataDict {

    private double temperature;
    private double apparentTemperature;
    private double dewPoint;
    private double humidity;
    private double pressure;
    private double ozone;
    private int precipIntensity;
    private int precipProbability;
    private int cloudCover;
    private int uvIndex;
    private int visibility;

    public double getTemperature() {
        return this.temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getApparentTemperature() {
        return this.apparentTemperature;
    }

    public void setApparentTemperature(double apparentTemperature) {
        this.apparentTemperature = apparentTemperature;
    }

    public double getDewPoint() {
        return this.dewPoint;
    }

    public void setDewPoint(double dewPoint) {
        this.dewPoint = dewPoint;
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

    public double getOzone() {
        return this.ozone;
    }

    public void setOzone(double ozone) {
        this.ozone = ozone;
    }

    public int getPrecipIntensity() {
        return this.precipIntensity;
    }

    public void setPrecipIntensity(int precipIntensity) {
        this.precipIntensity = precipIntensity;
    }

    public int getPrecipProbability() {
        return this.precipProbability;
    }

    public void setPrecipProbability(int precipProbability) {
        this.precipProbability = precipProbability;
    }

    public int getCloudCover() {
        return this.cloudCover;
    }

    public void setCloudCover(int cloudCover) {
        this.cloudCover = cloudCover;
    }

    public int getUvIndex() {
        return this.uvIndex;
    }

    public void setUvIndex(int uvIndex) {
        this.uvIndex = uvIndex;
    }

    public int getVisibility() {
        return this.visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public RegularDataDict temperature(double temperature) {
        this.temperature = temperature;
        return this;
    }

    public RegularDataDict apparentTemperature(double apparentTemperature) {
        this.apparentTemperature = apparentTemperature;
        return this;
    }

    public RegularDataDict dewPoint(double dewPoint) {
        this.dewPoint = dewPoint;
        return this;
    }

    public RegularDataDict humidity(double humidity) {
        this.humidity = humidity;
        return this;
    }

    public RegularDataDict pressure(double pressure) {
        this.pressure = pressure;
        return this;
    }

    public RegularDataDict ozone(double ozone) {
        this.ozone = ozone;
        return this;
    }

    public RegularDataDict precipIntensity(int precipIntensity) {
        this.precipIntensity = precipIntensity;
        return this;
    }

    public RegularDataDict precipProbability(int precipProbability) {
        this.precipProbability = precipProbability;
        return this;
    }

    public RegularDataDict cloudCover(int cloudCover) {
        this.cloudCover = cloudCover;
        return this;
    }

    public RegularDataDict uvIndex(int uvIndex) {
        this.uvIndex = uvIndex;
        return this;
    }

    public RegularDataDict visibility(int visibility) {
        this.visibility = visibility;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " temperature='" + getTemperature() + "'" +
            ", apparentTemperature='" + getApparentTemperature() + "'" +
            ", dewPoint='" + getDewPoint() + "'" +
            ", humidity='" + getHumidity() + "'" +
            ", pressure='" + getPressure() + "'" +
            ", ozone='" + getOzone() + "'" +
            ", precipIntensity='" + getPrecipIntensity() + "'" +
            ", precipProbability='" + getPrecipProbability() + "'" +
            ", cloudCover='" + getCloudCover() + "'" +
            ", uvIndex='" + getUvIndex() + "'" +
            ", visibility='" + getVisibility() + "'" +
            "}";
    }


}