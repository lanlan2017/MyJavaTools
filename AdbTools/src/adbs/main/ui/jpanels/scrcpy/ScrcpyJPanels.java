package adbs.main.ui.jpanels.scrcpy;

import adbs.cmd.AdbCommands;
import adbs.main.AdbTools;
import adbs.main.run.ForegroundAppRun;
import adbs.main.run.OppoR9ScrcpyRun;
import adbs.main.ui.config.FlowLayouts;
import adbs.main.ui.jpanels.adb.listener.OpenButtonListener;
import adbs.main.ui.jpanels.adb.open.Taskkill;
import adbs.model.Device;
import adbs.tools.thread.ThreadSleep;
import config.AdbConnectPortProperties;
import runnabletools.serial.AdbTaskAll;
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
    private final JButton addBtn;
    private final JButton decreaseBtn;
    private final JTextField widthTextField;

    private final JButton openScrcpyBtn;
    private final JButton switchNetworkDebugBtn;
    private final JButton killScrcpyBtn;
    /**
     * 更新赚钱APP列表
     * updateEarningApps
     */
    private final JButton updateEarningApps;
    /**
     * 当前APP已签到
     */
    private final JButton signedInBtn;

    private final JButton allCheckedInBtn;

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

        decreaseBtn = new JButton("-");
        addBtn = new JButton("+");

        // widthTextField = new JTextField(4);
        widthTextField = new JTextField(3);
        // 设置投屏的 默认高度
        // widthTextField.setText(String.valueOf(540));
        // widthTextField.setText(String.valueOf(600));


        Toolkit toolkit = Toolkit.getDefaultToolkit();
        // 获取显示器分辨率
        Dimension dimension = toolkit.getScreenSize();
        // System.out.println(dimension.height);
        // System.out.println(dimension.width);
        // System.out.println("dimension.height = " + dimension.height);
        // System.out.println("dimension.width = " + dimension.width);
        if (dimension.height == 1080) {
            index = 2;
        } else {
            // index = 1;
            index = 2;
        }

        widthTextField.setText(String.valueOf(widthArr[index]));
        // 禁止用户修改宽度
        // widthTextField.setEditable(false);


        // openScrcpyBtn = new JButton(new ImageIcon(AdbTools.class.getClassLoader().getResource("open.png")));
        openScrcpyBtn = new JButton("➚");
        // openScrcpyBtn = new JButton("➤");


        openScrcpyBtn.setToolTipText("使用scrcpy打开设备");
        // openScrcpyBtn.addActionListener(new OpenButtonListener());
        openScrcpyBtn.addActionListener(new OpenButtonListener(widthTextField));
        openScrcpyBtn.addActionListener(new ActionListener() {
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
                    // 启动运动健康APP
                    AdbTaskAll.openSportsAndHealthApp(serial);

                }
            }
        });

        killScrcpyBtn = new JButton("kill");
        killScrcpyBtn.setToolTipText("杀死打开的scrcpy镜像");
        killScrcpyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // String id = AdbTools.device.getId();
                String id = AdbTools.getInstance().getDevice().getSerial();
                Taskkill.killScrcpy(id);
            }
        });

        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (index > 0) {
                    widthTextField.setText(widthArr[--index]);
                }
            }
        });
        decreaseBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (index < widthArr.length - 1) {
                    widthTextField.setText(widthArr[++index]);
                }
            }
        });

        switchNetworkDebugBtn = new JButton("网调");
        switchNetworkDebugBtn.setToolTipText("切换网络调试");
        switchNetworkDebugBtn.addActionListener(new ActionListener() {
            Device device = null;

            @Override
            public void actionPerformed(ActionEvent e) {
                AdbTools adbTools = AdbTools.getInstance();

                // 停止自动线程
                // adbTools.getAdbJPanels().getStopBtn().doClick();

                if (device == null) {
                    device = adbTools.getDevice();
                }
                if ("网调".equals(switchNetworkDebugBtn.getText())) {
                    String serial = device.getSerial();
                    serialOld = serial;
                    nameOld = device.getName();
                    getIpCode(serial);
                    networkDebug(serial);


                } else {
                    killScrcpyBtn.doClick();
                    String ip_serial = device.getSerial();
                    device.setSerial(serialOld);
                    device.setName(nameOld);
                    switchNetworkDebugBtn.setText("网调");
                    switchNetworkDebugBtn.setBackground(switchNetworkDebugBtnBackground);
                    String code = "adb disconnect " + ip_serial;
                    AdbCommands.runAbdCmd(code);
                    // reopenScrcpy();
                    // ThreadSleep.seconds(2);
                    openScrcpyBtn.doClick();
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
                switchNetworkDebugBtn.setText("线调");
                switchNetworkDebugBtnBackground = switchNetworkDebugBtn.getBackground();
                switchNetworkDebugBtn.setBackground(Color.PINK);
                // reopenScrcpy();
                killScrcpyBtn.doClick();
                // ThreadSleep.seconds(2);
                openScrcpyBtn.doClick();
            }
        });

        updateEarningApps = new JButton("UL");
        updateEarningApps.setToolTipText("更新赚钱应用列表");
        updateEarningApps.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ForegroundAppRun.updatePackages_3_money();
            }
        });
        signedInBtn = new JButton("√");
        signedInBtn.setToolTipText("当前APP已签到");

        signedInBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ForegroundAppRun.setStopWait(true);
            }
        });

        // allCheckedInBtn = new JButton("all");
        // √√
        // all
        allCheckedInBtn = new JButton("√√");
        allCheckedInBtn.setToolTipText("所有的APP都签到过了");
        allCheckedInBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ForegroundAppRun.setStopWait(true);
                ForegroundAppRun.setIsAllAppSignedIn(true);
            }
        });

        // adb面板添加按钮
        scrcpyJPanel.add(label);
        scrcpyJPanel.add(widthTextField);
        scrcpyJPanel.add(decreaseBtn);
        scrcpyJPanel.add(addBtn);
        scrcpyJPanel.add(openScrcpyBtn);
        scrcpyJPanel.add(killScrcpyBtn);
        scrcpyJPanel.add(switchNetworkDebugBtn);
        scrcpyJPanel.add(updateEarningApps);
        scrcpyJPanel.add(signedInBtn);
        scrcpyJPanel.add(allCheckedInBtn);
        AbstractButtons.setMarginInButtonJPanel(scrcpyJPanel, 1);
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

    // private void reopenScrcpy() {
    //     killScrcpyBtn.doClick();
    //     // ThreadSleep.seconds(2);
    //     openScrcpyBtn.doClick();
    // }

    public JPanel getScrcpyJPanel() {
        return scrcpyJPanel;
    }

    public JButton getOpenScrcpyBtn() {
        return openScrcpyBtn;
    }

    public JButton getKillScrcpyBtn() {
        return killScrcpyBtn;
    }
}
