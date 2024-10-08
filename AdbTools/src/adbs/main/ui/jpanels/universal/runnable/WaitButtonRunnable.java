package adbs.main.ui.jpanels.universal.runnable;

import adbs.main.AdbTools;
import adbs.main.run.AdbGetPackage;
import adbs.main.run.act.model.ActivityInfo;
import adbs.main.ui.jpanels.adb.AdbJPanels;
import adbs.main.ui.jpanels.time.TimePanels;
import adbs.main.ui.jpanels.universal.UniversalPanels;
import tools.thead.Threads;

import javax.swing.*;
import java.awt.*;

/**
 * 等待线程体
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

    private static final WaitButtonRunnable instance = new WaitButtonRunnable();
    private TimePanels timePanels;
    private JTextField input1;
    private String input1OldText;
    private Color input1Background;

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

    @Override
    protected void before() {
        super.before();
        timePanels = AdbTools.getInstance().getTimePanels();
        input1 = timePanels.getInput1();
        // 解析输入文本1中的数字,并计算得到毫秒数
        input1OldText = input1.getText();
        // 保存最开始的背景色，后面不要重复保存
        if (input1Background == null) {
            input1Background = input1.getBackground();
        }

        // 设置文本框背景颜色为品红(MAGENTA)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                input1.setBackground(Color.PINK);
            }
        });

    }

    @Override
    protected void loop() {
        int millisecond = Integer.parseInt(input1OldText) * 1000;
        int count = 0;
        int timeSlice = 250;
        while (!stopLoopBody) {
            // 等待指定时间
            Threads.sleep(timeSlice);
            // 统计已经等待的时间
            count += timeSlice;
            // 如果等待指定时间
            if (count >= millisecond) {
                stopLoopBody = true;
                break;
            }
            //是有整数秒的时候才更新
            if (count % 1000 == 0) {
                int waitingSeconds = (millisecond - count) / 1000;
                input1.setText(String.valueOf(waitingSeconds));
            }
        }
    }

    @Override
    protected void cleanOutput() {
        //        super.cleanOutput();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // 恢复原来的设置
                input1.setText(input1OldText);
                input1.setBackground(input1Background);
                JLabel timerJLabel = timePanels.getTimerJLabel();
                timerJLabel.setText("");
                AdbTools.getInstance().getUniversalPanels().getOutput2().setText("");
            }
        });
    }

    @Override
    protected void after() {
        super.after();
        //        SwingUtilities.invokeLater(new Runnable() {
        //            @Override
        //            public void run() {
        //                // 恢复原来的设置
        //                input1.setText(input1OldText);
        //                input1.setBackground(input1Background);
        //                JLabel timerJLabel = timePanels.getTimerJLabel();
        //                timerJLabel.setText("");
        //            }
        //        });

        AdbTools adbTools = AdbTools.getInstance();
        AdbJPanels adbJPanels = adbTools.getAdbJPanels();
        UniversalPanels universalPanels = adbTools.getUniversalPanels();
        if (isClickStopButton) {
            // adbJPanels.getStopBtn().doClick();
            universalPanels.getBtnStop().doClick();
            isClickStopButton = false;
        }

        ActivityInfo activityInfo = AdbGetPackage.getActivityInfo();
        String packageName = activityInfo.getPackageName();

        switch (packageName) {
            case "com.taobao.live":
            case "com.taobao.taobao":
            case "com.taobao.litetao":
                // 点淘APP,停止后返回
                System.out.println("点淘APP,结束等待后 返回");
                adbJPanels.getBtnReturn().doClick();
                break;
            default:
                if (isClickTaskButton) {
                    adbJPanels.getBtnTask().doClick();
                    isClickTaskButton = false;
                }
                break;
        }
        //        timePanels.showConfirmDialog();
//        timePanels.beepDialog("等待结束");
        AdbTools.getInstance().beepDialog("等待结束");
    }
}
