package adbs.action.runnable;

import adbs.action.model.InOutputModel;
import adbs.cmd.*;
import adbs.ui.AdbTools;
import tools.file.Files;

import java.awt.*;
import java.io.File;

public class DouYinVideoButtonRunnable extends ClosableRunnable {
    private InOutputModel inOutputModel;

    public DouYinVideoButtonRunnable(InOutputModel inOutputModel) {
        this.inOutputModel = inOutputModel;
    }

    @Override
    public void setMsg() {
        msg = "抖音刷视频红包监听线程";
    }

    @Override
    protected void running() {
        AdbTools.setIsRunning(this);

        seeVideo();
    }

    /**
     * 快手阅读广告监听处理
     */
    public void seeVideo() {
        // python文件
        String pyFilePath = "G:\\dev2\\idea_workspace\\MyJavaTools\\AdbTools\\Pythons\\DouYin\\DouYinVideoAdv.py";
        // 执行python文件获取要操作的坐标点
        // 运行python进程，获取进程的标准输出
        String pyOutput = PythonRun.runPython(pyFilePath);
        Point point = PyAutoGui.getPoint(pyOutput);
        // 停止刷视频进程
        // VideoButtonRunnable.setStop(true);
        // 先点击鼠标左键 ，等待一定时间后 ，点击鼠标右键
        Robots.leftClickThenRightClick(point, 800);
        // 等待一小段时间，让解锁界面打开
        Robots.delay(1000);
    }


    public static void setStop(boolean stop) {
        // 给父类的
        ClosableRunnable.setStop(stop);
        if (stop) {
            // 读取python的输出文件，该输出文件存放当前启动的python进程的pid
            String pathname = "G:\\dev2\\idea_workspace\\MyJavaTools\\AdbTools\\Pythons\\DouYin\\DouYinVideoAdv.txt";
            String yueDuPidStr = Files.readFile(new File(pathname));
            if (yueDuPidStr.matches("\\d+")) {
                System.out.println("python进程pid=" + yueDuPidStr);
                // 关闭python进程
                CmdRun.run("taskkill -f -pid " + yueDuPidStr);
            }
        }
    }
}
