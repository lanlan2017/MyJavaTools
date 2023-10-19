package adbs.main.ui.jpanels.tools;

import adbs.cmd.AdbUninstall;
import adbs.main.AdbTools;
import adbs.main.run.AdbGetPackage;
import adbs.main.run.AdbShellPmListPackages_3;
import adbs.main.ui.config.FlowLayouts;
import adbs.main.ui.config.Fonts;
import adbs.main.ui.jpanels.auto.AdbTap;
import adbs.main.ui.jpanels.tools.example.FocusListenerJTextFieldInfo;
import adbs.model.Device;
import runnabletools.pull.AdbPullApk;
import tools.copy.SystemClipboard;
import tools.swing.button.AbstractButtons;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Collections;

/**
 * apk面板 工具面板，操作应用的面板
 */
public class ToolsJPanels {
    private final JPanel toolsJPanel;
    /**
     * 提取apk按钮
     */
    private final String flagJrbApk = ".apk";
    private final String flagJrbApkInfo = "apk名";
    /**
     * 获取包名按钮
     */
    private final String flagJrbPackage = "包|应用";
    private final String flagJrbPackageInfo = "应用名";
    private final String flagJrbUninstall = "全部卸载";
    // /**
    //  *
    //  */
    // private final String flagRatio = "比率";
    // private String flagRatioInfo1 = "x坐标";
    // private String flagRatioInfo2 = "y坐标";


    private final JComboBox<String> jComboBox;
    private final JButton btnUninstallAll;

    /**
     * 提示文本
     */
    private final JLabel tips;

    /**
     * 接收用书输入
     */
    private final JTextField input1;
    private final JTextField input2;
    /**
     * 确定按钮
     */
    private final JButton okButton;


    public ToolsJPanels() {


        toolsJPanel = new JPanel();
        toolsJPanel.setLayout(FlowLayouts.flowLayoutLeft);
        tips = new JLabel("");
        input1 = new JTextField(5);
        // input1.setFont(Fonts.Consolas_PLAIN_12);
        // input1.setFont(Fonts.Cascadia_BOLD_12);
        input1.setFont(Fonts.Cascadia_PLAIN_12);
        input1.setVisible(false);

        input2 = new JTextField(5);
        // input2.setFont(Fonts.Consolas_PLAIN_12);
        input2.setFont(Fonts.Cascadia_PLAIN_12);
        input2.setVisible(false);

        btnUninstallAll = initBtnUninstall();
        okButton = initOkJButton();
        jComboBox = initJComboBox();


        toolsJPanel.add(tips);
        toolsJPanel.add(btnUninstallAll);
        toolsJPanel.add(jComboBox);
        toolsJPanel.add(input1);
        toolsJPanel.add(input2);
        toolsJPanel.add(okButton);
        toolsJPanel.setVisible(false);
        // toolsJPanel.add(cancelButton);
        // AbstractButtons.setMarginInButtonJPanel(toolsJPanel,0);
        AbstractButtons.setMarginInButtonJPanel(toolsJPanel, 1);
    }

    private JButton initBtnUninstall() {
        final JButton btnUninstallAll;
        btnUninstallAll = new JButton("卸载");
        btnUninstallAll.setToolTipText("卸载所有无用的APP");
        btnUninstallAll.addActionListener(new BtnActionListener() {
            @Override
            public void action(ActionEvent e) {
                AdbUninstall.uninstallAllNonEssentialApps();
            }
        });
        return btnUninstallAll;
    }

    private JButton initOkJButton() {
        final JButton okButton;
        okButton = new JButton("确定");
        // okButton.setVisible(false);
        // okButton.addActionListener(new ActionListener() {
        okButton.addActionListener(new BtnActionListener() {
            @Override
            public void action(ActionEvent e) {
                JButton ok = (JButton) e.getSource();
                String okText = ok.getText();
                switch (okText) {
                    // case "开始提取":
                    case flagJrbApk:
                        pullTopApk();
                        // 输出包名和应用名，复制包名和应用名都剪贴板中
                        // copyPackage_AppName
                        copyPackage_AppName();

                        break;
                    // case "install":
                    case flagJrbPackage:
                        // pullTopApk();
                        soutPackageNameAppName();
                        // System.out.println("洗洗");
                        break;
                    // case flagJrbUninstall:
                    //     AdbUninstall.uninstallAllNonEssentialApps();
                    //     break;

                    // case flagRatio:
                    //
                    //     String textX = input1.getText();
                    //     String textY = input2.getText();
                    //     System.out.println("textX = " + textX);
                    //     System.out.println("textY = " + textY);
                    //
                    //     String regexDouble = "[0-9.]+";
                    //     if (textX.matches(regexDouble) && textY.matches(regexDouble)) {
                    //         double x = Double.parseDouble(textX);
                    //         double y = Double.parseDouble(textY);
                    //         System.out.println("x = " + x);
                    //         System.out.println("y = " + y);
                    //         AdbTap.printRatio(x, y);
                    //     }
                    //     break;

                }
            }

            /**
             * 复制包名和应用名到剪贴板中
             */
            private void copyPackage_AppName() {
                // jrbPack.doClick();
                jComboBox.setSelectedIndex(1);
                okButton.doClick();
            }
        });
        return okButton;
    }

