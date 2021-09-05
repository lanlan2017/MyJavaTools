package com.blue.ui.event.textfield;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.ArrayList;

public class TextFieldDocumentListener implements DocumentListener {
    JFrame frame;
    JTextField textField;
    int defaultCols;
    // 缓存文本框中输入的内容
    public static ArrayList<String> previousInput = new ArrayList<>(10);

    public TextFieldDocumentListener(JFrame frame, JTextField textField) {
        this.frame = frame;
        this.textField = textField;
        this.defaultCols = textField.getColumns();
        // 先缓存文本框中的默认内容，可以就是空字符串，这个默认缓存不会被删除
        previousInput.add(textField.getText());
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        // System.out.println("有插入内容啦");
        int textAreaLen = textField.getText().length();
        // 缓存输入的内容
        previousInput.add(textField.getText());

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
        // 第1个元素是默认内容，不可通过Ctrl-Z删除，
        // 第2个之后的元素，则是用户输入的内容，可以全部都删除
        if (previousInput.size() > 1) {
            // 移除最后一个输入
            previousInput.remove(previousInput.size() - 1);
        }
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
        System.out.println("有内容修改啦");
    }
}