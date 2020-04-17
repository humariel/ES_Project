package breatheeasy.app.Requests;

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

    public RegularDataDict() {
        super();
    }

    public RegularDataDict(double temperature, double apparentTemperature, double dewPoint, double humidity,
                           double pressure, double ozone, int precipIntensity, int precipProbability, int cloudCover,
                           int uvIndex, int visibility) {

        this.temperature = temperature;
        this.apparentTemperature = apparentTemperature;
        this.dewPoint = dewPoint;
        this.humidity = humidity;
        this.pressure = pressure;
        this.ozone = ozone;
        this.precipIntensity = precipIntensity;
        this.precipProbability = precipProbability;
        this.cloudCover = cloudCover;
        this.uvIndex = uvIndex;
        this.visibility = visibility;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getApparentTemperature() {
        return apparentTemperature;
    }

    public double getDewPoint() {
        return dewPoint;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getPressure() {
        return pressure;
    }

    public double getOzone() {
        return ozone;
    }

    public int getPrecipIntensity() {
        return precipIntensity;
    }

    public int getPrecipProbability() {
        return precipProbability;
    }

    public int getCloudCover() {
        return cloudCover;
    }

    public int getUvIndex() {
        return uvIndex;
    }

    public int getVisibility() {
        return visibility;
    }

    @Override
    public String toString() {
        return "RegularDataDict{" +
                "temperature=" + temperature +
                ", apparentTemperature=" + apparentTemperature +
                ", dewPoint=" + dewPoint +
                ", humidity=" + humidity +
                ", pressure=" + pressure +
                ", ozone=" + ozone +
                ", precipIntensity=" + precipIntensity +
                ", precipProbability=" + precipProbability +
                ", cloudCover=" + cloudCover +
                ", uvIndex=" + uvIndex +
                ", visibility=" + visibility +
                '}';
    }
}
