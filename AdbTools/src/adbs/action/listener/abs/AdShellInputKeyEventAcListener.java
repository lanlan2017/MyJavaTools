package adbs.action.listener.abs;

import adbs.cmd.AdbCommands;
import adbs.test.DeviceRadioBtAcListener;

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
        String id = DeviceRadioBtAcListener.getId();
        // 调用子类的方法，设置键值
        setKey();
        if (id != null && !"".equals(id) && key != null && !"".equals(key)) {
            AdbCommands.runAbdCmd("adb -s " + id + " shell input keyevent " + key);
        }
    }
}
