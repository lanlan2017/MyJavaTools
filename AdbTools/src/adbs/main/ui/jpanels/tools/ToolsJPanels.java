package adbs.main.ui.jpanels.tools;

import adbs.cmd.AdbUninstall;
import adbs.main.AdbTools;
import adbs.main.run.AdbGetPackage;
import adbs.main.run.AdbShellPmListPackages_3;
import adbs.main.ui.config.FlowLayouts;
import adbs.model.Device;
import adbs.tools.thread.ThreadSleep;
import runnabletools.pull.AdbPullApk;
import tools.copy.SystemClipboard;
import tools.swing.button.AbstractButtons;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

/**
 * apk面板 工具面板，操作应用的面板
 */
public class ToolsJPanels {
    private JPanel toolsJPanel;
    /**
     * 提取apk按钮
     */
    private JRadioButton apkBtn;
    private final String apkBtnFlag = ".apk";
    private JRadioButton packBtn;
    private final String packBtnFlag = "获取";
    private JButton btnUninstallUseless;

    /**
     * 提示文本
     */
    private JLabel tips;

    /**
     * 接收用书输入
     */
    private JTextField input;

    private JButton okButton;
    private JButton cancelButton;

    // private Device device;

    public ToolsJPanels() {

        toolsJPanel = new JPanel();
        toolsJPanel.setLayout(FlowLayouts.flowLayoutLeft);
        // toolsJPanel.setLayout(new BorderLayout());


        // toolsJPanel.setBorder(new TitledBorder(new LineBorder(Color.BLACK), "工具面板"));

        tips = new JLabel("");

        apkBtn = new JRadioButton("提取");
        // installApk = new JRadioButton("安装");

        packBtn = new JRadioButton("包名");

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(apkBtn);
        buttonGroup.add(packBtn);

        // buttonGroup.add(installApk);


        apkBtn.setToolTipText("提取屏幕顶部的APP的apk文件");
        packBtn.setToolTipText("保存当前应用的包名和应用名");

        btnUninstallUseless = new JButton("卸载");

        btnUninstallUseless.setToolTipText("一键卸载保存在配置文件中的无用的APP");
        // Color oldColor = btnUninstallUseless.getBackground();
        // btnUninstallUseless.setOpaque(true);
        // btnUninstallUseless.setBorderPainted(false);
        btnUninstallUseless.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 在后续的操作没有结束之前，禁用按钮
                // toolsJPanel.setBackground(Color.red);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        btnUninstallUseless.setText("卸载中...");
                        AdbTools.getInstance().getFrame().pack();
                        btnUninstallUseless.setEnabled(false);
                    }
                }).start();

                AdbUninstall.uninstallAllNonEssentialApps();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        ThreadSleep.seconds(3);
                        btnUninstallUseless.setText("卸载");
                        AdbTools.getInstance().getFrame().pack();
                        btnUninstallUseless.setEnabled(true);
                    }
                }).start();


            }
        });


        // 华为运动健康
        // input = new JTextField(7);
        input = new JTextField(5);
        input.setVisible(false);
        okButton = new JButton("确定");
        okButton.setVisible(false);
        cancelButton = new JButton("取消");
        cancelButton.setVisible(false);

        apkBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                input.setVisible(true);
                // tips.setText("应用名:");
                okButton.setText(apkBtnFlag);
                okButton.setVisible(true);
                AdbTools.getInstance().getFrame().pack();
                // AdbPullApk.setParentComponent(AdbTools.getInstance().getFrame());
                AdbPullApk.setParentComponent(AdbTools.getInstance().getContentPane());
            }
        });

        packBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                okButton.setText(packBtnFlag);
                okButton.setVisible(true);
                input.setVisible(true);
                // tips.setText("应用名:");
                AdbTools.getInstance().getFrame().pack();
            }
        });


        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton ok = (JButton) e.getSource();
                String okText = ok.getText();
                switch (okText) {
                    // case "开始提取":
                    case apkBtnFlag:
                        pullTopApk();
                        // 输出包名和应用名，复制包名和应用名都剪贴板中
                        // copyPackage_AppName
                        copyPackage_AppName();

                        break;
                    // case "install":
                    case packBtnFlag:
                        // pullTopApk();
                        soutPackageNameAppName();
                        // System.out.println("洗洗");
                        break;
                }
            }

            /**
             * 复制包名和应用名到剪贴板中
             */
            private void copyPackage_AppName() {
                packBtn.doClick();
                okButton.doClick();
            }
        });

        toolsJPanel.add(btnUninstallUseless);
        toolsJPanel.add(apkBtn);
        toolsJPanel.add(packBtn);
        // toolsJPanel.add(installApk);
        toolsJPanel.add(tips);
        toolsJPanel.add(input);
        toolsJPanel.add(okButton);
        toolsJPanel.setVisible(false);
        // toolsJPanel.add(cancelButton);
        // AbstractButtons.setMarginInButtonJPanel(toolsJPanel,0);
        AbstractButtons.setMarginInButtonJPanel(toolsJPanel, 1);
    }

    private void soutPackageNameAppName() {
        String appName = input.getText();
        if (!"".equals(appName)) {
            String serial = AdbTools.getInstance().getDevice().getSerial();
            String packageName = AdbGetPackage.getTopPackageName(serial);
            if (!"".equals(packageName)) {
                // System.out.println("packageName = " + packageName);
                // System.out.println("appName = " + appName);
                // System.out.printf("%-40s%s\n", packageName, appName);
                // if (packageName.startsWith("com.android.")) {
                //     System.out.println(packageName + " " + appName + "是 安卓系统程序，不可卸载！");
                // } else if (packageName.startsWith("com.huawei.")) {
                //     System.out.println(packageName + " " + appName + "是 华为系统程序，不可卸载！");
                // }
                // String format = String.format("%-50s%s\n", packageName, appName);
                String format = String.format("%-40s%-8s", packageName, appName);
                format = format.trim() + "\r\n";
                System.out.println("|" + format + "|");

                ArrayList<String> package_3 = new AdbShellPmListPackages_3().getPackage_3();
                int i = Collections.binarySearch(package_3, packageName);
                // 如果这个包名，在第三方APP包名列表中，则表示可以卸载
                if (i >= 0) {
                    SystemClipboard.setSysClipboardText(format);
                } else {
                    SystemClipboard.setSysClipboardText("非第三方APP！谨慎卸载：" + format);
                }

            }
        }
    }

    /**
     * 提前Android顶部的APP的apk文件到电脑中
     */
    private void pullTopApk() {
        AdbTools adbTools = AdbTools.getInstance();
        adbTools.getFrame().pack();

        Device device = adbTools.getDevice();
        String apkName = input.getText();

        AdbPullApk.pullTopApk(device, apkName);
    }

    public JPanel getToolsJPanel() {
        return toolsJPanel;
    }
}
