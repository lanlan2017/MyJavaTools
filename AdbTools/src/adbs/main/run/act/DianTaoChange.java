package adbs.main.run.act;

import java.util.HashSet;

public class DianTaoChange extends ActChange {
    private  static DianTaoChange instance=new DianTaoChange();

    public static DianTaoChange getInstance() {
        return instance;
    }

    @Override
    public HashSet<ActToAct> set_s_Set() {

        //哪些情况下，切换到逛街面板
        HashSet<ActToAct> set_s = new HashSet<>();
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

        return set_s;
    }

    @Override
    public HashSet<ActToAct> set_w35sDialog_Set() {
        return null;
    }

    @Override
    public HashSet<ActToAct> set_w65sDialog_Set() {

        //哪些情况下等待65秒
        HashSet<ActToAct> set_w65sDialog = new HashSet<>();
        set_w65sDialog.add(new ActToAct(".pha.PHAContainerActivity", "com.bytedance.sdk.openadsdk.stub.activity.Stub_Standard_Portrait_Activity"));
        set_w65sDialog.add(new ActToAct(".pha.PHAContainerActivity", "com.baidu.mobads.sdk.api.MobRewardVideoActivity"));

        return set_w65sDialog;
    }

    @Override
    protected HashSet<ActToAct> set_w180sDialog_Set() {
        return null;
    }

    @Override
    protected HashSet<ActToAct> set_s35sDialog_Set() {
        return null;
    }

    @Override
    public HashSet<ActToAct> set_vw180s_Set() {
        // 哪些条件下刷视频180秒
        HashSet<ActToAct> set_vw180s = new HashSet<>();
        set_vw180s.add(new ActToAct(".pha.PHAContainerActivity", ".TaoLiveVideoActivity"));
        // 走路，转到 直播
        set_vw180s.add(new ActToAct(".h5.BrowserActivity", ".TaoLiveVideoActivity"));
        set_vw180s.add(new ActToAct(".h5.BrowserActivity", "com.taobao.video.VideoListActivity"));
        set_vw180s.add(new ActToAct("com.alibaba.wireless.security.open.middletier.fc.ui.ContainerActivity", ".TaoLiveVideoActivity"));


        return set_vw180s;
    }

    @Override
    public HashSet<ActToAct> set_vw180sDialog_Set() {
        return null;
    }

    @Override
    public HashSet<ActToAct> set_s35s_Set() {
        return null;
    }

    @Override
    public HashSet<ActToAct> set_s65sDialog_Set() {
        HashSet<ActToAct> set_s65sDialog = new HashSet<>();
        //
        // 从元宝中心，进入 上新日历
        set_s65sDialog.add(new ActToAct(".pha.PHAContainerActivity", ".h5.BrowserActivity"));
        // 元宝中心 进入 精品推荐
        set_s65sDialog.add(new ActToAct(".pha.PHAContainerActivity", ".h5.BrowserUpperActivity"));
        //
        set_s65sDialog.add(new ActToAct(".h5.BrowserActivity", ".h5.BrowserUpperActivity"));

        return set_s65sDialog;
    }

    @Override
    public HashSet<ActToAct> set_return_Set() {

        // 跳过安全验证
        HashSet<ActToAct> set_return = new HashSet<>();
        set_return.add(new ActToAct(".TaoLiveVideoActivity", "com.taobao.taolive.sdk.permisson.PermissionActivity"));
        set_return.add(new ActToAct(".h5.BrowserUpperActivity", "com.alibaba.wireless.security.open.middletier.fc.ui.ContainerActivity"));
        set_return.add(new ActToAct(".pha.PHAContainerActivity", "com.alibaba.wireless.security.open.middletier.fc.ui.ContainerActivity"));


        return set_return;
    }
}
