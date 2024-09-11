package adbs.main.ui.jpanels.check;

import adbs.main.ui.config.FlowLayouts;
import adbs.main.ui.jpanels.act.ActSignedInPanels;
import adbs.main.ui.jpanels.adb.AdbJPanels;
import adbs.main.ui.jpanels.app.AppSignedInPanels;
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

    private final JPanel checkJPanel;

    private final JCheckBox generalJCheckBox;
    private final JCheckBox adbJCheckBox;
    private final JCheckBox scrcpyJCheckBox;
    private final JCheckBox controlJCheckBox;
    private final JCheckBox toolsJCheckBox;
    private final JCheckBox appSignInCheckBox;
    private final JCheckBox actSignInCheckBox;

    // /**
    // * 控制 自动面板
    // * 展开或收起 自动面板
    // */
    // private final JCheckBox autoCheckBox;

    // public CheckJPanels(TimingPanels2 timingPanels2, ToolsJPanels toolsJPanels, AutoPanels autoPanels, UniversalPanels universralPanels, AdbJPanels adbJPanels, ScrcpyJPanels scrcpyJPanels, AppPanels appPanels) {
    public CheckJPanels(TimingPanels2 timingPanels2, ToolsJPanels toolsJPanels, UniversalPanels universralPanels, AdbJPanels adbJPanels, ScrcpyJPanels scrcpyJPanels, AppSignedInPanels appSignedInPanels, ActSignedInPanels actSignedInPanels) {
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
        // toolsJCheckBox = new JCheckBox("工具", false);
        toolsJCheckBox = new JCheckBox("工", false);
        toolsJCheckBox.addItemListener(new JCheckBoxControlJPanelItemListener(toolsJPanels.getToolsJPanel()));
        // toolsJCheckBox.doClick();
        toolsJCheckBox.setToolTipText("usb上网，提前apk，安装apk功能");

        // // autoCheckBox = new JCheckBox("Auto");
        // // autoCheckBox = new JCheckBox("金币");
        // autoCheckBox = new JCheckBox("币");
        // autoCheckBox.setToolTipText("打开自动化面板");
        // autoCheckBox.addItemListener(new JCheckBoxControlJPanelItemListener(autoPanels.getAutoJPanel()));

        // signInCheckBox = new JCheckBox("签到");
        appSignInCheckBox = new JCheckBox("签");
        appSignInCheckBox.setToolTipText("显示已签到APP");
        appSignInCheckBox.addItemListener(new JCheckBoxControlJPanelItemListener(appSignedInPanels.getAppPanel()));

        //        JCheckBox topCheckBox = getTopCheckBox();

        actSignInCheckBox = new JCheckBox("细");
        actSignInCheckBox.setToolTipText("显示某个App的详细任务");
        actSignInCheckBox.addItemListener(new JCheckBoxControlJPanelItemListener(actSignedInPanels.getTopJPanel()) {
            @Override
            protected void after() {
//                super.after();
                JButton btnUpdate = actSignedInPanels.getBtnUpdate();
                btnUpdate.doClick();
            }
        });


        checkJPanel.add(adbJCheckBox);
        checkJPanel.add(scrcpyJCheckBox);
        checkJPanel.add(generalJCheckBox);
        checkJPanel.add(controlJCheckBox);
        checkJPanel.add(toolsJCheckBox);
        // checkJPanel.add(autoCheckBox);
        //        checkJPanel.add(topCheckBox);
        checkJPanel.add(appSignInCheckBox);
        checkJPanel.add(actSignInCheckBox);
        // return checkJPanel;
    }

    //    /**
    //     * 创建窗体置顶复选框
    //     *
    //     * @return
    //     */
    //    private JCheckBox getTopCheckBox() {
    //        JCheckBox topCheckBox = new JCheckBox("↑");
    //        topCheckBox.addItemListener(new ItemListener() {
    //            @Override
    //            public void itemStateChanged(ItemEvent e) {
    //                AdbTools instance = AdbTools.getInstance();
    //                //                instance.showDialogOk();
    //                JFrame frame = instance.getFrame();
    //                // 如果当前的状态是勾选状态
    //                if (e.getStateChange() == ItemEvent.SELECTED) {
    //                    System.out.println("开启 窗口置顶");
    //                    frame.setAlwaysOnTop(true);
    //                    DialogFactory.setAlwaysOnTop(true);
    //                } else {
    //                    System.out.println("取消 窗口置顶");
    //                    frame.setAlwaysOnTop(false);
    //                    DialogFactory.setAlwaysOnTop(false);
    //                }
    //            }
    //        });
    //        return topCheckBox;
    //    }

    // public JCheckBox getAutoCheckBox() {
    // return autoCheckBox;
    // }

    public JPanel getCheckJPanel() {
        return checkJPanel;
    }

    public JCheckBox getAppSignInCheckBox() {
        return appSignInCheckBox;
    }
}
