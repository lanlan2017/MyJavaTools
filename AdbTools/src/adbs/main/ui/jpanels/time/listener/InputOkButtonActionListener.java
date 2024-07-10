package adbs.main.ui.jpanels.time.listener;

import adbs.main.AdbTools;
import adbs.main.ui.jframe.JFramePack;
import adbs.main.ui.jpanels.adb.listener.ButtonFocusReleaseActionListener;
import adbs.main.ui.jpanels.time.TimePanels;
import adbs.main.ui.jpanels.universal.UniversalPanels;
import adbs.main.ui.jpanels.universal.runnable.*;
import tools.thead.Threads;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * 确认按钮事件处理程序
 */
public class InputOkButtonActionListener extends ButtonFocusReleaseActionListener {
    /**
     * 阅读线程体
     */
    private final ReadButtonRunnable readButtonRunnable;

    /**
     * 浏览 按钮功能 线程体
     */
    private final BrowseRunnable browseRunnable;
    /**
     * 等待 按钮功能 线程体
     */
    private final WaitButtonRunnable waitReturnButtonRunnable;
    /**
     * 刷视频 按钮功能 线程体
     */
    private final VideoButtonRunnable videoButtonRunnable;
    /**
     * 逛街 按钮功能 线程体
     */
    private final ShoppingButtonRunnable shoppingButtonRunnable;

    /**
     * 刷视频。逛街，阅读,浏览，共用线程。这个几个操作不能同时进行，所以钥共用一个线程。
     */
    private Thread thread;

    /**
     * 等待线程，
     */
    private Thread waitBtnThread;

    public InputOkButtonActionListener() {
        this.readButtonRunnable = ReadButtonRunnable.getInstance();
        this.browseRunnable = BrowseRunnable.getInstance();
        this.waitReturnButtonRunnable = WaitButtonRunnable.getInstance();
        this.videoButtonRunnable = VideoButtonRunnable.getInstance();
        this.shoppingButtonRunnable = ShoppingButtonRunnable.getInstance();
    }

    @Override
    protected void actionEvent(ActionEvent e) {
        JButton ok = (JButton) e.getSource();
        UniversalPanels universalPanels = AdbTools.getInstance().getUniversalPanels();
        // JLabel output = this.universalPanels.getOutput2();


        if ("开始浏览".equals(ok.getText())) {
            startThreadOnece(browseRunnable);

        } else if ("开始逛街".equals(ok.getText())) {
            JCheckBox checkBox = AdbTools.getInstance().getTimePanels().getTaskCheckBox();
            if (checkBox.isSelected()) {
                shoppingButtonRunnable.setClickTaskBtn(true);
            }
            startThreadOnece(shoppingButtonRunnable);

        }
        else if ("开始等待".equals(ok.getText())) {
            // output.setText("等待返回线程：开始等待");
            // new Thread(waitReturnButtonRunnable).start();
            TimePanels timePanels = AdbTools.getInstance().getTimePanels();
            // 清空时间提示信息
            timePanels.getTimerJLabel().setText("");
            // AdbTools.getInstance().getFrame().pack();
            JCheckBox taskCheckBox = timePanels.getTaskCheckBox();
            if (taskCheckBox.isSelected()) {
                waitReturnButtonRunnable.setClickTaskButton(true);
            } else {
                waitReturnButtonRunnable.setClickTaskButton(false);
            }
            if (timePanels.getStopCheckBox().isSelected()) {
                waitReturnButtonRunnable.setClickStopButton(true);
            } else {
                waitReturnButtonRunnable.setClickStopButton(false);
            }

            // new Thread(waitReturnButtonRunnable).start();

            // 如果线程已经死掉了,或者线程还没创建
            if (Threads.threadIsNullOrNotAlive(waitBtnThread)) {
                waitBtnThread = new Thread(waitReturnButtonRunnable);
                waitBtnThread.start();
                // 调整窗体外观
                JFramePack.pack();
            } else {
                System.out.println(waitReturnButtonRunnable.getMsg() + " 已经在运行中,请勿重复启动");
            }


        } else {
            // timePanels = inOutputModel.getTimePanels();
            TimePanels timePanels = AdbTools.getInstance().getTimePanels();
            if ("开始刷视频".equals(ok.getText())) {
                // output.setText("刷视频:开始");
                // 获取时间区间
                String input1Str = timePanels.getInput1().getText();
                String input2Str = timePanels.getInput2().getText();

                // 如果输入的都是数字
                if (input1Str.matches("\\d+") && input2Str.matches("\\d+")) {
                    videoButtonRunnable.setMin(Integer.parseInt(input1Str));
                    videoButtonRunnable.setMax(Integer.parseInt(input2Str));
                    startThreadOnece(videoButtonRunnable);

                }
            } else if ("开始阅读".equals(ok.getText())) {
                // output.setText("阅读:开始");
                // 获取时间区间
                String input1Str = timePanels.getInput1().getText();
                String input2Str = timePanels.getInput2().getText();
                // 如果输入的都是数字
                if (input1Str.matches("\\d+") && input2Str.matches("\\d+")) {
                    readButtonRunnable.setMin(Integer.parseInt(input1Str));
                    readButtonRunnable.setMax(Integer.parseInt(input2Str));
                    startThreadOnece(readButtonRunnable);
                }
            }
        }
//        JFramePack.pack();
    }

    private void startThreadOnece(CloseableRunnable runnable) {
        // 如果线程已经死掉了,或者线程还没创建
        if (Threads.threadIsNullOrNotAlive(thread)) {
            this.thread = new Thread(runnable);
            // this.waitBtnThread = shoppingBtnThread;
            this.thread.start();
        } else {
            System.out.println(runnable.getMsg() + " 已经在运行中,请勿重复启动");
        }
    }
}
