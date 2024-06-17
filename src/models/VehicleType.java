package models;

public enum VehicleType {
    BIKE,
    CAR,
    TRUCK,
    EV_CAR;
    public static VehicleType getVehicleType(String type) {
        for (VehicleType value : VehicleType.values()) {
            if(type.equalsIgnoreCase(value.toString())){
                return value;
            }
        }
        throw new IllegalArgumentException("unsupported tyepe");
    }
}
