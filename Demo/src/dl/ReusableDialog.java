package dl;

import javax.swing.*;
import java.awt.*;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  
import java.awt.event.WindowAdapter;  
import java.awt.event.WindowEvent;
import java.util.function.Consumer;

public class ReusableDialog {  
  
    private JDialog dialog;  
    private JButton okButton;  
    private JButton cancelButton;  
    private JLabel messageLabel;  
  
    public ReusableDialog(String title, String message) {  
        dialog = new JDialog();  
        dialog.setTitle(title); // 设置可更改的标题  
        dialog.setModal(true);  
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);  
  
        // 添加消息标签  
        messageLabel = new JLabel(message);  
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);  
        messageLabel.setVerticalAlignment(SwingConstants.CENTER);  
        messageLabel.setOpaque(true); // 如果需要背景色  
        messageLabel.setBackground(dialog.getBackground()); // 或者设置为其他颜色  
  
        // ... 初始化按钮和布局等  
        JPanel buttonPanel = new JPanel();  
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));  
        okButton = new JButton("确定");  
        cancelButton = new JButton("取消");  
  
        // 设置dialog的布局，并添加消息标签和按钮面板  
        dialog.setLayout(new BorderLayout());  
        dialog.add(messageLabel, BorderLayout.CENTER); // 将消息标签添加到中央  
        dialog.add(buttonPanel, BorderLayout.SOUTH); // 将按钮面板添加到底部  
  
        // 添加按钮的点击监听器  
        okButton.addActionListener(e -> onOkButtonClicked.accept(e));  
        cancelButton.addActionListener(e -> onCancelButtonClicked.accept(e));  
  
        // 添加窗口监听器来处理关闭事件  
        dialog.addWindowListener(new WindowAdapter() {  
            @Override  
            public void windowClosing(WindowEvent e) {  
                dialog.dispose();  
            }  
        });  
  
        // 初始化按钮点击事件的消费者（默认为空操作）  
        this.onOkButtonClicked = e -> {};  
        this.onCancelButtonClicked = e -> {};  
  
        // 添加按钮到面板  
        buttonPanel.add(okButton);  
        buttonPanel.add(cancelButton);  
    }  
  
    // 显示弹窗的方法，并接收确定和取消的点击动作  
    public void showDialog(Component parentComponent,  
                           Consumer<ActionEvent> onOkButtonClicked,
                           Consumer<ActionEvent> onCancelButtonClicked) {  
        this.onOkButtonClicked = onOkButtonClicked;  
        this.onCancelButtonClicked = onCancelButtonClicked;  
  
        dialog.pack();  
        dialog.setLocationRelativeTo(parentComponent);  
        dialog.setVisible(true);  
    }  
  
    // 确定按钮的点击动作（由外部传入）  
    private Consumer<ActionEvent> onOkButtonClicked;  
  
    // 取消按钮的点击动作（由外部传入）  
    private Consumer<ActionEvent> onCancelButtonClicked;  
  
    // 主方法或其他地方调用示例  
    public static void main(String[] args) {  
        SwingUtilities.invokeLater(() -> {  
            JFrame frame = new JFrame("主窗口");  
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
            frame.setSize(400, 300);  
  
            JButton showDialogButton = new JButton("显示弹窗");  
            showDialogButton.addActionListener(e -> {  
                ReusableDialog dialog = new ReusableDialog("自定义标题", "这是一个提示消息");  
                dialog.showDialog(frame,  
                        ae -> System.out.println("确定按钮被点击了"),  
                        ae -> System.out.println("取消按钮被点击了")  
                );  
            });  
            frame.getContentPane().add(showDialogButton, BorderLayout.CENTER);  
  
            frame.setVisible(true);  
        });  
    }  
}