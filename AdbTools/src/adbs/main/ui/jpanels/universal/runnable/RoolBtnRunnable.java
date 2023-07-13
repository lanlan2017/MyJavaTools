package adbs.main.ui.jpanels.universal.runnable;

import adbs.cmd.Robots;
import adbs.main.AdbTools;
import adbs.main.ui.jpanels.time.TimePanels;
import adbs.tools.thread.ThreadSleep;

import javax.swing.*;
import java.awt.*;

public class RoolBtnRunnable extends CloseableRunnable {

    private TimePanels timePanels;
    private JTextField input1;
    private int times;
    private Color background;
    JLabel timerJLabel;

    private static RoolBtnRunnable instance = new RoolBtnRunnable();

    public static RoolBtnRunnable getInstance() {
        return instance;
    }

    @Override
    protected void setMsg() {
        msg = "滚动按钮线程";
    }

    @Override
    protected void beforeLoop() {
        super.beforeLoop();
        timePanels = AdbTools.getInstance().getTimePanels();
        input1 = timePanels.getInput1();
        background = input1.getBackground();
        input1.setBackground(Color.PINK);
        times = Integer.parseInt(input1.getText());
        timerJLabel = timePanels.getTimerJLabel();
    }

    @Override
    protected void loopBody() {
        // // 等到一会让用户好移动鼠标
        // Robots.delaySecond(5);

        int wait = 5;
        for (int i = 0; i <= wait; i++) {
            ThreadSleep.seconds(1);
            timerJLabel.setText("" + (wait - i));
        }
        // Point point = Robots.getMousePointerLocation();
        // 按下鼠标左键
        Robots.mousePressLeftBtn();
        // int times = 30;
        int count = 0;
        while (!stop) {
            // 等待指定时间
            ThreadSleep.seconds(1);
            // 统计已经等待的时间
            count += 1;
            input1.setText("" + (times - count));
            // 如果等待指定时间
            if (count >= times) {
                stop = true;
                // break;
            }
        }
        // 释放鼠标左键
        Robots.mouseReleaseLeftBtn();
    }

    @Override
    protected void afterLoop() {
        super.afterLoop();
        stop = false;
        input1.setBackground(background);
        timerJLabel.setText("");
        input1.setText("" + times);
    }
}
