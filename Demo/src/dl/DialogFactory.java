package dl;

import javax.swing.*;
import java.awt.*;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  
  
public class DialogFactory {  
  
    // 创建一个新的对话框实例  
    public static JDialog createDialog(String title, String message, ActionListener okListener, ActionListener cancelListener) {  
        JDialog dialog = new JDialog();  
        dialog.setTitle(title);  
        dialog.setModal(true);  
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);  
  
        // 添加消息标签  
        JLabel messageLabel = new JLabel(message);  
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);  
        messageLabel.setVerticalAlignment(SwingConstants.CENTER);  
  
        // 初始化按钮  
        JButton okButton = new JButton("确定");  
        JButton cancelButton = new JButton("取消");  
  
        // 设置dialog的布局，并添加消息标签和按钮  
        dialog.setLayout(new BorderLayout());  
        dialog.add(messageLabel, BorderLayout.CENTER);  
  
        JPanel buttonPanel = new JPanel();  
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));  
        buttonPanel.add(okButton);  
        buttonPanel.add(cancelButton);  
        dialog.add(buttonPanel, BorderLayout.SOUTH);  
  
        // 添加按钮的点击监听器  
        okButton.addActionListener(okListener);  
        cancelButton.addActionListener(cancelListener);  
  
        // 添加窗口监听器来处理关闭事件  
        dialog.addWindowListener(new java.awt.event.WindowAdapter() {  
            @Override  
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {  
                dialog.dispose();  
            }  
        });  
  
        dialog.pack();  
          
        return dialog;  
    }  
  
    // 主方法或其他地方调用示例  
    public static void main(String[] args) {  
        SwingUtilities.invokeLater(() -> {  
            JFrame frame = new JFrame("主窗口");  
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
            frame.setSize(400, 300);  
  
            JButton showDialogButton = new JButton("显示弹窗");  
            showDialogButton.addActionListener(e -> {  
                JDialog dialog = DialogFactory.createDialog(  
                        "自定义标题",  
                        "这是一个提示消息",
                        ae -> System.out.println("确定按钮被点击了"),  
                        ae -> System.out.println("取消按钮被点击了")  
                );  
                dialog.setLocationRelativeTo(frame);  
                dialog.setVisible(true);  
            });  
            frame.getContentPane().add(showDialogButton, BorderLayout.CENTER);  
  
            frame.setVisible(true);  
        });  
    }  
}