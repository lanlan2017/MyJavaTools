package adbs.main.ui.jpanels.auto;

import adbs.cmd.AdbCommands;
import adbs.main.AdbTools;
import adbs.main.run.AdbGetPackage;
import adbs.main.ui.config.FlowLayouts;
import adbs.main.ui.jpanels.auto.runnable.*;
import adbs.main.ui.jpanels.timeauto2.TimingPanels2;
import adbs.main.ui.jpanels.universal.runnable.CloseableRunnable;
import adbs.model.Device;
import adbs.tools.thread.ThreadSleep;
import tools.swing.button.AbstractButtons;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * 自动控制面板
 */
public class AutoPanels implements CoinsType {
    /**
     * 主面板
     */
    private final JPanel autoJPanel;

    /**
     * 选择到底是赚金币还是收金币
     */
    private final JComboBox<String> jcbXuanZhe;

    /**
     * 自动赚金币选项
     */
    private final JComboBox<String> jcbZhuan;

    /**
     * 自动收金币选项
     */
    private final JComboBox<String> jcbShou;


    /**
     * 收金币确认按钮
     */
    private final JButton btnShouOk;


    /**
     * 赚金币确认按钮
     */
    private final JButton btnZhuanOk;

    /**
     * 停止按钮
     */
    private final JButton btnStop;
    /**
     * 标记
     */
    private boolean stop;
    /**
     * 线程体
     */
    // private DefaultNewWebActivityCloseRunnable closeRun;
    private CloseableRunnable closeRun;


    public JPanel getAutoJPanel() {
        return autoJPanel;
    }

    public AutoPanels() {
        autoJPanel = new JPanel();
        autoJPanel.setLayout(FlowLayouts.flowLayoutLeft);
        btnStop = initBtnStop();


        jcbXuanZhe = initJCBXuanZhe();

        jcbShou = initJCBShou();
        btnShouOk = initBtnShouOk();


        jcbZhuan = initJCBZhuan();
        btnZhuanOk = initBtnZhuanOk();

        autoJPanel.add(btnStop);
        autoJPanel.add(jcbXuanZhe);

        autoJPanel.add(jcbShou);
        autoJPanel.add(jcbZhuan);

        autoJPanel.add(btnShouOk);
        autoJPanel.add(btnZhuanOk);
        autoJPanel.setVisible(false);
        AbstractButtons.setMarginInButtonJPanel(autoJPanel, 0);

        // defaultSelected();
    }

    public void defaultSelected() {
        jcbXuanZhe.setSelectedIndex(0);
        jcbShou.setSelectedIndex(0);
        jcbZhuan.setSelectedIndex(0);
    }


