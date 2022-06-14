package adbs.ui;

import adbs.action.listener.*;
import adbs.action.listener.abs.shellinput.HomeBtnAcListener;
import adbs.action.listener.abs.shellinput.ReturnBtnAcListener;
import adbs.action.listener.abs.shellinput.TaskManageBtnAcListener;
import adbs.action.model.InOutputModel;
import adbs.action.model.InputPanelModel;
import adbs.action.runnable.*;
import adbs.cmd.CmdRun;
import adbs.test.AdbDi;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashSet;
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
    private JPanel universalPanel;
    private JPanel outputPanel;
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
    private JButton openButton;
    private JButton waitReturnButton;
    private JTextField input2;
    private JPanel adbControlPanel;
    private JPanel adbPanel;
    private JPanel customPanel;
    private JButton wuKongGuanBiBtn;
    private JButton douyinSeeVideoBtn;
    private JPanel controlPanel;
    private JCheckBox customCheckBox;
    private JCheckBox timedCheckbox;
    private JPanel dormantPanel;
    private JTextField dormantTextField;
    private JButton dormantOKButton;
    private JLabel dormantJLable2;
    private JLabel dormantJLable1;
    private JButton pddKaiHongBaoBtn;
    private JButton jinRiTouTiaoBtn;
    private JButton kuaishouTaskCenterBtn;
    private JButton taskManageBtn;
    private JButton returnBtn;
    private JButton homeBtn;
    private JButton douYinBtn;
    private JButton stopBtn;
    private JButton kuaiShouReadBtn;
    private JButton readButton;
    private JCheckBox universalCheckBox;
    private JButton kuaiShouVideoBtn;
    private JButton waitStopBtn;
    private JButton taoBaoNiuDanBtn;

    // 当前正在执行的线程
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
        // 设置窗体的内容面板
        frame.setContentPane(topPanel);
        // 窗体内容面板监听鼠标事件
        topPanel.addMouseListener(new TopPanelMouseAdapter(frame));
        // 添加当前设备面板
        devicePanel.add(new AdbDi(frame).createDevicesPanel());
        // 打开（设备）按钮
        openButton.addActionListener(new OpenButtonListener());
        // 创建输入面板的模型
        InputPanelModel inputPanelModel = new InputPanelModel(inputPanel, timeLable, timeRadioPanel, radioButton15s, radioButton35s, radioButton70s, input1, input2, inputOkButton);
        inOutputModel = new InOutputModel(inputPanelModel, output);

        // 输入面板等待按钮
        inputOkButton.addActionListener(new InputOkButtonActionListener(inOutputModel));


        // 刷视频按钮
        videoButton.addActionListener(new VideoButtonActionListener(frame, inputPanelModel));


        // 浏览后返回按钮事件处理程序
        browseButton.addActionListener(new BrowseButtonActionListener(frame, inputPanelModel));
        // 逛街按钮
        shoppingButton.addActionListener(new ShoppingButtonActionListener(frame, inputPanelModel));

        // 等待后返回按钮
        waitReturnButton.addActionListener(new WaitReturnButtonActionListener(frame, inputPanelModel));

        douyinSeeVideoBtn.addActionListener(new DouYinSeeVideoButtonListener(frame, inOutputModel));
        // 定时多选框
        timedCheckbox.addItemListener(new ItemListener() {
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
                // 如果勾选
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    customPanel.setVisible(true);
                } else {
                    customPanel.setVisible(false);
                }
                frame.pack();
            }
        });
        universalCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                // 如果勾选
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    universalPanel.setVisible(true);
                } else {
                    universalPanel.setVisible(false);
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
                // 等待指定毫秒后执行任务
                timer.schedule(task, ms);
            }
        });
        waitStopBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 创建定时器,守护进程
                Timer timer = new Timer(true);
                //
                String text = dormantTextField.getText();
                TimerTask task = new TimerTask() {
                    public void run() {
                        System.out.println(text + "m到了");
                        stopBtn.doClick();
                    }
                };

                long ms = Long.parseLong(text) * 60 * 1000;
                // 等待指定毫秒后执行任务
                timer.schedule(task, ms);
            }
        });

        // 悟空看视频按钮
        wuKongGuanBiBtn.addActionListener(new PyImgFindAcListener(new WuKongRun(), inOutputModel));

        pddKaiHongBaoBtn.addActionListener(new PyImgFindAcListener(new PddHongBaoOpenRun(), inOutputModel));

        jinRiTouTiaoBtn.addActionListener(new PyImgFindAcListener(new JinRiTouTiaoRun(), inOutputModel));
        kuaishouTaskCenterBtn.addActionListener(new PyImgFindAcListener(new KuaiShouTaskCenterRun(), inOutputModel));

        douYinBtn.addActionListener(new PyImgFindAcListener(DouYinTaskRun.getInstance(), inOutputModel));

        taoBaoNiuDanBtn.addActionListener(new PyImgFindAcListener(new TaoBaoRunnable(), inOutputModel));

        // 任务管理键
        taskManageBtn.addActionListener(new TaskManageBtnAcListener());
        // 返回键
        returnBtn.addActionListener(new ReturnBtnAcListener());
        // home键
        homeBtn.addActionListener(new HomeBtnAcListener());


        stopBtn.addActionListener(new StopButtonListener(isRunningSet, inOutputModel));
        kuaiShouReadBtn.addActionListener(new KuaiShouYueDuButtonListener(readButton, inOutputModel));


        readButton.addActionListener(new PyImgFindAcListener(ReadButtonRunnable.getInstance(), inOutputModel));

        kuaiShouVideoBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(new KuaiShouVideoBtnRunnable(inOutputModel)).start();
                // 触发刷视频按钮
                videoButton.doClick();
            }
        });


        AbstractButtons.setMarginInButtonJPanel(universalPanel);
        AbstractButtons.setMarginInButtonJPanel(customPanel);
        AbstractButtons.setJButtonMargin(stopBtn);
        AbstractButtons.setJButtonMargin(inputOkButton);
        AbstractButtons.setJButtonMargin(dormantOKButton);
        AbstractButtons.setJButtonMargin(waitStopBtn);

        // 不显示标题栏，最小化，关闭按钮
        // frame.setUndecorated(true);
        // 点击关闭按钮时退出程序
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // 永远置顶
        frame.setAlwaysOnTop(true);
        // 显示窗体
        frame.setVisible(true);
        // 隐藏休眠设置面板
        dormantPanel.setVisible(false);
        // 最合适的方式显示，这句要写在setVisible方法之后
        frame.pack();
    }

    public static void setIsRunning(Runnable isRunning) {
        System.out.println("正在运行的:" + isRunning);
        isRunningSet.add(isRunning);
        System.out.println("set长度:" + isRunningSet.size());
    }

    public static void main(String[] args) {
        AdbTools.getInstance();
    }
}
