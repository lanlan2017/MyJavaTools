package adbs.ui;

import adbs.action.listener.*;
import adbs.action.runnable.*;
import adbs.cmd.AdbCommands;
import adbs.test.AdbDi;
import adbs.test.DeviceRadioButtonActionListener;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdbTools {
    static {
        // 设置外观,先设置外观，再创建UI。
        // 为了保证创建UI时，已经设置好外观，设置外观的代码最好写在静态块中，
        // 并且把这个静态块写在类定义的最前面。
        FlatLightLaf.setup();
    }

    // 当前正在执行的线程
    private static Runnable isRunning;

    public static Runnable getIsRunning() {
        return isRunning;
    }

    public static void setIsRunning(Runnable isRunning) {
        AdbTools.isRunning = isRunning;
    }

    private JPanel topPanel;
    private JPanel buttonPanel;
    private JPanel outputPanel;
    private JButton readButton;
    private JButton videoButton;
    private JButton readAdvButton;
    private JPanel inputPanel;
    private JTextField input;
    private JButton inputOkButton;
    private JLabel output;
    private JButton browseButton;
    private JLabel timeLable;
    private JRadioButton radioButton15s;
    private JRadioButton radioButton35s;
    private JRadioButton radioButton75s;
    private JPanel timeRadioPanel;
    private JButton shoppingButton;
    private JPanel devicePanel;
    private JLabel deviceLabel;
    private JButton stopButton;
    private JPanel controlPanel;
    private JButton openButton;

    public AdbTools() {
        JFrame frame = new JFrame();
        frame.setTitle("adb工具箱");
        // 禁止调整窗体大小
        frame.setResizable(false);
        frame.setContentPane(topPanel);
        // 添加当前设备面板
        devicePanel.add(new AdbDi().createDevicesPanel());

        topPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // super.mouseClicked(e);
                if (e.getButton() == MouseEvent.BUTTON3 && e.getClickCount() == 2) {
                    frame.pack();
                }
            }
        });
        // 不显示标题栏，最小化，关闭按钮
        // frame.setUndecorated(true);
        ButtonGroup bg = new ButtonGroup();
        bg.add(radioButton15s);
        bg.add(radioButton35s);
        bg.add(radioButton75s);
        radioButton15s.addActionListener(e -> input.setText(String.valueOf(15)));
        radioButton35s.addActionListener(e -> input.setText(String.valueOf(35)));
        radioButton75s.addActionListener(e -> input.setText(String.valueOf(75)));

        inputOkButton.addActionListener(new InputOkButtonActionListener(output));
        // 刚开始,隐藏输入面板
        inputPanel.setVisible(false);

        // 点击关闭按钮时退出程序
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // 永远置顶
        frame.setAlwaysOnTop(true);
        // 最合适的方式显示
        frame.pack();
        // 显示窗体
        frame.setVisible(true);

        // 添加事件处理程序
        videoButton.addActionListener(new VideoButtonActionListener(output));
        readButton.addActionListener(new ReadButtonActionListener(output));
        readAdvButton.addActionListener(new AdvButtonActionListener(readButton, stopButton, output));
        browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputPanel.setVisible(true);
                input.setText(String.valueOf(35));
                // 默认75秒
                radioButton75s.doClick();
                inputOkButton.setText("开始浏览");
                frame.pack();
            }
        });
        shoppingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputPanel.setVisible(true);
                // 逛街20分钟
                input.setText(String.valueOf(20 * 60));
                inputOkButton.setText("开始逛街");
                frame.pack();
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 如果是视频线程正在运行的话
                if (isRunning instanceof AdvButtonRunnable) {
                    // 停止视频线程
                    AdvButtonRunnable.setStop(true);
                } else if (isRunning instanceof BrowseRunnable) {
                    // BrowseRunnable.setStop(true);
                    BrowseRunnable.setStop(true);
                    input.setText("确认");
                    inputPanel.setVisible(false);
                    frame.pack();
                } else if (isRunning instanceof ReadButtonRunnable) {
                    ReadButtonRunnable.setStop(true);
                } else if (isRunning instanceof ShoppingButtonRunnable) {
                    ShoppingButtonRunnable.setStop(true);
                    input.setText("确认");
                    inputPanel.setVisible(false);
                    frame.pack();
                } else if (isRunning instanceof VideoButtonRunnable) {
                    VideoButtonRunnable.setStop(true);
                }
            }
        });
        openButton.addActionListener(new OpenButtonListener());
    }

    public static void main(String[] args) {
        AdbTools adbTools = new AdbTools();
    }
}
