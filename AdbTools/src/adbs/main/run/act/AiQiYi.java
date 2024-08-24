package adbs.main.run.act;

import java.util.HashSet;

public class AiQiYi extends ActChangeAdapter {
    private static final AiQiYi instance = new AiQiYi();

    public static AiQiYi getInstance() {
        return instance;
    }

    @Override
    protected HashSet<ActToAct> set_w35sDialog_Set() {
        HashSet<ActToAct> w35Dialogs = new HashSet<>();
        w35Dialogs.add(new ActToAct(".webview.WebViewActivity", "com.baidu.mobads.sdk.api.MobRewardVideoActivity"));
        w35Dialogs.add(new ActToAct(".webview.WebViewActivity", "com.kwad.sdk.api.proxy.app.KsRewardVideoActivity"));
        //        w35Dialogs.add(new ActToAct(".webview.WebViewActivity", "com.bytedance.sdk.openadsdk.stub.activity.Stub_Standard_Portrait_Activity"));
        return w35Dialogs;
    }

    @Override
    protected HashSet<ActToAct> set_w65sDialog_Set() {
        HashSet<ActToAct> w65sDialog = new HashSet<>();
        w65sDialog.add(new ActToAct(".webview.WebViewActivity", "com.bytedance.sdk.openadsdk.stub.activity.Stub_Standard_Portrait_Activity"));
//        w65sDialog.add(new ActToAct(".webview.WebViewActivity", "com.bytedance.sdk.openadsdk.stub.activity.Stub_Standard_Portrait_Activity"));
//        w65sDialog.add(new ActToAct(".webview.WebViewActivity", "com.mcto.sspsdk.ssp.activity.QyTrueViewActivity"));
        w65sDialog.add(new ActToAct(".webview.WebViewActivity", "com.mcto.sspsdk.ssp.activity.QyTrueViewActivity"));

        w65sDialog.add(new ActToAct(".videoplayer.activity.PlayerV2Activity", "com.mcto.sspsdk.ssp.activity.QyTrueViewActivity"));

        return null;
    }
}
