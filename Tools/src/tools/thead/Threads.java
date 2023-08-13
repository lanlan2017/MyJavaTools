package tools.thead;

/**
 * Threads相关工具类
 */
public class Threads {

    /**
     * 当前线程睡眠指定毫秒
     *
     * @param millisecond 需要睡眠的毫秒数
     */
    public static void sleep(int millisecond) {
        try {
            Thread.sleep(millisecond);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断thread是否位null,或者已经死亡。
     *
     * @param thread 线程
     * @return 如果线程是null, 或者已经死掉, 则返回true.
     */
    public static boolean threadIsNullOrNotAlive(Thread thread) {
        return thread == null || thread != null && !thread.isAlive();
    }
}
