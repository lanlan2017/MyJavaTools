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


    private HashSet<ActToAct> set_w35sDialog;
    private HashSet<ActToAct> set_w65sDialog;
    private HashSet<ActToAct> set_w180sDialog;

    private HashSet<ActToAct> set_vw180s;

    private HashSet<ActToAct> set_s35s;
    private HashSet<ActToAct> set_s65sDialog;
    private HashSet<ActToAct> set_s;

    private HashSet<ActToAct> set_return;


    public ActChange() {
        initSets();
    }

    private void initSets() {
        set_s = setSet_s();
        set_return = setSet_return();
        set_s65sDialog = setSet_s65sDialog();
        set_s35s = setSet_s35s();
        set_vw180s = setSet_vw180s();

        set_w35sDialog = setSet_w35sDialog();
        set_w65sDialog = setSet_w65sDialog();
        set_w180sDialog = setSet_w180sDialog();


    }


    public abstract HashSet<ActToAct> setSet_w35sDialog();

    public abstract HashSet<ActToAct> setSet_w65sDialog();

    protected abstract HashSet<ActToAct> setSet_w180sDialog();

    public abstract HashSet<ActToAct> setSet_vw180s();

    public abstract HashSet<ActToAct> setSet_s35s();

    public abstract HashSet<ActToAct> setSet_s65sDialog();

    public abstract HashSet<ActToAct> setSet_s();

    public abstract HashSet<ActToAct> setSet_return();

    public void onChange(String actBefore, String act) {
        if (timingPanels2 == null) {
            timingPanels2 = AdbTools.getInstance().getTimingPanels2();
        }
        ActToAct actToAct = new ActToAct(actBefore, act);
        //        ActToAct actToAct = new ActToAct(actBefore, act);

        if (set_w35sDialog != null && set_w35sDialog.contains(actToAct)) {
            timingPanels2.w35sDialog();
        } else if (set_w65sDialog != null && set_w65sDialog.contains(actToAct)) {
            timingPanels2.w65sDialog();
        } else if (set_w180sDialog != null && set_w180sDialog.contains(actToAct)) {
            timingPanels2.w180sDialog();
        } else if (set_s65sDialog != null && set_s65sDialog.contains(actToAct)) {
            timingPanels2.w65sDialog();
        } else if (set_s35s != null && set_s35s.contains(actToAct)) {
            timingPanels2.s35s();
        } else if (set_s != null && set_s.contains(actToAct)) {
            timingPanels2.s();
        } else if (set_vw180s != null && set_vw180s.contains(actToAct)) {
            timingPanels2.vw180s();
        } else if (set_return != null && set_return.contains(actToAct)) {
            System.out.println("遇到授权要求，直接返回");
            Device device = AdbTools.getInstance().getDevice();
            AdbCommands.returnBtn(device);
        }
    }


}
