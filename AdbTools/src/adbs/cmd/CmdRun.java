package adbs.cmd;

//import tools.process.ProcessRunner;

import tools.process.ProcessRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * 调用cmd执行命令
 */
public class CmdRun {
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
        command.add("command");
    }

    public static String run(String code) {
        command.set(command.size() - 1, code);
        return runCmd().trim();
    }

    public static void runOnly(String code) {
        command.set(command.size() - 1, code);
        ProcessBuilder builder = new ProcessBuilder(command);
        ProcessRunner runner = new ProcessRunner();
         runner.runProcess(builder);
        runner.runOnly(builder);

        // return runCmd();
    }


    private static String runCmd() {
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        ProcessRunner processRunner = new ProcessRunner();
        return processRunner.runProcess(processBuilder);
    }
}
