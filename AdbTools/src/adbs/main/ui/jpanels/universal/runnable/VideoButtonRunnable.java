package adbs.main.ui.jpanels.universal.runnable;

import adbs.cmd.AdbCommands;
import adbs.main.AdbTools;
import adbs.main.ui.inout.InOutputModel;
import tools.random.Randoms;
import tools.thead.Threads;

import javax.swing.*;

public class VideoButtonRunnable extends CloseableRunnable {

    /**
     * 输入输出汇总模型
     */
    private static VideoButtonRunnable instance = new VideoButtonRunnable();


    private VideoButtonRunnable() {
    }

    public static VideoButtonRunnable getInstance() {
        return instance;
    }
    private int min = 7;
    private int max = 14;

    public void setMin(int min) {
        this.min = min;
    }

    public void setMax(int max) {
        this.max = max;
    }

    @Override
    protected void setMsg() {
        msg = "刷视频";
    }

    // @Override
    // protected void beforeLoop() {
    //     super.beforeLoop();
    //     // if (min == 0) {
    //     //     min = 7;
    //     // }
    //     // if (max == 0) {
    //     //     max = 14;
    //     // }
    //     System.out.println("随机等待时间[" + min + "~" + max + "]s");
    // }

    @Override
    protected void loopBody() {
        String id = AdbTools.device.getId();

        String oldOutput;
        String newOutput;
        // JLabel output=inOutputModel.getOutput();
        // 毫秒计数器
        int msCount = 0;
        // 生成[min,Max]区间的随机整数
        int randomInt = Randoms.getRandomInt(min, max);
        // 要求等待的毫秒数
        int msToWait = randomInt * 1000;

        // // 在手机左侧，从下往上滑动
        // String adbResult = AdbCommands.swipeBottom2TopOnLeft(id);

        // // 在手机左侧，从下往上滑动
        // String adbResult = AdbCommands.swipeBottom2TopOnLeft(id, DeviceListener.getHeight());
        String adbResult = AdbCommands.swipeBottom2TopOnLeft(id, AdbTools.device.getHeight());


        if (adbResult.startsWith("Error!ExitCode=")) {
            System.out.println("adb命令运行错误，退出程序." + adbResult);
            return;
            // break;
        }
        // 小片段等待循环
        while (msCount < msToWait) {
            // 当stop标记为true时，退出小片段等待
            // if (isStop()) {
            if (stop) {
                // stop = true;
                break;
            }
            // 等待一小段时间
            Threads.sleep(250);
            msCount += 250;

            JLabel output2 = inOutputModel.getUniversalPanels().getOutput2();
            // oldOutput = output.getText();
            oldOutput = output2.getText();
            // newOutput = msg + ":等待--" + ((msToWait - msCount) / 1000) + "s";
            newOutput = ((msToWait - msCount) / 1000) + "s后"+msg;
            // 如果新旧内容不相等，则设置为新内容
            if (!newOutput.equals(oldOutput)) {
                // output.setText(newOutput);
                output2.setText(newOutput);
            }
        }
    }

    // @Override
    // protected void afterLoop() {
    //     super.afterLoop();
    //     inOutputModel.getTimePanels().getTimerJLabel().setText("");
    // }
}
