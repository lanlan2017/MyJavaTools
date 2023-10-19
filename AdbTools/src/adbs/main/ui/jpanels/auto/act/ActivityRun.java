package adbs.main.ui.jpanels.auto.act;

import adbs.cmd.AdbCommands;
import adbs.main.AdbTools;
import adbs.main.run.AdbGetPackage;
import adbs.main.run.model.AppNames;
import adbs.main.ui.jpanels.auto.CoinsType;
import adbs.main.ui.jpanels.auto.act.code.*;
import adbs.main.ui.jpanels.universal.runnable.CloseableRunnable;
import adbs.model.Device;
import adbs.tools.thread.ThreadSleep;

import javax.swing.*;

/**
 * 根据activity自动进行操作
 */
public class ActivityRun extends CloseableRunnable implements CoinsType {
    /**
     * 用来记录当前的activity短名称，当整个数组中的短名称都一样时，说明操作无效，应该则停止线程结束操作
     */
    private static String[] arrActName = new String[3];

    private static ActivityRun runYueDuZaiLing = new ActivityRun(strYueDuZaiLing);
    private static ActivityRun runTingShuZaiLing = new ActivityRun(strTingShuZaiLing);
    private static ActivityRun runYueDuJinBi = new ActivityRun(strYueDuJinBi);
    private static ActivityRun runTingShuJinBi = new ActivityRun(strTingShuJinBi);
    private static ActivityRun runTingShuHongBao = new ActivityRun(strTingShuHongBao);
    private static ActivityRun runFQMFXSTingShu = new ActivityRun(strFQMFXSTingShu);
    private static ActivityRun runFQMFXSYueDu = new ActivityRun(strFQMFXSYueDu);
    private static ActivityRun runFQCT = new ActivityRun(strFQCT);
    private static ActivityRun runShuDuTingShu = new ActivityRun(strShuDuTingShu);
    private static ActivityRun runShuDuYueDu = new ActivityRun(strShuDuYueDu);
    // private static ActDo actDo;


    private String coinType;
    private int times;
    // boolean yijingShangHua = false;
    // public String version;

    public ActivityRun(String coinType) {
        this.coinType = coinType;
        // switch (coinType) {
        //     // case str
        // }
    }

    public static ActivityRun getRun(String coinType) {
        ActivityRun activityRun = null;
        System.out.println("在GetRun中");
        // actDo = null;
        switch (coinType) {
            case strYueDuJinBi:
                // actDo = getRunYueDuJinBi();
                activityRun = runYueDuJinBi;
                break;
            case strTingShuJinBi:
                // actDo = getRunTingShuJinBi();
                activityRun = runTingShuJinBi;
                break;
            case strYueDuZaiLing:
                // actDo = getRunYueDuZaiLing();
                activityRun = runYueDuZaiLing;

                break;
            case strTingShuZaiLing:
                // actDo = getRunTingShuZaiLing();
                activityRun = runTingShuZaiLing;
                break;
            case strTingShuHongBao:
                // actDo = getRunTingShuHongBao();
                activityRun = runTingShuHongBao;
                break;

            case strFQMFXSTingShu:
                activityRun = runFQMFXSTingShu;
                break;

            case strFQMFXSYueDu:
                activityRun = runFQMFXSYueDu;
                break;
            case strFQCT:
                activityRun = runFQCT;
                break;
            case strShuDuTingShu:
                activityRun = runShuDuTingShu;
                break;

            case strShuDuYueDu:
                // ActDo runShuDuYueDu = new ActDo(strShuDuYueDu);
                activityRun = runShuDuYueDu;
        }
        return activityRun;
    }

    // @Override
    // protected void setMsg() {
    //
    // }

    // @Override
    public void stop(String message) {
        // ActDo.stop = true;
        stop();
        // String message = "自动Act停止了";
        message = message + " 停止了";
        JOptionPane.showMessageDialog(AdbTools.getInstance().getContentPane(), message);
    }

