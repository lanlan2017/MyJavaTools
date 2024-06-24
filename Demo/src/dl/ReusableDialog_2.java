package dl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.function.Consumer;

/**
 * 可重用弹窗
 */
public class ReusableDialog_2 {

    private JDialog dialog;
    private JButton okButton;
    private JButton cancelButton;

    public ReusableDialog_2() {
        dialog = new JDialog();
        dialog.setTitle("可重用弹窗");
        dialog.setModal(true);

//        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
//        dialog.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//        dialog.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        /*
         * dialog.setDefaultCloseOperation() 方法接受的参数以及它们的含义如下：
         * WindowConstants.DO_NOTHING_ON_CLOSE
         *            （默认值通常不是它）：
         *           什么都不做。窗口不会隐藏，也不会被销毁。如果希望在关闭窗口时执行一些自定义的清理操作，或者希望防止窗口被关闭，可以使用这个值。
         * WindowConstants.HIDE_ON_CLOSE
         *        （对于 JDialog 的默认值通常是它，但对于 JFrame 通常不是）：
         *           隐藏窗口。窗口不会被销毁，只是变得不可见。这意味着你可以之后再次显示这个窗口，而不需要重新创建它。但请注意，如果窗口是模态的（modal），那么它的父窗口仍然会被阻塞，直到调用 dispose() 方法来销毁这个窗口。
         * WindowConstants.DISPOSE_ON_CLOSE
         *   （对于 JFrame 的默认值通常是它）：
         *   销毁窗口。调用 dispose() 方法来销毁窗口，释放窗口占用的所有资源。
         * WindowConstants.EXIT_ON_CLOSE
         *  （对于应用程序的主 JFrame 的默认值通常是它）：
         *   退出应用程序。这通常用于应用程序的主窗口。当主窗口被关闭时，会调用 System.exit(0) 来结束整个应用程序。但请注意，如果你把这个值设置给一个非主窗口（比如一个对话框），那么关闭这个窗口也会结束整个应用程序，这通常不是你想要的行为。
         * 所以，对于 JDialog，默认的 defaultCloseOperation 通常是 HIDE_ON_CLOSE，而对于 JFrame，默认的 defaultCloseOperation 通常是 DISPOSE_ON_CLOSE 或 EXIT_ON_CLOSE（取决于是否是主窗口）。但无论如何，为了代码的可读性和可维护性，最好总是使用 WindowConstants 接口中定义的常量，而不是直接使用整数值。
         *
         * */

        // 初始化按钮  
        okButton = new JButton("确定");
        cancelButton = new JButton("取消");

        // 创建面板来容纳按钮  
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT)); // 右侧对齐按钮  
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        // 设置dialog的布局，并添加按钮面板到dialog的底部  
        dialog.setLayout(new BorderLayout());
        dialog.add(buttonPanel, BorderLayout.SOUTH);

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
                ReusableDialog_2 dialog = new ReusableDialog_2();
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