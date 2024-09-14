package adbs.main.run.act;

import java.util.HashSet;

public class TaoTe extends ActChangeAdapter {
    private static final TaoTe instance = new TaoTe();

    public static TaoTe getInstance() {
        return instance;
    }

    @Override
    protected HashSet<ActToAct> set_s35sDialog_Set() {
        HashSet<ActToAct> s35Dialog = new HashSet<>();
        s35Dialog.add(new ActToAct("com.litetao.app.MNWebActivity", "com.taobao.themis.container.app.TMSActivity"));
        s35Dialog.add(new ActToAct("com.taobao.themis.container.app.TMSActivity", "com.litetao.app.MNWebActivity"));
        return s35Dialog;
    }

    @Override
    protected HashSet<ActToAct> set_s_Set() {
        HashSet<ActToAct> s_set = new HashSet<>();
        //首页，进入现金签到页面
        s_set.add(new ActToAct("com.taobao.ltao.maintab.MainFrameActivity", "com.taobao.themis.container.app.TMSActivity"));
        return s_set;
    }
}
