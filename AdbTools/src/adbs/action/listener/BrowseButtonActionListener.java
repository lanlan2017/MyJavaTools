package adbs.action.listener;

import adbs.action.listener.abs.ButtonFocusReleaseActionListener;
import adbs.action.model.InputPanelModel;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class BrowseButtonActionListener extends ButtonFocusReleaseActionListener {
    private JFrame frame;
    private InputPanelModel inputPanelModel;

    public BrowseButtonActionListener(JFrame frame, InputPanelModel inputPanelModel) {
        this.frame = frame;
        this.inputPanelModel = inputPanelModel;
    }

    @Override
    protected void actionEvent(ActionEvent e) {
        inputPanelModel.getInputPanel().setVisible(true);
        inputPanelModel.getTimeLable().setText("浏览时间(s):");
        inputPanelModel.getTimeRadioPanel().setVisible(true);
        inputPanelModel.getInput1().setText(String.valueOf(30));
        inputPanelModel.getInput2().setVisible(false);
        inputPanelModel.getInputOkButton().setText("开始浏览");
        frame.pack();
    }
}
