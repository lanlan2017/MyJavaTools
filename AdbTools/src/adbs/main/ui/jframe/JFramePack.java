package adbs.main.ui.jframe;

import adbs.main.AdbTools;
import tools.thead.Threads;

import javax.swing.*;
import java.awt.*;

public class JFramePack {
    /**
     * 窗体引用
     */
    private static JFrame frame;

    /**
     * 立即调用fram.pack()
     *
     * @param panel JFrame中的JPanel对象
     */
    public static void byJPanel(JPanel panel) {
        byJPanel(panel, 0);
    }

    /**
     * 等待一段时间后调用frame.pack()
     *
     * @param panel       JFrame中的JPanel对象
     * @param millisecond 要等待的毫秒数
     */
    public static void byJPanel(JPanel panel, int millisecond) {
        if (panel != null) {
            Container parent = panel.getParent();
            while (parent instanceof JComponent) {
                parent = parent.getParent();
            }
            if (parent instanceof JFrame) {
                packAfterSleep(millisecond, (JFrame) parent);
            }
        }
    }

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


    /**
     * 立即调用JFrame.pack方法更新窗体
     *
     * @param e 事件
     */
    public static void onJComponentActionEvent(AWTEvent e) {
        onJComponentActionEvent(e, 0);
    }

    private static void packAfterSleep(int millisecond, JFrame frame2) {
        // // 启动一个线程来刷新界面
        // new Thread(() -> {
        //     if (millisecond > 0) {
        //         // 等待指定的毫秒数之后
        //         Threads.sleep(millisecond);
        //     }
        //     // System.out.println("jFrame_pack_刷新界面");
        //     // 刷新界面
        //     frame2.pack();
        // }).start();
        //

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // // 在这里编写需要在事件调度线程上执行的代码
                // AdbTools.getInstance().getFrame().pack();

                if (millisecond > 0) {
                    // 等待指定的毫秒数之后
                    Threads.sleep(millisecond);
                }
                // System.out.println("jFrame_pack_刷新界面");
                // 刷新界面
                frame2.pack();
            }
        });
    }

    /**
     * 使用事件调度线程来调整JFrame界面的大小，免得出现死锁。
     */
    public static void pack() {
        /**
         * 在处理GUI应用程序时，通常建议在事件调度线程 (Event Dispatch Thread, EDT) 中进行所有的GUI操作，以避免潜在的并发问题。如果在EDT之外修改GUI组件的状态，可能会导致不可预知的行为，包括但不限于死锁。
         *
         * 总结来说，连续调用JFrame的pack()方法通常不会导致死锁，但在处理GUI时，请确保在事件调度线程中进行所有的操作。
         */
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                System.out.println("调用一次");
                // // 在这里编写需要在事件调度线程上执行的代码
                AdbTools.getInstance().getFrame().pack();
            }
        });
    }


}
