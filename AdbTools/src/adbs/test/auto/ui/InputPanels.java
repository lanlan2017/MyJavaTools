package adbs.test.auto.ui;

import adbs.test.Device;
import adbs.test.DeviceListener;
import adbs.test.auto.ui.config.FlowLayouts;
import adbs.test.auto.ui.config.Fonts;
import tools.swing.button.AbstractButtons;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InputPanels {
    final JPanel inputPanel;
    JLabel timeLable;
    JPanel timeRadioPanel;
    JRadioButton radioButton15s;
    JRadioButton radioButton35s;
    JRadioButton radioButton70s;
    JTextField input1;
    JTextField input2;
    JButton plusBtn;
    JButton minusBtn;
    JButton inputOkButton;


    public InputPanels() {

        // 创建输入选择面板
        inputPanel = new JPanel();
        inputPanel.setLayout(FlowLayouts.flowLayoutLeft);
        timeLable = new JLabel("时间(s)");
        // timeRadioPanel = new JPanel(flowLayoutLeft);
        timeRadioPanel = new JPanel();
        radioButton15s = new JRadioButton("15");
        radioButton35s = new JRadioButton("35");
        radioButton70s = new JRadioButton("70");
        timeLable.add(radioButton15s);
        timeLable.add(radioButton35s);
        timeLable.add(radioButton70s);

        input1 = new JTextField(3);

        input1.setFont(Fonts.Consolas_PLAIN_12);

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


        input2 = new JTextField(3);
        input2.setFont(Fonts.Consolas_PLAIN_12);



        plusBtn = new JButton(">");
        minusBtn = new JButton("<");
        inputOkButton = new JButton("确认");

        inputPanel.add(timeLable);
        inputPanel.add(timeRadioPanel);
        inputPanel.add(input1);
        inputPanel.add(input2);
        inputPanel.add(plusBtn);
        inputPanel.add(minusBtn);
        inputPanel.add(inputOkButton);
        inputPanel.setVisible(false);

        AbstractButtons.setMarginInButtonJPanel(inputPanel);
    }

    public JPanel getInputPanel() {
        return inputPanel;
    }

    public JLabel getTimeLable() {
        return timeLable;
    }

    public JPanel getTimeRadioPanel() {
        return timeRadioPanel;
    }

    public JRadioButton getRadioButton15s() {
        return radioButton15s;
    }

    public JRadioButton getRadioButton35s() {
        return radioButton35s;
    }

    public JRadioButton getRadioButton70s() {
        return radioButton70s;
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


    public void showConfirmDialog() {
        // 得到窗体的内容面板
        Container parent = inputPanel.getParent();
        String simpleId = Device.findSimpleId(DeviceListener.getPhoneId());
        int returnVal;
        String message = simpleId + "再次执行?";
        if (parent instanceof Component) {
            System.out.println("---------------是组件");
            Component comp = parent;
            // DeviceListener.getPhoneId();
            returnVal = JOptionPane.showConfirmDialog(comp, message);
        } else {
            returnVal = JOptionPane.showConfirmDialog(null, message);
        }
        // 如果选择的是确认按键
        if (returnVal == JOptionPane.OK_OPTION) {
            if (inputOkButton != null && inputOkButton instanceof JButton) {
                // 触发按钮
                inputOkButton.doClick();
            }
        }
    }
}
