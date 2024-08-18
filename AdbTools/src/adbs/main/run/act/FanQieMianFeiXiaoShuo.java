package adbs.main.run.act;

import java.util.HashSet;

public class FanQieMianFeiXiaoShuo extends ActChange {

    // 单例模式
    // 实例对象
    // instanceObject
    private static final FanQieMianFeiXiaoShuo instance = new FanQieMianFeiXiaoShuo();


    public static FanQieMianFeiXiaoShuo getInstance() {
        return instance;
    }

    @Override
    public HashSet<ActToAct> set_s_Set() {
        return null;
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
        HashSet<ActToAct> set_w180sDialog = new HashSet<>();
        set_w180sDialog.add(new ActToAct("com.ss.android.excitingvideo.ExcitingVideoActivity", "com.bytedance.mira.stub.p0.StubSingleTaskActivity1"));
        //        set_w180sDialog.add(new ActToAct());

        return set_w180sDialog;
    }

    @Override
    protected HashSet<ActToAct> set_s35sDialog_Set() {
        return null;
    }

    @Override
    public HashSet<ActToAct> set_vw180s_Set() {
        return null;
    }

    @Override
    public HashSet<ActToAct> set_vw180sDialog_Set() {
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
    public HashSet<ActToAct> set_return_Set() {
        return null;
    }
}
