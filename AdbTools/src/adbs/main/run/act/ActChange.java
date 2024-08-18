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

    private HashSet<ActToAct> s35s_Set;
    private HashSet<ActToAct> s65sDialog_Set;
    private HashSet<ActToAct> s_Set;

    private HashSet<ActToAct> return_Set;


    public ActChange() {
        initSets();
    }

    private void initSets() {
        w35sDialog_Set = set_w35sDialog_Set();
        w65sDialog_Set = set_w65sDialog_Set();
        w180sDialog_set = set_w180sDialog_Set();

        s35s_Set = set_s35s_Set();
        s65sDialog_Set = set_s65sDialog_Set();
        s_Set = set_s_Set();

        vw180s_Set = set_vw180s_Set();

        return_Set = set_return_Set();
    }


    public abstract HashSet<ActToAct> set_w35sDialog_Set();

    public abstract HashSet<ActToAct> set_w65sDialog_Set();

    protected abstract HashSet<ActToAct> set_w180sDialog_Set();


    public abstract HashSet<ActToAct> set_s35s_Set();
    public abstract HashSet<ActToAct> set_s65sDialog_Set();

    public abstract HashSet<ActToAct> set_s_Set();

    public abstract HashSet<ActToAct> set_vw180s_Set();

    public abstract HashSet<ActToAct> set_return_Set();

    public void onChange(String actBefore, String act) {
        if (timingPanels2 == null) {
            timingPanels2 = AdbTools.getInstance().getTimingPanels2();
        }
        ActToAct actToAct = new ActToAct(actBefore, act);
        // ActToAct actToAct = new ActToAct(actBefore, act);

        if (w35sDialog_Set != null && w35sDialog_Set.contains(actToAct)) {
            // 弹窗询问是否要等待35秒
            timingPanels2.w35sDialog();
        } else if (w65sDialog_Set != null && w65sDialog_Set.contains(actToAct)) {
            // 弹窗询问是否要等待65秒
            timingPanels2.w65sDialog();
        } else if (w180sDialog_set != null && w180sDialog_set.contains(actToAct)) {
            // 弹窗询问是否需要等待180秒
            timingPanels2.w180sDialog();
        } else if (s65sDialog_Set != null && s65sDialog_Set.contains(actToAct)) {
            // 弹窗询问是否需要逛街65秒
            timingPanels2.s65sDialog();
        } else if (s35s_Set != null && s35s_Set.contains(actToAct)) {
            // 弹窗询问是否需要逛街35秒
            timingPanels2.s35s();
        } else if (s_Set != null && s_Set.contains(actToAct)) {
            // 显示逛街系列按钮
            timingPanels2.s();
        } else if (vw180s_Set != null && vw180s_Set.contains(actToAct)) {
            // 刷视频180秒
            timingPanels2.vw180s();
        } else if (return_Set != null && return_Set.contains(actToAct)) {
            System.out.println("遇到授权要求，直接返回");
            Device device = AdbTools.getInstance().getDevice();
            // 按下返回键
            AdbCommands.returnBtn(device);
        }
    }
}
