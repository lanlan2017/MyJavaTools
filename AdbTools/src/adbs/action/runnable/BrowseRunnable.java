package adbs.action.runnable;

import adbs.action.model.InOutputModel;
import adbs.action.runnable.abs.CloseableRunnable;
import adbs.cmd.AdbCommands;
import adbs.test.DeviceRadioBtAcListener;
import tools.thead.Threads;

public class BrowseRunnable extends CloseableRunnable {
    private InOutputModel inOutputModel;

    private static BrowseRunnable instance = new BrowseRunnable();


    private BrowseRunnable() {
    }

    public static BrowseRunnable getInstance() {
        return instance;
    }

    public void setInOutputModel(InOutputModel inOutputModel) {
        this.inOutputModel = inOutputModel;
    }

    @Override
    protected void setMsg() {
        msg = "浏览线程2";
    }

    @Override
    protected void beforeLoop() {
        inOutputModel.getOutput().setText(msg + ":已经开始");
    }

    @Override
    protected void loopBody() {
        String id = DeviceRadioBtAcListener.getId();
        if (id != null) {
            String text = inOutputModel.getInputPanelModel().getInput1().getText();
            System.out.println("text = " + text);
            int s = Integer.parseInt(text) * 1000;
            int count = 0;
            // 如果stop为false，则一直执行。
            inOutputModel.getOutput().setText(msg + ":在手机左侧，从下向上滑动");
            while (!stop) {
                // 休眠1秒
                // output.setText("浏览线程：在手机左侧，从下向上滑动");
                Threads.sleep(1000);
                count += 1000;
                if (count <= s) {
                    AdbCommands.swipeBottom2TopOnLeft(id);
                } else {
                    // 停止线程
                    stop();
                    break;
                }
                inOutputModel.getOutput().setText(msg + ":" + ((s - count) / 1000) + "s");
            }
        }

    }

    @Override
    protected void afterLoop() {
        inOutputModel.getOutput().setText(msg + ":已经结束");
        // 弹出确认框
        inOutputModel.getInputPanelModel().showConfirmDialog();
    }
}
