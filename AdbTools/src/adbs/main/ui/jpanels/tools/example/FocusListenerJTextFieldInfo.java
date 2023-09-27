package adbs.main.ui.jpanels.tools.example;

import javax.swing.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class FocusListenerJTextFieldInfo implements FocusListener {
    /**
     * 提示信息
     */
    String info;
    /**
     * 文本框
     */
    JTextField jTextField;

    public FocusListenerJTextFieldInfo(String info, JTextField jTextField) {
        this.info = info;
        this.jTextField = jTextField;
    }

    @Override
    public void focusGained(FocusEvent e) {
        //获得焦点的时候,清空提示文字
        String jTextFieldText = jTextField.getText();
        if (jTextFieldText.equals(info)) {
            jTextField.setText("");
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        //失去焦点的时候,判断如果为空,就显示提示文字
        String temp = jTextField.getText();
        if (temp.equals("")) {
            jTextField.setText(info);
        }
    }
}