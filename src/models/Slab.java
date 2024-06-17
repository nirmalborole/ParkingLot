package models;

public class Slab extends BaseModel{
    private VehicleType vehicleType;
    private  int statHour;
    private int endHour;
    private double pricePerhour;

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public int getStatHour() {
        return statHour;
    }

    public void setStatHour(int statHour) {
        this.statHour = statHour;
    }

    public int getEndHour() {
        return endHour;
    }

    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }

    public double getPricePerhour() {
        return pricePerhour;
    }

    public void setPricePerhour(double pricePerhour) {
        this.pricePerhour = pricePerhour;
    }
}
