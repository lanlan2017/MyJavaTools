package adbs.action.runnable;

import adbs.action.model.InputOutputModel;
import adbs.cmd.AdbCommands;
import adbs.test.DeviceRadioBtAcListener;
import adbs.ui.AdbTools;
import tools.thead.Threads;

import javax.swing.*;
import java.util.Random;

public class ReadButtonRunnable implements Runnable {
    // 输入输出汇总Model
    private InputOutputModel model;
    private static boolean isStop = false;

    public ReadButtonRunnable(InputOutputModel model) {
        this.model = model;
    }

    public boolean isStop() {
        return isStop;
    }

    public static void setStop(boolean stop) {
        isStop = stop;
    }

    @Override
    public void run() {
        isStop = false;
        String id = DeviceRadioBtAcListener.getId();
        JLabel output = model.getOutput();

        // 告诉主线程当前线程正在运行
        AdbTools.setIsRunning(this);
        // 先等待1秒
        Threads.sleep(800);
        // 先等待一会 再开始执行命令
        Random random = new Random();
        int min = 5;
        int max = 9;
        String oldText;
        String newText;
        while (!isStop) {
            // 执行一次adb命令，从右向左滑动屏幕
            // runOneTime(command);
            AdbCommands.swipeRight2LeftOnTop(id);
            // 生成[min,Max]区间的随机整数
            int s = random.nextInt(max) % (max - min + 1) + min;
            // System.out.println("阅读线程：" + "等待" + s + "秒");
            output.setText("阅读线程：" + "等待" + s + "秒");

            int count = 0;
            // 倍数
            int times = 4;
            while (count < s * times) {
                // 如果已经收到结束的通知
                if (isStop()) {
                    // 不再等待
                    break;
                }
                Threads.sleep(1000 / times);
                oldText = output.getText();
                newText = "阅读线程：" + "等待" + (s - count / times) + "秒";
                if (!oldText.equals(newText)) {
                    output.setText(newText);
                }
                count++;
            }
        }
        output.setText("阅读线程：已停止");
    }
}
