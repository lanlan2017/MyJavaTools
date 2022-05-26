package adbs.action.runnable;

import adbs.action.model.InputOutputModel;
import adbs.cmd.AdbCommands;
import adbs.test.DeviceRadioBtAcListener;
import adbs.ui.AdbTools;
import tools.thead.Threads;

import javax.swing.*;
import java.awt.*;

public class WaitReturnButtonRunnable implements Runnable {
    private static JButton startButton;
    /**
     * 输入输出汇总对象
     */
    private InputOutputModel inputOutputModel;

    private static boolean stop = false;

    // private WaitReturnButtonRunnable() {
    // }

    // private static WaitReturnButtonRunnable instance = new WaitReturnButtonRunnable();
    //
    // public static WaitReturnButtonRunnable getInstance() {
    //     return instance;
    // }
    //
    // public void setInputOutputModel(InputOutputModel inputOutputModel) {
    //     this.inputOutputModel = inputOutputModel;
    // }

    public WaitReturnButtonRunnable(InputOutputModel inputOutputModel) {
        this.inputOutputModel = inputOutputModel;
    }


    public static boolean isStop() {
        return stop;
    }

    public static void setStop(boolean stop) {
        WaitReturnButtonRunnable.stop = stop;
    }

    public void setStartButton(JButton startButton) {
        this.startButton = startButton;
    }

    @Override
    public void run() {
        stop = false;
        // 获取输入文本框1
        JTextField input1 = inputOutputModel.getInputPanelModel().getInput1();
        // 解析输入文本1中的数字,并计算得到毫秒数
        int millisecond = Integer.parseInt(input1.getText()) * 1000;
        // 获取输入标签
        JLabel output = inputOutputModel.getOutput();

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
        showConfirmDialog();
    }

    private void showConfirmDialog() {
        // 得到窗体的内容面板
        Container parent = inputOutputModel.getInputPanelModel().getInputPanel().getParent();
        // System.out.println(parent);
        int returnVal;
        if (parent instanceof Component) {
            System.out.println("是组件");
            Component comp = parent;
            returnVal = JOptionPane.showConfirmDialog(comp, "等待后返回已结束，是否再次执行");
        } else {
            returnVal = JOptionPane.showConfirmDialog(null, "等待后返回已结束，是否再次执行");
        }
        // 如果选择的是确认按键
        if (returnVal == JOptionPane.OK_OPTION) {
            if (startButton != null && startButton instanceof JButton) {
                // 触发按钮
                startButton.doClick();
            }
        }
    }
}
