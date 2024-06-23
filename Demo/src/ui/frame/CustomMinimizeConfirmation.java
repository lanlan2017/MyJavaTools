package ui.frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CustomMinimizeConfirmation {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Custom Minimize Confirmation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);

        // 添加窗口状态监听器
        frame.addWindowStateListener(new WindowStateListener() {
            @Override
            public void windowStateChanged(WindowEvent e) {
                if (e.getNewState() == Frame.ICONIFIED) { // 检查窗口是否被最小化
                    // 获取窗口当前的中心位置
                    Point center = frame.getLocationOnScreen();
                    center.x += frame.getWidth() / 2;
                    center.y += frame.getHeight() / 2;

                    int result = showCustomDialog(frame, center);

                    // 根据用户选择决定是否最小化
                    if (result == JOptionPane.YES_OPTION) {
                        frame.setExtendedState(Frame.ICONIFIED);
                    } else {
                        frame.setExtendedState(Frame.NORMAL);
                        frame.setLocation(center);
                    }
                }
            }
        });

        JButton testButton = new JButton("Test Button");
        frame.getContentPane().add(testButton, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private static int showCustomDialog(JFrame parent, Point center) {
        JOptionPane optionPane = new JOptionPane(
                "确认要最小化吗？",
                JOptionPane.QUESTION_MESSAGE,
                JOptionPane.YES_NO_OPTION);

        JDialog dialog = optionPane.createDialog(parent, "确认");

        // 设置对话框位置
        dialog.pack();
        dialog.setLocation(center.x - dialog.getWidth() / 2, center.y - dialog.getHeight() / 2);

        dialog.setVisible(true);
        return ((Integer)optionPane.getValue()).intValue(); // 返回用户的选择
    }
}