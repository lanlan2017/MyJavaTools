package adbs.action.listener;

import adbs.action.model.InOutputModel;
import adbs.action.runnable.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Iterator;

public class StopButtonListener implements ActionListener {
    private HashSet<Runnable> isRunningSet = new HashSet<>();
    private InOutputModel inOutputModel;

    public StopButtonListener(HashSet<Runnable> isRunningSet, InOutputModel inOutputModel) {
        this.isRunningSet = isRunningSet;
        this.inOutputModel = inOutputModel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("isRunningSet.size() = " + isRunningSet.size());

        Iterator<Runnable> iterator = isRunningSet.iterator();
        while (iterator.hasNext()) {
            Runnable isRunning = iterator.next();

            System.out.println(isRunning);
            // 如果是视频线程正在运行的话
            if (isRunning instanceof BrowseRunnable) {
                // 结束正在执行的线程
                BrowseRunnable.setStop(true);
            } else if (isRunning instanceof ReadButtonRunnable) {
                // 如果当前正在运行的线程是 阅读线程的话，就停止阅读线程
                // 或者当前正在运行的线程是 快手阅读广告监听线程 的话
                ReadButtonRunnable.setStop(true);
            }
            // else if (isRunning instanceof KuaiShouYueDuRunnable) {
            //     // 停止阅读广告监听线程
            //     KuaiShouYueDuRunnable.setStop(true);
            // }
            else if (isRunning instanceof KuaiShouYueDuRunnable) {
                // 停止阅读广告监听线程
                KuaiShouYueDuRunnable.setStop(true);
            } else if (isRunning instanceof ShoppingButtonRunnable) {
                // 如果是逛街线程
                ShoppingButtonRunnable.setStop(true);
            } else if (isRunning instanceof VideoButtonRunnable) {
                // 如果是刷视频线程在运行
                // 关闭刷视频线程
                VideoButtonRunnable.setStop(true);
            } else if (isRunning instanceof WaitReturnButtonRunnable) {
                // 如果是等待后返回线程
                WaitReturnButtonRunnable.setStop(true);
            } else if (isRunning instanceof WuKongGuanBiRunnable) {
                // 如果是等待后返回线程
                WuKongGuanBiRunnable.setStop(true);
            } else if (isRunning instanceof DouYinVideoButtonRunnable) {
                // 结束抖音看视频红包监听线程
                DouYinVideoButtonRunnable.setStop(true);
            } else if (isRunning instanceof PddHongBaoOpenRunnable) {
                PddHongBaoOpenRunnable.setStop(true);
            } else if (isRunning instanceof AiQiYiRunnable) {
                AiQiYiRunnable.setStop(true);
            } else if (isRunning instanceof JinRiTouTiaoRunnable) {
                JinRiTouTiaoRunnable.setStop(true);
            } else if (isRunning instanceof KuaiShouTaskCenterRunnable) {
                KuaiShouTaskCenterRunnable.setStop(true);
            }

            iterator.remove();
        }
        System.out.println("end isRunningSet.size() = " + isRunningSet.size());
        // inputPanel.setVisible(false);
        inOutputModel.getInputPanelModel().getInputPanel().setVisible(false);
    }
}
