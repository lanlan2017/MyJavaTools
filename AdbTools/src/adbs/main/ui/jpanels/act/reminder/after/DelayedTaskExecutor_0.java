package adbs.main.ui.jpanels.act.reminder.after;

import java.util.concurrent.*;

public class DelayedTaskExecutor_0 {

    private final ScheduledExecutorService scheduler;

    public DelayedTaskExecutor_0(int threadPoolSize) {
        // 创建一个ScheduledExecutorService实例，它内部维护了一个线程池
        this.scheduler = Executors.newScheduledThreadPool(threadPoolSize);
    }

    /**
     * 在指定的延迟后执行任务。
     * @param seconds 延迟时间（秒）
     * @param task 要执行的任务
     */
    public void afterSeconds(long seconds, Runnable task) {
        this.scheduler.schedule(task, seconds, TimeUnit.SECONDS);
    }

    /**
     * 在指定的延迟后执行任务。
     * @param milliseconds 延迟时间（毫秒）
     * @param task 要执行的任务
     */
    public void afterMilliseconds(long milliseconds, Runnable task) {
        this.scheduler.schedule(task, milliseconds, TimeUnit.MILLISECONDS);
    }

    /**
     * 在指定的延迟后执行任务。
     * @param minutes 延迟时间（分钟）
     * @param task 要执行的任务
     */
    public void afterMinutes(long minutes, Runnable task) {
        this.scheduler.schedule(task, minutes, TimeUnit.MINUTES);
    }

    /**
     * 在指定的延迟后执行任务。
     * @param hours 延迟时间（小时）
     * @param task 要执行的任务
     */
    public void afterHours(long hours, Runnable task) {
        this.scheduler.schedule(task, hours, TimeUnit.HOURS);
    }

    /**
     * 关闭executor service。
     */
    public void shutdown() {
        this.scheduler.shutdown();
        // 尝试优雅地关闭executor，等待所有任务完成
        try {
            if (!this.scheduler.awaitTermination(60, TimeUnit.SECONDS)) {
                this.scheduler.shutdownNow(); // 取消正在执行的任务
                // 如果有任务被取消，则重新尝试终止
                if (!this.scheduler.awaitTermination(60, TimeUnit.SECONDS))
                    System.err.println("ScheduledExecutorService did not terminate");
            }
        } catch (InterruptedException ie) {
            this.scheduler.shutdownNow(); // (Re-)Cancel if current thread also interrupted
            Thread.currentThread().interrupt(); // Preserve interrupt status
        }
    }

    public static void main(String[] args) throws InterruptedException {
        DelayedTaskExecutor_0 executor = new DelayedTaskExecutor_0(1);

        // 定义一个任务
        Runnable myTask = () -> System.out.println("Hello from scheduled task at " + System.currentTimeMillis());

        // 在5秒后执行任务
        executor.afterSeconds(5, myTask);

        // 在1秒后执行任务
        executor.afterMilliseconds(1000, myTask);

        // 在1分钟后执行任务
        executor.afterMinutes(1, myTask);

        // 在1小时后执行任务
        executor.afterHours(1, myTask);

        // 在主程序中做一些其他事情，这里等待足够长的时间来确保所有任务被执行
        Thread.sleep(37000); // 等待37秒，确保所有任务被执行

        // 关闭executor
        executor.shutdown();
    }
}
