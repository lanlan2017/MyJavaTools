package adbs.tools.thread;

/**
 * 线程停止工具类
 */
public class ThreadSleep {

    public static void millisecond(double millisecond) {
        try {
            Thread.sleep((long) millisecond);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 停止指定的秒数
     *
     * @param seconds 需要停止线程的秒数
     */
    public static void seconds(double seconds) {
        // try {
        //     // 5分钟检测一次
        //     int millis = 1000 * seconds;
        //     Thread.sleep(millis);
        // } catch (InterruptedException e) {
        //     e.printStackTrace();
        // }
        millisecond(1000 * seconds);
    }

    /**
     * 线程停止指定分钟数
     *
     * @param m 要线程停止的分钟数
     */
    public static void minutes(double m) {
        seconds(60 * m);
    }
}
