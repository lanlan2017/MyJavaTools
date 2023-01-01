package adbs.main.ui.jframe;

import tools.thead.Threads;

import javax.swing.*;
import java.awt.*;

public class JFramePack {
    /**
     * 点击按钮后，调用JFrame.pack()方法，更新界面。
     *
     * @param e           ActionEvent对象
     * @param millisecond
     */
    // public static void onJComponentActionEvent(ActionEvent e) {
    public static void onJComponentActionEvent(AWTEvent e, int millisecond) {
        // Container parent = ((JButton) e.getSource()).getParent();
        Container parent = ((Container) e.getSource()).getParent();
        while (parent instanceof JComponent) {
            parent = parent.getParent();
        }
        if (parent instanceof JFrame) {
            JFrame frame = (JFrame) parent;
            // 启动一个线程来刷新界面
            new Thread(() -> {
                // 等待1秒
                // Threads.sleep(1000);
                if (millisecond > 0) {
                    Threads.sleep(millisecond);
                }
                System.out.println("jFrame_pack_刷新界面");
                // 刷新界面
                frame.pack();
            }).start();
        }
    }

    public static void onJComponentActionEvent(AWTEvent e) {
        onJComponentActionEvent(e, 0);
    }
}
