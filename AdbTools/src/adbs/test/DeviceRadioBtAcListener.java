package adbs.test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeviceRadioBtAcListener implements ActionListener {
    private static String id;
    // DeviceRadioButtonActionListener()

    public static String getId() {
        return id;
    }

    public static void setId(String id) {
        DeviceRadioBtAcListener.id = id;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 获取触发事件的按钮
        AbstractButton button = (AbstractButton) e.getSource();
        // 输出按钮中的文本
        System.out.println("你选择了:" + button.getText());
        setId(button.getText());
    }
}