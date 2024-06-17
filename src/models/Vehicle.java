package models;

public class Vehicle extends BaseModel{
    private String VaehicleNumber;
    private VehicleType vehicleType;

    public String getVaehicleNumber() {
        return VaehicleNumber;
    }

    public void setVaehicleNumber(String vaehicleNumber) {
        VaehicleNumber = vaehicleNumber;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }
}
