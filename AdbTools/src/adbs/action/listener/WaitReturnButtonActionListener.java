package adbs.action.listener;

import adbs.action.listener.abs.ButtonFocusReleaseActionListener;
import adbs.test.auto.ui.InputPanels;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class WaitReturnButtonActionListener extends ButtonFocusReleaseActionListener {
    private JFrame frame;
    private InputPanels inputPanels;

    public WaitReturnButtonActionListener(JFrame frame, InputPanels inputPanels) {
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
        // inputPanels.getPlusBtn().setVisible(false);
        // inputPanels.getMinusBtn().setVisible(false);
        inputPanels.getPlusBtn().setVisible(true);
        inputPanels.getMinusBtn().setVisible(true);

        frame.pack();
    }
}
