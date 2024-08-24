package adbs.main.run.act;

import java.util.HashSet;

public class TaoBao extends ActChangeAdapter {

    private static final TaoBao instance = new TaoBao();

    public static TaoBao getInstance() {
        return instance;
    }


    @Override
    protected HashSet<ActToAct> set_w35sDialog_Set() {

        HashSet<ActToAct> set_w35Dialog = new HashSet<>();
        set_w35Dialog.add(new ActToAct("com.taobao.tao.welcome.Welcome", "com.taobao.browser.exbrowser.BrowserUpperActivity"));
        set_w35Dialog.add(new ActToAct("com.taobao.tao.welcome.Welcome", "com.taobao.android.detail2.core.framework.NewDetailActivity"));

        set_w35Dialog.add(new ActToAct("com.taobao.browser.BrowserActivity", "com.taobao.browser.exbrowser.BrowserUpperActivity"));
        set_w35Dialog.add(new ActToAct("com.taobao.browser.BrowserActivity", "com.taobao.android.detail2.core.framework.NewDetailActivity"));

        set_w35Dialog.add(new ActToAct("com.taobao.themis.container.app.TMSActivity", "com.taobao.android.detail2.core.framework.NewDetailActivity"));


        return set_w35Dialog;
    }

    @Override
    protected HashSet<ActToAct> set_s35sDialog_Set() {
        HashSet<ActToAct> s35sDialog = new HashSet<>();
        s35sDialog.add(new ActToAct("com.taobao.tao.welcome.Welcome", "com.taobao.themis.container.app.TMSActivity"));
        s35sDialog.add(new ActToAct("com.taobao.tao.welcome.Welcome", "com.taobao.search.sf.MainSearchResultActivity"));

        s35sDialog.add(new ActToAct("com.taobao.browser.BrowserActivity", "com.taobao.themis.container.app.TMSActivity"));

        s35sDialog.add(new ActToAct("com.taobao.themis.container.app.TMSActivity", "com.taobao.browser.BrowserActivity"));
        s35sDialog.add(new ActToAct("com.taobao.themis.container.app.TMSActivity", "com.taobao.android.layoutmanager.container.MultiPageContainerActivity"));

//        s35sDialog.add(new ActToAct("com.taobao.browser.exbrowser.BrowserUpperActivity", "com.taobao.browser.BrowserActivity"));
        s35sDialog.add(new ActToAct("com.taobao.search.searchdoor.SearchDoorActivity", "com.taobao.search.sf.MainSearchResultActivity"));
        return s35sDialog;
    }

    @Override
    protected HashSet<ActToAct> set_return_Set() {
        HashSet<ActToAct> return_set = new HashSet<>();
        return_set.add(new ActToAct("com.taobao.tao.TBMainActivity", "com.taobao.taolive.room.TaoLiveVideoActivity"));

        return return_set;
    }
}
