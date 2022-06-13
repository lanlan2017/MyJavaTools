package adbs.action.runnable;

import adbs.action.model.InOutputModel;
import adbs.action.runnable.abs.CloseableRunnable;
import adbs.cmd.AdbCommands;
import adbs.test.DeviceRadioBtAcListener;
import tools.thead.Threads;

import javax.swing.*;
import java.util.Random;

public class ReadButtonRunnable extends CloseableRunnable {

    private static ReadButtonRunnable instance = new ReadButtonRunnable();
    // 输入输出汇总Model
    private InOutputModel inOutputModel;
    // 输出内容
    private JLabel output;
    private Random random;
    private int min;
    private int max;

    private ReadButtonRunnable() {
        setMsg();
        random = new Random();
        min = 5;
        max = 9;
    }

    public static ReadButtonRunnable getInstance() {
        return instance;
    }

    public void setInOutputModel(InOutputModel inOutputModel) {
        this.inOutputModel = inOutputModel;
        this.output = inOutputModel.getOutput();
    }

    @Override
    protected void setMsg() {
        msg = "阅读线程2";
    }

    @Override
    protected void beforeLoop() {
        // 先等待1秒
        Threads.sleep(500);
    }

    @Override
    protected void loopBody() {
        body();
    }

    private void body() {
        String id;
        id = DeviceRadioBtAcListener.getId();
        if (id == null) {
            // 如果没有选择设备
            JOptionPane.showConfirmDialog(null, "请勾选要操作的设备");
            // return;
            stop = true;
        } else {
            // 如果选择了设备
            // 执行一次adb命令，从右向左滑动屏幕
            AdbCommands.swipeRight2LeftOnTop(id);
        }
        // 生成[min,Max]区间的随机整数
        int s = random.nextInt(max) % (max - min + 1) + min;
        s = s * 1000;
        output.setText(msg + "等待" + s + "秒");

        String oldText;
        String newText;
        int count = 0;
        while (count < s) {
            // 如果已经收到结束的通知
            if (stop) {
                // 不再等待
                break;
            }
            // 等待一小段时间
            Threads.sleep(250);
            oldText = output.getText();
            newText = msg + "等待" + (s - count) / 1000 + "秒";
            if (!oldText.equals(newText)) {
                output.setText(newText);
            }
            count += 250;
        }
    }

    @Override
    protected void afterLoop() {
        output.setText(msg + "已停止");
    }
}
