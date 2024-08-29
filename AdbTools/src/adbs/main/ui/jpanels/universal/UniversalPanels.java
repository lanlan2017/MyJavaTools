package adbs.main.ui.jpanels.universal;

import adbs.main.AdbTools;
import adbs.main.run.AdbGetPackage;
import adbs.main.run.PythonCloseableRun;
import adbs.main.ui.config.FlowLayouts;
import adbs.main.ui.inout.listener.StopBtnAcListener2;
import adbs.main.ui.jframe.JFramePack;
import adbs.main.ui.jpanels.time.TimePanels;
import adbs.main.ui.jpanels.universal.listener.*;
import adbs.main.ui.jpanels.universal.pinyin.FileCreator;
import adbs.main.ui.jpanels.universal.pinyin.PinyinConverter;
import adbs.main.ui.jpanels.universal.runnable.CloseableRunnable;
import adbs.model.Device;
import adbs.python.Region;
import config.AdbToolsProperties;
import tools.swing.button.AbstractButtons;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

/**
 * 通用面板
 */
public class UniversalPanels {
    /**
     * 通用面板体
     */
    private final JPanel universalPanel;
    /**
     * 浏览按钮
     */
    private final JButton browseButton;
    /**
     * 等待返回按钮
     */
    private final JButton waitButton;
    /**
     * 阅读按钮
     */
    private final JButton readButton;
    /**
     * 刷视频按钮
     */
    private final JButton videoButton;
    /**
     * 逛街按钮
     */
    private final JButton shoppingButton;


    private final JButton btnStop;
    /**
     * 通用面板输出功能
     */
    private final JLabel output2;

    private PythonCloseableRun pyRun;
    private Thread pyThread;

    private final JButton btnScrcpyOrder;

    private static final int MAX_VALUE;
    private int currentValue = 0;

    static {
        MAX_VALUE = Region.parts;
    }

