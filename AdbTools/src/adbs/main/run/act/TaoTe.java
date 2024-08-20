package adbs.main.run.act;

import java.util.HashSet;

public class TaoTe extends ActChangeAdapter {
    private static TaoTe instance=new TaoTe();

    public static TaoTe getInstance() {
        return instance;
    }

    @Override
    protected HashSet<ActToAct> set_s35sDialog_Set() {
        HashSet<ActToAct> s35Dialog = new HashSet<>();
        s35Dialog.add(new ActToAct("com.litetao.app.MNWebActivity", "com.taobao.themis.container.app.TMSActivity"));
        return s35Dialog;
    }
}
