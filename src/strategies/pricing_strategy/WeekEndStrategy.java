package strategies.pricing_strategy;

import models.Slab;
import models.VehicleType;
import repositories.SlabRepository;
import utils.DateTimeUtil;

import java.util.Date;
import java.util.List;

public class WeekEndStrategy implements CalculateFeeStrategy{
    private SlabRepository slabRepository;
    @Override
    public double calculateFees(Date entryTime, Date exitTime, VehicleType vehicleType) {
        List<Slab> slabs = slabRepository.getSlabsByVehicleType(vehicleType);
        int hours = DateTimeUtil.calculateHour(entryTime, exitTime);

        return 0;
    }
}