    /**
     * 初始化通用面板
     *
     * @param timePanels TimePanels面板对象
     */
    public UniversalPanels(TimePanels timePanels) {
        // 创建通用功能面板
        universalPanel = new JPanel();
        // universalPanel.setBorder(new TitledBorder("通用功能"));

        universalPanel.setLayout(FlowLayouts.flowLayoutLeft);
        // readButton = new JButton("阅读");
        readButton = new JButton("读");
        readButton.setToolTipText("阅读功能:连续点击屏幕右侧");

        // browseButton = new JButton("浏览");
        browseButton = new JButton("浏");
        browseButton.setToolTipText("连续在屏幕左侧从下向上滑动");

        // waitButton = new JButton("等待");
        waitButton = new JButton("等");
        waitButton.setToolTipText("等待一定时间后提示");
        // videoButton = new JButton("刷视频");
        videoButton = new JButton("视");
        videoButton.setToolTipText("等待随机秒数后，从下向上滑动一次");

        // shoppingButton = new JButton("逛街");
        shoppingButton = new JButton("逛");
        shoppingButton.setToolTipText("连续从下向上滑动三次，然后上下来回滑动");
        // output2 = new JLabel("输出2");
        output2 = new JLabel("");

        // 阅读线程
        readButton.addActionListener(new ReadButtonActionListener(timePanels));
        // 浏览线程
        browseButton.addActionListener(new BrowseButtonActionListener(timePanels));
        // 等待后返回按钮
        waitButton.addActionListener(new WaitButtonActionListener(timePanels));
        // 刷视频按钮
        videoButton.addActionListener(new VideoButtonActionListener(timePanels));
        // 逛街按钮
        shoppingButton.addActionListener(new ShoppingButtonActionListener(timePanels));

        btnStop = initBtnStop();

        JButton zhongDuanBtn = initBtnZhongDuan();


        JButton btnPy = initBtnPy();
        btnScrcpyOrder = new JButton("0");

        btnScrcpyOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentValue = (currentValue + 1) % (MAX_VALUE + 1); // 更新值并循环
                btnScrcpyOrder.setText(String.valueOf(currentValue));
            }
        });

        JButton btnKPy = intiBtnKPy();

        // 添加到面板中
        universalPanel.add(readButton);
        universalPanel.add(browseButton);
        universalPanel.add(videoButton);
        universalPanel.add(shoppingButton);
        universalPanel.add(waitButton);
        universalPanel.add(btnStop);
        universalPanel.add(zhongDuanBtn);

        universalPanel.add(btnScrcpyOrder);
        universalPanel.add(btnPy);
        universalPanel.add(btnKPy);
        universalPanel.add(output2);

        AbstractButtons.setMargin_2_InButtonJPanel(universalPanel, 1);
    }

    private JButton initBtnStop() {
        JButton stopBtn = new JButton("停止");
        stopBtn.setToolTipText("停止所有后台线程,刷新界面");
        stopBtn.addActionListener(new StopBtnAcListener2());
        return stopBtn;
    }

    /**
     * 创建停止CloseRunnable线程，并且跳过最后一步操作的按钮
     *
     * @return
     */
    private JButton initBtnZhongDuan() {
        JButton zhongDuanBtn = new JButton("中断");
        zhongDuanBtn.setToolTipText("中断正在运行的的线程");
        zhongDuanBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //获取正在运行的线程列表
                Iterator<Runnable> iterator = AdbTools.getInstance().getIsRunningSet().iterator();
                //遍历正在运行的线程列表
                while (iterator.hasNext()) {
                    Runnable runnable = iterator.next();
                    //如果是可停止的线程类或者它的子类
                    if (runnable instanceof CloseableRunnable) {
                        CloseableRunnable closeableRunnable = (CloseableRunnable) runnable;
                        //停止并跳过线程的after方法
                        closeableRunnable.stopSkipAfter();
                        // 从线程池中删除掉
                        iterator.remove();
                    }
                }
                // JLable线程不安全，在事件调度线程中执行，以确保线程安全
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        // 时间面板的标签文字设为空字符串
                        AdbTools.getInstance().getTimePanels().getTimerJLabel().setText("");
                        // 隐藏时间面板
//                        AdbTools.getInstance().getTimePanels().getTimePanel().setVisible(false);
//                        ThreadSleep.seconds(1);
//                        // 通用面板的标签文字设置为空字符串
//                        AdbTools.getInstance().getUniversalPanels().getOutput2().setText("");
                        JFramePack.pack();
                    }
                });
            }
        });
        return zhongDuanBtn;
    }

    private JButton intiBtnKPy() {
        JButton btnKPy = new JButton("KP");
        btnKPy.setToolTipText("杀死后台的Py线程");
        btnKPy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (pyRun != null) {
                    pyRun.stop();
                }
            }
        });
        return btnKPy;
    }

    private JButton initBtnPy() {
        JButton btnPy = new JButton("P");
        btnPy.setToolTipText("图片识别");
        btnPy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //如果线程不存在，或者线程存在，但是线程死掉了
                if (pyThread == null || !pyThread.isAlive()) {
                    System.out.println("创建新的py线程");


                    String msg = "";
                    Device device = AdbTools.getInstance().getDevice();
                    String deviceName = device.getName();

                    //                    System.out.println("deviceName = " + deviceName);
                    if (deviceName.endsWith("+")) {
                        deviceName = deviceName.substring(0, deviceName.length() - 1);
                    }
                    //                    System.out.println("deviceName = " + deviceName);

                    String packageName = AdbGetPackage.getTopPackageName(device.getSerial());
                    //                    System.out.println("packageName = " + packageName);

                    // 获取应用名（中文名）
                    String chName = AdbToolsProperties.moneyApkPro.getProperty(packageName);
                    System.out.println("chName = " + chName);
                    if (!chName.equals(packageName)) {
                        String pinyin = PinyinConverter.convertToPinyin(chName);

                        System.out.println("pinyin = " + pinyin);

                        //                        //拼接Python文件的路径
                        //                        String deviceFilePath = "AdbToolsPythons" + "\\" + deviceName;
                        //                        String deviceFilePath = device.getFilePath();
                        String deviceFilePath = device.getDeviceFilePath();

                        String pyPath = deviceFilePath + "\\" + pinyin + "\\1.py";
                        System.out.println("pyPath = " + pyPath);
                        // 如果文件不存在，则创建文件
                        FileCreator.createFile(pyPath);
                        pyRun = new PythonCloseableRun(msg, pyPath, output2);
                        //重新创建线程
                        pyThread = new Thread(pyRun);
                        pyThread.start();
                    }


                } else {
                    System.out.println("py线程运行中，请勿重复启动");
                }


            }
        });
        return btnPy;
    }


    public JPanel getUniversalPanel() {
        return universalPanel;
    }

    public JButton getReadButton() {
        return readButton;
    }

    public JLabel getOutput2() {
        return output2;
    }

    public JButton getBrowseButton() {
        return browseButton;
    }

    public JButton getWaitButton() {
        return waitButton;
    }

    public JButton getVideoButton() {
        return videoButton;
    }

    public JButton getShoppingButton() {
        return shoppingButton;
    }


    public JButton getBtnStop() {
        return btnStop;
    }

    public JButton getBtnScrcpyOrder() {
        return btnScrcpyOrder;
    }

    public void vidioBtnDoClick() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                videoButton.doClick();
            }
        });
    }

}
