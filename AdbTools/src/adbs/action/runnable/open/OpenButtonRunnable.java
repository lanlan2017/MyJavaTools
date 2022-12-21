package adbs.action.runnable.open;

import adbs.cmd.CmdRun;
import adbs.main.AdbTools;

/**
 * 打开设备 按钮事件处理程序
 */
public class OpenButtonRunnable implements Runnable {

    private String simpleId;

    @Override
    public void run() {
        // 获取选择的id
        // String id = DeviceListener.getPhoneId();
        // String id = DeviceListener.getSelectedPhoneId();
        // simpleId = Device.findSimpleId(id);

        String id= AdbTools.device.getId();
         simpleId = AdbTools.device.getSimpleId();
        // 如果存在id
        if (id != null && !"".equals(id)) {
            System.out.println("scrcpy.exe 打开镜像");
            // 使用scrcpy.exe命令打开设备
            // AdbCommands.runAbdCmd("scrcpy.exe -s " + id + " --turn-screen-off --stay-awake -m 768 --window-title 182 --always-on-top");
            // AdbCommands.runAbdCmd("scrcpy.exe -s " + id + " --turn-screen-off --stay-awake -m 674 --window-title " + id + " --always-on-top");
            // AdbCommands.runAbdCmd("scrcpy.exe -s " + id + " --turn-screen-off -b 2M -m 768 --stay-awake --window-title " + simpleId + " --always-on-top");
            // AdbCommands.runAbdCmd("scrcpy.exe -s " + id + " --turn-screen-off -b 2M -m 768 --stay-awake --window-title " + simpleId + " --always-on-top");
            // CmdRun.run("scrcpy.exe -s " + id + " --turn-screen-off -b 2M -m 768 --stay-awake --window-title " + simpleId + " --always-on-top");
            // CmdRun.run("G:\\dev2\\idea_workspace\\MyJavaTools\\runable\\open_scrcpy.bat " + id + " " + simpleId);
            // String code = "G:\\dev2\\idea_workspace\\MyJavaTools\\runable\\open_scrcpy.bat " + id + " " + simpleId;
            // String code = "open_scrcpy.bat " + id + " " + simpleId;
            String code = "adbtools_open_scrcpy.bat " + id + " " + this.simpleId;
            System.out.println("调用另一个Jar:" + code);
            CmdRun.run(code);
        }
        System.out.println("scrcpy.exe 已关闭");
    }
}
