package adbs.main;

import adbs.cmd.AdbCommands;
import adbs.main.run.ActAutoRun;
import adbs.main.run.BatteryLevelRun2;
import adbs.main.run.IsTest;
import adbs.main.run.model.FrameTitle;
import adbs.main.ui.jpanels.act.ActSignedInPanels;
import adbs.main.ui.jpanels.adb.AdbJPanels;
import adbs.main.ui.jpanels.app.AppSignedInPanels;
import adbs.main.ui.jpanels.check.CheckJPanels;
import adbs.main.ui.jpanels.scrcpy.ScrcpyJPanels;
import adbs.main.ui.jpanels.time.TimePanels;
import adbs.main.ui.jpanels.timeauto2.TimingPanels2;
import adbs.main.ui.jpanels.tools.ToolsJPanels;
import adbs.main.ui.jpanels.universal.UniversalPanels;
import adbs.model.Device;
import adbs.tools.thread.ThreadSleep;
import com.formdev.flatlaf.FlatLightLaf;
import tools.copy.SystemClipboard;
import tools.swing.button.AbstractButtons;
import tools.swing.dialog.DialogFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class AdbTools {

    static {
        // 设置外观,先设置外观，再创建UI。
        // 为了保证创建UI时，已经设置好外观，设置外观的代码最好写在静态块中，
        // 并且把这个静态块写在类定义的最前面。
        FlatLightLaf.setup();
    }

    // 窗体
    private final JFrame frame;
    // 时间输入面板
    private final TimePanels timePanels;
    // adb面板
    private final AdbJPanels adbJPanels;

    // 通用面板
    private final UniversalPanels universalPanels;
    // scrcpy面板
    private final ScrcpyJPanels scrcpyJPanels;
    private final CheckJPanels checkJPanels;
    private final TimingPanels2 timingPanels2;
    private final AppSignedInPanels appSignedInPanels;
    //    private final AutoPanels autoPanels;
    private final ToolsJPanels toolsJPanels;

    // 当前选择的设备
    // public static Device device;
    public Device device;

    private final HashSet<Runnable> isRunningSet = new HashSet<>();
    private static final AdbTools instance = new AdbTools();
    private JPanel contentPane;

    private AdbTools() {
        // 创建窗体
        frame = new JFrame();
        // 禁用最大化窗口
        frame.setResizable(false);
        // 设置窗体内容面板
        contentPaneSetting();
        // 初始化第0个面板，初始化设备面板
        initDevicesPanel2();

        // 初始化第1个面板,adb面板
        adbJPanels = new AdbJPanels();
        // 投屏面板
        scrcpyJPanels = new ScrcpyJPanels();

        // 初始化第3个面板，控制面板
        // timingPanels = new TimingPanels();
        timingPanels2 = new TimingPanels2();
        // JPanel controlJPanel = controlJPanels.getControlJPanel();

        // 初始化 时间输入面板
        // timePanels = initTimePanels();
        timePanels = new TimePanels();

        // 初始化通用功能面板
        universalPanels = new UniversalPanels(timePanels);
        // 初始化工具面板
        toolsJPanels = new ToolsJPanels();

        //        autoPanels = new AutoPanels();

        //
        appSignedInPanels = new AppSignedInPanels();

        ActSignedInPanels actSignedInPanels  = new ActSignedInPanels();

        // JPanel checkJPanel = initCheckJPanel(timingPanels2, toolsJPanels, autoPanels, universalPanels, adbJPanels, scrcpyJPanels);
        //        checkJPanels = new CheckJPanels(timingPanels2, toolsJPanels, autoPanels, universalPanels, adbJPanels, scrcpyJPanels, appPanels);
        checkJPanels = new CheckJPanels(timingPanels2, toolsJPanels, universalPanels, adbJPanels, scrcpyJPanels, appSignedInPanels, actSignedInPanels);




        JPanel checkJPanel = checkJPanels.getCheckJPanel();
        // 添加 选项面板 到窗体中 第1列
        frame.add(checkJPanel);
        // 添加 adb面板 到窗体中 第2行
        frame.add(adbJPanels.getAdbJPanel());
        frame.add(scrcpyJPanels.getScrcpyJPanel());
        // 添加 通用功能面板 到第3行
        // frame.add(universalPanel);
        frame.add(universalPanels.getUniversalPanel());
        // 添加 时间输入面板 到第4行
        frame.add(timePanels.getTimePanel());
        // 添加 控制面板 到第5行
        // frame.add(timingPanels.getTimingPanel());
        frame.add(timingPanels2.getTimingPanels2());

        frame.add(toolsJPanels.getToolsJPanel());
        //        frame.add(autoPanels.getAutoJPanel());
        frame.add(appSignedInPanels.getAppPanel());

        frame.add(actSignedInPanels.getTopJPanel());

        // 添加多选框面板到第3行
        AbstractButtons.setMargin_2_InButtonJPanel(checkJPanel, -1);
        AbstractButtons.setMargin_2_InButtonJPanel(checkJPanel, -1);
        // 添加输出面包到最后一行
        // frame.add(outputJPanel);

        frameSettings();
        // timingPanels2.getjComboBox().setSelectedIndex(0);

    }

    /**
     * 窗体设置，最小化，关闭按钮设置
     */
    private void frameSettings() {
        // 取消默认关闭操作
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        // 关闭窗体之前提醒
        // 自己实现windowClosing
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // int result = JOptionPane.showConfirmDialog(contentPane, "确认关闭");
                int result = JOptionPane.showConfirmDialog(contentPane, "", "关闭窗体", JOptionPane.YES_NO_OPTION);

                switch (result) {
                    case JOptionPane.OK_OPTION:
                        super.windowClosing(e);
                        // adbJPanels.getStopBtn().doClick();
                        universalPanels.getBtnStop().doClick();
                        scrcpyJPanels.getBtnKillScrcpy().doClick();
                        System.out.println("窗体正在关闭。。。。。。。。。。。。");
                        // ThreadSleep.seconds(5);
                        System.exit(0);
                        break;
                    // default:
                    //     frame.pack();
                    //     break;
                    default:
                        break;
                    // throw new IllegalStateException("Unexpected value: " + result);
                }
            }
        });

        // 添加窗口状态监听器
        frame.addWindowStateListener(new WindowStateListener() {
            @Override
            public void windowStateChanged(WindowEvent e) {
                if (e.getNewState() == Frame.ICONIFIED) {
                    // 检查窗口是否被最小化
                    String message = "最小化" + device.getName() + "?";
                    //                     String title = "确认";
                    String title = "";
                    int result = JOptionPane.showConfirmDialog(frame, message, title, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (result != JOptionPane.YES_OPTION) {
                        // 如果用户点击“否”，则取消最小化操作并恢复原窗口位置
                        frame.setExtendedState(Frame.NORMAL);
                    }
                }
            }
        });


        //永远置顶
        //        frame.setAlwaysOnTop(true);

        // 调整窗体到最佳大小
        frame.pack();
        // 显示窗体
        frame.setVisible(true);
    }


    /**
     * 窗体内容面板设置。
     */
    private void contentPaneSetting() {
        // 创建窗体的内容面板
        contentPane = new JPanel();

        // 内容面板监听鼠标右键双击事件
        contentPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON3) {
                    System.out.println("双击主面板,调用一次frame.pack()");
                    frame.pack();
                }
            }
        });

        // 设置窗体的内容面板
        frame.setContentPane(contentPane);
        // 窗体使用箱型布局,垂直排列,此布局不支持换行显示，超出容器的组件将会被隐藏
        frame.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
    }

    /**
     * 初始化设备面板
     */
    private void initDevicesPanel2() {
        // 保存设备序列号的列表
        ArrayList<String> idList = new ArrayList<>();
        // 设备别名
        LinkedHashMap<String, Device> simpleId_Device_map = new LinkedHashMap<>();
        // 执行adb命令
        String devicesListStr = AdbCommands.runAbdCmd("adb devices -l");

        // 分析adb devices -l命令结果
        Scanner scanner = new Scanner(devicesListStr);
        String line;
        while (scanner.hasNextLine()) {
            // 逐行读入
            line = scanner.nextLine();
            // System.out.println("line = " + line);
            // List of devices attached表示没有设备，
            // 如果是设备输出信息
            if (!line.equals("List of devices attached") && !"".equals(line)) {
                // 按两个或者更多的空格符作为分界 来分割字符串
                String[] deviceStrs = line.split("[ ]{2,}");
                // System.out.println("ID = " + deviceStrs[0]);
                // System.out.println("dir = " + deviceStrs[1]);
                // 创建设备对象
                // 分割得到的第1段是设备id，第2段是设备的描述信息
                Device device = new Device(deviceStrs[0], deviceStrs[1]);
                // 添加设备的id到列表中
                idList.add(device.getName());
                // 把id和设备作为键值对放入map中
                simpleId_Device_map.put(device.getName(), device);
            }
        }
        scanner.close();
        int size = simpleId_Device_map.size();
        if (size == 1) {
            device = simpleId_Device_map.get(idList.get(0));
            System.out.println("device = " + device);
            // return device;
            //            frame.setTitle(device.getName());

            FrameTitle frameTitle = FrameTitle.getFrameTitle();
            frameTitle.setDeviceName(device.getName());
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    frame.setTitle(frameTitle.toString());
                }
            });

            return;
        }

        /**
         * 排序设备名
         */
        sortIdList(idList, simpleId_Device_map);


        // 打印id列表
        System.out.println("idList = " + idList);

        // showConfirmDialog();
        // Component parentComponent = null;
        Component parentComponent = frame;
        String message = "请选择要操作的adb设备：";
        String title = "选择Adb设备";
        // int optionType = JOptionPane.YES_NO_CANCEL_OPTION;
        int optionType = JOptionPane.YES_OPTION;
        int messageType = JOptionPane.PLAIN_MESSAGE;
        Icon icon = null;
        int initialValue = 0;
        // String[] options = {"HonorWiFi", "RedmiWiFi"};
        // 弹出对话框的选项列表
        String[] options = idList.toArray(new String[idList.size()]);
        // 弹出选项框，获取用户选择的按钮编号
        int dialogReturn = JOptionPane.showOptionDialog(parentComponent, message, title, optionType, messageType, icon, options, initialValue);
        // 如果用户选择了某个按钮
        if (dialogReturn >= 0) {
            // 获取编号对应的字符串
            String devieceSelected = options[dialogReturn];
            System.out.println("你选择了:" + devieceSelected);
            SystemClipboard.setSysClipboardText(devieceSelected);
            // 把这个编号对应的字符串设置到窗体的标题
            //            frame.setTitle(devieceSelected);

            //            FrameTitle.getFrameTitle();
            FrameTitle frameTitle = FrameTitle.getFrameTitle();
            frameTitle.setDeviceName(devieceSelected);

            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    frame.setTitle(frameTitle.toString());
                }
            });


            device = simpleId_Device_map.get(devieceSelected);
        } else {
            System.out.println("退出程序");
            System.exit(-1);
        }
    }

    private void sortIdList(ArrayList<String> idList, LinkedHashMap<String, Device> simpleId_Device_map) {
        Collections.sort(idList, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                Device device1 = simpleId_Device_map.get(o1);
                Device device2 = simpleId_Device_map.get(o2);
                if (device1.getPriority() > device2.getPriority()) {
                    return -1;
                } else if (device1.getPriority() < device2.getPriority()) {
                    return 1;
                }
                return 0;
            }
        });
    }

    public static AdbTools getInstance() {
        return instance;
    }

    public TimePanels getTimePanels() {
        return timePanels;
    }

    public TimingPanels2 getTimingPanels2() {
        return timingPanels2;
    }

    public AdbJPanels getAdbJPanels() {
        return adbJPanels;
    }


    public UniversalPanels getUniversalPanels() {
        return universalPanels;
    }


    public CheckJPanels getCheckJPanels() {
        return checkJPanels;
    }

    public AppSignedInPanels getAppPanels() {
        return appSignedInPanels;
    }

    public ToolsJPanels getToolsJPanels() {
        return toolsJPanels;
    }

    public ScrcpyJPanels getScrcpyJPanels() {
        return scrcpyJPanels;
    }

    public Device getDevice() {
        return device;
    }

    public JFrame getFrame() {
        return frame;
    }


    public HashSet<Runnable> getIsRunningSet() {
        return isRunningSet;
    }

    public JPanel getContentPane() {
        return contentPane;
    }

    public void addRunningInSet(Runnable isRunning) {
        // System.out.println("正在运行的:" + isRunning);
        isRunningSet.add(isRunning);
        // System.out.println("set长度:" + isRunningSet.size());
    }


    /**
     * 在AdbTools程序中弹出有一个确认按钮的对话框
     *
     * @param title            对话框的标题
     * @param message          消息
     * @param actionListenerOk 点击确认按钮需要执行的操作
     */
    public void showDialogOk(String title, String message, ActionListener actionListenerOk) {
        //        JFrame frame = AdbTools.getInstance().getFrame();
        DialogFactory.showDialogOk(frame, title, message, actionListenerOk);
    }

    /**
     * 在AdbTools程序中弹出有一个确认按钮的对话框
     *
     * @param message          消息
     * @param actionListenerOk 点击确认按钮需要执行的操作
     */
    public void showDialogOk(String message, ActionListener actionListenerOk) {
        //        JFrame frame = AdbTools.getInstance().getFrame();
//        DialogFactory.showDialogOk(frame, "", message, actionListenerOk);
        DialogFactory.showDialogOk(frame, device.getName(), message, actionListenerOk);
    }

    public void showDialogOkClose(String message, ActionListener actionListenerOk, WindowAdapter windowAdapter) {
        //        JFrame frame = AdbTools.getInstance().getFrame();

        //        DialogFactory.showDialogOkClose(frame, "", message, actionListenerOk, windowAdapter);
        DialogFactory.showDialogOkClose(frame, device.getName(), message, actionListenerOk, windowAdapter);
    }

    public void showDialogOkCancel(String message, ActionListener actionListenerOk, ActionListener actionListenerCancel) {
        //        JFrame frame = AdbTools.getInstance().getFrame();
        //        DialogFactory.showDialogOkCancel(frame, "", message, actionListenerOk,actionListenerCancel);
        DialogFactory.showDialogOkCancel(frame, device.getName(), message, actionListenerOk, actionListenerCancel);
    }


    public static void main(String[] args) {
        AdbTools instance = AdbTools.getInstance();
        instance.timingPanels2.getjComboBox().setSelectedIndex(0);
        //        instance.autoPanels.defaultSelected();

        // timingPanels2.getjComboBox().setSelectedIndex(0);
        if (!IsTest.isTest()) {
            // 在打开应用的时候，就触发投屏按钮
            instance.getScrcpyJPanels().getBtnOpenScrcpy().doClick();
            ThreadSleep.seconds(5);
            //            new Thread(new ForegroundAppRun()).start();
            // // 启动电池监测线程
            new Thread(new BatteryLevelRun2()).start();
            new Thread(new ActAutoRun()).start();
        }

    }
}
