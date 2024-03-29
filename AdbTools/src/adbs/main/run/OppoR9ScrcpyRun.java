package adbs.main.run;

import adbs.main.AdbTools;
import adbs.main.ui.jframe.JFramePack;
import adbs.main.ui.jpanels.scrcpy.ScrcpyJPanels;
import adbs.main.ui.jpanels.universal.runnable.CloseableRunnable;
import adbs.tools.thread.ThreadSleep;

import javax.swing.*;

// public class OppoR9ScrcpyRun extends CloseableRunnable {
public class OppoR9ScrcpyRun implements Runnable {
    private static OppoR9ScrcpyRun instance = new OppoR9ScrcpyRun();

    public static OppoR9ScrcpyRun getInstance() {
        return instance;
    }

    boolean stop;

    private OppoR9ScrcpyRun() {
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    private ScrcpyJPanels scrcpyJPanels;

    // @Override
    protected void beforeLoop() {
        // super.beforeLoop();
        // 获取 投屏面板
        AdbTools instance = AdbTools.getInstance();
        scrcpyJPanels = instance.getScrcpyJPanels();
    }

    // @Override
    protected void loopBody() {
        if (IsTest.isTest()) {
            // 测试时，每隔10秒检测一次
            ThreadSleep.seconds(10);
        } else {
            // 等待35分钟
            ThreadSleep.minutes(35);
        }
        JPanel scrcpyJPanel = scrcpyJPanels.getScrcpyJPanel();
        // 如果投屏面板可见的话
        if (scrcpyJPanel.isVisible()) {
            // 点击直接重新打开scrcpy.exe
            reopenScrcpy();
        } else {
            // 先
            scrcpyJPanel.setVisible(true);
            // JFramePack.onJComponentActionEvent();
            // 杀死scrcpy.exe投屏
            // JFramePack.byJPanel(scrcpyJPanel);
            JFramePack.byJPanel(scrcpyJPanel, 1000);
            reopenScrcpy();
            // JFramePack.byJPanel(scrcpyJPanel);
            JFramePack.byJPanel(scrcpyJPanel, 1000);
            scrcpyJPanel.setVisible(false);
        }
    }

    /**
     * 重新打开scrcpy
     */
    private void reopenScrcpy() {
        // 杀死scrcpy.exe投屏
        scrcpyJPanels.getBtnKillScrcpy().doClick();
        ThreadSleep.seconds(3);
        // 重新开始scrcpy.exe投屏
        scrcpyJPanels.getBtnOpenScrcpy().doClick();
    }

    @Override
    public void run() {
        beforeLoop();
        while (stop) {
            loopBody();
        }
    }
}
