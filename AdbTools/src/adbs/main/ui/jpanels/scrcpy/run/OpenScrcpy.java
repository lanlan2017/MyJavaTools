package adbs.main.ui.jpanels.scrcpy.run;

import adbs.cmd.CmdRun;

/**
 * 调用scrcpy.exe程序打开手机镜像
 */
public class OpenScrcpy {
    public static void main(String[] args) {
        String serial;
        String title;
        String window_width;
        String code = "";
        switch (args.length) {
            case 1:
                serial = args[0];
                code = scrcpyCall(serial, serial);
                break;
            case 2:
                serial = args[0];
                title = args[1];
                code = scrcpyCall(serial, title);
                break;
            case 3:
                serial = args[0];
                title = args[1];
                window_width = args[2];
                code = scrcpyCall(serial, title, window_width);

        }
        // 打印执行的命令
        System.out.println(code);
        // 创建cmd进程，执行scrcpy.exe命令
        CmdRun.run(code);
    }

    private static String scrcpyCall(String id, String title, String window_hight) {
        String code;
        title = portAbbr(title);
        // 对以指定字符串开头的设备
        if (title.startsWith("HuaWei")) {
            // 启动scrcpy.exe镜像的时候不息屏
            code = "scrcpy.exe -s " + id + " -b 2M --stay-awake --window-title " + title + " -m 600 --window-height=" + window_hight;
        } else {
            // 如果是高度的话，则设置高度
            if (window_hight.matches("\\d+")) {
                // 其他设备，启动scrcpy.exe镜像时，关闭屏幕
                code = "scrcpy.exe -s " + id + " --turn-screen-off -b 2M --stay-awake --window-title " + title + " -m 600 --window-height=" + window_hight;
                // code="scrcpy.exe -s "+
            } else {
                code = "scrcpy.exe -s " + id + " --turn-screen-off --stay-awake --window-title " + title;
            }
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
        title = portAbbr(title);
        String code;
        // 对以指定字符串开头的设备
        if (title.startsWith("HuaWei")) {
            // 启动scrcpy.exe镜像的时候不息屏
            code = "scrcpy.exe -s " + id + " -b 2M -m 600 --stay-awake --window-title " + title;
        } else {
            // 其他设备，启动scrcpy.exe镜像时，关闭屏幕
            code = "scrcpy.exe -s " + id + " --turn-screen-off -b 2M -m 600 --stay-awake --window-title " + title;
        }
        return code;
    }


    private static String portAbbr(String ip_port) {
        if (ip_port.matches("[0-9.:]+")) {
            System.out.println("序列号是IP地址");
            ip_port = ip_port.substring(ip_port.length() - 2);
        }
        return ip_port;
    }
}
