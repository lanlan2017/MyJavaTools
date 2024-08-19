package adbs.main.run.act;

import java.util.HashSet;

public class TaoBaoChange extends ActChange {

    private static final TaoBaoChange instance = new TaoBaoChange();

    public static TaoBaoChange getInstance() {
        return instance;
    }


    @Override
    protected HashSet<ActToAct> set_w35sDialog_Set() {

        HashSet<ActToAct> set_w35Dialog = new HashSet<>();
        set_w35Dialog.add(new ActToAct("com.taobao.tao.welcome.Welcome", "com.taobao.browser.exbrowser.BrowserUpperActivity"));
        set_w35Dialog.add(new ActToAct("com.taobao.browser.BrowserActivity", "com.taobao.android.detail2.core.framework.NewDetailActivity"));
        set_w35Dialog.add(new ActToAct("com.taobao.themis.container.app.TMSActivity", "com.taobao.android.detail2.core.framework.NewDetailActivity"));


        return set_w35Dialog;
    }

    @Override
    protected HashSet<ActToAct> set_s35sDialog_Set() {
        HashSet<ActToAct> s35sDialog = new HashSet<>();
        // 从淘金币到淘宝视频
        s35sDialog.add(new ActToAct("com.taobao.themis.container.app.TMSActivity", "com.taobao.android.layoutmanager.container.MultiPageContainerActivity"));
        s35sDialog.add(new ActToAct("com.taobao.search.searchdoor.SearchDoorActivity", "com.taobao.search.sf.MainSearchResultActivity"));
        s35sDialog.add(new ActToAct("com.taobao.browser.BrowserActivity", "com.taobao.themis.container.app.TMSActivity"));
        s35sDialog.add(new ActToAct("com.taobao.themis.container.app.TMSActivity", "com.taobao.browser.BrowserActivity"));
        return s35sDialog;
    }


    @Override
    protected HashSet<ActToAct> set_s35s_Set() {
        HashSet<ActToAct> set_s35s = new HashSet<>();
        set_s35s.add(new ActToAct("com.taobao.tao.welcome.Welcome", "com.taobao.themis.container.app.TMSActivity"));
        set_s35s.add(new ActToAct("com.taobao.tao.welcome.Welcome", "com.taobao.search.sf.MainSearchResultActivity"));
        return set_s35s;
    }

    @Override
    protected HashSet<ActToAct> set_w65sDialog_Set() {
        return null;
    }


    @Override
    protected HashSet<ActToAct> set_s_Set() {
        return null;
    }

    @Override
    protected HashSet<ActToAct> set_w180sDialog_Set() {
        return null;
    }


    @Override
    protected HashSet<ActToAct> set_vw180s_Set() {
        return null;
    }

    @Override
    protected HashSet<ActToAct> set_vw180sDialog_Set() {
        return null;
    }

    @Override
    protected HashSet<ActToAct> set_s65sDialog_Set() {
        return null;
    }

    @Override
    protected HashSet<ActToAct> set_return_Set() {
        return null;
    }
}
