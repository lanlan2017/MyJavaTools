package adbs.main.ui.jpanels.act.reminder.after;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 倒计时器，定时器，从现在开始等待指定时间，然后执行指定的任务
 */
public final class TimerUtils {
    /**
     * 延迟任务执行器。
     */
    private static ScheduledExecutorService SCHEDULER;

    /**
     * 初始化调度器
     * 此方法确保调度器实例仅被创建一次，并在需要时重新启动
     */
    public static void initScheduler() {
        // 检查调度器是否未初始化或已关闭
        if (SCHEDULER == null || SCHEDULER.isShutdown()) {
            // 可以根据需要调整线程数
            SCHEDULER = new ScheduledThreadPoolExecutor(1);
        }
    }


    // 私有构造器，防止外部实例化
    private TimerUtils() {
        throw new AssertionError("Cannot instantiate utility class.");
    }

    /**
     * 在指定的延迟后执行任务。
     *
     * @param milliseconds 延迟时间（毫秒）
     * @param task         要执行的任务
     */
    public static void afterMilliseconds(long milliseconds, Runnable task) {
        initScheduler();
        SCHEDULER.schedule(task, milliseconds, TimeUnit.MILLISECONDS);
    }

    /**
     * 在指定的延迟后执行任务。
     *
     * @param seconds 延迟时间（秒）
     * @param task    要执行的任务
     */
    public static void afterSeconds(long seconds, Runnable task) {
        initScheduler();
        SCHEDULER.schedule(task, seconds, TimeUnit.SECONDS);
    }


    /**
     * 在指定的延迟后执行任务。
     *
     * @param minutes 延迟时间（分钟）
     * @param task    要执行的任务
     */
    public static void afterMinutes(long minutes, Runnable task) {
        initScheduler();
        SCHEDULER.schedule(task, minutes, TimeUnit.MINUTES);
    }

    /**
     * 在指定的延迟后执行任务。
     *
     * @param hours 延迟时间（小时）
     * @param task  要执行的任务
     */
    public static void afterHours(long hours, Runnable task) {
        initScheduler();
        SCHEDULER.schedule(task, hours, TimeUnit.HOURS);
    }

    /**
     * 关闭executor service。
     */
    public static void shutdown() {
        System.out.println("关闭延迟定时器");
        SCHEDULER.shutdown();
        // 尝试优雅地关闭executor，等待所有任务完成
        try {
            if (!SCHEDULER.awaitTermination(60, TimeUnit.MILLISECONDS)) {
                SCHEDULER.shutdownNow(); // 取消正在执行的任务
                // 如果有任务被取消，则重新尝试终止
                if (!SCHEDULER.awaitTermination(60, TimeUnit.MILLISECONDS))
                    System.err.println("ScheduledExecutorService did not terminate");
            }
        } catch (InterruptedException ie) {
            SCHEDULER.shutdownNow(); // (Re-)Cancel if current thread also interrupted
            Thread.currentThread().interrupt(); // Preserve interrupt status
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 定义一个任务
        Runnable myTask = () -> System.out.println("Hello from scheduled task at " + System.currentTimeMillis());

        // 在1秒后执行任务
        afterMilliseconds(1000, myTask);

        // 在5秒后执行任务
        afterSeconds(5, myTask);

        // 在1分钟后执行任务
        afterMinutes(1, myTask);

        // 在1小时后执行任务
        afterHours(1, myTask);

        // 在主程序中做一些其他事情，这里等待足够长的时间来确保所有任务被执行
        Thread.sleep(37000); // 等待37秒，确保所有任务被执行

        // 关闭executor
        shutdown();
    }
}
