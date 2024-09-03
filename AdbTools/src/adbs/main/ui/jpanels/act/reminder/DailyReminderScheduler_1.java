package adbs.main.ui.jpanels.act.reminder;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DailyReminderScheduler_1 {

    public static void main(String[] args) {
        // 创建一个ScheduledExecutorService实例
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

        // 设置提醒时间
        String time1 = "13:00:00";
        String time2 = "16:00:00";

        // 定义提醒任务
        Runnable reminderTask1 = () -> {
            System.out.println("Reminder 1: It's " + LocalDateTime.now().toString() + " now!");
            // 在这里添加你需要执行的操作
        };

        Runnable reminderTask2 = () -> {
            System.out.println("Reminder 2: It's " + LocalDateTime.now().toString() + " now!");
            // 在这里添加你需要执行的操作
        };

        // 调度第一个提醒
        scheduleDailyReminder(scheduler, time1, reminderTask1);

        // 调度第二个提醒
        scheduleDailyReminder(scheduler, time2, reminderTask2);

        // 防止主线程立即退出
        try {
            Thread.sleep(Long.MAX_VALUE);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Interrupted");
        }
    }

    /**
     * 在每天的特定时间给出提醒。
     *
     * @param scheduler 调度器
     * @param time      提醒时间，格式为 "HH:mm:ss"
     * @param task      提醒任务
     */
    private static void scheduleDailyReminder(ScheduledExecutorService scheduler, String time, Runnable task) {
        // 创建提醒时间解析器
        TimeParser timeParser = new TimeParser(time);
        // 计算延迟时间
        long delayInSeconds = timeParser.calculateDelayInSeconds(LocalDateTime.now());
        // 安排任务
        timeParser.scheduleReminder(scheduler, task, delayInSeconds);
    }

    /**
     * 时间解析器类。
     */
    private static class TimeParser {
        private final LocalTime reminderTime;
        private final int secondsInDay;

        public TimeParser(String time) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            this.reminderTime = LocalTime.parse(time, formatter);
            this.secondsInDay = 24 * 60 * 60;
        }

        public long calculateDelayInSeconds(LocalDateTime now) {
            LocalDateTime reminderDateTime = LocalDateTime.of(now.toLocalDate(), reminderTime);

            // 如果提醒时间已经过去，则计算明天的提醒时间
            if (!reminderDateTime.isAfter(now)) {
                reminderDateTime = reminderDateTime.plusDays(1);
            }

            // 计算距离提醒时间的延迟时间（以秒为单位）
            return now.until(reminderDateTime, ChronoUnit.SECONDS);
        }

        public void scheduleReminder(ScheduledExecutorService scheduler, Runnable task, long delayInSeconds) {
            // 安排任务
            scheduler.scheduleAtFixedRate(task, delayInSeconds, secondsInDay, TimeUnit.SECONDS);
        }
    }
}
