package adbs.main.ui.jpanels.universal;

import adbs.main.AdbTools;
import adbs.main.run.AdbGetPackage;
import adbs.main.run.PythonCloseableRun;
import adbs.main.ui.config.FlowLayouts;
import adbs.main.ui.inout.listener.StopBtnAcListener2;
import adbs.main.ui.jpanels.time.TimePanels;
import adbs.main.ui.jpanels.universal.listener.*;
import adbs.main.ui.jpanels.universal.pinyin.FileCreator;
import adbs.main.ui.jpanels.universal.pinyin.PinyinUtils;
import config.AdbToolsProperties;
import tools.swing.button.AbstractButtons;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JButton browseButton;
    /**
     * 等待返回按钮
     */
    private JButton waitButton;
    /**
     * 阅读按钮
     */
    private JButton readButton;
    /**
     * 刷视频按钮
     */
    private JButton videoButton;
    /**
     * 逛街按钮
     */
    private JButton shoppingButton;
    //
    // /**
    //  * 滚动
    //  */
    // private JButton rollingButton;
//    /**
//     * 快手上下滚动滑动功能
//     */
//    private final JButton btnSlideUpAndDown;

    // private final JButton btnSlideUpAndDown2;

    private final JButton btnStop;
    /**
     * 通用面板输出功能
     */
    private JLabel output2;
    private PythonCloseableRun pyRun;
    private Thread pyThread;

    /**
     * 初始化通用面板
     *
     * @param timePanels
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


        readButton.addActionListener(new ReadButtonActionListener(timePanels));

        browseButton.addActionListener(new BrowseButtonActionListener(timePanels));
        // 等待后返回按钮
        waitButton.addActionListener(new WaitButtonActionListener(timePanels));
        // 刷视频按钮
        videoButton.addActionListener(new VideoButtonActionListener(timePanels));
        // 逛街按钮
        shoppingButton.addActionListener(new ShoppingButtonActionListener(timePanels));

        btnStop = initBtnStop();

        JButton btnPy = new JButton("P");
        btnPy.setToolTipText("图片识别");
        btnPy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String msg = "";
                String name = AdbTools.getInstance().getDevice().getName();

                System.out.println("name = " + name);
                if (name.endsWith("+")) {

                    name = name.substring(0, name.length() - 1);
                }
                System.out.println("name = " + name);

                String packageName = AdbGetPackage.getTopPackageName(AdbTools.getInstance().getDevice().getSerial());
                System.out.println("packageName = " + packageName);

                String property = AdbToolsProperties.moneyApkPro.getProperty(packageName);
                System.out.println("property = " + property);
                if (!property.equals(packageName)) {

                    String pinyin = PinyinUtils.convertToPinyinWithCapitalizedFirstLetter(property);

//                    String pyPath = "AdbToolsPythons"+"\\"+name+"\\"+property+"\\1.py";
                    String pyPath = "AdbToolsPythons" + "\\" + name + "\\" + pinyin + "\\1.py";
                    System.out.println("pyPath = " + pyPath);

                    FileCreator.createFile(pyPath);
//                    new File

                    if (pyRun == null) {
                        pyRun = new PythonCloseableRun(msg, pyPath, output2);
                    }
                    //如果线程不存在，或者线程存在，但是线程死掉了
                    if (pyThread == null || !pyThread.isAlive()) {
                        //重新创建线程
                        pyThread = new Thread(pyRun);
                        pyThread.start();
                    }

                }


            }
        });
        JButton btnKPy = new JButton("KP");
        btnKPy.setToolTipText("杀死后台的Py线程");
        btnKPy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pyRun.stop();
            }
        });

        // 添加到面板中
        universalPanel.add(readButton);
        universalPanel.add(browseButton);
        universalPanel.add(videoButton);
        universalPanel.add(shoppingButton);
        universalPanel.add(waitButton);
        universalPanel.add(btnStop);
        universalPanel.add(btnPy);
        universalPanel.add(btnKPy);
        universalPanel.add(output2);

        AbstractButtons.setMarginInButtonJPanel(universalPanel, 1);
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

    private JButton initBtnStop() {
        JButton stopBtn = new JButton("停止");
        stopBtn.setToolTipText("停止所有后台线程,刷新界面");
        stopBtn.addActionListener(new StopBtnAcListener2());
        return stopBtn;
    }

    public JButton getBtnStop() {
        return btnStop;
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
