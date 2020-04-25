package application;

public class Breezo_Concentration {

    private double value;
    private String untis;

    public double getValue() {
        return this.value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getUntis() {
        return this.untis;
    }

    @Override
    public String toString() {
        return "{" +
            " value='" + getValue() + "'" +
            ", untis='" + getUntis() + "'" +
            "}";
    }


}