package tools.swing.dialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.util.ArrayList;

public class DialogFactory {
    private static volatile boolean alwaysOnTop;
    //    private static final JDialog dialog = new JDialog();
    private static final ArrayList<JDialog> dialogs = new ArrayList<>();

    public static void setAlwaysOnTop(boolean alwaysOnTop) {
        DialogFactory.alwaysOnTop = alwaysOnTop;
    }

    public static ArrayList<JDialog> getDialogs() {
        return dialogs;
    }

    // 创建一个新的对话框实例
    private static JDialog ok(String title, String message, ActionListener okListener) {
        JDialog dialog = new JDialog();
        dialogs.add(dialog);

        //        dialog.setAlwaysOnTop(true);
        dialog.setAlwaysOnTop(alwaysOnTop);
        dialog.setTitle(title);
        //        dialog.setModal(true);
        dialog.setModal(false);
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

        // 添加消息标签
        JLabel messageLabel = new JLabel(message);
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        messageLabel.setVerticalAlignment(SwingConstants.CENTER);

        // 初始化按钮
        JButton okButton = new JButton("确定");
        //        JButton cancelButton = new JButton("取消");

        // 设置dialog的布局，并添加消息标签和按钮
        dialog.setLayout(new BorderLayout());
        dialog.add(messageLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(okButton);
        //        buttonPanel.add(cancelButton);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        // 添加按钮的点击监听器
        okButton.addActionListener(okListener);
        okButton.addActionListener(e -> {
            dialogs.remove(dialog);
            dialog.dispose();

        });
        //        cancelButton.addActionListener(cancelListener);

        // 添加窗口监听器来处理关闭事件
        dialog.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                dialogs.remove(dialog);
                dialog.dispose();
            }
        });

        dialog.pack();

        return dialog;
    }

    /**
     * 创建只有一个确按钮的对话框
     *
     * @param title         标题
     * @param message       消息
     * @param okListener    确定按钮事件处理
     * @param windowAdapter 关闭控件事件处理
     * @return
     */
    private static JDialog okClose(String title, String message, ActionListener okListener, WindowAdapter windowAdapter) {
        JDialog dialog = new JDialog();
        dialogs.add(dialog);
        //        dialog.setAlwaysOnTop(true);
        dialog.setAlwaysOnTop(alwaysOnTop);
        dialog.setTitle(title);
        //        dialog.setModal(true);
        dialog.setModal(false);
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

        // 添加消息标签
        JLabel messageLabel = new JLabel(message);
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        messageLabel.setVerticalAlignment(SwingConstants.CENTER);

        // 初始化按钮
        JButton okButton = new JButton("确定");
        //        JButton cancelButton = new JButton("取消");

        // 设置dialog的布局，并添加消息标签和按钮
        dialog.setLayout(new BorderLayout());
        dialog.add(messageLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(okButton);
        //        buttonPanel.add(cancelButton);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        // 添加按钮的点击监听器
        okButton.addActionListener(okListener);
        okButton.addActionListener(e -> {
            dialogs.remove(dialog);
            dialog.dispose();
        });
        //        cancelButton.addActionListener(cancelListener);

        // 添加窗口监听器来处理关闭事件
        dialog.addWindowListener(windowAdapter);
        // 添加窗口监听器来处理关闭事件
        dialog.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                dialogs.remove(dialog);
                dialog.dispose();
            }
        });

        dialog.pack();

        return dialog;
    }


    // 创建一个新的对话框实例
    private static JDialog okCancel(String title, String message, ActionListener okListener, ActionListener cancelListener) {
        JDialog dialog = new JDialog();
        dialogs.add(dialog);
        //        dialog.setAlwaysOnTop(true);
        dialog.setAlwaysOnTop(alwaysOnTop);
        dialog.setTitle(title);
        //        dialog.setModal(true);
        //非模式，不阻塞主程序
        dialog.setModal(false);
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
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialogs.remove(dialog);
                dialog.dispose();
            }
        });
        cancelButton.addActionListener(cancelListener);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialogs.remove(dialog);
                dialog.dispose();
            }
        });

        // 添加窗口监听器来处理关闭事件  
        dialog.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                dialogs.remove(dialog);
                dialog.dispose();
            }
        });

        dialog.pack();

        return dialog;
    }


    public static void showDialogOk(JFrame jFrame, String title, String message, ActionListener actionListenerOk) {
        JDialog dialog = DialogFactory.ok(title, message, actionListenerOk);
        dialog.setLocationRelativeTo(jFrame);
        dialog.setVisible(true);
        //        new Thread()
    }

    public static void showDialogOkClose(JFrame jFrame, String title, String message, ActionListener actionListenerOk, WindowAdapter windowAdapter) {
        JDialog dialog = DialogFactory.okClose(title, message, actionListenerOk, windowAdapter);
        dialog.setLocationRelativeTo(jFrame);
        dialog.setVisible(true);
        //        new Thread()
    }

    public static void showDialogOkCancel(JFrame jFrame, String title, String message, ActionListener actionListenerOk, ActionListener actionListenerCancel) {
        JDialog dialog = DialogFactory.okCancel(title, message, actionListenerOk, actionListenerCancel);
        dialog.setLocationRelativeTo(jFrame);
        dialog.setVisible(true);
    }

    // 主方法或其他地方调用示例
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("主窗口");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);

            JButton showDialogButton = new JButton("显示弹窗");
            showDialogButton.addActionListener(e -> {
                ActionListener actionListenerOk = ae -> System.out.println("确定按钮被点击了");
                ActionListener actionListenerCancel = ae -> System.out.println("确定按钮被点击了");
                //                ActionListener actionListenerOk ;
                //                ActionListener actionListenerCancel;
                String title = "自定义标题";
                String message = "这是一个提示消息";
                JFrame frame1 = frame;

                showDialogOkCancel(frame1, title, message, actionListenerOk, actionListenerCancel);
            });
            frame.getContentPane().add(showDialogButton, BorderLayout.CENTER);
            frame.setVisible(true);
        });

        setAlwaysOnTopFalse();
    }

    /**
     * 设置这些弹窗不要置顶
     * 不要置顶
     */
    public static void setAlwaysOnTopFalse() {
        for (JDialog dialog : dialogs) {
            dialog.setAlwaysOnTop(false);
        }
        // 如果有新建的弹窗的置顶功能也取消
        setAlwaysOnTop(false);

    }

}
