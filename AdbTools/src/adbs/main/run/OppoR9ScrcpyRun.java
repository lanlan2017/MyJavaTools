package adbs.main.run;

import adbs.main.AdbTools;
import adbs.main.ui.jframe.JFramePack;
import adbs.main.ui.jpanels.scrcpy.ScrcpyJPanels;
import adbs.tools.thread.ThreadSleep;

import javax.swing.*;

/**
 * 大概30分钟之后重启scrcpy.exe
 */
public class OppoR9ScrcpyRun implements Runnable {
    private static final OppoR9ScrcpyRun instance = new OppoR9ScrcpyRun();

    public static OppoR9ScrcpyRun getInstance() {
        return instance;
    }

    private volatile boolean stop = false;

    private OppoR9ScrcpyRun() {
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    private ScrcpyJPanels scrcpyJPanels;

    // @Override
    protected void beforeLoop() {
        // System.out.println("before");
        // super.beforeLoop();
        // 获取 投屏面板
        AdbTools instance = AdbTools.getInstance();
        scrcpyJPanels = instance.getScrcpyJPanels();
    }

    // @Override
    protected void loopBody() {
        // System.out.println("OPPO检车");
        if (IsTest.isTest()) {
            // System.out.println("OPPO检车 等待10秒");
            // 测试时，每隔10秒检测一次
            ThreadSleep.seconds(10);
        } else {
            System.out.println("OPPO检车 等待35秒");
            // 等待35分钟
            ThreadSleep.minutes(32);
            // ThreadSleep.minutes(1);
            // ThreadSleep.minutes(2);
            // ThreadSleep.minutes(5);
            // ThreadSleep.seconds(10);
        }
        JPanel scrcpyJPanel = scrcpyJPanels.getScrcpyJPanel();
        // 如果投屏面板可见的话
        if (scrcpyJPanel.isVisible()) {
            // System.out.println("重新打开OPPO 1");
            // 点击直接重新打开scrcpy.exe
            reopenScrcpy();
        } else {
            // System.out.println("重新打开OPPO 2");
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
        while (!stop) {
            loopBody();
        }
    }
}
