package adbs.main.ui.jpanels.universal.listener;

import adbs.main.ui.jframe.JFramePack;
import adbs.main.ui.jpanels.adb.listener.ButtonFocusReleaseActionListener;
import adbs.main.ui.jpanels.time.TimePanels;
import adbs.main.ui.jpanels.time.listener.WaitValues;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ShoppingButtonActionListener extends ButtonFocusReleaseActionListener {
    private TimePanels timePanels;

    public ShoppingButtonActionListener(TimePanels timePanels) {
        this.timePanels = timePanels;
    }

    @Override
    protected void actionEvent(ActionEvent e) {
        timePanels.getTimePanel().setVisible(true);
        timePanels.getTimeLabel().setText("时长(s)");
        // 关闭单选按钮组
        // inputPanels.getTimeRadioPanel().setVisible(false);
        // 逛街20分钟
        JTextField input1 = timePanels.getInput1();
        input1.setVisible(true);

        int index = 5;
        WaitValues.setIndex(index);
        input1.setText(String.valueOf(WaitValues.values[index]));


        timePanels.getInput2().setVisible(false);

        // inputPanels.getPlusBtn().setVisible(false);
        // inputPanels.getMinusBtn().setVisible(false);
        timePanels.getPlusBtn().setVisible(true);
        timePanels.getMinusBtn().setVisible(true);
        timePanels.getInputOkButton().setText("开始逛街");

        // timePanels.getTimerJLabel().setVisible(false);
        // timePanels.getTaskCheckBox().setVisible(false);
        JCheckBox checkBox = timePanels.getTaskCheckBox();
        checkBox.setVisible(true);
        checkBox.setToolTipText("逛街结束时 点击 返回键");

        JCheckBox stopCheckBox = timePanels.getStopCheckBox();
        stopCheckBox.setVisible(false);

        JFramePack.onJComponentActionEvent(e);
    }
}
