package adbs.main.ui.jpanels.universal.runnable;

import adbs.main.ui.inout.InOutputModel;
import adbs.main.AdbTools;

public abstract class CloseableRunnable implements Runnable {
    protected InOutputModel inOutputModel;

    /**
     * 是否结束线程
     */
    protected boolean stop = false;
    /**
     * 线程消息
     */
    protected String msg;

    public void setInOutputModel(InOutputModel inOutputModel) {
        this.inOutputModel = inOutputModel;
    }

    public CloseableRunnable() {
        setMsg();
    }

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
        // 默认循环不停止
        stop = false;
        // 表示当前进程正在运行
        // AdbTools.getInstance().setIsRunning(this);
        AdbTools.getInstance().setIsRunning(this);
        // 循环之前要做的
        beforeLoop();
        // 如果不需要停止循环的话，就一直循环
        while (!stop) {
            loopBody();
        }
        // 循环之后要做的
        afterLoop();
    }

    /**
     * 循环之前要准备的工作
     */
    protected void beforeLoop() {
        if (inOutputModel != null) {
            inOutputModel.getOutput().setText(msg + ":已经开始");
        }
    }


    /**
     * 循环体
     */
    protected abstract void loopBody();


    /**
     * 循环之后要准备的工作
     */
    protected void afterLoop() {
        System.out.println("Closeable AfterLoop" + this + " " + inOutputModel);
        if (inOutputModel != null) {
            inOutputModel.getOutput().setText(msg + ":已经结束");
        }
    }
}
