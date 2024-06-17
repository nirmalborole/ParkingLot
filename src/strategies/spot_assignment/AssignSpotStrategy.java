package strategies.spot_assignment;

import Exceptions.NoSpotAvailableException;
import models.ParkingLot;
import models.Spot;
import models.VehicleType;

public interface AssignSpotStrategy {
    public Spot assignSpot(VehicleType vehicleType, ParkingLot parkingLot) throws NoSpotAvailableException;
}
