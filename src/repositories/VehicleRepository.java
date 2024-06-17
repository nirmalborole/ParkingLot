package repositories;

import models.Vehicle;
import models.VehicleType;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class VehicleRepository {
    private Map<Integer, Vehicle>map;

    public VehicleRepository(Map<Integer, Vehicle> map) {
        this.map = map;
    }
    public VehicleRepository() {
        this.map = new HashMap<>();
    }
    private static int id=1;

    public Vehicle createIfNotexist(String vehicleNumber, VehicleType vehivleType){
        for (Map.Entry<Integer, Vehicle> entry : map.entrySet()) {
            Vehicle vehicle = entry.getValue();
            if(vehicle.getVaehicleNumber().equals(vehicleNumber)){
                return vehicle;
            }
        }
        Vehicle vehicle=new Vehicle();
        vehicle.setVaehicleNumber(vehicleNumber);
        vehicle.setVehicleType(vehivleType);
        vehicle.setId(id);
        map.put(id++,vehicle);
        return vehicle;
    }
}
