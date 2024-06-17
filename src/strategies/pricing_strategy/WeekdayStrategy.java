package strategies.pricing_strategy;

import models.VehicleType;
import utils.DateTimeUtil;

import java.util.Date;

public class WeekdayStrategy implements CalculateFeeStrategy{
    @Override
    public double calculateFees(Date entryTime, Date exitTime, VehicleType vehicleType) {
        return DateTimeUtil.calculateHour(entryTime,exitTime)*10;
    }
}
