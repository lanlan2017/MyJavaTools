package adbs.action.runnable;

import adbs.action.model.InputOutputModel;
import adbs.cmd.AdbCommands;
import adbs.test.DeviceRadioBtAcListener;
import adbs.ui.AdbTools;
import tools.thead.Threads;

import javax.swing.*;
import java.util.Random;

public class VideoButtonRunnable implements Runnable {

    private static boolean stop = false;
    private int min = 0;
    private int max = 0;
    /**
     * 输入输出汇总模型
     */
    private InputOutputModel model;


    public VideoButtonRunnable(InputOutputModel model) {
        this.model = model;
    }

    public static boolean isStop() {
        return stop;
    }

    public static void setStop(boolean stop) {
        VideoButtonRunnable.stop = stop;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    @Override
    public void run() {
        String id = DeviceRadioBtAcListener.getId();
        // 告诉主线程当前线程正在运行
        AdbTools.setIsRunning(this);

        JLabel output = model.getOutput();

        // 每次默认不停止循环
        setStop(false);
        // 先睡眠一小段时间
        Random random = new Random();
        if (min == 0) {
            min = 7;
        }
        if (max == 0) {
            max = 14;
        }
        System.out.println("随机等待时间[" + min + "~" + max + "]");
        int times = 4;
        String oldOutput;
        String newOutput;
        // Threads.sleep(500);
        while (!isStop()) {
            int count = 0;
            // 生成[min,Max]区间的随机整数
            int s = random.nextInt(max) % (max - min + 1) + min;
            // 在手机左侧，从下往上滑动
            String adbResult = AdbCommands.swipeBottom2TopOnLeft(id);

            if (adbResult.startsWith("Error!ExitCode=")) {
                System.out.println("adb命令运行错误，退出程序." + adbResult);
                break;
            }
            // 小片段等待循环
            while (count < s * times) {
                // 当stop标记为true时，退出小片段等待
                if (isStop()) {
                    break;
                }
                // 等待一小段时间
                Threads.sleep(1000 / times);
                count++;

                oldOutput = output.getText();
                newOutput = "刷视频线程:等待" + (s - count / times) + "s";
                // 如果内容不相等，则更新输出
                if (!newOutput.equals(oldOutput)) {
                    output.setText(newOutput);
                }
            }
        }
        output.setText("刷视频线程:已停止");
    }
}
