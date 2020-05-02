package application;

import java.util.Random;

public class Simulator {

    private String id;
    private Location location;
    private Random random;

    //temperature    
    private double TEMPERATURE;
    private final double TEMPERATURE_AVG = 25;
    private final double TEMPERATURE_STD = 20;
    //humidity
    private double  HUMIDITY;
    private final double HUMIDITY_AVG = 30;
    private final double HUMIDITY_STD = 10;
    //pressure
    private double PRESSURE;
    private final double PRESSURE_AVG = 100;
    private final double PRESSURE_STD = 200;
    //precipitation intensity
    private double PRECIPITATION;
    private final double PRECIPITATION_AVG = 15;
    private final double PRECIPITATION_STD = 5;

    //ozone
    private double O3;
    private final double O3_AVG = 25;
    private final double O3_STD = 20;
    //carbon monoxide
    private double CO;
    private final double CO_AVG = 100;
    private final double CO_STD = 150;
    //nitrogen dioxide
    private double NO2;
    private final double NO2_AVG = 15;
    private final double NO2_STD = 15;
    //inhalable particulate matter (<10µm)
    private double PM10;
    private final double PM10_AVG = 20;
    private final double PM10_STD = 10;
    //inhalable particulate matter (<25µm)
    private double PM25;
    private final double PM25_AVG = 20;
    private final double PM25_STD = 10;
    //sulfur dioxide
    private double SO2;
    private final double SO2_AVG = 5;
    private final double SO2_STD = 2;

    public Simulator(String id, Location location) {
        this.random = new Random();

        this.id =  id;
        this.location = location;

        this.TEMPERATURE = gaussian(this.TEMPERATURE_STD, this.TEMPERATURE_AVG);
        this.HUMIDITY = gaussian(this.HUMIDITY_STD, this.HUMIDITY_AVG);
        this.PRESSURE = gaussian(this.PRESSURE_STD, this.PRESSURE_AVG);
        this.PRECIPITATION = gaussian(this.PRECIPITATION_STD, this.PRECIPITATION_AVG);

        this.O3 = gaussian(this.O3_STD, this.O3_AVG);
        this.CO = gaussian(this.CO_STD, this.CO_AVG);
        this.NO2 = gaussian(this.NO2_STD, this.NO2_AVG);
        this.PM10 = gaussian(this.PM10_STD, this.PM10_AVG);
        this.PM25 = gaussian(this.PM25_STD, this.PM25_AVG);
        this.SO2 = gaussian(this.SO2_STD, this.SO2_AVG);
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

    private double gaussian(double std, double mean) {
        return random.nextGaussian()*std + mean;
    }

    public Value simulate() {
        Value entity = new Value();

        entity.setId(this.id);
        entity.setLocation(this.location);

        entity.setTemperature(nextTemperature(this.TEMPERATURE_STD, this.TEMPERATURE_AVG));
        entity.setHumidity(nextHumidity(this.HUMIDITY_STD, this.HUMIDITY_AVG));
        entity.setPressure(nextPressure(this.PRESSURE_STD, this.PRESSURE_AVG));
        /* entity.setPrecipIntensity((int) nextPrecipitation(this.PRECIPITATION_STD, this.PRECIPITATION_AVG));

        entity.setO3(nextO3(this.O3_STD, this.O3_AVG));
        entity.setCo(nextCo(this.CO_STD, this.CO_AVG));
        entity.setNo2(nextNo2(this.NO2_STD, this.NO2_AVG)); */
        entity.setPm10(nextPm10(this.PM10_STD, this.PM10_AVG));
        /* entity.setPm25(nextPm25(this.PM25_STD, this.PM25_AVG));
        entity.setSo2(nextSo2(this.SO2_STD, this.SO2_AVG)); */

        return entity;
    }

    private double nextTemperature(double std, double mean) {
        this.TEMPERATURE += random.nextGaussian()*0.1;
        if(this.TEMPERATURE < 0) this.TEMPERATURE = 0;
        else if(this.TEMPERATURE > mean + std) this.TEMPERATURE = mean + std;
        return this.TEMPERATURE;
    }

    private double nextHumidity(double std, double mean) {
        this.HUMIDITY += random.nextGaussian()*0.1;
        if(this.HUMIDITY < 0) this.HUMIDITY = 0;
        else if(this.HUMIDITY >  mean + std) this.HUMIDITY = mean + std;
        return this.HUMIDITY;
    }

    private double nextPressure(double std, double mean) {
        this.PRESSURE += random.nextGaussian()*0.1;
        if(this.PRESSURE < 0) this.PRESSURE = 0;
        else if(this.PRESSURE > mean + std) this.PRESSURE = mean + std;
        return this.PRESSURE;
    }

    private double nextPrecipitation(double std, double mean) {
        this.PRECIPITATION += random.nextGaussian()*0.1;
        if(this.PRECIPITATION < 0) this.PRECIPITATION = 0;
        else if(this.PRECIPITATION > mean + std) this.PRECIPITATION = mean + std;
        return this.PRECIPITATION;
    }

    private double nextO3(double std, double mean) {
        this.O3 += random.nextGaussian()*0.1;
        if(this.O3 < 0) this.O3 = 0;
        else if(this.O3 > mean + std) this.O3 = mean + std;
        return this.O3;
    }

    private double nextCo(double std, double mean) {
        this.CO += random.nextGaussian()*0.1;
        if(this.CO < 0) this.CO = 0;
        else if(this.CO > mean + std) this.CO = mean + std;
        return this.CO;
    }

    private double nextNo2(double std, double mean) {
        this.NO2 += random.nextGaussian()*0.1;
        if(this.NO2 < 0) this.NO2 = 0;
        else if(this.NO2 > mean + std) this.NO2 = mean + std;
        return this.NO2;
    }

    private double nextPm10(double std, double mean) {
        this.PM10 += random.nextGaussian()*0.1;
        if(this.PM10 < 0) this.PM10 = 0;
        else if(this.PM10 > mean + std) this.PM10 = mean + std;
        return this.PM10;
    }

    private double nextPm25(double std, double mean) {
        this.PM25 += random.nextGaussian()*0.1;
        if(this.PM25 < 0) this.PM25 = 0;
        else if(this.PM25 > mean + std) this.PM25 = mean + std;
        return this.PM25;
    }

    private double nextSo2(double std, double mean) {
        this.SO2 += random.nextGaussian()*0.1;
        if(this.SO2 < 0) this.SO2 = 0;
        else if(this.SO2 > mean + std) this.SO2 = mean + std;
        return this.SO2;

    }

}
