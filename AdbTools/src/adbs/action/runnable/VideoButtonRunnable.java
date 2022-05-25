package adbs.action.runnable;

import adbs.cmd.AdbCommands;
import adbs.test.DeviceRadioBtAcListener;
import adbs.ui.AdbTools;
import tools.thead.Threads;

import javax.swing.*;
import java.util.Random;

public class VideoButtonRunnable implements Runnable {

    private static boolean stop = false;
    private static int min = 0;
    private static int max = 0;
    private JLabel output;

    public static boolean isStop() {
        return stop;
    }

    public static void setStop(boolean stop) {
        VideoButtonRunnable.stop = stop;
    }

    public static int getMin() {
        return min;
    }

    public static void setMin(int min) {
        VideoButtonRunnable.min = min;
    }

    public static int getMax() {
        return max;
    }

    public static void setMax(int max) {
        VideoButtonRunnable.max = max;
    }

    public VideoButtonRunnable(JLabel output) {
        this.output = output;
    }

    @Override
    public void run() {
        String id = DeviceRadioBtAcListener.getId();
        // 告诉主线程当前线程正在运行
        AdbTools.setIsRunning(this);

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
