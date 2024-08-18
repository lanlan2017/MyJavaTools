package adbs.main.run.act;

import java.util.HashSet;

public class DouYinJiSuBan extends ActChange {
    private static final DouYinJiSuBan instance = new DouYinJiSuBan();

    public static DouYinJiSuBan getInstance() {
        return instance;
    }

    @Override
    public HashSet<ActToAct> setSet_w35sDialog() {
        return null;
    }

    @Override
    public HashSet<ActToAct> setSet_w65sDialog() {
        return null;
    }

    @Override
    protected HashSet<ActToAct> setSet_w180sDialog() {
        HashSet<ActToAct> actToActs = new HashSet<>();
        actToActs.add(new ActToAct("com.ss.android.excitingvideo.ExcitingVideoActivity", "com.ss.android.ugc.aweme.live.LivePlayActivity"));
        //        "com.ss.android.excitingvideo.ExcitingVideoActivity","com.ss.android.ugc.aweme.live.LivePlayActivity"
        return null;
    }

    @Override
    public HashSet<ActToAct> setSet_vw180s() {
        return null;
    }

    @Override
    public HashSet<ActToAct> setSet_s35s() {
        return null;
    }

    @Override
    public HashSet<ActToAct> setSet_s65sDialog() {
        return null;
    }

    @Override
    public HashSet<ActToAct> setSet_s() {
        return null;
    }

    @Override
    public HashSet<ActToAct> setSet_return() {
        return null;
    }
}
