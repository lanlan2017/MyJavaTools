package adbs.action.runnable;

import adbs.cmd.AdbCommands;
import adbs.test.DeviceRadioBtAcListener;
import adbs.ui.AdbTools;
import tools.thead.Threads;

import javax.swing.*;

public class WaitReturnButtonRunnable implements Runnable {
    private static JButton startButton;
    private JTextField input;
    private JLabel output;
    private int millisecond;
    private static boolean stop = false;


    public WaitReturnButtonRunnable(JTextField input, JLabel output) {
        this.input = input;
        this.output = output;
    }


    public static boolean isStop() {
        return stop;
    }

    public static void setStop(boolean stop) {
        WaitReturnButtonRunnable.stop = stop;
    }

    public JButton getStartButton() {
        return startButton;
    }

    public void setStartButton(JButton startButton) {
        this.startButton = startButton;
    }

    @Override
    public void run() {
        stop = false;
        millisecond = Integer.parseInt(input.getText()) * 1000;
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

    private static void showConfirmDialog() {
        int returnVal = JOptionPane.showConfirmDialog(null, "等待后返回已结束，是否再次执行");
        // 如果选择的是确认按键
        if (returnVal == JOptionPane.OK_OPTION) {
            if (startButton != null && startButton instanceof JButton) {
                startButton.doClick();
            }
        }
    }
}
