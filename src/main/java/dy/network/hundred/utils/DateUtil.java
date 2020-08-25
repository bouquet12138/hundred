package dy.network.hundred.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;


public class DateUtil {

    public static String getDay() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(new Date());
        return date;
    }

    public static String getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(new Date());
        return date;
    }

    public static String getOrderIdByTime(int user_id) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String newDate = sdf.format(new Date());
        String result = "";
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            result = result + random.nextInt(10);
        }
        return newDate + user_id + "-" + result;
    }

    public static int getHour() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        return calendar.get(11);
    }

    public static int getMinute() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        return calendar.get(12);
    }

    public static boolean isInTime() {
        int hour = getHour();

        if (hour >= 9 && hour < 21) {
            return true;
        }
        return false;
    }
}
