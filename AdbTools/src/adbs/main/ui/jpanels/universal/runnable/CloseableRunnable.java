package adbs.main.ui.jpanels.universal.runnable;

import adbs.main.AdbTools;

import javax.swing.*;

public abstract class CloseableRunnable implements Runnable {

    /**
     * 是否继续执行loopBody()方法
     */
    protected boolean stopLoopBody = false;
    /**
     * 是否需要调用afterLoop()方法
     */
    protected boolean callAfter = true;
    /**
     * 线程消息
     */
    protected String msg;


    public CloseableRunnable() {
        setMsg();
    }

    /**
     * 设置消息
     */
    protected void setMsg() {
        msg = "";
    }

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
        stopLoopBody = true;
    }

    /**
     * 停止线程，跳过最后一步
     */
    public void stopSkipAfter() {
        stopLoopBody = true;
        callAfter = false;

    }

    @Override
    public void run() {
        // 默认循环不停止
        stopLoopBody = false;
        // 表示当前进程正在运行
        // AdbTools.getInstance().addRunningInSet(this);
        AdbTools.getInstance().addRunningInSet(this);
        // 循环之前要做的
        before();
        // 如果不需要停止循环的话，就一直循环
        while (!stopLoopBody) {
            loop();
        }
        // 清空输出标签
        cleanOutput();
        if (callAfter) {
            // 循环之后要做的
            after();
        }
    }

    /**
     * 循环之前要准备的工作
     */
    protected void before() {
    }


    /**
     * 循环体
     */
    protected abstract void loop();


    /**
     * 循环之后要准备的工作
     */
    protected void after() {
        // AdbTools.getInstance().getUniversalPanels().getOutput2().setText("");
        // 确保JLabel线程安全
        //        cleanOutput();
    }

    /**
     * 清理输出面板
     * cleanOutput
     */
    protected void cleanOutput() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                AdbTools.getInstance().getUniversalPanels().getOutput2().setText("");
            }
        });
    }
}
