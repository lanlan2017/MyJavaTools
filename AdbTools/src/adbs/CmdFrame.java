package adbs;

import adbs.action.listener.AdvButtonActionListener;
import adbs.action.runnable.AdvButtonRunnable;
import adbs.action.listener.ReadButtonActionListener;
import adbs.action.runnable.ReadButtonRunnable;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CmdFrame {
    static {
        // 设置外观,先设置外观，再创建UI。
        // 为了保证创建UI时，已经设置好外观，设置外观的代码最好写在静态块中，
        // 并且把这个静态块写在类定义的最前面。
        FlatLightLaf.setup();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("阅读辅助工具");
        frame.setLayout(new BorderLayout());
        // 输出面板
        JPanel outputPanel = new JPanel();
        outputPanel.setLayout(new FlowLayout());
        // 显示程序输出
        JLabel output = new JLabel("输出");
        //进制输入内容
        outputPanel.add(output);

        frame.add(outputPanel, BorderLayout.SOUTH);

        // 按钮面板
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        // 阅读按钮
        JButton readButton = new JButton("阅读");
        readButton.addActionListener(new ReadButtonActionListener(output));
        buttonPanel.add(readButton);
        // 停止阅读按钮
        JButton readStopButton = new JButton("停止阅读");
        readStopButton.addActionListener(e -> ReadButtonRunnable.setStop(true));
        buttonPanel.add(readStopButton);
        // 看广告按钮
        JButton advButton = new JButton("看广告");
        advButton.addActionListener(new AdvButtonActionListener(readButton, readStopButton, output));
        buttonPanel.add(advButton);

        JButton advStopButton = new JButton("退出广告");
        advStopButton.addActionListener(e -> AdvButtonRunnable.setStop(true));
        buttonPanel.add(advStopButton);

        frame.add(buttonPanel, BorderLayout.NORTH);
        frame.pack();
        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // super.mouseClicked(e);
                // 如果双击右键的话
                if (e.getButton() == MouseEvent.BUTTON3 && e.getClickCount() == 2) {
                    frame.pack();
                }
            }
        });
        // frame.setBounds(200, 200, 200, 200);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setAlwaysOnTop(true);
        frame.setVisible(true);

    }
}
