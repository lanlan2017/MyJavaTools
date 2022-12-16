package adbs.main.auto.ui.jpanels.universal.listener;

import adbs.main.auto.ui.jpanels.adb.listener.ButtonFocusReleaseActionListener;
import adbs.main.auto.ui.jpanels.input.InputPanels;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * 等待按钮事件监听器
 */
public class WaitButtonActionListener extends ButtonFocusReleaseActionListener {
    private JFrame frame;
    private InputPanels inputPanels;

    public WaitButtonActionListener(JFrame frame, InputPanels inputPanels) {
        this.frame = frame;
        this.inputPanels = inputPanels;
    }

    @Override
    protected void actionEvent(ActionEvent e) {

        // 测试替换
        inputPanels.getInputPanel().setVisible(true);
        inputPanels.getTimeLable().setText("时间(s):");
        inputPanels.getTimeRadioPanel().setVisible(true);
        inputPanels.getInputOkButton().setText("开始等待");
        inputPanels.getInput1().setColumns(4);
        inputPanels.getInput1().setText(String.valueOf(30));
        inputPanels.getInput2().setVisible(false);
        // inputPanels.getInput2().setVisible(true);
        // inputPanels.getPlusBtn().setVisible(false);
        // inputPanels.getMinusBtn().setVisible(false);
        inputPanels.getPlusBtn().setVisible(true);
        inputPanels.getMinusBtn().setVisible(true);

        frame.pack();
    }
}
