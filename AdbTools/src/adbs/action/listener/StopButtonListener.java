package adbs.action.listener;

import adbs.action.model.InOutputModel;
import adbs.action.runnable.CloseableRunnable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Iterator;

public class StopButtonListener implements ActionListener {
    private HashSet<Runnable> isRunningSet;
    private InOutputModel inOutputModel;

    public StopButtonListener(HashSet<Runnable> isRunningSet, InOutputModel inOutputModel) {
        this.isRunningSet = isRunningSet;
        this.inOutputModel = inOutputModel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Iterator<Runnable> iterator = isRunningSet.iterator();
        while (iterator.hasNext()) {
            Runnable runnable = iterator.next();
            // System.out.println(runnable);
            //如果是可关闭的线程体
            if (runnable instanceof CloseableRunnable) {
                CloseableRunnable closeableRunnable2 = (CloseableRunnable) runnable;
                System.out.println(closeableRunnable2 + " is stop now");
                // 关闭线程
                closeableRunnable2.stop();
                // 从线程池中删除掉
                iterator.remove();
            }
        }
        // DouYinTaskRunnable2.getInstance().stop();
        System.out.println("end isRunningSet.size() = " + isRunningSet.size());
        // inputPanel.setVisible(false);
        inOutputModel.getInputPanelModel().getInputPanel().setVisible(false);
    }
}