    /**
     * 判断是否应该停止操作。
     *
     * @return 当连续点击三次，当前的activity都没有改变改变的时候，就应该停止了。
     */
    private boolean isEnd() {
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
    protected void beforeLoop() {
        super.beforeLoop();
        // zaiYunXingZhiQian();
        stop = false;
        // Arrays.fill(arrActName, "");
        for (int i = 0; i < arrActName.length; i++) {
            arrActName[i] = "" + i;
        }
        times = 0;
    }


    @Override
    protected void loopBody() {
        autoActDo();
    }

    public static void main(String[] args) {
        AdbCommands.hideNavigationBar(AdbTools.getInstance().getDevice());
        // runFQCT = new ActDo(strFQCT);
        // new Thread(runFQCT).start();
        runShuDuTingShu = new ActivityRun(strShuDuTingShu);
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

        while (!stop) {
            AppNames appNames = AdbGetPackage.getAppNames();
            // System.out.println("appNames = " + appNames);
            System.out.println(appNames);
            String packageName = appNames.getPackageName();
            String actShortName = appNames.getActShortName();
            // 根据应用的包名来执行操作
            switchPackageName(device, width, height, packageName, actShortName);

            System.out.println("循环 等待5秒");
            System.out.println();
            ThreadSleep.seconds(5);
        }

        JOptionPane.showMessageDialog(AdbTools.getInstance().getContentPane(), "收金币已停止");
        System.out.println("自动操作停止...");
    }

    /**
     * 根据包名执行不同的操作
     *
     * @param device
     * @param width
     * @param height
     * @param packageName
     * @param actShortName
     */
    private void switchPackageName(Device device, int width, int height, String packageName, String actShortName) {
        // System.out.println("packageName = " + packageName);
        // System.out.println("actShortName = " + actShortName);
        switch (packageName) {
            // 熊猫免费小说
            // 茄子免费小说
            // 星空免费小说
            case "com.xm.freader":
            case "com.qz.freader":
            case "com.xk.qreader":
                if (width == p_1080) {
                    if (height == p_2160) {
                        // shouJinBi(coinType, device, actShortName);
                        // new XingMaoQieZiXingKong_1080_2160(this).shouJinBi(coinType, device, actShortName);
                        XMQZXK1080_2160.shouJinBi(this, coinType, device, actShortName);
                    } else if (height == p_1920) {
                        // shouJinBi(device, actShortName);
                        // new XingMaoQieZiXingKong_1080_1920(this).shouJinBi(coinType, device, actShortName);
                        XMQZXK1080_1920.shouJinBi(this, coinType, device, actShortName);
                    }
                    // if (width ==  width1080 && height==  height2160) {
                }

                break;
            case "com.dragon.read":
                if (width == p_1080 && height == p_2160) {
                    // 番茄免费小说收金币
                    // shouJinBi(device, packageName, actShortName);
                    // new FQMFXS(this).shouJinBi(coinType, device, packageName, actShortName);
                    FQMFXS.shouJinBi(this, coinType, device, packageName, actShortName);
                }

                break;
            case "com.xs.fm":
                if (width == p_1080 && height == p_2160) {
                    FQCT.shouJinBi(this, coinType, device, packageName, actShortName);
                }
                break;

            case "com.dj.speed":
                if (width == p_1080) {
                    // _1920 = 1920;
                    if (height == p_1920) {
                        ShuDu.shuDuJinBi(this, coinType, device, packageName, actShortName);

                    }
                }

                break;

            default:
                // 误操作，关闭其他APP，回到本APP
                AdbCommands.killOtherApp(device);
                break;
        }
    }

    /**
     * 判断是否应该停止
     *
     * @param strYueDuZaiLing
     */
    public void isEndTest(String strYueDuZaiLing) {
        if (isEnd()) {
            System.out.println("判断： 听书再领 结束了！");
            stop(strYueDuZaiLing);
        }
        times++;
    }

    public void addArrActName(String actShortName) {
        arrActName[times % arrActName.length] = actShortName;
    }

}
