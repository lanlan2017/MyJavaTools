package adbs.action.listener;

import adbs.action.model.InOutputModel;
import adbs.action.runnable.*;
import tools.thead.Threads;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InputOkButtonActionListener implements ActionListener {
    private InOutputModel inOutputModel;


    private BrowseRunnable browseRunnable;
    private ShoppingButtonRunnable shoppingButtonRunnable;
    private WaitReturnButtonRunnable waitReturnButtonRunnable;
    // private VideoButtonRunnable videoButtonRunnable;
    private VideoButtonRunnable videoButtonRunnable;
    private Thread videoBtnThread;


    public InputOkButtonActionListener(InOutputModel inOutputModel) {
        this.inOutputModel = inOutputModel;
        this.browseRunnable = BrowseRunnable.getInstance();
        browseRunnable.setInOutputModel(inOutputModel);

        this.shoppingButtonRunnable = ShoppingButtonRunnable.getInstance();
        shoppingButtonRunnable.setInOutputModel(inOutputModel);

        // this.waitReturnButtonRunnable = new WaitReturnButtonRunnable(inOutputModel);
        this.waitReturnButtonRunnable = WaitReturnButtonRunnable.getInstance();
        waitReturnButtonRunnable.setInOutputModel(inOutputModel);
        // this.videoButtonRunnable = new VideoButtonRunnable(inOutputModel);

        this.videoButtonRunnable = VideoButtonRunnable.getInstance();
        videoButtonRunnable.setInOutputModel(inOutputModel);
        // videoBtnThread = new Thread(videoButtonRunnable2);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton ok = (JButton) e.getSource();
        JLabel output = inOutputModel.getOutput();

        if ("开始浏览".equals(ok.getText())) {
            output.setText("浏览线程：开始浏览");

            // new Thread(browseRunnable).start();
            new Thread(browseRunnable).start();
        } else if ("开始逛街".equals(ok.getText())) {
            output.setText("逛街线程：开始逛街");
            // new Thread(shoppingButtonRunnable).start();
            new Thread(shoppingButtonRunnable).start();

        } else if ("开始等待".equals(ok.getText())) {
            output.setText("等待返回线程：开始等待");
            // new Thread(waitReturnButtonRunnable).start();
            new Thread(waitReturnButtonRunnable).start();
        } else if ("开始刷视频".equals(ok.getText())) {
            output.setText("刷视频线程：开始等待");
            String input1Str = inOutputModel.getInputPanelModel().getInput1().getText();
            String input2Str = inOutputModel.getInputPanelModel().getInput2().getText();
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

    // /**
    //  * 判断thread是否位null,或者已经死亡。
    //  *
    //  * @param thread 线程
    //  * @return 如果线程是null, 或者已经死掉, 则返回true.
    //  */
    // private boolean threadIsNullOrNoAlive(Thread thread) {
    //     return thread == null || thread != null && !thread.isAlive();
    //     Threads
    // }
}
