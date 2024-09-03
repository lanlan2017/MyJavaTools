package adbs.main.ui.jpanels.act.reminder;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 每天特定时间给出提醒，精确到分钟的版本
 */
public final class DailyReminderScheduler_Second {

    // 私有构造函数，防止实例化
    private DailyReminderScheduler_Second() {
        throw new AssertionError("Utility class cannot be instantiated.");
    }

    // 静态成员变量，作为全局的调度器
    private static final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    public static void main(String[] args) {
        // 设置提醒时间
        //        String time1 = "13:00:00";
        //        String time2 = "16:00:00";


        String time1 = "20:59:00";
        String time2 = "21:00:00";

        // 定义提醒任务
        Runnable reminderTask1 = () -> {
            System.out.println("Reminder 1: It's " + LocalDateTime.now() + " now!");
            // 在这里添加你需要执行的操作
        };

        Runnable reminderTask2 = () -> {
            System.out.println("Reminder 2: It's " + LocalDateTime.now() + " now!");
            // 在这里添加你需要执行的操作
        };

        // 调度第一个提醒
        scheduleDailyReminder(time1, reminderTask1);

        // 调度第二个提醒
        scheduleDailyReminder(time2, reminderTask2);

        // 注册关闭钩子
        Runtime.getRuntime().addShutdownHook(new Thread(DailyReminderScheduler_Second::shutdownScheduler));

        // 防止主线程立即退出
        try {
            Thread.sleep(Long.MAX_VALUE);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Interrupted");
        }

        // 关闭程序
        // 这里使用 System.exit(0) 来模拟程序的正常退出
        System.exit(0);
    }

    /**
     * 在每天的特定时间给出提醒。
     *
     * @param time 提醒时间，格式为 "HH:mm:ss"
     * @param task 提醒任务
     */
    public static void scheduleDailyReminder(String time, Runnable task) {
        // 解析提醒时间
        LocalTime reminderTime = parseReminderTime(time);

        // 获取当前时间
        LocalDateTime now = LocalDateTime.now();

        // 计算延迟时间
        long delayInSeconds = calculateDelayInSeconds(now, reminderTime);
        System.out.println("提醒任务：" + time);

        // 安排任务
        scheduleReminder(task, delayInSeconds);
    }

    /**
     * 解析提醒时间字符串为LocalTime对象。
     *
     * @param time 提醒时间字符串，格式为 "HH:mm:ss"
     * @return 解析后的LocalTime对象
     */
    private static LocalTime parseReminderTime(String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        try {
            return LocalTime.parse(time, formatter);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid time format: " + time, e);
        }
    }

    /**
     * 计算从当前时间到提醒时间的延迟时间（以秒为单位）。
     *
     * @param now          当前时间
     * @param reminderTime 提醒时间
     * @return 延迟时间（秒）
     */
    private static long calculateDelayInSeconds(LocalDateTime now, LocalTime reminderTime) {
        LocalDateTime reminderDateTime = LocalDateTime.of(now.toLocalDate(), reminderTime);

        // 如果提醒时间已经过去，则计算明天的提醒时间
        if (!reminderDateTime.isAfter(now)) {
            reminderDateTime = reminderDateTime.plusDays(1);
        }

        // 计算距离提醒时间的延迟时间（以秒为单位）
        return now.until(reminderDateTime, ChronoUnit.SECONDS);
    }

    /**
     * 安排任务。
     *
     * @param task  提醒任务
     * @param delay 延迟时间（秒）
     */
    private static void scheduleReminder(Runnable task, long delay) {
        if (scheduler.isShutdown()) {
            throw new IllegalStateException("Scheduler is already shut down.");
        }
        scheduler.scheduleAtFixedRate(task, delay, 24 * 60 * 60, TimeUnit.SECONDS);
    }

    /**
     * 关闭调度器。
     */
    public static void shutdownScheduler() {
        System.out.println("关闭调度器");
        if (!scheduler.isShutdown()) {
            scheduler.shutdown();
            try {
                // 等待所有任务完成
                if (!scheduler.awaitTermination(5, TimeUnit.SECONDS)) {
                    scheduler.shutdownNow(); // 如果超时，强制关闭所有任务
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                scheduler.shutdownNow(); // 如果线程被中断，强制关闭所有任务
            }
        }
    }
}
