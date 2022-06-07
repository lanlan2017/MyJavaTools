package adbs.action.runnable;

import adbs.ui.AdbTools;

public abstract class CloseableRunnable2 implements Runnable {

    public CloseableRunnable2() {
        setMsg();
    }

    /**
     * 是否结束线程
     */
    protected boolean stop = false;
    /**
     * 线程消息
     */
    protected String msg;

    /**
     * 设置消息
     */
    abstract protected void setMsg();

    /**
     * 获取消息
     *
     * @return 当前线程的消息
     */
    public String getMsg() {
        return msg;
    }

    /**
     * 停止线程
     */
    public void stop() {
        stop = true;
    }

    @Override
    public void run() {
        stop = false;
        // 表示当前进程正在运行
        AdbTools.setIsRunning(this);
        // 循环之前要做的
        beforeLoop();
        while (!stop) {
            loopBody();
        }
        // 循环之后要做的
        afterLoop();
    }

    /**
     * 循环之前要准备的工作
     */
    protected abstract void beforeLoop();


    /**
     * 循环体
     */
    protected abstract void loopBody();


    /**
     * 循环之后要准备的工作
     */
    protected abstract void afterLoop();
}
