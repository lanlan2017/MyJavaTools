package adbs.action.listener;

import adbs.action.model.InOutputModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 增加按钮监听器
 */
public class PlusBtnAcListener implements ActionListener {
    private InOutputModel inOutputModel;

    public PlusBtnAcListener(InOutputModel inOutputModel) {
        this.inOutputModel = inOutputModel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JTextField input1 = inOutputModel.getInputPanelModel().getInput1();
        JTextField input2 = inOutputModel.getInputPanelModel().getInput2();

        if (input1.isVisible()) {
            // 两个输入文本框都可见
            if (input2.isVisible()) {
                int value1 = Integer.parseInt(input1.getText());
                int value2 = Integer.parseInt(input2.getText());
                //7-14
                //8-16
                //9-18
                //10-20
                //20-40
                //40-60
                //60-90
                //90-150
                //150-240
                if (value1 < 10) {
                    value1 = value1 + 1;
                    value2 = value1 * 2;
                } else if (value1 == 10) {
                    value1 = 15;
                    value2 = 30;

                } else if (value1 == 15) {
                    value1 = 20;
                    value2 = 40;

                } else if (value1 == 20) {
                    value1 = 30;
                    value2 = 60;
                } else if (value1 == 30) {
                    value1 = 60;
                    value2 = 90;
                } else if (value1 == 60) {
                    value1 = 90;
                    value2 = 150;
                } else if (value1 == 90) {
                    value1 = 150;
                    value2 = 240;
                } else {
                    return;
                }
                input1.setText(String.valueOf(value1));
                input2.setText(String.valueOf(value2));
            }
            // 如果第1个文本框可见，第2个文本框不可见
            else {
                int value1 = Integer.parseInt(input1.getText());
                //    35
                //    65
                //    1200
                switch (value1) {
                    case 35:
                        value1 = 65;
                        break;
                    case 65:
                        value1 = 95;
                        break;
                    case 95:
                        value1 = 1200;
                        break;
                    // case :
                    //     break;
                }

                input1.setText(String.valueOf(value1));
            }
        }

    }
}
