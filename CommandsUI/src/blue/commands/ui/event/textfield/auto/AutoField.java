package blue.commands.ui.event.textfield.auto;

import blue.commands.ui.event.textfield.JComboBoxActionListener;
import blue.commands.ui.event.textfield.TextFieldDocumentListener2;
import blue.commands.ui.event.textfield.TextFieldFocusAdapter;
import blue.commands.ui.event.textfield.TextFieldKeyListener2;
import com.formdev.flatlaf.FlatLightLaf;
import ui.key.MapKeyList;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;
import java.util.Iterator;

public class AutoField {

    public static boolean isAdjusting(JComboBox jComboBox) {
        if (jComboBox.getClientProperty("is_adjusting") instanceof Boolean) {
            return (Boolean) jComboBox.getClientProperty("is_adjusting");
        }
        return false;
    }

    public static void setAdjusting(JComboBox jComboBox, boolean adjusting) {
        jComboBox.putClientProperty("is_adjusting", adjusting);
    }

    /**
     * 给文本框添加自动补全功能
     *
     * @param textField
     * @param jComboBox
     */
    public static void setupAutoComplete2(JFrame frame, JTextField textField, JComboBox jComboBox, JTextArea textArea, JPanel scrollPaneFather) {
        // 设置帮助内容
        Collection<String> items = MapKeyList.getKeysList();
        DefaultComboBoxModel model = (DefaultComboBoxModel) jComboBox.getModel();
        // 设置JComboBox
        // setAdjusting(jComboBox, false);
        setAdjusting(jComboBox, true);
        // 给JComBox添加选项
        Iterator<String> it = items.iterator();
        while (it.hasNext()) {
            model.addElement(it.next());
        }
        // 设置JComboBox默认不选择任何选项
        jComboBox.setSelectedItem(null);
        // JComboxBox事件监听器
        jComboBox.addActionListener(new JComboBoxActionListener(jComboBox, textField));

        // 文本框 注册键盘事件处理器
        // textField.addKeyListener(new TextFieldKeyListener(jComboBox, textField));
        textField.addKeyListener(new TextFieldKeyListener2(frame, textField, jComboBox, textArea, scrollPaneFather));
        // 文本框 得到焦点时的操作
        textField.addFocusListener(new TextFieldFocusAdapter(frame, scrollPaneFather, textArea));
        // 文本框 内容变化
        textField.getDocument().addDocumentListener(new TextFieldDocumentListener2(frame,model, textField, jComboBox, items));

        // 设置文本框的布局管理器
        textField.setLayout(new BorderLayout());
        // 在文本框中添加JComboBox,
        textField.add(jComboBox, BorderLayout.SOUTH);
    }
}