package adbs.action.runnable;

import adbs.cmd.AdbCommands;
import adbs.test.DeviceRadioButtonActionListener;
import adbs.ui.AdbTools;
import tools.thead.Threads;

import javax.swing.*;
import java.util.Random;

public class VideoButtonRunnable implements Runnable {

    private static boolean stop = false;
    private JLabel output;

    public static boolean isStop() {
        return stop;
    }

    public static void setStop(boolean stop) {
        VideoButtonRunnable.stop = stop;
    }

    public VideoButtonRunnable(JLabel output) {
        this.output = output;
    }

    @Override
    public void run() {
        String id = DeviceRadioButtonActionListener.getId();
        // 告诉主线程当前线程正在运行
        AdbTools.setIsRunning(this);

        // 每次默认不停止循环
        setStop(false);
        // 先睡眠一小段时间
        Random random = new Random();
        int min = 7;
        int max = 14;
        int times = 4;
        String oldOutput;
        String newOutput;
        while (!isStop()) {

            int count = 0;
            Threads.sleep(500);
            // 生成[min,Max]区间的随机整数
            int s = random.nextInt(max) % (max - min + 1) + min;
            // 在手机左侧，从下往上滑动
            String adbResult = AdbCommands.swipeBottom2TopOnLeft(id);
            // if ("".equals(adbResult)) {
            //     System.out.println("adb命令运行正常");
            // } else

            // if (adbResult == null) {
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
