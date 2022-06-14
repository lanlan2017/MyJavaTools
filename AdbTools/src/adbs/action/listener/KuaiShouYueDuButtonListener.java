package adbs.action.listener;

import adbs.action.listener.abs.ButtonFocusReleaseActionListener;
import adbs.action.model.InOutputModel;
import adbs.action.runnable.KuaiShouYueDuRunnable;
import adbs.action.runnable.ReadButtonRunnable;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class KuaiShouYueDuButtonListener extends ButtonFocusReleaseActionListener {
    private ReadButtonRunnable readButtonRunnable;
    private KuaiShouYueDuRunnable kuaiShouYueDuRunnable = KuaiShouYueDuRunnable.getInstance();
    private Thread kuaiShouYueDuThread;

    public KuaiShouYueDuButtonListener(JButton readButton, InOutputModel inOutputModel) {
        this.readButtonRunnable = ReadButtonRunnable.getInstance();
        this.readButtonRunnable.setInOutputModel(inOutputModel);
        this.kuaiShouYueDuThread = new Thread(kuaiShouYueDuRunnable);
        this.kuaiShouYueDuRunnable.setReadButton(readButton);
        this.kuaiShouYueDuRunnable.setInOutputModel(inOutputModel);
    }

    @Override
    protected void actionEvent(ActionEvent e) {
        // 启动阅读线程
        new Thread(readButtonRunnable).start();
        // 如果线程已经死掉了，重新创建一个线程
        if (!kuaiShouYueDuThread.isAlive()) {
            System.out.println("快手阅读广告监听线程已经死掉，重新创建一个线程");
            kuaiShouYueDuThread = new Thread(kuaiShouYueDuRunnable);
            kuaiShouYueDuThread.start();
        } else {
            System.out.println("快手阅读广告监听线程还在活动中");
        }
    }
}
