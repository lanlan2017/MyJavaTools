package adbs.action.runnable;

import adbs.cmd.*;
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
        //先等待一小段时间
        System.out.println("悟空看视频广告监听线程...");
        runPython("G:\\dev2\\idea_workspace\\MyJavaTools\\AdbTools\\Pythons\\WuKongLiuLanQi\\GuangGao.py");
    }

    private void runPython(String pyFilePath) {
        // python文件
        // 运行python进程，获取进程的标准输出
        String pyOutput = PythonRun.runPython(pyFilePath);
        if (pyOutput.contains(".png")) {
            haveFoundPictures(pyOutput);
        }
    }

    /**
     * 如果找到特定的图片
     *
     * @param pyOutput
     */
    private void haveFoundPictures(String pyOutput) {
        // 打印进程输出
        // System.out.println("pyOutput=" + pyOutput);
        String img = pyOutput.substring(0, pyOutput.indexOf(".png") + ".png".length());
        System.out.println("匹配到：img='" + img + "'");
        // 从输出中获取坐标点
        Point point1 = PyAutoGui.getPoint(pyOutput);
        switch (img) {
            case "WuKong_DaCha.png":
            case "WuKong_DaCha0.png":
            case "WuKong_DaCha1.png":
            case "WuKong_DaCha2.png":
            case "WuKong_GuanBi1.png":
            case "WuKong_GuanBi2.png":
                System.out.println("只单击左键");
                Robots.leftMouseButtonClick(point1);
                break;
            default:
                System.out.println("先单击左键，等待，再单击右键");
                // 点击左键（进入广告），然后点击右键（返回）
                Robots.leftClickThenRightClick(point1, 35 * 1000);
                break;
        }
    }

    public static void setStop(boolean stop) {
        // 给父类的
        ClosableRunnable.setStop(stop);
        if (stop) {
            String pathname = "G:\\dev2\\idea_workspace\\MyJavaTools\\AdbTools\\Pythons\\WuKongLiuLanQi\\GuangGao.txt";
            String yueDuPidStr = Files.readFile(new File(pathname));
            if (yueDuPidStr.matches("\\d+")) {
                System.out.println(yueDuPidStr);
                // AdbCommands.runAbdCmd("taskkill /F /IM python.exe");
                CmdRun.run("taskkill -f -pid " + yueDuPidStr);
            }
        }
    }

}
