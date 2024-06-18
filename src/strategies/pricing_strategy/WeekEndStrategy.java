package strategies.pricing_strategy;

import models.Slab;
import models.VehicleType;
import repositories.SlabRepository;
import utils.DateTimeUtil;

import java.util.Date;
import java.util.List;

public class WeekEndStrategy implements CalculateFeeStrategy{
    private SlabRepository slabRepository;

    public WeekEndStrategy(SlabRepository slabRepository) {
        this.slabRepository = slabRepository;
    }

    @Override
    public double calculateFees(Date entryTime, Date exitTime, VehicleType vehicleType) {
        List<Slab> slabs = slabRepository.getSlabsByVehicleType(vehicleType);
        int hours = DateTimeUtil.calculateHour(entryTime, exitTime);
        double totalAmount=0;
        for (Slab slab : slabs) {
            if(hours >= slab.getStatHour()&& slab.getEndHour() != -1){
                if(hours >= slab.getEndHour()){
                   totalAmount+=(slab.getEndHour()-slab.getStatHour())*slab.getPricePerhour();
                }else {
                    totalAmount+= (hours-slab.getStatHour())* slab.getPricePerhour();
                }
            }else if(slab.getEndHour() == -1){
                totalAmount+=(hours-slab.getStatHour())*slab.getPricePerhour();
            }else {
                break;
            }
        }
        return totalAmount;
    }
}
