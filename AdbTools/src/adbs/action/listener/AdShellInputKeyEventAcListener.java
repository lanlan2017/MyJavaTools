package adbs.action.listener;

import adbs.buttons.JButtons;
import adbs.cmd.AdbCommands;
import adbs.test.DeviceRadioBtAcListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class AdShellInputKeyEventAcListener implements ActionListener {
    protected String key;

    public abstract void setKey();

    @Override
    public void actionPerformed(ActionEvent e) {
        // JButton button = (JButton) e.getSource();
        // // 释放焦点
        // button.setFocusPainted(false);
        JButtons.setFocusPainted(e);

        String id = DeviceRadioBtAcListener.getId();
        // 调用子类的方法，设置键值
        setKey();
        if (id != null && !"".equals(id) && key != null && !"".equals(key)) {
            AdbCommands.runAbdCmd("adb -s " + id + " shell input keyevent " + key);
        }
    }
}
