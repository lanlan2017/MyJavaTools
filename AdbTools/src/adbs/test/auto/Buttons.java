package adbs.test.auto;

import adbs.action.listener.*;
import adbs.action.model.InOutputModel;
import adbs.action.model.InputPanelModel;
import adbs.action.runnable.ReadButtonRunnable;
import adbs.test.auto.ui.*;
import adbs.test.auto.ui.config.FlowLayouts;
import tools.swing.button.AbstractButtons;
import adbs.test.AdbDi;
import adbs.test.Device;
import adbs.test.auto.listener.JChcekBoxControl2JPanelItemListener;
import adbs.test.auto.listener.JCheckBoxControlJPanelItemListener;
import adbs.test.auto.listener.StopBtnAcListener2;
import adbs.test.auto.run.PythonCloseableRun;
import com.formdev.flatlaf.FlatLightLaf;
import tools.config.properties.PropertiesTools;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.HashSet;

public class Buttons {

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
    private static final String dirPath = "AdbToolsPythons\\";

    private PropertiesTools propertiesTools = new PropertiesTools("AdbTools.properties");

    private JPanel inputPanel;

    private JPanel controlJPanel;
    private JPanel universalPanel;
    private JButton readButton;
    private final JPanel otherJPanel;
    /**
     * 多选按钮面包
     */
    private final JPanel checkJPanel;
    private final JCheckBox otherJCheckBox;
    private final JPanel outputJPanel;
    private final JCheckBox generalJCheckBox;
    private JLabel output;

    private final HashSet<Runnable> isRunningSet = new HashSet<>();
    private static final Buttons instance = new Buttons();

