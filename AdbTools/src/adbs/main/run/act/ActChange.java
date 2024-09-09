package adbs.main.run.act;

import adbs.cmd.AdbCommands;
import adbs.main.AdbTools;
import adbs.main.ui.jpanels.timeauto2.TimingPanels2;
import adbs.model.Device;

import java.util.HashSet;

/**
 * 根据Activity的改变做出对应的操作
 */
public abstract class ActChange {
    private static TimingPanels2 timingPanels2;


    private HashSet<ActToAct> w35sDialog_Set;
    private HashSet<ActToAct> w65sDialog_Set;
    private HashSet<ActToAct> w180sDialog_set;

    private HashSet<ActToAct> vw180s_Set;
    private HashSet<ActToAct> vw180sDialog_Set;
    private HashSet<ActToAct> vw1HDialog_Set;

    private HashSet<ActToAct> s35sDialog_Set;
    private HashSet<ActToAct> s65sDialog_Set;
    private HashSet<ActToAct> s35s_Set;
    private HashSet<ActToAct> s_Set;
    private HashSet<ActToAct> rw5HDialog_Set;

    private HashSet<ActToAct> return_Set;


    public ActChange() {
        initSets();
    }

    private void initSets() {
        w35sDialog_Set = set_w35sDialog_Set();
        w65sDialog_Set = set_w65sDialog_Set();
        w180sDialog_set = set_w180sDialog_Set();

        s35s_Set = set_s35s_Set();
        s35sDialog_Set = set_s35sDialog_Set();
        s65sDialog_Set = set_s65sDialog_Set();
        s_Set = set_s_Set();

        vw180s_Set = set_vw180s_Set();
        vw180sDialog_Set = set_vw180sDialog_Set();
        vw1HDialog_Set = set_vw1HDialog_Set();

        rw5HDialog_Set = set_rw5HDialog_Set();
        return_Set = set_return_Set();
    }


    protected abstract HashSet<ActToAct> set_w35sDialog_Set();

    protected abstract HashSet<ActToAct> set_w65sDialog_Set();

    protected abstract HashSet<ActToAct> set_w180sDialog_Set();


    protected abstract HashSet<ActToAct> set_s35sDialog_Set();

    protected abstract HashSet<ActToAct> set_s65sDialog_Set();

    protected abstract HashSet<ActToAct> set_s35s_Set();

    protected abstract HashSet<ActToAct> set_s_Set();

    protected abstract HashSet<ActToAct> set_vw180s_Set();

    protected abstract HashSet<ActToAct> set_vw180sDialog_Set();

    protected abstract HashSet<ActToAct> set_vw1HDialog_Set();

    protected abstract HashSet<ActToAct> set_rw5HDialog_Set();

    protected abstract HashSet<ActToAct> set_return_Set();

    public void onChange(String actBefore, String act) {
        if (timingPanels2 == null) {
            timingPanels2 = AdbTools.getInstance().getTimingPanels2();
        }
        ActToAct actToAct = new ActToAct(actBefore, act);
        // ActToAct actToAct = new ActToAct(actBefore, act);

        if (contains(w35sDialog_Set, actToAct)) {
            // 弹窗询问是否要等待35秒
            timingPanels2.w35sDialog();
        } else if (contains(w65sDialog_Set, actToAct)) {
            // 弹窗询问是否要等待65秒
            timingPanels2.w65sDialog();
        } else if (contains(w180sDialog_set, actToAct)) {
            // 弹窗询问是否需要等待180秒
            timingPanels2.w180sDialog();
        } else if (contains(s65sDialog_Set, actToAct)) {
            // 弹窗询问是否需要逛街65秒
            timingPanels2.s65sDialog();
        } else if (contains(s35sDialog_Set, actToAct)) {
            // 弹窗询问是否需要逛街35秒
            timingPanels2.s35sDialog();
        } else if (contains(s35s_Set, actToAct)) {
            // 弹窗询问是否需要逛街35秒
            timingPanels2.s35s();
        } else if (contains(s_Set, actToAct)) {
            // 显示逛街系列按钮
            timingPanels2.s();
        } else if (contains(vw180sDialog_Set, actToAct)) {
            // 弹窗询问是否需要刷视频180秒
            timingPanels2.vw180sDialog();
        } else if (contains(vw180s_Set, actToAct)) {
            // 直接 刷视频180秒
            timingPanels2.vw180s();
        } else if (contains(vw1HDialog_Set, actToAct)) {
            // timingPanels2.vw180sDialog();
            // 刷视频1小时
            timingPanels2.vw1HDialog();
        } else if (contains(rw5HDialog_Set, actToAct)) {
            timingPanels2.rw5HDialog();
        } else if (contains(return_Set, actToAct)) {
            clickReturn();
        }
    }

    protected void clickReturn() {
        System.out.println("按下返回键");
        Device device = AdbTools.getInstance().getDevice();
        // 按下返回键
        AdbCommands.returnBtn(device);
    }

    /**
     * 判断给定的set集合中是否存在actToAct这个元素。
     *
     * @param set      要查询的Set
     * @param actToAct 要查询的元素
     * @return 如果set不是null, 并且set中存在要查询的元素的话，返回true，否则返回false。
     */
    private boolean contains(HashSet<ActToAct> set, ActToAct actToAct) {
        return set != null && set.contains(actToAct);
    }
}
