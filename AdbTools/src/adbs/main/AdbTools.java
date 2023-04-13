package adbs.main;

import adbs.cmd.AdbCommands;
import adbs.main.run.BatteryLevelRun;
import adbs.main.ui.config.FlowLayouts;
import adbs.main.ui.inout.InOutputModel;
import adbs.main.ui.inout.listener.StopBtnAcListener2;
import adbs.main.ui.jpanels.adb.AdbJPanels;
import adbs.main.ui.jpanels.check.JCheckBoxControlJPanelItemListener;
import adbs.main.ui.jpanels.control.ControlJPanels;
import adbs.main.ui.jpanels.scrcpy.ScrcpyJPanels;
import adbs.main.ui.jpanels.time.TimePanels;
import adbs.main.ui.jpanels.time.listener.InputOkButtonActionListener;
import adbs.main.ui.jpanels.time.listener.MinusBtnAcListener;
import adbs.main.ui.jpanels.time.listener.PlusBtnAcListener;
import adbs.main.ui.jpanels.universal.UniversalPanels;
import adbs.model.Device;
import com.formdev.flatlaf.FlatLightLaf;
import tools.swing.button.AbstractButtons;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class AdbTools {

    static {
        // 设置外观,先设置外观，再创建UI。
        // 为了保证创建UI时，已经设置好外观，设置外观的代码最好写在静态块中，
        // 并且把这个静态块写在类定义的最前面。
        FlatLightLaf.setup();
    }

    // 窗体
    private final JFrame frame;
    // 停止按钮
    private static JButton stopBtn;
    // 当前选择的设备
    public static Device device;

    private final HashSet<Runnable> isRunningSet = new HashSet<>();
    private static final AdbTools instance = new AdbTools();
    private static JPanel contentPane;

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
        AdbJPanels adbJPanels = initAdbJPanel();

        ScrcpyJPanels scrcpyJPanels = new ScrcpyJPanels();

        // 初始化第3个面板，控制面板
        JPanel controlJPanel = initControlJPanel();

        // 初始化 时间输入面板
        TimePanels timePanels = initTimePanels();

        // 初始化通用功能面板
        UniversalPanels universalPanel = initUniversalPanel(timePanels);
        // private final JPanel otherJPanel;
        // // 输出标签

        // 初始化多选框面板
        JPanel checkJPanel = new JPanel();
        checkJPanel.setLayout(FlowLayouts.flowLayoutLeft);

        // 通用复选框
        JCheckBox generalJCheckBox = new JCheckBox("通用", true);
        // JCheckBox generalJCheckBox = new JCheckBox("", true);
        generalJCheckBox.setToolTipText("展开/折叠 通用功能");

        JCheckBox adbJCheckBox = new JCheckBox("系统", true);
        // JCheckBox adbJCheckBox = new JCheckBox("", true);
        adbJCheckBox.setToolTipText("展开/折叠 系统功能");
        JCheckBox scrcpyJCheckBox = new JCheckBox("投屏", true);
        // JCheckBox adbJCheckBox = new JCheckBox("", true);
        scrcpyJCheckBox.setToolTipText("展开/折叠 scrcpy设置功能");

        adbJCheckBox.addItemListener(new JCheckBoxControlJPanelItemListener(adbJPanels.getAdbJPanel()));
        scrcpyJCheckBox.addItemListener(new JCheckBoxControlJPanelItemListener(scrcpyJPanels.getScrcpyJPanel()));

        // 控制复选框
        JCheckBox controlJCheckBox = new JCheckBox("控制", true);
        controlJCheckBox.addItemListener(new JCheckBoxControlJPanelItemListener(controlJPanel));
        // 现在就触发
        controlJCheckBox.doClick();

        JCheckBox multitaskingJCheckBox = new JCheckBox("后台", false);
        scrcpyJCheckBox.setToolTipText("标记，勾选表示有APP在后台播放音频");
        checkJPanel.add(adbJCheckBox);
        checkJPanel.add(scrcpyJCheckBox);
        checkJPanel.add(generalJCheckBox);
        checkJPanel.add(controlJCheckBox);
        checkJPanel.add(multitaskingJCheckBox);

        // // 输出面板
        // // private final JCheckBox otherJCheckBox;
        // JPanel outputJPanel = new JPanel();
        // output = new JLabel();
        // outputJPanel.setLayout(FlowLayouts.flowLayoutLeft);
        // output.setText("统一输出");
        // outputJPanel.add(output);

        // 创建输入面板的模型
        // InOutputModel inOut = new InOutputModel(timePanels, output, stopBtn);
        // InOutputModel inOut = new InOutputModel(timePanels, universalPanel,output, stopBtn);
        InOutputModel inOut = new InOutputModel(timePanels, universalPanel, stopBtn);
        // 测试替换
        // stopBtn.addActionListener(new StopBtnAcListener2(frame, isRunningSet, inOut));
        stopBtn.addActionListener(new StopBtnAcListener2(isRunningSet, inOut));


        // 设置inputOK按钮事件监听器
        timePanels.getInputOkButton().addActionListener(new InputOkButtonActionListener(inOut));
        timePanels.getPlusBtn().addActionListener(new PlusBtnAcListener(inOut));
        timePanels.getMinusBtn().addActionListener(new MinusBtnAcListener(inOut));

        // 初始化通用面板
        // JPanel universalPanel = initUniversalPanel(inOut.getTimePanels());
        // JPanel universalPanel = initUniversalPanel(timePanels);

        // 添加 选项面板 到窗体中 第1列
        frame.add(checkJPanel);
        // 添加 adb面板 到窗体中 第2行
        frame.add(adbJPanels.getAdbJPanel());
        frame.add(scrcpyJPanels.getScrcpyJPanel());
        // 添加 通用功能面板 到第3行
        // frame.add(universalPanel);
        frame.add(universalPanel.getUniversalPanel());
        // 添加 时间输入面板 到第4行
        frame.add(timePanels.getTimePanel());
        // 添加 控制面板 到第5行
        frame.add(controlJPanel);

        // 需要先初始化通用面板 要放在 initUniversalPanel(inputPanels, inOut);之后
        // generalJCheckBox.addItemListener(new JCheckBoxControlJPanelItemListener(frame, universalPanel));
        // generalJCheckBox.addItemListener(new JCheckBoxControlJPanelItemListener(universalPanel));
        generalJCheckBox.addItemListener(new JCheckBoxControlJPanelItemListener(universalPanel.getUniversalPanel()));

        // 添加选项到多选面板
        // newButtonJPanel(frame, checkJPanel, otherJPanel);
        // frame.add(checkJPanel);
        // newButtonJPanel(frame, checkJPanel);

        // 添加多选框面板到第3行
        // frame.add(checkJPanel, 2);
        // AbstractButtons.setMarginInButtonJPanel(checkJPanel, 10);
        AbstractButtons.setMarginInButtonJPanel(checkJPanel, -1);
        AbstractButtons.setMarginInButtonJPanel(checkJPanel, -1);
        // 添加输出面包到最后一行
        // frame.add(outputJPanel);

        // 永远置顶
        frame.setAlwaysOnTop(true);
        // 设置关闭按钮功能
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // 调整窗体到最佳大小
        frame.pack();
        // 显示窗体
        frame.setVisible(true);
    }

    // private JPanel initUniversalPanel(InOutputModel inout2) {
    private UniversalPanels initUniversalPanel(TimePanels timePanels) {
        // 创建通用面板，并添加到窗体中
        UniversalPanels universalPanels = new UniversalPanels(timePanels);
        // return universalPanels.getUniversalPanel();
        return universalPanels;
    }

    /**
     * 初始化第3个面板，控制面板
     */
    private JPanel initControlJPanel() {
        final JPanel controlJPanel;
        ControlJPanels controlJPanels = new ControlJPanels();
        controlJPanel = controlJPanels.getControlJPanel();
        return controlJPanel;
    }

    private TimePanels initTimePanels() {
        return new TimePanels();
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
                    System.out.println("双击主面板");
                    frame.pack();
                }
            }
        });

        // 设置窗体的内容面板
        frame.setContentPane(contentPane);
        // 窗体使用箱型布局,垂直排列
        frame.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
    }

    /**
     * 初始化设备面板
     */
    private void initDevicesPanel2() {
        // 设备序列号列表
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
                idList.add(device.getSimpleId());
                // 把id和设备作为键值对放入map中
                simpleId_Device_map.put(device.getSimpleId(), device);
            }
        }
        // 打印id列表
        System.out.println("idList = " + idList);

        // showConfirmDialog();
        Component parentComponent = frame;
        // Component parentComponent = null;
        String message = "请选择要操作的adb设备：";
        String title = "选择Adb设备";
        // int optionType = JOptionPane.YES_NO_CANCEL_OPTION;
        int optionType = JOptionPane.YES_OPTION;
        int messageType = JOptionPane.PLAIN_MESSAGE;
        Icon icon = null;
        // String[] options = {"HonorWiFi", "RedmiWiFi"};
        // 弹出对话框的选项列表
        String[] options = idList.toArray(new String[idList.size()]);
        int initialValue = 0;
        // 弹出选项框，获取用户选择的按钮编号
        int dialogReturn = JOptionPane.showOptionDialog(parentComponent, message, title, optionType, messageType, icon, options, initialValue);
        // 如果用户选择了某个按钮
        if (dialogReturn >= 0) {
            // 获取编号对应的字符串
            String adbSelected = options[dialogReturn];
            // 把这个编号对应的字符串设置到窗体的标题
            frame.setTitle(adbSelected);
            // frame.setTitle(Device.map.get(options[dialogReturn]));
            // System.out.println("simpleId_Device_map.get(options[dialogReturn]) = " + simpleId_Device_map.get(options[dialogReturn]));
            device = simpleId_Device_map.get(adbSelected);
            // CmdRun.run("title " + adbSelected);
        } else {
            System.out.println("退出程序");
            System.exit(-1);
        }
    }

    /**
     * 初始化adb面板
     */
    private AdbJPanels initAdbJPanel() {
        AdbJPanels adbJPanels = new AdbJPanels();
        stopBtn = adbJPanels.getStopBtn();
        return adbJPanels;
    }

    public static AdbTools getInstance() {
        return instance;
    }

    public static JButton getStopBtn() {
        return stopBtn;
    }

    public static JPanel getContentPane() {
        return contentPane;
    }

    public void setIsRunning(Runnable isRunning) {
        // System.out.println("正在运行的:" + isRunning);
        isRunningSet.add(isRunning);
        // System.out.println("set长度:" + isRunningSet.size());
    }

    public static void main(String[] args) {
        AdbTools.getInstance();
        // 启动电池监测线程
        new Thread(new BatteryLevelRun()).start();
    }
}
