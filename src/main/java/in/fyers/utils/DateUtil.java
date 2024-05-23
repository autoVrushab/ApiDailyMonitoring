package in.fyers.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil {

    /**
     * Method to return current date in specified format
     * @param format - format in which the date is required
     * @return - date is the given format
     */
    public static String returnDateInSpecificFormat(String format){
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        Calendar startDate = Calendar.getInstance();
        Date date = startDate.getTime();
        return dateFormat.format(date);
    }

    /**
     * Method to return current date in specified format
     * @param format - format in which the date is required
     * @param daysToAdd - No of days to add
     * @return - date is the given format
     */
    public static String returnDateByAddingDays(String format, int daysToAdd){
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.DATE, daysToAdd);
        Date date = startDate.getTime();
        return dateFormat.format(date);
    }

    public static String convertEpocToDateTime(Long epoc){
        Date date = new Date(epoc);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        format.setTimeZone(TimeZone.getTimeZone("GMT+5:30"));
        return format.format(date);
    }
}
