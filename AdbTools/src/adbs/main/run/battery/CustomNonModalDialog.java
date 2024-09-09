package adbs.main.run.battery;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CustomNonModalDialog {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGui());
    }

    private static void createAndShowGui() {
        // 主窗口
        JFrame mainFrame = new JFrame("Main Application");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(300, 200);
        mainFrame.setLayout(new BorderLayout());

        // 非模态对话框
        final JDialog dialog = new JDialog(mainFrame, "提示", true);
        dialog.setModalityType(Dialog.ModalityType.DOCUMENT_MODAL); // 设置为非模态
        dialog.setSize(300, 150);
        dialog.setLayout(new BorderLayout());

        // 对话框的内容面板
        JPanel contentPanel = new JPanel();
        contentPanel.add(new JLabel("电量充足，禁止USB充电?"));
        dialog.add(contentPanel, BorderLayout.CENTER);

        // 按钮面板
        JPanel buttonPanel = new JPanel();
        JButton yesButton = new JButton("确定");
        JButton noButton = new JButton("否");
        JButton cancelButton = new JButton("取消");

        buttonPanel.add(yesButton);
        buttonPanel.add(noButton);
        buttonPanel.add(cancelButton);

        dialog.add(buttonPanel, BorderLayout.SOUTH);

        // 按钮的ActionListener
        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectOk();
                dialog.dispose();
            }
        });

        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectNo();
                dialog.dispose();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectCancel();
                dialog.dispose();
            }
        });

        // 添加窗口关闭监听器
        dialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                dialog.dispose(); // 关闭对话框
            }
        });

        // 显示对话框
        dialog.setLocationRelativeTo(mainFrame); // 居中显示
        dialog.setVisible(true);

        // 显示主窗口
        mainFrame.setVisible(true);
    }

    private static void selectOk() {
        System.out.println("选择了 '确定'");
    }

    private static void selectNo() {
        System.out.println("选择了 '否'");
    }

    private static void selectCancel() {
        System.out.println("选择了 '取消'");
    }
}
