package adbs.action.model;

import adbs.action.listener.MinusBtnAcListener;
import adbs.action.listener.PlusBtnAcListener;
import adbs.main.auto.listener.Device;
import adbs.main.auto.listener.DeviceListener;
import adbs.main.auto.ui.InputPanels;

import javax.swing.*;
import java.awt.*;

/**
 * 定义输入面板模型
 */
public class InputPanelModel {

    private JPanel inputPanel;
    private JLabel timeLable;
    private JPanel timeRadioPanel;
    private JRadioButton radioButton15s;
    private JRadioButton radioButton35s;
    private JRadioButton radioButton70s;
    private JTextField input1;
    private JTextField input2;
    private JButton inputOkButton;
    private JButton plusBtn;
    private JButton minusBtn;

    public InputPanelModel(JPanel inputPanel, JLabel timeLable, JPanel timeRadioPanel, JRadioButton radioButton15s, JRadioButton radioButton35s, JRadioButton radioButton70s, JTextField input1, JTextField input2, JButton inputOkButton, JButton plusBtn, JButton minusBtn) {
        this.inputPanel = inputPanel;
        this.timeLable = timeLable;
        this.timeRadioPanel = timeRadioPanel;
        this.radioButton15s = radioButton15s;
        this.radioButton35s = radioButton35s;
        this.radioButton70s = radioButton70s;
        this.input1 = input1;
        this.input2 = input2;
        this.inputOkButton = inputOkButton;
        this.plusBtn = plusBtn;
        this.minusBtn = minusBtn;

        ButtonGroup bg = new ButtonGroup();
        bg.add(radioButton15s);
        bg.add(radioButton35s);
        bg.add(radioButton70s);

        radioButton15s.addActionListener(e -> input1.setText(String.valueOf(15)));
        radioButton35s.addActionListener(e -> input1.setText(String.valueOf(35)));
        radioButton70s.addActionListener(e -> input1.setText(String.valueOf(70)));

        // 增加按钮
        plusBtn.addActionListener(new PlusBtnAcListener(input1, input2));
        // 减少按钮
        minusBtn.addActionListener(new MinusBtnAcListener(input1, input2));

        // // 输入面板等待按钮
        // inputOkButton.addActionListener(new InputOkButtonActionListener(inOutputModel));

        // 刚开始,隐藏输入面板
        inputPanel.setVisible(false);
    }

    public InputPanelModel(InputPanels inputPanels) {
        // new InputPanelModel(inputPanels.getInputPanel(), inputPanels.getTimeLable(), inputPanels.getTimeRadioPanel(), inputPanels.getRadioButton15s(), inputPanels.getRadioButton35s(), inputPanels.getRadioButton70s(), inputPanels.getInput1(), inputPanels.getInput2(), inputPanels.getInputOkButton(), inputPanels.getPlusBtn(), inputPanels.getMinusBtn());
        this(inputPanels.getInputPanel(), inputPanels.getTimeLable(), inputPanels.getTimeRadioPanel(), inputPanels.getRadioButton15s(), inputPanels.getRadioButton35s(), inputPanels.getRadioButton70s(), inputPanels.getInput1(), inputPanels.getInput2(), inputPanels.getInputOkButton(), inputPanels.getPlusBtn(), inputPanels.getMinusBtn());
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

    public JTextField getInput1() {
        return input1;
    }

    public JTextField getInput2() {
        return input2;
    }

    public JButton getInputOkButton() {
        return inputOkButton;
    }

    public JButton getPlusBtn() {
        return plusBtn;
    }

    public JButton getMinusBtn() {
        return minusBtn;
    }

    public void showConfirmDialog() {
        // 得到窗体的内容面板
        Container parent = inputPanel.getParent();
        String simpleId = Device.findSimpleId(DeviceListener.getPhoneId());
        int returnVal;
        String message = simpleId + "再次执行?";
        if (parent instanceof Component) {
            System.out.println("是组件");
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
