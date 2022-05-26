package adbs.action.runnable;

import adbs.action.model.InOutputModel;
import adbs.cmd.AdbCommands;
import adbs.test.DeviceRadioBtAcListener;
import adbs.ui.AdbTools;
import tools.thead.Threads;

import javax.swing.*;

public class ShoppingButtonRunnable implements Runnable {
    /**
     * 输入输出汇总模型
     */
    private InOutputModel model;
    private static boolean stop = false;

    public ShoppingButtonRunnable(InOutputModel model) {
        this.model = model;
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
        JLabel output = model.getOutput();
        output.setText("逛街线程：已经开始");
        output.setText("逛街线程: 在左侧 从下向上滑动3次");
        // 在左侧，从下向上滑动三次
        Threads.sleep(1000);
        AdbCommands.swipeBottom2TopOnLeft(id);
        Threads.sleep(1000);
        AdbCommands.swipeBottom2TopOnLeft(id);
        Threads.sleep(1000);
        AdbCommands.swipeBottom2TopOnLeft(id);

        while (!isStop()) {
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
