package adbs.test.auto.run;

import adbs.action.runnable.ReadButtonRunnable;
import adbs.action.runnable.abs.CloseableRunnable;
import adbs.cmd.CmdRun;
import adbs.cmd.PyAutoGui;
import adbs.cmd.PythonRun;
import adbs.cmd.Robots;
import adbs.python.PythonGenerator;
import adbs.test.auto.Buttons;
import tools.file.Files;
import tools.format.date.DateFormatters;
import tools.thead.Threads;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Date;

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

    // public PythonCloseableRun(String msg, String pyPath, JLabel output, PythonCloseableRun closeableRun, JButton afterBtn) {
    //     this.msg = msg;
    //     this.pyPath = pyPath;
    //     this.output = output;
    //     this.closeableRun = closeableRun;
    //     this.afterBtn = afterBtn;
    // }

    // public PythonCloseableRun(String msg, String pyPath, JLabel output, JButton afterBtn) {
    //     this.msg = msg;
    //     this.pyPath = pyPath;
    //     this.output = output;
    //     this.afterBtn = afterBtn;
    // }

    public PythonCloseableRun(String msg, String pyPath, JLabel output) {
        this.msg = msg;
        this.pyPath = pyPath;
        this.output = output;
    }

    // public PythonCloseableRun(String chName1, String pythonFile, JLabel output, CloseableRunnable closeableRunnable, JButton readButton) {
    //
    // }


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
                // AdbCommands.runAbdCmd("taskkill /F /IM python.exe");
                CmdRun.run("taskkill -f -pid " + yueDuPidStr);
            }
        }
    }

    @Override
    public void run() {
        // 默认循环不停止
        stop = false;
        beforeLoop();
        Buttons.getInstance().setIsRunning(this);
        while (!stop) {
            loopBody();
        }
        afterLoop();
    }

    private void beforeLoop() {
        // 更新Python文件
        updatePythonFile();
        output.setText(msg + ":开始");
        // if (closeableRun != null) {
        //     closeableRun.stop();
        // }
    }

    private void updatePythonFile() {
        // 调用子类的方法
        // setPyPath();
        // if (!isPythonFileUpdate && pyPath != null && !"".equals(pyPath)) {
        if (pyPath != null && !"".equals(pyPath)) {
            System.out.println("更新要运行的Python文件：" + pyPath);
            // 自动生成Python文件
            PythonGenerator.updatePythonFile(pyPath);
            // isPythonFileUpdate = true;
            // setMsg();
            // System.out.println(msg);
        }
    }

    private void loopBody() {
        runPython();
    }


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
        // 从输出中获取坐标点
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
        // 额外等待时间
        // extraWaitingTime
        int extraWaitingTime = extraWaitingTime(img);
        if (img.startsWith("begin_")) {
            // 先停止辅助的线程
            if (closeableRun != null) {
                closeableRun.stop();
            }

            Robots.leftMouseButtonClick(point);
            // s2;
            int s = waitSeconds + extraWaitingTime;
            // 如果已经按下了停止键
            if (isNotStopThenWait(s)) {
                return;
            }
            Robots.rightClickButton(point);
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
            isNotStopThenWait(2 + extraWaitingTime);
            // int s = 2 + extraWaitingTime;
            // output.setText(msg + "等待:" + s + "s");
            // Robots.delay(s * 1000);
        }
        // else if (img.startsWith("stop_")) {
        //     // 点击停止按钮
        //     inOutputModel.getStopBtn().doClick();
        //     String id = DeviceRadioBtAcListener.getId();
        //     // 杀死快手极速版
        //     CmdRun.run("adb -s " + id + " shell am force-stop com.kuaishou.nebula");
        //     Threads.sleep(1000);
        //     // 息屏
        //     CmdRun.run("adb -s " + id + " shell input keyevent 223");
        //     // 休眠电脑
        //     // CmdRun.run("shutdown /h");
        // }
        else {
            Robots.leftMouseButtonClick(point);
            Robots.delay(2 * 1000);
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
        if (img.contains("+") && img.contains("_wait")) {
            String times = img.substring(img.indexOf("+") + 1, img.lastIndexOf("_wait"));
            System.out.println("times = " + times);
            seconds = Integer.parseInt(times);
        }
        return seconds;
    }
}
