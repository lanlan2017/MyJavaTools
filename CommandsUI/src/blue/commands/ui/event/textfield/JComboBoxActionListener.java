package blue.commands.ui.event.textfield;

import blue.commands.ui.event.textfield.auto.AutoField;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class JComboBoxActionListener implements ActionListener {
    JComboBox jComboBox;
    JTextField textField;

    public JComboBoxActionListener(JComboBox jComboBox, JTextField textField) {
        this.jComboBox = jComboBox;
        this.textField = textField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //
        if (!AutoField.isAdjusting(jComboBox)) {
            // 如果有选项被选中
            if (jComboBox.getSelectedItem() != null) {
                // 文本框中填写选项的内容。
                textField.setText(jComboBox.getSelectedItem().toString());
            }
        }
    }
}