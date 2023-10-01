package adbs.main.ui.jpanels.scrcpy;

import adbs.cmd.AdbCommands;
import adbs.main.AdbTools;
import adbs.main.run.ForegroundAppRun;
import adbs.main.run.OppoR9ScrcpyRun;
import adbs.main.ui.config.FlowLayouts;
import adbs.main.ui.config.Fonts;
import adbs.main.ui.jpanels.scrcpy.listener.OpenButtonListener;
import adbs.main.ui.jpanels.adb.open.Taskkill;
import adbs.model.Device;
import config.AdbConnectPortProperties;
import tools.swing.button.AbstractButtons;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 投屏面板
 */
public class ScrcpyJPanels {

    private static String serialOld;
    private static String nameOld;
    private static String ip;
    /**
     * 面板
     */
    private final JPanel scrcpyJPanel;

    private final JLabel label;
    /**
     * 增加高度
     */
    private final JButton addBtn;
    /**
     * 减少高度
     */
    private final JButton btnDecrease;
    /**
     * 高度输入框
     */
    private final JTextField widthTextField;

    /**
     * 打开scrcpy.exe，限定高度
     */
    private final JButton btnOpenScrcpy;

    /**
     * 直接调用scrcpy.exe，不设置高度
     */
    private final JButton btnOpenScrcpyFull;
    /**
     * 切换网络调试，并打开scrcpy.exe
     */
    private final JButton btnSwitchNetworkDebug;
    /**
     * 杀死scrcpy.exe
     */
    private final JButton btnKillScrcpy;
    /**
     * 更新赚钱APP列表
     * updateEarningApps
     */
    private final JButton btnUpdateEarningApps;
    /**
     * 当前APP已签到
     */
    private final JButton btnSignedIn;
    /**
     * 所有的APP已经签到完毕
     */
    private final JButton btnAllCheckedIn;

    /**
     * scrcpy.exe内部镜像宽度数组
     */
    private final String[] widthArr = {"600", "540", "500", "480", "420", "360", "350", "340"};


    /**
     * 内部镜像宽度数组的下标
     */
    private int index = 1;
    private Color switchNetworkDebugBtnBackground;
    // private int index = 0;

    public ScrcpyJPanels() {
        scrcpyJPanel = new JPanel();
        // adbJPanel.setBorder(new TitledBorder(""));
        scrcpyJPanel.setLayout(FlowLayouts.flowLayoutLeft);

        // label = new JLabel("高度:");
        label = new JLabel("");


        widthTextField = getWidthTextField();

        btnOpenScrcpy = initBtnOpenScrcpy();

        btnOpenScrcpyFull = initBtnOpenScrcpyFull();


        btnKillScrcpy = getKillScrcpy();

        addBtn = getAddBtn();
        btnDecrease = getBtnDecrease();

        btnSwitchNetworkDebug = getBtnSwitchNetworkDebug();

        btnUpdateEarningApps = getBtnUpdateEarningApps();
        btnSignedIn = getBtnSignedIn();

        btnAllCheckedIn = getBtnAllCheckedIn();


        // adb面板添加按钮
        scrcpyJPanel.add(label);
        scrcpyJPanel.add(widthTextField);
        scrcpyJPanel.add(btnDecrease);
        scrcpyJPanel.add(addBtn);
        scrcpyJPanel.add(btnOpenScrcpy);
        scrcpyJPanel.add(btnKillScrcpy);
        scrcpyJPanel.add(btnOpenScrcpyFull);
        scrcpyJPanel.add(btnSwitchNetworkDebug);
        scrcpyJPanel.add(btnUpdateEarningApps);
        scrcpyJPanel.add(btnSignedIn);
        scrcpyJPanel.add(btnAllCheckedIn);
        // scrcpyJPanel.add(btnGetAct);
        // scrcpyJPanel.add(btnOpenMobileButlerApp);
        // scrcpyJPanel.add(btnWiFiSettings);
        // AbstractButtons.setMarginInButtonJPanel(scrcpyJPanel, 1);
        // AbstractButtons.setMarginInButtonJPanel(scrcpyJPanel, -1);
        AbstractButtons.setMarginInButtonJPanel(scrcpyJPanel, 0);
    }

    private JButton initBtnOpenScrcpyFull() {
        final JButton btnOpenScrcpyFull;
        btnOpenScrcpyFull = new JButton("➚➚");
        btnOpenScrcpyFull.setToolTipText("调用scrcpy.exe不限定高度");
        btnOpenScrcpyFull.addActionListener(new OpenButtonListener("full"));
        return btnOpenScrcpyFull;
    }

