package adbs.main.auto.ui.jpanels.input.listener;

import adbs.main.auto.ui.inout.InOutputModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 减少按钮监听器
 */
public class MinusBtnAcListener implements ActionListener {
    private JTextField input1;
    private JTextField input2;

    public MinusBtnAcListener(InOutputModel inOutputModel) {
        input1 = inOutputModel.getInputPanels().getInput1();
        input2 = inOutputModel.getInputPanels().getInput2();
    }

    public MinusBtnAcListener(JTextField input1, JTextField input2) {
        this.input1 = input1;
        this.input2 = input2;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 如果输入框1 可见的话
        if (input1.isVisible()) {
            int value1 = Integer.parseInt(input1.getText());
            // 如果输入框2也可见的话
            if (input2.isVisible()) {
                // 输入框1，输入框2都可见
                int value2;
                if (value1 == 150) {
                    value1 = 120;
                    value2 = 150;
                } else if (value1 == 120) {
                    value1 = 90;
                    value2 = 120;
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
                // 输入框1可见，输入框2不可见
                switch (value1) {
                    case 1200:
                        value1 = 185;
                        break;
                    case 185:
                        value1 = 155;
                        break;
                    case 155:
                        value1 = 125;
                        break;
                    case 125:
                        value1 = 95;
                        break;
                    case 95:
                        value1 = 65;
                        break;
                    case 65:
                        value1 = 35;
                        break;
                    case 35:
                        value1 = 30;
                        break;
                    case 30:
                        value1 = 20;
                        break;
                    case 20:
                        value1 = 15;
                        break;
                    case 15:
                        value1 = 10;
                        break;
                    case 10:
                        value1 = 8;
                        break;
                    case 8:
                        value1 = 5;
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
