package adbs.action.listener;

import adbs.action.listener.abs.ButtonFocusReleaseActionListener;
import adbs.action.model.InputPanelModel;
import adbs.test.auto.ui.InputPanels;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class WaitReturnButtonActionListener extends ButtonFocusReleaseActionListener {
    JFrame frame;
    // InputPanelModel inputPanelModel;
    private InputPanels inputPanels;

    // public WaitReturnButtonActionListener(JFrame frame, InputPanelModel inputPanelModel) {
    //     this.frame = frame;
    //     this.inputPanelModel = inputPanelModel;
    // }
    public WaitReturnButtonActionListener(JFrame frame, InputPanels inputPanels) {
        this.frame = frame;
        this.inputPanels = inputPanels;
    }

    @Override
    protected void actionEvent(ActionEvent e) {
        // inputPanelModel.getInputPanel().setVisible(true);
        // inputPanelModel.getTimeLable().setText("等待时间(s):");
        // inputPanelModel.getTimeRadioPanel().setVisible(true);
        // inputPanelModel.getInputOkButton().setText("开始等待");
        // inputPanelModel.getInput1().setColumns(2);
        // inputPanelModel.getInput2().setVisible(false);
        // inputPanelModel.getPlusBtn().setVisible(false);
        // inputPanelModel.getMinusBtn().setVisible(false);


        // 测试替换
        inputPanels.getInputPanel().setVisible(true);
        inputPanels.getTimeLable().setText("等待时间(s):");
        inputPanels.getTimeRadioPanel().setVisible(true);
        inputPanels.getInputOkButton().setText("开始等待");
        inputPanels.getInput1().setColumns(2);
        inputPanels.getInput2().setVisible(false);
        inputPanels.getPlusBtn().setVisible(false);
        inputPanels.getMinusBtn().setVisible(false);

        frame.pack();
    }
}
