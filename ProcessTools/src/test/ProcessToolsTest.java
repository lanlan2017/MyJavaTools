package test;

import tools.ProcessRunner;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * @author francis
 * create at 2020/7/7-23:23
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
