package robot;

import java.awt.*;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VPN {

    Robot robot;

    public VPN() {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public VPN(Robot robot) {
        this.robot = robot;
    }

    public void vpn() {
        Logger logger = Logger.getLogger("日志");
        logger.setLevel(Level.ALL);

        FileHandler fileHandler = null;
        try {
            fileHandler = new FileHandler("xxx.log");
        } catch (IOException e) {
            e.printStackTrace();
        }
        fileHandler.setLevel(Level.ALL);
        fileHandler.setFormatter(new LogFormatter());


        logger.addHandler(fileHandler);


        if (robot != null) {
            // 延迟两秒
            logger.info("延迟两秒");
            robot.delay(RobotDelayTimes.twoSeconds);

            for (int i = 0; i < 50; i++) {
                logger.info("移动到指定位置");
                // robot.mouseMove(1306, 176);
                robot.mouseMove(1300, 126);
                robot.delay(RobotDelayTimes.oneSecond);

                // 点击左键
                // 按下鼠标左键
                logger.info("按下鼠标左键");
                robot.mousePress(RobotDemo.leftMouseButton);
                // 延时100毫秒
                logger.info("延时100毫秒");
                robot.delay(RobotDelayTimes.oneHundredMilliseconds);
                // 释放鼠标左键
                logger.info("释放鼠标左键");
                robot.mouseRelease(RobotDemo.leftMouseButton);

                // 延时30毫秒
                logger.info("延时40毫秒");
                robot.delay(RobotDelayTimes.fortySeconds);

                // 移动到悬浮球
                logger.info("移动到悬浮控制球");
                robot.mouseMove(995, 185);
                robot.delay(RobotDelayTimes.oneSecond);

                // 按下悬浮球的返回键
                logger.info("按下悬浮球的返回键");
                robot.mouseMove(1050, 248);
                robot.delay(RobotDelayTimes.oneSecond);
            }
        }
    }
}
