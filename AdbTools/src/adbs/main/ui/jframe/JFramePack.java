package adbs.main.ui.jframe;

import tools.thead.Threads;

import javax.swing.*;
import java.awt.*;

public class JFramePack {
    /**
     * 窗体引用
     */
    private static JFrame frame;

    /**
     * 点击按钮后，调用JFrame.pack()方法，更新界面。
     *
     * @param e           AWTEvent对象
     * @param millisecond 调用JFrame.pack()方法之前需要等待的毫秒数
     */
    // public static void onJComponentActionEvent(ActionEvent e) {
    public static void onJComponentActionEvent(AWTEvent e, int millisecond) {
        // Container parent = ((JButton) e.getSource()).getParent();
        if (frame == null) {
            // 获取触发事件的父容器
            Container parent = ((Container) e.getSource()).getParent();
            while (parent instanceof JComponent) {
                parent = parent.getParent();
            }
            // 如果是窗体的话
            if (parent instanceof JFrame) {
                // 强制转换为窗体对象
                // JFrame frame2 = (JFrame) parent;
                frame = (JFrame) parent;
            }
        }
        if (frame != null) {
            packAfterSleep(millisecond, frame);
        }
    }

    private static void packAfterSleep(int millisecond, JFrame frame2) {
        // 启动一个线程来刷新界面
        new Thread(() -> {
            if (millisecond > 0) {
                // 等待指定的毫秒数之后
                Threads.sleep(millisecond);
            }
            // System.out.println("jFrame_pack_刷新界面");
            // 刷新界面
            frame2.pack();
        }).start();
    }

    /**
     * 立即调用JFrame.pack方法更新窗体
     *
     * @param e 事件
     */
    public static void onJComponentActionEvent(AWTEvent e) {
        onJComponentActionEvent(e, 0);
    }
}
