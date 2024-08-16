package adbs.main.run.act;

import adbs.cmd.AdbCommands;
import adbs.main.AdbTools;
import adbs.main.ui.jpanels.timeauto2.TimingPanels2;
import adbs.model.Device;

import java.util.HashSet;

public class DianTao {

    private static TimingPanels2 timingPanels2;
    private static final HashSet<ActToAct> actToAct_s65;
    private static final HashSet<ActToAct> actToAct_s;
    private static final HashSet<ActToAct> actToAct_vw;
    private static final HashSet<ActToAct> actToAct_w60;
    private static final HashSet<ActToAct> actToAct_return;


    static {
        actToAct_s65 = new HashSet<>();
        //从元宝中心，进入 上新日历
        actToAct_s65.add(new ActToAct(".pha.PHAContainerActivity", ".h5.BrowserActivity"));

        //哪些情况下，切换到逛街面板
        actToAct_s = new HashSet<>();
        //从元宝中心，进入 精品推荐
        actToAct_s.add(new ActToAct(".pha.PHAContainerActivity", ".h5.BrowserUpperActivity"));
        // 从直播 回到元宝中心
        actToAct_s.add(new ActToAct(".TaoLiveVideoActivity", ".pha.PHAContainerActivity"));
        // 从直播 转到 走路
        actToAct_s.add(new ActToAct(".TaoLiveVideoActivity", ".h5.BrowserActivity"));


        actToAct_s.add(new ActToAct(".h5.BrowserActivity", ".h5.BrowserUpperActivity"));

        actToAct_s.add(new ActToAct(".h5.BrowserUpperActivity", ".h5.BrowserActivity"));

        //从头条广告界面 进入 元宝中心
        actToAct_s.add(new ActToAct("com.bytedance.sdk.openadsdk.stub.activity.Stub_Standard_Portrait_Activity", ".pha.PHAContainerActivity"));

        // 哪些条件下刷视频180秒
        actToAct_vw = new HashSet<>();
        actToAct_vw.add(new ActToAct(".pha.PHAContainerActivity", ".TaoLiveVideoActivity"));
        // 走路，转到 直播
        actToAct_vw.add(new ActToAct(".h5.BrowserActivity", ".TaoLiveVideoActivity"));
        actToAct_vw.add(new ActToAct(".h5.BrowserActivity", "com.taobao.video.VideoListActivity"));

        //哪些情况下等待65秒
        actToAct_w60 = new HashSet<>();
        actToAct_w60.add(new ActToAct(".pha.PHAContainerActivity", "com.bytedance.sdk.openadsdk.stub.activity.Stub_Standard_Portrait_Activity"));

        // 跳过安全验证
        actToAct_return = new HashSet<>();
        actToAct_return.add(new ActToAct(".TaoLiveVideoActivity", "com.taobao.taolive.sdk.permisson.PermissionActivity"));
        actToAct_return.add(new ActToAct(".h5.BrowserUpperActivity", "com.alibaba.wireless.security.open.middletier.fc.ui.ContainerActivity"));
        actToAct_return.add(new ActToAct(".pha.PHAContainerActivity", "com.alibaba.wireless.security.open.middletier.fc.ui.ContainerActivity"));


    }

    public static void onChange(String actBefore, String act) {
        if (timingPanels2 == null) {
            timingPanels2 = AdbTools.getInstance().getTimingPanels2();
        }
        ActToAct actToAct = new ActToAct(actBefore, act);
        if (actToAct_s65.contains(actToAct)) {
            timingPanels2.s65sDialog();
        } else if (actToAct_s.contains(actToAct)) {
            timingPanels2.s();
        } else if (actToAct_vw.contains(actToAct)) {
            timingPanels2.vw180s();
        } else if (actToAct_w60.contains(actToAct)) {
            timingPanels2.w65sDialog();
        } else if (actToAct_return.contains(actToAct)) {
            System.out.println("遇到授权要求，直接返回");
            Device device = AdbTools.getInstance().getDevice();
            AdbCommands.returnBtn(device);
        }
    }
}
