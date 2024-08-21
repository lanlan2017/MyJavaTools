package adbs.main.run.act;

import java.util.HashSet;

public class XiGuaShiPin extends ActChangeAdapter {
    private static final XiGuaShiPin instance = new XiGuaShiPin();

    public static XiGuaShiPin getInstance() {
        return instance;
    }

    @Override
    protected HashSet<ActToAct> set_return_Set() {
        HashSet<ActToAct> return_set = new HashSet<>();
        // 从精选，进入直播间
        return_set.add(new ActToAct(".activity.SplashActivity", "com.ixigua.openliveplugin.live.LivePlayerActivity"));
        return return_set;
    }
}
