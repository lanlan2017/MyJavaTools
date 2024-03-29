package adbs.main.ui.jpanels.adb.listener;

import adbs.cmd.AdbCommands;
import adbs.main.AdbTools;

import java.awt.event.ActionEvent;

/**
 * 事件处理程序，触发时执行如下命令：<br>
 * adb -s _id_ shell input keyevent _key_
 */
public abstract class AdShellInputKeyEventAcListener extends ButtonFocusReleaseActionListener {
    protected String key;

    public abstract void setKey();

    @Override
    protected void actionEvent(ActionEvent e) {
        // 获取adb设备的ID
        // String id = AdbTools.device.getId();
        String id = AdbTools.getInstance().getDevice().getSerial();
        // 调用实现类的方法，设置键值
        setKey();

        if (id != null && !"".equals(id) && key != null && !"".equals(key)) {
            // 启动cmd进程，执行adb命令
            AdbCommands.runAbdCmd("adb -s " + id + " shell input keyevent " + key);
        }
    }
}
