package adbs.main.ui.jpanels.check;

import adbs.main.ui.config.FlowLayouts;
import adbs.main.ui.jpanels.adb.AdbJPanels;
import adbs.main.ui.jpanels.auto.AutoPanels;
import adbs.main.ui.jpanels.check.JCheckBoxControlJPanelItemListener;
import adbs.main.ui.jpanels.scrcpy.ScrcpyJPanels;
import adbs.main.ui.jpanels.timeauto2.TimingPanels2;
import adbs.main.ui.jpanels.tools.ToolsJPanels;
import adbs.main.ui.jpanels.universal.UniversalPanels;

import javax.swing.*;

/**
 * 展开或折叠其他面板
 * 展开或折叠
 */
public class CheckJPanels {

    private JPanel checkJPanel;
    private JCheckBox generalJCheckBox;
    private JCheckBox adbJCheckBox;
    private JCheckBox scrcpyJCheckBox;
    private JCheckBox controlJCheckBox;
    private JCheckBox toolsJCheckBox;
    /**
     * 控制 自动面板
     * 展开或收起 自动面板
     */
    private JCheckBox autoCheckBox;

    public CheckJPanels(TimingPanels2 timingPanels2, ToolsJPanels toolsJPanels, AutoPanels autoPanels, UniversalPanels universralPanels, AdbJPanels adbJPanels, ScrcpyJPanels scrcpyJPanels) {
        // 初始化多选框面板
        checkJPanel = new JPanel();
        checkJPanel.setLayout(FlowLayouts.flowLayoutLeft);

        // 通用复选框
        // JCheckBox generalJCheckBox = new JCheckBox("通用", true);
        generalJCheckBox = new JCheckBox("动", true);
        // JCheckBox generalJCheckBox = new JCheckBox("", true);
        generalJCheckBox.setToolTipText("展开/折叠 通用功能");
        // 需要先初始化通用面板 要放在 initUniversalPanel(inputPanels, inOut);之后
        generalJCheckBox.addItemListener(new JCheckBoxControlJPanelItemListener(universralPanels.getUniversalPanel()));


        // JCheckBox adbJCheckBox = new JCheckBox("系统", true);
        // JCheckBox adbJCheckBox = new JCheckBox("system", true);
        adbJCheckBox = new JCheckBox("系", true);
        // JCheckBox adbJCheckBox = new JCheckBox("", true);
        adbJCheckBox.setToolTipText("展开/折叠 系统功能 面板");
        adbJCheckBox.addItemListener(new JCheckBoxControlJPanelItemListener(adbJPanels.getAdbJPanel()));

        // JCheckBox scrcpyJCheckBox = new JCheckBox("投屏", true);
        scrcpyJCheckBox = new JCheckBox("投", true);
        // JCheckBox adbJCheckBox = new JCheckBox("", true);
        scrcpyJCheckBox.setToolTipText("展开/折叠 scrcpy设置功能");
        scrcpyJCheckBox.addItemListener(new JCheckBoxControlJPanelItemListener(scrcpyJPanels.getScrcpyJPanel()));

        // 控制复选框
        controlJCheckBox = new JCheckBox("等", true);
        // controlJCheckBox.addItemListener(new JCheckBoxControlJPanelItemListener(timingPanels.getTimingPanel()));
        controlJCheckBox.addItemListener(new JCheckBoxControlJPanelItemListener(timingPanels2.getTimingPanels2()));
        // 现在就触发
        // controlJCheckBox.doClick();

        // JCheckBox multitaskingJCheckBox = new JCheckBox("后台", false);
        // JCheckBox multitaskingJCheckBox = new JCheckBox("工具", true);
        toolsJCheckBox = new JCheckBox("工具", false);
        toolsJCheckBox.addItemListener(new JCheckBoxControlJPanelItemListener(toolsJPanels.getToolsJPanel()));
        // toolsJCheckBox.doClick();
        toolsJCheckBox.setToolTipText("usb上网，提前apk，安装apk功能");

        autoCheckBox = new JCheckBox("Auto");
        autoCheckBox.setToolTipText("打开自动化面板");
        autoCheckBox.addItemListener(new JCheckBoxControlJPanelItemListener(autoPanels.getAutoJPanel()));


        checkJPanel.add(adbJCheckBox);
        checkJPanel.add(scrcpyJCheckBox);
        checkJPanel.add(generalJCheckBox);
        checkJPanel.add(controlJCheckBox);
        checkJPanel.add(toolsJCheckBox);
        checkJPanel.add(autoCheckBox);
        // return checkJPanel;
    }

    public JCheckBox getAutoCheckBox() {
        return autoCheckBox;
    }

    public JPanel getCheckJPanel() {
        return checkJPanel;
    }
}
