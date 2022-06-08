package tools.format.date;

import java.text.SimpleDateFormat;

public class DateFormatters {
    /**
     * yyyy-MM-dd 年月日，以横线作为分隔符。例如2000-01-02
     */
    public static SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");
    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static SimpleDateFormat yyyyMMddHHmmss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
}
