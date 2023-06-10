package adbs.main.ui.jpanels.time.listener;

import adbs.main.AdbTools;
import adbs.main.ui.jframe.JFramePack;
import adbs.main.ui.jpanels.adb.listener.ButtonFocusReleaseActionListener;
import adbs.main.ui.inout.InOutputModel;
import adbs.main.ui.jpanels.time.TimePanels;
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
    private final InOutputModel inOutputModel;

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
     * 线程
     */
    private Thread videoBtnThread;


    public InputOkButtonActionListener(InOutputModel inOutputModel) {
        this.inOutputModel = inOutputModel;

        this.readButtonRunnable = ReadButtonRunnable.getInstance();
        readButtonRunnable.setInOutputModel(inOutputModel);

        this.browseRunnable = BrowseRunnable.getInstance();
        browseRunnable.setInOutputModel(inOutputModel);

        this.shoppingButtonRunnable = ShoppingButtonRunnable.getInstance();
        shoppingButtonRunnable.setInOutputModel(inOutputModel);

        this.waitReturnButtonRunnable = WaitButtonRunnable.getInstance();
        waitReturnButtonRunnable.setInOutputModel(inOutputModel);

        this.videoButtonRunnable = VideoButtonRunnable.getInstance();
        videoButtonRunnable.setInOutputModel(inOutputModel);

    }

    @Override
    protected void actionEvent(ActionEvent e) {
        JButton ok = (JButton) e.getSource();
        // JLabel output = inOutputModel.getOutput();
        JLabel output = inOutputModel.getUniversalPanels().getOutput2();


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

        } else if ("开始等待".equals(ok.getText())) {
            // output.setText("等待返回线程：开始等待");
            // new Thread(waitReturnButtonRunnable).start();
            TimePanels timePanels = AdbTools.getInstance().getTimePanels();
            JCheckBox taskCheckBox = timePanels.getTaskCheckBox();
            if (taskCheckBox.isSelected()) {
                waitReturnButtonRunnable.setClickTaskButton(true);
            }
            if (timePanels.getStopCheckBox().isSelected()) {
                waitReturnButtonRunnable.setClickStopButton(true);
            }

            new Thread(waitReturnButtonRunnable).start();


        } else if ("开始刷视频".equals(ok.getText())) {
            output.setText("刷视频:开始");
            // 获取时间区间
            String input1Str = inOutputModel.getTimePanels().getInput1().getText();
            String input2Str = inOutputModel.getTimePanels().getInput2().getText();
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
            String input1Str = inOutputModel.getTimePanels().getInput1().getText();
            String input2Str = inOutputModel.getTimePanels().getInput2().getText();
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
        // 等待指定毫秒之后，刷新JFrame界面，以最佳大小显示
        JFramePack.onJComponentActionEvent(e, 500);
    }
}
