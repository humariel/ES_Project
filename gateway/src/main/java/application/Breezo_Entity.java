package application;

public class Breezo_Entity implements Entity{
    
    private String metadata;
    private Breezo_Data data;
    private String error;
    
    @Override
    public String toString() {
        return "{" +
            " metadata='" + getMetadata() + "'" +
            ", data='" + getData() + "'" +
            ", error='" + getError() + "'" +
            "}";
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




}