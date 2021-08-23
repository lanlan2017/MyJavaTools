package com.blue.ui;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;
import tools.config.ConfigTools;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrom {

    private JFrame frame;
    private JPanel panel;
    private JButton exitButton;
    private JTextField textField;
    private JTextArea textArea;
    private JLabel lable;

    public MainFrom() {

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // 监听面板事件
        panel.addMouseMotionListener(new MouseAdapter() {
            private boolean isNearTop = false;
            private boolean isNearBottom = false;
            private boolean isNearLeft = false;
            private boolean isNearRight = false;
            private boolean drag = false;
            // 移动的位置
            private Point draggingAnchor = null;

            // 监听鼠标移动事件
            @Override
            public void mouseMoved(MouseEvent e) {
                // 精度，距离窗体边框多少距离时可以拖动来调整窗体的大小。
                int jingDu = 5;
                // if (e.getPoint().getY() == 0) {
                if (Math.abs(e.getPoint().getY() - 0) <= jingDu) {
                    // 设置拖动光标
                    frame.setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
                    isNearTop = true;
                    // System.out.println("Cursor is near TOP");
                } else if (Math.abs(e.getPoint().getY() - frame.getSize().getHeight()) <= jingDu) {
                    frame.setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
                    isNearBottom = true;
                    // System.out.println("Cursor is near Bottom");
                }
                // else if (e.getPoint().getX() == 0) {
                else if (Math.abs(e.getPoint().getX() - 0) <= jingDu) {
                    frame.setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
                    isNearLeft = true;
                    // System.out.println("Cursor is near left");
                } else if (Math.abs(e.getPoint().getX() - frame.getSize().getWidth()) <= jingDu) {
                    frame.setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
                    isNearRight = true;
                    // System.out.println("Cursor is near right");
                } else {
                    frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                    draggingAnchor = new Point(e.getX() + panel.getX(), e.getY() + panel.getY());
                    isNearTop = false;
                    isNearBottom = false;
                    isNearLeft = false;
                    isNearRight = false;
                    drag = true;
                    // System.out.println("is near center");
                }
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                // 获取窗体的大小
                Dimension dimension = frame.getSize();
                // 当鼠标指针在顶部的时候
                if (isNearTop) {
                    // 设置高度减去鼠标移动后的坐标
                    dimension.setSize(dimension.getWidth(), dimension.getHeight() - e.getY());
                    // 设置窗体的大小
                    frame.setSize(dimension);
                    // 移动变大后的窗体到原来的坐标
                    frame.setLocation(frame.getLocationOnScreen().x, frame.getLocationOnScreen().y + e.getY());
                }
                // 鼠标指针在底部时
                else if (isNearBottom) {
                    dimension.setSize(dimension.getWidth(), e.getY());
                    // 设置窗体的大小
                    frame.setSize(dimension);
                }
                // 当鼠标指针在左边时
                else if (isNearLeft) {

                    dimension.setSize(dimension.getWidth() - e.getX(), dimension.getHeight());
                    frame.setSize(dimension);
                    // 移动窗体的坐标
                    frame.setLocation(frame.getLocationOnScreen().x + e.getX(), frame.getLocationOnScreen().y);
                }
                // 当鼠标指针在右边时
                else if (isNearRight) {
                    dimension.setSize(e.getX(), dimension.getHeight());
                    // 设置窗体的大小
                    frame.setSize(dimension);
                }
                // 当鼠标指针在中间时
                else if (drag) {
                    // 移动窗体的位置
                    frame.setLocation(e.getLocationOnScreen().x - draggingAnchor.x, e.getLocationOnScreen().y - draggingAnchor.y);
                }

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
                    if (!"".equals(input) && input != null) {
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
     * @param input 文本框中输入的文本
     * @return 对文本框中的文本处理的结果
     */
    private String doTextField(String input) {
        String output;
        // 按空格分隔得到命令
        String[] args = input.split(" ");

        // 执行命令，返回执行结果
        output = ConfigTools.getInstance().forward(args);
        // 复制到系统剪贴板
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

    /**
     * 设置窗口初始界面
     *
     * @param mainFrom 主界面
     * @param frame    窗体
     */
    private static void mainFromSetting(MainFrom mainFrom, JFrame frame) {
        // 记下窗体的引用
        mainFrom.frame = frame;
        // 该开始的时候不要显示文本域
        mainFrom.textArea.setVisible(false);
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
        frame.setUndecorated(true);
        // 永远置顶
        frame.setAlwaysOnTop(true);

        // 设置初始界面
        mainFromSetting(mainFrom, frame);
        // 设置主题
        // FlatDarkLaf.setup();
        FlatLightLaf.setup();
        // 给所有的组件都使用该主题
        SwingUtilities.updateComponentTreeUI(frame);
        // 最合适的方式显示
        frame.pack();
        // 显示窗体
        frame.setVisible(true);
    }

}
