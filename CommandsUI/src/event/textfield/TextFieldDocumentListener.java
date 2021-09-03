package event.textfield;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class TextFieldDocumentListener implements DocumentListener {
    JFrame frame;
    JTextField textField;
    int defaultCols;
    // 默认

    public TextFieldDocumentListener(JFrame frame, JTextField textField) {
        this.frame = frame;
        this.textField = textField;
        this.defaultCols = textField.getColumns();
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        // System.out.println("有插入内容啦");
        int textAreaLen = textField.getText().length();
        // 当输入的内容比文本框的默认列数大时
        if (textAreaLen >= textField.getColumns()) {
            // 增加文本框的列数
            textField.setColumns(textAreaLen + 1);
            frame.pack();
        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        // System.out.println("有删除内容啦");
        int textAreaLen = textField.getText().length();
        // 文本的字数大于默认的宽度并小于
        if (textField.getColumns() > defaultCols) {
            // 设置列数问字符数字+1
            textField.setColumns(textAreaLen + 1);
            frame.pack();
        }
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
    }
}