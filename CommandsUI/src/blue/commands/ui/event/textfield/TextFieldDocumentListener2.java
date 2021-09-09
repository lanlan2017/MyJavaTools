package blue.commands.ui.event.textfield;

import blue.commands.ui.event.textfield.auto.AutoField;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.Collection;
import java.util.Iterator;


public class TextFieldDocumentListener2 implements DocumentListener {
    DefaultComboBoxModel model;
    JTextField textField;
    JComboBox jComboBox;
    Collection<String> items;

    public TextFieldDocumentListener2(DefaultComboBoxModel model, JTextField textField, JComboBox jComboBox, Collection<String> items) {
        this.model = model;
        this.textField = textField;
        this.jComboBox = jComboBox;
        this.items = items;
    }

    // 当有内容插入的时候
    @Override
    public void insertUpdate(DocumentEvent e) {
        updateList();
    }

    public void removeUpdate(DocumentEvent e) {
        updateList();
    }

    public void changedUpdate(DocumentEvent e) {
        updateList();
    }

    private void updateList() {
        AutoField.setAdjusting(jComboBox, true);
        // 移除JComboBox中的全部内容
        model.removeAllElements();
        // 读取文本框中的全部内容
        String input = textField.getText();
        // 如果文本框中有内容的话
        if (!input.isEmpty()) {
            // String inputTemp = input.trim();
            // String[] keyStrs = inputTemp.split(" ");
            Iterator<String> it = items.iterator();
            while (it.hasNext()) {
                String item = it.next();
                // 如果输入的内容能在选项列表中查找到的话
                if (item.toLowerCase().startsWith(input.toLowerCase())) {
                    // 就把符合的加入到JComboBox中
                    model.addElement(item);
                }
            }
        }
        // 如果JComboBox中的元素大于0，则弹出显示。
        jComboBox.setPopupVisible(model.getSize() > 0);
        // jComboBox.updateUI();
        // textField.updateUI();
        // 不调整
        AutoField.setAdjusting(jComboBox, false);
    }
}