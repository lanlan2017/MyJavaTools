package adbs.cmd;

import tools.process.ProcessRunner;
import tools.random.Randoms;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
     * 按下手机的返回键
     */
    public static void returnButton(String id) {
        // add命令出发返回键
        AdbCommands.runAbdCmd("adb -s " + id + " shell input keyevent 4");
    }

    /**
     * 在手机顶部从右向左滑动
     */
    public static void swipeRight2LeftOnTop(String id) {
        AdbCommands.runAbdCmd("adb -s " + id + " shell input swipe 893 233 73 228 200");
    }

    /**
     * 在手机顶部从右向左滑动
     */
    public static void swipeRight2LeftOnTop(String id, int width, int height) {
        int x_right = (width / 100) * 90;
        int x_left = (width / 100) * 10;
        int y = (height / 100) * 60;
        AdbCommands.runAbdCmd("adb -s " + id + " shell input swipe " + x_right + " " + y + " " + x_left + " " + y + " 200");
    }

    /**
     * 单击屏幕右侧
     *
     * @param id     手机的设备ID，可通过adb devices -l查看
     * @param width  手机设备的宽度
     * @param height 手机设备的高度
     */
    public static void clickScreenRightSide(String id, int width, int height) {
        // Random random=new Random();
        // Randoms.getRandomInt(10,90);
        // random.nextInt()
        // int y = (height / 100) * 10;
        // y的范围位20%到80%之间
        int y = (height / 100) * Randoms.getRandomInt(20, 80);
        // int x = (width / 100) * 98;
        // 假设width=1080，则1080/270=4,4*260=1040,4*269=1076
        int x = (width / 270) * Randoms.getRandomInt(260, 269);
        //adb shell input tap 250 250
        // AdbCommands.runAbdCmd("adb -s " + id + " shell input swipe 893 233 73 228 200");
        AdbCommands.runAbdCmd("adb -s " + id + " shell input tap " + x + " " + y);
    }

    /**
     * 在手机左侧，从下向上滑动
     */
    public static String swipeBottom2TopOnLeft(String id) {
        // return AdbCommands.runAbdCmd("adb -s " + id + " shell input swipe 5 1650 5 700 200");
        return AdbCommands.runAbdCmd("adb -s " + id + " shell input swipe 5 1600 5 800 200");
    }

    /**
     * 在手机左侧，从下向上滑动
     */
    public static String swipeBottom2TopOnLeft(String id, int height) {
        // return AdbCommands.runAbdCmd("adb -s " + id + " shell input swipe 5 1650 5 700 200");
        int y1 = (height / 100) * 30;
        int y2 = (height / 100) * 70;
        return AdbCommands.runAbdCmd("adb -s " + id + " shell input swipe 5 " + y2 + " 5 " + y1 + " 200");
    }

    /**
     * 在手机中间从下向上滑动
     */
    public static String swipeBottom2TopOnMiddle(String id, int width, int height) {
        // return AdbCommands.runAbdCmd("adb -s " + id + " shell input swipe 5 1650 5 700 200");
        int x = (int) (width * 0.5);
        int y1 = (height / 100) * 30;
        int y2 = (height / 100) * 70;
        return AdbCommands.runAbdCmd("adb -s " + id + " shell input swipe " + x + " " + y2 + " " + x + " " + y1 + " 200");
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
        System.out.println("adbCmd = " + adbCmd);
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

    /**
     * 运行cmd命令
     *
     * @param command 保存要执行命令的<code>List&lt;String&gt;</code>。
     */
    private static void runCmd(List<String> command) {
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        // 子进程和父进程使用相同的输入输出流.
        processBuilder = processBuilder.inheritIO();
        Process process = null;
        try {
            // 启动子进程
            process = processBuilder.start();
            // System.out.println("阅读线程:从右向左滑动手机");
            // output("阅读线程:从右向左滑动手机");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try {
            //等待子进程结束.
            process.waitFor();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
