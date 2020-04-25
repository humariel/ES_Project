package application;

public class Breezo_Data {

    private String datetime;
    private Boolean data_available;
    private Breezo_Pollutants pollutants;

    public String getDatetime() {
        return this.datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public Boolean isData_available() {
        return this.data_available;
    }

    public Boolean getData_available() {
        return this.data_available;
    }

    public void setData_available(Boolean data_available) {
        this.data_available = data_available;
    }

    public Breezo_Pollutants getPollutants() {
        return this.pollutants;
    }

    public void setPollutants(Breezo_Pollutants pollutants) {
        this.pollutants = pollutants;
    }

    @Override
    public String toString() {
        return "{" +
            " datetime='" + getDatetime() + "'" +
            ", data_available='" + isData_available() + "'" +
            ", pollutants='" + getPollutants() + "'" +
            "}";
    }
}
