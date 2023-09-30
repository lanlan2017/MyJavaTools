package adbs.main.ui.jpanels.auto;

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
        btnStop.addActionListener(new BtnActionListener() {
            @Override
            public void action(ActionEvent e) {
                if (closeRun != null) {
                    closeRun.stop();
                }
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
            //
            // private void collecionCoins() {
            //     Device device = AdbTools.getInstance().getDevice();
            //     String actName = AdbGetPackage.getActName();
            //     System.out.println("actName = " + actName);
            //
            //     // final String actQieZi = "com.qz.freader/com.kmxs.reader.webview.ui.DefaultNewWebActivity";
            //     // // final String actXiongMao = "com.xm.freader/com.kmxs.reader.webview.ui.DefaultNewWebActivity";
            //     // final String actXiongMao = "com.xm.freader/" + flag0;
            //     // final String actXiongMao2 = "com.xm.freader/" + flag1;
            //
            //     String flag0 = "com.kmxs.reader.webview.ui.DefaultNewWebActivity";
            //     String flag1 = "com.kmxs.reader.home.ui.HomeActivity";
            //     if (actName.contains(flag0)) {
            //         tap(device);
            //     } else if (actName.contains(flag1)) {
            //         tap(device);
            //     }
            //
            //
            // }


            private void readCoinsCloseRun(Device device) {
                String actName = AdbGetPackage.getActName();
                System.out.println("actName = " + actName);
                String actShortName = "";
                if (actName.contains("/")) {
                    actShortName = actName.substring(actName.indexOf("/") + 1);
                    System.out.println("actShortName = " + actShortName);
                }

                int height = device.getHeight();
                int width = device.getWidth();
                System.out.println("height = " + height);
                System.out.println("width = " + width);

                switch (actShortName) {
                    case "com.kmxs.reader.webview.ui.DefaultNewWebActivity":
                        if (width == 1080) {
                            if (height == 2160) {
                                System.out.println("2160=" + height);
                                ScreenPositionRatio closeBtn_1080_2160 = new ScreenPositionRatio(0.8472222222222222, 0.45555555555555555);
                                ScreenPositionRatio readBtn_1080_2160 = new ScreenPositionRatio(0.8305555555555556, 0.5548611111111111);
                                tapCloseTapRead(device, closeBtn_1080_2160, readBtn_1080_2160);
                            } else if (height == 1920) {
                                System.out.println("1920=" + height);
                                // closeRun.setBtnClose(Ratios.qieZiBtnClose);
                                // closeRun.setBtnCoin(Ratios.qieZiReadCoin);
                                tapCloseTapRead(device, Ratios.qieZiBtnClose, Ratios.qieZiReadCoin);
                            }
                        }
                        break;

                    default:

                        System.out.println("像素不对");
                        break;

                }


                // closeRun.setBtnClose(Ratios.qieZiBtnClose);
                // closeRun.setBtnCoin(Ratios.qieZiReadCoin);
                // new Thread(closeRun).start();
            }

            private void audioCoinsCloseRun(Device device) {
                closeRun = QieZiAudioCoinCloseRun.getInstance();
                closeRun.setDevice(device);
                closeRun.setBtnClose(Ratios.qieZiBtnClose);
                closeRun.setBtnCoin(Ratios.qieZiAudioCoin);
                System.out.println("closeableRunnable = " + closeRun);
                new Thread(closeRun).start();
            }

            private void adbTap_Wait(Device device, ScreenPositionRatio closeButton) {
                AdbTap.tap(device, closeButton);
                ThreadSleep.seconds(3);
            }

        });

        return button;
    }

    private void tapCloseTapRead(Device device, ScreenPositionRatio closeBtn_1080_2160, ScreenPositionRatio readBtn_1080_2160) {
        closeRun = QieZiReadCoinCloseRun.getInstance();
        closeRun.setDevice(device);
        // closeRun.setBtnClose(closeBtn_1080_2160);
        // closeRun.setBtnCoin(Ratios.qieZiReadCoin);
        closeRun.setBtnClose(closeBtn_1080_2160);
        closeRun.setBtnCoin(readBtn_1080_2160);
        new Thread(closeRun).start();
    }
}
