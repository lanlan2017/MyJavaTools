package adbs.main.auto.ui.inout.listener;

import adbs.main.auto.ui.inout.InOutputModel;
import adbs.action.runnable.abs.CloseableRunnable;
import adbs.main.auto.run.PythonCloseableRun;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Iterator;

public class StopBtnAcListener2 implements ActionListener {
    private JFrame frame;
    private HashSet<Runnable> isRunningSet;
    private InOutputModel inOutputModel;

    public StopBtnAcListener2(JFrame frame, HashSet<Runnable> isRunningSet, InOutputModel inOutputModel) {
        this.frame = frame;
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
                System.out.println(python_CloseableRun + " is stop now");
                // 关闭线程
                python_CloseableRun.stop();
                // 从线程池中删除掉
                iterator.remove();
            } else if (runnable instanceof CloseableRunnable) {
                CloseableRunnable closeableRunnable = (CloseableRunnable) runnable;
                System.out.println(closeableRunnable + " is stop now");
                // 关闭线程
                closeableRunnable.stop();
                // 从线程池中删除掉
                iterator.remove();
            }
        }
        System.out.println("end isRunningSet.size() = " + isRunningSet.size());


        // inOutputModel.getInputPanelModel().getInputPanel().setVisible(false);
        
        inOutputModel.getInputPanels().getInputPanel().setVisible(false);
        frame.pack();
    }
}
