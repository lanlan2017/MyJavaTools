package adbs.action.runnable;

import adbs.action.model.InOutputModel;
import adbs.cmd.AdbCommands;
import adbs.test.DeviceRadioBtAcListener;
import tools.thead.Threads;

import javax.swing.*;

public class WaitReturnButtonRunnable extends CloseableRunnable2 {
    private static WaitReturnButtonRunnable instance = new WaitReturnButtonRunnable();

    private WaitReturnButtonRunnable() {

    }

    public static WaitReturnButtonRunnable getInstance() {
        return instance;
    }

    private InOutputModel inOutputModel;

    public void setInOutputModel(InOutputModel inOutputModel) {
        this.inOutputModel = inOutputModel;
    }

    @Override
    protected void setMsg() {
        msg = "等待后返回线程2";
    }

    @Override
    protected void beforeLoop() {

    }

    @Override
    protected void loopBody() {
        // 获取输入文本框1
        JTextField input1 = inOutputModel.getInputPanelModel().getInput1();
        // 解析输入文本1中的数字,并计算得到毫秒数
        int millisecond = Integer.parseInt(input1.getText()) * 1000;
        // 获取输入标签
        JLabel output = inOutputModel.getOutput();
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
            newOutput = msg + ":等待" + ((millisecond - count) / 1000) + "s";
            if (!newOutput.equals(oldOutput)) {
                output.setText(newOutput);
            }
        }
    }

    @Override
    protected void afterLoop() {
        // 触发返回键
        AdbCommands.returnButton(DeviceRadioBtAcListener.getId());
        inOutputModel.getOutput().setText(msg + ":已停止");
        inOutputModel.getInputPanelModel().showConfirmDialog();
    }
}
