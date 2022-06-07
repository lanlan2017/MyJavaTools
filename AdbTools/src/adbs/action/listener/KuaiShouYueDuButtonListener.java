package adbs.action.listener;

import adbs.action.model.InOutputModel;
import adbs.action.runnable.KuaiShouYueDuRunnable2;
import adbs.action.runnable.ReadButtonRunnable2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KuaiShouYueDuButtonListener implements ActionListener {
    private ReadButtonRunnable2 readButtonRunnable2;
    private KuaiShouYueDuRunnable2 kuaiShouYueDuRunnable2 = KuaiShouYueDuRunnable2.getInstance();
    private Thread kuaiShouYueDuThread;

    public KuaiShouYueDuButtonListener(JButton readButton, InOutputModel inOutputModel) {
        this.readButtonRunnable2 = ReadButtonRunnable2.getInstance();
        readButtonRunnable2.setInOutputModel(inOutputModel);
        kuaiShouYueDuRunnable2.setReadButton(readButton);
        this.kuaiShouYueDuThread = new Thread(kuaiShouYueDuRunnable2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 启动阅读线程
        new Thread(readButtonRunnable2).start();
        // 如果线程已经死掉了，重新创建一个线程
        if (!kuaiShouYueDuThread.isAlive()) {
            System.out.println("快手阅读广告监听线程已经死掉，重新创建一个线程");
            kuaiShouYueDuThread = new Thread(kuaiShouYueDuRunnable2);
            kuaiShouYueDuThread.start();
        } else {
            System.out.println("快手阅读广告监听线程还在活动中");
        }
    }
}
