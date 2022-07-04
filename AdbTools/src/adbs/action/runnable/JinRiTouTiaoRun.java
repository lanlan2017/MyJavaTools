package adbs.action.runnable;

import adbs.action.runnable.abs.PyImgFinderCloseRunnable;

public class JinRiTouTiaoRun extends PyImgFinderCloseRunnable {
    @Override
    protected void setPyPath() {
        // pyPath = "G:\\dev2\\idea_workspace\\MyJavaTools\\AdbTools\\Pythons\\JinRiTouTiao\\JinRiTouTiao.py";
        pyPath = "AdbToolsPythons\\JinRiTouTiao\\JinRiTouTiao.py";
    }

    @Override
    public void setMsg() {
        msg = "今日头条极速版赚金币线程";
    }
}
