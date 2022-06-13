package adbs.action.runnable;

import adbs.action.model.InOutputModel;
import adbs.action.runnable.abs.CloseableRunnable;
import adbs.cmd.AdbCommands;
import adbs.test.DeviceRadioBtAcListener;
import tools.thead.Threads;

import javax.swing.*;

public class ShoppingButtonRunnable extends CloseableRunnable {
    /**
     * 输入输出汇总模型
     */
    private InOutputModel inOutputModel;

    private static ShoppingButtonRunnable instance = new ShoppingButtonRunnable();

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
        msg = "逛街线程2";
    }

    @Override
    protected void beforeLoop() {

    }

    @Override
    protected void loopBody() {
        String id = DeviceRadioBtAcListener.getId();
        // 告诉主线程当前线程正在运行
        // AdbTools.setIsRunning(this);

        JTextField input1 = inOutputModel.getInputPanelModel().getInput1();
        // 读取文本框1中的秒数
        int seconds = Integer.parseInt(input1.getText()) * 1000;
        // 计数器
        int count = 0;

        JLabel output = inOutputModel.getOutput();
        output.setText("逛街线程：已经开始");
        output.setText("逛街线程: 在左侧 从下向上滑动3次");

        // 在左侧，从下向上滑动三次
        count = swipeFromBottomToTopOnce(id, input1, seconds, count);
        count = swipeFromBottomToTopOnce(id, input1, seconds, count);
        count = swipeFromBottomToTopOnce(id, input1, seconds, count);

        while (!stop && count <= seconds) {
            output.setText(msg + ":在左侧 从下向上 滑动1次");
            if (stop) {
                break;
            }
            // 休眠1秒
            count = swipeFromBottomToTopOnce(id, input1, seconds, count);

            // 如果已经运行了指定的时间
            if (count >= seconds) {
                // 退出循环
                break;
            }

            // output.setText("逛街线程: 在左侧 从上向下滑动1次");
            output.setText(msg + ":在左侧 从上向下 滑动1次");
            Threads.sleep(1000);
            count += 1000;

            // 在输入框中显示剩余时间
            input1.setText(String.valueOf((seconds - count) / 1000));
            if (stop) {
                break;
            }
            AdbCommands.swipeTop2BottomOnLeft(id);
        }
    }

    /**
     * 从下往上滑动一次
     *
     * @param id      手机的id
     * @param input1  输入文本1
     * @param seconds 总秒数
     * @param count   计数器
     * @return 计数器
     */
    private int swipeFromBottomToTopOnce(String id, JTextField input1, int seconds, int count) {
        Threads.sleep(1000);
        count += 1000;
        // 在输入框中显示剩余时间
        input1.setText(String.valueOf((seconds - count) / 1000));
        AdbCommands.swipeBottom2TopOnLeft(id);
        return count;
    }

    @Override
    protected void afterLoop() {
        inOutputModel.getOutput().setText(msg + ":已经结束");
    }
}
