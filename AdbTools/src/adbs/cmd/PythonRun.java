package adbs.cmd;

import tools.process.ProcessRunner;

import java.util.ArrayList;
import java.util.List;

public class PythonRun {
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
        command.add("python.exe");
        // command.add("d:/Desktop/test/Python/auto/study/mytest.py");
        command.add("file");
    }

    public static String runPython(String pyFilePath) {
        command.set(command.size() - 1, pyFilePath);
        return runCmd();
    }

    // public static Point getPoint(String pyFilePath) {
    //     // command.set(command.size() - 1, pyFilePath);
    //     // return runCmd();
    //     // String pointStr = runPython("D:\\Desktop\\test\\Python\\auto\\kuaishou\\kuaishou_yuedu_jieshuo.py");
    //
    // }

    private static String runCmd() {
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        ProcessRunner processRunner = new ProcessRunner();
        // 执行进程，并返回进程的标准输出
        return processRunner.runProcess(processBuilder);
        // System.out.println("adbResult = " + adbResult);
    }

    public static void main(String[] args) {
        // jieSuoZhangJiePoint();


    }

    // private static void jieSuoZhangJiePoint() {
    //
    // }
}
