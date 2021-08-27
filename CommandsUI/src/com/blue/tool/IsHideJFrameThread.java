package com.blue.tool;

import com.blue.ui.MainFrom;

import javax.swing.*;

public class IsHideJFrameThread implements Runnable {
    private MainFrom mainFrom;

    public IsHideJFrameThread(MainFrom mainFrom) {
        this.mainFrom = mainFrom;
    }

    @Override
    public void run() {
        while (true) {
            if (!mainFrom.getFrame().isActive()) {
                // System.out.println("线程检测到，窗体已经不活动，透明化窗体");
                mainFrom.getScrollPane().setVisible(false);
                mainFrom.getFrame().setOpacity(0.5f);
                mainFrom.getFrame().pack();
            }
            try {
                int millis = 500;
                // 线程睡眠
                Thread.sleep(millis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
