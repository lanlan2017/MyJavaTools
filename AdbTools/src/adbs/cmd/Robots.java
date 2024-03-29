package adbs.cmd;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * 机器人类
 */
public class Robots {
    private static Robot robot;
    /**
     * 鼠标左键
     */
    private static int leftMouseBtnDownMask = InputEvent.BUTTON1_DOWN_MASK;
    private static int anInt;
    ;

    static {
        try {
            // 在主屏幕上创建一个机器人了
            robot = new Robot();
            // robot = new Robot(gd[1]);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    /**
     * 返回已经创建的机器人对象。
     *
     * @return 机器人类对象
     */
    public static Robot getRobot() {
        return robot;
    }

    /**
     * 在给定的位置，点击鼠标左键
     *
     * @param point
     */
    public static void leftMouseButtonClick(Point point) {
        // System.out.println(DateFormatters.yyyyMMddHHmmss.format(new Date()));
        // 触发ctrl键
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.delay(50);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.delay(500);

        // 移动到指定位置
        robot.mouseMove(point.x, point.y);
        System.out.println("机器人,单击左键");
        robot.delay(50);
        // 按下鼠标左键
        mousePressLeftBtn();
        robot.delay(50);
        // 释放鼠标左键
        // robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        mouseReleaseLeftBtn();
    }

    public static void mouseReleaseLeftBtn() {
        robot.mouseRelease(leftMouseBtnDownMask);
    }

    public static void mousePressLeftBtn() {
        robot.mousePress(leftMouseBtnDownMask);
    }

    /**
     * 获取鼠标的坐标，获取鼠标位置
     *
     * @return 鼠标的位置
     */
    public static Point getMousePointerLocation() {
        Point location = MouseInfo.getPointerInfo().getLocation();
        return location;
    }

    public static void verticalScrolling(int x, int y, int times) {
        if (times > 0 && times < Integer.MAX_VALUE) {
            robot.keyPress(leftMouseBtnDownMask);
            for (int i = 0; i < times; i++) {
                robot.mouseMove(x, y + 100);
                robot.mouseMove(x, y);
            }
            robot.keyRelease(leftMouseBtnDownMask);
        }
    }

    public static void verticalScrolling(Point point, int times) {
        if (times > 0 && times < Integer.MAX_VALUE) {
            int x = ((int) point.getX());
            int y = ((int) point.getY());
            robot.keyPress(leftMouseBtnDownMask);
            for (int i = 0; i < times; i++) {
                robot.mouseMove(x, y + 100);
                robot.delay(10);
                robot.mouseMove(x, y);
            }
            robot.keyRelease(leftMouseBtnDownMask);
        }
    }

    public static void verticalScrolling(Point point) {
        int x = ((int) point.getX());
        int y = ((int) point.getY());
        // anInt = 100;
        anInt = 200;
        robot.mouseMove(x, y + anInt);
        robot.delay(500);
        // robot.delay(30);
        robot.mouseMove(x, y);
    }


    /**
     * 在给定的位置，点击鼠标右键
     *
     * @param point 屏幕坐标
     */
    public static void rightClickButton(Point point) {
        // 鼠标移动到之前的位置（防止等待的时候，鼠标被移开了）
        robot.mouseMove(point.x, point.y);
        System.out.println("机器人,单击右键");
        // 移动到位置
        robot.delay(50);
        // 按下鼠标右键
        robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
        robot.delay(50);
        // 释放鼠标右键
        robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
    }

    /**
     * 在给定的位置，先点击左键，等待指定毫秒后，再点击右键
     *
     * @param point 要点击的屏幕坐标
     */
    public static void leftClickThenRightClick(Point point, int millisecond) {
        if (point != null) {
            // 点击鼠标左键，进入广告界面
            leftMouseButtonClick(point);
            // 等待30秒
            System.out.println("机器人,等待:" + millisecond + "毫秒");
            robot.delay(millisecond);
            // 点击鼠标右键，（触发返回功能）退出广告界面。
            rightClickButton(point);
            robot.delay(1 * 1000);
        }
    }

    public static void mouseMove(Point point) {
        robot.mouseMove(point.x, point.y);
    }

    /**
     * 等待指定毫秒数
     *
     * @param millisecond 需要等待的毫秒数
     */
    public static void delay(int millisecond) {
        // 等待一小段时间，让解锁界面打开
        robot.delay(millisecond);
    }

    /**
     * 等待指定秒数
     *
     * @param second 需要等待的秒数
     */
    public static void delaySecond(int second) {
        // 等待一小段时间，让解锁界面打开
        robot.delay(second * 1000);
    }
}
