package adbs.action.runnable;

import adbs.action.runnable.abs.PyImgFinderCloseRunnable;

public class WuKongGuanBiRunnable extends PyImgFinderCloseRunnable {
    @Override
    protected void setPyPath() {
        pyPath = "G:\\dev2\\idea_workspace\\MyJavaTools\\AdbTools\\Pythons\\WuKongLiuLanQi\\GuangGao.py";
    }

    @Override
    public void setMsg() {
        msg = "悟空浏览器 看视频监听线程";
    }
}
