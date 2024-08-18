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
    public HashSet<ActToAct> setSet_s() {
        return null;
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
        HashSet<ActToAct> set_w180sDialog = new HashSet<>();
        set_w180sDialog.add(new ActToAct("com.ss.android.excitingvideo.ExcitingVideoActivity", "com.bytedance.mira.stub.p0.StubSingleTaskActivity1"));
        //        set_w180sDialog.add(new ActToAct());

        return set_w180sDialog;
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
    public HashSet<ActToAct> setSet_return() {
        return null;
    }
}
