package adbs.action.listener;

import adbs.action.model.InputPanelModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WaitReturnButtonActionListener implements ActionListener {
    JFrame frame;
    InputPanelModel inputPanelModel;

    public WaitReturnButtonActionListener(JFrame frame, InputPanelModel inputPanelModel) {
        this.frame = frame;
        this.inputPanelModel = inputPanelModel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        inputPanelModel.getInputPanel().setVisible(true);
        inputPanelModel.getTimeLable().setText("等待时间:");
        inputPanelModel.getTimeRadioPanel().setVisible(true);
        inputPanelModel.getInputOkButton().setText("开始等待");
        inputPanelModel.getInput1().setColumns(2);
        inputPanelModel.getInput2().setVisible(false);
        frame.pack();
    }


}
