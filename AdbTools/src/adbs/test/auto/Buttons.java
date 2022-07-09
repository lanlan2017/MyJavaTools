package adbs.test.auto;

import adbs.action.listener.*;
import adbs.action.listener.abs.shell.RebootBtnAcListener;
import adbs.action.listener.abs.shellinput.HomeBtnAcListener;
import adbs.action.listener.abs.shellinput.ReturnBtnAcListener;
import adbs.action.listener.abs.shellinput.TaskManageBtnAcListener;
import adbs.action.model.InOutputModel;
import adbs.action.model.InputPanelModel;
import adbs.action.runnable.ReadButtonRunnable;
import adbs.action.runnable.open.Taskkill;
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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.HashSet;

public class Buttons {

    private static JButton stopBtn;


    static {
        // 设置外观,先设置外观，再创建UI。
        // 为了保证创建UI时，已经设置好外观，设置外观的代码最好写在静态块中，
        // 并且把这个静态块写在类定义的最前面。
        FlatLightLaf.setup();
    }

    // private static final String dirPath = "G:\\dev2\\idea_workspace\\MyJavaTools\\AdbTools\\Pythons\\";
    // private static final String dirPath = "Pythons\\";
    private static final String dirPath = "AdbToolsPythons\\";
    private final JButton rebootBtn;
    private final JButton closeBtn;
    private final JButton killBtn;
    // private PropertiesTools propertiesTools = new PropertiesTools(dirPath + "\\" + "ui.properties");
    // private PropertiesTools propertiesTools = new PropertiesTools(dirPath + "\\" + "AdbTools.properties");
    private PropertiesTools propertiesTools = new PropertiesTools("AdbTools.properties");
    private final InOutputModel inOutputModel;
    private final JButton taskBtn;
    private final JButton homeBtn;
    private final JButton returnBtn;
    private final JButton openBtn;
    private final JPanel adbJPanel;
    private final JPanel devicesPanel;
    private final FlowLayout flowLayout;
    private final JPanel contentPane;
    private final JFrame frame;
    private final JPanel inputPanel;
    private final JLabel timeLable;
    private final JPanel timeRadioPanel;
    private final JTextField input1;
    private final JTextField input2;
    private final JButton plusBtn;
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
        // 设置窗体的内容面板
        frame.setContentPane(contentPane);

        // 窗体使用箱型布局,垂直排列
        frame.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        flowLayout = new FlowLayout(FlowLayout.LEFT, 0, 0);
        // 创建设备面板
        devicesPanel = new AdbDi(frame).createDevicesPanel();
        // 设备面板设置布局管理器
        devicesPanel.setLayout(flowLayout);
        // 添加到窗体中
        frame.add(devicesPanel);
        // 添加AdbJPanel到frame中
        // addAdbJPanel(frame, flowLayout);

        adbJPanel = new JPanel();
        adbJPanel.setLayout(flowLayout);
        // openBtn = new JButton(new ImageIcon());
        openBtn = new JButton(new ImageIcon(Buttons.class.getClassLoader().getResource("open.png")));
        openBtn.setToolTipText("打开设备");
        killBtn = new JButton("kill");


        // openBtn.setIcon(new ImageIcon(Buttons.class.getClassLoader().getResource("open.png")));
        returnBtn = new JButton(new ImageIcon(Buttons.class.getClassLoader().getResource("向左三角形.png")));
        returnBtn.setToolTipText("返回键");

        homeBtn = new JButton(new ImageIcon(Buttons.class.getClassLoader().getResource("圆圈.png")));
        homeBtn.setToolTipText("home键");

        taskBtn = new JButton(new ImageIcon(Buttons.class.getClassLoader().getResource("空框.png")));
        taskBtn.setToolTipText("任务键");

        rebootBtn = new JButton("重启");
        rebootBtn.addActionListener(new RebootBtnAcListener(frame, "reboot"));
        closeBtn = new JButton("关机");
        closeBtn.addActionListener(new RebootBtnAcListener(frame, "shell reboot -p"));

