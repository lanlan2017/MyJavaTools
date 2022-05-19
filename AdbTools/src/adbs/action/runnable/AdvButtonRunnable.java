package adbs.action.runnable;

import adbs.cmd.AdbCommands;
import adbs.test.DeviceRadioButtonActionListener;
import adbs.ui.AdbTools;
import tools.thead.Threads;

import javax.swing.*;

/**
 * 看广告按钮，线程执行体
 */
public class AdvButtonRunnable implements Runnable {
    JLabel output;
    JButton readButton;
    private static boolean stop = false;

    public static boolean isStop() {
        return stop;
    }

    public static void setStop(boolean stop) {
        AdvButtonRunnable.stop = stop;
    }

    public AdvButtonRunnable(JButton readButton, JLabel output) {
        this.readButton = readButton;
        this.output = output;
    }

    @Override
    public void run() {
        String id = DeviceRadioButtonActionListener.getId();
        // 告诉主线程当前线程正在运行
        AdbTools.setIsRunning(this);
        Threads.sleep(500);
        // 等待广告结束
        // System.out.println("广告线程：等待广告结束(31s)");
        output.setText("广告线程：等待广告结束(31s)");
        // 如果stop为false
        if (!isStop()) {
            int count = 0;
            // 倍数
            int times = 4;
            String oldText;
            String newText;
            while (count < 31 * times) {
                // 如果已经收到结束的通知
                if (isStop()) {
                    // 不再等待
                    break;
                }
                // 等待一小段时间
                Threads.sleep(1000 / times);
                count++;

                oldText = output.getText();
                newText = "广告线程：等待广告结束(" + (31 - count / times) + "s)";
                if (!oldText.equals(newText)) {
                    output.setText(newText);
                }

            }
        }
        output.setText("广告线程：已停止");
        // 按下返回键
        AdbCommands.returnButton(id);
        // 触发阅读按钮的click事件
        readButton.doClick();
    }
}
