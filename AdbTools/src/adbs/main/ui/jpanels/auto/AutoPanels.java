package adbs.main.ui.jpanels.auto;

import adbs.cmd.AdbCommands;
import adbs.main.AdbTools;
import adbs.main.run.AdbGetPackage;
import adbs.main.ui.config.FlowLayouts;
import adbs.main.ui.jpanels.auto.runnable.*;
import adbs.main.ui.jpanels.tools.BtnActionListener;
import adbs.model.Device;
import adbs.tools.thread.ThreadSleep;
import tools.swing.button.AbstractButtons;

import javax.swing.*;
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

    private final JComboBox<String> comboBox;
    /**
     *
     */
    private final JButton btnOk;
    private final JButton btnStop;
    private boolean stop;
    private DefaultNewWebActivityCloseRunnable closeRun;


    public JPanel getAutoJPanel() {
        return autoJPanel;
    }

    public AutoPanels() {
        autoJPanel = new JPanel();
        autoJPanel.setLayout(FlowLayouts.flowLayoutLeft);

        comboBox = initComboBox();
        btnOk = initBtnOk();
        btnOk.setText(ReadCoins);
        btnStop = initBtnStop();

        autoJPanel.add(btnStop);
        autoJPanel.add(comboBox);
        autoJPanel.add(btnOk);
        autoJPanel.setVisible(false);
        AbstractButtons.setMarginInButtonJPanel(autoJPanel, 0);
    }

    private JComboBox<String> initComboBox() {
        final JComboBox<String> comboBox;
        comboBox = new JComboBox<>();
        comboBox.addItem(ReadCoins);
        comboBox.addItem(AudioCoins);
        comboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                // int stateChange = e.getStateChange();
                ComboBoxModel<String> model = comboBox.getModel();
                String element = model.getElementAt(comboBox.getSelectedIndex());
                btnOk.setText(element);

            }
        });
        return comboBox;
    }

    private JButton initBtnStop() {
        final JButton btnStop;
        btnStop = new JButton("停止");
        // btnStop.setText(ReadCoins);
        // btnStop.addActionListener(new BtnActionListener() {
        //     @Override
        //     public void action(ActionEvent e) {
        //         if (closeRun != null) {
        //             closeRun.stop();
        //             // btnStop.setEnabled(true);
        //         }
        //         btnStop.setEnabled(true);
        //     }
        // });
        //

        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (closeRun != null) {
                    closeRun.stop();
                }
                autoJPanel.setVisible(false);
                AdbTools.getInstance().getFrame().pack();
            }
        });

        return btnStop;
    }

    private JButton initBtnOk() {
        final JButton button;
        button = new JButton("确定");
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

            private void adbTap_Wait(Device device, ScreenPositionRatio closeButton) {
                AdbTap.tap(device, closeButton);
                ThreadSleep.seconds(3);
            }

        });

        return button;
    }

    private void closeAudioDefaultNewWebActivity(Device device) {
        int width = device.getWidth();
        int height = device.getHeight();

        if (width == 1080) {
            if (height == 2160) {
                System.out.println("听书2160=" + height);
                // ScreenPositionRatio audioCloseBtn_1080_2160 = new ScreenPositionRatio(0.8111111111111111, 0.4337962962962963);
                // ScreenPositionRatio audioCoinsBtn_1080_2160 = new ScreenPositionRatio(0.8296296296296296, 0.7731481481481481);
                // tapCloseTapCoins(device, audioCoinsBtn_1080_2160, audioCoinsBtn_1080_2160);

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
                // tapCloseTapCoins(device, Ratios.qieZiBtnClose, Ratios.qieZiReadCoin);

            }
        }
    }


    private void tapCloseTapCoins(Device device, ScreenPositionRatio closeBtn, ScreenPositionRatio coinsBtn) {
        closeRun = QieZiReadCoinCloseRun.getInstance();
        closeRun.setDevice(device);
        // closeRun.setBtnClose(closeBtn);
        // closeRun.setBtnCoin(Ratios.qieZiReadCoin);
        closeRun.setBtnClose(closeBtn);
        closeRun.setBtnCoin(coinsBtn);
        new Thread(closeRun).start();
    }
}
