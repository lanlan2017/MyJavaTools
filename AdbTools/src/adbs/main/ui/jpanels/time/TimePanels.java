package adbs.main.ui.jpanels.time;

import adbs.main.AdbTools;
import adbs.main.ui.config.FlowLayouts;
import adbs.main.ui.config.Fonts;
import tools.swing.button.AbstractButtons;

import javax.swing.*;
import java.awt.*;
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
    private JTextField input1;
    /**
     * 时间输入框2
     */
    private JTextField input2;
    /**
     * 时间增加按钮
     */
    private JButton plusBtn;
    /**
     * 时间减少按钮
     */
    private JButton minusBtn;
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
        timeLabel = new JLabel("时间(s)");
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
        inputOkButton = new JButton("确认");
        timerJLabel = new JLabel("");


        timePanel.add(timeLabel);
        // inputPanel.add(timeRadioPanel);
        timePanel.add(input1);
        timePanel.add(input2);
        timePanel.add(plusBtn);
        timePanel.add(minusBtn);
        timePanel.add(inputOkButton);
        timePanel.add(timerJLabel);
        timePanel.setVisible(false);

        AbstractButtons.setMarginInButtonJPanel(timePanel, 1);
        // AbstractButtons.setMarginInButtonJPanel(inputPanel,0);
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

    public JButton getInputOkButton() {
        return inputOkButton;
    }

    public JLabel getTimerJLabel() {
        return timerJLabel;
    }

    public void showConfirmDialog() {
        // 得到窗体的内容面板
        Container parent = timePanel.getParent();
        // String simpleId = AdbTools.device.getSimpleId();
        String simpleId = AdbTools.getInstance().getDevice().getName();

        int returnVal;
        String message = simpleId + "再次执行?";
        if (parent != null) {
            // System.out.println("---------------是组件");
            Component comp = parent;
            returnVal = JOptionPane.showConfirmDialog(comp, message);
        } else {
            returnVal = JOptionPane.showConfirmDialog(null, message);
        }
        // 如果选择的是确认按键
        if (returnVal == JOptionPane.OK_OPTION) {
            if (inputOkButton != null) {
                // 触发按钮
                inputOkButton.doClick();
            }
        }
    }
}
