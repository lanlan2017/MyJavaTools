package adbs.main.ui.jpanels.act;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateString {
    /**
     * 线程安全的获取yyyy-MM-dd格式的日期字符串。
     *
     * @return
     */
    public static String getDate_yyyyMMdd() {
        // 获取当前日期
        LocalDate currentDate = LocalDate.now();
        // 定义一个 DateTimeFormatter 使用 yyyy-MM-dd 的格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // 将 LocalDate 格式化为字符串
        String formattedDate = currentDate.format(formatter);
//        System.out.println("formattedDate = " + formattedDate);
        return formattedDate;
    }
}
