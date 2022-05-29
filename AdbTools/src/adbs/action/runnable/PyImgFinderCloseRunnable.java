package adbs.action.runnable;

import adbs.cmd.*;
import adbs.ui.AdbTools;
import tools.file.Files;

import java.awt.*;
import java.io.File;

/**
 * Python图片查找器 可关闭线程体
 */
public abstract class PyImgFinderCloseRunnable extends ClosableRunnable {

    /**
     * python输出文件的绝对路径，txt文件，里面存放当前python进程的pid
     */
    protected static String pyOutputPath;
    /**
     * python文件的绝对路径
     */
    protected static String pyPath;

    // @Override
    // public void setMsg() {
    //     msg = "拼多多开红包线程";
    // }

    protected abstract void setPyOutputPath();

    protected abstract void setPyPath();

    @Override
    protected void running() {
        AdbTools.setIsRunning(this);

        setPyOutputPath();
        setPyPath();
        setMsg();
        System.out.println(msg);
        // this.pyPath = "G:\\dev2\\idea_workspace\\MyJavaTools\\AdbTools\\Pythons\\WuKongLiuLanQi\\GuangGao.py";
        // String pyPath = this.pyPath;
        // 运行python文件
        runPython(pyPath);
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
        imageMappingOperation(img, point1);
    }

    /**
     * 根据图片执行操作
     *
     * @param img
     * @param point
     */
    protected abstract void imageMappingOperation(String img, Point point);

    // /**
    //  * 根据图片执行操作
    //  *
    //  * @param img
    //  * @param point1
    //  */
    // protected void imageMappingOperation(String img, Point point1) {
    //     switch (img) {
    //         case "WuKong_DaCha.png":
    //         case "WuKong_DaCha0.png":
    //         case "WuKong_DaCha1.png":
    //         case "WuKong_DaCha2.png":
    //         case "WuKong_GuanBi1.png":
    //         case "WuKong_GuanBi2.png":
    //             System.out.println("只单击左键");
    //             Robots.clickLeftButton(point1);
    //             break;
    //         default:
    //             System.out.println("先单击左键，等待，再单击右键");
    //             // 点击左键（进入广告），然后点击右键（返回）
    //             Robots.leftClickThenRightClick(point1, 35 * 1000);
    //             break;
    //     }
    // }
    public static void setStop(boolean stop) {
        // 给父类的
        ClosableRunnable.setStop(stop);
        if (stop) {
            String yueDuPidStr = Files.readFile(new File(pyOutputPath));
            if (yueDuPidStr.matches("\\d+")) {
                System.out.println(yueDuPidStr);
                // AdbCommands.runAbdCmd("taskkill /F /IM python.exe");
                CmdRun.run("taskkill -f -pid " + yueDuPidStr);
            }
        }
    }

}
