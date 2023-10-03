package adbs.main.ui.jpanels.time.listener;

import adbs.main.AdbTools;
import adbs.main.ui.jframe.JFramePack;
import adbs.main.ui.jpanels.adb.listener.ButtonFocusReleaseActionListener;
import adbs.main.ui.inout.InOutputModel;
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
     * 被操作的输入输出组件
     */
    private InOutputModel inOutputModel;
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
     * 锁定线程体
     */
    private final RoolBtnRunnable roolBtnRunnable;

    /**
     * 刷视频线程
     */
    private Thread videoBtnThread;

    /**
     * 等待线程
     *
     * @param inOutputModel
     */
    private Thread waitBtnThread;

    /**
     * 锁定线程体
     */

    private Thread roolBtnThread;
    // private UniversalPanels universalPanels;
    // private TimePanels timePanels;

    public InputOkButtonActionListener(InOutputModel inOutputModel) {
        this.inOutputModel = inOutputModel;
        // this.universalPanels = inOutputModel.getUniversalPanels();
        // this.timePanels = inOutputModel.getTimePanels();

        this.readButtonRunnable = ReadButtonRunnable.getInstance();
        // readButtonRunnable.setInOutputModel(inOutputModel);
        // readButtonRunnable.setUniversalPanels(universalPanels);


        this.browseRunnable = BrowseRunnable.getInstance();
        // browseRunnable.setInOutputModel(inOutputModel);
        // browseRunnable.setTimePanels(timePanels);
        // browseRunnable.setUniversalPanels(universalPanels);


        this.waitReturnButtonRunnable = WaitButtonRunnable.getInstance();
        // waitReturnButtonRunnable.setInOutputModel(inOutputModel);


        this.videoButtonRunnable = VideoButtonRunnable.getInstance();
        // videoButtonRunnable.setInOutputModel(inOutputModel);

        this.shoppingButtonRunnable = ShoppingButtonRunnable.getInstance();
        // shoppingButtonRunnable.setInOutputModel(inOutputModel);

        this.roolBtnRunnable = RoolBtnRunnable.getInstance();
    }

    public InputOkButtonActionListener() {
        // this.inOutputModel = inOutputModel;
        // this.universalPanels = inOutputModel.getUniversalPanels();
        // this.timePanels = inOutputModel.getTimePanels();

        this.readButtonRunnable = ReadButtonRunnable.getInstance();
        // readButtonRunnable.setInOutputModel(inOutputModel);
        // readButtonRunnable.setUniversalPanels(universalPanels);


        this.browseRunnable = BrowseRunnable.getInstance();
        // browseRunnable.setInOutputModel(inOutputModel);
        // browseRunnable.setTimePanels(timePanels);
        // browseRunnable.setUniversalPanels(universalPanels);


        this.waitReturnButtonRunnable = WaitButtonRunnable.getInstance();
        // waitReturnButtonRunnable.setInOutputModel(inOutputModel);


        this.videoButtonRunnable = VideoButtonRunnable.getInstance();
        // videoButtonRunnable.setInOutputModel(inOutputModel);

        this.shoppingButtonRunnable = ShoppingButtonRunnable.getInstance();
        // shoppingButtonRunnable.setInOutputModel(inOutputModel);

        this.roolBtnRunnable = RoolBtnRunnable.getInstance();
    }

    @Override
    protected void actionEvent(ActionEvent e) {
        JButton ok = (JButton) e.getSource();
        // JLabel output = inOutputModel.getOutput();
        // universalPanels = inOutputModel.getUniversalPanels();
        UniversalPanels universalPanels = AdbTools.getInstance().getUniversalPanels();
        // JLabel output = this.universalPanels.getOutput2();
        JLabel output = universalPanels.getOutput2();


        if ("开始浏览".equals(ok.getText())) {
            output.setText("浏览:开始");
            new Thread(browseRunnable).start();
        } else if ("开始逛街".equals(ok.getText())) {
            output.setText("逛街:开始");
            // new Thread(shoppingButtonRunnable).start();
            JCheckBox checkBox = AdbTools.getInstance().getTimePanels().getTaskCheckBox();
            if (checkBox.isSelected()) {
                shoppingButtonRunnable.setClickReturnBtn(true);
            }

            new Thread(shoppingButtonRunnable).start();

        } else if ("开始锁定".equals(ok.getText())) {
            // output.setText("锁定:开始");
            // new Thread(shoppingButtonRunnable).start();
            // JCheckBox checkBox = AdbTools.getInstance().getTimePanels().getTaskCheckBox();
            // if (checkBox.isSelected()) {
            //     shoppingButtonRunnable.setClickReturnBtn(true);
            // }

            // new Thread(shoppingButtonRunnable).start();

            // 如果线程已经死掉了,或者线程还没创建
            if (Threads.threadIsNullOrNotAlive(roolBtnThread)) {
                roolBtnThread = new Thread(roolBtnRunnable);
                roolBtnThread.start();
            } else {
                System.out.println(roolBtnRunnable.getMsg() + " 已经在运行中,请勿重复启动");
            }


        } else if ("开始等待".equals(ok.getText())) {
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
            } else {
                System.out.println(waitReturnButtonRunnable.getMsg() + " 已经在运行中,请勿重复启动");
            }


        } else {
            // timePanels = inOutputModel.getTimePanels();
            TimePanels timePanels = AdbTools.getInstance().getTimePanels();
            if ("开始刷视频".equals(ok.getText())) {
                output.setText("刷视频:开始");
                // 获取时间区间
                String input1Str = timePanels.getInput1().getText();
                String input2Str = timePanels.getInput2().getText();
                // 如果输入的都是数字
                if (input1Str.matches("\\d+") && input2Str.matches("\\d+")) {
                    videoButtonRunnable.setMin(Integer.parseInt(input1Str));
                    videoButtonRunnable.setMax(Integer.parseInt(input2Str));
                    // 如果线程已经死掉了,或者线程还没创建
                    if (Threads.threadIsNullOrNotAlive(videoBtnThread)) {
                        videoBtnThread = new Thread(videoButtonRunnable);
                        videoBtnThread.start();
                    } else {
                        System.out.println(videoButtonRunnable.getMsg() + " 已经在运行中,请勿重复启动");
                    }
                }
            } else if ("开始阅读".equals(ok.getText())) {
                output.setText("阅读:开始");
                // 获取时间区间
                String input1Str = timePanels.getInput1().getText();
                String input2Str = timePanels.getInput2().getText();
                // 如果输入的都是数字
                if (input1Str.matches("\\d+") && input2Str.matches("\\d+")) {
                    readButtonRunnable.setMin(Integer.parseInt(input1Str));
                    readButtonRunnable.setMax(Integer.parseInt(input2Str));
                    // 如果线程已经死掉了,或者线程还没创建
                    if (Threads.threadIsNullOrNotAlive(videoBtnThread)) {
                        videoBtnThread = new Thread(readButtonRunnable);
                        videoBtnThread.start();
                    } else {
                        System.out.println(readButtonRunnable.getMsg() + " 已经在运行中,请勿重复启动");
                    }
                }
            }
        }
        // 等待指定毫秒之后，刷新JFrame界面，以最佳大小显示
        // JFramePack.onJComponentActionEvent(e, 500);
        JFramePack.onJComponentActionEvent(e, 200);
        // JFramePack.onJComponentActionEvent(e, 0);
    }
}
