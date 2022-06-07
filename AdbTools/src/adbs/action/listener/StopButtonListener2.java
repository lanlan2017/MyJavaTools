package adbs.action.listener;

import adbs.action.model.InOutputModel;
import adbs.action.runnable.CloseableRunnable2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Iterator;

public class StopButtonListener2 implements ActionListener {
    private HashSet<Runnable> isRunningSet;
    private InOutputModel inOutputModel;

    public StopButtonListener2(HashSet<Runnable> isRunningSet, InOutputModel inOutputModel) {
        this.isRunningSet = isRunningSet;
        this.inOutputModel = inOutputModel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Iterator<Runnable> iterator = isRunningSet.iterator();
        while (iterator.hasNext()) {
            Runnable next = iterator.next();
            System.out.println(next);
            //如果是可关闭的线程体
            if (next instanceof CloseableRunnable2) {
                CloseableRunnable2 closeableRunnable2 = (CloseableRunnable2) next;
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
