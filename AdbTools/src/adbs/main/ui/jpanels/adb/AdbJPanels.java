package adbs.main.ui.jpanels.adb;

import adbs.cmd.AdbCommands;
import adbs.main.AdbTools;
import adbs.main.run.AdbGetPackage;
import adbs.main.ui.config.FlowLayouts;
import adbs.main.ui.config.Fonts;
import adbs.main.ui.jpanels.adb.listener.*;
import adbs.main.ui.jpanels.scrcpy.OpenApp;
import adbs.model.Device;
import config.AdbToolsProperties;
import tools.config.properties.PropertiesTools;
import tools.copy.SystemClipboard;
import tools.swing.button.AbstractButtons;
import tools.swing.dialog.DialogFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * adb命令相关面板
 */
public class AdbJPanels {

    private final JPanel adbJPanel;

    /**
     * 返回键
     */
    private final JButton btnReturn;
    /**
     * home键
     */
    private final JButton btnHome;
    /**
     * 任务视图
     */
    private final JButton btnTask;
    /**
     * 重启
     */
    private final JButton btnReboot;
    /**
     * 关机
     */
    private final JButton btnPowerOff;
    /**
     * 音量加一
     */
    private final JButton volumePlus;
    /**
     * 音量减一
     */
    private final JButton volumeMinus;
    /**
     * 静音
     */
    private final JButton volumeNone;
    /**
     * 展开或者收起状态栏
     */
    private final JButton btnStatusbar;
    /**
     * 获取顶部应用的全限定Activity名称
     */
    private final JButton btnAct;
    /**
     * 打开手机管家
     */
    private final JButton btnMobileButler;
    /**
     * 打开系统设置
     */
    private final JButton btnSetting;
    /**
     * 打开WiFi设置
     */
    private final JButton btnWiFiSettings;
    /**
     * 电源按钮
     */
    private final JButton btnPower;

    public AdbJPanels() {
        adbJPanel = new JPanel();
        // adbJPanel.setBorder(new TitledBorder(""));
        adbJPanel.setLayout(FlowLayouts.flowLayoutLeft);

        // PropertiesTools propertiesTools = new PropertiesTools("AdbTools.properties");
        PropertiesTools propertiesTools = AdbToolsProperties.propertiesTools;

        btnReturn = initBtnRetun();

        btnHome = initBtnHome();
        btnTask = initBtnTask();

        JPanel navigationKeyJPanel = new JPanel();
        navigationKeyJPanel.setLayout(FlowLayouts.flowLayoutLeft);
        navigationKeyJPanel.add(btnReturn);
        navigationKeyJPanel.add(btnHome);
        navigationKeyJPanel.add(btnTask);

        volumeNone = initbtnVolumeNone();

        volumePlus = intBtnVolumePlus();

        volumeMinus = initBtnVolumeMinus();

        // 音量面板
        JPanel volumeJPanel = new JPanel();
        volumeJPanel.setLayout(FlowLayouts.flowLayoutLeft);
        volumeJPanel.add(volumePlus);
        volumeJPanel.add(volumeMinus);
        volumeJPanel.add(volumeNone);


        btnStatusbar = initBtnStatusbasShow();
        // statusbarHide = initBtnStatusbasHide();
        btnReboot = intBtnReboot();
        btnPowerOff = initBntPowerOff();

        // 按下手机电源键
        btnPower = initBtnPower();
        // 音量面板
        JPanel statusbarJPanel = new JPanel();
        statusbarJPanel.setLayout(FlowLayouts.flowLayoutLeft);


        volumeJPanel.add(btnStatusbar);
        // volumeJPanel.add(statusbarHide);
        btnAct = intBtnAct();
        btnMobileButler = initBtnMobileButler();
        btnWiFiSettings = initBtnWiFiSettings();
        // 打开系统设置
        btnSetting = initBtnSetting();

        adbJPanel.add(btnReturn);
        adbJPanel.add(btnHome);
        adbJPanel.add(btnTask);
        // adbJPanel.add(volumePlus);
        // adbJPanel.add(volumeMinus);
        // adbJPanel.add(volumeNone);
        adbJPanel.add(volumeJPanel);

        // adbJPanel.add(statusbarShow);
        // adbJPanel.add(statusbarHide);
        adbJPanel.add(statusbarJPanel);

        adbJPanel.add(btnReboot);
        adbJPanel.add(btnPowerOff);
        // adbJPanel.add(stopBtn);

        adbJPanel.add(btnPower);
        adbJPanel.add(btnAct);
        adbJPanel.add(btnMobileButler);
        adbJPanel.add(btnSetting);
        adbJPanel.add(btnWiFiSettings);


        // AbstractButtons.setMarginInButtonJPanel(adbJPanel);
        // AbstractButtons.setMarginInButtonJPanel(adbJPanel, -1);
        AbstractButtons.setMargin_2_InButtonJPanel(adbJPanel, 0);
        AbstractButtons.setMargin_2_InButtonJPanel(volumeJPanel, -1);
        AbstractButtons.setMargin_2_InButtonJPanel(statusbarJPanel, -1);
        // 设置的内切
        AbstractButtons.setMargin_2_InButtonJPanel(navigationKeyJPanel, 0);
    }

