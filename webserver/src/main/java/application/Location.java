package application;

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
    public String toString() {
        return "{" +
            " type='" + getType() + "'" +
            ", coordinates='" + getCoords() + 
            "}";
    }

}