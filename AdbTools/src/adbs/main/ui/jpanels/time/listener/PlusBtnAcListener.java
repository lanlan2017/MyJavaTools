package adbs.main.ui.jpanels.time.listener;

import adbs.main.AdbTools;
import adbs.main.ui.jpanels.time.TimePanels;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 增加按钮监听器
 */
public class PlusBtnAcListener extends WaitValues implements ActionListener {
    private JTextField input1;
    private JTextField input2;
    private JLabel timerJLabel;

    // int times = 0;
    //
    // public PlusBtnAcListener(InOutputModel inOutputModel) {
    //     input1 = inOutputModel.getTimePanels().getInput1();
    //     input2 = inOutputModel.getTimePanels().getInput2();
    //     timerJLabel = inOutputModel.getTimePanels().getTimerJLabel();
    // }


    public PlusBtnAcListener(TimePanels timePanels) {
        input1 = timePanels.getInput1();
        input2 = timePanels.getInput2();
        timerJLabel = timePanels.getTimerJLabel();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (input1.isVisible()) {
            // 两个输入文本框都可见
            if (input2.isVisible()) {
                // int value1 = Integer.parseInt(input1.getText());
                // int value2;
                // // 5
                // if (value1 < 10) {
                //     value1 = value1 + 1;
                //     value2 = value1 * 2;
                // } else if (value1 == 10) {
                //     value1 = 12;
                //     value2 = 22;
                // } else if (value1 == 12) {
                //     value1 = 15;
                //     value2 = 30;
                // } else if (value1 == 15) {
                //     value1 = 20;
                //     value2 = 40;
                //
                // } else if (value1 == 20) {
                //     value1 = 30;
                //     value2 = 60;
                // } else if (value1 == 30) {
                //     value1 = 40;
                //     value2 = 80;
                // } else if (value1 == 40) {
                //     value1 = 60;
                //     value2 = 90;
                // } else if (value1 == 60) {
                //     value1 = 90;
                //     value2 = 120;
                // } else if (value1 == 90) {
                //     value1 = 120;
                //     value2 = 150;
                // } else if (value1 == 120) {
                //     value1 = 150;
                //     value2 = 240;
                // } else if (value1 == 150) {
                //     value1 = 240;
                //     value2 = 300;
                // } else {
                //     return;
                // }
                // // System.out.print("times = " + times++);
                // // System.out.print(",value1 = " + value1);
                // // System.out.println(",value2 = " + value2);
                //
                //
                // // qujianList.add(new QuJian(5, 10));
                // // System.out.print("times = " + times++);
                // System.out.print("qujianList.add(new QuJian(" + value1);
                // System.out.print("," + value2);
                // System.out.println("));");

                // int value1=ShiPinQuJian.

                if (ShiPinQuJian.hasNext()) {
                    QuJian next = ShiPinQuJian.next();
                    System.out.println("next = " + next);
                    int start = next.getStart();
                    int end = next.getEnd();
                    input1.setText(String.valueOf(start));
                    input2.setText(String.valueOf(end));
                }

                // input1.setText(String.valueOf(value1));
                // input2.setText(String.valueOf(value2));
            }
            // 如果第1个文本框可见，第2个文本框不可见
            else {
                // 下标小于长度
                if (index < values.length - 1) {
                    // 下标加1
                    index++;
                    int value = values[index];
                    // System.out.println("plus index = " + index + ", value = " + value);
                    // 设置文本内容
                    input1.setText(String.valueOf(value));
                    input1.updateUI();
                    int length = getNumLength(value);
                    input1.setColumns(greaterOrEqual4(length));

                    if (value >= 120) {
                        timerJLabel.setText(valueStrs[index]);
                    }
                }
            }
        }
        // 刷新界面
        AdbTools.getInstance().getFrame().pack();
    }
}