        // propertiesTools.getProperty("stop")
        // stopBtn = new JButton("stop");
        stopBtn = new JButton(propertiesTools.getProperty("stop"));

        stopBtn.setToolTipText("停止所有后台线程");

        // 创建输入选择面板
        inputPanel = new JPanel(flowLayout);
        timeLable = new JLabel("时间(s)");
        timeRadioPanel = new JPanel(flowLayout);
        radioButton15s = new JRadioButton("15");
        radioButton35s = new JRadioButton("35");
        radioButton70s = new JRadioButton("70");
        timeLable.add(radioButton15s);
        timeLable.add(radioButton35s);
        timeLable.add(radioButton70s);
        input1 = new JTextField(3);
        input2 = new JTextField(3);
        plusBtn = new JButton(">");
        minusBtn = new JButton("<");
        inputOkButton = new JButton("确认");

        // 创建通用功能面板
        universalPanel = new JPanel();
        browseButton = new JButton("浏览返回");
        // waitReturnButton = new JButton("等待返回");
        // waitReturnButton = new JButton("等待提示");
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

        // 创建输入面板的模型
        inputPanelModel = new InputPanelModel(inputPanel, timeLable, timeRadioPanel, radioButton15s, radioButton35s, radioButton70s, input1, input2, inputOkButton, plusBtn, minusBtn);
        inOutputModel = new InOutputModel(inputPanelModel, output, stopBtn);

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