    private JButton getBtnSwitchNetworkDebug() {
        final JButton btnSwitchNetworkDebug;
        // final String networkDebugging = "网调";
        // final String usbDebugging = "线调";
        final String networkDebugging = "网";
        final String usbDebugging = "线";

        btnSwitchNetworkDebug = new JButton(networkDebugging);
        btnSwitchNetworkDebug.setToolTipText("切换网络调试");
        btnSwitchNetworkDebug.addActionListener(new ActionListener() {
            Device device = null;

            @Override
            public void actionPerformed(ActionEvent e) {
                AdbTools adbTools = AdbTools.getInstance();

                // 停止自动线程
                // adbTools.getAdbJPanels().getStopBtn().doClick();

                if (device == null) {
                    device = adbTools.getDevice();
                }
                if (networkDebugging.equals(btnSwitchNetworkDebug.getText())) {
                    String serial = device.getSerial();
                    serialOld = serial;
                    nameOld = device.getName();
                    getIpCode(serial);
                    networkDebug(serial);


                } else {
                    btnKillScrcpy.doClick();
                    String ip_serial = device.getSerial();
                    device.setSerial(serialOld);
                    device.setName(nameOld);
                    btnSwitchNetworkDebug.setText(networkDebugging);
                    btnSwitchNetworkDebug.setBackground(switchNetworkDebugBtnBackground);
                    String code = "adb disconnect " + ip_serial;
                    AdbCommands.runAbdCmd(code);
                    // reopenScrcpy();
                    // ThreadSleep.seconds(2);
                    btnOpenScrcpy.doClick();
                }

            }

            private void networkDebug(String serial) {
                // String port = "5555";
                String port = AdbConnectPortProperties.getPort();
                String tcp = "adb -s " + serial + " tcpip " + port;
                AdbCommands.runAbdCmd(tcp);

                String connectCode = "adb connect " + ip + ":" + port;
                AdbCommands.runAbdCmd(connectCode);
                device.setSerial(ip + ":" + port);
                device.setName(device.getName() + "+");

                btnSwitchNetworkDebug.setText(usbDebugging);
                switchNetworkDebugBtnBackground = btnSwitchNetworkDebug.getBackground();
                btnSwitchNetworkDebug.setBackground(Color.PINK);
                // reopenScrcpy();
                btnKillScrcpy.doClick();
                // ThreadSleep.seconds(2);
                btnOpenScrcpy.doClick();
            }
        });
        return btnSwitchNetworkDebug;
    }

    private JTextField getWidthTextField() {
        final JTextField widthTextField;
        // widthTextField = new JTextField(4);
        widthTextField = new JTextField(3);
        widthTextField.setFont(Fonts.Consolas_PLAIN_12);
        // 设置投屏的 默认高度


        Toolkit toolkit = Toolkit.getDefaultToolkit();
        // 获取显示器分辨率
        Dimension dimension = toolkit.getScreenSize();
        if (dimension.height == 1080) {
            index = 2;
        } else {
            // index = 1;
            index = 2;
        }

        widthTextField.setText(String.valueOf(widthArr[index]));
        // 禁止用户修改宽度
        // widthTextField.setEditable(false);
        return widthTextField;
    }

