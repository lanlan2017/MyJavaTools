package adbs.action.listener;

import adbs.action.runnable.AdvButtonRunnable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 看广告按钮事件监听器
 */
public class AdvButtonActionListener implements ActionListener {
    JButton readButton;
    JButton stopButton;
    AdvButtonRunnable advButtonRunnable;

    public AdvButtonActionListener(JButton readButton, JButton stopButton, JLabel output) {
    // public AdvButtonActionListener(JButton readButton, JLabel output) {
        this.readButton = readButton;
        this.stopButton = stopButton;
        advButtonRunnable = new AdvButtonRunnable(readButton, output);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 广告线程停止标记设置为false
        AdvButtonRunnable.setStop(false);
        // 触发停止阅读按钮的Click事件
        stopButton.doClick();
        Thread thread = new Thread(advButtonRunnable);
        thread.start();
    }
}
