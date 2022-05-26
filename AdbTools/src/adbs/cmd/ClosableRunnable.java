package adbs.cmd;

import adbs.ui.AdbTools;

/**
 * 可关闭的Runnable
 */
public abstract class ClosableRunnable implements Runnable {

    private static boolean stop = false;
    protected String msg;


    public boolean isStop() {
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
        AdbTools.setIsRunning(this);
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
