package adbs.action.runnable;

import adbs.cmd.CmdRun;
import adbs.cmd.PyAutoGui;
import adbs.cmd.Robots;
import adbs.cmd.ClosableRunnable;
import tools.file.Files;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class KuaiShouYueDuRunnable extends ClosableRunnable {
    private JButton readButton;
    private JButton stopButton;

    public KuaiShouYueDuRunnable(JButton readButton, JButton stopButton) {
        this.readButton = readButton;
        this.stopButton = stopButton;
    }

    @Override
    protected void running() {
        System.out.println("本次阅读广告监听 开始");
        yueDu();
        System.out.println("本次阅读广告监听 结束");
    }


    public static void setStop(boolean stop) {
        // 给父类的
        ClosableRunnable.setStop(stop);
        if (stop) {
            String yueDuPidStr = Files.readFile(new File("G:\\dev2\\idea_workspace\\MyJavaTools\\Pythons\\KuaiShou\\YueDuPid.txt"));
            if (yueDuPidStr.matches("\\d+")) {
                System.out.println("python进程pid=" + yueDuPidStr);
                // 关闭python进程
                CmdRun.run("taskkill -f -pid " + yueDuPidStr);
            }
        }
    }

    @Override
    public void setMsg() {
        this.msg = "快手阅读_解锁金币监听线程";
    }

    /**
     * 快手阅读广告监听处理
     */
    public void yueDu() {
        // python文件
        String pyFilePath = "G:\\dev2\\idea_workspace\\MyJavaTools\\Pythons\\KuaiShou\\YueDu.py";
        // 执行python文件获取要操作的坐标点
        Point point = PyAutoGui.getPoint(pyFilePath);
        // 当找到广告按钮时触发关闭按钮时间
        // stopButton.doClick();
        ReadButtonRunnable.setStop(true);
        // 先点击鼠标左键 ，等待一定时间后 ，点击鼠标右键
        Robots.leftClickThenRightClick(point, 30 * 1000);
        // 退出广告界面之后，开启阅读线程
        readButton.doClick();

        // 等待一小段时间，让解锁界面打开
        Robots.delay(1000);
    }
}
