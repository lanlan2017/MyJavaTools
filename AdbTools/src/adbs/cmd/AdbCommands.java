package adbs.cmd;

import adbs.main.AdbTools;
import adbs.main.ui.jpanels.auto.AdbTap;
import adbs.model.Device;
import adbs.tools.thread.ThreadSleep;
import tools.process.ProcessRunner;
import tools.random.Randoms;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 调用cmd,执行adb命令
 */
public class AdbCommands implements ActivityStr {

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
    public static String swipeBottom2TopOnLeft(Device device) {
        String id = device.getSerial();
        int height = device.getHeight();
        int width = device.getWidth();
        int y30 = (height / 100) * 30;
        int y70 = (height / 100) * 70;
        // return AdbCommands.runAbdCmd("adb -s " + id + " shell input swipe 5 1650 5 700 200");
        return AdbCommands.runAbdCmd("adb -s " + id + " shell input swipe 5 " + y70 + " 5 " + y30 + " 200");
    }

    public static String swipeBotton2TopOnRight(Device device) {
        int width = device.getWidth();
        int rightX = (int) (width * 0.9);
        int height = device.getHeight();
        int yTop = (int) (height * 0.2);
        int yButton = (int) (height * 0.6);

        return AdbCommands.runAbdCmd("adb -s " + device.getSerial() + " shell input swipe " + rightX + " " + yButton + " " + rightX + " " + yTop + " 200");
        // return AdbCommands.runAbdCmd("adb -s " + device.getSerial() + " shell input swipe 5 " + y70 + " 5 " + y30 + " 200");
        // return AdbCommands.runAbdCmd("adb -s " +)
    }

    /**
     * 小心向上滑动
     *
     * @param device
     * @return
     */
    public static void swipeBotton2TopOnRightXiaoXiaoDe(Device device, int times) {
        int width = device.getWidth();
        // int rightX = (int) (width * 0.9);
        int rightX = 5;
        int height = device.getHeight();

        int yBottom = (int) (height * 0.5);
        int yTop = yBottom - 100;

        for (int i = 0; i < times; i++) {

            AdbCommands.runAbdCmd("adb -s " + device.getSerial() + " shell input swipe " + rightX + " " + yBottom + " " + rightX + " " + yTop + " 100");
        }

        // return AdbCommands.runAbdCmd("adb -s " + device.getSerial() + " shell input swipe 5 " + y70 + " 5 " + y30 + " 200");
        // return AdbCommands.runAbdCmd("adb -s " +)
    }

    /**
     * 在手机左侧，从下向上滑动
     */
    public static void swipeTop2BottomOnLeft(String id) {
        // AdbCommands.runAbdCmd("adb -s " + id + " shell input swipe 5 700 5 1650 200");
        AdbCommands.runAbdCmd("adb -s " + id + " shell input swipe 5 800 5 1600 200");
    }

    /**
     * 在手机顶部，从右向左滑动
     * 在手机顶部，从右向左滑动
     *
     * @param device
     */
    public static void swipeRight2LeftOnTop(Device device) {

        String serial = device.getSerial();
        int width = device.getWidth();
        // int height = device.getHeight();

        int rightX = width - 10;
        int y = 100;
        int leftX = 50;

        AdbCommands.runAbdCmd("adb -s " + serial + " shell input swipe " + rightX + " " + y + " " + leftX + " " + y + " 200");
    }

    /**
     * 按下指定设备的home键
     *
     * @param device 需要按home键的设备
     */
    public static void home(Device device) {
        String home = "adb -s " + device.getSerial() + " shell input keyevent 3";
        AdbCommands.runAbdCmd(home);
    }

    public static void returnBtn(Device device) {
        String code = "adb -s " + device.getSerial() + " shell input keyevent BACK";
        // String code = "adb -s " + device.getSerial() + " shell input keyevent 4";
        AdbCommands.runAbdCmd(code);
    }

