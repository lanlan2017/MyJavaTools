package adbs.cmd;

import adbs.action.runnable.ReadButtonRunnable;

import java.awt.*;
import java.awt.event.InputEvent;

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
     *
     * @param robot
     */
    private static void clickLeftButton(Robot robot) {
        System.out.println("单击左键");
        robot.delay(50);
        // 按下鼠标左键
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.delay(50);
        // 释放鼠标左键
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    /**
     * 点击鼠标右键
     *
     * @param robot
     */
    private static void clickRightButton(Robot robot) {
        System.out.println("单击右键");
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
            // 移动到位置
            robot.mouseMove(point.x, point.y);
            // 点击鼠标左键，进入广告界面
            clickLeftButton(robot);

            // 进入广告界面之后，关闭阅读线程
            ReadButtonRunnable.setStop(true);
            // 等待30秒
            // robot.delay(30 * 1000);
            robot.delay(millisecond);
            // 鼠标移动到之前的位置（防止等待的时候，鼠标被移开了）
            robot.mouseMove(point.x, point.y);
            // 点击鼠标右键，（触发返回功能）退出广告界面。
            clickRightButton(robot);

            // // 退出广告界面之后，开启阅读线程
            // readButton.doClick();
            //
            // delay(1000);
            // // 关闭当前进程
            // setStop(true);
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
