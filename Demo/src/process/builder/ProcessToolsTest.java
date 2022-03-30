package process.builder;

import java.io.IOException;
import java.util.ArrayList;

/**
 * 测试ProcessTools
 */
public class ProcessToolsTest {
    public static void main(String[] args) {
        showHelp();
    }

    private static void showHelp() {
        // 创建命令列表
        ArrayList<String> commands = new ArrayList<>();
        commands.add("notepad.exe");
        commands.add("help.txt");

        try {
            // 创建进程构造器
            ProcessBuilder runner = new ProcessBuilder(commands);
            // 执行命令
            runner.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
