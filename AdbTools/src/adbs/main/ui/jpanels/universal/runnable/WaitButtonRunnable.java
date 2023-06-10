package adbs.main.ui.jpanels.universal.runnable;

import adbs.main.AdbTools;
import adbs.main.ui.inout.InOutputModel;
import adbs.main.ui.jpanels.adb.AdbJPanels;
import adbs.main.ui.jpanels.time.TimePanels;
import tools.thead.Threads;

import javax.swing.*;

/**
 * 等待 线程体
 */
public class WaitButtonRunnable extends CloseableRunnable {
    /**
     * 是否在等待结束之后触发 任务键
     */
    private boolean isClickTaskButton;
    /**
     * 是否在等待结束之后 点击 停止按钮
     */
    private boolean isClickStopButton;
    // private InOutputModel inOutputModel;

    private static WaitButtonRunnable instance = new WaitButtonRunnable();

    private WaitButtonRunnable() {

    }

    public static WaitButtonRunnable getInstance() {
        return instance;
    }

    public void setClickTaskButton(boolean clickTaskButton) {
        isClickTaskButton = clickTaskButton;
    }

    public void setClickStopButton(boolean clickStopButton) {
        isClickStopButton = clickStopButton;
    }

    // public void setInOutputModel(InOutputModel inOutputModel) {
    //     this.inOutputModel = inOutputModel;
    // }

    @Override
    protected void setMsg() {
        // msg = "等待后返回线程2";
        // msg = "等待:";
        msg = "";
    }

    @Override
    protected void loopBody() {
        // 获取输入文本框1
        // JTextField input1 = inOutputModel.getInputPanelModel().getInput1();
        // 测试替换
        // TimePanels timePanels = inOutputModel.getTimePanels();

        TimePanels timePanels = AdbTools.getInstance().getTimePanels();

        JTextField input1 = timePanels.getInput1();
        // 解析输入文本1中的数字,并计算得到毫秒数
        String input1Text = input1.getText();
        int millisecond = Integer.parseInt(input1Text) * 1000;
        // 获取输入标签
        // // JLabel timerJLabel = inOutputModel.getOutput();
        // JLabel timerJLabel = timePanels.getTimerJLabel();

        // AdbTools.setIsRunning(this);
        int count = 0;
        int timeSlice = 250;
        // String oldOutput;
        // String newOutput;
        while (!stop) {
            // 等待指定时间
            Threads.sleep(timeSlice);
            // 统计已经等待的时间
            count += timeSlice;
            // 如果等待指定时间
            if (count >= millisecond) {
                stop = true;
                break;
            }
            // // 获取
            // oldOutput = timerJLabel.getText();
            // newOutput = msg + ":等待" + ((millisecond - count) / 1000) + "s";




            int waitingSeconds = (millisecond - count) / 1000;
            input1.setText(String.valueOf(waitingSeconds));
            if (input1.isEditable()) {
                input1.setEditable(false);
            }


            // newOutput = msg + waitingSeconds + "s";
            // if (!newOutput.equals(oldOutput)) {
            //     timerJLabel.setText(newOutput);
            // }

        }
        // 恢复原来的设置
        input1.setText(input1Text);
        input1.setEditable(true);

    }

    @Override
    protected void afterLoop() {
        super.afterLoop();
        // 触发返回键
        // AdbCommands.returnButton(DeviceListener.getPhoneId());
        // inOutputModel.getOutput().setText(msg + ":已停止");
        // inOutputModel.getTimePanels().getTimerJLabel().setText("等待结束");

        // JLabel timerJLabel = inOutputModel.getTimePanels().getTimerJLabel();

        TimePanels timePanels = AdbTools.getInstance().getTimePanels();
        JLabel timerJLabel = timePanels.getTimerJLabel();


        timerJLabel.setText("");
        AdbJPanels adbJPanels = AdbTools.getInstance().getAdbJPanels();
        if (isClickTaskButton) {
            adbJPanels.getTaskBtn().doClick();
            isClickTaskButton = false;
        }
        if (isClickStopButton) {
            adbJPanels.getStopBtn().doClick();
            isClickStopButton = false;
        }
        // inOutputModel.getInputPanelModel().showConfirmDialog();
        // 测试替换
        // inOutputModel.getTimePanels().showConfirmDialog();
        timePanels.showConfirmDialog();

    }
}
