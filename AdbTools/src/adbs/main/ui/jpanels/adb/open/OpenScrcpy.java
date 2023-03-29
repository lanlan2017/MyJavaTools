package adbs.main.ui.jpanels.adb.open;

import adbs.cmd.CmdRun;

/**
 * 调用scrcpy.exe程序打开手机镜像
 */
public class OpenScrcpy {
    public static void main(String[] args) {
        String id;
        String title;
        String window_width;
        String code = "";
        switch (args.length) {
            case 1:
                id = args[0];
                code = scrcpyCall(id, id);
                break;
            case 2:
                id = args[0];
                title = args[1];
                code = scrcpyCall(id, title);
                break;
            case 3:
                id = args[0];
                title = args[1];
                window_width = args[2];
                code = scrcpyCall(id, title, window_width);

        }
        // 打印执行的命令
        System.out.println(code);
        // 创建cmd进程，执行scrcpy.exe命令
        CmdRun.run(code);
    }

    private static String scrcpyCall(String id, String title, String window_width) {
        String code;
        // 对以指定字符串开头的设备
        if (title.startsWith("HuaWei")) {
            // 启动scrcpy.exe镜像的时候不息屏
            // code = "scrcpy.exe -s " + id + " -b 2M -m 768 --stay-awake --window-title " + title;
            // code = "scrcpy.exe -s " + id + " -b 2M -m 512 --stay-awake --window-title " + title;
            // code = "scrcpy.exe -s " + id + " -b 2M -m 528 --stay-awake --window-title " + title;
            // code = "scrcpy.exe -s " + id + " -b 2M -m 576 --stay-awake --window-title " + title;
            // code = "scrcpy.exe -s " + id + " -b 2M -m 584 --stay-awake --window-title " + title;
            // code = "scrcpy.exe -s " + id + " -b 2M -m 292 --stay-awake --window-title " + title;
            code = "scrcpy.exe -s " + id + " -b 2M -m 600 --stay-awake --window-title " + title + " --window-width=" + window_width;
            // code = "scrcpy.exe -s " + id + " -b 2M -m 608 --stay-awake --window-title " + title;
            // code = "scrcpy.exe -s " + id + " -b 2M -m 616 --stay-awake --window-title " + title;
        } else {
            // 其他设备，启动scrcpy.exe镜像时，关闭屏幕
            // code = "scrcpy.exe -s " + id + " --turn-screen-off -b 2M -m 768 --stay-awake --window-title " + title;
            // code = "scrcpy.exe -s " + id + " --turn-screen-off -b 2M -m 512 --stay-awake --window-title " + title;
            // code = "scrcpy.exe -s " + id + " --turn-screen-off -b 2M -m 528 --stay-awake --window-title " + title;
            // code = "scrcpy.exe -s " + id + " --turn-screen-off -b 2M -m 536 --stay-awake --window-title " + title;
            // code = "scrcpy.exe -s " + id + " --turn-screen-off -b 2M -m 544 --stay-awake --window-title " + title;
            // code = "scrcpy.exe -s " + id + " --turn-screen-off -b 2M -m 552 --stay-awake --window-title " + title;
            // code = "scrcpy.exe -s " + id + " --turn-screen-off -b 2M -m 560 --stay-awake --window-title " + title;
            // code = "scrcpy.exe -s " + id + " --turn-screen-off -b 2M -m 568 --stay-awake --window-title " + title;
            // code = "scrcpy.exe -s " + id + " --turn-screen-off -b 2M -m 568 --stay-awake --window-title " + title;
            // code = "scrcpy.exe -s " + id + " --turn-screen-off -b 2M -m 576 --stay-awake --window-title " + title;
            // code = "scrcpy.exe -s " + id + " --turn-screen-off -b 2M -m 584 --stay-awake --window-title " + title;
            // code = "scrcpy.exe -s " + id + " --turn-screen-off -b 2M -m 592 --stay-awake --window-title " + title;
            code = "scrcpy.exe -s " + id + " --turn-screen-off -b 2M -m 600 --stay-awake --window-title " + title + " --window-width=" + window_width;
            // code = "scrcpy.exe -s " + id + " --turn-screen-off -b 2M -m 608 --stay-awake --window-title " + title;
            // code = "scrcpy.exe -s " + id + " --turn-screen-off -b 2M -m 616 --stay-awake --window-title " + title;
        }
        return code;
    }

