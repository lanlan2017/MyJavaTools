package com.blue.ui;

import tools.config.ConfigTools;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainFrom {

    private JFrame frame;
    private JPanel panel;
    private JButton exitButton;
    private JButton showButton;
    private JTextField textField;
    private JTextArea textArea;
    private JLabel lable;
    /**
     * 记录鼠标移动的点
     */
    Point mousePressedPoint = new Point();

    public MainFrom() {

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        panel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                // 获取顶层的JFrame容器
                // JFrame frame = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, panel);
                // 获取窗体的位置
                Point point = frame.getLocation();
                // frame.repaint();
                // 设置窗体的新位置
                frame.setLocation(point.x + e.getX() - (int) mousePressedPoint.getX(), point.y + e.getY() - (int) mousePressedPoint.getY());

            }
        });

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // 获取鼠标按压的位置
                mousePressedPoint.x = e.getX();
                mousePressedPoint.y = e.getY();
            }
        });

        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                // System.out.println();
                // 如果按下回车键
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    // 显示文本域
                    showTextArea();
                    // 获取文本框的内容
                    String input = textField.getText();
                    if ("" != input && input != null) {
                        // 处理输入的文本
                        String output = doTextField(input);
                        // 处理结果写到文本域中
                        textArea.setText(output);
                    }
                }
            }
        });
    }

    /**
     * 处理输入的内容
     *
     * @param input
     * @return
     */
    private String doTextField(String input) {
        String output = "";
        String[] args = input.split(" ");
        // for (int i = 0; i < args.length; i++) {
        //     output += args[i] + "\n";
        // }
        // 执行命令，返回执行结果
        output = ConfigTools.getInstance().forward(args);
        // 赋值到系统剪贴板
        ConfigTools.getInstance().copyToSysClipboard(output);
        return output;
    }

    /**
     * 显示文本域
     */
    private void showTextArea() {
        // 如果textArea不可见
        if (!textArea.isVisible()) {
            // 设置为可见
            textArea.setVisible(true);
        }
        // 重绘组件
        repaint();
    }

    /**
     * 重绘组件
     */
    private void repaint() {
        // 重绘面板
        panel.repaint();
        // 整个窗体最小显示
        frame.pack();
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    private String formatDateStrNow() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        return format.format(date);
    }

    public static void main(String[] args) {
        MainFrom mainFrom = new MainFrom();
        JFrame frame = new JFrame("MainFrom");
        // 设置面板到窗体上
        frame.setContentPane(mainFrom.panel);
        // // 设置关闭按钮功能
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.setType(Window.Type.NORMAL);
        // frame.setType(Window.Type.POPUP);

        // 不显示任务栏
        frame.setType(Window.Type.UTILITY);
        // 不显示标题栏，最小化，关闭按钮
        // frame.setUndecorated(true);
        // 永远置顶
        frame.setAlwaysOnTop(true);


        // 设置初始界面
        mainFromSetting(mainFrom, frame);

        // 最合适的方式显示
        frame.pack();
        // 显示窗体
        frame.setVisible(true);
    }

    /**
     * 设置窗口初始界面
     *
     * @param mainFrom 主界面
     * @param frame    窗体
     */
    private static void mainFromSetting(MainFrom mainFrom, JFrame frame) {
        // 记下窗体的引用
        mainFrom.setFrame(frame);
        // 该开始的时候不要显示文本域
        mainFrom.textArea.setVisible(false);
    }
}
