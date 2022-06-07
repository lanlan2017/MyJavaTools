package adbs.action.runnable;

import adbs.cmd.Robots;

import java.awt.*;

public class PddHongBaoOpenRunnable extends PyImgFinderCloseRunnable {
    @Override
    protected void setMsg() {
        msg = "拼多多开红包线程";
    }

    @Override
    protected void setPyPath() {
        pyPath = "G:\\dev2\\idea_workspace\\MyJavaTools\\AdbTools\\Pythons\\PinDuoDuo\\PinDuoDuoKaiHongBao.py";
    }

    @Override
    protected void performAction(String img, Point point) {
        switch (img) {
            case "JiXuKai.png":
            case "Kai.png":
                // 点击鼠标左键
                Robots.leftMouseButtonClick(point);
                // 等待动画结束
                Robots.delay(1000);
                break;
        }
    }
}
