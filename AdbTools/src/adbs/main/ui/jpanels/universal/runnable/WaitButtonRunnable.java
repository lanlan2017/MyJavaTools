package adbs.main.ui.jpanels.universal.runnable;

import adbs.main.ui.inout.InOutputModel;
import tools.thead.Threads;

import javax.swing.*;

/**
 * 等待返回 线程体
 */
public class WaitButtonRunnable extends CloseableRunnable {
    private InOutputModel inOutputModel;

    private static WaitButtonRunnable instance = new WaitButtonRunnable();

    private WaitButtonRunnable() {

    }

    public static WaitButtonRunnable getInstance() {
        return instance;
    }

    public void setInOutputModel(InOutputModel inOutputModel) {
        this.inOutputModel = inOutputModel;
    }

    @Override
    protected void setMsg() {
        // msg = "等待后返回线程2";
        msg = "等待:";
    }

    @Override
    protected void loopBody() {
        // 获取输入文本框1
        // JTextField input1 = inOutputModel.getInputPanelModel().getInput1();
        // 测试替换
        JTextField input1 = inOutputModel.getInputPanels().getInput1();
        // 解析输入文本1中的数字,并计算得到毫秒数
        int millisecond = Integer.parseInt(input1.getText()) * 1000;
        // 获取输入标签
        // JLabel output = inOutputModel.getOutput();
        JLabel output = inOutputModel.getInputPanels().getTimerJLabel();
        // AdbTools.setIsRunning(this);
        int count = 0;
        int timeSlice = 250;
        String oldOutput;
        String newOutput;
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
            // 获取
            oldOutput = output.getText();
            // newOutput = msg + ":等待" + ((millisecond - count) / 1000) + "s";
            newOutput = msg + ((millisecond - count) / 1000) + "s";
            if (!newOutput.equals(oldOutput)) {
                output.setText(newOutput);
            }
        }
    }

    @Override
    protected void afterLoop() {
        super.afterLoop();
        // 触发返回键
        // AdbCommands.returnButton(DeviceListener.getPhoneId());
        // inOutputModel.getOutput().setText(msg + ":已停止");
        // inOutputModel.getInputPanels().getTimerJLabel().setText("等待结束");
        inOutputModel.getInputPanels().getTimerJLabel().setText("");

        // inOutputModel.getInputPanelModel().showConfirmDialog();
        // 测试替换
        inOutputModel.getInputPanels().showConfirmDialog();
    }
}
