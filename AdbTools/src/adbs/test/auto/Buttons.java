package adbs.test.auto;

import adbs.action.MinusBtnAcListener;
import adbs.action.listener.*;
import adbs.action.listener.abs.shell.RebootBtnAcListener;
import adbs.action.listener.abs.shellinput.HomeBtnAcListener;
import adbs.action.listener.abs.shellinput.ReturnBtnAcListener;
import adbs.action.listener.abs.shellinput.TaskManageBtnAcListener;
import adbs.action.model.InOutputModel;
import adbs.action.model.InputPanelModel;
import adbs.action.runnable.ReadButtonRunnable;
import adbs.action.runnable.open.Taskkill;
import adbs.cmd.AdbCommands;
import tools.swing.button.AbstractButtons;
import adbs.test.AdbDi;
import adbs.test.Device;
import adbs.test.DeviceListener;
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

    private final JFrame frame;
    private static JButton stopBtn;
    private static final String dirPath = "AdbToolsPythons\\";

    private PropertiesTools propertiesTools = new PropertiesTools("AdbTools.properties");
    private final InOutputModel inOutputModel;
    private final JPanel adbJPanel;
    private final JPanel devicesPanel;
    private final FlowLayout flowLayoutLeft;
    private final JPanel contentPane;
    private final JPanel inputPanel;
    private final JLabel timeLable;
    private final JPanel timeRadioPanel;
    private final JTextField input1;
    private final JTextField input2;
    // 增加按钮
    private final JButton plusBtn;
    // 减少按钮
    private final JButton minusBtn;
    private final JButton inputOkButton;
    private final InputPanelModel inputPanelModel;
    private final JRadioButton radioButton15s;
    private final JRadioButton radioButton35s;
    private final JRadioButton radioButton70s;
    private final JPanel universalPanel;
    private final JButton browseButton;
    private final JButton waitReturnButton;
    private final JButton readButton;
    private final JButton videoButton;
    private final JButton shoppingButton;
    private final JPanel otherJPanel;
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

        // 流式布局左对齐
        flowLayoutLeft = new FlowLayout(FlowLayout.LEFT, 0, 0);
        // 创建设备面板
        devicesPanel = new AdbDi(frame).createDevicesPanel();

        // 设备面板设置布局管理器
        devicesPanel.setLayout(flowLayoutLeft);
        // 添加到窗体中
        frame.add(devicesPanel);

        // 创建adb面板
        adbJPanel = getAdbJPanel();
        frame.add(adbJPanel);

        // 停止面板
        JPanel stopJPanel = new JPanel();
        stopJPanel.setBorder(new TitledBorder("stop"));
        stopJPanel.setLayout(flowLayoutLeft);

        // 停止按钮
        stopBtn = new JButton(propertiesTools.getProperty("stop"));
        stopBtn.setToolTipText("停止所有后台线程");
        stopJPanel.add(stopBtn);
        AbstractButtons.setMarginInButtonJPanel(stopJPanel);
        frame.add(stopJPanel);


        // 创建输入选择面板
        inputPanel = new JPanel(flowLayoutLeft);
        timeLable = new JLabel("时间(s)");
        timeRadioPanel = new JPanel(flowLayoutLeft);
        radioButton15s = new JRadioButton("15");
        radioButton35s = new JRadioButton("35");
        radioButton70s = new JRadioButton("70");
        timeLable.add(radioButton15s);
        timeLable.add(radioButton35s);
        timeLable.add(radioButton70s);

        input1 = new JTextField(3);
        input1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    System.out.println("按下回车键");
                    // 触发回车键
                    inputOkButton.doClick();
                }
            }
        });



        input2 = new JTextField(3);
        plusBtn = new JButton(">");


        minusBtn = new JButton("<");
        inputOkButton = new JButton("确认");

        inputPanel.add(timeLable);
        inputPanel.add(timeRadioPanel);
        inputPanel.add(input1);
        inputPanel.add(input2);
        inputPanel.add(plusBtn);
        inputPanel.add(minusBtn);
        inputPanel.add(inputOkButton);
        frame.add(inputPanel);



        // 创建通用功能面板
        universalPanel = new JPanel();
        browseButton = new JButton("浏览返回");
        waitReturnButton = new JButton("等待");
        readButton = new JButton("阅读");
        videoButton = new JButton("刷视频");
        shoppingButton = new JButton("逛街");

        // 其他按钮面板
        otherJPanel = new JPanel();
        checkJPanel = new JPanel();
        otherJCheckBox = new JCheckBox("其他", true);
        generalJCheckBox = new JCheckBox("通用", true);

        outputJPanel = new JPanel();
        output = new JLabel();

        // // 创建输入面板的模型
        inputPanelModel = new InputPanelModel(inputPanel, timeLable, timeRadioPanel, radioButton15s, radioButton35s, radioButton70s, input1, input2, inputOkButton, plusBtn, minusBtn);
        inOutputModel = new InOutputModel(inputPanelModel, output, stopBtn);

        stopBtn.addActionListener(new StopBtnAcListener2(frame, isRunningSet, inOutputModel));

        // // 增加按钮
        // plusBtn.addActionListener(new PlusBtnAcListener(inOutputModel));
        // 减少按钮
        minusBtn.addActionListener(new MinusBtnAcListener(inOutputModel));
        // 输入面板等待按钮
        inputOkButton.addActionListener(new InputOkButtonActionListener(inOutputModel));

        // inputPanel.add(timeLable);
        // inputPanel.add(timeRadioPanel);
        // inputPanel.add(input1);
        // inputPanel.add(input2);
        // inputPanel.add(plusBtn);
        // inputPanel.add(minusBtn);
        // inputPanel.add(inputOkButton);
        // frame.add(inputPanel);


        universalPanel.setBorder(new TitledBorder("通用功能"));
        universalPanel.setLayout(flowLayoutLeft);
        universalPanel.add(browseButton);
        universalPanel.add(waitReturnButton);
        universalPanel.add(readButton);
        universalPanel.add(videoButton);
        universalPanel.add(shoppingButton);
        frame.add(universalPanel);

        AbstractButtons.setMarginInButtonJPanel(universalPanel);
        AbstractButtons.setMarginInButtonJPanel(adbJPanel);
        AbstractButtons.setMarginInButtonJPanel(inputPanel);

        // 浏览后返回按钮事件处理程序
        browseButton.addActionListener(new BrowseButtonActionListener(frame, inputPanelModel));
        // 等待后返回按钮
        waitReturnButton.addActionListener(new WaitReturnButtonActionListener(frame, inputPanelModel));
        readButton.addActionListener(new PyImgFindAcListener(ReadButtonRunnable.getInstance(), inOutputModel));
        // 刷视频按钮
        videoButton.addActionListener(new VideoButtonActionListener(frame, inputPanelModel));
        // 逛街按钮
        shoppingButton.addActionListener(new ShoppingButtonActionListener(frame, inputPanelModel));


        newButtonJPanel(frame, checkJPanel, otherJPanel);

        checkJPanel.setLayout(flowLayoutLeft);

        otherJCheckBox.addItemListener(new JCheckBoxControlJPanelItemListener(frame, otherJPanel));

        generalJCheckBox.addItemListener(new JChcekBoxControl2JPanelItemListener(frame, universalPanel, inputPanel));

        checkJPanel.add(otherJCheckBox);
        checkJPanel.add(generalJCheckBox, 0);
        // 插入到第2行
        frame.add(checkJPanel, 2);
        // frame.add(checkJPanel);

        outputJPanel.setLayout(flowLayoutLeft);
        output.setText("统一输出");

        outputJPanel.add(output);
        frame.add(outputJPanel);

        otherJCheckBox.doClick();
        // frame.add(checkJPanel);
        // 永远置顶
        frame.setAlwaysOnTop(true);
        // 设置关闭按钮功能
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // 显示窗体
        frame.setVisible(true);
        // 调整窗体到最佳大小
        frame.pack();
    }

    private JPanel getAdbJPanel() {
        final JPanel adbJPanel;
        adbJPanel = new JPanel();
        adbJPanel.setBorder(new TitledBorder("adb"));
        adbJPanel.setLayout(flowLayoutLeft);

        // 打开镜像按钮
        JButton openBtn = new JButton(new ImageIcon(Buttons.class.getClassLoader().getResource("open.png")));
        openBtn.setToolTipText("打开设备");
        openBtn.addActionListener(new OpenButtonListener());

        // 杀死镜像按钮
        JButton  killBtn = new JButton("kill");
        killBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = DeviceListener.getPhoneId();
                Taskkill.killScrcpy(id);
            }
        });


        // 返回键按钮
        JButton returnBtn = new JButton(new ImageIcon(Buttons.class.getClassLoader().getResource("向左三角形.png")));
        returnBtn.setToolTipText("返回键");
        // 返回键
        returnBtn.addActionListener(new ReturnBtnAcListener());


        // home键按钮
        JButton  homeBtn = new JButton(new ImageIcon(Buttons.class.getClassLoader().getResource("圆圈.png")));
        homeBtn.setToolTipText("home键");
        homeBtn.addActionListener(new HomeBtnAcListener());
        // 任务键按钮
        JButton taskBtn = new JButton(new ImageIcon(Buttons.class.getClassLoader().getResource("空框.png")));
        taskBtn.setToolTipText("任务键");
        // 任务管理键
        taskBtn.addActionListener(new TaskManageBtnAcListener());


        // 重启键按钮
        JButton rebootBtn = new JButton("重启");
        rebootBtn.addActionListener(new RebootBtnAcListener(frame, "reboot"));
        // 关机键按钮
        JButton closeBtn = new JButton("关机");
        closeBtn.addActionListener(new RebootBtnAcListener(frame, "shell reboot -p"));
        // adb面板添加按钮
        adbJPanel.add(openBtn);
        adbJPanel.add(killBtn);
        adbJPanel.add(returnBtn);
        adbJPanel.add(homeBtn);
        adbJPanel.add(taskBtn);
        adbJPanel.add(rebootBtn);
        adbJPanel.add(closeBtn);
        // frame.add(adbJPanel);
        return adbJPanel;
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
