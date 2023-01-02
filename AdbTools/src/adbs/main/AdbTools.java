package adbs.main;

import adbs.cmd.AdbCommands;
import adbs.main.run.PythonCloseableRun;
import adbs.main.ui.config.FlowLayouts;
import adbs.main.ui.inout.InOutputModel;
import adbs.main.ui.inout.listener.StopBtnAcListener2;
import adbs.main.ui.jpanels.adb.AdbJPanels;
import adbs.main.ui.jpanels.check.JCheckBoxControlJPanelItemListener;
import adbs.main.ui.jpanels.control.ControlJPanels;
import adbs.main.ui.jpanels.input.InputPanels;
import adbs.main.ui.jpanels.input.listener.InputOkButtonActionListener;
import adbs.main.ui.jpanels.input.listener.MinusBtnAcListener;
import adbs.main.ui.jpanels.input.listener.PlusBtnAcListener;
import adbs.main.ui.jpanels.universal.UniversalPanels;
import adbs.main.ui.jpanels.universal.listener.ReadButtonRunnable;
import adbs.model.Device;
import com.formdev.flatlaf.FlatLightLaf;
import config.AdbToolsProperties;
import tools.config.properties.PropertiesTools;
import tools.swing.button.AbstractButtons;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
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
    // 配置文件路径
    private static final String dirPath = "AdbToolsPythons\\";
    // 配置文件
    private final PropertiesTools propertiesTools = AdbToolsProperties.propertiesTools;

    // 阅读按钮
    private JButton readButton;
    // private final JPanel otherJPanel;
    // 输出标签
    private final JLabel output;
    // 当前选择的设备
    public static Device device;

    private final HashSet<Runnable> isRunningSet = new HashSet<>();
    private static final AdbTools instance = new AdbTools();

    private AdbTools() {
        // 创建窗体
        frame = new JFrame();
        // 设置窗体内容面板
        contentPaneSetting();

        // 初始化第0个面板，初始化设备面板
        // initDevicesPanel();
        initDevicesPanel2();
        // 初始化第1个面板,adb面板
        AdbJPanels adbJPanels = initAdbJPanel();
        // 初始化第3个面板，控制面板
        JPanel controlJPanel = initControlJPanel();

        // 初始化输入面板
        InputPanels inputPanels = initInputPanels();


        // 初始化多选框面板
        JPanel checkJPanel = new JPanel();
        checkJPanel.setLayout(FlowLayouts.flowLayoutLeft);

        // // 选项面板
        // frame.add(checkJPanel);
        // // adb面板
        // frame.add(adbJPanels.getAdbJPanel());
        // // 时间输入面板
        // frame.add(inputPanels.getInputPanel());
        // // 控制面板
        // frame.add(controlJPanel);

        // 通用复选框
        // JCheckBox generalJCheckBox = new JCheckBox("通用", true);
        JCheckBox generalJCheckBox = new JCheckBox("", true);
        generalJCheckBox.setToolTipText("展开/折叠 通用功能");
        // generalJCheckBox.setMargin();
        // JCheckBox adbJCheckBox = new JCheckBox("系统", true);
        JCheckBox adbJCheckBox = new JCheckBox("", true);
        adbJCheckBox.setToolTipText("展开/折叠 系统功能");
        // AbstractButtons.setMarginInButtonJPanel(checkJPanel);
        adbJCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                boolean visible = adbJPanels.getAdbJPanel().isVisible();
                adbJPanels.getAdbJPanel().setVisible(!visible);
                frame.pack();
            }
        });
        // 控制复选框
        JCheckBox controlJCheckBox = new JCheckBox("控制", true);
        // controlJCheckBox.addItemListener(new JCheckBoxControlJPanelItemListener(frame, controlJPanel));
        controlJCheckBox.addItemListener(new JCheckBoxControlJPanelItemListener(controlJPanel));
        // 现在就触发
        controlJCheckBox.doClick();

        // checkJPanel.add(generalJCheckBox, 0);
        checkJPanel.add(adbJCheckBox);
        checkJPanel.add(generalJCheckBox);
        checkJPanel.add(controlJCheckBox);

        // 输出面板
        // private final JCheckBox otherJCheckBox;
        JPanel outputJPanel = new JPanel();
        output = new JLabel();
        outputJPanel.setLayout(FlowLayouts.flowLayoutLeft);
        output.setText("统一输出");
        outputJPanel.add(output);

        // 创建输入面板的模型
        InOutputModel inOut = new InOutputModel(inputPanels, output, stopBtn);
        // 测试替换
        // stopBtn.addActionListener(new StopBtnAcListener2(frame, isRunningSet, inOut));
        stopBtn.addActionListener(new StopBtnAcListener2(isRunningSet, inOut));
        // 设置inputOK按钮事件监听器
        inputPanels.getInputOkButton().addActionListener(new InputOkButtonActionListener(inOut));
        inputPanels.getPlusBtn().addActionListener(new PlusBtnAcListener(inOut));
        inputPanels.getMinusBtn().addActionListener(new MinusBtnAcListener(inOut));

        // 初始化通用面板
        JPanel universalPanel = initUniversalPanel(inOut);

        // 选项面板
        frame.add(checkJPanel);
        // adb面板
        frame.add(adbJPanels.getAdbJPanel());
        // 添加通用功能面板
        frame.add(universalPanel);
        // 时间输入面板
        frame.add(inputPanels.getInputPanel());
        // 控制面板
        frame.add(controlJPanel);

        // 需要先初始化通用面板 要放在 initUniversalPanel(inputPanels, inOut);之后
        // generalJCheckBox.addItemListener(new JCheckBoxControlJPanelItemListener(frame, universalPanel));
        generalJCheckBox.addItemListener(new JCheckBoxControlJPanelItemListener(universalPanel));

        // 添加选项到多选面板
        // newButtonJPanel(frame, checkJPanel, otherJPanel);
        // frame.add(checkJPanel);
        newButtonJPanel(frame, checkJPanel);

        // 添加多选框面板到第3行
        // frame.add(checkJPanel, 2);
        AbstractButtons.setMarginInButtonJPanel(checkJPanel, 10);
        AbstractButtons.setMarginInButtonJPanel(checkJPanel, -1);
        // 添加输出面包到最后一行
        frame.add(outputJPanel);

        // 永远置顶
        frame.setAlwaysOnTop(true);
        // 设置关闭按钮功能
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // 调整窗体到最佳大小
        frame.pack();
        // 显示窗体
        frame.setVisible(true);
    }

    private JPanel initUniversalPanel(InOutputModel inout2) {
        // 创建通用面板，并添加到窗体中
        // UniversalPanels universalPanels = new UniversalPanels(frame, inout2);
        UniversalPanels universalPanels = new UniversalPanels(inout2);
        readButton = universalPanels.getReadButton();
        return universalPanels.getUniversalPanel();
    }

    /**
     * 初始化第3个面板，控制面板
     */
    private JPanel initControlJPanel() {
        final JPanel controlJPanel;
        ControlJPanels controlJPanels = new ControlJPanels();
        controlJPanel = controlJPanels.getControlJPanel();
        // frame.add(controlJPanel);
        return controlJPanel;
    }

    private InputPanels initInputPanels() {
        // JPanel inputPanel = inputPanels.getInputPanel();
        // inputPanel.setLayout(FlowLayouts.flowLayoutLeft);
        // inputPanels.getTimeRadioPanel().setLayout(FlowLayouts.flowLayoutLeft);
        // frame.add(inputPanel);
        return new InputPanels();
    }

    /**
     * 窗体内容面板设置。
     */
    private void contentPaneSetting() {
        // 创建窗体的内容面板
        JPanel contentPane = new JPanel();

        // 内容面板监听鼠标右键双击事件
        contentPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON3) {
                    System.out.println("双击主面板");
                    frame.pack();
                }
                // else if (e.getClickCount() == 1 && e.getButton() == MouseEvent.BUTTON1) {
                //     System.out.println("单击主面板上的按钮");
                //     frame.pack();
                // }
            }
        });

        // 设置窗体的内容面板
        frame.setContentPane(contentPane);
        // 窗体使用箱型布局,垂直排列
        frame.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
    }

    // /**
    //  * 初始化设备面板
    //  */
    // private void initDevicesPanel() {
    //     final JPanel devicesPanel;
    //     // 创建设备面板
    //     devicesPanel = new AdbDi(frame).createDevicesPanel();
    //     // 设备面板设置 流式布局 左对齐
    //     devicesPanel.setLayout(FlowLayouts.flowLayoutLeft);
    //     // 添加到窗体中
    //     frame.add(devicesPanel);
    // }

    /**
     * 初始化设备面板
     */
    private void initDevicesPanel2() {
        ArrayList<String> idList = new ArrayList<>();
        LinkedHashMap<String, Device> simpleId_Device_map = new LinkedHashMap<>();
        String devicesListStr = AdbCommands.runAbdCmd("adb devices -l");
        // 分析adb devices -l命令结果
        Scanner scanner = new Scanner(devicesListStr);

        String line;
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            // System.out.println("line = " + line);
            // List of devices attached表示没有设备，
            // 如果是设备输出信息
            if (!line.equals("List of devices attached") && !"".equals(line)) {
                // 按两个或者更多的空格符作为分界 来分割字符串
                String[] deviceStrs = line.split("[ ]{2,}");
                // System.out.println("ID = " + deviceStrs[0]);
                // System.out.println("dir = " + deviceStrs[1]);
                // 分割得到的第1段是设备id，第2段是设备的描述信息
                Device device = new Device(deviceStrs[0], deviceStrs[1]);
                idList.add(device.getSimpleId());
                simpleId_Device_map.put(device.getSimpleId(), device);
            }
        }

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
        String[] options = idList.toArray(new String[idList.size()]);
        int initialValue = 0;
        // 弹出选项框
        int dialogReturn = JOptionPane.showOptionDialog(parentComponent, message, title, optionType, messageType, icon, options, initialValue);
        if (dialogReturn >= 0) {
            // 设置到标题
            String adbSelected = options[dialogReturn];
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

    // /**
    //  * 初始化adb面板
    //  */
    // private void initAdbJPanel() {
    //     AdbJPanels adbJPanels = new AdbJPanels();
    //     stopBtn = adbJPanels.getStopBtn();
    //     frame.add(adbJPanels.getAdbJPanel());
    // }

    /**
     * 初始化adb面板
     */
    private AdbJPanels initAdbJPanel() {
        AdbJPanels adbJPanels = new AdbJPanels();
        stopBtn = adbJPanels.getStopBtn();
        // frame.add(adbJPanels.getAdbJPanel());
        return adbJPanels;
    }

    public static AdbTools getInstance() {
        return instance;
    }

    public void setIsRunning(Runnable isRunning) {
        // System.out.println("正在运行的:" + isRunning);
        isRunningSet.add(isRunning);
        // System.out.println("set长度:" + isRunningSet.size());
    }

    public static void main(String[] args) {
        AdbTools.getInstance();
    }

    /**
     * 根据体魄目录生成按钮
     *
     * @param frame       窗体
     * @param checkJPanel 复选框面板
     */
    private void newButtonJPanel(JFrame frame, JPanel checkJPanel) {
        File dir = new File(dirPath);
        System.out.println("AdbToolsPythonImg = " + dir.getAbsolutePath());
        File[] dirList = dir.listFiles(File::isDirectory);

        if (dirList != null) {
            for (File dirDeep1 : dirList) {
                if (dirDeep1.isDirectory()) {
                    // 列出子目录
                    File[] dirDeep1List = dirDeep1.listFiles(File::isDirectory);
                    // 如果一级子目录存在子目录
                    if (dirDeep1List != null) {
                        if (dirDeep1List.length > 0) {
                            // 获取一级子目录的名称
                            String name = dirDeep1.getName();
                            // System.out.println("name = " + name);
                            // 创建面板
                            JPanel jPanel = new JPanel();
                            // propertiesTools.getProperty(name);
                            // 给面板标题边框，使用一级目录名作为标题
                            // jPanel.setBorder(new TitledBorder(name));
                            String chName = propertiesTools.getProperty(name);
                            jPanel.setBorder(new TitledBorder(chName));

                            FlowLayout layout = new FlowLayout(FlowLayout.LEFT, 0, 0);
                            // 设置布局管理器
                            jPanel.setLayout(layout);
                            // 创建复选框，默认勾选
                            // JCheckBox checkBox = new JCheckBox(name, true);
                            JCheckBox checkBox = new JCheckBox(chName, true);
                            checkBox.doClick();
                            checkJPanel.add(checkBox);
                            // 监听
                            // checkBox.addItemListener(new JCheckBoxControlJPanelItemListener(frame, jPanel));
                            checkBox.addItemListener(new JCheckBoxControlJPanelItemListener(jPanel));

                            // 遍历二级子目录
                            for (File dirDeep2 : dirDeep1List) {
                                // 获取二级目录名
                                String name1 = dirDeep2.getName();
                                if (!name1.contains("test") && !name1.contains("demo")) {
                                    String chName1 = propertiesTools.getProperty(name1);
                                    // String chName1 = getCHName(name1);
                                    // 创建按钮
                                    // JButton button = new JButton(name1);
                                    JButton button = new JButton(chName1);
                                    button.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            // 拼接内容Python文件路径
                                            // String pythonFile = dirPath + name + "\\" + name1 + "\\" + "_" + Device.getBrand() + ".py";
                                            String pythonFile = dirPath + name + "\\" + name1 + "\\" + "_" + device.getBrand2() + ".py";
                                            System.out.println("pythonFile = " + pythonFile);
                                            // new Thread(new PythonCloseableRun(name1, pythonFile, output)).start();
                                            // new Thread(new PythonCloseableRun(chName1, pythonFile, output)).start();
                                            if (name.equals("KuaiShou") && name1.equals("YueDu")) {
                                                System.out.println("name = " + name);
                                                System.out.println("name1 = " + name1);
                                                // ReadButtonRunnable.getInstance()
                                                ReadButtonRunnable readButtonRunnable = ReadButtonRunnable.getInstance();
                                                new Thread(readButtonRunnable).start();
                                                new Thread(new PythonCloseableRun(chName1, pythonFile, output, readButtonRunnable, readButton)).start();
                                                // new Thread(new PythonCloseableRun(chName1, pythonFile, output, readButtonRunnable, )).start();
                                                // new
                                            } else {
                                                new Thread(new PythonCloseableRun(name1, pythonFile, output)).start();
                                            }
                                        }
                                    });
                                    // 设置按钮的内切为1
                                    AbstractButtons.setJButtonMargin(button, 1);
                                    jPanel.add(button);
                                }
                            }
                            // 隐藏面板
                            jPanel.setVisible(false);
                            frame.add(jPanel);
                        }
                    }
                }
            }
        }
    }
}
