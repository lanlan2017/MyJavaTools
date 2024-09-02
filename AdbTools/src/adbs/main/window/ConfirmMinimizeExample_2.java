package adbs.main.window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ConfirmMinimizeExample_2 {

    private static WindowStateListener listener;
    private static ScheduledExecutorService executorService;
    private static JFrame frame;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        frame = new JFrame("Confirm Minimize Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        // 添加组件到窗口...
        frame.add(new JLabel("这是一个测试窗口"));

        // 创建WindowStateListener实例
        listener = new WindowStateListener() {
            @Override
            public void windowStateChanged(WindowEvent e) {
                if (e.getNewState() == Frame.ICONIFIED) {
                    // 先恢复窗口的正常状态
                    frame.setState(Frame.NORMAL);

                    // 显示确认对话框
                    int result = JOptionPane.showConfirmDialog(frame,
                            "您确定要最小化窗口吗？", "确认",
                            JOptionPane.YES_NO_OPTION);

                    if (result == JOptionPane.YES_OPTION) {
                        // 如果用户选择“是”，则最小化窗口
                        frame.setState(Frame.ICONIFIED);

                        // 移除监听器
                        frame.removeWindowStateListener(this);

                        // 使用ScheduledExecutorService在一定时间后重新添加监听器
                        executorService.schedule(() -> {
                            SwingUtilities.invokeLater(() -> {
                                frame.addWindowStateListener(this);
                            });
                        }, 1, TimeUnit.SECONDS);
                    } else {
                        // 如果用户选择“否”，则保持窗口正常状态
                    }
                }
            }
        };

        // 添加WindowStateListener来监听窗口状态变化
        frame.addWindowStateListener(listener);

        // 初始化ScheduledExecutorService
        executorService = Executors.newSingleThreadScheduledExecutor();

        frame.setVisible(true);
    }
}
