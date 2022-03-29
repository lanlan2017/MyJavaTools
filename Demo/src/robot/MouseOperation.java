package robot;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.logging.Logger;

public class MouseOperation {
    private Robot robot;
    private Logger logger = Logger.getLogger(TaoBaoSpecialEdition.class.getName());

    public MouseOperation() {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public MouseOperation(Robot robot) {
        this.robot = robot;
    }

    /**
     * 模拟鼠标点击
     */
    public void leftClick() {
        robot.delay(DelayTimes.oneHundredMilliseconds);
        // 按下鼠标左键
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.delay(DelayTimes.oneHundredMilliseconds);
        // 放开鼠标左键
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        robot.delay(DelayTimes.oneHundredMilliseconds);
    }

    /**
     * 模拟鼠标点击
     */
    public void rightClick() {
        robot.delay(DelayTimes.oneHundredMilliseconds);
        // 按下鼠标左键
        robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
        robot.delay(DelayTimes.oneHundredMilliseconds);
        // 放开鼠标左键
        robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
        robot.delay(DelayTimes.oneHundredMilliseconds);
    }
}
