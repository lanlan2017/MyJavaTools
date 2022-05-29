package adbs.action.runnable;

import adbs.action.model.InOutputModel;
import adbs.cmd.AdbCommands;
import adbs.test.DeviceRadioBtAcListener;
import adbs.ui.AdbTools;
import tools.thead.Threads;

import javax.swing.*;
import java.awt.*;

public class BrowseRunnable implements Runnable {

    private InOutputModel model;
    private static boolean stop = false;
    // 手机设备的ID
    private String id = DeviceRadioBtAcListener.getId();

    public BrowseRunnable(InOutputModel model) {
        this.model = model;
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
        JLabel output = model.getOutput();
        String text = model.getInputPanelModel().getInput1().getText();
        int s = Integer.parseInt(text);
        int count = 0;
        // 如果stop为false，则一直执行。
        output.setText("浏览线程：在手机左侧，从下向上滑动");
        while (!stop) {
            // 休眠1秒
            // output.setText("浏览线程：在手机左侧，从下向上滑动");
            Threads.sleep(1000);
            count += 1000;
            if (count <= s * 1000) {
                AdbCommands.swipeBottom2TopOnLeft(id);
            } else {
                break;
            }
            output.setText("浏览线程:" + (s * 1000 - count) / 1000 + "s");
        }
        output.setText("浏览线程：已结束");
        // showConfirmDialog();
        model.getInputPanelModel().showConfirmDialog();
    }
}
