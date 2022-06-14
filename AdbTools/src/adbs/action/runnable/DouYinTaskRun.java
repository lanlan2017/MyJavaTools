package adbs.action.runnable;

import adbs.action.runnable.abs.PyImgFinderCloseRunnable;

public class DouYinTaskRun extends PyImgFinderCloseRunnable {

    private static DouYinTaskRun instance = new DouYinTaskRun();

    private DouYinTaskRun() {
    }

    public static DouYinTaskRun getInstance() {
        return instance;
    }


    @Override
    protected void setPyPath() {
        pyPath = "G:\\dev2\\idea_workspace\\MyJavaTools\\AdbTools\\Pythons\\DouYin\\ZhuanQianRenWu\\Task.py";
    }

    @Override
    protected void setMsg() {
        msg = "抖音任务测试版";
    }
}
