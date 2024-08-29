package adbs.main.ui.jpanels.time.beep;

import adbs.main.ui.jpanels.universal.runnable.CloseableRunnable;
import adbs.tools.thread.ThreadSleep;

import java.awt.*;

public class BeepRunnable extends CloseableRunnable {
    private static Runnable instance = new BeepRunnable();
    private int count = 0;

    @Override
    protected void setMsg() {
        msg = "响铃提示线程";
    }

    @Override
    protected void before() {
        super.before();
        count = 0;
    }

    @Override
    protected void loop() {
        // ThreadSleep.seconds(2);
        Toolkit.getDefaultToolkit().beep();
        // 等待5秒
        ThreadSleep.seconds(5);
        count++;
        if (count>=10){
            // 结束进程，响铃5次就够了，
            // 如果响了五次之后用户还不点击结束线程，
            // 说明用户不在电脑旁边，响铃再多也是没有用的
            stopLoopBody =true;
        }
    }

    public static Runnable getInstance() {
        return instance;
    }
}
