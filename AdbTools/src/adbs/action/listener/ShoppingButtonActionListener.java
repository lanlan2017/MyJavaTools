package adbs.action.listener;

import adbs.action.listener.abs.ButtonFocusReleaseActionListener;
import adbs.test.auto.ui.InputPanels;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ShoppingButtonActionListener extends ButtonFocusReleaseActionListener {
    private JFrame frame;
    private InputPanels inputPanels;



    public ShoppingButtonActionListener(JFrame frame, InputPanels inputPanels) {
        this.frame = frame;
        this.inputPanels = inputPanels;
    }

    @Override
    protected void actionEvent(ActionEvent e) {
        inputPanels.getInputPanel().setVisible(true);
        inputPanels.getTimeLable().setText("时长(s)");
        // 关闭单选按钮组
        inputPanels.getTimeRadioPanel().setVisible(false);
        // 逛街20分钟
        JTextField input1 = inputPanels.getInput1();
        input1.setVisible(true);
        // input1.setText(String.valueOf(20 * 60));
        input1.setText(String.valueOf(35));
        input1.setColumns(4);
        inputPanels.getInput2().setVisible(false);

        // inputPanels.getPlusBtn().setVisible(false);
        // inputPanels.getMinusBtn().setVisible(false);
        inputPanels.getPlusBtn().setVisible(true);
        inputPanels.getMinusBtn().setVisible(true);

        inputPanels.getInputOkButton().setText("开始逛街");






        frame.pack();
    }
}
