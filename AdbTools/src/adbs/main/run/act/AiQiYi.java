package adbs.main.run.act;

import java.util.HashSet;

public class AiQiYi extends ActChange {
    private static final AiQiYi instance = new AiQiYi();

    public static AiQiYi getInstance() {
        return instance;
    }

    @Override
    protected HashSet<ActToAct> set_w35sDialog_Set() {
        HashSet<ActToAct> w35Dialogs = new HashSet<>();
        w35Dialogs.add(new ActToAct(".webview.WebViewActivity", "com.baidu.mobads.sdk.api.MobRewardVideoActivity"));
//        w35Dialogs.add(new ActToAct(".webview.WebViewActivity", "com.bytedance.sdk.openadsdk.stub.activity.Stub_Standard_Portrait_Activity"));
        return w35Dialogs;
    }

    @Override
    protected HashSet<ActToAct> set_w65sDialog_Set() {
        HashSet<ActToAct> w65sDialog = new HashSet<>();
        w65sDialog.add(new ActToAct(".webview.WebViewActivity", "com.bytedance.sdk.openadsdk.stub.activity.Stub_Standard_Portrait_Activity"));

        return null;
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
    protected HashSet<ActToAct> set_s65sDialog_Set() {
        return null;
    }

    @Override
    protected HashSet<ActToAct> set_s35s_Set() {
        return null;
    }

    @Override
    protected HashSet<ActToAct> set_s_Set() {
        return null;
    }

    @Override
    protected HashSet<ActToAct> set_vw180s_Set() {
        return null;
    }

    @Override
    protected HashSet<ActToAct> set_vw180sDialog_Set() {
        return null;
    }

    @Override
    protected HashSet<ActToAct> set_return_Set() {
        return null;
    }
}
