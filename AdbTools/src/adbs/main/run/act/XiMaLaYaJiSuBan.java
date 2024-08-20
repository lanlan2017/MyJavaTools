package adbs.main.run.act;

import java.util.HashSet;

public class XiMaLaYaJiSuBan extends ActChangeAdapter {

    private static XiMaLaYaJiSuBan instance=new XiMaLaYaJiSuBan();

    public static XiMaLaYaJiSuBan getInstance() {
        return instance;
    }

    @Override
    protected HashSet<ActToAct> set_w35sDialog_Set() {
        HashSet<ActToAct> w35sDialog = new HashSet<>();
        w35sDialog.add(new ActToAct("com.ximalaya.ting.android.host.activity.MainActivity", "com.ximalaya.ting.android.adsdk.ADActivity"));
        w35sDialog.add(new ActToAct("com.ximalaya.ting.android.host.activity.MainActivity", "com.bytedance.sdk.openadsdk.stub.activity.Stub_Standard_Activity"));
//        w35sDialog.add(new ActToAct("com.ximalaya.ting.android.host.activity.MainActivity", "com.bytedance.sdk.openadsdk.stub.activity.Stub_Standard_Activity"));

        return w35sDialog;
    }

}
