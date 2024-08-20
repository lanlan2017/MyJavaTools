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

        return w35sDialog;
    }

}
