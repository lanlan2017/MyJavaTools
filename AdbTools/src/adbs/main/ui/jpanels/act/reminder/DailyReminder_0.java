package adbs.main.ui.jpanels.act.reminder;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 示例，在每天的13:00给出提醒，在每天的16:00给出提醒
 */
public class DailyReminder_0 {

    public static void main(String[] args) {
        // 创建一个ScheduledExecutorService实例
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

        // 设置提醒时间
        String time1 = "13:00";
        String time2 = "16:00";

        // 当前系统时间
        ZonedDateTime now = ZonedDateTime.now(ZoneId.systemDefault());

        // 定义提醒任务
        Runnable reminderTask = () -> {
            System.out.println("Reminder: It's " + LocalDateTime.now().toLocalTime() + " now!");
            // 在这里添加你需要执行的操作
        };

        // 调度第一个提醒
        scheduleReminder(scheduler, time1, now, reminderTask);

        // 调度第二个提醒
        scheduleReminder(scheduler, time2, now, reminderTask);
    }

    private static void scheduleReminder(ScheduledExecutorService scheduler, String time, ZonedDateTime now, Runnable task) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime reminderTime = LocalTime.parse(time, formatter);

        // 计算提醒的具体时间点
        LocalDateTime reminderDateTime = LocalDateTime.of(now.toLocalDate(), reminderTime);

        // 如果提醒时间已经过去，则计算明天的提醒时间
        if (!reminderDateTime.isAfter(now.toLocalDateTime())) {
            reminderDateTime = reminderDateTime.plusDays(1);
        }

        // 计算距离提醒时间的延迟时间
        long delay = now.until(ZonedDateTime.of(reminderDateTime, ZoneId.systemDefault()), java.time.temporal.ChronoUnit.MILLIS);

        // 安排任务
        scheduler.scheduleAtFixedRate(task, delay, TimeUnit.DAYS.toMillis(1), TimeUnit.MILLISECONDS);
    }
}
