package adbs.cmd;

import adbs.main.AdbTools;
import tools.process.ProcessRunner;
import tools.random.Randoms;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 调用cmd,执行adb命令
 */
public class AdbCommands {

    /**
     * 存放命令的List
     */
    private final static List<String> command = new ArrayList<>();

    static {
        // 程序:cmd.exe
        command.add("cmd");
        // 参数:/C 执行字符串指定的命令然后终止
        command.add("/c");
        // 参数:adb命令
        command.add("adb");
    }

    public static void main(String[] args) {
        // AdbCommands.swipeRight2LeftOnTop();
        String id = "192.168.10.4:5555";
        AdbCommands.swipeBottom2TopOnLeft(id);
    }

    /**
     * 单击屏幕右侧
     *
     * @param id     手机的设备ID，可通过adb devices -l查看
     * @param width  手机设备的宽度
     * @param height 手机设备的高度
     */
    public static String clickScreenRightSide(String id, int width, int height) {
        // y的范围位20%到80%之间
        int y = (height / 100) * Randoms.getRandomInt(20, 80);
        // int x = (width / 100) * 98;
        // 假设width=1080，则1080/270=4,4*260=1040,4*269=1076
        // int x = (width / 270) * Randoms.getRandomInt(267, 269);
        int x = (width / 120) * Randoms.getRandomInt(119, 120) - Randoms.getRandomInt(2, 7);
        //adb shell input tap 250 250
        return AdbCommands.runAbdCmd("adb -s " + id + " shell input tap " + x + " " + y);
    }

    /**
     * 在手机左侧，从下向上滑动
     */
    public static String swipeBottom2TopOnLeft(String id) {
        return AdbCommands.runAbdCmd("adb -s " + id + " shell input swipe 5 1600 5 800 200");
    }

    /**
     * 在手机左侧，从下向上滑动
     */
    public static String swipeBottom2TopOnLeft(String id, int height) {
        int y30 = (height / 100) * 30;
        int y70 = (height / 100) * 70;
        // return AdbCommands.runAbdCmd("adb -s " + id + " shell input swipe 5 1650 5 700 200");
        return AdbCommands.runAbdCmd("adb -s " + id + " shell input swipe 5 " + y70 + " 5 " + y30 + " 200");
    }

    /**
     * 在手机左侧，从下向上滑动
     */
    public static void swipeTop2BottomOnLeft(String id) {
        // AdbCommands.runAbdCmd("adb -s " + id + " shell input swipe 5 700 5 1650 200");
        AdbCommands.runAbdCmd("adb -s " + id + " shell input swipe 5 800 5 1600 200");
    }

    /**
     * 运行adb命令
     *
     * @param adbCmd adb命令，必须与adb开头，如adb -s xxx shell
     */
    public static String runAbdCmd(String adbCmd) {
        // 替换最后一行adb命令
        command.set(command.size() - 1, adbCmd);
        System.out.println("command:" + adbCmd);
        // 执行cmd命令
        // runCmd(command);
        return runCmd();
    }

    private static String runCmd() {
        ProcessBuilder builder = new ProcessBuilder(command);
        ProcessRunner runner = new ProcessRunner();
        return runner.runProcess(builder);
        // System.out.println("adbResult = " + adbResult);
    }

    public static boolean ifDeviceNotExist(String adbResult) {
        if (adbResult.startsWith("Error!ExitCode=")) {
            System.out.println("adb命令运行错误，退出程序." + adbResult);
            // System.exit(0);
            // JButton stopBtn = AdbTools.getInstance().getStopBtn();
            // JButton stopBtn = AdbTools.getInstance().getAdbJPanels().getStopBtn();

            JButton stopBtn = AdbTools.getInstance().getUniversalPanels().getBtnStop();

            if (stopBtn != null && stopBtn instanceof JButton) {
                System.out.println("点击停止按钮" + adbResult);
                stopBtn.doClick();
            }
            return true;
            // break;
        }
        return false;
    }
}
