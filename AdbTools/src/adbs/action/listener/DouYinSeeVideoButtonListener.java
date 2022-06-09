package adbs.action.listener;

import adbs.action.listener.abs.ButtonFocusReleaseActionListener;
import adbs.action.model.InOutputModel;
import adbs.action.runnable.DouYinVideoButtonRunnable;
import adbs.action.runnable.VideoButtonRunnable;
import tools.thead.Threads;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class DouYinSeeVideoButtonListener extends ButtonFocusReleaseActionListener {
    private JFrame frame;
    private InOutputModel inOutputModel;
    private DouYinVideoButtonRunnable douYinVideoButtonRunnable;
    private Thread douYinVideoThread;

    public DouYinSeeVideoButtonListener(JFrame frame, InOutputModel inOutputModel) {
        this.frame = frame;
        this.inOutputModel = inOutputModel;
        this.douYinVideoButtonRunnable = new DouYinVideoButtonRunnable();
    }

    @Override
    protected void actionEvent(ActionEvent e) {
        inOutputModel.getInputPanelModel().getInputPanel().setVisible(false);
        frame.pack();
        // 启动刷视频线程
        // new Thread(new VideoButtonRunnable(inOutputModel)).start();
        // 先停止刷视频线程
        VideoButtonRunnable videoButtonRunnable = VideoButtonRunnable.getInstance();
        videoButtonRunnable.stop();

        videoButtonRunnable.setInOutputModel(inOutputModel);
        new Thread(videoButtonRunnable).start();

        // 如果抖音刷视频置顶红包监听线程 死掉了(没活着)
        // if (douYinVideoThread == null || douYinVideoThread != null && !douYinVideoThread.isAlive()) {
        if (Threads.threadIsNullOrNotAlive(douYinVideoThread)) {
            System.out.println("抖音视频置顶红包 监听线程已经死掉，重新创建一个线程");
            // 重现创建一个线程，并执行
            // douYinVideoThread = new Thread(douYinVideoButtonRunnable);
            douYinVideoThread = new Thread(douYinVideoButtonRunnable);
            douYinVideoThread.start();
        } else {
            System.out.println("抖音视频置顶红包 监听线程还在活动中");
        }
    }

}
