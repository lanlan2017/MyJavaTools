package com.blue.tool;

import com.blue.ui.MainFrom;

public class ThreadAutoSetFrameOpacity implements Runnable {
    private final MainFrom mainFrom;
    public static boolean isSetOpacity = true;
    public static boolean runing = true;

    public ThreadAutoSetFrameOpacity(MainFrom mainFrom) {
        this.mainFrom = mainFrom;
    }

    @Override
    public void run() {
        // 当没有关闭线程的时候
        while (runing) {
            // 当要求设置透明度的时候
            if (isSetOpacity) {
                // 当窗口不活动的时候
                if(!mainFrom.getFrame().isActive()){
                    // System.out.println("调整");
                    // 隐藏输出面板
                    // mainFrom.getScrollPane().setVisible(false);
                    mainFrom.getScrollPaneFather().setVisible(false);
                    // 并设置半透明
                    mainFrom.getFrame().setOpacity(0.5f);
                    // 调整大小
                    mainFrom.getFrame().pack();
                }
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
