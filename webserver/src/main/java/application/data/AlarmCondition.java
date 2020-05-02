package application.data;

public class AlarmCondition {

    private String type;
    private int[][] range;


    public AlarmCondition() {
    }

    public AlarmCondition(String type, int[][] range) {
        this.type = type;
        this.range = range;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int[][] getRange() {
        return this.range;
    }

    public void setRange(int[][] range) {
        this.range = range;
    }

    @Override
    public String toString() {
        return "{" +
            " type='" + getType() + "'" +
            ", range='" + getRange() + "'" +
            "}";
    }

    
}