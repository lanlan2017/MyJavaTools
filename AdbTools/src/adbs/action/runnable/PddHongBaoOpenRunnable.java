package adbs.action.runnable;

import adbs.action.runnable.abs.PyImgFinderCloseRunnable;

public class PddHongBaoOpenRunnable extends PyImgFinderCloseRunnable {
    @Override
    protected void setMsg() {
        msg = "拼多多开红包线程";
    }

    @Override
    protected void setPyPath() {
        pyPath = "G:\\dev2\\idea_workspace\\MyJavaTools\\AdbTools\\Pythons\\PinDuoDuo\\PinDuoDuoKaiHongBao.py";
    }
}
