package adbs.action.listener;

import adbs.action.model.InOutputModel;
import adbs.action.runnable.BrowseRunnable;
import adbs.action.runnable.ShoppingButtonRunnable;
import adbs.action.runnable.VideoButtonRunnable;
import adbs.action.runnable.WaitReturnButtonRunnable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InputOkButtonActionListener implements ActionListener {
    private InOutputModel inOutputModel;


    private BrowseRunnable browseRunnable;
    private ShoppingButtonRunnable shoppingButtonRunnable;
    private WaitReturnButtonRunnable waitReturnButtonRunnable;
    private VideoButtonRunnable videoButtonRunnable;

    public InputOkButtonActionListener(InOutputModel inOutputModel) {
        this.inOutputModel = inOutputModel;
        this.browseRunnable = BrowseRunnable.getInstance();
        browseRunnable.setInOutputModel(inOutputModel);


        this.shoppingButtonRunnable = new ShoppingButtonRunnable(inOutputModel);
        this.waitReturnButtonRunnable = new WaitReturnButtonRunnable(inOutputModel);
        this.videoButtonRunnable = new VideoButtonRunnable(inOutputModel);

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
            new Thread(shoppingButtonRunnable).start();
        } else if ("开始等待".equals(ok.getText())) {
            output.setText("等待返回线程：开始等待");
            new Thread(waitReturnButtonRunnable).start();
        } else if ("开始刷视频".equals(ok.getText())) {
            output.setText("刷视频线程：开始等待");
            String input1Str = inOutputModel.getInputPanelModel().getInput1().getText();
            String input2Str = inOutputModel.getInputPanelModel().getInput2().getText();
            // 如果输入的都是数字
            if (input1Str.matches("\\d+") && input2Str.matches("\\d+")) {
                videoButtonRunnable.setMin(Integer.parseInt(input1Str));
                videoButtonRunnable.setMax(Integer.parseInt(input2Str));
                new Thread(videoButtonRunnable).start();
            }
        }
    }
}
