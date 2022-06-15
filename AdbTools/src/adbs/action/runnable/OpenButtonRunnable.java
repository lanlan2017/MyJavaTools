package adbs.action.runnable;

import adbs.cmd.AdbCommands;
import adbs.test.Device;
import adbs.test.DeviceRadioBtAcListener;

import java.awt.*;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

/**
 * 打开设备 按钮事件处理程序
 */
public class OpenButtonRunnable implements Runnable {
    // 保存屏幕宽度
    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    static {
        System.out.println("屏幕宽度:" + screenSize);
    }

    private String simpleId;

    @Override
    public void run() {
        // 获取选择的id
        String id = DeviceRadioBtAcListener.getId();
        simpleId = Device.findSimpleId(id);

        // 如果存在id
        if (id != null && !"".equals(id)) {
            System.out.println("scrcpy.exe 打开镜像");
            // 使用scrcpy.exe命令打开设备
            // AdbCommands.runAbdCmd("scrcpy.exe -s " + id + " --turn-screen-off --stay-awake -m 768 --window-title 182 --always-on-top");
            // AdbCommands.runAbdCmd("scrcpy.exe -s " + id + " --turn-screen-off --stay-awake -m 674 --window-title " + id + " --always-on-top");
            AdbCommands.runAbdCmd("scrcpy.exe -s " + id + " --turn-screen-off -b 2M -m 768 --stay-awake --window-title " + simpleId + " --always-on-top");
        }
        System.out.println("scrcpy.exe 已关闭");
    }


}
