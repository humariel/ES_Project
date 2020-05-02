package application;

public class AlarmCondition {

    private String type;
    private String operation;
    private int value;


    public AlarmCondition() {
    }

    public AlarmCondition(String type, String operation, int value) {
        this.type = type;
        this.operation = operation;
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

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "{" +
            " type='" + getType() + "'" +
            ", operation='" + getOperation() + "'" +
            ", value='" + getValue() + "'" +
            "}";
    }
    
}