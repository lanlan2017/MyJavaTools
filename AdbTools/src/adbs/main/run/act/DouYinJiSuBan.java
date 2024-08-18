package adbs.main.run.act;

import java.util.HashSet;

public class DouYinJiSuBan extends ActChange {
    private static final DouYinJiSuBan instance = new DouYinJiSuBan();

    public static DouYinJiSuBan getInstance() {
        return instance;
    }

    @Override
    public HashSet<ActToAct> set_w35sDialog_Set() {
        return null;
    }

    @Override
    public HashSet<ActToAct> set_w65sDialog_Set() {
        return null;
    }

    @Override
    protected HashSet<ActToAct> set_w180sDialog_Set() {
        HashSet<ActToAct> actToActs = new HashSet<>();
        actToActs.add(new ActToAct("com.ss.android.excitingvideo.ExcitingVideoActivity", "com.ss.android.ugc.aweme.live.LivePlayActivity"));
        //        "com.ss.android.excitingvideo.ExcitingVideoActivity","com.ss.android.ugc.aweme.live.LivePlayActivity"
        return null;
    }

    @Override
    public HashSet<ActToAct> set_vw180s_Set() {
        return null;
    }

    @Override
    public HashSet<ActToAct> set_s35s_Set() {
        return null;
    }

    @Override
    public HashSet<ActToAct> set_s65sDialog_Set() {
        return null;
    }

    @Override
    public HashSet<ActToAct> set_s_Set() {
        return null;
    }

    @Override
    public HashSet<ActToAct> set_return_Set() {
        return null;
    }
}
