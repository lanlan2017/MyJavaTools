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
}
