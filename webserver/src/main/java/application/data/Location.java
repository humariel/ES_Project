package application.data;

import java.util.Objects;

public class Location {

    private String type;
    private double[] coords;

    public Location() {

    }

    public Location(String type, double[] coords) {
        this.type = type;
        this.coords = coords;
    }

    public Location(String type, double lat, double lon) {
        this.type = type;
        this.coords = new double[] {lat, lon};
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double[] getCoords() {
        return this.coords;
    }

    public void setCoords(double[] coords) {
        this.coords = coords;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Location)) {
            return false;
        }
        Location location = (Location) o;
        return Objects.equals(type, location.type) && Objects.equals(coords, location.coords);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, coords);
    }

    @Override
    public String toString() {
        return "{" +
            " type='" + getType() + "'" +
            ", coordinates=[" + getCoords()[0] + ", " + getCoords()[1] +"]" + 
            "}";
    }



}