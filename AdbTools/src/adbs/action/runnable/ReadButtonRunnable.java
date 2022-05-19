package adbs.action.runnable;

import adbs.cmd.AdbCommands;
import adbs.test.DeviceRadioButtonActionListener;
import adbs.ui.AdbTools;
import tools.thead.Threads;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ReadButtonRunnable implements Runnable {
    private JLabel output;
    private static boolean isStop = false;

    public ReadButtonRunnable(JLabel textComponent) {
        this.output = textComponent;
    }

    public static boolean isStop() {
        return isStop;
    }


    public static void setStop(boolean stop) {
        isStop = stop;
    }

    @Override
    public void run() {
        List<String> command = new ArrayList<>();
        String id = DeviceRadioButtonActionListener.getId();
        // 告诉主线程当前线程正在运行
        AdbTools.setIsRunning(this);
        // // 程序:cmd.exe
        // command.add("cmd");
        // // 参数:/C 执行字符串指定的命令然后终止
        // command.add("/c");
        // // 参数:adb命令，在屏幕上方从右向左滑动
        // command.add("adb -s 192.168.10.4:5555 shell input swipe 893 233 73 228 200");

        Random random = new Random();
        int min = 5;
        int max = 9;
        String oldText;
        String newText;
        while (!isStop) {
            // 先等待一会 再开始执行命令
            Threads.sleep(900);
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
                    // System.out.println("更新 output");
                    output.setText(newText);
                }
                // else {
                //     System.out.println("不更新 output");
                // }
                count++;
            }

        }
        output.setText("阅读线程：已停止");
    }

    // /**
    //  * 运行一次进程
    //  *
    //  * @param command 要执行的命令。
    //  */
    // private void runOneTime(List<String> command) {
    //     ProcessBuilder processBuilder = new ProcessBuilder(command);
    //     // 子进程和父进程使用相同的输入输出流.
    //     processBuilder = processBuilder.inheritIO();
    //     // 启动子进程
    //     Process process = null;
    //     try {
    //         process = processBuilder.start();
    //         output.setText("阅读线程:从右向左滑动手机");
    //     } catch (IOException ex) {
    //         ex.printStackTrace();
    //     }
    //     //等待线程结束.
    //     try {
    //         process.waitFor();
    //     } catch (InterruptedException ex) {
    //         ex.printStackTrace();
    //     }
    // }
}
