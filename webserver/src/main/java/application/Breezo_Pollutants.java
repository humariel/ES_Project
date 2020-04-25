package application;

public class Breezo_Pollutants {
    
    private Pollutant co;
    private Pollutant no2;
    private Pollutant o3;
    private Pollutant pm10;
    private Pollutant pm25;

    public Breezo_Pollutants() {
    }

    public Breezo_Pollutants(Pollutant co, Pollutant no2, Pollutant o3, Pollutant pm10, Pollutant pm25, Pollutant so2) {
        this.co = co;
        this.no2 = no2;
        this.o3 = o3;
        this.pm10 = pm10;
        this.pm25 = pm25;
        this.so2 = so2;
    }

    public Pollutant getCo() {
        return this.co;
    }

    public void setCo(Pollutant co) {
        this.co = co;
    }

    public Pollutant getNo2() {
        return this.no2;
    }

    public void setNo2(Pollutant no2) {
        this.no2 = no2;
    }

    public Pollutant getO3() {
        return this.o3;
    }

    public void setO3(Pollutant o3) {
        this.o3 = o3;
    }

    public Pollutant getPm10() {
        return this.pm10;
    }

    public void setPm10(Pollutant pm10) {
        this.pm10 = pm10;
    }

    public Pollutant getPm25() {
        return this.pm25;
    }

    public void setPm25(Pollutant pm25) {
        this.pm25 = pm25;
    }

    public Pollutant getSo2() {
        return this.so2;
    }

    public void setSo2(Pollutant so2) {
        this.so2 = so2;
    }

    @Override
    public String toString() {
        return "{" +
            " co='" + getCo() + "'" +
            ", no2='" + getNo2() + "'" +
            ", o3='" + getO3() + "'" +
            ", pm10='" + getPm10() + "'" +
            ", pm25='" + getPm25() + "'" +
            ", so2='" + getSo2() + "'" +
            "}";
    }
    private Pollutant so2;
}