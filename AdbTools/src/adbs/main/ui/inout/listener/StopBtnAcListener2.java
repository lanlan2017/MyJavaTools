package adbs.main.ui.inout.listener;

import adbs.main.ui.inout.InOutputModel;
import adbs.main.ui.jframe.JFramePack;
import adbs.main.ui.jpanels.universal.runnable.CloseableRunnable;
import adbs.main.run.PythonCloseableRun;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Iterator;

public class StopBtnAcListener2 implements ActionListener {
    private HashSet<Runnable> isRunningSet;
    private InOutputModel inOutputModel;

    public StopBtnAcListener2(HashSet<Runnable> isRunningSet, InOutputModel inOutputModel) {
        this.isRunningSet = isRunningSet;
        this.inOutputModel = inOutputModel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Iterator<Runnable> iterator = isRunningSet.iterator();
        while (iterator.hasNext()) {
            Runnable runnable = iterator.next();
            //如果是可关闭的线程体
            if (runnable instanceof PythonCloseableRun) {
                PythonCloseableRun python_CloseableRun = (PythonCloseableRun) runnable;
                // 关闭线程
                python_CloseableRun.stop();
                // 从线程池中删除掉
                iterator.remove();
            } else if (runnable instanceof CloseableRunnable) {
                CloseableRunnable closeableRunnable = (CloseableRunnable) runnable;
                // System.out.println(closeableRunnable + " is stop now");
                // 关闭线程
                closeableRunnable.stop();
                // 从线程池中删除掉
                iterator.remove();
            }
        }
        // 隐藏时间面板
        inOutputModel.getTimePanels().getTimePanel().setVisible(false);
        inOutputModel.getUniversalPanels().getOutput2().setText("");
        inOutputModel.getTimePanels().getTimerJLabel().setText("");
        // 更新JFrame界面
        JFramePack.onJComponentActionEvent(e);
    }
}
