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
                if (ShiPinQuJian.hasPrevious()) {
                    QuJian previous = ShiPinQuJian.previous();
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
