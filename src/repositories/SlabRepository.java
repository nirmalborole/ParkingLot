package repositories;

import models.Slab;
import models.Vehicle;
import models.VehicleType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SlabRepository {
    private Map<Integer, Slab>map;

    public SlabRepository(Map<Integer, Slab> map) {
        this.map = map;
    }
    public SlabRepository() {
        this.map = new HashMap<>();
    }

    public List<Slab> getSlabsByVehicleType(VehicleType vehicleType){
        List<Slab>slabs=new ArrayList<>();
        for (Map.Entry<Integer, Slab> entry : this.map.entrySet()) {
            Slab value = entry.getValue();
            if(value.getVehicleType().equals(vehicleType)){
                slabs.add(value);
            }
        }
        return slabs;
    }
}