    public static void taskBtn(Device device) {
        // String code = "adb -s " + device.getSerial() + " shell input keyevent BACK";
        // String code = "adb -s " + device.getSerial() + " shell input keyevent BACK";
        String code = "adb -s " + device.getSerial() + " shell input keyevent 187";
        AdbCommands.runAbdCmd(code);
    }

    /**
     * 按下安卓设备的电源键
     *
     * @param device
     */
    public static void powerBtn(Device device) {
        String serial = device.getSerial();
        AdbCommands.runAbdCmd("adb -s" + serial + " shell input keyevent 26");
    }

    /**
     * 隐藏导航条
     *
     * @param device 要隐藏导航条的设备
     */
    public static void hideNavigationBar(Device device) {
        String code = "adb -s " + device.getSerial() + " shell settings put global policy_control immersive.navigation=*";
        AdbCommands.runAbdCmd(code);
    }

    /**
     * 显示导航栏
     *
     * @param serial
     */
    public static void showNavigationBar(String serial) {
        // // 显示导航栏
        String showNB = "adb -s " + serial + " shell settings put global policy_control null";
        AdbCommands.runAbdCmd(showNB);
    }

    /**
     * 显示导航栏
     */
    public static void showNavigationBar(Device device) {
        // // 显示导航栏
        String showNB = "adb -s " + device.getSerial() + " shell settings put global policy_control null";
        AdbCommands.runAbdCmd(showNB);
    }


    // /**
    //  * 按下安卓设备的电源键
    //  *
    //  * @param device
    //  */
    // private void powerBtn(Device device) {
    //     String serial = device.getSerial();
    //     AdbCommands.runAbdCmd("adb -s" + serial + "shell input keyevent 26");
    // }

    /**
     * 点击任务键，然后向上滑动，杀死右边的APP，接着点击屏幕中点，启动应用。
     *
     * @param device
     */
    public static void killOtherApp(Device device) {
        ThreadSleep.seconds(3);
        AdbCommands.taskBtn(device);
        ThreadSleep.seconds(3);
        AdbCommands.swipeBotton2TopOnRight(device);
        ThreadSleep.seconds(3);
        AdbTap.tapCenterPosition(device);
        ThreadSleep.seconds(3);
    }

    public static void openSetting(Device device) {

        String brand = device.getBrand();
        // System.out.println("brand = " + brand);
        // switch (brand) {
        //     case
        // }
        String brandLC = brand.toLowerCase();
        System.out.println("brand = " + brandLC);
        String code = "";
        switch (brandLC) {
            case "honor":
                code = "adb -s " + device.getSerial() + " shell am start -n " + huaweiSettings;
                break;
            case "oppo":
                code = "adb -s " + device.getSerial() + " shell am start -n " + oppoSetting;
                break;
            default:
                code = "adb -s " + device.getSerial() + " shell am start -n " + androidSettings;
                break;
        }

        // String code = "adb -s " + device.getSerial() + " shell am start -n com.android.settings/.Settings";
        AdbCommands.runAbdCmd(code);
    }


    public static String getPackageVersion(Device device, String packageName) {
        String code = "adb -s " + device.getSerial() + " shell dumpsys package " + packageName + " |findstr versionName";
        // String version = AdbCommands.runAbdCmd(code).trim();
        String version = AdbCommands.runAbdCmd(code);
        System.out.println("version = " + version);
        String versionsFlag = "versionName=";
        if (version.contains(versionsFlag)) {
            version = version.substring(version.indexOf(versionsFlag) + versionsFlag.length());
            System.out.println("version = " + version);

        }
        return version;
    }


    // /**
    //  * 在手机顶部，从右向左滑动
    //  * 在手机顶部，从右向左滑动
    //  *
    //  * @param device
    //  */
    // public static void swipeRight2LeftOnTop() {
    //
    //     // String serial = device.getSerial();
    //     // int width = device.getWidth();
    //     // int height = device.getHeight();
    //
    //     int rightX = width - 10;
    //     int y = 100;
    //     int leftX = 50;
    //
    //     AdbCommands.runAbdCmd("adb -s " + serial + " shell input swipe " + rightX + " " + y + " " + leftX + " " + y + " 200");
    // }

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
