package adbs.main.run;

import adbs.main.AdbTools;
import adbs.main.ui.jpanels.control.ControlJPanels;
import adbs.main.ui.jpanels.scrcpy.ScrcpyJPanels;
import adbs.main.ui.jpanels.universal.runnable.CloseableRunnable;
import adbs.tools.thread.ThreadSleep;

public class OppoR9ScrcpyRun extends CloseableRunnable {

    private ScrcpyJPanels scrcpyJPanels;
    // private ControlJPanels controlJPanels;

    @Override
    protected void setMsg() {
        msg = "OppoR9投屏刷新线程";
    }

    @Override
    protected void beforeLoop() {
        super.beforeLoop();
        // 获取 投屏面板
        AdbTools instance = AdbTools.getInstance();
        scrcpyJPanels = instance.getScrcpyJPanels();
        // controlJPanels = instance.getControlJPanels();


        // System.out.println("OppoR9投屏刷新线程");

    }

    @Override
    protected void loopBody() {
        // 测试
        // ThreadSleep.seconds(10);
        // 等待40分钟
        ThreadSleep.minutes(40);
        // 杀死scrcpy.exe投屏
        scrcpyJPanels.getKillScrcpyBtn().doClick();
        ThreadSleep.seconds(3);
        // 重新开始scrcpy.exe投屏
        scrcpyJPanels.getOpenScrcpyBtn().doClick();
    }
}
