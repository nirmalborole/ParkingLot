package utils;

import java.util.Date;

public class DateTimeUtil {
    public static int calculateHour(Date start, Date end) {
        long diff=start.getTime()-end.getTime();
        long diffInSec= diff/1000;
        int hours=(int)Math.ceil((double) diffInSec/3600);
        return hours;
    }
}
