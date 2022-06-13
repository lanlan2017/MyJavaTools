package adbs.action.runnable;

import adbs.action.runnable.abs.PyImgFinderCloseRunnable;

public class KuaiShouTaskCenterRunnable extends PyImgFinderCloseRunnable {
    @Override
    protected void setPyPath() {
        pyPath = "G:\\dev2\\idea_workspace\\MyJavaTools\\AdbTools\\Pythons\\KuaiShou\\TaskCenter\\TaskCenter.py";
    }

    @Override
    public void setMsg() {
        msg = "快手任务中心监听线程";
    }
}
