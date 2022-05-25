package adbs.action.runnable;

import adbs.cmd.AdbCommands;
import adbs.test.DeviceRadioBtAcListener;
import adbs.ui.AdbTools;

/**
 * 打开设备 按钮事件处理程序
 */
public class OpenButtonRunnable implements Runnable {

    @Override
    public void run() {
        AdbTools.setIsRunning(this);
        // 获取选择的id
        String id = DeviceRadioBtAcListener.getId();
        // 如果存在id
        if (id != null && !"".equals(id)) {
            // 使用scrcpy.exe命令打开设备
            AdbCommands.runAbdCmd("scrcpy.exe -s " + id + " --turn-screen-off --stay-awake -m 768 --window-title 182 --always-on-top");
        }
    }
}
