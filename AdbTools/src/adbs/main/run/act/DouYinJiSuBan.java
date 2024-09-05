package adbs.main.run.act;

import java.util.HashSet;

public class DouYinJiSuBan extends ActChangeAdapter {
    private static final DouYinJiSuBan instance = new DouYinJiSuBan();

    public static DouYinJiSuBan getInstance() {
        return instance;
    }

    @Override
    protected HashSet<ActToAct> set_return_Set() {
        HashSet<ActToAct> actToActs = new HashSet<>();
        actToActs.add(new ActToAct("com.ss.android.excitingvideo.ExcitingVideoActivity", "com.ss.android.ugc.aweme.live.LivePlayActivity"));

        return actToActs;
    }

    @Override
    protected HashSet<ActToAct> set_w180sDialog_Set() {
        HashSet<ActToAct> actToActs = new HashSet<>();
//        actToActs.add(new ActToAct("com.ss.android.excitingvideo.ExcitingVideoActivity", "com.ss.android.ugc.aweme.live.LivePlayActivity"));
        //        actToActs.add(new ActToAct("com.ss.android.excitingvideo.ExcitingVideoActivity", "com.ss.android.ugc.aweme.live.LivePlayActivity"));
        actToActs.add(new ActToAct("com.ss.android.ugc.aweme.splash.SplashActivity", "com.ss.android.excitingvideo.ExcitingVideoActivity"));
        //        actToActs.add(new ActToAct("com.ss.android.ugc.aweme.splash.SplashActivity", "com.ss.android.excitingvideo.ExcitingVideoActivity"));
        return null;
    }
}
