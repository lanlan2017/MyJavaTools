package adbs.main.ui.jpanels.act.reminder;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public final class DailyReminderScheduler_Minutes {

    // 私有构造函数，防止实例化
    private DailyReminderScheduler_Minutes() {
        throw new AssertionError("Utility class cannot be instantiated.");
    }

    // 静态成员变量，作为全局的调度器
    private static final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    public static void main(String[] args) {
        // 设置提醒时间
        String time1 = "13:00";
        String time2 = "16:00";

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
        scheduleDailyReminder(time1, reminderTask1);

        // 调度第二个提醒
        scheduleDailyReminder(time2, reminderTask2);

        // 注册关闭钩子
        Runtime.getRuntime().addShutdownHook(new Thread(DailyReminderScheduler_Minutes::shutdownScheduler));

        // 防止主线程立即退出
        try {
            Thread.sleep(Long.MAX_VALUE);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Interrupted");
        }

        // 模拟程序退出
        System.exit(0);
    }

    /**
     * 在每天的特定时间给出提醒。
     *
     * @param time  提醒时间，格式为 "HH:mm"
     * @param task  提醒任务
     */
    public static void scheduleDailyReminder(String time, Runnable task) {
        // 解析提醒时间
        LocalTime reminderTime = parseReminderTime(time);

        // 获取当前时间
        LocalDateTime now = LocalDateTime.now();

        // 计算延迟时间
        long delayInMinutes = calculateDelayInMinutes(now, reminderTime);

        // 安排任务
        scheduleReminder(task, delayInMinutes, reminderTime);
    }

    /**
     * 解析提醒时间字符串为LocalTime对象。
     *
     * @param time 提醒时间字符串，格式为 "HH:mm"
     * @return 解析后的LocalTime对象
     */
    private static LocalTime parseReminderTime(String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return LocalTime.parse(time, formatter);
    }

    /**
     * 计算从当前时间到提醒时间的延迟时间（以分钟为单位）。
     *
     * @param now          当前时间
     * @param reminderTime 提醒时间
     * @return 延迟时间（分钟）
     */
    private static long calculateDelayInMinutes(LocalDateTime now, LocalTime reminderTime) {
        LocalDateTime reminderDateTime = LocalDateTime.of(now.toLocalDate(), reminderTime);

        // 如果提醒时间已经过去，则计算明天的提醒时间
        if (!reminderDateTime.isAfter(now)) {
            reminderDateTime = reminderDateTime.plusDays(1);
        }

        // 计算距离提醒时间的延迟时间（以分钟为单位）
        return now.until(reminderDateTime, ChronoUnit.MINUTES);
    }

    /**
     * 安排任务。
     *
     * @param task         提醒任务
     * @param initialDelay 初始延迟时间（分钟）
     * @param reminderTime 提醒时间
     */
    private static void scheduleReminder(Runnable task, long initialDelay, LocalTime reminderTime) {
        if (scheduler.isShutdown()) {
            throw new IllegalStateException("Scheduler is already shut down.");
        }

        scheduler.scheduleAtFixedRate(() -> {
            LocalTime currentTime = LocalTime.now();
            if (currentTime.equals(reminderTime)) {
                task.run();
            }
        }, initialDelay, 1, TimeUnit.MINUTES);
    }

    /**
     * 关闭调度器。
     */
    public static void shutdownScheduler() {
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
