package adbs.main.ui.jpanels.time.listener;

import adbs.main.AdbTools;
import adbs.main.ui.jpanels.time.TimePanels;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 减少按钮监听器
 */
public class MinusBtnAcListener extends WaitValues implements ActionListener {
    private JTextField input1;
    private JTextField input2;
    /**
     * 倒计时标签
     */
    private JLabel timerJLabel;

    // public MinusBtnAcListener(InOutputModel inOutputModel) {
    //     input1 = inOutputModel.getTimePanels().getInput1();
    //     input2 = inOutputModel.getTimePanels().getInput2();
    //     timerJLabel = inOutputModel.getTimePanels().getTimerJLabel();
    // }

    public MinusBtnAcListener(TimePanels timePanels) {
        input1 = timePanels.getInput1();
        input2 = timePanels.getInput2();
        timerJLabel = timePanels.getTimerJLabel();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 如果输入框1 可见的话
        if (input1.isVisible()) {
            int value1 = Integer.parseInt(input1.getText());
            // 如果输入框2也可见的话
            if (input2.isVisible()) {
                // // 输入框1，输入框2都可见
                // int value2;
                // if (value1 == 240) {
                //     value1 = 150;
                //     value2 = 240;
                // } else if (value1 == 150) {
                //     value1 = 120;
                //     value2 = 150;
                // } else if (value1 == 120) {
                //     value1 = 90;
                //     value2 = 120;
                // } else if (value1 == 90) {
                //     value1 = 60;
                //     value2 = 90;
                // }
                // else if (value1 == 60) {
                //     value1 = 40;
                //     value2 = 80;
                // }
                // else if (value1 == 40) {
                //     value1 = 30;
                //     value2 = 60;
                // }
                // else if (value1 == 30) {
                //     value1 = 20;
                //     value2 = 40;
                // } else if (value1 == 20) {
                //     value1 = 15;
                //     value2 = 30;
                // } else if (value1 == 15) {
                //     value1 = 12;
                //     value2 = 22;
                // } else if (value1 == 12) {
                //     value1 = 10;
                //     value2 = 22;
                // } else if (value1 > 5) {
                //     value1 = value1 - 1;
                //     value2 = value1 * 2;
                // } else {
                //     return;
                // }
                // input1.setText(String.valueOf(value1));
                // input2.setText(String.valueOf(value2));

                if (ShiPinQuJian.hasPrevious()) {
                    QuJian previous = ShiPinQuJian.previous();
                    System.out.println("previous = " + previous);
                    // QuJian next = previous;
                    int start = previous.getStart();
                    int end = previous.getEnd();
                    input1.setText(String.valueOf(start));
                    input2.setText(String.valueOf(end));

                }

            }
            // 第2个不可见
            else {
                // // 输入框1可见，输入框2不可见
                if (index > 0) {
                    index--;
                    int value = values[index];
                    // System.out.println("minus index = " + index + ", value = " + value);
                    input1.setText(String.valueOf(value));
                    int length = getNumLength(value);
                    input1.setColumns(greaterOrEqual4(length));
                    if (value >= 95) {
                        timerJLabel.setText(valueStrs[index]);
                    }
                }
            }
        }
        AdbTools.getInstance().getFrame().pack();
    }

}
