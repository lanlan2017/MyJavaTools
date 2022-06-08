package adbs.action.runnable;

import adbs.cmd.Robots;

import java.awt.*;

public class JinRiTouTiaoRunnable extends PyImgFinderCloseRunnable {
    @Override
    protected void setPyPath() {
        pyPath = "G:\\dev2\\idea_workspace\\MyJavaTools\\AdbTools\\Pythons\\JinRiTouTiao\\JinRiTouTiao.py";
    }

    @Override
    protected void performAction(String img, Point point) {
        switch (img) {
            case "A_KanGuangGaoShiPinZaiZhuan.png":
            case "A_KanWanShiPinZaiLing_honor.png":
            case "A_KanWanShiPinZaiLing2.png":
            case "A_LingQuJiangLi.png":
            case "A_ZaiKanYiGe.png":
                Robots.leftClickThenRightClick(point, 35 * 1000);
                break;
            default:
                Robots.leftMouseButtonClick(point);
                Robots.delay(1000);
                break;
        }
    }

    @Override
    public void setMsg() {
        msg = "今日头条极速版赚金币线程";
    }
}
