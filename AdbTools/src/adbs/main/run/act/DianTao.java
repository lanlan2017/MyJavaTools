package adbs.main.run.act;

import adbs.cmd.AdbCommands;
import adbs.main.AdbTools;
import adbs.main.ui.jpanels.timeauto2.TimingPanels2;
import adbs.model.Device;

import java.util.HashSet;

public class DianTao {

    private static TimingPanels2 timingPanels2;
    private static final HashSet<ActToAct> set_s65sDialog;
    private static final HashSet<ActToAct> set_s;
    private static final HashSet<ActToAct> set_vw180s;
    private static final HashSet<ActToAct> set_w65sDialog;
    private static final HashSet<ActToAct> set_return;


    static {
        set_s65sDialog = new HashSet<>();
        //
        // 从元宝中心，进入 上新日历
        set_s65sDialog.add(new ActToAct(".pha.PHAContainerActivity", ".h5.BrowserActivity"));
        // 元宝中心 进入 精品推荐
        set_s65sDialog.add(new ActToAct(".pha.PHAContainerActivity", ".h5.BrowserUpperActivity"));
        //
        set_s65sDialog.add(new ActToAct(".h5.BrowserActivity", ".h5.BrowserUpperActivity"));

        //哪些情况下，切换到逛街面板
        set_s = new HashSet<>();
        //从元宝中心，进入 精品推荐
        //        set_s.add(new ActToAct(".pha.PHAContainerActivity", ".h5.BrowserUpperActivity"));
        // 从直播 回到元宝中心
        set_s.add(new ActToAct(".TaoLiveVideoActivity", ".pha.PHAContainerActivity"));
        // 从直播 转到 走路
        set_s.add(new ActToAct(".TaoLiveVideoActivity", ".h5.BrowserActivity"));


        set_s.add(new ActToAct(".h5.BrowserActivity", ".h5.BrowserUpperActivity"));

        set_s.add(new ActToAct(".h5.BrowserUpperActivity", ".h5.BrowserActivity"));

        //从头条广告界面 进入 元宝中心
        set_s.add(new ActToAct("com.bytedance.sdk.openadsdk.stub.activity.Stub_Standard_Portrait_Activity", ".pha.PHAContainerActivity"));

        // 哪些条件下刷视频180秒
        set_vw180s = new HashSet<>();
        set_vw180s.add(new ActToAct(".pha.PHAContainerActivity", ".TaoLiveVideoActivity"));
        // 走路，转到 直播
        set_vw180s.add(new ActToAct(".h5.BrowserActivity", ".TaoLiveVideoActivity"));
        set_vw180s.add(new ActToAct(".h5.BrowserActivity", "com.taobao.video.VideoListActivity"));
        set_vw180s.add(new ActToAct("com.alibaba.wireless.security.open.middletier.fc.ui.ContainerActivity",".TaoLiveVideoActivity"));

        //哪些情况下等待65秒
        set_w65sDialog = new HashSet<>();
        set_w65sDialog.add(new ActToAct(".pha.PHAContainerActivity", "com.bytedance.sdk.openadsdk.stub.activity.Stub_Standard_Portrait_Activity"));
        set_w65sDialog.add(new ActToAct(".pha.PHAContainerActivity", "com.baidu.mobads.sdk.api.MobRewardVideoActivity"));

        // 跳过安全验证
        set_return = new HashSet<>();
        set_return.add(new ActToAct(".TaoLiveVideoActivity", "com.taobao.taolive.sdk.permisson.PermissionActivity"));
        set_return.add(new ActToAct(".h5.BrowserUpperActivity", "com.alibaba.wireless.security.open.middletier.fc.ui.ContainerActivity"));
        set_return.add(new ActToAct(".pha.PHAContainerActivity", "com.alibaba.wireless.security.open.middletier.fc.ui.ContainerActivity"));


    }

    /**
     * 点淘
     * @param actBefore
     * @param act
     */
    public static void onChange(String actBefore, String act) {
        if (timingPanels2 == null) {
            timingPanels2 = AdbTools.getInstance().getTimingPanels2();
        }
        ActToAct actToAct = new ActToAct(actBefore, act);
        if (set_s65sDialog.contains(actToAct)) {
            timingPanels2.s65sDialog();
        } else if (set_s.contains(actToAct)) {
            timingPanels2.s();
        } else if (set_vw180s.contains(actToAct)) {
            timingPanels2.vw180s();
        } else if (set_w65sDialog.contains(actToAct)) {
            timingPanels2.w65sDialog();
        } else if (set_return.contains(actToAct)) {
            System.out.println("遇到授权要求，直接返回");
            Device device = AdbTools.getInstance().getDevice();
            AdbCommands.returnBtn(device);
        }
    }
}
