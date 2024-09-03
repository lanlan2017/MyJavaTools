package adbs.main.ui.jpanels.act.reminder;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public final class DailyReminderScheduler {

    // 私有构造函数，防止实例化
    private DailyReminderScheduler() {
        throw new AssertionError("Utility class cannot be instantiated.");
    }

    // 静态成员变量，作为全局的调度器
    private static final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    public static void main(String[] args) {
        // 设置提醒时间及对应的提醒任务
        Map<LocalTime, Runnable> reminders = new HashMap<>();
        reminders.put(LocalTime.parse("13:00"), () -> System.out.println("Reminder 1: It's " + LocalDateTime.now().toString() + " now!"));
        reminders.put(LocalTime.parse("16:00"), () -> System.out.println("Reminder 2: It's " + LocalDateTime.now().toString() + " now!"));

        // 注册关闭钩子
        Runtime.getRuntime().addShutdownHook(new Thread(DailyReminderScheduler::shutdownScheduler));

        // 遍历提醒时间和任务，分别调度
        reminders.forEach((time, task) -> scheduleDailyReminder(time, task));

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
     * @param reminderTime 提醒时间
     * @param task         提醒任务
     */
    private static void scheduleDailyReminder(LocalTime reminderTime, Runnable task) {
        LocalDateTime now = LocalDateTime.now();
        long delayInMinutes = calculateDelayInMinutes(now, reminderTime);

        // 安排任务
        scheduleReminder(task, delayInMinutes, reminderTime);
    }

    /**
     * 安排任务。
     *
     * @param task         提醒任务
     * @param delay        初始延迟时间（分钟）
     * @param reminderTime 提醒时间
     */
    private static void scheduleReminder(Runnable task, long delay, LocalTime reminderTime) {
        if (scheduler.isShutdown()) {
            throw new IllegalStateException("Scheduler is already shut down.");
        }

        scheduler.scheduleAtFixedRate(() -> {
            if (LocalTime.now().equals(reminderTime)) {
                task.run();
            }
        }, delay, 1, TimeUnit.MINUTES);
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
