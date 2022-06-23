package adbs.test.auto;

import adbs.action.listener.OpenButtonListener;
import adbs.action.listener.abs.shellinput.HomeBtnAcListener;
import adbs.action.listener.abs.shellinput.ReturnBtnAcListener;
import adbs.action.listener.abs.shellinput.TaskManageBtnAcListener;
import adbs.action.runnable.abs.CloseableRunnable;
import adbs.buttons.AbstractButtons;
import adbs.test.AdbDi;
import adbs.test.Device;
import adbs.test.auto.listener.OtherJCheckBoxItemListener;
import adbs.test.auto.run.Python_FileCloseableRunnable;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileFilter;
import java.util.HashSet;
import java.util.Iterator;

public class Buttons {
    static {
        // 设置外观,先设置外观，再创建UI。
        // 为了保证创建UI时，已经设置好外观，设置外观的代码最好写在静态块中，
        // 并且把这个静态块写在类定义的最前面。
        FlatLightLaf.setup();
    }

    private static String dirPath = "G:\\dev2\\idea_workspace\\MyJavaTools\\AdbTools\\Pythons\\";
    private static JLabel output;

    private HashSet<Runnable> isRunningSet = new HashSet<>();
    private static Buttons instance = new Buttons();

    private Buttons() {
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
        // 创建窗体
        JFrame frame = new JFrame();
        // 创建窗体的内容面板
        JPanel contentPane = new JPanel();
        // 设置窗体的内容面板
        frame.setContentPane(contentPane);
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
        // 窗体使用箱型布局,垂直排列
        frame.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT, 0, 0);
        // 创建设备面板
        JPanel devicesPanel = new AdbDi(frame).createDevicesPanel();
        // 设备面板设置布局管理器
        devicesPanel.setLayout(flowLayout);
        // 添加到窗体中
        frame.add(devicesPanel);

        JPanel adbJPanel = new JPanel();
        adbJPanel.setLayout(flowLayout);

        JButton openBtn = new JButton("打开");
        JButton returnBtn = new JButton("返回");
        JButton homeBtn = new JButton("Home");
        JButton taskBtn = new JButton("task");
        JButton stopBtn = new JButton("stop");

