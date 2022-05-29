package adbs.ui;

import adbs.action.listener.*;
import adbs.action.model.InOutputModel;
import adbs.action.model.InputPanelModel;
import adbs.action.runnable.*;
// import adbs.action.runnable.KuaiShouYueDuRunnable;
import adbs.action.runnable.KuaiShouYueDuRunnable;
import adbs.cmd.CmdRun;
import adbs.test.AdbDi;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

public class AdbTools {
    static {
        // 设置外观,先设置外观，再创建UI。
        // 为了保证创建UI时，已经设置好外观，设置外观的代码最好写在静态块中，
        // 并且把这个静态块写在类定义的最前面。
        FlatLightLaf.setup();
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
    private JRadioButton radioButton70s;
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
    private JButton douyinSeeVideoBtn;
    private JPanel controlPanel;
    private JCheckBox customCheckBox;
    private JCheckBox dormantCheckBox;
    private JPanel dormantPanel;
    private JTextField dormantTextField;
    private JButton dormantOKButton;
    private JLabel dormantJLable2;
    private JLabel dormantJLable1;
    private JButton pddKaiHongBaoBtn;
    private JButton aiQiYiBtn;

    // 当前正在执行的线程
    // private static Runnable isRunning;
    private static HashSet<Runnable> isRunningSet = new HashSet<>();
    private final JFrame frame;
    // 输入输出汇总
    private InOutputModel inOutputModel;

    private static AdbTools instance = new AdbTools();

    public static AdbTools getInstance() {
        return instance;
    }

    public InOutputModel getInputOutputModel() {
        return inOutputModel;
    }

    private AdbTools() {
        frame = new JFrame();
        frame.setTitle("adb工具箱");
        // 禁止调整窗体大小
        frame.setResizable(false);
        frame.setContentPane(topPanel);
        // 窗体内容面板监听鼠标事件
        topPanel.addMouseListener(new TopPanelMouseAdapter(frame));
        // 添加当前设备面板
        devicePanel.add(new AdbDi().createDevicesPanel());
        // 打开（设备）按钮
        openButton.addActionListener(new OpenButtonListener());

        // 创建输入面板的模型
        InputPanelModel inputPanelModel = new InputPanelModel(inputPanel, timeLable, timeRadioPanel, radioButton15s, radioButton35s, radioButton70s, input1, input2, inputOkButton);
        inOutputModel = new InOutputModel(inputPanelModel, output);

        // 通用按钮面板 使用网格布局
        buttonPanel.setLayout(new GridLayout(2, 4, 1, 1));
        // 特定面板按钮 使用网格布局
        specificPanel.setLayout(new GridLayout(2, 4, 1, 1));


        // 输入面板等待按钮
        inputOkButton.addActionListener(new InputOkButtonActionListener(inOutputModel));


        // 刷视频按钮
        videoButton.addActionListener(new VideoButtonActionListener(frame, inputPanelModel));
        kuaiShouReadButton.addActionListener(new KuaiShouYueDuButtonListener(kuaiShouReadButton, inOutputModel));
        // 浏览后返回按钮事件处理程序
        browseButton.addActionListener(new BrowseButtonActionListener(frame, inputPanelModel));
        // 逛街按钮
        shoppingButton.addActionListener(new ShoppingButtonActionListener(frame, inputPanelModel));

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("isRunningSet.size() = " + isRunningSet.size());

                Iterator<Runnable> iterator = isRunningSet.iterator();
                while (iterator.hasNext()) {
                    Runnable isRunning = iterator.next();

                    System.out.println(isRunning);
                    // 如果是视频线程正在运行的话
                    if (isRunning instanceof BrowseRunnable) {
                        // 结束正在执行的线程
                        BrowseRunnable.setStop(true);
                    } else if (isRunning instanceof ReadButtonRunnable) {
                        // 如果当前正在运行的线程是 阅读线程的话，就停止阅读线程
                        // 或者当前正在运行的线程是 快手阅读广告监听线程 的话
                        ReadButtonRunnable.setStop(true);
                    }
                    // else if (isRunning instanceof KuaiShouYueDuRunnable) {
                    //     // 停止阅读广告监听线程
                    //     KuaiShouYueDuRunnable.setStop(true);
                    // }
                    else if (isRunning instanceof KuaiShouYueDuRunnable) {
                        // 停止阅读广告监听线程
                        KuaiShouYueDuRunnable.setStop(true);
                    } else if (isRunning instanceof ShoppingButtonRunnable) {
                        // 如果是逛街线程
                        ShoppingButtonRunnable.setStop(true);
                    } else if (isRunning instanceof VideoButtonRunnable) {
                        // 如果是刷视频线程在运行
                        // 关闭刷视频线程
                        VideoButtonRunnable.setStop(true);
                    } else if (isRunning instanceof WaitReturnButtonRunnable) {
                        // 如果是等待后返回线程
                        WaitReturnButtonRunnable.setStop(true);
                    } else if (isRunning instanceof WuKongGuanBiRunnable) {
                        // 如果是等待后返回线程
                        WuKongGuanBiRunnable.setStop(true);
                    } else if (isRunning instanceof DouYinVideoButtonRunnable2) {
                        // 结束抖音看视频红包监听线程
                        DouYinVideoButtonRunnable2.setStop(true);
                    } else if (isRunning instanceof PddHongBaoOpenRunnable) {
                        PddHongBaoOpenRunnable.setStop(true);
                    } else if (isRunning instanceof AiQiYiRunnable) {
                        AiQiYiRunnable.setStop(true);
                    }

                    iterator.remove();
                }
                System.out.println("end isRunningSet.size() = " + isRunningSet.size());
                inputPanel.setVisible(false);
            }
        });
        // 等待后返回按钮
        waitReturnButton.addActionListener(new WaitReturnButtonActionListener(frame, inputPanelModel));
        // 悟空看视频按钮
        wuKongGuanBiBtn.addActionListener(e -> new Thread(new WuKongGuanBiRunnable()).start());
        // 阅读按钮
        readButton.addActionListener(e -> {
            ReadButtonRunnable readButtonRunnable = new ReadButtonRunnable(inOutputModel);
            new Thread(readButtonRunnable).start();
        });
        douyinSeeVideoBtn.addActionListener(new DouYinSeeVideoButtonListener(frame, inOutputModel));
        dormantCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                // 如果勾选了当前按钮
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    dormantPanel.setVisible(true);
                } else {
                    dormantPanel.setVisible(false);
                }
                frame.pack();
            }
        });
        customCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    specificPanel.setVisible(true);
                } else {
                    specificPanel.setVisible(false);
                }
                frame.pack();
            }
        });
        dormantOKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Timer timer = new Timer(true);
                // 注意，javax.swing包中也有一个Timer类，如果import中用到swing包,要注意名字的冲突。

                String text = dormantTextField.getText();
                TimerTask task = new TimerTask() {
                    public void run() {
                        System.out.println(text + "m到了");
                        // 杀死悟空浏览器
                        // CmdRun.run("adb shell am force-stop com.cat.readall");
                        // 杀死快手极速版
                        CmdRun.run("adb shell am force-stop com.kuaishou.nebula");
                        // // 杀死抖音极速版
                        // CmdRun.run("adb shell am force-stop com.ss.android.ugc.aweme.lite");
                        // 息屏，并且休眠电脑
                        CmdRun.run("adb shell input keyevent 223 && shutdown /h");
                        // CmdRun.run("adb shell input keyevent 223");
                    }
                };

                long ms = Long.parseLong(text) * 60 * 1000;
                // timer.schedule(task, 5 * 1000);
                // 等待指定毫秒后执行任务
                timer.schedule(task, ms);
            }
        });
        pddKaiHongBaoBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(new PddHongBaoOpenRunnable()).start();
            }
        });


        // 不显示标题栏，最小化，关闭按钮
        // frame.setUndecorated(true);
        // 点击关闭按钮时退出程序
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // 永远置顶
        frame.setAlwaysOnTop(true);
        // 最合适的方式显示
        frame.pack();
        // 隐藏休眠设置面板
        dormantPanel.setVisible(false);
        // 显示窗体
        frame.setVisible(true);
        aiQiYiBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(new AiQiYiRunnable()).start();
            }
        });
    }

    public static void setIsRunning(Runnable isRunning) {
        isRunningSet.add(isRunning);
    }

    public static void main(String[] args) {
        // new AdbTools();
        AdbTools.getInstance();
    }
}
