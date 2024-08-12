package adbs.main.run.act;

import adbs.main.AdbTools;
import adbs.main.ui.jpanels.timeauto2.TimingPanels2;

public class DianTao {

    private static TimingPanels2 timingPanels2;

    public static void dianTao(String actBefore, String act) {
        if (timingPanels2 == null) {
            timingPanels2 = AdbTools.getInstance().getTimingPanels2();
        }
        switch (actBefore) {
            // 之前是元宝中心
            case ".pha.PHAContainerActivity":
                switch (act) {
                    // 现在是精品推荐
                    case ".h5.BrowserUpperActivity":
                        // 上新日历
                    case ".h5.BrowserActivity":
                        timingPanels2.s();
                        break;
                    // 现在是直播界面
                    case ".TaoLiveVideoActivity":
                        // timingPanels2.vw();
                        timingPanels2.vw180s();
                        break;
                    //现在是字节的广告界面
                    case "com.bytedance.sdk.openadsdk.stub.activity.Stub_Standard_Portrait_Activity":
                        timingPanels2.w65s();
                        break;
                }
                break;
        }

    }
}
