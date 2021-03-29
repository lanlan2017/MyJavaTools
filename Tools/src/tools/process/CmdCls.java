package tools.process;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 实现控制台清屏功能.
 */
public class CmdCls {
    /**
     * 实现在cmd控制台下清屏功能.
     */
    public static void cls() {
        try {
            List<String> command = new ArrayList<>();
            // 程序:cmd.exe
            command.add("cmd");
            // 参数:/C 执行字符串指定的命令然后终止
            command.add("/c");
            // 参数:清屏命令cls
            command.add("cls");
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            // 子进程和父进程使用相同的输入输出流.
            processBuilder = processBuilder.inheritIO();
            // 启动子进程
            Process process = processBuilder.start();
            //等待线程结束.
            process.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
