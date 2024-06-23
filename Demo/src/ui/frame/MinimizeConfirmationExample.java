package ui.frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

/**
 * 窗体最小化提醒代码示例。
 */
public class MinimizeConfirmationExample {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Minimize Confirmation Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
//        frame.setLocationRelativeTo(null);

        // 添加窗口状态监听器
        frame.addWindowStateListener(new WindowStateListener() {
            @Override
            public void windowStateChanged(WindowEvent e) {
                if (e.getNewState() == Frame.ICONIFIED) {
                    // 检查窗口是否被最小化
                    int result = JOptionPane.showConfirmDialog(frame,
                            "确认要最小化吗？", "确认",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE);
                    if (result != JOptionPane.YES_OPTION) {
                        // 如果用户点击“否”，则取消最小化操作并恢复原窗口位置
                        frame.setExtendedState(Frame.NORMAL);
//                        frame.setLocationRelativeTo(null); // 或者使用之前记录的位置
                    }
                }
            }
        });

        JButton testButton = new JButton("Test Button");
        frame.getContentPane().add(testButton, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}