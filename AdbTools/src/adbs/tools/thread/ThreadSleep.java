package adbs.tools.thread;

public class ThreadSleep {
    public static void seconds(int seconds) {
        try {
            // 5分钟检测一次
            int millis = 1000 * seconds;
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void minutes(int m){
        seconds(60*m);
    }
}
