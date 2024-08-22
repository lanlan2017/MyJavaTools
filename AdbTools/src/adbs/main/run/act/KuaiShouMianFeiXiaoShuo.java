package adbs.main.run.act;

import java.util.HashSet;

public class KuaiShouMianFeiXiaoShuo extends ActChangeAdapter {

    private static final KuaiShouMianFeiXiaoShuo instance = new KuaiShouMianFeiXiaoShuo();

    public static KuaiShouMianFeiXiaoShuo getInstance() {
        return instance;
    }

    @Override
    protected HashSet<ActToAct> set_w65sDialog_Set() {
        HashSet<ActToAct> actToActs = new HashSet<>();
        //        从金币界面，进入广告界面
        actToActs.add(new ActToAct("com.kuaishou.novel.MainActivity", "com.kwai.ad.biz.award.AwardVideoPlayActivity"));
//        actToActs.add(new ActToAct("com.kuaishou.novel.MainActivity", "com.kwai.ad.biz.award.AwardVideoPlayActivity"));
//        actToActs.add(new ActToAct("com.kuaishou.novel.MainActivity", "com.kwai.ad.biz.award.AwardVideoPlayActivity"));
        return actToActs;
    }

    @Override
    public HashSet<ActToAct> set_rw5HDialog_Set() {
        HashSet<ActToAct> rw5H_set = new HashSet<>();
        rw5H_set.add(new ActToAct("com.kuaishou.novel.MainActivity", "com.kuaishou.novel.read.ReaderActivityV2"));
        return rw5H_set;
    }

}
