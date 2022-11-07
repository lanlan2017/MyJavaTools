package adbs.action.listener;

import adbs.action.model.InOutputModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 增加按钮监听器
 */
public class PlusBtnAcListener implements ActionListener {
    private JTextField input1;
    private JTextField input2;

    public PlusBtnAcListener(InOutputModel inOutputModel) {
        input1 = inOutputModel.getInputPanels().getInput1();
        input2 = inOutputModel.getInputPanels().getInput2();
    }

    public PlusBtnAcListener(JTextField input1, JTextField input2) {
        this.input1 = input1;
        this.input2 = input2;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
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
                    value1 = 120;
                    value2 = 150;
                } else if (value1 == 120) {
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
                    // case 0:
                    //     value1 = 8;
                    //     break;
                    case 0:
                        value1 = 5;
                        break;
                    case 5:
                        value1 = 8;
                        break;
                    case 8:
                        value1 = 10;
                        break;
                    case 10:
                        value1 = 15;
                        break;
                    case 15:
                        value1 = 20;
                        break;
                    case 20:
                        value1 = 30;
                        break;
                    case 30:
                        value1 = 35;
                        break;
                    case 35:
                        value1 = 65;
                        break;
                    case 65:
                        value1 = 95;
                        break;
                    case 95:
                        value1 = 1200;
                        break;
                    // default:
                    //     value1 = 30;
                    //     break;
                    // case :
                    //     break;
                }
                // if (value1 < 20) {
                //     value1 = 20;
                // }

                input1.setText(String.valueOf(value1));
            }
        }

    }
}
