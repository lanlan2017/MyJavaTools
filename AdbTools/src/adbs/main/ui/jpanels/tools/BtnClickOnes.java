package adbs.main.ui.jpanels.tools;

import adbs.main.AdbTools;
import adbs.tools.thread.ThreadSleep;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * 当按钮没有响应完毕之前，不可重复点击按钮。
 */
public class BtnClickOnes {
    private static final String suffix = "...";

    public static void before(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        BtnClickOnes.before(button);
    }

    public static void before(final JButton button) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String text = button.getText();
                if (!text.endsWith(suffix)) {
                    // button.setText("卸载...");
                    button.setText(text + suffix);
                    // AdbTools.getInstance().getFrame().pack();
                    // button.updateUI();
                    // AdbTools.getInstance().getFrame().pack();
                    // ThreadSleep.seconds(1);
                }
                button.setEnabled(false);
            }
        }).start();
    }

    public static void after(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        BtnClickOnes.after(button);
    }

    public static void after(final JButton button) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String text = button.getText();
                if (text.endsWith(suffix)) {
                    text = text.substring(0, text.lastIndexOf(suffix));
                    AdbTools.getInstance().getFrame().pack();
                    // ThreadSleep.seconds(10);
                    ThreadSleep.seconds(3);
                    button.setText(text);
                    AdbTools.getInstance().getFrame().pack();
                    // ThreadSleep.seconds(1);
                }

                button.setEnabled(true);
            }
        }).start();
    }
}
