package adbs.main.ui.jpanels.time.beep;

import adbs.main.ui.jpanels.universal.runnable.CloseableRunnable;
import adbs.tools.thread.ThreadSleep;

import java.awt.*;

public class BeepRunnable extends CloseableRunnable {
    private static final Runnable instance = new BeepRunnable();
    /**
     * 响铃计数器
     */
    private int count = 0;
    /**
     * 最大的响铃次数
     */
    private final int times;

    private BeepRunnable() {
//        times = 10;
        times = 5;
    }


    @Override
    protected void setMsg() {
        msg = "响铃提示线程";
    }

    @Override
    protected void before() {
        super.before();
        count = 0;
        // 先等待几秒，在进入循环体
        //        ThreadSleep.seconds(2);
        //        ThreadSleep.seconds(3);
        ThreadSleep.seconds(5);
    }

    @Override
    protected void loop() {
        if (count < times && !stopLoopBody) {
            //响铃
            Toolkit.getDefaultToolkit().beep();
            //等待5秒
            ThreadSleep.seconds(5);
        } else {
            // 如果响了多次之后用户还不点击结束线程，
            // 说明用户不在电脑旁边，响铃再多也是没有用的
            // 设置跳过循环体
            stopLoopBody = true;
        }
        count++;
    }

    public static Runnable getInstance() {
        return instance;
    }
}
