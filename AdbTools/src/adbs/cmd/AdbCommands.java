package adbs.cmd;

import tools.process.ProcessRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
     * 在手机左侧，从下向上滑动
     */
    public static String swipeBottom2TopOnLeft(String id) {
        return AdbCommands.runAbdCmd("adb -s " + id + " shell input swipe 8 1650 8 700 200");
    }

    /**
     * 在手机左侧，从下向上滑动
     */
    public static void swipeTop2BottomOnLeft(String id) {
        AdbCommands.runAbdCmd("adb -s " + id + " shell input swipe 8 700 8 1650 200");
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
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        ProcessRunner processRunner = new ProcessRunner();
        return processRunner.runProcess(processBuilder);
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
