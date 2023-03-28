package adbs.main.ui.jpanels.universal.runnable;

import adbs.cmd.AdbCommands;
import adbs.main.AdbTools;
import adbs.main.ui.inout.InOutputModel;
import tools.thead.Threads;

import javax.swing.*;

/**
 * 浏览 线程体
 */
public class BrowseRunnable extends CloseableRunnable {
    // private InOutputModel inOutputModel;

    private static BrowseRunnable instance = new BrowseRunnable();


    private BrowseRunnable() {
    }

    public static BrowseRunnable getInstance() {
        return instance;
    }

    // public void setInOutputModel(InOutputModel inOutputModel) {
    //     this.inOutputModel = inOutputModel;
    // }

    @Override
    protected void setMsg() {
        msg = "浏览";
    }

    @Override
    protected void loopBody() {
        // 获取选中的adb设备的序列号
        String id = AdbTools.device.getId();
        if (id != null) {
            // 获取输入框1的内容
            String text = inOutputModel.getTimePanels().getInput1().getText();


            System.out.println("text = " + text);
            // int s = Integer.parseInt(text) * 1000;
            int s = Integer.parseInt(text);
            int count = 0;
            // 如果stop为false，则一直执行。
            // inOutputModel.getOutput().setText(msg + ":在手机左侧，从下向上滑动");

            JLabel output2 = inOutputModel.getUniversalPanels().getOutput2();
            // output2.setText(msg + ":在手机左侧，从下向上滑动");
            while (!stop) {
                // 休眠1秒
                // output.setText("浏览线程：在手机左侧，从下向上滑动");
                Threads.sleep(1000);
                // count += 1000;
                count += 1;
                if (count <= s) {
                    AdbCommands.swipeBottom2TopOnLeft(id);
                } else {
                    // 停止线程
                    stop();
                    break;
                }
                // inOutputModel.getOutput().setText(msg + ":" + ((s - count) / 1000) + "s");
                // inOutputModel.getOutput().setText(msg + ":" + (s - count) + "s");
                output2.setText(msg + ":" + (s - count) + "s");
            }
        }

    }

    @Override
    protected void afterLoop() {
        super.afterLoop();
        // 弹出确认框
        inOutputModel.getTimePanels().showConfirmDialog();
    }
}
