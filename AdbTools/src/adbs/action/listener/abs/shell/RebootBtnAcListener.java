package adbs.action.listener.abs.shell;

import adbs.cmd.AdbCommands;
import adbs.test.DeviceRadioBtAcListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 重启按钮事件处理程序
 */
public class RebootBtnAcListener implements ActionListener {
    private JFrame frame;
    private String code;

    public RebootBtnAcListener(JFrame frame, String code) {
        this.frame = frame;
        this.code = code;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AbstractButton button = (AbstractButton) e.getSource();
        button.setFocusPainted(false);
        int returnVal = JOptionPane.showConfirmDialog(frame, "确认" + button.getText() + "?");
        // 如果选择的是确认按键
        if (returnVal == JOptionPane.OK_OPTION) {
            String id = DeviceRadioBtAcListener.getId();
            // 重启
            // code = "reboot";
            String adbCmd = "adb -s " + id + " " + code;
            System.out.println("adbCmd = " + adbCmd);
            AdbCommands.runAbdCmd(adbCmd);
        }
    }
}
