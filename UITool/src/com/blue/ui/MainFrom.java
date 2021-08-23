package com.blue.ui;

import javax.swing.*;
import java.awt.*;

public class MainFrom {

    private JLabel lable;
    private JPanel panel;

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
