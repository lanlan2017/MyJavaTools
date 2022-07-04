package adbs.action.runnable.open;

import adbs.cmd.CmdRun;

/**
 * 调用scrcpy.exe程序
 */
public class OpenScrcpy {
    public static void main(String[] args) {
        switch (args.length) {
            case 1:
                break;
            case 2:
                String id = args[0];
                String simpleId = args[1];
                // AdbCommands.runAbdCmd("scrcpy.exe -s " + id + " --turn-screen-off -b 2M -m 768 --stay-awake --window-title " + simpleId + " --always-on-top");
                String code = "scrcpy.exe -s " + id + " --turn-screen-off -b 2M -m 768 --stay-awake --window-title " + simpleId + " --always-on-top";
                System.out.println(code);
                CmdRun.run(code);
                // CmdRun.run("F:\\Program Files\\scrcpy-win64-v1.18\\scrcpy.exe -s " + id + " --turn-screen-off -b 2M -m 768 --stay-awake --window-title " + simpleId + " --always-on-top");
                break;
        }
    }
}
