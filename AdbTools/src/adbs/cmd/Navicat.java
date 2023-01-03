package adbs.cmd;

import tools.thead.Threads;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Navicat编辑
 */
public class Navicat {
    public static void main(String[] args) {
        String pyFilePath = "G:\\dev2\\idea_workspace\\MyJavaTools\\Conifg_Navicat\\NavicatAutoClick.py";
        // while (true) {
        doOnes(pyFilePath);
        // }
    }

    private static void doOnes(String pyFilePath) {
        String pyOutput = PythonRun.runPython(pyFilePath);
        System.out.println("pyOutput = " + pyOutput);
        if (pyOutput.startsWith("ForeignKeySearchBox")) {
            Point point = PyAutoGui.getPoint(pyOutput);
            point.setLocation(point.getX() + 80, point.getY());
            System.out.println("point = " + point);
            Robots.leftMouseButtonClick(point);
            Robot robot = Robots.getRobot();
            // 按下Ctrl+A
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_A);
            robot.delay(50);
            robot.keyRelease(KeyEvent.VK_A);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            // robot.keyRelease(KeyEvent);

            robot.delay(50);
            robot.keyPress(KeyEvent.VK_BACK_SPACE);
            robot.keyRelease(KeyEvent.VK_BACK_SPACE);
            // robot.keyRelease(KeyEvent);
            // Threads.sleep(10 * 1000);
            Threads.sleep(8 * 1000);

        } else if (pyOutput.contains("wait_")) {
            System.out.println("等待...");
            Threads.sleep(2 * 1000);

        }
    }
}
