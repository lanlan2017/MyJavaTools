package com.blue.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrom {

    private JPanel panel;
    private JLabel lable;
    private JButton exitButton;
    Point mousePressedPoint = new Point();

    public MainFrom() {
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        lable.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                // 获取顶层的JFrame容器
                JFrame frame = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, panel);
                // 获取窗体的位置
                Point point = frame.getLocation();
                // 设置窗体的新位置
                frame.setLocation(point.x + e.getX() - (int) mousePressedPoint.getX(), point.y + e.getY() - (int) mousePressedPoint.getY());

            }
        });

        lable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // 获取鼠标按压的位置
                mousePressedPoint.x = e.getX();
                mousePressedPoint.y = e.getY();
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MainFrom");

        frame.setContentPane(new MainFrom().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.setType(Window.Type.NORMAL);
        // frame.setType(Window.Type.POPUP);
        // 不显示任务栏
        frame.setType(Window.Type.UTILITY);
        // 不显示标题栏，最小化，关闭按钮
        frame.setUndecorated(true);
        // 永远置顶
        frame.setAlwaysOnTop(true);
        // 最合适的方式显示
        frame.pack();
        // 显示窗体
        frame.setVisible(true);
    }
}
