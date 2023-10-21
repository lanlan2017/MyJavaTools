package adbs.main.ui.jpanels.universal.runnable;

import adbs.cmd.AdbCommands;
import adbs.main.AdbTools;
import adbs.main.run.AdbGetPackage;
import adbs.main.run.model.AppNames;
import adbs.model.Device;
import tools.random.Randoms;
import tools.thead.Threads;

import javax.swing.*;

public class VideoButtonRunnable extends CloseableRunnable {
    private boolean stopWait = false;

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

    // @Override
    // protected void setMsg() {
    //     msg = "刷视频";
    //     // msg = "";
    // }

    @Override
    protected void loopBody() {
        AdbTools adbTools = AdbTools.getInstance();
        Device device = adbTools.getDevice();
        JLabel output2 = adbTools.getUniversalPanels().getOutput2();

        // 毫秒计数器
        int msCount = 0;
        // 生成[min,Max]区间的随机整数
        int randomInt = Randoms.getRandomInt(min, max);
        // 要求等待的毫秒数
        int msToWait = randomInt * 1000;
        //
        // // 如果向上滑动之后，跳转到其他activity，则修复
        isNotVideoActivity(device);
        // 向上滑动，如果滑动失败的话
        slideUp(device);
        // 等待并显示倒计时
        wait_(device, output2, msCount, msToWait);
    }

    private void slideUp(Device device) {
        if (isSlideUpError(device)) {
            // return;
            stop();
        }
    }

    /**
     * 等待，并显示倒计时。
     *
     * @param output2
     * @param msCount  计时器
     * @param msToWait 需要等待的总时间
     */
    private void wait_(Device device, JLabel output2, int msCount, int msToWait) {
        stopWait = false;
        // 小片段等待循环
        while (msCount < msToWait && !stopWait) {
            // 当stop标记为true时，退出小片段等待
            // if (isStop()) {
            if (stop) {
                // stop = true;
                break;
            }
            // 等待一小段时间
            Threads.sleep(250);
            msCount += 250;
            // if (msCount % 3000 == 0) {
            if (msCount % 2000 == 0) {
                // System.out.println("msCount = " + msCount);
                // 如果向上滑动之后，跳转到其他activity，则修复
                isNotVideoActivity(device);
            }
            // 显示倒计时
            showCountdown(output2, msCount, msToWait);
        }
    }

    /**
     * 检查是不是视频activity
     * <p>
     * 如果不是视频activity，则执行操作，回到视频activity
     *
     * @param device 要检查
     */
    private void isNotVideoActivity(Device device) {
        AppNames appNames = AdbGetPackage.getAppNames();

        String packageName = appNames.getPackageName();
        String actShortName = appNames.getActShortName();
        switch (packageName) {
            case "com.smile.gifmaker":
                // 如果在其他界面
                // if (isNotVideoAct_KuaiShou(device, actShortName)) {
                // if (isNotVideoAct_KuaiShou(device, actShortName)) {
                // isSlideUpError(device)
                // slideUp(device);
                // stopWait = true;
                // }
                isNotVideoAct_KuaiShou(device, actShortName);

                break;

        }
    }

    private void isNotVideoAct_KuaiShou(Device device, String actShortName) {
        // boolean inOtherActivity = false;
        // 如果是快手APP
        switch (actShortName) {
            case "com.yxcorp.gifshow.HomeActivity":
                // inOtherActivity = false;
                stopWait = false;
                break;

            case "com.yxcorp.gifshow.detail.PhotoDetailActivity":
            case "com.yxcorp.gifshow.webview.KwaiYodaWebViewActivity":
            case "com.yxcorp.gifshow.profile.activity.UserProfileActivity":
            case "com.kuaishou.live.core.basic.activity.LiveSlideActivity":
            case "com.isNotVideoAct_KuaiShou.live.core.basic.activity.LiveSlideActivity":
                // case "com.isNotVideoAct_KuaiShou.live.core.basic.activity.LiveSlideActivity":
            default:
                // 如果是要求安全验证的界面
                // 按返回键
                AdbCommands.returnBtn(device);
                // AdbCommands.swipeBottom2TopOnLeft(device);
                // inOtherActivity = true;
                stopWait = true;
                break;
        }
        // return inOtherActivity;
    }


    /**
     * 向上滑动是否出错
     *
     * @param device 要向上滑动的设备的对象
     * @return 如果向上滑动的adb命令报错，则返回true，否则返回false。
     */
    private boolean isSlideUpError(Device device) {
        String result = AdbCommands.swipeBottom2TopOnLeft(device);
        // 如果滑动的返回值有问题，则停止主线程
        if (AdbCommands.ifDeviceNotExist(result))
            return true;
        return false;
    }

    /**
     * 在JLable上显示倒计时
     *
     * @param output2  用来倒计时显示的JLable对象
     * @param msCount  已经等待的时间
     * @param msToWait 需要等待的时间
     */
    private void showCountdown(JLabel output2, int msCount, int msToWait) {
        String newOutput;
        String oldOutput;
        oldOutput = output2.getText();
        newOutput = ((msToWait - msCount) / 1000) + "s↑";
        // 如果新旧内容不相等，则设置为新内容
        if (!newOutput.equals(oldOutput)) {
            // output.setText(newOutput);
            output2.setText(newOutput);
        }
    }


}
