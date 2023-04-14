package adbs.main.ui.jpanels.universal.runnable;

import adbs.cmd.AdbCommands;
import adbs.main.AdbTools;
import adbs.main.ui.inout.InOutputModel;
import tools.thead.Threads;

import javax.swing.*;

public class ShoppingButtonRunnable extends CloseableRunnable {
    /**
     * 输入输出汇总模型
     */
    private InOutputModel inOutputModel;

    private static ShoppingButtonRunnable instance = new ShoppingButtonRunnable();
    private int seconds;
    private int count;

    private ShoppingButtonRunnable() {
    }

    public static ShoppingButtonRunnable getInstance() {
        return instance;
    }

    public void setInOutputModel(InOutputModel inOutputModel) {
        this.inOutputModel = inOutputModel;
    }

    @Override
    protected void setMsg() {
        msg = "逛街";
    }

    @Override
    protected void loopBody() {
        // String id = AdbTools.device.getId();
        String id = AdbTools.getInstance().getDevice().getId();
        JLabel output = inOutputModel.getUniversalPanels().getOutput2();
        JTextField input1 = inOutputModel.getTimePanels().getInput1();
        // 读取文本框1中的秒数
        seconds = Integer.parseInt(input1.getText()) * 1000;
        // 计数器
        count = 0;

        output.setText("左↑滑3次");
        // 在左侧，从下向上滑动三次
        if (swipeFromBottomToTopOnce(id, input1)) {
            // 如果到达了指定时间，
            stop = true;
            return;
        }
        if (swipeFromBottomToTopOnce(id, input1)) {
            stop = true;
            return;
        }
        if (swipeFromBottomToTopOnce(id, input1)) {
            stop = true;
            return;
        }

        while (!stop) {
            output.setText("↓");
            // 休眠1秒
            if (swipeFromBottomToTopOnce(id, input1)) {
                System.out.println("到达指定时间1!");
                stop = true;
                break;
            }
            output.setText("↑");
            if (swipeFromTopToBottomOnce(id, input1)) {
                System.out.println("到达指定时间2!");
                stop = true;
                break;
            }
        }
    }

    /**
     * 从下往上滑动一次
     *
     * @param id     手机的id
     * @param input1 输入文本1
     * @return 计数器
     */
    private boolean swipeFromBottomToTopOnce(String id, JTextField input1) {
        // 等待1秒
        Threads.sleep(1000);
        // 计数器+1秒
        count += 1000;
        // 计算剩余时间
        int diff = (seconds - count) / 1000;
        if (diff >= 0) {
            // 从下向上滑动
            AdbCommands.swipeBottom2TopOnLeft(id);
            // 更新输入文本框
            input1.setText(String.valueOf(diff));
            // 返回false表示还没达到指定时间
            return false;
        }
        // 返回true表示已经达到指定时间了
        return true;
    }

    /**
     * 从下往上滑动一次
     *
     * @param id     手机的id
     * @param input1 输入文本1
     * @return 计数器
     */
    private boolean swipeFromTopToBottomOnce(String id, JTextField input1) {
        Threads.sleep(1000);
        count += 1000;
        int shengYu = (seconds - count) / 1000;
        if (shengYu >= 0) {
            AdbCommands.swipeTop2BottomOnLeft(id);
            input1.setText(String.valueOf(shengYu));
            return false;
        }
        return true;
    }

    @Override
    protected void afterLoop() {
        super.afterLoop();
        inOutputModel.getTimePanels().getTimerJLabel().setText("");
    }
}
