package adbs.action.runnable;

import adbs.action.runnable.abs.PyImgFinderCloseRunnable;
import adbs.test.Device;

public class WuKongRun extends PyImgFinderCloseRunnable {
    @Override
    protected void setPyPath() {

        pyPath = "G:\\dev2\\idea_workspace\\MyJavaTools\\AdbTools\\Pythons\\WuKongLiuLanQi\\WuKong_" + Device.getBrand() + ".py";
        setWaitSeconds(40);
    }

    @Override
    public void setMsg() {
        msg = "悟空浏览器";
    }
}
