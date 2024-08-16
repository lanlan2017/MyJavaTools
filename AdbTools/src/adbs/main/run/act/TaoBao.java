package adbs.main.run.act;

import adbs.cmd.AdbCommands;
import adbs.main.AdbTools;
import adbs.main.ui.jpanels.timeauto2.TimingPanels2;
import adbs.model.Device;

import java.util.HashSet;

public class TaoBao {
    private static TimingPanels2 timingPanels2;
    private static final HashSet<ActToAct> actToAct_s35;
    private static final HashSet<ActToAct> actToAct_w35;
    private static final HashSet<ActToAct> actToAct_s;
    private static final HashSet<ActToAct> actToAct_vw;
    private static final HashSet<ActToAct> actToAct_w60;
    private static final HashSet<ActToAct> actToAct_return;


    static {
        actToAct_w35 = new HashSet<>();
        actToAct_w35.add(new ActToAct("com.taobao.tao.welcome.Welcome", "com.taobao.themis.container.app.TMSActivity"));
        actToAct_w35.add(new ActToAct("com.taobao.tao.welcome.Welcome", "com.taobao.browser.exbrowser.BrowserUpperActivity"));
        actToAct_w35.add(new ActToAct("com.taobao.browser.BrowserActivity", "com.taobao.android.detail2.core.framework.NewDetailActivity"));
        actToAct_w35.add(new ActToAct("com.taobao.themis.container.app.TMSActivity", "com.taobao.android.detail2.core.framework.NewDetailActivity"));
        // 从淘金币到淘宝视频
        actToAct_w35.add(new ActToAct("com.taobao.themis.container.app.TMSActivity", "com.taobao.android.layoutmanager.container.MultiPageContainerActivity"));

        actToAct_s35 = new HashSet<>();
        actToAct_s35.clear();
        actToAct_s35.add(new ActToAct("com.taobao.tao.welcome.Welcome", "com.taobao.search.sf.MainSearchResultActivity"));
        actToAct_s35.add(new ActToAct("com.taobao.browser.BrowserActivity", "com.taobao.themis.container.app.TMSActivity"));
        actToAct_s35.add(new ActToAct("com.taobao.themis.container.app.TMSActivity", "com.taobao.browser.BrowserActivity"));
        actToAct_s35.add(new ActToAct("com.taobao.search.searchdoor.SearchDoorActivity", "com.taobao.search.sf.MainSearchResultActivity"));

        //哪些情况下，切换到逛街面板
        actToAct_s = new HashSet<>();
        //        //从元宝中心，进入 精品推荐
        //        actToAct_s.add(new ActToAct(".pha.PHAContainerActivity", ".h5.BrowserUpperActivity"));
        //        //从元宝中心，进入 上新日历
        //        actToAct_s.add(new ActToAct(".pha.PHAContainerActivity", ".h5.BrowserActivity"));
        //        // 从直播 回到元宝中心
        //        actToAct_s.add(new ActToAct(".TaoLiveVideoActivity", ".pha.PHAContainerActivity"));
        //        actToAct_s.add(new ActToAct(".h5.BrowserActivity", ".h5.BrowserUpperActivity"));
        //        actToAct_s.add(new ActToAct(".h5.BrowserUpperActivity", ".h5.BrowserActivity"));
        //从头条广告界面 进入 元宝中心
        //        actToAct_s.add(new ActToAct("com.bytedance.sdk.openadsdk.stub.activity.Stub_Standard_Portrait_Activity", ".pha.PHAContainerActivity"));

        // 哪些条件下刷视频180秒
        actToAct_vw = new HashSet<>();
        //        actToAct_vw.add(new ActToAct(".pha.PHAContainerActivity", ".TaoLiveVideoActivity"));
        //        actToAct_vw.add(new ActToAct(".h5.BrowserActivity", "com.taobao.video.VideoListActivity"));

        //哪些情况下等待65秒
        actToAct_w60 = new HashSet<>();
        //        actToAct_w60.add(new ActToAct(".pha.PHAContainerActivity", "com.bytedance.sdk.openadsdk.stub.activity.Stub_Standard_Portrait_Activity"));

        // 跳过安全验证
        actToAct_return = new HashSet<>();
        //        actToAct_return.add(new ActToAct(".TaoLiveVideoActivity", "com.taobao.taolive.sdk.permisson.PermissionActivity"));
        //        actToAct_return.add(new ActToAct(".h5.BrowserUpperActivity", "com.alibaba.wireless.security.open.middletier.fc.ui.ContainerActivity"));


    }

    public static void onChange(String actBefore, String act) {
        if (timingPanels2 == null) {
            timingPanels2 = AdbTools.getInstance().getTimingPanels2();
        }
        ActToAct actToAct = new ActToAct(actBefore, act);
        if (actToAct_w35.contains(actToAct)) {
            timingPanels2.w35sDialog();
        } else if (actToAct_s35.contains(actToAct)) {
            //            timingPanels2.s35s();
            timingPanels2.s35sDialog();
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
