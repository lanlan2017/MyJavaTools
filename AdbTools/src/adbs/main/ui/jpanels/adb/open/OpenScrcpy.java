package adbs.main.ui.jpanels.adb.open;

import adbs.cmd.CmdRun;

/**
 * 调用scrcpy.exe程序打开手机镜像
 */
public class OpenScrcpy {
    public static void main(String[] args) {
        String id;
        String title;
        switch (args.length) {
            case 1:
                id = args[0];
                scrcpyCall(id, id);
                break;
            case 2:
                id = args[0];
                title = args[1];
                scrcpyCall(id, title);
                break;
        }
    }

    /**
     * 调用scrcpy.exe
     * @param id 设备id
     * @param title 窗口标题
     */
    private static void scrcpyCall(String id, String title) {
        // AdbCommands.runAbdCmd("scrcpy.exe -s " + id + " --turn-screen-off -b 2M -m 768 --stay-awake --window-title " + title + " --always-on-top");
        // CmdRun.run("F:\\Program Files\\scrcpy-win64-v1.18\\scrcpy.exe -s " + id + " --turn-screen-off -b 2M -m 768 --stay-awake --window-title " + title + " --always-on-top");
        // String code = "scrcpy.exe -s " + id + " --turn-screen-off -b 2M -m 768 --stay-awake --window-title " + title + " --always-on-top";
        String code = "scrcpy.exe -s " + id + " --turn-screen-off -b 2M -m 768 --stay-awake --window-title " + title;
        System.out.println(code);
        CmdRun.run(code);
    }
}
