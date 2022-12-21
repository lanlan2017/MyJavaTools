package adbs.main;

import adbs.cmd.AdbCommands;

import javax.swing.*;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        JFrame frame=new JFrame();
        String devicesListStr = AdbCommands.runAbdCmd("adb devices -l");
        // 分析adb devices -l命令结果
        Scanner scanner = new Scanner(devicesListStr);
        String line;
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            System.out.println(line);
        }
    }
}
