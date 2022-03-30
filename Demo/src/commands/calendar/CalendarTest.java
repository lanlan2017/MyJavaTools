package commands.calendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalendarTest {
    static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    static SimpleDateFormat dateFormatSmall = new SimpleDateFormat("HH:mm");

    public static void main(String[] args) {
        // test1();
        try {
            // 设置火车出发时间：
            Date date = dateFormat.parse("2022-03-11 20:36");
            System.out.println("火车出发：" + dateFormat.format(date));
            // 计算去火车站的时间
            Date date1 = quHuoCheZhan(date);
            System.out.println("地铁开始：" + dateFormat.format(date1));
            // 飞机到达时间
            Date feiJiDaoDa = dateFormat.parse("2022-03-11 17:50");
            System.out.println("飞机到达：" + dateFormat.format(feiJiDaoDa));

            printDiffHourDiffMinute(date1, feiJiDaoDa);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        // test3();
    }

    /**
     * 计算开始前往火车站的时间
     *
     * @param huoCheChuFa 火车出发时间
     * @return 开始前往火车站的时间
     */
    private static Date quHuoCheZhan(Date huoCheChuFa) {
        Calendar calendar = Calendar.getInstance();
        // calendar.set(2022, 3, 11, 20, 47);
        // calendar.set(2022, 2, 11, 20, 36);
        calendar.setTime(huoCheChuFa);
        // calendar.set(2022, 3, 11, 20, 47);
        // System.out.println("");
        System.out.println("    ——火车出发:" + dateFormat.format(calendar.getTime()));
        calendar.add(Calendar.MINUTE, -20);
        System.out.println("    候车20分钟:" + dateFormat.format(calendar.getTime()));
        calendar.add(Calendar.MINUTE, -44);
        System.out.println("    地铁44分钟:" + dateFormat.format(calendar.getTime()));
        return calendar.getTime();
    }

    private static void test3() {
        try {
            // Date date1 = dateFormat.parse("2011-03-11 20:47");
            Date date1 = dateFormat.parse("2011-03-11 19:32");
            Date date2 = dateFormat.parse("2011-03-11 17:50");
            // Date date2 = dateFormat.parse("2011-03-11 18:50");
            // Date date2 = dateFormat.parse("2011-03-11 20:46");

            // System.out.println("相差毫秒:" + diff);
            // System.out.println("相差秒数:" + diff / 1000);
            // System.out.println("相差分钟:" + diff / (60 * 1000));
            // System.out.println("相差小时:" + getDiffHour(diff));

            printDiffHourDiffMinute(date1, date2);

            // Calendar c = Calendar.getInstance();
            // Date date3 = new Date();

            // c.setTime(difference);
            // c.setTimeInMillis(difference);
            // System.out.println(dateFormat.format(date3));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static void printDiffHourDiffMinute(Date date1, Date date2) {
        Long diff = date1.getTime() - date2.getTime();
        // 相差的小时数
        long diffHour = getHour(diff);
        // 相差的分钟数
        long diffMinute = getMinute(diff);

        // 除去小时后相差的分钟数
        // long diffMinute = getDiffMinute(diff, diffHour);
        long diffMinuteMinusHours = diffMinuteMinusHours(diff);
        System.out.println(diffMinute + "分钟");
        System.out.println(diffHour + "小时" + diffMinuteMinusHours + "分钟");
    }

    /**
     * 获取毫秒中包含的分钟数
     *
     * @param millisecond 毫秒
     * @return 毫秒中包含的分钟数
     */
    private static long getMinute(Long millisecond) {
        return millisecond / (60 * 1000);
    }

    /**
     * 求毫秒求余掉小时后还剩多少分钟。
     *
     * @param diff 超过1小时的毫秒数
     * @return 去除小时后还剩多少分钟
     */
    private static long diffMinuteMinusHours(Long diff) {
        return (diff % (60 * 60 * 1000)) / ((60 * 1000));
    }

    /**
     * 获取毫秒包含的小时数
     *
     * @param millisecond 毫秒
     * @return 该有多少个小时
     */
    private static long getHour(Long millisecond) {
        return millisecond / (60 * 60 * 1000);
    }

    private static void test1() {
        // 获取今天的日历
        Calendar c = Calendar.getInstance();
        // 取出年
        System.out.println("年：" + c.get(Calendar.YEAR));
        // 取出月份
        System.out.println("月：" + c.get(Calendar.MONTH));
        // 取出日
        System.out.println("日：" + c.get(Calendar.DATE));
        System.out.println("-------------------------");

        // 分别设置年、月、日、小时、分钟、秒
        c.set(2003, 10, 23, 12, 32, 23); // 2003-11-23 12:32:23
        System.out.println("当前时间:" + dateFormat.format(c.getTime()));
        // 将Calendar的年前推1年
        c.add(Calendar.YEAR, -1); // 2002-11-23 12:32:23
        System.out.println("一年之前:" + dateFormat.format(c.getTime()));
        System.out.println("-------------------------");
        // 将Calendar的月前推8个月
        c.roll(Calendar.MONTH, -8); // 2002-03-23 12:32:23
        System.out.println("再8个月之前:" + dateFormat.format(c.getTime()));
    }
}
