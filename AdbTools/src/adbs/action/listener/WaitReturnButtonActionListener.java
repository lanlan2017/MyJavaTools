package adbs.action.listener;

import adbs.action.listener.abs.ButtonFocusReleaseActionListener;
import adbs.action.model.InputPanelModel;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class WaitReturnButtonActionListener extends ButtonFocusReleaseActionListener {
    JFrame frame;
    InputPanelModel inputPanelModel;

    public WaitReturnButtonActionListener(JFrame frame, InputPanelModel inputPanelModel) {
        this.frame = frame;
        this.inputPanelModel = inputPanelModel;
    }

    @Override
    protected void actionEvent(ActionEvent e) {
        inputPanelModel.getInputPanel().setVisible(true);
        inputPanelModel.getTimeLable().setText("等待时间:");
        inputPanelModel.getTimeRadioPanel().setVisible(true);
        inputPanelModel.getInputOkButton().setText("开始等待");
        inputPanelModel.getInput1().setColumns(2);
        inputPanelModel.getInput2().setVisible(false);
        inputPanelModel.getPlusBtn().setVisible(false);
        inputPanelModel.getMinusBtn().setVisible(false);
        frame.pack();
    }
}
