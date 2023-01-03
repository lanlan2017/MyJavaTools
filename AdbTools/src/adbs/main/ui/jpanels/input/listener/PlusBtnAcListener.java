package adbs.main.ui.jpanels.input.listener;

import adbs.main.ui.inout.InOutputModel;

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
                int value2;
                if (value1 < 10) {
                    value1 = value1 + 1;
                    value2 = value1 * 2;
                } else if (value1 == 10) {
                    value1 = 12;
                    value2 = 22;
                } else if (value1 == 12) {
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
                }
                else if (value1 == 120) {
                    value1 = 150;
                    value2 = 240;
                }
                else if (value1 == 150) {
                    value1 = 240;
                    value2 = 300;
                }
                else {
                    return;
                }
                input1.setText(String.valueOf(value1));
                input2.setText(String.valueOf(value2));
            }
            // 如果第1个文本框可见，第2个文本框不可见
            else {
                int value1 = Integer.parseInt(input1.getText());
                switch (value1) {
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
                        value1 = 125;
                        break;
                    case 125:
                        value1 = 155;
                        break;
                    case 155:
                        value1 = 185;
                        break;
                    case 185:
                        value1 = 300;
                        break;
                    case 300:
                        value1 = 600;
                        break;
                    case 600:
                        value1 = 900;
                        break;
                    case 900:
                        value1 = 1200;
                        break;
                    // default:
                    //     value1 = 30;
                    //     break;
                    // case :
                    //     break;
                }
                input1.setText(String.valueOf(value1));
            }
        }

    }
}
