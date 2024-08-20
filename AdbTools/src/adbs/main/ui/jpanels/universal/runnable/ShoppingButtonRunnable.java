package adbs.main.ui.jpanels.universal.runnable;

import adbs.cmd.AdbCommands;
import adbs.main.AdbTools;
import adbs.main.run.AdbGetPackage;
import adbs.main.ui.jpanels.time.TimePanels;
import adbs.main.ui.jpanels.universal.UniversalPanels;
import tools.thead.Threads;

import javax.swing.*;
import java.awt.*;

/**
 * 逛街线程体
 */
public class ShoppingButtonRunnable extends CloseableRunnable {
    /**
     * 是否触发任务键
     */
    private boolean isClickTaskBtn;

    private static final ShoppingButtonRunnable instance = new ShoppingButtonRunnable();
    private int seconds;
    private int count;
    private String oldInput1Text;
    private Color input1Background;

    private ShoppingButtonRunnable() {
    }

    public static ShoppingButtonRunnable getInstance() {
        return instance;
    }

    public void setClickTaskBtn(boolean clickTaskBtn) {
        isClickTaskBtn = clickTaskBtn;
    }


    @Override
    protected void beforeLoop() {
        super.beforeLoop();
        TimePanels timePanels = AdbTools.getInstance().getTimePanels();
        if (timePanels.getTimerJLabel().isVisible()) {
            JTextField input1 = timePanels.getInput1();
            // 保存原来设定的值
            oldInput1Text = input1.getText();
            //            input1.setEditable(false);
            input1Background = input1.getBackground();

            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    input1.setBackground(Color.PINK);
                }
            });

        }
    }

    @Override
    protected void loopBody() {
        // String serial = AdbTools.device.getId();
        AdbTools adbTools = AdbTools.getInstance();
        String serial = adbTools.getDevice().getSerial();

        // UniversalPanels universalPanels = inOutputModel.getUniversalPanels();
        UniversalPanels universalPanels = adbTools.getUniversalPanels();
        JLabel output = universalPanels.getOutput2();
        // TimePanels timePanels = inOutputModel.getTimePanels();
        TimePanels timePanels = adbTools.getTimePanels();

        JTextField input1 = timePanels.getInput1();

        // 读取文本框1中的秒数
        seconds = Integer.parseInt(input1.getText()) * 1000;
        // 计数器
        count = 0;

        // 向上滑动三次
        output.setText("↑3");
        for (int i = 0; i < 3; i++) {
            // 在左侧，从下向上滑动三次
            if (swipeFromBottomToTopOnce(serial, input1)) {
                // 如果到达了指定时间，
                stop = true;
                return;
            }
        }

        while (!stop) {
            output.setText("↓");
            // 休眠1秒
            if (swipeFromBottomToTopOnce(serial, input1)) {
                System.out.println("到达指定时间1!");
                stop = true;
                break;
            }
            output.setText("↑");
            if (swipeFromTopToBottomOnce(serial, input1)) {
                System.out.println("到达指定时间2!");
                stop = true;
                break;
            }
        }
    }

    /**
     * 从下往上滑动一次
     *
     * @param id     手机的id
     * @param input1 输入文本1
     * @return 计数器
     */
    private boolean swipeFromBottomToTopOnce(String id, JTextField input1) {
        // 等待1秒
        Threads.sleep(1000);
        // 计数器+1秒
        count += 1000;
        // 计算剩余时间
        int diff = (seconds - count) / 1000;
        if (diff >= 0) {
            // 从下向上滑动
            AdbCommands.swipeBottom2TopOnLeft(id);
            // 更新输入文本框
            input1.setText(String.valueOf(diff));
            // 返回false表示还没达到指定时间
            return false;
        }
        // 返回true表示已经达到指定时间了
        return true;
    }

    /**
     * 从下往上滑动一次
     *
     * @param id     手机的id
     * @param input1 输入文本1
     * @return 计数器
     */
    private boolean swipeFromTopToBottomOnce(String id, JTextField input1) {
        Threads.sleep(1000);
        count += 1000;
        int shengYu = (seconds - count) / 1000;
        if (shengYu >= 0) {
            AdbCommands.swipeTop2BottomOnLeft(id);
            input1.setText(String.valueOf(shengYu));
            return false;
        }
        return true;
    }

    @Override
    protected void afterLoop() {
        super.afterLoop();

        // TimePanels timePanels = inOutputModel.getTimePanels();
        TimePanels timePanels = AdbTools.getInstance().getTimePanels();

        timePanels.getTimerJLabel().setText("");
        // if (isClickReturnBtn) {
        //     // AdbTools.getInstance().getAdbJPanels().getReturnBtn().doClick();
        //     AdbTools.getInstance().getAdbJPanels().getBtnReturn().doClick();
        //     // AdbTools.getInstance().getAdbJPanels().getBtnTask().doClick();
        //     isClickReturnBtn = false;
        // }
        String packageName = AdbGetPackage.getActivityInfo().getPackageName();

        switch (packageName) {
            case "com.taobao.live":
            case "com.taobao.taobao":
            case "com.taobao.litetao":
                System.out.println("点淘APP,结束等待后 返回");
                AdbTools.getInstance().getAdbJPanels().getBtnReturn().doClick();
                break;
            default:
                if (isClickTaskBtn) {
                    // AdbTools.getInstance().getAdbJPanels().getReturnBtn().doClick();
                    // AdbTools.getInstance().getAdbJPanels().getBtnReturn().doClick();
                    AdbTools.getInstance().getAdbJPanels().getBtnTask().doClick();
                    isClickTaskBtn = false;
                }
                break;
        }


        if (timePanels.getTimerJLabel().isVisible()) {
            JTextField input1 = timePanels.getInput1();
            // 恢复原来的值
            input1.setText(oldInput1Text);
            // 可以重新编辑
            //            input1.setEditable(true);
            // 恢复原来的颜色

            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    input1.setBackground(input1Background);
                }
            });

        }
        timePanels.beepDialog("逛街结束");

    }
}