    private JComboBox<String> initJComboBox() {
        // flagRatio = "比例";
        // String[] chioces = {flagJrbApk, flagJrbPackage, flagJrbUninstall, flagRatio};
        // String[] chioces = {flagJrbApk, flagJrbPackage, flagRatio};
        String[] chioces = {flagJrbApk, flagJrbPackage};
        JComboBox<String> jComboBox = new JComboBox(chioces);
        jComboBox.setFont(Fonts.Cascadia_PLAIN_12);
        // 设置原型显示值，选项的宽度以这个作为原型
        // jComboBox.setPrototypeDisplayValue(flagJrbPackage);

        jComboBox.addItemListener(new ItemListener() {

            // private ComboBoxModel<String> model;
            private ComboBoxModel<String> model = jComboBox.getModel();
            // FocusListenerJTextFieldInfo focusL_X = new FocusListenerJTextFieldInfo("x坐标", input1);
            // FocusListenerJTextFieldInfo focusLApkName = new FocusListenerJTextFieldInfo("apk名", input1);
            // FocusListenerJTextFieldInfo focusLApkName = new FocusListenerJTextFieldInfo(flagJrbApkInfo, input1);
            // FocusListenerJTextFieldInfo focusLAppName = new FocusListenerJTextFieldInfo("应用名", input1);
            // FocusListenerJTextFieldInfo focusLAppName = new FocusListenerJTextFieldInfo(flagJrbPackageInfo, input1);
            // FocusListenerJTextFieldInfo focusL_Y = new FocusListenerJTextFieldInfo("y坐标", input2);

            // private boolean isRemoveFirst = false;

            @Override
            public void itemStateChanged(ItemEvent e) {
                int selectedIndex = jComboBox.getSelectedIndex();
                // AbstractButtons.setJButtonMargin(jComboBox);
                String elementAt = model.getElementAt(selectedIndex);
                System.out.println("elementAt = " + elementAt);
                // input2.setText("");
                // 全部移除监听器
                // input1.removeFocusListener(focusL_X);
                // input1.removeFocusListener(focusLApkName);
                // input1.removeFocusListener(focusLAppName);
                // input2.removeFocusListener(focusL_Y);

                // jComboBox.setPrototypeDisplayValue(elementAt);

                switch (elementAt) {
                    case flagJrbApk:
                        // input1.setText("apk名");
                        // input1.setText(flagJrbApkInfo);
                        input1.setColumns(6);
                        input1.setToolTipText("输入apk名");
                        // input1.addFocusListener(focusLApkName);
                        // input1.setText("");
                        input1.setVisible(true);
                        input2.setVisible(false);


                        break;
                    case flagJrbPackage:
                        // input1.setText("应用名");
                        // input1.setText(flagJrbPackageInfo);
                        input1.setColumns(6);
                        input1.setToolTipText("输入应用名");
                        // input1.setText("");
                        input1.setVisible(true);
                        // input1.addFocusListener(focusLAppName);
                        input2.setVisible(false);
                        break;
                    case flagJrbUninstall:
                        input1.setVisible(false);
                        input2.setVisible(false);
                        break;
                    // case flagRatio:
                    //     input1.setVisible(true);
                    //     // input1.addFocusListener(focusL_X);
                    //     // flagRatioInfo1 = "x坐标";
                    //     // input1.setText(flagRatioInfo1);
                    //     input1.setToolTipText("输入x坐标");
                    //     input1.setColumns(4);
                    //
                    //     input2.setVisible(true);
                    //     // input2.addFocusListener(focusL_Y);
                    //     // flagRatioInfo2 = "y坐标";
                    //     // input2.setText(flagRatioInfo2);
                    //     input2.setToolTipText("输入y坐标");
                    //     input2.setColumns(4);
                    //     break;
                }
                okButton.setText(elementAt);
                okButton.setVisible(true);
                AdbTools.getInstance().getFrame().pack();

            }
        });
        // jComboBox.setSelectedIndex(1);
        jComboBox.setSelectedIndex(0);
        // input1.setText(flagJrbApkInfo);
        input1.setVisible(true);
        okButton.setText(flagJrbApk);

        // input1.setText();


        return jComboBox;
    }

    private JRadioButton getJrbUninstall() {
        final JRadioButton jrbUninstall;
        jrbUninstall = new JRadioButton("卸载");
        jrbUninstall.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                input1.setVisible(false);
                input2.setVisible(false);
                okButton.setText(flagJrbUninstall);
                AdbTools.getInstance().getFrame().pack();
            }
        });
        return jrbUninstall;
    }

    private JRadioButton getJrbPack() {
        final JRadioButton jrbPack;
        jrbPack = new JRadioButton("包名");
        jrbPack.setToolTipText("保存当前应用的包名和应用名");
        jrbPack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                okButton.setText(flagJrbPackage);
                okButton.setVisible(true);
                input1.setVisible(true);
                input2.setVisible(false);
                // tips.setText("应用名:");
                AdbTools.getInstance().getFrame().pack();
            }
        });
        return jrbPack;
    }

    private JRadioButton getJrbApk() {
        final JRadioButton jrbApk;
        jrbApk = new JRadioButton("提取");
        jrbApk.setToolTipText("提取屏幕顶部的APP的apk文件");
        jrbApk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                input1.setVisible(true);
                input2.setVisible(false);
                // input1.text
                // tips.setText("应用名:");
                okButton.setText(flagJrbApk);
                okButton.setVisible(true);
                AdbTools.getInstance().getFrame().pack();
                // AdbPullApk.setParentComponent(AdbTools.getInstance().getFrame());
                AdbPullApk.setParentComponent(AdbTools.getInstance().getContentPane());
            }
        });
        return jrbApk;
    }

    private void soutPackageNameAppName() {
        String appName = input1.getText();
        if (!"".equals(appName)) {
            String serial = AdbTools.getInstance().getDevice().getSerial();
            String packageName = AdbGetPackage.getTopPackageName(serial);
            if (!"".equals(packageName)) {
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
        String apkName = input1.getText();

        AdbPullApk.setParentComponent(AdbTools.getInstance().getContentPane());
        AdbPullApk.pullTopApk(device, apkName);
    }

    public JPanel getToolsJPanel() {
        return toolsJPanel;
    }
}