    private Buttons() {
        // 创建窗体
        frame = new JFrame();
        // 设置窗体内容面板
        contentPaneSetting();

        // 初始化第0个面板
        initZeroPanel();
        // 初始化第1个面板
        initFirstPanel();
        // 初始化第2个面板
        initSeconPanel();
        // 初始化第3个面板
        controlJPanel = initControlJPanel();

        // 初始化输入面板
        InputPanels inputPanels = initInputPanels();

        // 其他按钮面板
        otherJPanel = new JPanel();

        // 创建多选按钮面板
        checkJPanel = new JPanel();
        checkJPanel.setLayout(FlowLayouts.flowLayoutLeft);

        // 其他复选框
        otherJCheckBox = new JCheckBox("其他", true);
        // 其他单选框 可以 控制其他面板
        otherJCheckBox.addItemListener(new JCheckBoxControlJPanelItemListener(frame, otherJPanel));
        otherJCheckBox.doClick();

        // 通用复选框
        generalJCheckBox = new JCheckBox("通用", true);
        //
        // 控制单选框
        JCheckBox controlJCheckBox = new JCheckBox("控制", true);
        controlJCheckBox.addItemListener(new JCheckBoxControlJPanelItemListener(frame, controlJPanel));
        controlJCheckBox.doClick();


        // 输出面板
        outputJPanel = new JPanel();
        output = new JLabel();
        outputJPanel.setLayout(FlowLayouts.flowLayoutLeft);
        output.setText("统一输出");
        outputJPanel.add(output);

        // // 创建输入面板的模型
        // inputPanelModel = new InputPanelModel(inputPanels);
        InOutputModel inout2 = new InOutputModel(inputPanels, output, stopBtn);
        // 测试替换
        stopBtn.addActionListener(new StopBtnAcListener2(frame, isRunningSet, inout2));
        // 设置inputOK按钮事件监听器
        inputPanels.getInputOkButton().addActionListener(new InputOkButtonActionListener(inout2));
        // 初始化通用面板
        universalPanel = initUniversalPanel(inputPanels, inout2);
        // 需要先初始化通用面板 要放在 initUniversalPanel(inputPanels, inout2);之后
        generalJCheckBox.addItemListener(new JCheckBoxControlJPanelItemListener(frame, universalPanel));

        newButtonJPanel(frame, checkJPanel, otherJPanel);

        checkJPanel.add(otherJCheckBox);
        checkJPanel.add(controlJCheckBox);
        checkJPanel.add(generalJCheckBox, 0);
        // 插入到第2行
        // frame.add(checkJPanel, 2);
        frame.add(checkJPanel, 3);
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

    private JPanel initUniversalPanel(InputPanels inputPanels, InOutputModel inout2) {
        UniversalPanels universalPanels = new UniversalPanels(frame, inputPanels, inout2);
        return universalPanels.getUniversalPanel();
    }

    /**
     * 初始化第3个面板，控制面板
     */
    private JPanel initControlJPanel() {
        final JPanel controlJPanel;
        ControlJPanels controlJPanels = new ControlJPanels();
        controlJPanel = controlJPanels.getControlJPanel();
        frame.add(controlJPanel);
        return controlJPanel;
    }

    private InputPanels initInputPanels() {
        InputPanels inputPanels = new InputPanels();
        inputPanel = inputPanels.getInputPanel();
        // inputPanel.setLayout(FlowLayouts.flowLayoutLeft);
        inputPanels.getTimeRadioPanel().setLayout(FlowLayouts.flowLayoutLeft);
        frame.add(inputPanel);
        return inputPanels;
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
            }
        });
        // 设置窗体的内容面板
        frame.setContentPane(contentPane);
        // 窗体使用箱型布局,垂直排列
        frame.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
    }

    private void initZeroPanel() {
        final JPanel devicesPanel;
        // 创建设备面板
        devicesPanel = new AdbDi(frame).createDevicesPanel();
        // 设备面板设置 流式布局 左对齐
        devicesPanel.setLayout(FlowLayouts.flowLayoutLeft);
        // 添加到窗体中
        frame.add(devicesPanel);
    }

    private void initSeconPanel() {
        final JPanel stopJPanel;
        StopJPanels stopJPanels = new StopJPanels();
        stopJPanel = stopJPanels.getStopJPanel();
        stopJPanel.setLayout(FlowLayouts.flowLayoutLeft);
        stopBtn = stopJPanels.getStopBtn();
        frame.add(stopJPanel);
    }

    private void initFirstPanel() {
        final JPanel adbJPanel;
        // 创建第1个面板
        // 创建adb面板和面板中的控件
        AdbJPanels adbJPanels = new AdbJPanels();
        adbJPanel = adbJPanels.getAdbJPanel();
        // adbJPanel.setLayout(FlowLayouts.flowLayoutLeft);
        frame.add(adbJPanel);
    }

    public static Buttons getInstance() {
        return instance;
    }

    public void setIsRunning(Runnable isRunning) {
        // System.out.println("正在运行的:" + isRunning);
        isRunningSet.add(isRunning);
        // System.out.println("set长度:" + isRunningSet.size());
    }

    public static void main(String[] args) {
        Buttons.getInstance();
    }

    /**
     * 根据体魄目录生成按钮
     *
     * @param frame
     * @param checkJPanel
     * @param otherJPanel
     */
    private void newButtonJPanel(JFrame frame, JPanel checkJPanel, JPanel otherJPanel) {
        // File dir = new File("G:\\dev2\\idea_workspace\\MyJavaTools\\AdbTools\\Pythons");
        File dir = new File(dirPath);
        System.out.println("AdbToolsPythonImg = " + dir.getAbsolutePath());
        File[] dirList = dir.listFiles(pathname -> pathname.isDirectory());

        if (dirList != null) {
            for (File dirDeep1 : dirList) {
                if (dirDeep1.isDirectory()) {
                    // 列出子目录
                    File[] dirDeep1List = dirDeep1.listFiles(pathname -> pathname.isDirectory());
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
                            checkBox.addItemListener(new JCheckBoxControlJPanelItemListener(frame, jPanel));

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
                                            String pythonFile = dirPath + name + "\\" + name1 + "\\" + "_" + Device.getBrand() + ".py";
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
                                                // new
                                            } else {
                                                new Thread(new PythonCloseableRun(name1, pythonFile, output)).start();
                                            }
                                        }
                                    });

                                    AbstractButtons.setJButtonMargin(button);
                                    jPanel.add(button);
                                }
                            }
                            // 隐藏面板
                            jPanel.setVisible(false);
                            frame.add(jPanel);
                        }
                        // 如果不存在子目录
                        else {
                            // 一级目录处理
                            // 处理子目录
                            subdirHandling(otherJPanel, dirDeep1);
                        }
                    }
                }
            }
        }
        int jPanel1ComponentCount = otherJPanel.getComponentCount();
        // 向上取整
        int rows = (int) Math.ceil(jPanel1ComponentCount / 4.0);
        otherJPanel.setLayout(new GridLayout(rows, 4, 0, 0));
        otherJPanel.setBorder(new TitledBorder("Other"));
        frame.add(otherJPanel);
        // return otherJPanel;
    }

    private void subdirHandling(JPanel otherJPanel, File dirDeep1) {
        String name = dirDeep1.getName();
        String chName = propertiesTools.getProperty(name);
        JButton button = new JButton(chName);
        if ("Auto".equals(name)) {
            // auto(name, chName, button);
        } else {
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // JButton source = (JButton) e.getSource();
                    String pythonDir = dirPath + name + "\\";
                    String pythonFile = pythonDir + "_" + Device.getBrand() + ".py";
                    System.out.println("pythonFile = " + pythonFile);
                    new Thread(new PythonCloseableRun(chName, pythonFile, output)).start();
                }
            });
        }
        AbstractButtons.setJButtonMargin(button);
        // 添加到其他面板
        otherJPanel.add(button);
    }
}
