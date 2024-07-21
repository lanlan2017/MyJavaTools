package adbs.main.ui.jpanels.universal.runnable;

import adbs.cmd.AdbCommands;
import adbs.main.AdbTools;
import adbs.main.ui.jpanels.universal.UniversalPanels;
import tools.random.Randoms;
import tools.thead.Threads;

import javax.swing.*;

/**
 * 阅读线程体
 */
public class ReadButtonRunnable extends CloseableRunnable {
    private static final ReadButtonRunnable instance = new ReadButtonRunnable();
    private int min;
    private int max;
    private ReadButtonRunnable() {
        min = 5;
        max = 9;
    }

    public static ReadButtonRunnable getInstance() {
        return instance;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setMax(int max) {
        this.max = max;
    }


    @Override
    protected void beforeLoop() {
        super.beforeLoop();
        // 先等待1秒
        Threads.sleep(500);
    }

    @Override
    protected void loopBody() {
        body();
    }

    private void body() {
        String serial = AdbTools.getInstance().getDevice().getSerial();
        // 获取选中的adb设备的屏幕宽度
        int width = AdbTools.getInstance().getDevice().getWidth();
        // 获取选中的adb设备的屏幕高度
        int height = AdbTools.getInstance().getDevice().getHeight();

        if (serial == null) {
            // 如果没有选择设备
            JOptionPane.showConfirmDialog(null, "请勾选要操作的设备");
            // return;
            stop = true;
        } else {
            // 如果选择了设备
            // 点击屏幕右侧
            String adbResult = AdbCommands.clickScreenRightSide(serial, width, height);
            // System.out.println("    " + adbResult);
            if (AdbCommands.ifDeviceNotExist(adbResult))
                return;
            //    在这里加上代码控制
        }
        // 生成[min,Max]区间的随机整数
        // int s = random.nextInt(max) % (max - min + 1) + min;
        int s = Randoms.getRandomInt(min, max);

        // output.setText(msg + "等待" + s + "秒");
        // output2.setText(msg + "等待" + s + "s");

        UniversalPanels universalPanels = AdbTools.getInstance().getUniversalPanels();
        // universalPanels = inOutputModel.getUniversalPanels();

        JLabel output2 = universalPanels.getOutput2();
        // output2.setText(s + "s:" + msg);
        int finalS = s;
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                output2.setText(finalS + "s:" + msg);
            }
        });

        // output2.setText(s + "s后" + msg);
        String oldText;
        String newText;
        s = s * 1000;
        int count = 0;
        while (count < s) {
            // 如果已经收到结束的通知
            if (stop) {
                // 不再等待
                break;
            }
            // 等待一小段时间
            Threads.sleep(250);
            // oldText = output.getText();
            oldText = output2.getText();
            // newText = msg + "等待" + (s - count) / 1000 + "s";
            // newText = (s - count) / 1000 + "s后" + msg;
            newText = (s - count) / 1000 + "s:" + msg;
            if (!oldText.equals(newText)) {
                // output.setText(newText);
                // output2.setText(newText);
                // output2.setText(newText);
                String finalNewText = newText;
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        output2.setText(finalNewText);
                    }
                });
            }
            count += 250;
        }
    }
}
