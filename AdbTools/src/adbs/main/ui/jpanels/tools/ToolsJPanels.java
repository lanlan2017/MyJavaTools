package adbs.main.ui.jpanels.tools;

import adbs.main.AdbTools;
import adbs.main.ui.config.FlowLayouts;
import adbs.model.Device;
import runnabletools.pull.AdbPullApk;
import tools.swing.button.AbstractButtons;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolsJPanels {
    private JPanel toolsJPanel;
    /**
     * 提取apk按钮
     */
    private JRadioButton getApk;
    /**
     * 安装apk到Android
     */
    private JRadioButton installApk;


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


        toolsJPanel.setBorder(new TitledBorder(new LineBorder(Color.BLACK), "工具面板"));
        tips = new JLabel("");

        getApk = new JRadioButton("提取");
        installApk = new JRadioButton("安装");


        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(getApk);
        buttonGroup.add(installApk);


        getApk.setToolTipText("提取屏幕顶部的APP的apk文件");
        input = new JTextField(5);
        input.setVisible(false);
        okButton = new JButton("确定");
        okButton.setVisible(false);
        cancelButton = new JButton("取消");
        cancelButton.setVisible(false);

        getApk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                input.setVisible(true);
                tips.setText(" apk名称:");
                okButton.setText("开始提取");
                okButton.setVisible(true);
                AdbTools.getInstance().getFrame().pack();
                // AdbPullApk.setParentComponent(AdbTools.getInstance().getFrame());
                AdbPullApk.setParentComponent(AdbTools.getInstance().getContentPane());
            }
        });

        installApk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                input.setVisible(true);
                tips.setText(" apk编号:");
                okButton.setText("开始安装");
                okButton.setVisible(true);
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
                    case "开始提取":
                        pullTopApk();
                        break;
                    // case "install":
                    case "开始安装":
                        // pullTopApk();
                        System.out.println("洗洗");
                        break;
                }
            }
        });

        toolsJPanel.add(getApk);
        // toolsJPanel.add(installApk);
        toolsJPanel.add(tips);
        toolsJPanel.add(input);
        toolsJPanel.add(okButton);
        toolsJPanel.setVisible(false);
        // toolsJPanel.add(cancelButton);
        // AbstractButtons.setMarginInButtonJPanel(toolsJPanel,0);
        AbstractButtons.setMarginInButtonJPanel(toolsJPanel, 1);
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
