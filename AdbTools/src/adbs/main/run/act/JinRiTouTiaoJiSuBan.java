package adbs.main.run.act;

import java.util.HashSet;

public class JinRiTouTiaoJiSuBan extends ActChangeAdapter {
    private static final JinRiTouTiaoJiSuBan instance = new JinRiTouTiaoJiSuBan();

    public static JinRiTouTiaoJiSuBan getInstance() {
        return instance;
    }
    @Override
    protected HashSet<ActToAct> set_return_Set() {
        HashSet<ActToAct> returnSet = new HashSet<>();
        returnSet.add(new ActToAct("com.ss.android.ugc.aweme.main.MainActivity", "com.ss.android.ugc.aweme.feed.FeedActivity"));
        return returnSet;
    }
}
