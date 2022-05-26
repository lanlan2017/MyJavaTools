package adbs.action.runnable;

import adbs.cmd.ClosableRunnable;
import adbs.cmd.CmdRun;
import adbs.cmd.PyAutoGui;
import adbs.cmd.Robots;
import adbs.ui.AdbTools;
import tools.file.Files;

import java.awt.*;
import java.io.File;

public class WuKongGuanBiRunnable extends ClosableRunnable {

    @Override
    public void setMsg() {
        this.msg = "悟空浏览器 看视频监听线程";
    }

    @Override
    protected void running() {
        AdbTools.setIsRunning(this);
        // python文件
        String pyFilePath = "G:\\dev2\\idea_workspace\\MyJavaTools\\Pythons\\WuKongLiuLanQi\\GuangGao.py";
        // 执行python文件获取要操作的坐标点
        Point point = PyAutoGui.getPoint(pyFilePath);
        Robots.leftClickThenRightClick(point, 40 * 1000);
    }

    public static void setStop(boolean stop) {
        // 给父类的
        ClosableRunnable.setStop(stop);
        if (stop) {
            String yueDuPidStr = Files.readFile(new File("G:\\dev2\\idea_workspace\\MyJavaTools\\Pythons\\WuKongLiuLanQi\\GuangGao.txt"));
            if (yueDuPidStr.matches("\\d+")) {
                System.out.println(yueDuPidStr);
                // AdbCommands.runAbdCmd("taskkill /F /IM python.exe");
                CmdRun.run("taskkill -f -pid " + yueDuPidStr);
            }
        }
    }

}
