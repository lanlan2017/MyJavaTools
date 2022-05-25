package adbs.action.runnable;

import adbs.cmd.AdbCommands;
import adbs.test.DeviceRadioBtAcListener;
import adbs.ui.AdbTools;
import tools.thead.Threads;

import javax.swing.*;

public class ShoppingButtonRunnable implements Runnable {
    private JLabel output;
    private static boolean stop = false;


    public ShoppingButtonRunnable(JLabel output) {
        this.output = output;
    }

    public static boolean isStop() {
        return stop;
    }

    public static void setStop(boolean stop) {
        ShoppingButtonRunnable.stop = stop;
    }

    @Override
    public void run() {
        stop = false;
        String id = DeviceRadioBtAcListener.getId();
        // 告诉主线程当前线程正在运行
        AdbTools.setIsRunning(this);
        output.setText("逛街线程：已经开始");
        output.setText("逛街线程: 在左侧 从下向上滑动3次");
        // 在左侧，从下向上滑动三次
        Threads.sleep(1000);
        AdbCommands.swipeBottom2TopOnLeft(id);
        Threads.sleep(1000);
        AdbCommands.swipeBottom2TopOnLeft(id);
        Threads.sleep(1000);
        AdbCommands.swipeBottom2TopOnLeft(id);

        while (!stop) {
            output.setText("逛街线程: 在左侧 从下向上滑动1次");
            Threads.sleep(1000);
            AdbCommands.swipeBottom2TopOnLeft(id);
            output.setText("逛街线程: 在左侧 从上向下滑动1次");
            Threads.sleep(1000);
            AdbCommands.swipeTop2BottomOnLeft(id);
        }
        output.setText("逛街线程：已经结束");
    }
}
