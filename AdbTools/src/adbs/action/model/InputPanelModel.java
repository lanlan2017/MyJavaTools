package adbs.action.model;

import javax.swing.*;

/**
 * 定义输入面板模型
 */
public class InputPanelModel {
    private JPanel inputPanel;
    private JLabel timeLable;
    private JPanel timeRadioPanel;
    private JRadioButton radioButton15s;
    private JRadioButton radioButton35s;
    private JRadioButton radioButton75s;
    private JTextField input1;
    private JTextField input2;
    private JButton inputOkButton;

    public InputPanelModel(JPanel inputPanel, JLabel timeLable, JPanel timeRadioPanel, JRadioButton radioButton15s, JRadioButton radioButton35s, JRadioButton radioButton75s, JTextField input1, JTextField input2, JButton inputOkButton) {
        this.inputPanel = inputPanel;
        this.timeLable = timeLable;
        this.timeRadioPanel = timeRadioPanel;
        this.radioButton15s = radioButton15s;
        this.radioButton35s = radioButton35s;
        this.radioButton75s = radioButton75s;
        this.input1 = input1;
        this.input2 = input2;
        this.inputOkButton = inputOkButton;

        ButtonGroup bg = new ButtonGroup();
        bg.add(radioButton15s);
        bg.add(radioButton35s);
        bg.add(radioButton75s);
        radioButton15s.addActionListener(e -> input1.setText(String.valueOf(15)));
        radioButton35s.addActionListener(e -> input1.setText(String.valueOf(35)));
        radioButton75s.addActionListener(e -> input1.setText(String.valueOf(75)));

        // 刚开始,隐藏输入面板
        inputPanel.setVisible(false);

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

    public JRadioButton getRadioButton75s() {
        return radioButton75s;
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
}
