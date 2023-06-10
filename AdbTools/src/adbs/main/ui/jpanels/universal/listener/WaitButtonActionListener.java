package adbs.main.ui.jpanels.universal.listener;

import adbs.main.ui.jframe.JFramePack;
import adbs.main.ui.jpanels.adb.listener.ButtonFocusReleaseActionListener;
import adbs.main.ui.jpanels.time.TimePanels;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * 等待按钮事件监听器
 */
public class WaitButtonActionListener extends ButtonFocusReleaseActionListener {
    private TimePanels timePanels;

    public WaitButtonActionListener(TimePanels timePanels) {
        this.timePanels = timePanels;
    }

    @Override
    protected void actionEvent(ActionEvent e) {

        // 测试替换
        timePanels.getTimePanel().setVisible(true);
        timePanels.getTimeLabel().setText("时间(s):");
        // inputPanels.getTimeRadioPanel().setVisible(true);
        timePanels.getInputOkButton().setText("开始等待");
        timePanels.getInput1().setColumns(4);
        timePanels.getInput1().setText(String.valueOf(30));
        timePanels.getInput2().setVisible(false);
        // inputPanels.getInput2().setVisible(true);
        // inputPanels.getPlusBtn().setVisible(false);
        // inputPanels.getMinusBtn().setVisible(false);
        timePanels.getPlusBtn().setVisible(true);
        timePanels.getMinusBtn().setVisible(true);

        JCheckBox checkBox = timePanels.getTaskCheckBox();
        checkBox.setVisible(true);
        checkBox.setToolTipText("等待结束后 点击 任务键");

        JCheckBox stopCheckBox = timePanels.getStopCheckBox();
        stopCheckBox.setVisible(true);
        stopCheckBox.setToolTipText("等待结束后 点击 停止按钮");

        timePanels.getTimerJLabel().setVisible(true);
        JFramePack.onJComponentActionEvent(e);
    }
}
