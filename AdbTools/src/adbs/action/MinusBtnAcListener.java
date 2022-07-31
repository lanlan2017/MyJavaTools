package adbs.action;

import adbs.action.model.InOutputModel;
import adbs.action.model.InputPanelModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MinusBtnAcListener implements ActionListener {
    private InOutputModel inOutputModel;

    public MinusBtnAcListener(InOutputModel inOutputModel) {
        this.inOutputModel = inOutputModel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        InputPanelModel inputPanelModel = inOutputModel.getInputPanelModel();
        JTextField input1 = inputPanelModel.getInput1();
        JTextField input2 = inputPanelModel.getInput2();


        if (input1.isVisible()) {
            int value1 = Integer.parseInt(input1.getText());
            if (input2.isVisible()) {
                int value2 = Integer.parseInt(input2.getText());
                if (value1 == 150) {
                    value1 = 90;
                    value2 = 150;
                } else if (value1 == 90) {
                    value1 = 60;
                    value2 = 90;
                } else if (value1 == 60) {
                    value1 = 30;
                    value2 = 60;
                } else if (value1 == 30) {
                    value1 = 20;
                    value2 = 40;
                } else if (value1 == 20) {
                    value1 = 15;
                    value2 = 30;
                } else if (value1 == 15) {
                    value1 = 10;
                    value2 = 20;
                } else if (value1 > 7) {
                    value1 = value1 - 1;
                    value2 = value1 * 2;
                } else {
                    return;
                }
                input1.setText(String.valueOf(value1));
                input2.setText(String.valueOf(value2));
            }
            // 第2个不可见
            else {
                switch (value1) {
                    case 1200:
                        value1 = 95;
                        break;
                    case 95:
                        value1 = 65;
                        break;
                    case 65:
                        value1 = 35;
                        break;
                    // case :
                    //     break;
                    // case :
                    //     break;
                }
                input1.setText(String.valueOf(value1));
            }
        }

    }
}
