package adbs.action.listener;

import adbs.action.runnable.ReadButtonRunnable;
import adbs.action.runnable.KuaiShouYueDuRunnable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReadButtonActionListener implements ActionListener {
    private JButton readButton;
    private final ReadButtonRunnable readButtonRunnable;
    private KuaiShouYueDuRunnable kuaiShouYueDuRunnable;
    private Thread kuaiShouYueDuThread;


    public ReadButtonActionListener(JButton readButton, JButton stopButton, JLabel output) {
        this.readButtonRunnable = new ReadButtonRunnable(output);
        this.readButton = readButton;
        this.kuaiShouYueDuRunnable = new KuaiShouYueDuRunnable(readButton, stopButton);
        this.kuaiShouYueDuThread = new Thread(kuaiShouYueDuRunnable);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
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
