package adbs.action.runnable;

import adbs.cmd.Robots;

import java.awt.*;

public class PddHongBaoOpenRunnable extends PyImgFinderCloseRunnable {
    public PddHongBaoOpenRunnable() {
        setPyPath();
    }

    public static void setStop(boolean stop) {
        PyImgFinderCloseRunnable.setStop(stop);
    }

    @Override
    protected void setPyPath() {
        pyPath = "G:\\dev2\\idea_workspace\\MyJavaTools\\AdbTools\\Pythons\\PinDuoDuo\\PinDuoDuoKaiHongBao.py";
    }

    @Override
    protected void imageMappingOperation(String img, Point point1) {
        switch (img) {
            case "JiXuKai.png":
            case "Kai.png":
                // 点击鼠标左键
                Robots.leftMouseButtonClick(point1);
                Robots.delay(1000);
                break;
            default:
                System.out.println("拼多多开红包");
                break;
        }
    }

    @Override
    public void setMsg() {
        msg = "拼多多开红包线程";
    }
}
