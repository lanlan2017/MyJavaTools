package adbs.action.listener;

import adbs.action.model.InputPanelModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShoppingButtonActionListener implements ActionListener {
    private JFrame frame;
    private InputPanelModel inputPanelModel;

    public ShoppingButtonActionListener(JFrame frame, InputPanelModel inputPanelModel) {
        this.frame = frame;
        this.inputPanelModel = inputPanelModel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        inputPanelModel.getInputPanel().setVisible(true);
        inputPanelModel.getTimeLable().setText("逛街时长(s)");
        // 关闭单选按钮组
        inputPanelModel.getTimeRadioPanel().setVisible(false);
        // 逛街20分钟
        JTextField input1 = inputPanelModel.getInput1();
        input1.setVisible(true);
        input1.setText(String.valueOf(20 * 60));
        input1.setColumns(4);
        inputPanelModel.getInput2().setVisible(false);
        inputPanelModel.getInputOkButton().setText("开始逛街");
        frame.pack();
    }
}
