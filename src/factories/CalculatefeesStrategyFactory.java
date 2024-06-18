package factories;

import repositories.SlabRepository;
import strategies.pricing_strategy.CalculateFeeStrategy;
import strategies.pricing_strategy.WeekEndStrategy;
import strategies.pricing_strategy.WeekdayStrategy;

import java.util.Calendar;
import java.util.Date;

public class CalculatefeesStrategyFactory {
    private SlabRepository slabRepository;

    public CalculatefeesStrategyFactory(SlabRepository slabRepository) {
        this.slabRepository = slabRepository;
    }

    public CalculateFeeStrategy getCalculateFeeStrategy(Date exitDate) {
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(exitDate);
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        boolean isWeekEnd= day==Calendar.SATURDAY || day== Calendar.SUNDAY;
        CalculateFeeStrategy calculateFeeStrategy;
        if(isWeekEnd){
            calculateFeeStrategy=new WeekEndStrategy(slabRepository);
        }else{
            calculateFeeStrategy=new WeekdayStrategy();
        }
        return calculateFeeStrategy;
    }
}
