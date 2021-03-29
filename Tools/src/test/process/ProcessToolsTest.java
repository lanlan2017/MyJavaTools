package test.process;

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
        ArrayList<String> commands = new ArrayList<>();
        commands.add("notepad.exe");
        commands.add("help.txt");
        try {
            ProcessBuilder runner = new ProcessBuilder(commands);
            runner.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
