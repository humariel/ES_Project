package application;

public class AlarmCondition {

    private String type;
    private String operation;
    private Double threshold;
    private Double value;

    public AlarmCondition() {
    }

    public AlarmCondition(String type, String operation, Double threshold, Double value) {
        this.type = type;
        this.operation = operation;
        this.threshold = threshold;
        this.value = value;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOperation() {
        return this.operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Double getThreshold() {
        return this.threshold;
    }

    public void setThreshold(Double threshold) {
        this.threshold = threshold;
    }

    public Double getValue() {
        return this.value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "{" +
            " type='" + getType() + "'" +
            ", operation='" + getOperation() + "'" +
            ", threshold='" + getThreshold() + "'" +
            ", value='" + getValue() + "'" +
            "}";
    }

    
}