        // 打开（设备）按钮
        openBtn.addActionListener(new OpenButtonListener());
        // 任务管理键
        taskBtn.addActionListener(new TaskManageBtnAcListener());
        // 返回键
        returnBtn.addActionListener(new ReturnBtnAcListener());
        // home键
        homeBtn.addActionListener(new HomeBtnAcListener());
        stopBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HashSet<Runnable> isRunningSet = Buttons.getInstance().isRunningSet;
                Iterator<Runnable> iterator = isRunningSet.iterator();
                while (iterator.hasNext()) {
                    Runnable runnable = iterator.next();
                    // System.out.println(runnable);
                    //如果是可关闭的线程体
                    // if (runnable instanceof CloseableRunnable) {
                    if (runnable instanceof Python_FileCloseableRunnable) {
                        Python_FileCloseableRunnable python_fileCloseableRunnable = (Python_FileCloseableRunnable) runnable;
                        System.out.println(python_fileCloseableRunnable + " is stop now");
                        // 关闭线程
                        python_fileCloseableRunnable.stop();
                        // 从线程池中删除掉
                        iterator.remove();
                    }
                }
                // DouYinTaskRunnable2.getInstance().stop();
                System.out.println("end isRunningSet.size() = " + isRunningSet.size());
                // inputPanel.setVisible(false);
                // inOutputModel.getInputPanelModel().getInputPanel().setVisible(false);
            }
        });

        adbJPanel.add(openBtn);
        adbJPanel.add(returnBtn);
        adbJPanel.add(homeBtn);
        adbJPanel.add(taskBtn);
        adbJPanel.add(stopBtn);
        AbstractButtons.setMarginInButtonJPanel(adbJPanel);
        frame.add(adbJPanel);


        // 其他按钮面板
        JPanel otherJPanel = new JPanel();
        //
        JPanel checkJPanel = new JPanel();
        newButtonJPanel(frame, checkJPanel, otherJPanel);

        checkJPanel.setLayout(flowLayout);
        JCheckBox otherJCheckBox = new JCheckBox("其他", true);
        otherJCheckBox.addItemListener(new OtherJCheckBoxItemListener(frame, otherJPanel));
        checkJPanel.add(otherJCheckBox);
        // 插入到第2行
        frame.add(checkJPanel, 2);
        // frame.add(checkJPanel);

        JPanel outputJPanel = new JPanel();
        outputJPanel.setLayout(flowLayout);
        output = new JLabel();
        output.setText("统一输出");


        outputJPanel.add(output);
        frame.add(outputJPanel);
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

    private static JPanel newButtonJPanel(JFrame frame, JPanel checkJPanel, JPanel otherJPanel) {
        File dir = new File("G:\\dev2\\idea_workspace\\MyJavaTools\\AdbTools\\Pythons");
        File[] dirList = dir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });

        for (File dirDeep1 : dirList) {
            if (dirDeep1.isDirectory()) {
                // 列出子目录
                File[] dirDeep1List = dirDeep1.listFiles(new FileFilter() {
                    @Override
                    public boolean accept(File pathname) {
                        return pathname.isDirectory();
                    }
                });
                // 如果一级子目录存在子目录
                if (dirDeep1List.length > 0) {
                    // 获取一级子目录的名称
                    String name = dirDeep1.getName();
                    // System.out.println("name = " + name);
                    // 创建面板
                    JPanel jPanel = new JPanel();
                    // 给面板标题边框，使用一级目录名作为标题
                    jPanel.setBorder(new TitledBorder(name));

                    FlowLayout layout = new FlowLayout(FlowLayout.LEFT, 0, 0);
                    // 设置布局管理器
                    jPanel.setLayout(layout);
                    // 创建复选框，默认勾选
                    JCheckBox checkBox = new JCheckBox(name, true);
                    checkJPanel.add(checkBox);
                    // 监听
                    checkBox.addItemListener(new OtherJCheckBoxItemListener(frame, jPanel));

                    // 遍历二级子目录
                    for (File dirDeep2 : dirDeep1List) {
                        // 获取二级目录名
                        String name1 = dirDeep2.getName();
                        if (!name1.contains("test") && !name1.contains("demo")) {
                            // 创建按钮
                            JButton button = new JButton(name1);
                            button.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    // 拼接内容Python文件路径
                                    String pythonFile = dirPath + name + "\\" + name1 + "\\" + "_" + Device.getBrand() + ".py";
                                    System.out.println("pythonFile = " + pythonFile);
                                    new Thread(new Python_FileCloseableRunnable(name1, pythonFile, output)).start();
                                }
                            });

                            AbstractButtons.setJButtonMargin(button);
                            jPanel.add(button);
                        }
                    }
                    frame.add(jPanel);
                }
                // 如果不存在子目录
                else {
                    JButton button = new JButton(dirDeep1.getName());
                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            JButton source = (JButton) e.getSource();
                            String msg = source.getText();
                            String pythonDir = dirPath + msg + "\\";
                            String pythonFile = pythonDir + "_" + Device.getBrand() + ".py";
                            System.out.println("pythonFile = " + pythonFile);
                            new Thread(new Python_FileCloseableRunnable(msg, pythonFile, output)).start();
                        }
                    });
                    AbstractButtons.setJButtonMargin(button);
                    // 添加到其他面板
                    otherJPanel.add(button);
                }
            }
        }
        int jPanel1ComponentCount = otherJPanel.getComponentCount();
        // 向上取整
        int rows = (int) Math.ceil(jPanel1ComponentCount / 4.0);
        otherJPanel.setLayout(new GridLayout(rows, 4, 0, 0));
        otherJPanel.setBorder(new TitledBorder("Other"));
        frame.add(otherJPanel);
        return otherJPanel;
    }
}
