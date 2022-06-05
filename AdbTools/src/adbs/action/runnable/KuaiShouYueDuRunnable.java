package adbs.action.runnable;

import adbs.cmd.Robots;

import javax.swing.*;
import java.awt.*;

public class KuaiShouYueDuRunnable extends PyImgFinderCloseRunnable {
    private JButton readButton;

    public KuaiShouYueDuRunnable(JButton readButton) {
        this.readButton = readButton;
    }

    @Override
    protected void setPyPath() {
        pyPath = "G:\\dev2\\idea_workspace\\MyJavaTools\\AdbTools\\Pythons\\KuaiShou\\YueDu.py";
    }

    @Override
    protected void performAction(String img, Point point) {
        // 停止阅读进程
        ReadButtonRunnable.setStop(true);
        // 先点击鼠标左键 ，等待一定时间后 ，点击鼠标右键
        Robots.leftClickThenRightClick(point, 40 * 1000);
        // 退出广告界面之后，开启阅读线程
        readButton.doClick();
        // 等待一小段时间，让解锁界面打开
        Robots.delay(1500);
    }

    @Override
    public void setMsg() {
        msg = "快手阅读_解锁金币监听线程";
    }
}
