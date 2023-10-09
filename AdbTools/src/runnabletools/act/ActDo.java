package runnabletools.act;

import adbs.cmd.AdbCommands;
import adbs.main.AdbTools;
import adbs.main.run.AdbGetPackage;
import adbs.main.run.model.AppNames;
import adbs.main.ui.jpanels.auto.AdbTap;
import adbs.main.ui.jpanels.auto.CoinsType;
import adbs.main.ui.jpanels.universal.runnable.CloseableRunnable;
import adbs.model.Device;
import adbs.tools.thread.ThreadSleep;

import javax.swing.*;
import java.util.Arrays;

/**
 * 根据activity自动进行操作
 */
public class ActDo extends CloseableRunnable implements CoinsType {
    /**
     * 用来记录当前的activity短名称，当整个数组中的短名称都一样时，说明操作无效，应该则停止线程结束操作
     */
    private static String[] arrActName = new String[3];

    private static ActDo runYueDuZaiLing = new ActDo(strYueDuZaiLing);
    private static ActDo runTingShuZaiLing = new ActDo(strTingShuZaiLing);


    private String coinType;

    public ActDo(String coinType) {
        this.coinType = coinType;
    }

    public static ActDo getRunYueDuZaiLing() {
        return runYueDuZaiLing;
    }

    public static ActDo getRunTingShuZaiLing() {
        return runTingShuZaiLing;
    }

    // private static boolean stop = false;

    @Override
    protected void setMsg() {

    }

    @Override
    public void stop() {
        // ActDo.stop = true;
        super.stop();
        JOptionPane.showMessageDialog(AdbTools.getInstance().getContentPane(), "自动Act停止了");
    }

    /**
     * 判断是否应该停止操作。
     *
     * @return 当连续点击三次，当前的activity都没有改变改变的时候，就应该停止了。
     */
    private  boolean isEnd() {
        // System.out.println("arrActName[0] = " + arrActName[0]);
        int i = 1;
        for (; i < arrActName.length; i++) {
            // System.out.println("arrActName[" + i + "] = " + arrActName[i]);
            // 如果找到不相等的元素
            if (!arrActName[0].equals(arrActName[i])) {
                // 停止搜索
                break;
            }
        }

        // System.out.println("i = " + i);
        // System.out.println("arrActName.length = " + arrActName.length);

        // boolean b =

        return i == arrActName.length;
    }


    @Override
    protected void loopBody() {
        autoActDo();
    }

    public static void main(String[] args) {

        // autoActDo();

        // runTingShuZaiLing = new ActDo(strTingShuZaiLing);

        // new Thread(runTingShuZaiLing).start();
        // new Thread(runYueDuZaiLing).start();

    }

    @Override
    protected void beforeLoop() {
        super.beforeLoop();
        zaiYunXingZhiQian();
    }

    /**
     * 根据activity自动操作
     */
    private void autoActDo() {
        // zaiYunXingZhiQian();

        AdbTools adbTools = AdbTools.getInstance();
        Device device = adbTools.getDevice();
        int width = device.getWidth();
        int height = device.getHeight();

        System.out.println("width = " + width);
        System.out.println("height = " + height);
        int times = 0;
        while (!stop) {

            AppNames appNames = AdbGetPackage.getAppNames();
            // System.out.println("appNames = " + appNames);
            System.out.println(appNames);

            String packageName = appNames.getPackageName();
            String actShortName = appNames.getActShortName();


            // System.out.println("packageName = " + packageName);
            // System.out.println("actShortName = " + actShortName);
            switch (packageName) {
                // 熊猫免费小说
                // 茄子免费小说
                // 星空免费小说
                case "com.xm.freader":
                case "com.qz.freader":
                case "com.xk.qreader":
                    if (width == 1080 && height == 2160) {
                        freader_1080_2160(device, actShortName);
                        // 记录打开的act短名称
                        arrActName[times % arrActName.length] = actShortName;
                    }

                    if (isEnd()) {
                        stop();
                    }
                    times++;


                    break;
                case "com.huawei.fastapp":
                case "com.huawei.hwid":
                default:
                    ThreadSleep.seconds(3);
                    AdbCommands.taskBtn(device);
                    ThreadSleep.seconds(3);
                    AdbCommands.swipeBotton2TopOnRight(device);
                    ThreadSleep.seconds(3);
                    AdbTap.tapCenterPosition(device);
                    ThreadSleep.seconds(3);

                    break;
            }
            System.out.println("循环 等待5秒");
            ThreadSleep.seconds(5);
        }

        System.out.println("自动操作停止...");
    }

    private void zaiYunXingZhiQian() {
        stop = false;
        Arrays.fill(arrActName, "");
        // System.out.println("arrActName = " + arrActName);
    }



    private void freader_1080_2160(Device device, String actShortName) {
        // System.out.println("actShortName = " + actShortName);
        switch (actShortName) {
            // 金币领取界面
            case "com.kmxs.reader.webview.ui.DefaultNewWebActivity":

                switch (coinType) {
                    case strTingShuZaiLing:
                        System.out.println("金币模式：" + strTingShuZaiLing);
                        tingShuZaiLing(device);
                        break;
                    case strYueDuZaiLing:
                        System.out.println("金币模式：" + strYueDuZaiLing);
                        wait_tap(device, 5, new WeiZhi(888, 1201));
                        break;

                }


                break;
            case "com.bytedance.sdk.openadsdk.stub.activity.Stub_Standard_Portrait_Activity":

                ThreadSleep.seconds(2);
                System.out.println("右侧向上滑动");
                AdbCommands.swipeBotton2TopOnRight(device);
                int closeGuangGaoX = 979;
                int closeGuangGaoY = 97;
                WeiZhi closeGuangGao = new WeiZhi(closeGuangGaoX, closeGuangGaoY);
                // wait_tap(device, 5, closeGuangGaoX, closeGuangGaoY);
                // wait_tap(device, 35, closeGuangGaoX, closeGuangGaoY);
                wait_tap(device, 35, closeGuangGao);

            case "com.qq.e.ads.PortraitADActivity":
                wait_tap(device, 35, new WeiZhi(979, 161));
                break;
            case "com.qimao.qmreader.commonvoice.CommonVoiceActivityV2":
                //
                wait_tap(device, 5, new WeiZhi(952, 161));
                break;
            case "com.kwad.sdk.api.proxy.app.KsRewardVideoActivity":
                wait_tap(device, 35, new WeiZhi(989, 121));
                break;
            // case "com.kwad.sdk.api.proxy.app.KsRewardVideoActivity":

            // case "":
            //     break;


        }
    }

    private static void tingShuZaiLing(Device device) {
        int audioX = 884;
        int audioY = 1651;
        WeiZhi tingShuZaiLing = new WeiZhi(audioX, audioY);
        // wait_tap(device, 5, audioX, audioY);
        wait_tap(device, 5, tingShuZaiLing);
    }

    private static void wait_tap(Device device, int seconds, WeiZhi tingShuZaiLing) {
        wait_tap(device, seconds, tingShuZaiLing.getX(), tingShuZaiLing.getY());
    }

    private static void wait_tap(Device device, int seconds, int x, int y) {
        System.out.print("操作 等待" + seconds + "秒,");
        ThreadSleep.seconds(seconds);
        // ThreadSleep.seconds(35);
        System.out.print("点击:" + x + "," + y);
        System.out.println();
        AdbTap.tap(device, x, y);
        System.out.println();
    }
}
