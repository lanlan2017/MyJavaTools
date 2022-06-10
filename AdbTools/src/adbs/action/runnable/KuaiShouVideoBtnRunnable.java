package adbs.action.runnable;

import adbs.cmd.Robots;

import javax.swing.*;
import java.awt.*;

public class KuaiShouVideoBtnRunnable extends PyImgFinderCloseRunnable {
    private JButton videoBtn;
    private JButton stopBtn;

    // public KuaiShouVideoBtnRunnable() {
    // }
    //
    // public KuaiShouVideoBtnRunnable(JButton videoBtn) {
    //     this.videoBtn = videoBtn;
    // }

    public KuaiShouVideoBtnRunnable(JButton videoBtn, JButton stopBtn) {
        this.videoBtn = videoBtn;
        this.stopBtn = stopBtn;
    }

    @Override
    protected void setMsg() {
        msg = "快手刷视频线程";
    }

    @Override
    protected void setPyPath() {
        pyPath = "G:\\dev2\\idea_workspace\\MyJavaTools\\AdbTools\\Pythons\\KuaiShou\\Video\\video.py";
    }

    @Override
    protected void performAction(String img, Point point) {
        switch (img) {
            case "DianJiFanBei.png":
                // // 先停止刷视频
                // stopBtn.doClick();
                Robots.leftMouseButtonClick(point);
                Robots.delay(1 * 1000);
                // //开始刷视频
                // videoBtn.doClick();
                break;

        }
    }
}
