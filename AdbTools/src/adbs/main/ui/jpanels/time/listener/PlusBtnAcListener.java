package adbs.main.ui.jpanels.time.listener;

import adbs.main.AdbTools;
import adbs.main.ui.jframe.JFramePack;
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
                if (ShiPinQuJian.hasNext()) {
                    QuJian next = ShiPinQuJian.next();
                    // System.out.println("next = " + next);
                    int start = next.getStart();
                    int end = next.getEnd();
                    input1.setText(String.valueOf(start));
                    input2.setText(String.valueOf(end));
                }
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
        // AdbTools.getInstance().getFrame().pack();
        JFramePack.pack();
    }
}
