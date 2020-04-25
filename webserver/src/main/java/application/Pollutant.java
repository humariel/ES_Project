package application;

public class Pollutant {

    private String display_name;
    private String full_name;
    private Breezo_Concentration concentration;

    public String getDisplay_name() {
        return this.display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getFull_name() {
        return this.full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public Breezo_Concentration getConcentration() {
        return this.concentration;
    }

    public void setConcentration(Breezo_Concentration concentration) {
        this.concentration = concentration;
    }

    @Override
    public String toString() {
        return "{" +
            " display_name='" + getDisplay_name() + "'" +
            ", full_name='" + getFull_name() + "'" +
            ", concentration='" + getConcentration() + "'" +
            "}";
    }

}