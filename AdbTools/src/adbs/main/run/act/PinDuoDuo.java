package adbs.main.run.act;

import java.util.HashSet;

public class PinDuoDuo extends ActChangeAdapter {
    private static final PinDuoDuo instance = new PinDuoDuo();

    public static PinDuoDuo getInstance() {
        return instance;
    }

    @Override
    protected HashSet<ActToAct> set_return_Set() {
        HashSet<ActToAct> actToActs = new HashSet<>();
        //        从刷视频推荐页面 离开
        actToActs.add(new ActToAct(".ui.activity.HomeActivity", ".activity.NewPageActivity"));
        return actToActs;
        //        return super.set_return_Set();
    }
}
