package adbs.action.listener;

import adbs.action.model.InOutputModel;
import adbs.action.model.InputPanelModel;
import adbs.action.runnable.DouYinVideoButtonRunnable;
import adbs.action.runnable.VideoButtonRunnable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DouYinSeeVideoButtonListener implements ActionListener {
    private JFrame frame;
    private InOutputModel inOutputModel;
    private DouYinVideoButtonRunnable douYinVideoButtonRunnable;
    private Thread douYinVideoThread;

    public DouYinSeeVideoButtonListener(JFrame frame, InOutputModel inOutputModel) {
        this.frame = frame;
        this.inOutputModel = inOutputModel;
        this.douYinVideoButtonRunnable = new DouYinVideoButtonRunnable(inOutputModel);
        this.douYinVideoThread = new Thread(douYinVideoButtonRunnable);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // // 显示输入界面
        // InputPanelModel inputPanelModel = inOutputModel.getInputPanelModel();
        // inputPanelModel.getInputPanel().setVisible(true);
        // inputPanelModel.getTimeLable().setText("抖音刷视频");
        // // 隐藏单选按钮组面板
        // inputPanelModel.getTimeRadioPanel().setVisible(false);
        // JTextField input1 = inputPanelModel.getInput1();
        // input1.setVisible(true);
        // input1.setText("7");
        // input1.setColumns(2);
        //
        // JTextField input2 = inputPanelModel.getInput2();
        // input2.setVisible(true);
        // input2.setText("14");
        // input2.setColumns(2);
        //
        // inputPanelModel.getInputOkButton().setText("刷抖音视频");
        // frame.pack();
        // inputPanelModel.getInputPanel().getParent().getParent()



        inOutputModel.getInputPanelModel().getInputPanel().setVisible(false);
        frame.pack();
        // 启动刷视频线程
        new Thread(new VideoButtonRunnable(inOutputModel)).start();

        // 启动抖音视频红包监听线程
        // douYinVideoButtonRunnable = new DouYinVideoButtonRunnable(inOutputModel);
        // thread = new Thread(douYinVideoButtonRunnable);
        // 如果抖音刷视频置顶红包监听线程 死掉了(没活着)
        if (!douYinVideoThread.isAlive()) {
            System.out.println("抖音视频置顶红包 监听线程已经死掉，重新创建一个线程");
            // 重现创建一个线程，并执行
            douYinVideoThread = new Thread(douYinVideoButtonRunnable);
            douYinVideoThread.start();
        } else {
            System.out.println("抖音视频置顶红包 监听线程还在活动中");
        }
    }

}
