package adbs.action.runnable;

import adbs.action.runnable.abs.PyImgFinderCloseRunnable;

public class TaoBaoRunnable extends PyImgFinderCloseRunnable {
    @Override
    protected void setMsg() {
        msg = "淘宝进程";
    }

    @Override
    protected void setPyPath() {
        // pyPath = "G:\\dev2\\idea_workspace\\MyJavaTools\\AdbTools\\Pythons\\TaoBao\\TaoBao.py";
        pyPath = "AdbToolsPythons\\TaoBao\\TaoBao.py";
    }
}
