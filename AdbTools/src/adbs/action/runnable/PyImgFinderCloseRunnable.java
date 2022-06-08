package adbs.action.runnable;

import adbs.action.model.InOutputModel;
import adbs.cmd.CmdRun;
import adbs.cmd.PyAutoGui;
import adbs.cmd.PythonRun;
import adbs.python.PythonGenerator;
import tools.file.Files;

import java.awt.*;
import java.io.File;

public abstract class PyImgFinderCloseRunnable extends CloseableRunnable {
    private InOutputModel inOutputModel;

    public void setInOutputModel(InOutputModel inOutputModel) {
        this.inOutputModel = inOutputModel;
    }

    /**
     * python文件的绝对路径
     */
    protected static String pyPath;
    /**
     * 是否要更新python文件
     */
    private boolean isPythonFileUpdate = false;

    @Override
    protected void beforeLoop() {
        if (inOutputModel != null) {
            inOutputModel.getOutput().setText(msg + ":已经开始");
        }
    }

    @Override
    protected void loopBody() {
        updatePythonFile();
        // 调用子类的方法
        // setMsg();
        // System.out.println(msg);
        // 运行python文件
        runPython(pyPath);
    }

    @Override
    protected void afterLoop() {
        if (inOutputModel != null) {
            inOutputModel.getOutput().setText(msg + ":已经结束");
        }
    }

    /**
     * 设置要执行的Python文件的绝对路径
     */
    protected abstract void setPyPath();

    private void updatePythonFile() {
        // 调用子类的方法
        setPyPath();
        if (!isPythonFileUpdate && pyPath != null && !"".equals(pyPath)) {
            System.out.println("更新要运行的Python文件：" + pyPath);
            // 自动生成Python文件
            PythonGenerator.updatePythonFile(pyPath);
            isPythonFileUpdate = true;
            setMsg();
            System.out.println(msg);
        }
    }


    /**
     * 执行Python文件
     *
     * @param pyFilePath python文件的绝对路径
     */
    private void runPython(String pyFilePath) {
        // 运行python进程，获取进程的标准输出
        String pyOutput = PythonRun.runPython(pyFilePath);
        // 如果python输出中有.png的话
        if (pyOutput.contains(".png")) {
            // 处理带图片的输出
            haveFoundPictures(pyOutput);
        }
    }

    /**
     * 如果python找到特定的图片
     *
     * @param pyOutput python输出（带有图片的输出）
     */
    private void haveFoundPictures(String pyOutput) {
        // 打印进程输出
        // System.out.println("pyOutput=" + pyOutput);
        // 截取出图片的完整名称
        String img = pyOutput.substring(0, pyOutput.indexOf(".png") + ".png".length());
        System.out.println("匹配到：img='" + img + "'");
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
    protected abstract void performAction(String img, Point point);

    @Override
    public void stop() {
        super.stop();
        if (stop) {
            // 获取与python文件同名的txt文件
            String pyOutputPath = pyPath.replace(".py", ".txt");
            File file = new File(pyOutputPath);
            // 如果文件存在
            if (file.exists()) {
                // 读取文件内容
                String yueDuPidStr = Files.readFile(file);
                // 如果文件内容都是数字
                if (yueDuPidStr.matches("\\d+")) {
                    // 输出内容
                    System.out.println("杀死pid=" + yueDuPidStr + "的进程（python）");
                    // AdbCommands.runAbdCmd("taskkill /F /IM python.exe");
                    CmdRun.run("taskkill -f -pid " + yueDuPidStr);
                }
            }
        }
    }
}
