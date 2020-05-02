package application;

public class Geometry {

    private String type;
    private Double[][][] coordinates;

    public Geometry() {
    }

    public Geometry(String type, Double[][][] coordinates) {
        this.type = type;
        this.coordinates = coordinates;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double[][][] getCoordinates() {
        return this.coordinates;
    }

    public void setCoordinates(Double[][][] coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        return "{" +
            " type='" + getType() + "'" +
            ", coordinates='" + getCoordinates() + "'" +
            "}";
    }

}