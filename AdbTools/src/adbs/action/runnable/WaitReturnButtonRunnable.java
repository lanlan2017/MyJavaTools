package adbs.action.runnable;

import adbs.action.model.InOutputModel;
import adbs.cmd.AdbCommands;
import adbs.test.DeviceRadioBtAcListener;
import adbs.ui.AdbTools;
import tools.thead.Threads;

import javax.swing.*;
import java.awt.*;

public class WaitReturnButtonRunnable implements Runnable {
    // private static JButton startButton;
    /**
     * 输入输出汇总对象
     */
    private InOutputModel inOutputModel;

    private static boolean stop = false;

    public WaitReturnButtonRunnable(InOutputModel inOutputModel) {
        this.inOutputModel = inOutputModel;
    }


    public static boolean isStop() {
        return stop;
    }

    public static void setStop(boolean stop) {
        WaitReturnButtonRunnable.stop = stop;
    }

    // public void setStartButton(JButton startButton) {
    //     this.startButton = startButton;
    // }

    @Override
    public void run() {
        stop = false;
        // 获取输入文本框1
        JTextField input1 = inOutputModel.getInputPanelModel().getInput1();
        // 解析输入文本1中的数字,并计算得到毫秒数
        int millisecond = Integer.parseInt(input1.getText()) * 1000;
        // 获取输入标签
        JLabel output = inOutputModel.getOutput();

        AdbTools.setIsRunning(this);
        int count = 0;
        int timeSlice = 250;
        String oldOutput;
        String newOutput;
        while (!isStop()) {
            // 等待指定时间
            Threads.sleep(timeSlice);
            // 统计已经等待的时间
            count += timeSlice;
            // 如果等待指定时间
            if (count >= millisecond) {
                break;
            }
            // 获取
            oldOutput = output.getText();
            newOutput = "等待后返回：等待" + ((millisecond - count) / 1000) + "s";
            if (!newOutput.equals(oldOutput)) {
                output.setText(newOutput);
            }
        }
        // 触发返回键
        AdbCommands.returnButton(DeviceRadioBtAcListener.getId());
        output.setText("等待后返回：已停止");
        inOutputModel.getInputPanelModel().showConfirmDialog();
    }
}