    /**
     * 调用scrcpy.exe
     *
     * @param id    设备id
     * @param title 窗口标题
     */
    private static String scrcpyCall(String id, String title) {
        // AdbCommands.runAbdCmd("scrcpy.exe -s " + id + " --turn-screen-off -b 2M -m 768 --stay-awake --window-title " + title + " --always-on-top");
        // CmdRun.run("F:\\Program Files\\scrcpy-win64-v1.18\\scrcpy.exe -s " + id + " --turn-screen-off -b 2M -m 768 --stay-awake --window-title " + title + " --always-on-top");
        // String code = "scrcpy.exe -s " + id + " --turn-screen-off -b 2M -m 768 --stay-awake --window-title " + title + " --always-on-top";
        String code;
        // 对以指定字符串开头的设备
        if (title.startsWith("HuaWei")) {
            // 启动scrcpy.exe镜像的时候不息屏
            // code = "scrcpy.exe -s " + id + " -b 2M -m 768 --stay-awake --window-title " + title;
            // code = "scrcpy.exe -s " + id + " -b 2M -m 512 --stay-awake --window-title " + title;
            // code = "scrcpy.exe -s " + id + " -b 2M -m 528 --stay-awake --window-title " + title;
            // code = "scrcpy.exe -s " + id + " -b 2M -m 576 --stay-awake --window-title " + title;
            // code = "scrcpy.exe -s " + id + " -b 2M -m 584 --stay-awake --window-title " + title;
            // code = "scrcpy.exe -s " + id + " -b 2M -m 292 --stay-awake --window-title " + title;
            code = "scrcpy.exe -s " + id + " -b 2M -m 600 --stay-awake --window-title " + title;
            // code = "scrcpy.exe -s " + id + " -b 2M -m 608 --stay-awake --window-title " + title;
            // code = "scrcpy.exe -s " + id + " -b 2M -m 616 --stay-awake --window-title " + title;
        } else {
            // 其他设备，启动scrcpy.exe镜像时，关闭屏幕
            // code = "scrcpy.exe -s " + id + " --turn-screen-off -b 2M -m 768 --stay-awake --window-title " + title;
            // code = "scrcpy.exe -s " + id + " --turn-screen-off -b 2M -m 512 --stay-awake --window-title " + title;
            // code = "scrcpy.exe -s " + id + " --turn-screen-off -b 2M -m 528 --stay-awake --window-title " + title;
            // code = "scrcpy.exe -s " + id + " --turn-screen-off -b 2M -m 536 --stay-awake --window-title " + title;
            // code = "scrcpy.exe -s " + id + " --turn-screen-off -b 2M -m 544 --stay-awake --window-title " + title;
            // code = "scrcpy.exe -s " + id + " --turn-screen-off -b 2M -m 552 --stay-awake --window-title " + title;
            // code = "scrcpy.exe -s " + id + " --turn-screen-off -b 2M -m 560 --stay-awake --window-title " + title;
            // code = "scrcpy.exe -s " + id + " --turn-screen-off -b 2M -m 568 --stay-awake --window-title " + title;
            // code = "scrcpy.exe -s " + id + " --turn-screen-off -b 2M -m 568 --stay-awake --window-title " + title;
            // code = "scrcpy.exe -s " + id + " --turn-screen-off -b 2M -m 576 --stay-awake --window-title " + title;
            // code = "scrcpy.exe -s " + id + " --turn-screen-off -b 2M -m 584 --stay-awake --window-title " + title;
            // code = "scrcpy.exe -s " + id + " --turn-screen-off -b 2M -m 592 --stay-awake --window-title " + title;
            code = "scrcpy.exe -s " + id + " --turn-screen-off -b 2M -m 600 --stay-awake --window-title " + title;
            // code = "scrcpy.exe -s " + id + " --turn-screen-off -b 2M -m 608 --stay-awake --window-title " + title;
            // code = "scrcpy.exe -s " + id + " --turn-screen-off -b 2M -m 616 --stay-awake --window-title " + title;
        }
        // // 打印执行的命令
        // System.out.println(code);
        // // 创建cmd进程，执行scrcpy.exe命令
        // CmdRun.run(code);
        return code;
    }
}
