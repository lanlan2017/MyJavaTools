package robot;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TaoBaoSpecialEdition {
    private Robot robot;
    private MouseOperation mouseOperation;
    private Logger logger;

    public TaoBaoSpecialEdition() {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        mouseOperation = new MouseOperation(robot);

        logger = Logger.getLogger("操作淘宝特价版");
        // 不将输出向父Logger递归发送
        logger.setUseParentHandlers(false);
        // // 创建日志处理器
        ConsoleHandler handler = new ConsoleHandler();
        // 给日志处理器设置格式
        handler.setFormatter(new MyFormatter());
        // handler.setFormatter(new LogFormatter());
        // 先移除所有的Handler
        // removeAllHandler();
        // 添加自定义的Handler
        logger.addHandler(handler);
        // 配置日志具体级别
        logger.setLevel(Level.ALL);
        handler.setLevel(Level.ALL);
    }

    private void removeAllHandler() {
        Handler[] handlers = logger.getHandlers();
        System.out.println(handlers.length);
        for (int i = 0; i < handlers.length; i++) {
            System.err.println("移除" + handlers[i].toString());
            // logger.removeHandler(handlers[i]);
            if (handlers[i] instanceof ConsoleHandler) {
                logger.warning("哈哈哈哈");
                handlers[i].setFormatter(new MyFormatter());
            }
        }
    }

    public TaoBaoSpecialEdition(Robot robot) {
        this.robot = robot;
        mouseOperation = new MouseOperation(robot);
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
                alt_Tab();
                // 移动鼠标到底部
                robot.mouseMove(x1, y1);
                robot.delay(DelayTimes.oneHundredMilliseconds);

                // 按下鼠标左键
                robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                robot.delay(DelayTimes.oneHundredMilliseconds);

                // 移动到顶部（拖动）
                robot.mouseMove(x2, y2);
                robot.delay(DelayTimes.twoHundredMilliseconds);

                // 放开鼠标左键
                robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

                // 切换回到工作窗口
                alt_Tab();
                // 等待较长的时间
                robot.delay(DelayTimes.thirtySeconds);
            }
        }
    }

    /**
     * 淘宝特价版刷视频，置顶版本。手机投屏窗口要一直显示在顶部。
     * 适用于用户不需要使用电脑的情况。
     */
    public void watchVideo() {
        // 底部坐标
        int x1 = 1164;
        int y1 = 500;
        // 顶部坐标
        int x2 = 1168;
        int y2 = 200;
        for (int i = 0; i < 30; i++) {
            logger.info("移动鼠标到底部");
            // 移动鼠标到底部
            robot.mouseMove(x1, y1);
            robot.delay(DelayTimes.oneHundredMilliseconds);

            // 按下鼠标左键
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.delay(DelayTimes.oneHundredMilliseconds);
            logger.info("上刷视频");
            // 移动到顶部（拖动）
            robot.mouseMove(x2, y2);
            robot.delay(DelayTimes.twoHundredMilliseconds);

            // 放开鼠标左键
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

            // 等待较长的时间
            robot.delay(DelayTimes.thirtySeconds);
        }
    }

    /**
     * 淘宝浏览首页赚积分
     */
    public void browseHomepage() {
        if (robot != null) {
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

                robot.mouseWheel(loopScroll);
                robot.delay(1000);

                robot.mouseWheel(loopScroll);
                robot.delay(1000);

                robot.mouseWheel(loopScroll);
                robot.delay(1000);

                robot.mouseWheel(loopScroll);
                robot.delay(1000);

                // 鼠标滚轮向上滚动1次
                robot.mouseWheel(-loopScroll);
                robot.delay(1000);

                robot.mouseWheel(-loopScroll);
                robot.delay(1000);

                robot.mouseWheel(-loopScroll);
                robot.delay(1000);

                robot.mouseWheel(-loopScroll);
                robot.delay(1000);
            }
        }
    }

    /**
     * 喂鸡
     */
    public void feedTheChickens(int numberOfTimes) {
        robot.delay(DelayTimes.threeSeconds);
        // robot.keyRelease(InputEvent.BUTTON1_DOWN_MASK);

        // 移动鼠标顶部空白处
        robot.mouseMove(1176, 253);
        mouseOperation.leftClick();

        for (int i = 0; i < numberOfTimes; i++) {
            // 移动鼠标到底部
            robot.mouseMove(1314, 650);
            mouseOperation.leftClick();

            robot.delay(DelayTimes.tenSeconds);
        }
    }

    /**
     * 发财鸭浏览15秒
     */
    public void browseForSeconds(int second) {
        robot.delay(DelayTimes.twoSeconds);
        //移动到顶部空白处
        robot.mouseMove(1083, 84);
        // 点击左键，激活窗体
        mouseOperation.leftClick();
        // robot.delay(DelayTimes.thirtySeconds);
        robot.delay(DelayTimes.oneSecond);
        int scrollDown = 1;
        // 向下滚动多次
        for (int i = 0; i < second; i++) {
            // 鼠标滚轮向下滚动1次
            robot.mouseWheel(scrollDown);
            robot.delay(DelayTimes.oneSecond);
        }
        // 点击右键，表示返回
        mouseOperation.rightClick();
    }

    // /**
    //  * 模拟鼠标点击
    //  */
    // private void leftClick() {
    //     robot.delay(DelayTimes.oneHundredMilliseconds);
    //     // 按下鼠标左键
    //     robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
    //     robot.delay(DelayTimes.oneHundredMilliseconds);
    //     // 放开鼠标左键
    //     robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    //     robot.delay(DelayTimes.oneHundredMilliseconds);
    // }
    //
    // /**
    //  * 模拟鼠标点击
    //  */
    // private void rightClick() {
    //     robot.delay(DelayTimes.oneHundredMilliseconds);
    //     // 按下鼠标左键
    //     robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
    //     robot.delay(DelayTimes.oneHundredMilliseconds);
    //     // 放开鼠标左键
    //     robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
    //     robot.delay(DelayTimes.oneHundredMilliseconds);
    // }

    /**
     * 发财鸭喂食3次
     * fortuneDuckFeeds3Times
     */
    public void fortuneDuckFeeds(int times) {
        robot.delay(DelayTimes.twoSeconds);
        robot.mouseMove(1164, 664);
        robot.delay(DelayTimes.oneSecond);
        for (int i = 0; i < times; i++) {
            mouseOperation.leftClick();
            robot.delay(DelayTimes.threeSeconds);
        }
    }

    /**
     * 发财鸭喂食3次
     * fortuneDuckFeeds3Times
     */
    public void fortuneDuckFeedsWork(int times) {
        robot.delay(DelayTimes.twoSeconds);
        for (int i = 0; i < times; i++) {
            alt_Tab();
            robot.mouseMove(1164, 664);
            mouseOperation.leftClick();
            alt_Tab();
            robot.delay(DelayTimes.threeSeconds);
        }
    }

    /**
     * 按下alt+Tab快捷键，切换Windows窗口
     */
    private void alt_Tab() {
        robot.keyPress(KeyEvent.VK_ALT);
        robot.keyPress(KeyEvent.VK_TAB);
        // robot.delay(oneHundredMilliseconds);
        robot.keyRelease(KeyEvent.VK_ALT);
        robot.keyRelease(KeyEvent.VK_TAB);
    }
}
