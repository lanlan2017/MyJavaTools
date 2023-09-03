package adbs.cmd;

import adbs.main.run.IsTest;
import adbs.tools.thread.ThreadSleep;
import tools.random.Randoms;

import java.awt.*;
import java.awt.event.InputEvent;

public class RobotsDraw {

    private static Robot robot = Robots.getRobot();

    /**
     * 当前的y坐标
     */
    private static int yt;

    // private static int ms = 0;
    private static int ms = 1;

    static {
        if (IsTest.isIsTest()) {
            ms = 1;
        } else {
            ms = 0;
        }
    }


    private static int xxR;
    // private static int yyR;

    public static void main(String[] args) {
        slideUpAndDown();
    }

    /**
     * 循环上下滑动
     * slideUpAndDown
     */

    public static void slideUpAndDown() {
        // ThreadSleep.seconds(3);
        ThreadSleep.seconds(6);

        int x;
        int y;
        // Point location = MouseInfo.getPointerInfo().getLocation();
        Point location = Robots.getMousePointerLocation();
        x = (int) location.getX();
        y = (int) location.getY();


        int yRange = 140;
        // int xRange = 20;
        int xRange = 20;
        // int xRange = 10;

        // int xRange = 10;
        // int xRange = 5;
        int yTop = y - yRange;
        int yBottom = y + yRange;
        yt = yTop;

        // 移动到指定位置
        robot.mouseMove(x, yTop);
        // 按下鼠标左键
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        // 循环次数
        int times;
        // 随机循环次数
        if (IsTest.isIsTest()) {
            times = Randoms.getRandomInt(30, 50);
        } else {
            times = Randoms.getRandomInt(200, 300);
        }


        // int times = 50;
        // xxR = 5;
        xxR = 10;
        // yyR = 10;
        int yy;
        for (int i = 0; i < times; i++) {
            // 生成随机底部坐标
            yy = getRandomsY(yBottom);
            // 当前坐标画一条线段到 底部
            vLineTop2Bottom(x, yy);

            x += xRange;
            yy = getRandomsY(yTop);

            vLineBottom2Top(x, yy);
            x -= xRange;
        }

        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    private static int getRandomsY(int yTop) {
        int yy;
        int yyR = 5;
        if (yTop > yyR) {
            yy = Randoms.getRandomInt(yTop - yyR, yTop + yyR);
        } else {
            yy = Randoms.getRandomInt(0, 5);
        }

        return yy;
    }

    /**
     * 从上往下画垂直线段
     * 从上往下画
     *
     * @param x
     * @param y1
     */
    private static void vLineBottom2Top(int x, int y1) {
        int xt;
        // int xxR = 10;

        // int xxR = 5;
        // 从下网上绘制
        while (yt >= y1) {
            // robot.mouseMove(x, yt);
            xt = Randoms.getRandomInt(x - xxR, x + xxR);
            robot.mouseMove(xt, yt);
            // yt--;
            yt -= 2;
            // ms = 10;
            robot.delay(ms);
        }
    }

    private static void vLineTop2Bottom(int x, int y2) {
        int xt;
        // int xxR = 10;
        // int xxR = 20;
        // 从上往下绘制
        while (yt <= y2) {
            // robot.mouseMove(x, yt);
            xt = Randoms.getRandomInt(x - xxR, x + xxR);
            robot.mouseMove(xt, yt);
            // yt++;
            yt += 2;
            robot.delay(ms);
        }
    }
}
