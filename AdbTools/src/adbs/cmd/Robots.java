package adbs.cmd;

import tools.format.date.DateFormatters;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.Date;

public class Robots {
    private static Robot robot;

    static {
        try {
            // 主屏幕
            robot = new Robot();
            // robot = new Robot(gd[1]);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    /**
     * 点击鼠标左键
     */
    public static void leftMouseButtonClick(Point point) {
        System.out.println(DateFormatters.yyyyMMddHHmmss.format(new Date()));
        // 移动到指定位置
        robot.mouseMove(point.x, point.y);
        System.out.println("机器人,单击左键");
        robot.delay(50);
        // 按下鼠标左键
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.delay(50);
        // 释放鼠标左键
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    /**
     * 点击鼠标右键
     */
    public static void rightClickButton(Point point) {
        // 鼠标移动到之前的位置（防止等待的时候，鼠标被移开了）
        robot.mouseMove(point.x, point.y);
        System.out.println("机器人,单击右键");
        // 移动到位置
        robot.delay(50);
        // 按下鼠标左键
        robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
        robot.delay(50);
        // 释放鼠标左键
        robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
    }

    /**
     * 先点击左键，再点击右键
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

    /**
     * 等待指定毫秒数
     *
     * @param millisecond
     */
    public static void delay(int millisecond) {
        // 等待一小段时间，让解锁界面打开
        robot.delay(millisecond);
    }

}
