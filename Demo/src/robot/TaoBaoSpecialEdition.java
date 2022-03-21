package robot;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class TaoBaoSpecialEdition {
    Robot robot;

    public TaoBaoSpecialEdition() {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public TaoBaoSpecialEdition(Robot robot) {
        this.robot = robot;
    }

    /**
     * 陶特刷视频，工作版。使用Alt+Tab快捷键切换到手机投屏窗口后，快速滑动，然后再使用Alt+Tab快捷返回工作窗口
     */
    public void watchVideo2() {
        if (robot != null) {
            // 底部坐标
            int x1 = 1164;
            int y1 = 500;
            // 顶部坐标
            int x2 = 1168;
            int y2 = 200;
            for (int i = 0; i < 30; i++) {
                // 切换到新窗体
                switchWin10Windows(robot);

                // 移动鼠标到底部
                robot.mouseMove(x1, y1);
                robot.delay(RobotDelayTimes.oneHundredMilliseconds);

                // 按下鼠标左键
                robot.mousePress(InputEvent.BUTTON1_MASK);
                robot.delay(RobotDelayTimes.oneHundredMilliseconds);

                // 移动到顶部（拖动）
                robot.mouseMove(x2, y2);
                robot.delay(RobotDelayTimes.twoHundredMilliseconds);

                // 放开鼠标左键
                robot.mouseRelease(InputEvent.BUTTON1_MASK);

                // 切换回到工作窗口
                switchWin10Windows(robot);
                // 等待较长的时间
                robot.delay(RobotDelayTimes.thirtySeconds);
            }
        }
    }

    /**
     * 陶特刷视频置顶版
     */
    public void watchVideo() {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        if (robot != null) {
            // 底部坐标
            int x1 = 1164;
            int y1 = 500;
            // 顶部坐标
            int x2 = 1168;
            int y2 = 200;
            for (int i = 0; i < 30; i++) {
                // 移动鼠标到底部
                robot.mouseMove(x1, y1);
                robot.delay(RobotDelayTimes.oneHundredMilliseconds);

                // 按下鼠标左键
                robot.mousePress(InputEvent.BUTTON1_MASK);
                robot.delay(RobotDelayTimes.oneHundredMilliseconds);

                // 移动到顶部（拖动）
                robot.mouseMove(x2, y2);
                robot.delay(RobotDelayTimes.twoHundredMilliseconds);

                // 放开鼠标左键
                robot.mouseRelease(InputEvent.BUTTON1_MASK);

                // 等待较长的时间
                robot.delay(RobotDelayTimes.thirtySeconds);
            }
        }
    }

    /**
     * 淘宝浏览首页赚积分
     */
    public void browseHomepage() {

        try {
            Robot robot = new Robot();
            int x = 1152;
            int y = 176;
            robot.delay(1000);
            // 移动鼠标到特定位置
            robot.mouseMove(x, y);
            robot.delay(1000);
            // 设置鼠标滚轮每次滚动的次数
            int scrollDown = 8;
            // 向下滚动多次
            for (int i = 0; i < 3; i++) {
                // 鼠标滚轮向下滚动1次
                robot.mouseWheel(scrollDown);
                robot.delay(1000);
            }
            // 循环滚动
            int loopScroll = 5;
            for (int i = 0; i < 100; i++) {
                // 鼠标滚轮向下滚动1次
                robot.mouseWheel(loopScroll);
                robot.delay(1000);
                // 鼠标滚轮向上滚动1次
                robot.mouseWheel(-loopScroll);
                robot.delay(1000);
            }
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    /**
     * 按下alt+Tab快捷键，切换Windows窗口
     *
     * @param robot Robot对象
     */
    public void switchWin10Windows(Robot robot) {
        robot.keyPress(KeyEvent.VK_ALT);
        robot.keyPress(KeyEvent.VK_TAB);
        // robot.delay(oneHundredMilliseconds);
        robot.keyRelease(KeyEvent.VK_ALT);
        robot.keyRelease(KeyEvent.VK_TAB);
    }
}
