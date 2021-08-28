package com.blue.tool;

import com.blue.ui.MainFrom;

import javax.swing.*;

public class IsHideJFrameThread implements Runnable {
    private MainFrom mainFrom;
    public static boolean isSetOpacity = true;
    public static boolean runing = true;
    public static boolean isPack=true;

    public IsHideJFrameThread(MainFrom mainFrom) {
        this.mainFrom = mainFrom;
    }

    @Override
    public void run() {
        while (runing) {
            if (isSetOpacity && !mainFrom.getFrame().isActive()) {
                System.out.println("调整");
                // System.out.println("线程检测到，窗体已经不活动，透明化窗体");
                mainFrom.getScrollPane().setVisible(false);
                mainFrom.getFrame().setOpacity(0.5f);
                if(isPack){
                    // 自动调整大小
                    mainFrom.getFrame().pack();
                }
            }else {
                System.out.println("不调整");
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
