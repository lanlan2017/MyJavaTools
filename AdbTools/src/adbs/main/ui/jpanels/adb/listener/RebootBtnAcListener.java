package adbs.main.ui.jpanels.adb.listener;

import adbs.cmd.AdbCommands;
import adbs.main.AdbTools;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 重启按钮事件处理程序
 */
public class RebootBtnAcListener implements ActionListener {
    /**
     * 弹窗的父容器
     */
    private final JPanel jPanel;

    private final String code;

    public RebootBtnAcListener(JPanel jPanel, String code) {
        this.jPanel = jPanel;
        this.code = code;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 获取事件的按钮
        AbstractButton button = (AbstractButton) e.getSource();
        // button.setFocusPainted(false);
        int returnVal = JOptionPane.showConfirmDialog(jPanel, "确认" + button.getText() + "?");
        // 如果选择的是确认按键
        if (returnVal == JOptionPane.OK_OPTION) {
            // 获取选中的adb设备的序列号
            String id = AdbTools.device.getId();
            // 拼接重启代码
            String adbCmd = "adb -s " + id + " " + code;
            // System.out.println("adbCmd = " + adbCmd);
            // 启动cmd进程执行adb命令
            AdbCommands.runAbdCmd(adbCmd);
        }
    }
}
