package ui.frame;

import javax.swing.*;
import java.awt.*;

/**
 * 非模式对话框示例
 */
public class NonModalConfirmationDialog {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Non-Modal Confirmation Dialog");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);

        JButton minimizeButton = new JButton("Try to Minimize");
        minimizeButton.addActionListener(e -> showNonModalConfirmation(frame));
        frame.getContentPane().add(minimizeButton, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private static void showNonModalConfirmation(JFrame parent) {
        // 创建一个非模态的JDialog作为确认对话框
        // 第三个参数设为false，表示非模态
        JDialog dialog = new JDialog(parent, "确认", false);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        JLabel messageLabel = new JLabel("您确定要进行此操作吗？", SwingConstants.CENTER);
        JButton yesButton = new JButton("是");
        JButton noButton = new JButton("否");

        yesButton.addActionListener(e -> {
            System.out.println("用户点击了'是'");
            dialog.dispose(); // 关闭对话框
        });

        noButton.addActionListener(e -> {
            System.out.println("用户点击了'否'");
            dialog.dispose(); // 关闭对话框
        });

        contentPane.add(messageLabel);
        // 添加间距
        contentPane.add(Box.createRigidArea(new Dimension(0, 15)));
        contentPane.add(yesButton);
        contentPane.add(noButton);

        dialog.setContentPane(contentPane);
        dialog.pack();
        // 设置对话框位置相对父窗口居中
        dialog.setLocationRelativeTo(parent);
        dialog.setVisible(true);
    }
}