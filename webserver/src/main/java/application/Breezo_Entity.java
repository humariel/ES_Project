package application;

public class Breezo_Entity implements Entity{
    
    private double latitude;
    private double longitude;
    private String metadata;
    private Breezo_Data data;
    private String error;
    private long timestamp;
    
    @Override
    public String toString() {
        return "{" +
            " latitude='" + getLatitude() + "'" +
            ", longitude='" + getLongitude() + "'" +
            " metadata='" + getMetadata() + "'" +
            ", data='" + getData() + "'" +
            ", error='" + getError() + "'" +
            ", timestamp=" + getTimestamp() + "''" +
            "}";
    }

    public double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getMetadata() {
        return this.metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public Breezo_Data getData() {
        return this.data;
    }

    public void setData(Breezo_Data data) {
        this.data = data;
    }

    public String getError() {
        return this.error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

}