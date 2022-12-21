package adbs.main.ui.jpanels.input.listener;

import adbs.main.ui.jpanels.adb.listener.ButtonFocusReleaseActionListener;
import adbs.main.ui.inout.InOutputModel;
import adbs.main.ui.jpanels.universal.runnable.BrowseRunnable;
import adbs.main.ui.jpanels.universal.runnable.ShoppingButtonRunnable;
import adbs.main.ui.jpanels.universal.runnable.VideoButtonRunnable;
import adbs.main.ui.jpanels.universal.runnable.WaitButtonRunnable;
import tools.thead.Threads;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class InputOkButtonActionListener extends ButtonFocusReleaseActionListener {
    /**
     * 被操作的输入输出组件
     */
    private final InOutputModel inOutputModel;

    private final BrowseRunnable browseRunnable;
    private final ShoppingButtonRunnable shoppingButtonRunnable;
    private final WaitButtonRunnable waitReturnButtonRunnable;
    private final VideoButtonRunnable videoButtonRunnable;
    private Thread videoBtnThread;


    public InputOkButtonActionListener(InOutputModel inOutputModel) {
        this.inOutputModel = inOutputModel;

        this.browseRunnable = BrowseRunnable.getInstance();
        browseRunnable.setInOutputModel(inOutputModel);

        this.shoppingButtonRunnable = ShoppingButtonRunnable.getInstance();
        shoppingButtonRunnable.setInOutputModel(inOutputModel);

        this.waitReturnButtonRunnable = WaitButtonRunnable.getInstance();
        waitReturnButtonRunnable.setInOutputModel(inOutputModel);

        this.videoButtonRunnable = VideoButtonRunnable.getInstance();
        videoButtonRunnable.setInOutputModel(inOutputModel);

    }
    //
    // public InputOkButtonActionListener() {
    //
    // }

    @Override
    protected void actionEvent(ActionEvent e) {
        JButton ok = (JButton) e.getSource();
        JLabel output = inOutputModel.getOutput();

        if ("开始浏览".equals(ok.getText())) {
            output.setText("浏览线程：开始浏览");
            new Thread(browseRunnable).start();
        } else if ("开始逛街".equals(ok.getText())) {
            output.setText("逛街线程：开始逛街");
            // new Thread(shoppingButtonRunnable).start();
            new Thread(shoppingButtonRunnable).start();

        } else if ("开始等待".equals(ok.getText())) {
            // output.setText("等待返回线程：开始等待");
            // new Thread(waitReturnButtonRunnable).start();
            new Thread(waitReturnButtonRunnable).start();

        } else if ("开始刷视频".equals(ok.getText())) {
            output.setText("刷视频线程：开始等待");
            // String input1Str = inOutputModel.getInputPanelModel().getInput1().getText();
            // String input2Str = inOutputModel.getInputPanelModel().getInput2().getText();

            // 测试替换
            String input1Str = inOutputModel.getInputPanels().getInput1().getText();
            String input2Str = inOutputModel.getInputPanels().getInput2().getText();
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
        }
    }
}
