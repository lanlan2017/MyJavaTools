package adbs.main.ui.jpanels.universal.runnable;

import adbs.cmd.AdbCommands;
import adbs.main.AdbTools;
import adbs.main.ui.jpanels.time.TimePanels;
import adbs.main.ui.jpanels.universal.UniversalPanels;
import tools.thead.Threads;

import javax.swing.*;

/**
 * 浏览 线程体
 */
public class BrowseRunnable extends CloseableRunnable {

    private static final BrowseRunnable instance = new BrowseRunnable();

    private BrowseRunnable() {
    }

    public static BrowseRunnable getInstance() {
        return instance;
    }

    @Override
    protected void loop() {
        // 获取选中的adb设备的序列号
        // String id = AdbTools.device.getId();
        AdbTools adbTools = AdbTools.getInstance();
        String id = adbTools.getDevice().getSerial();
        if (id != null) {
            // 获取输入框1的内容
            // timePanels = inOutputModel.getTimePanels();
            TimePanels timePanels = adbTools.getTimePanels();
            String text = timePanels.getInput1().getText();


            System.out.println("text = " + text);
            // int s = Integer.parseInt(text) * 1000;
            int s = Integer.parseInt(text);
            int count = 0;
            // 如果stop为false，则一直执行。

            // universalPanels = inOutputModel.getUniversalPanels();
            UniversalPanels universalPanels = AdbTools.getInstance().getUniversalPanels();

            JLabel output2 = universalPanels.getOutput2();


            // output2.setText(msg + ":在手机左侧，从下向上滑动");
            while (!stopLoopBody) {
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
                // output2.setText(msg + ":" + (s - count) + "s");
                int finalCount = count;
                // 确保JLable线程安全
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        output2.setText(msg + ":" + (s - finalCount) + "s");
                    }
                });
            }
        }

    }

    @Override
    protected void after() {
        super.after();
//        AdbTools.getInstance().getTimePanels().beepDialog("浏览结束");
        AdbTools.getInstance().beepDialog("浏览结束");
    }
}
