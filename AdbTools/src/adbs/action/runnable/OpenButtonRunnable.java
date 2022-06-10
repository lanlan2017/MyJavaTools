package adbs.action.runnable;

import adbs.cmd.AdbCommands;
import adbs.test.Device;
import adbs.test.DeviceRadioBtAcListener;
import adbs.ui.AdbTools;

import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

/**
 * 打开设备 按钮事件处理程序
 */
public class OpenButtonRunnable implements Runnable {
    private String simpleId;

    @Override
    public void run() {
        // 获取选择的id
        String id = DeviceRadioBtAcListener.getId();

        Set<Map.Entry<String, String>> entries = Device.map.entrySet();
        entries.forEach(new Consumer<Map.Entry<String, String>>() {
            @Override
            public void accept(Map.Entry<String, String> stringStringEntry) {
                if (stringStringEntry.getValue().equals(id)) {
                    simpleId = stringStringEntry.getKey();
                }
            }
        });

        // 如果存在id
        if (id != null && !"".equals(id)) {
            System.out.println("scrcpy.exe 打开镜像");
            // 使用scrcpy.exe命令打开设备
            // AdbCommands.runAbdCmd("scrcpy.exe -s " + id + " --turn-screen-off --stay-awake -m 768 --window-title 182 --always-on-top");
            // AdbCommands.runAbdCmd("scrcpy.exe -s " + id + " --turn-screen-off --stay-awake -m 768 --window-title " + id + " --always-on-top");
            AdbCommands.runAbdCmd("scrcpy.exe -s " + id + " --turn-screen-off --stay-awake -m 768 --window-title " + simpleId + " --always-on-top");
        }
        System.out.println("scrcpy.exe 已关闭");
    }
}
