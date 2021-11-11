package blue.commands.ui.event.textfield;

import blue.commands.ui.event.textfield.auto.AutoFieldSetting;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.Collection;
import java.util.Iterator;


public class TextFieldDocumentListener implements DocumentListener {
    JFrame frame;
    DefaultComboBoxModel model;
    JTextField textField;
    JComboBox jComboBox;
    Collection<String> items;

    int defaultCols;

    public TextFieldDocumentListener(JFrame frame, DefaultComboBoxModel model, JTextField textField, JComboBox jComboBox, Collection<String> items) {
        this.frame=frame;
        this.model = model;
        this.textField = textField;
        this.jComboBox = jComboBox;
        this.items = items;
        this.defaultCols = textField.getColumns();
    }

    // 当有内容插入的时候
    @Override
    public void insertUpdate(DocumentEvent e) {
        updateList();
        System.out.println("插入命令输出框");
        int textAreaLen = textField.getText().length();
        // 当输入的内容比文本框的默认列数大时
        if (textAreaLen >= textField.getColumns()) {
            // 增加文本框的列数
            textField.setColumns(textAreaLen + 1);
            // textField.setColumns(textAreaLen);
            frame.pack();
        }

        // textField.set
    }

    public void removeUpdate(DocumentEvent e) {
        updateList();
        System.out.println("删除命令输出框");
        int textAreaLen = textField.getText().length();
        // 文本的字数大于默认的宽度
        if (textField.getColumns() > defaultCols) {
            // 设置列数问字符数字+1
            textField.setColumns(textAreaLen + 1);
            // textField.setColumns(textAreaLen);
            frame.pack();
        }
    }

    public void changedUpdate(DocumentEvent e) {
        updateList();
    }

    private void updateList() {
        AutoFieldSetting.setAdjusting(jComboBox, true);
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
        AutoFieldSetting.setAdjusting(jComboBox, false);
    }
}