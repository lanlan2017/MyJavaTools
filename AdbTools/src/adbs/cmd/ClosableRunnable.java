package adbs.cmd;

/**
 * 可关闭的Runnable
 */
public abstract class ClosableRunnable implements Runnable {
    // ClosableRunnable
    private static boolean stop = false;
    protected String msg;


    public static boolean isStop() {
        return stop;
    }

    public static void setStop(boolean stop) {
        ClosableRunnable.stop = stop;
    }

    public String getMsg() {
        return msg;
    }

    abstract public void setMsg();


    @Override
    public void run() {
        //
        stop = false;
        if (msg != null) {
            System.out.println(msg + " 已进入");
        }
        while (!isStop()) {
            running();
        }
        if (msg != null) {
            System.out.println(msg + " 已结束");
        }
    }

    /**
     * 可关闭线程的执行体
     */
    abstract protected void running();
}