    private JButton initBtnStop() {
        final JButton btnStop;
        btnStop = new JButton("停止");
        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (closeRun != null) {
                    closeRun.stop();
                }

                String serial = AdbTools.getInstance().getDevice().getSerial();
                // // 显示导航栏
                String showNB = "adb -s " + serial + " shell settings put global policy_control null";
                AdbCommands.runAbdCmd(showNB);

                AdbTools adbTools = AdbTools.getInstance();
                JCheckBox jCheckBox = adbTools.getCheckJPanels().getAutoCheckBox();
                if (jCheckBox.isSelected()) {
                    jCheckBox.doClick();
                }
                adbTools.getFrame().pack();

            }
        });

        return btnStop;
    }


    private JComboBox<String> initJCBXuanZhe() {
        final JComboBox<String> comboBox;
        comboBox = new JComboBox<>();
        comboBox.addItem(Zhuan);
        comboBox.addItem(Shou);

        // comboBox.setFont(Fonts.Cascadia_BOLD_12);
        // comboBox.setPrototypeDisplayValue(Zhuan);

        Dimension preferredSize = btnStop.getPreferredSize();
        int height = (int) preferredSize.getHeight();
        // int width = (int) preferredSize.getWidth();
        // int width = height * 2+1;
        // int width = height * 2+2;
        int width = height * 2 + 3;
        // int width = height * 2+4;
        // int width = height * 2+5;


        System.out.println("width = " + width);
        System.out.println("height = " + height);

        comboBox.setPreferredSize(new Dimension(width, height));


        Dimension preferredSize1 = comboBox.getPreferredSize();
        System.out.println("preferredSize1 = " + preferredSize1);


        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ComboBoxModel<String> model = comboBox.getModel();

                String elementAt = model.getElementAt(comboBox.getSelectedIndex());

                switch (elementAt) {
                    case Zhuan:
                        jcbZhuan.setVisible(true);
                        btnZhuanOk.setVisible(true);
                        jcbShou.setVisible(false);
                        btnShouOk.setVisible(false);
                        break;
                    case Shou:
                        jcbShou.setVisible(true);
                        btnShouOk.setVisible(true);
                        jcbZhuan.setVisible(false);
                        btnZhuanOk.setVisible(false);
                        break;

                }

                AdbTools.getInstance().getFrame().pack();
            }
        });
        // comboBox.setSelectedIndex(0);
        return comboBox;
    }

    private JComboBox<String> initJCBZhuan() {
        JComboBox<String> jcbZhuanJinBi = new JComboBox<>();
        jcbZhuanJinBi.addItem("茄子免费小说");
        jcbZhuanJinBi.addItem("熊猫免费小说");
        jcbZhuanJinBi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ComboBoxModel<String> model = jcbZhuanJinBi.getModel();
                String elementAt = model.getElementAt(jcbZhuanJinBi.getSelectedIndex());
                btnZhuanOk.setText(elementAt);
                AdbTools.getInstance().getFrame().pack();
            }
        });
        // jcbZhuanJinBi.setSelectedIndex(0);
        jcbZhuanJinBi.setVisible(false);
        return jcbZhuanJinBi;
    }


    private JComboBox<String> initJCBShou() {
        final JComboBox<String> comboBox;
        comboBox = new JComboBox<>();
        comboBox.addItem(ReadCoins);
        comboBox.addItem(AudioCoins);
        // comboBox.addItem(SuDuMianFeiXiaoShuo);

        comboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                // int stateChange = e.getStateChange();
                ComboBoxModel<String> model = comboBox.getModel();
                String element = model.getElementAt(comboBox.getSelectedIndex());
                btnShouOk.setText(element);

            }
        });
        // comboBox.setSelectedIndex(0);
        comboBox.setVisible(false);
        return comboBox;
    }


    private JButton initBtnZhuanOk() {
        JButton btnZhuanOk = new JButton();

        btnZhuanOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = btnZhuanOk.getText();
                Device device = AdbTools.getInstance().getDevice();

                switch (text) {
                    case "茄子免费小说":

                        String deviceName = device.getName();
                        if (deviceName.equals("mz6")) {

                            String actName;
                            // 打开茄子免费小说
                            openQieZiApp(device);
                            // 跳过广告
                            skipAd(device);

                            // // 点击福利界面
                            // intoJinBiJieMian(device);
                            //
                            // // 点击关闭按钮
                            // clickCloseBtn(device);


                            // clickReadBtn(device);

                            readingRecommendedBooks(device);

                        } else if (deviceName.equals("op")) {

                            // // String openAct = "adb -s " + device.getSerial() + " shell am start -n com.qz.freader/com.kmxs.reader.home.ui.HomeActivity";
                            String openAct = "adb -s " + device.getSerial() + " shell am start -n com.qz.freader/com.kmxs.reader.home.ui.HomeActivity";
                            //com.qz.freader/com.km.app.home.view.LoadingActivity
                            //adb -s 95AQACQJCMZPA shell am start -n com.qz.freader/com.km.app.home.view.LoadingActivity
                            // String openAct = "adb -s " + device.getSerial() + " shell am start -n com.qz.freader/com.km.app.home.view.LoadingActivity";

                            // com.qz.freader/com.kmxs.reader.home.ui.HomeActivity
                            // adb -s 75aed56d shell am start -n com.qz.freader/com.kmxs.reader.home.ui.HomeActivity
                            // com.qz.freader/com.kmxs.reader.home.ui.HomeActivity
                            // adb -s 75aed56d shell am start -n com.qz.freader/com.kmxs.reader.home.ui.HomeActivity


                            AdbCommands.runAbdCmd(openAct);
                            readingRecommendedBooks(device);


                        }


                        break;
                }
            }
        });
        btnZhuanOk.setVisible(false);

        return btnZhuanOk;
    }



    private JButton initBtnShouOk() {
        final JButton button;
        button = new JButton("确定");
        button.setText(ReadCoins);
        button.setVisible(false);
        // button.setToolTipText("连续多次点击");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stop = false;
                Device device = AdbTools.getInstance().getDevice();
                String serial = device.getSerial();
                // 隐藏导航栏
                //
                String hideNavigationBar = "adb -s " + serial + " shell settings put global policy_control immersive.navigation=*";
                AdbCommands.runAbdCmd(hideNavigationBar);
                // // 显示导航栏
                // String showNB = "adb -s " + serial + " shell settings put global policy_control null";

                String buttonText = button.getText();
                switch (buttonText) {
                    case ReadCoins:
                        // collecionCoins();
                        System.out.println(ReadCoins);
                        readCoinsCloseRun(device);
                        break;
                    case AudioCoins:
                        System.out.println(AudioCoins);
                        audioCoinsCloseRun(device);
                        break;
                }
            }


            private void readCoinsCloseRun(Device device) {
                String actShortName = AdbGetPackage.getActShortName();
                switch (actShortName) {
                    case "com.kmxs.reader.webview.ui.DefaultNewWebActivity":
                        readClose(device);
                        break;
                    default:
                        System.out.println("像素不对");
                        break;
                }

            }

            private void audioCoinsCloseRun(Device device) {
                String actShortName = AdbGetPackage.getActShortName();
                switch (actShortName) {
                    case "com.kmxs.reader.webview.ui.DefaultNewWebActivity":
                        closeAudioDefaultNewWebActivity(device);
                        break;

                    default:
                        System.out.println("听书金币，还没匹配当前的activity:" + actShortName);
                        break;

                }

            }
        });
        return button;
    }

    private void readingRecommendedBooks(Device device) {
        // 点击书架界面
        ScreenPositionRatio bookshelf = new ScreenPositionRatio(0.09907407407407408, 0.95);
        // AdbTap.tap_wait(device, bookshelf, 3);
        AdbTap.tap_wait(device, bookshelf, 5);
        AdbTap.tap_wait(device, bookshelf, 5);
        // 点击今日推荐
        ScreenPositionRatio recommendedToday = new ScreenPositionRatio(0.5425925925925926, 0.24739583333333334);
        // AdbTap.tap_wait(device, recommendedToday, 5);
        AdbTap.tap_wait(device, recommendedToday, 5);

        ScreenPositionRatio bookTuiJian1 = new ScreenPositionRatio(0.6314814814814815, 0.4984375);
        // AdbTap.tap_wait(device, bookTuiJian1, 4);
        AdbTap.tap_wait(device, bookTuiJian1, 8);

        // 阅读并等待
        TimingPanels2 timingPanels2 = AdbTools.getInstance().getTimingPanels2();
        timingPanels2.getjComboBox().setSelectedIndex(1);
        timingPanels2.getBtn3HMore().doClick();
    }

    private void clickReadBtn(Device device) {
        // ScreenPositionRatio readBtn = new ScreenPositionRatio(0.8314814814814815, 0.6151041666666667);
        ScreenPositionRatio readBtn = new ScreenPositionRatio(0.825, 0.5729166666666666);
        AdbTap.tap_wait(device, readBtn, 1);
        // AdbCommands.returnBtn(device);
        printActName();
        ThreadSleep.seconds(4);
    }

    // private void tap_wait(Device device, ScreenPositionRatio readBtn, int i) {
    //     AdbTap.tap(device, readBtn);
    //     ThreadSleep.seconds(i);
    // }

    private void clickCloseBtn(Device device) {
        String actName;
        //
        // String actName = AdbGetPackage.getActName();
        // System.out.println("actName = " + actName);
        // // actName.equals()
        //
        ScreenPositionRatio closeBtn = new ScreenPositionRatio(0.8148148148148148, 0.4265625);
        AdbTap.tap_wait(device, closeBtn, 1);
        //
        actName = AdbGetPackage.getActName();
        System.out.println("actName = " + actName);
        if (actName.endsWith("com.kmxs.reader.webview.ui.DefaultNewWebActivity")) {
            AdbCommands.returnBtn(device);
            ThreadSleep.seconds(3);
        }
    }

    private void intoJinBiJieMian(Device device) {
        // ThreadSleep.seconds(2);
        // ThreadSleep.seconds(3);
        // ThreadSleep.seconds(4);
        // ThreadSleep.seconds(6);
        // skipAd(device);


        //
        // // 点击金币界面
        // // ScreenPositionRatio goldCoinInterface = new ScreenPositionRatio(0.4935185185185185, 0.8796875);
        // // ScreenPositionRatio goldCoinInterface = new ScreenPositionRatio(0.5, 0.8833333333333333);
        // ScreenPositionRatio goldCoinInterface = new ScreenPositionRatio (0.5,0.9546875);
        ScreenPositionRatio goldCoinInterface = new ScreenPositionRatio(0.5, 0.959375);
        ThreadSleep.seconds(1);
        AdbTap.tap_wait(device, goldCoinInterface, 1);
        AdbTap.tap(device, goldCoinInterface);
    }

    private void skipAd(Device device) {
        // 等待广告加载
        ThreadSleep.seconds(5);
        // // 点击跳过广告按钮
        // ScreenPositionRatio skipAd = new ScreenPositionRatio(0.8638888888888889, 0.06822916666666666);
        ScreenPositionRatio skipAd = new ScreenPositionRatio(0.8583333333333333, 0.06666666666666667);
        AdbTap.tap(device, skipAd);
    }

    /**
     * 手动打开茄子免费小说
     *
     * @param device
     */
    private void openQieZiApp(Device device) {
        String actName;
        hideNav_home_wait(device);

        // 从右向左滑动一次
        AdbCommands.swipeRight2LeftOnTop(device);
        ThreadSleep.seconds(1);
        ScreenPositionRatio app_0_0 = new ScreenPositionRatio(0.14074074074074075, 0.12135416666666667);
        AdbTap.tap(device, app_0_0);
    }

    private void hideNav_home_wait(Device device) {
        hideNavigationBar_wait(device);
        home_wait(device);
    }

    private void home_wait(Device device) {
        AdbCommands.home(device);
        ThreadSleep.seconds(1);
    }

    private void hideNavigationBar_wait(Device device) {
        // 隐藏导航栏
        // hideNavigationBar
        AdbCommands.hideNavigationBar(device);
        ThreadSleep.seconds(1);
    }

    private void printActName() {
        String actName;
        actName = AdbGetPackage.getActName();
        System.out.println("actName = " + actName);
    }

    // private void returnBtn(Device device) {
    //     String code = "adb -s " + device.getSerial() + " shell input keyevent BACK";
    //     // String code = "adb -s " + device.getSerial() + " shell input keyevent 4";
    //     AdbCommands.runAbdCmd(code);
    // }


    private void closeAudioDefaultNewWebActivity(Device device) {
        int width = device.getWidth();
        int height = device.getHeight();

        if (width == 1080) {
            if (height == 2160) {
                System.out.println("听书2160=" + height);

                ScreenPositionRatio closeBtn = new ScreenPositionRatio(0.812962962962963, 0.4361111111111111);
                ScreenPositionRatio audioBtn = new ScreenPositionRatio(0.825, 0.7662037037037037);
                tapCloseTapCoins(device, closeBtn, audioBtn);

            } else if (height == 1920) {
                System.out.println("1920=" + height);
                // tapCloseTapCoins(device, audioCoinsBtn_1080_2160, audioCoinsBtn_1080_2160);
                ScreenPositionRatio audioBtn = new ScreenPositionRatio(0.7592592592592593, 0.8635416666666667);
                ScreenPositionRatio closeBtn = new ScreenPositionRatio(0.8185185185185185, 0.4265625);

                tapCloseTapCoins(device, closeBtn, audioBtn);

            }

        }
    }

    private void readClose(Device device) {

        int height = device.getHeight();
        int width = device.getWidth();
        System.out.println("height = " + height);
        System.out.println("width = " + width);

        if (width == 1080) {
            if (height == 2160) {
                System.out.println("2160=" + height);
                ScreenPositionRatio closeBtn_1080_2160 = new ScreenPositionRatio(0.8472222222222222, 0.45555555555555555);
                ScreenPositionRatio readBtn_1080_2160 = new ScreenPositionRatio(0.8305555555555556, 0.5548611111111111);

                tapCloseTapCoins(device, closeBtn_1080_2160, readBtn_1080_2160);
            } else if (height == 1920) {
                System.out.println("1920=" + height);
                // closeRun.setBtnClose(Ratios.qieZiBtnClose);
                // closeRun.setBtnCoin(Ratios.qieZiReadCoin);
                ScreenPositionRatio closeBtn = new ScreenPositionRatio(0.8148148148148148, 0.4265625);
                ScreenPositionRatio readBtn = new ScreenPositionRatio(0.8314814814814815, 0.6151041666666667);
                tapCloseTapCoins(device, closeBtn, readBtn);

            }
        }
    }


    private void tapCloseTapCoins(Device device, ScreenPositionRatio closeBtn, ScreenPositionRatio coinsBtn) {
        QieZiReadCoinCloseRun qieZiReadCoinCloseRun = QieZiReadCoinCloseRun.getInstance();
        closeRun = qieZiReadCoinCloseRun;
        // closeRun.setDevice(device);
        // closeRun.setBtnClose(closeBtn);
        // closeRun.setBtnCoin(coinsBtn);
        qieZiReadCoinCloseRun.setDevice(device);
        qieZiReadCoinCloseRun.setBtnClose(closeBtn);
        qieZiReadCoinCloseRun.setBtnCoin(coinsBtn);
        new Thread(closeRun).start();
    }
}
