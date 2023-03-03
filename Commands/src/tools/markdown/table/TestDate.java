package tools.markdown.table;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TestDate {
    public static void main(String[] args) {
        // LocalDate localDate = LocalDate.now();
        // LocalDate localDate = LocalDate.of(2023, 2,1);
        // // 显示当前是第几号
        // System.out.println(localDate.getDayOfMonth());
        //
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        // calendar.setTime(new Date());

        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date parse = format.parse("2023-02-22");
            calendar.setTime(parse);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // 当前是第几天
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
        // 本月有几天
        System.out.println(calendar.getActualMaximum(Calendar.DAY_OF_MONTH));


    }

    // public static int getDayOfMonth() {
    //     // Calendar aCalendar = Calendar.getInstance(Locale.CHINA);
    //     // int day = aCalendar.getActualMaximum(Calendar.DATE);
    //     // System.out.println();
    //     int day = aCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    //     return day;
    // }
}