    private JButton initBtnSetting() {
        final JButton btnSetting;
        btnSetting = new JButton("设");
        btnSetting.setToolTipText("打开设置");
        btnSetting.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = "设置";
                String message = "打开设置？";

                ActionListener actionListenerOk = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        AdbCommands.openSetting(AdbTools.getInstance().getDevice());
                    }
                };

                AdbTools.getInstance().showDialogOk(title, message, actionListenerOk);

            }
        });
        return btnSetting;
    }

    /**
     * 初始化电源按钮
     *
     * @return
     */
    private JButton initBtnPower() {
        final JButton btnPower;
        btnPower = new JButton("电");
        btnPower.setToolTipText("按下电源键");
        btnPower.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdbTools.getInstance().showDialogOk("电源键", "按下手机电源键？", new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // AdbCommands.
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                Device device = AdbTools.getInstance().getDevice();
                                // AdbCommands.batteryReset(device.getSerial());
                                // 按下开机按键
                                AdbCommands.powerBtn(device);

                            }
                        }).start();

                    }
                });

            }
        });
        return btnPower;
    }

    private JButton intBtnAct() {
        final JButton btnGetAct;
        // btnGetAct = new JButton("ACT");
        // btnGetAct = new JButton("Act");
        btnGetAct = new JButton("A");
        // btnGetAct = new JButton("a");
        btnGetAct.setToolTipText("获取顶部APP的activity");
        btnGetAct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = "获取顶部APP的Activity";
                DialogFactory.showDialogOk(AdbTools.getInstance().getFrame(), "ACT", message, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String actName = AdbGetPackage.getActName();
                        System.out.println();
                        System.out.println("actName = " + actName);
                        // System.out.println();
                        String serial = AdbTools.getInstance().getDevice().getSerial();
                        // String openAct = "adb -s " + serial + " shell am start -n " + actName;
                        // System.out.println("openAct = " + openAct);
                        // System.out.println();
                        // String openActJavaStr = "String openAct = \"adb -s \" + device.getSerial() + \" shell am start -n " + actName + "\";";
                        // System.out.println("openActJavaStr = " + openActJavaStr);
                        // System.out.println();
                        // String clipOut = "// " + actName + "\n// " + openAct + "\n// " + openActJavaStr;
                        // SystemClipboard.setSysClipboardText(clipOut);
                        SystemClipboard.setSysClipboardText(actName);
                    }
                });
            }
        });
        return btnGetAct;
    }

    private JButton initBtnMobileButler() {
        final JButton btnOpenMobileButlerApp;
        // openMobileButlerApp = new JButton("管家");
        btnOpenMobileButlerApp = new JButton("G");
        // btnOpenMobileButlerApp = new JButton("GJ");
        btnOpenMobileButlerApp.setToolTipText("打开手机管家APP");
        btnOpenMobileButlerApp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdbTools.getInstance().showDialogOk("管家", "打开手机管家?", new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        OpenApp.openGuanJiaApp();
                    }
                });
            }
        });
        return btnOpenMobileButlerApp;
    }

    private JButton initBtnWiFiSettings() {
        final JButton btnWiFiSettings;
        // btnWiFiSettings = new JButton("WiFi");
        btnWiFiSettings = new JButton("W");
        // btnWiFiSettings = new JButton("w");
        // btnWiFiSettings = new JButton("WF");
        btnWiFiSettings.setToolTipText("打开WiFi设置界面");
        btnWiFiSettings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdbTools.getInstance().showDialogOk("WiFi", "打开WiFi设置界面？", new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        OpenApp.openWiFiSetting();
                    }
                });
            }
        });
        return btnWiFiSettings;
    }

    private JButton initBtnStatusbasShow() {
        String showFlag = "↓";
        // String hideFlag = "↑";
        JButton statusbarShow = new JButton(showFlag);
        statusbarShow.setToolTipText("展开状态栏");
        statusbarShow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // String text = statusbarShow.getText();
                // if (text.equals(showFlag)) {
                // 获取选中的adb设备的序列号
                // String id = AdbTools.device.getId();
                String id = AdbTools.getInstance().getDevice().getSerial();
                // 拼接重启代码
                String adbCmd = "adb -s " + id + " shell service call statusbar 1";
                // System.out.println("adbCmd = " + adbCmd);
                // 启动cmd进程执行adb命令
                AdbCommands.runAbdCmd(adbCmd);
                // statusbarShow.setText(hideFlag);
                statusbarShow.setToolTipText("展开状态栏");
                // } else {
                // String id = AdbTools.getInstance().getDevice().getSerial();
                // // 拼接重启代码
                // String adbCmd = "adb -s " + id + " shell service call statusbar 2";
                // // System.out.println("adbCmd = " + adbCmd);
                // // 启动cmd进程执行adb命令
                // AdbCommands.runAbdCmd(adbCmd);
                // statusbarShow.setText(showFlag);
                // statusbarShow.setToolTipText("收起状态栏");
                // }
            }
        });
        return statusbarShow;
    }

    private JButton initBntPowerOff() {
        // 关机按钮
        // JButton powerOffBtn = new JButton("关机");
        JButton powerOffBtn = new JButton("关");
        powerOffBtn.setToolTipText("关机");
        // closeBtn.addActionListener(new RebootBtnAcListener(frame, "shell reboot -p"));
        // powerOffBtn.addActionListener(new RebootBtnAcListener(adbJPanel, "shell reboot -p"));
        powerOffBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdbTools.getInstance().showDialogOk("关机", "关机?", new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String serial = AdbTools.getInstance().getDevice().getSerial();
                        // 拼接重启代码
                        String adbCmd = "adb -s " + serial + " " + "shell reboot -p";
                        System.out.println("adbCmd = " + adbCmd);
                        // 启动cmd进程执行adb命令
                        AdbCommands.runAbdCmd(adbCmd);
                    }
                });
            }
        });

        return powerOffBtn;
    }

    private JButton intBtnReboot() {
        // 重启按钮
        // JButton rebootBtn = new JButton("重启");
        JButton rebootBtn = new JButton("重");
        rebootBtn.setToolTipText("重启手机");
        // rebootBtn.addActionListener(new RebootBtnAcListener(frame, "reboot"));
        // rebootBtn.addActionListener(new RebootBtnAcListener(adbJPanel, "reboot"));
        rebootBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdbTools.getInstance().showDialogOk("重启", "重启手机?", new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String serial = AdbTools.getInstance().getDevice().getSerial();

                        // 拼接重启代码
                        // String adbCmd = "adb -s " + serial + " " + code;
                        // 拼接重启代码
                        String adbCmd = "adb -s " + serial + " " + "reboot";
                        System.out.println("adbCmd = " + adbCmd);
                        // 启动cmd进程执行adb命令
                        AdbCommands.runAbdCmd(adbCmd);
                    }
                });
            }
        });
        return rebootBtn;
    }

    private JButton initBtnVolumeMinus() {
        JButton volumeMinus = new JButton("-");
        volumeMinus.setToolTipText("音量减一");
        // 音量减一键
        volumeMinus.addActionListener(new VolumeMinusBtnAcListener());
        return volumeMinus;
    }

    private JButton intBtnVolumePlus() {
        JButton volumePlus = new JButton("+");
        volumePlus.setToolTipText("音量加一");
        // 音量加一键
        volumePlus.addActionListener(new VolumePlusBtnAcListener());
        return volumePlus;
    }

    private JButton initbtnVolumeNone() {
        JButton volumeNone = new JButton("x");
        volumeNone.setToolTipText("静音");
        // 静音键
        volumeNone.addActionListener(new VolumeNoneBtnAcListener());
        return volumeNone;
    }

    private JButton initBtnTask() {
        // 任务键按钮
        // ◁ ○ □
        // taskBtn = new JButton(new ImageIcon(Buttons.class.getClassLoader().getResource("空框.png")));
        // taskBtn = new JButton("□");
        JButton taskBtn = new JButton("t");
        // taskBtn.setFont(Fonts.Consolas_BOLD_14);
        taskBtn.setFont(Fonts.Consolas_BOLD_12);
        taskBtn.setToolTipText("任务键");
        // 任务管理键
        taskBtn.addActionListener(new TaskManageBtnAcListener());
        return taskBtn;
    }

    private JButton initBtnHome() {
        // home键按钮
        JButton homeBtn = new JButton("o");
        homeBtn.setFont(Fonts.Consolas_BOLD_12);
        homeBtn.setToolTipText("home键");
        homeBtn.addActionListener(new HomeBtnAcListener());
        return homeBtn;
    }

    private JButton initBtnRetun() {
        // ◁ ○ □
        JButton returnBtn = new JButton("<");
        returnBtn.setToolTipText("返回键");
        // returnBtn.setFont(Fonts.Consolas_BOLD_14);
        returnBtn.setFont(Fonts.Consolas_BOLD_12);
        // 返回键
        returnBtn.addActionListener(new ReturnBtnAcListener());
        return returnBtn;
    }

    public JPanel getAdbJPanel() {
        return adbJPanel;
    }

    public JButton getBtnReturn() {
        return btnReturn;
    }

    public JButton getBtnTask() {
        return btnTask;
    }

    public JButton getBtnMobileButler() {
        return btnMobileButler;
    }
}