        // 打开（设备）按钮
        openBtn.addActionListener(new OpenButtonListener());
        killBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = DeviceListener.getPhoneId();
                Taskkill.killScrcpy(id);
            }
        });
        // 任务管理键
        taskBtn.addActionListener(new TaskManageBtnAcListener());
        // 返回键
        returnBtn.addActionListener(new ReturnBtnAcListener());
        // home键
        homeBtn.addActionListener(new HomeBtnAcListener());
        stopBtn.addActionListener(new StopBtnAcListener2(frame, isRunningSet, inOutputModel));

        // adb面板添加按钮
        adbJPanel.add(openBtn);
        adbJPanel.add(killBtn);
        adbJPanel.add(returnBtn);
        adbJPanel.add(homeBtn);
        adbJPanel.add(taskBtn);
        adbJPanel.add(rebootBtn);
        adbJPanel.add(closeBtn);
        adbJPanel.add(stopBtn);
        frame.add(adbJPanel);


        plusBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int value1 = Integer.parseInt(input1.getText());
                int value2 = Integer.parseInt(input2.getText());
                //7-14
                //8-16
                //9-18
                //10-20
                //20-40
                //40-60
                //60-90
                //90-150
                //150-240
                if (value1 < 10) {
                    value1 = value1 + 1;
                    value2 = value1 * 2;
                } else if (value1 == 10) {
                    value1 = 15;
                    value2 = 30;

                } else if (value1 == 15) {
                    value1 = 20;
                    value2 = 40;

                } else if (value1 == 20) {
                    value1 = 30;
                    value2 = 60;
                } else if (value1 == 30) {
                    value1 = 60;
                    value2 = 90;
                } else if (value1 == 60) {
                    value1 = 90;
                    value2 = 150;
                } else if (value1 == 90) {
                    value1 = 150;
                    value2 = 240;
                } else {
                    return;
                }
                input1.setText(String.valueOf(value1));
                input2.setText(String.valueOf(value2));


            }
        });
        minusBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int value1 = Integer.parseInt(input1.getText());
                int value2 = Integer.parseInt(input2.getText());
                if (value1 == 150) {
                    value1 = 90;
                    value2 = 150;
                } else if (value1 == 90) {
                    value1 = 60;
                    value2 = 90;
                } else if (value1 == 60) {
                    value1 = 30;
                    value2 = 60;
                } else if (value1 == 30) {
                    value1 = 20;
                    value2 = 40;
                } else if (value1 == 20) {
                    value1 = 15;
                    value2 = 30;
                } else if (value1 == 15) {
                    value1 = 10;
                    value2 = 20;
                } else if (value1 > 7) {
                    value1 = value1 - 1;
                    value2 = value1 * 2;
                } else {
                    return;
                }
                input1.setText(String.valueOf(value1));
                input2.setText(String.valueOf(value2));
            }
        });
        // 输入面板等待按钮
        inputOkButton.addActionListener(new InputOkButtonActionListener(inOutputModel));
        inputPanel.add(timeLable);
        inputPanel.add(timeRadioPanel);
        inputPanel.add(input1);
        inputPanel.add(input2);
        inputPanel.add(plusBtn);
        inputPanel.add(minusBtn);
        inputPanel.add(inputOkButton);
        frame.add(inputPanel);


        universalPanel.setBorder(new TitledBorder("通用功能"));
        universalPanel.setLayout(flowLayout);
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

        checkJPanel.setLayout(flowLayout);

        otherJCheckBox.addItemListener(new JCheckBoxControlJPanelItemListener(frame, otherJPanel));

        generalJCheckBox.addItemListener(new JChcekBoxControl2JPanelItemListener(frame, universalPanel, inputPanel));

        checkJPanel.add(otherJCheckBox);
        checkJPanel.add(generalJCheckBox, 0);
        // 插入到第2行
        frame.add(checkJPanel, 2);
        // frame.add(checkJPanel);

        outputJPanel.setLayout(flowLayout);
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
                            String name = dirDeep1.getName();
                            String chName = propertiesTools.getProperty(name);
                            JButton button = new JButton(chName);
                            button.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    JButton source = (JButton) e.getSource();
                                    // String msg = source.getText();
                                    // String msg = source.getText();
                                    // String pythonDir = dirPath + msg + "\\";
                                    // String pythonFile = pythonDir + "_" + Device.getBrand() + ".py";
                                    // System.out.println("pythonFile = " + pythonFile);
                                    // new Thread(new PythonCloseableRun(msg, pythonFile, output)).start();
                                    String pythonDir = dirPath + name + "\\";
                                    String pythonFile = pythonDir + "_" + Device.getBrand() + ".py";
                                    System.out.println("pythonFile = " + pythonFile);
                                    new Thread(new PythonCloseableRun(chName, pythonFile, output)).start();
                                }
                            });
                            AbstractButtons.setJButtonMargin(button);
                            // 添加到其他面板
                            otherJPanel.add(button);
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


    // private static String getCHName(String name) {
    //     switch (name) {
    //         case "JinRiTouTiao":
    //             name = "今日头条";
    //             break;
    //         case "AiQiYi":
    //             name = "爱奇艺";
    //             break;
    //         case "PinDuoDuo":
    //             name = "拼多多";
    //             break;
    //         case "TaoBao":
    //             name = "淘宝";
    //             break;
    //         case "TaoBaoTeJia":
    //             name = "陶特";
    //             break;
    //         case "WuKongLiuLanQi":
    //             name = "悟空";
    //             break;
    //         case "FanQieXiaoShuo":
    //             name = "番茄小说";
    //             break;
    //         case "HuoShan":
    //             name = "火山";
    //             break;
    //         case "ZhuanQianRenWu":
    //             name = "赚钱任务";
    //             break;
    //         case "TaskCenter":
    //             name = "任务";
    //             break;
    //         case "Video":
    //             name = "刷视频";
    //             break;
    //         case "video":
    //             name = "刷视频";
    //             break;
    //         case "YueDu":
    //             name = "阅读";
    //             break;
    //         case "DouYin":
    //             name = "抖音";
    //             break;
    //         case "KuaiShou":
    //             name = "快手";
    //             break;
    //         case "KuaiKanDian":
    //             name = "快看点";
    //             break;
    //         // case "":
    //         //     name = "";
    //         //     break;
    //     }
    //     return name;
    // }
}
