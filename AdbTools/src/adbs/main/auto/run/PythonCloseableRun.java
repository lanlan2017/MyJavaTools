package adbs.main.auto.run;

import adbs.action.runnable.abs.CloseableRunnable;
import adbs.cmd.*;
import adbs.main.auto.listener.DeviceListener;
import adbs.python.PythonGenerator;
import adbs.main.AdbTools;
import tools.file.Files;
import tools.format.date.DateFormatters;
import tools.thead.Threads;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Date;

/**
 * python线程体
 */
public class PythonCloseableRun implements Runnable {
    /**
     * 线程消息
     */
    protected String msg;
    private String pyPath;
    private JLabel output;
    private CloseableRunnable closeableRun;
    private JButton afterBtn;
    /**
     * 是否结束线程
     */
    protected boolean stop = false;
    private int waitSeconds = 35;

    public PythonCloseableRun(String msg, String pyPath, JLabel output) {
        this.msg = msg;
        this.pyPath = pyPath;
        this.output = output;
    }

    public PythonCloseableRun(String msg, String pyPath, JLabel output, CloseableRunnable closeableRun, JButton afterBtn) {
        this.msg = msg;
        this.pyPath = pyPath;
        this.output = output;
        this.closeableRun = closeableRun;
        this.afterBtn = afterBtn;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public void stop() {
        this.stop = true;
        // 获取与python文件同名的txt文件
        String pyOutputPath = pyPath.replace(".py", ".txt");
        File file = new File(pyOutputPath);
        // 如果文件存在
        if (file.exists()) {
            // 读取文件内容
            String yueDuPidStr = Files.readFile(file);
            // 如果文件内容都是数字
            if (yueDuPidStr != null && yueDuPidStr.matches("\\d+")) {
                // 输出内容
                System.out.println("杀死pid=" + yueDuPidStr + "的进程（python）");
                CmdRun.run("taskkill -f -pid " + yueDuPidStr);
            }
        }
    }

    @Override
    public void run() {
        // 默认循环不停止
        stop = false;
        beforeLoop();
        AdbTools.getInstance().setIsRunning(this);
        while (!stop) {
            loopBody();
        }
        afterLoop();
    }

    /**
     * 循环之前
     */
    private void beforeLoop() {
        // 更新Python文件
        updatePythonFile();
        output.setText(msg + ":开始");
    }

    /**
     * 更新要运行的python文件
     */
    private void updatePythonFile() {
        // 调用子类的方法
        if (pyPath != null && !"".equals(pyPath)) {
            System.out.println("更新要运行的Python文件：" + pyPath);
            // 自动生成Python文件
            PythonGenerator.updatePythonFile(pyPath);
        }
    }

    /**
     * 循环体
     */
    private void loopBody() {
        runPython();
    }

    /**
     * 运行Python
     */
    private void runPython() {
        // 运行python进程，获取进程的标准输出
        String pyOutput = PythonRun.runPython(pyPath);
        // System.out.println("pyOutput = " + pyOutput);
        // 如果python输出中有.png的话
        if (pyOutput.contains(".png")) {
            // 处理带图片的输出
            haveFoundPictures(pyOutput);
        }
    }

    /**
     * 线程循环结束后要做的
     */
    private void afterLoop() {
        output.setText(msg + ":结束");
    }


    /**
     * 如果python找到特定的图片
     *
     * @param pyOutput python输出（带有图片的输出）
     */
    private void haveFoundPictures(String pyOutput) {
        // 截取出图片的完整名称
        String img = pyOutput.substring(0, pyOutput.indexOf(".png") + ".png".length());
        System.out.println("\n匹配到：img='" + img + "'(" + DateFormatters.yyyyMMddHHmmss.format(new Date()) + ")");
        // 从输出中获取坐标
        Point point = PyAutoGui.getPoint(pyOutput);
        // 根据图片名称和坐标执行操作
        // 根据img和point执行操作
        performAction(img, point);
    }

    /**
     * 根据图片名称和坐标执行操作
     *
     * @param img   图片名称
     * @param point 图片的坐标
     */
    protected void performAction(String img, Point point) {
        // 从图片中获取需要，额外等待时间
        int extraWaitingTime = extraWaitingTime(img);
        // 如果图片以begin_开头的话
        if (img.startsWith("begin_")) {
            // 先停止辅助的线程
            if (closeableRun != null) {
                closeableRun.stop();
            }
            // 点击左键
            Robots.leftMouseButtonClick(point);
            // s2;
            int s = waitSeconds + extraWaitingTime;
            // 等待指定时间
            // 如果已经按下了停止键
            if (isNotStopThenWait(s)) {
                return;
            }
            // 点击右键
            Robots.rightClickButton(point);
            {
                // System.out.println("移动鼠标，免得遮挡");
                // 向右下角移动鼠标，免得遮挡
                Robots.getRobot().mouseMove(point.x + 40, point.y + 40);
            }
            Threads.sleep(1500);
            // 触发后续的按键
            if (afterBtn != null) {
                afterBtn.doClick();
            }
        } else if (img.startsWith("click_")) {
            Robots.leftMouseButtonClick(point);
            // isNotStopThenWait(2 + extraWaitingTime);
            // int s = 2 + extraWaitingTime;
            // output.setText(msg + "等待:" + s + "s");
            // Robots.delay(s * 1000);
        } else if (img.startsWith("exit_")) {
            Robots.leftMouseButtonClick(point);
            isNotStopThenWait(3 + extraWaitingTime);
        } else if (img.startsWith("return_")) {
            Robots.rightClickButton(point);
            isNotStopThenWait(3);
        } else if (img.startsWith("GuangJie_")) {
            isNotStopThenWait(3);
        } else if (img.startsWith("scrollUp")) {
            System.out.println("从下向上滑动,跳过精选里的直播");
            // AdbCommands.swipeBottom2TopOnLeft(DeviceListener.getPhoneId());
            AdbCommands.swipeBottom2TopOnMiddle(DeviceListener.getPhoneId(), DeviceListener.getWidth(), DeviceListener.getHeight());
            //多等待3秒
            Robots.delay(3*1000);
        } else {
            Robots.leftMouseButtonClick(point);
            // Robots.delay(4 * 1000);
            isNotStopThenWait(3 + extraWaitingTime);
        }
        output.setText("无");
    }

    /**
     * 如果stop为false的话，则等待指定秒数。
     * 当按下停止按钮时，stop会被设置为true.
     *
     * @param s 需要等待的秒数
     * @return 如果stop为true, 则返回true, 否则返回false。
     */
    private boolean isNotStopThenWait(int s) {
        System.out.println("预计等待" + s);
        int count = 0;
        while (count <= s) {
            if (stop) {
                output.setText(msg + "等待:已结束");
                return true;
            }
            // 等待1秒
            Threads.sleep(1000);
            count += 1;
            output.setText(msg + "等待:" + (s - count) + "s");
        }
        return false;
    }

    private int extraWaitingTime(String img) {

        int seconds = 0;
        if (img.contains("+")) {
            if (img.contains("_wait")) {
                String times = img.substring(img.indexOf("+") + 1, img.lastIndexOf("_wait"));
                System.out.println("times = " + times);
                seconds = Integer.parseInt(times);
            }
            // HuoShan_oppo_+30_0.png
            // HuoShan_oppo_+20_0.png
            // HuoShan_oppo_+40_0.png
            // HuoShan_oppo_+50_0.png
            else if (img.matches("[a-zA-Z_]+\\+([0-9]+)_[a-zA-Z0-9_]+\\.png")) {
                System.out.println("简写");
                String times = img.replaceAll("[a-zA-Z_]+\\+([0-9]+)_[a-zA-Z0-9_]+\\.png", "$1");
                System.out.println("times = " + times);
                seconds = Integer.parseInt(times);
            }
        }
        return seconds;
    }
}
