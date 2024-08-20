package adbs.main.run.act;

import java.util.HashSet;

public class KuaiShouMianFeiXiaoShuo extends ActChangeAdapter {

    private static KuaiShouMianFeiXiaoShuo instance=new KuaiShouMianFeiXiaoShuo();

    public static KuaiShouMianFeiXiaoShuo getInstance() {
        return instance;
    }

    @Override
    public HashSet<ActToAct> set_rw5HDialog_Set() {
        HashSet<ActToAct> rw5H_set = new HashSet<>();
        rw5H_set.add(new ActToAct("com.kuaishou.novel.MainActivity", "com.kuaishou.novel.read.ReaderActivityV2"));
        return rw5H_set;
    }

}
