package PECS;

public class Vehicle {

    private final String label;
    private double mileage;
    private String older;
    private String type;

    public Vehicle(String label, double mileage, String older, String type) {
        this.label = label;
        this.mileage = mileage;
        this.older = older;
        this.type = type;
    }

    public String getLabel() {
        return label;
    }

    public double getMileage() {
        return mileage;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }

    public String getOlder() {
        return older;
    }

    public void setOlder(String older) {
        this.older = older;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
