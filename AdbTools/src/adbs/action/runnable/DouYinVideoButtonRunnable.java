package adbs.action.runnable;

import adbs.action.runnable.abs.PyImgFinderCloseRunnable;
import adbs.cmd.Robots;

import java.awt.*;

public class DouYinVideoButtonRunnable extends PyImgFinderCloseRunnable {
    @Override
    protected void setPyPath() {
        // pyPath = "G:\\dev2\\idea_workspace\\MyJavaTools\\AdbTools\\Pythons\\DouYin\\DouYinVideoAdv.py";
        pyPath = "AdbToolsPythons\\DouYin\\DouYinVideoAdv.py";
    }

    @Override
    protected void performAction(String img, Point point) {
        // 先点击鼠标左键 ，等待一定时间后 ，点击鼠标右键
        Robots.leftClickThenRightClick(point, 800);
        // 等待一小段时间，让解锁界面打开
        Robots.delay(1000);
    }

    @Override
    public void setMsg() {
        msg = "抖音刷视频红包监听线程";
    }
}
