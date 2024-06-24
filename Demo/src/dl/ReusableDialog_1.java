package dl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.function.Consumer;

public class ReusableDialog_1 {
  
    private JDialog dialog;  
    private JButton okButton;  
    private JButton cancelButton;  
  
    public ReusableDialog_1() {
        dialog = new JDialog();  
        dialog.setTitle("可重用弹窗");  
        dialog.setModal(true);  
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);  
  
        // 初始化按钮  
        okButton = new JButton("确定");  
        cancelButton = new JButton("取消");  
  
        // 添加按钮到dialog（这里省略了布局代码）  
        // ...  
  
        // 设置按钮的点击监听器  
        okButton.addActionListener(e -> onOkButtonClicked.accept(e));  
        cancelButton.addActionListener(e -> onCancelButtonClicked.accept(e));  
    }  
  
    // 显示弹窗的方法，并接收确定和取消的点击动作  
    public void showDialog(Component parentComponent, Consumer<ActionEvent> onOkButtonClicked, Consumer<ActionEvent> onCancelButtonClicked) {
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

                ReusableDialog_1 dialog = new ReusableDialog_1();
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