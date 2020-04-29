package application;

import java.lang.Math;

public class Simulator {

    private final double T_RANDOM_VARIATION = 0.001;
    private final double SECONDS_IN_DAY = 86400;
    //how many seconds between simulations
    private final double REQUEST_RATE = 1;
    private final double T_INCREMENT = (1/SECONDS_IN_DAY) * REQUEST_RATE;

    //temperature
    private final double TEMPERATURE_MIN = 5;
    private final double TEMPERATURE_MAX = 40;
    private double TEMPERATURE_T = 0;
    //humidity
    private final double HUMIDITY_MIN = 20;
    private final double HUMIDITY_MAX = 80;
    private double  HUMIDITY_T = 0.1;
    //pressure
    private final double PRESSURE_MIN = 5;
    private final double PRESSURE_MAX = 40;
    private double PRESSURE_T = 0.2;
    //precipitation intensity
    private final double PRECIPITATION_MIN = 20;
    private final double PRECIPITATION_MAX = 80;
    private double PRECIPITATION_T = 0.3;

    //ozone
    private final double O3_MIN = 0;
    private final double O3_MAX = 100;
    private double O3_T = 0.4;
    //carbon monoxide
    private final double CO_MIN = 20;
    private final double CO_MAX = 80;
    private double CO_T = 0.5;
    //nitrogen dioxide
    private final double NO2_MIN = 20;
    private final double NO2_MAX = 80;
    private double NO2_T = 0.6;
    //inhalable particulate matter (<10µm)
    private final double PM10_MIN = 0;
    private final double PM10_Max = 100;
    private double PM10_T = 0.7;
    //inhalable particulate matter (<25µm)
    private final double PM25_MIN = 0;
    private final double PM25_MAX = 100;
    private double PM25_T = 0.8;
    //sulfur dioxide
    private final double SO2_MIN = 20;
    private final double SO2_MAX = 80;
    private double SO2_T = 0.9;

    public Simulator() {

    }

    public Entity simulate(String id, Location location) {
        Entity entity = new Entity();

        entity.setId(id);
        entity.setLocation(location);

        entity.setTemperature(simulateValue(temperatureSin(), TEMPERATURE_MIN, TEMPERATURE_MAX));
        entity.setHumidity(simulateValue(humiditySin(), HUMIDITY_MIN, HUMIDITY_MAX));
        entity.setPressure(simulateValue(pressureSin(), PRESSURE_MIN, PRESSURE_MAX));
        entity.setPrecipIntensity((int)simulateValue(precipitationSin(), PRECIPITATION_MIN, PRECIPITATION_MAX));

        entity.setO3(simulateValue(o3Sin(), O3_MIN, O3_MAX));
        entity.setCo(simulateValue(coSin(), CO_MIN, CO_MAX));
        entity.setNo2(simulateValue(no2Sin(), NO2_MIN, NO2_MAX));
        entity.setPm10(simulateValue(pm10Sin(), PM10_MIN, PM10_Max));
        entity.setPm25(simulateValue(pm25Sin(), PM25_MIN, PM25_MAX));
        entity.setSo2(simulateValue(so2Sin(), SO2_MIN, SO2_MAX));

        return entity;
    }

    private double simulateValue(double sin, double min, double max) {
        return sin*(max-min) + min;
    }

    private double temperatureSin() {
        double res = Math.sin(TEMPERATURE_T);
        TEMPERATURE_T += T_INCREMENT + Math.random()*T_RANDOM_VARIATION;
        return res;
    }

    private double humiditySin() {
        double res = Math.sin(HUMIDITY_T);
        HUMIDITY_T += T_INCREMENT + Math.random()*T_RANDOM_VARIATION;
        return res;
    }

    private double pressureSin() {
        double res = Math.sin(PRESSURE_T);
        PRESSURE_T += T_INCREMENT + Math.random()*T_RANDOM_VARIATION;
        return res;
    }

    private double precipitationSin() {
        double res = Math.sin(PRECIPITATION_T);
        PRECIPITATION_T += T_INCREMENT + Math.random()*T_RANDOM_VARIATION;
        return res;
    }

    private double o3Sin() {
        double res = Math.sin(O3_T);
        O3_T += T_INCREMENT + Math.random()*T_RANDOM_VARIATION;
        return res;
    }

    private double coSin() {
        double res = Math.sin(CO_T);
        CO_T += T_INCREMENT + Math.random()*T_RANDOM_VARIATION;
        return res;
    }

    private double no2Sin() {
        double res = Math.sin(NO2_T);
        NO2_T += T_INCREMENT + Math.random()*T_RANDOM_VARIATION;
        return res;
    }

    private double pm10Sin() {
        double res = Math.sin(PM10_T);
        PM10_T += T_INCREMENT + Math.random()*T_RANDOM_VARIATION;
        return res;
    }

    private double pm25Sin() {
        double res = Math.sin(PM25_T);
        PM25_T += T_INCREMENT + Math.random()*T_RANDOM_VARIATION;
        return res;
    }

    private double so2Sin() {
        double res = Math.sin(SO2_T);
        SO2_T += T_INCREMENT + Math.random()*T_RANDOM_VARIATION;
        return res;
    }

}
