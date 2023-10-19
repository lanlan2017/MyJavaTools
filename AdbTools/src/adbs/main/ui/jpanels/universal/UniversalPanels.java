package adbs.main.ui.jpanels.universal;

import adbs.cmd.AdbCommands;
import adbs.cmd.CmdRun;
import adbs.cmd.RobotsDraw;
import adbs.main.AdbTools;
import adbs.main.ui.inout.listener.StopBtnAcListener2;
import adbs.main.ui.jpanels.time.TimePanels;
import adbs.main.ui.jpanels.tools.BtnActionListener;
import adbs.main.ui.jpanels.universal.listener.*;
import adbs.main.ui.config.FlowLayouts;
import adbs.main.ui.jpanels.universal.runnable.CloseableRunnable;
import adbs.main.ui.jpanels.universal.runnable.RoolBtnRunnable;
import adbs.model.Device;
import adbs.tools.thread.ThreadSleep;
import tools.copy.SystemClipboard;
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
    /**
     * 快手上下滚动滑动功能
     */
    private final JButton btnSlideUpAndDown;

    private final JButton btnSlideUpAndDown2;

    private final JButton btnStop;
    /**
     * 通用面板输出功能
     */
    private JLabel output2;

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
        readButton = new JButton("阅读");
        readButton.setToolTipText("阅读功能:连续点击屏幕右侧");

        browseButton = new JButton("浏览");
        browseButton.setToolTipText("连续在屏幕左侧从下向上滑动");

        waitButton = new JButton("等待");
        waitButton.setToolTipText("等待一定时间后提示");
        // videoButton = new JButton("刷视频");
        videoButton = new JButton("视频");
        videoButton.setToolTipText("等待随机秒数后，从下向上滑动一次");

        shoppingButton = new JButton("逛街");
        shoppingButton.setToolTipText("连续从下向上滑动三次，然后上下来回滑动");

        // rollingButton=new JButton("锁定");
        // rollingButton.setToolTipText("锁定鼠标左键");

        // output2 = new JLabel("输出2");
        output2 = new JLabel("");


        // readButton.addActionListener(new PyImgFindAcListener(ReadButtonRunnable.getInstance(), inout2));
        // readButton.addActionListener(new PyImgFindAcListener(ReadButtonRunnable.getInstance()));
        readButton.addActionListener(new ReadButtonActionListener(timePanels));

        browseButton.addActionListener(new BrowseButtonActionListener(timePanels));
        // 等待后返回按钮
        waitButton.addActionListener(new WaitButtonActionListener(timePanels));
        // 刷视频按钮
        videoButton.addActionListener(new VideoButtonActionListener(timePanels));
        // 逛街按钮
        shoppingButton.addActionListener(new ShoppingButtonActionListener(timePanels));

        btnSlideUpAndDown = getBtnSlideUpAndDown();

        btnSlideUpAndDown2 = intiBtnSlideUpDown2();

        btnStop = initBtnStop();

        // 添加到面板中
        universalPanel.add(readButton);
        universalPanel.add(browseButton);
        universalPanel.add(waitButton);
        universalPanel.add(videoButton);
        universalPanel.add(shoppingButton);
        // universalPanel.add(rollingButton);
        universalPanel.add(btnSlideUpAndDown);
        universalPanel.add(btnSlideUpAndDown2);
        universalPanel.add(btnStop);
        universalPanel.add(output2);

        AbstractButtons.setMarginInButtonJPanel(universalPanel, 1);
    }

    private JButton intiBtnSlideUpDown2() {
        final JButton btnSlideUpAndDown2;
        btnSlideUpAndDown2 = new JButton("滚");
        btnSlideUpAndDown2.setToolTipText("上下滚动");
        // btnSlideUpAndDown2.addActionListener(new ActionListener() {
        btnSlideUpAndDown2.addActionListener(new BtnActionListener() {
            @Override
            public void action(ActionEvent e) {
                upDowm();
            }
            //
            // @Override
            // public void actionPerformed(ActionEvent e) {
            //     upDowm();
            // }

            private void upDowm() {
                Device device = AdbTools.getInstance().getDevice();
                int width = device.getWidth();
                // int x = (int) (width * 0.5);
                int x = 8;
                int height = device.getHeight();

                // int yBottom = (int) (height * 0.8);
                // int yTop = (int) (height * 0.2);

                int yBottom = (int) (height * 0.8);
                int yTop = (int) (height * 0.2);

                // int yBottom = (int) (height * 0.75);
                // int yTop = (int) (height * 0.25);

                // int yBottom = (int) (height * 0.7);
                // int yTop = (int) (height * 0.3);

                // int yBottom = (int) (height * 0.65);
                // int yTop = (int) (height * 0.35);

                // int yBottom = (int) (height * 0.6);
                // int yTop = (int) (height * 0.4);

                // AdbCommands.swipeBotton2TopOnRightXiaoXiaoDe();
                // int millisecond = 10;
                // int millisecond = 20;
                // int millisecond = 30;
                // int millisecond = 40;
                // int millisecond = 50;
                // int millisecond = 60;
                // int millisecond = 70;
                int millisecond = 80;
                // int millisecond = 100;
                // int millisecond = 200;
                // int millisecond = 500;
                // int millisecond = 1000;
                // String bottomToTop = "adb -s " + device.getSerial() + " shell input swipe " + x + " " + yBottom + " " + x + " " + yTop + " " + millisecond;
                // String topToBottom = "adb -s " + device.getSerial() + " shell input swipe " + x + " " + yTop + " " + x + " " + yBottom + " " + millisecond;

                String bottomToTop = "adb -s " + device.getSerial() + " shell input touchscreen swipe " + x + " " + yBottom + " " + x + " " + yTop + " " + millisecond;
                // String midpointToBottom = "adb -s " + device.getSerial() + " shell input touchscreen swipe " + x + " " + yTop + " " + x + " " + yBottom + " " + millisecond;
                String topToBottom = "adb -s " + device.getSerial() + " shell input touchscreen swipe " + x + " " + yTop + " " + x + " " + yBottom + " " + millisecond;

                // pinJieDaiMa(bottomToTop, topToBottom);

                System.out.println("开始滚动");
                ThreadSleep.seconds(5);
                CloseableRunnable closeableRunnable = new CloseableRunnable() {
                    @Override
                    protected void beforeLoop() {
                        // super.beforeLoop();
                        System.out.println("启动线程");
                    }

                    // @Override
                    // protected void setMsg() {
                    //
                    // }

                    @Override
                    protected void loopBody() {
                        int times = 50;
                        for (int i = 0; i < times && !stop; i++) {
                            // AdbCommands.runAbdCmd(bottomToTop);
                            // AdbCommands.runAbdCmd(topToBottom);
                            // CmdRun.run(bottomToTop);
                            // CmdRun.run(topToBottom);
                            // System.out.println("i = " + i);
                            CmdRun.runOnly(bottomToTop);
                            CmdRun.runOnly(topToBottom);
                            // CmdRun.runOnly();
                        }
                        stop();
                    }

                    @Override
                    protected void afterLoop() {
                        super.afterLoop();
                        System.out.println("线程结束");
                        JOptionPane.showMessageDialog(AdbTools.getInstance().getContentPane(), "循环拖动已结束");
                    }
                };
                new Thread(closeableRunnable).start();
            }

            private void pinJieDaiMa(String bottomToTop, String topToBottom) {
                StringBuffer sb = new StringBuffer();

                // String commandSeparator = " & ";
                // String commandSeparator = " & call ";
                String commandSeparator = " & start ";
                // String commandSeparator = " & start /b ";

                int times = 50;
                for (int i = 0; i < times; i++) {
                    // sb += bottomToTop;
                    // sb += topToBottom;
                    if (i > 0) {
                        sb.append(commandSeparator);
                    }
                    sb.append(topToBottom).append(commandSeparator).append(bottomToTop);

                }
                sb.append(commandSeparator).append(bottomToTop);
                System.out.println(sb.toString());

                SystemClipboard.setSysClipboardText(sb.toString());
            }
        });
        return btnSlideUpAndDown2;
    }

    private JButton getBtnSlideUpAndDown() {
        final JButton btnSlideUpAndDown;
        btnSlideUpAndDown = new JButton("⇅");
        btnSlideUpAndDown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RobotsDraw.slideUpAndDown();
            }
        });
        return btnSlideUpAndDown;
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
}
