package strategies.pricing_strategy;

import models.VehicleType;

import java.util.Date;

public interface CalculateFeeStrategy {

    public double calculateFees(Date entryTime, Date exitTime, VehicleType vehicleType);
}
