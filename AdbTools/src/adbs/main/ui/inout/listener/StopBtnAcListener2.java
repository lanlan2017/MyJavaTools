package adbs.main.ui.inout.listener;

import adbs.main.AdbTools;
import adbs.main.ui.jframe.JFramePack;
import adbs.main.ui.jpanels.universal.runnable.CloseableRunnable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

public class StopBtnAcListener2 implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        Iterator<Runnable> iterator = AdbTools.getInstance().getIsRunningSet().iterator();
        // Iterator<Runnable> iterator = isRunningSet.iterator();
        while (iterator.hasNext()) {
            Runnable runnable = iterator.next();
            if (runnable instanceof CloseableRunnable) {
                CloseableRunnable closeableRunnable = (CloseableRunnable) runnable;
                // 关闭线程
                closeableRunnable.stop();
                // 从线程池中删除掉
                iterator.remove();
            }
        }

        // // 时间面板的标签文字设为空字符串
        // AdbTools.getInstance().getTimePanels().getTimerJLabel().setText("");
        // // 隐藏时间面板
        // AdbTools.getInstance().getTimePanels().getTimePanel().setVisible(false);
        // // 通用面板的标签文字设置为空字符串
        // AdbTools.getInstance().getUniversalPanels().getOutput2().setText("");

        // JLable线程不安全，在事件调度线程中执行，以确保线程安全
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // 时间面板的标签文字设为空字符串
                AdbTools.getInstance().getTimePanels().getTimerJLabel().setText("");
                // 隐藏时间面板
                AdbTools.getInstance().getTimePanels().getTimePanel().setVisible(false);
                // 通用面板的标签文字设置为空字符串
                AdbTools.getInstance().getUniversalPanels().getOutput2().setText("");

            }
        });

        // 更新JFrame界面
        JFramePack.onJComponentActionEvent(e);
    }
}
