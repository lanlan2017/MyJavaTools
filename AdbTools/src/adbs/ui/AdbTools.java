package adbs.ui;

import adbs.action.listener.*;
import adbs.action.runnable.*;
import adbs.action.runnable.KuaiShouYueDuRunnable;
import adbs.test.AdbDi;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;
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
    private final JFrame frame;

    public static Runnable getIsRunning() {
        return isRunning;
    }

    public static void setIsRunning(Runnable isRunning) {
        AdbTools.isRunning = isRunning;
    }

    private JPanel topPanel;
    private JPanel buttonPanel;
    private JPanel outputPanel;
    private JButton kuaiShouReadButton;
    private JButton videoButton;
    private JPanel inputPanel;
    private JTextField input1;
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
    private JButton openButton;
    private JButton waitReturnButton;
    private JTextField input2;
    private JPanel adbControlPanel;
    private JPanel adbPanel;
    private JPanel specificPanel;
    private JButton wuKongGuanBiBtn;
    private JButton readButton;

    public AdbTools() {
        frame = new JFrame();
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
        // 使用网格布局
        buttonPanel.setLayout(new GridLayout(2, 4, 1, 1));

        // 不显示标题栏，最小化，关闭按钮
        // frame.setUndecorated(true);
        ButtonGroup bg = new ButtonGroup();
        bg.add(radioButton15s);
        bg.add(radioButton35s);
        bg.add(radioButton75s);
        radioButton15s.addActionListener(e -> input1.setText(String.valueOf(15)));
        radioButton35s.addActionListener(e -> input1.setText(String.valueOf(35)));
        radioButton75s.addActionListener(e -> input1.setText(String.valueOf(75)));

        inputOkButton.addActionListener(new InputOkButtonActionListener(inputPanel, input1, output));
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
        videoButton.addActionListener(new VideoButtonActionListener(frame, inputPanel, output));
        kuaiShouReadButton.addActionListener(new ReadButtonActionListener(kuaiShouReadButton, stopButton, output));


        browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputPanel.setVisible(true);
                input1.setText(String.valueOf(35));
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
                input1.setText(String.valueOf(20 * 60));
                inputOkButton.setText("开始逛街");
                frame.pack();
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(isRunning);
                // 如果是视频线程正在运行的话
                if (isRunning instanceof BrowseRunnable) {
                    BrowseRunnable.setStop(true);
                    closeInputPanel();
                } else if (isRunning instanceof ReadButtonRunnable || isRunning instanceof KuaiShouYueDuRunnable) {
                    // 如果当前正在运行的线程是 阅读线程的话，就停止阅读线程
                    // 或者当前正在运行的线程是 快手阅读广告监听线程 的话
                    ReadButtonRunnable.setStop(true);
                    // 停止阅读广告监听线程
                    KuaiShouYueDuRunnable.setStop(true);
                    // AdbCommands.runAbdCmd("taskkill /F /IM python.exe");
                    // System.out.println(AdbCommands.runAbdCmd("tasklist |findstr python"));
                } else if (isRunning instanceof ShoppingButtonRunnable) {
                    // 如果是逛街线程
                    ShoppingButtonRunnable.setStop(true);
                    closeInputPanel();
                } else if (isRunning instanceof VideoButtonRunnable) {
                    // 如果是刷视频线程在运行
                    // 关闭刷视频线程
                    VideoButtonRunnable.setStop(true);
                    input2.setVisible(false);
                    closeInputPanel();
                } else if (isRunning instanceof WaitReturnButtonRunnable) {
                    // 如果是等待后返回线程
                    WaitReturnButtonRunnable.setStop(true);
                    closeInputPanel();
                } else if (isRunning instanceof WuKongGuanBiRunnable) {
                    // 如果是等待后返回线程
                    WuKongGuanBiRunnable.setStop(true);
                    // closeInputPanel();
                }
            }
        });
        openButton.addActionListener(new OpenButtonListener());
        waitReturnButton.addActionListener(new WaitReturnButtonActionListener(frame, inputPanel, input1, inputOkButton));
        // 悟空浏览器广告关闭监听
        wuKongGuanBiBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(new WuKongGuanBiRunnable()).start();
            }
        });
        readButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(new ReadButtonRunnable(output)).start();
            }
        });
    }

    /**
     * 关闭输入面板
     */
    private void closeInputPanel() {
        inputPanel.setVisible(false);
        inputOkButton.setText("确认");
        frame.pack();
    }


    public static void main(String[] args) {
        AdbTools adbTools = new AdbTools();
    }
}
