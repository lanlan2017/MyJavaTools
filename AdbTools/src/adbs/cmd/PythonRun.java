package adbs.cmd;

import tools.process.ProcessRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * 运行python文件
 */
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

    /**
     * 运行Python文件,
     * @param pyFilePath Python文件的绝对路径
     * @return Python文件运行结果。
     */
    public static String runPython(String pyFilePath) {
        command.set(command.size() - 1, pyFilePath);
        return runCmd();
    }

    /**
     * 启动一个进程，运行Command列表中的命令，并返回进程输出的内容。
     * @return 进程的输出字符串
     */
    private static String runCmd() {
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        ProcessRunner processRunner = new ProcessRunner();
        // 执行进程，并返回进程的标准输出
        return processRunner.runProcess(processBuilder);
    }

    // public static void main(String[] args) {
    //     // jieSuoZhangJiePoint();
    // }
}