    private JButton getBtnDecrease() {
        final JButton btnDecrease;
        btnDecrease = new JButton("-");
        btnDecrease.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (index < widthArr.length - 1) {
                    widthTextField.setText(widthArr[++index]);
                }
            }
        });
        return btnDecrease;
    }

    private JButton getAddBtn() {
        final JButton addBtn;
        addBtn = new JButton("+");
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (index > 0) {
                    widthTextField.setText(widthArr[--index]);
                }
            }
        });
        return addBtn;
    }

    private JButton initBtnOpenScrcpy() {
        final JButton btnOpenScrcpy;
        // openScrcpyBtn = new JButton(new ImageIcon(AdbTools.class.getClassLoader().getResource("open.png")));
        btnOpenScrcpy = new JButton("➚");
        // openScrcpyBtn = new JButton("s");
        // openScrcpyBtn = new JButton("投");
        // openScrcpyBtn = new JButton("➤");


        btnOpenScrcpy.setToolTipText("使用scrcpy打开设备");
        // openScrcpyBtn.addActionListener(new OpenButtonListener());
        btnOpenScrcpy.addActionListener(new OpenButtonListener(widthTextField));
        // btnOpenScrcpy.addActionListener(new OpenButtonListener(widthTextField.getText()));


        btnOpenScrcpy.addActionListener(new ActionListener() {
            boolean isFirstTimeRun = true;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (isFirstTimeRun) {
                    // 只启动一次线程即可，不可多次启动
                    AdbTools adbTools = AdbTools.getInstance();
                    String serial = adbTools.getDevice().getSerial();
                    if ("75aed56d".equals(serial)) {
                        System.out.println("zzzzzzzzzzz启动一次线程");
                        new Thread(new OppoR9ScrcpyRun()).start();
                    }
                    isFirstTimeRun = false;

                    // if (!IsTest.isTest()) {
                    //     // 启动运动健康APP
                    //     // AdbTaskAll.openSportsAndHealthApp(serial);
                    //     OpenApp.openPedometerAPP();
                    //     AdbTaskAll.wait_TaskBtn();
                    // }

                }
            }
        });
        return btnOpenScrcpy;
    }

    private JButton getKillScrcpy() {
        final JButton btnKillScrcpy;
        // killScrcpyBtn = new JButton("kill");
        // killScrcpyBtn = new JButton("×");
        btnKillScrcpy = new JButton("k");
        btnKillScrcpy.setToolTipText("杀死打开的scrcpy镜像");
        btnKillScrcpy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // String id = AdbTools.device.getId();
                String id = AdbTools.getInstance().getDevice().getSerial();
                Taskkill.killScrcpy(id);
            }
        });
        return btnKillScrcpy;
    }

    private JButton getBtnUpdateEarningApps() {
        final JButton btnUpdateEarningApps;
        btnUpdateEarningApps = new JButton("U");
        btnUpdateEarningApps.setToolTipText("更新赚钱应用列表");
        btnUpdateEarningApps.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ForegroundAppRun.updatePackages_3_money();
            }
        });
        return btnUpdateEarningApps;
    }

    private JButton getBtnSignedIn() {
        final JButton btnSignedIn;
        btnSignedIn = new JButton("√");
        btnSignedIn.setToolTipText("当前APP已签到");
        btnSignedIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ForegroundAppRun.setStopWait(true);
            }
        });
        return btnSignedIn;
    }

    private JButton getBtnAllCheckedIn() {
        final JButton btnAllCheckedIn;
        // allCheckedInBtn = new JButton("all");
        // √√
        // all
        btnAllCheckedIn = new JButton("√√");
        btnAllCheckedIn.setToolTipText("所有的APP都签到过了");
        btnAllCheckedIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ForegroundAppRun.setStopWait(true);
                ForegroundAppRun.setIsAllAppSignedIn(true);
            }
        });
        return btnAllCheckedIn;
    }


    private String getIpCode(String serial) {
        String ipCode;
        // 获取手机型号
        String phoneModel = AdbCommands.runAbdCmd("adb -s " + serial + " shell getprop ro.product.model");
        phoneModel = phoneModel.trim();
        System.out.println("phoneModel = " + phoneModel);
        if (phoneModel.equals("MX6")) {
            ipCode = "adb -s " + serial + " shell ifconfig|findstr Bcast";
            String adbOutput = AdbCommands.runAbdCmd(ipCode);
            /*
             *  C:\Users\lan>adb -s 95AQACQJCMZPA shell ifconfig|findstr Bcast
             *       inet addr:192.168.0.103  Bcast:192.168.0.255  Mask:255.255.255.0
             *  C:\Users\lan>
             */
            if (adbOutput.contains("Bcast:")) {
                // String substring = adbOutput.substring(adbOutput.indexOf("Bcast:"));
                ip = getIfconfigIp(adbOutput);
            }
        } else {
            ipCode = "adb -s " + serial + " shell netcfg| findstr wlan0";

            String adbOutput = AdbCommands.runAbdCmd(ipCode);
            if (adbOutput.contains("wlan0") && adbOutput.contains("/")) {
                System.out.println("adbOutput = " + adbOutput);
                // String ip = getNetcfgIp(adbOutput);
                ip = getNetcfgIp(adbOutput);
            }

        }
        return ipCode;
    }

    private String getNetcfgIp(String adbOutput) {
        String ip = adbOutput.substring(0, adbOutput.lastIndexOf("/"));
        ip = ip.substring(ip.lastIndexOf(" ") + 1);
        System.out.println("ip = |" + ip + "|");
        return ip;
    }

    private String getIfconfigIp(String input) {
        return input.substring(input.indexOf("addr:") + "addr:".length(), input.indexOf("Bcast:")).trim();
    }

    public JPanel getScrcpyJPanel() {
        return scrcpyJPanel;
    }

    public JButton getBtnOpenScrcpy() {
        return btnOpenScrcpy;
    }

    public JButton getBtnKillScrcpy() {
        return btnKillScrcpy;
    }
}
