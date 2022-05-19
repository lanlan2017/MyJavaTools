package adbs.action.runnable;

import adbs.cmd.AdbCommands;
import adbs.test.DeviceRadioButtonActionListener;
import adbs.ui.AdbTools;
import tools.thead.Threads;

import javax.swing.*;

public class BrowseRunnable implements Runnable {
    private JLabel output;
    private static boolean stop = false;
    // 手机设备的ID
    private String id = DeviceRadioButtonActionListener.getId();

    public BrowseRunnable(JLabel output) {
        this.output = output;
    }

    public static boolean isStop() {
        return stop;
    }

    public static void setStop(boolean stop) {
        BrowseRunnable.stop = stop;
    }

    @Override
    public void run() {
        // 告诉主线程当前线程正在运行
        AdbTools.setIsRunning(this);
        stop = false;
        // 如果stop为false，则一直执行。
        output.setText("浏览线程：在手机左侧，从下向上滑动");
        while (!stop) {
            // 休眠1秒
            // output.setText("浏览线程：在手机左侧，从下向上滑动");
            Threads.sleep(1000);
            AdbCommands.swipeBottom2TopOnLeft(id);
        }
        output.setText("浏览线程：已结束");
    }
}
