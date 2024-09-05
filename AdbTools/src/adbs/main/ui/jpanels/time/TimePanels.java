package adbs.main.ui.jpanels.time;

import adbs.main.ui.config.FlowLayouts;
import adbs.main.ui.config.Fonts;
import adbs.main.ui.jpanels.time.listener.InputOkButtonActionListener;
import adbs.main.ui.jpanels.time.listener.MinusBtnAcListener;
import adbs.main.ui.jpanels.time.listener.PlusBtnAcListener;
import tools.swing.button.AbstractButtons;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * 时间选择面板
 */
public class TimePanels {


    private final JPanel timePanel;
    /**
     * 时间提示标签
     */
    private JLabel timeLabel;
    /**
     * 时间输入框1
     */
    private volatile JTextField input1;
    /**
     * 时间输入框2
     */
    private volatile JTextField input2;
    /**
     * 时间增加按钮
     */
    private JButton plusBtn;
    /**
     * 时间减少按钮
     */
    private JButton minusBtn;
    /**
     * 时间结束后 是否 是否点击task键
     */
    private JCheckBox taskCheckBox;
    /**
     * 时间结束 后是否自动点击停止按钮
     */
    private JCheckBox stopCheckBox;
    /**
     * 确认按钮
     */
    private JButton inputOkButton;
    /**
     * 倒计时标签
     */
    private JLabel timerJLabel;

    public TimePanels() {
        // 创建输入选择面板
        timePanel = new JPanel();
        timePanel.setLayout(FlowLayouts.flowLayoutLeft);
        timeLabel = new JLabel("时间:s");
        // timeRadioPanel = new JPanel();
        input1 = new JTextField(2);
        input1.setFont(Fonts.Consolas_PLAIN_14);
        input1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    System.out.println("按下回车键");
                    // 触发回车键
                    inputOkButton.doClick();
                }
            }
        });
        input2 = new JTextField(2);
        input2.setFont(Fonts.Consolas_PLAIN_14);


        plusBtn = new JButton(">");
        minusBtn = new JButton("<");

        taskCheckBox = new JCheckBox("t");
        taskCheckBox.setToolTipText("等待结束后 自动点击 task按钮");
        taskCheckBox.setVisible(false);

        stopCheckBox = new JCheckBox("s");
        stopCheckBox.setToolTipText("等待结束后 自动点击 停止按钮");
        stopCheckBox.setVisible(false);

        inputOkButton = new JButton("确认");
        timerJLabel = new JLabel("");


        timePanel.add(timeLabel);
        // inputPanel.add(timeRadioPanel);
        timePanel.add(input1);
        timePanel.add(input2);
        timePanel.add(plusBtn);
        timePanel.add(minusBtn);
        timePanel.add(taskCheckBox);
        timePanel.add(stopCheckBox);
        timePanel.add(inputOkButton);
        timePanel.add(timerJLabel);
        timePanel.setVisible(false);

        AbstractButtons.setMarginInButtonJPanel(timePanel, 1);
        // AbstractButtons.setMarginInButtonJPanel(inputPanel,0);
        plusBtn.addActionListener(new PlusBtnAcListener(this));
        minusBtn.addActionListener(new MinusBtnAcListener(this));
        inputOkButton.addActionListener(new InputOkButtonActionListener());


    }

    public JPanel getTimePanel() {
        return timePanel;
    }

    public JLabel getTimeLabel() {
        return timeLabel;
    }

    public JTextField getInput1() {
        return input1;
    }

    public JTextField getInput2() {
        return input2;
    }

    public JButton getPlusBtn() {
        return plusBtn;
    }

    public JButton getMinusBtn() {
        return minusBtn;
    }

    public JCheckBox getTaskCheckBox() {
        return taskCheckBox;
    }

    public JCheckBox getStopCheckBox() {
        return stopCheckBox;
    }

    public JButton getInputOkButton() {
        return inputOkButton;
    }

    public JLabel getTimerJLabel() {
        return timerJLabel;
    }
}
