package adbs.action.runnable;

import adbs.action.runnable.abs.PyImgFinderCloseRunnable;
import adbs.main.AdbTools;
import adbs.main.auto.listener.Device;

public class KuaiShouTaskCenterRun extends PyImgFinderCloseRunnable {
    @Override
    protected void setPyPath() {
        // pyPath = "G:\\dev2\\idea_workspace\\MyJavaTools\\AdbTools\\Pythons\\KuaiShou\\TaskCenter\\TaskCenter.py";
        // String brand = Device.getBrand();
        String brand = AdbTools.device.getBrand2();
        // pyPath = "G:\\dev2\\idea_workspace\\MyJavaTools\\AdbTools\\Pythons\\KuaiShou\\TaskCenter\\TaskCenter_" + brand + ".py";
        pyPath = "AdbToolsPythons\\KuaiShou\\TaskCenter\\TaskCenter_" + brand + ".py";
    }

    @Override
    public void setMsg() {
        msg = "快手任务监听线程";
    }
}
