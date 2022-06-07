package adbs.action.runnable;

import adbs.cmd.Robots;

import java.awt.*;

public class DouYinTaskRunnable extends PyImgFinderCloseRunnable {
    @Override
    protected void setPyPath() {
        pyPath = "G:\\dev2\\idea_workspace\\MyJavaTools\\AdbTools\\Pythons\\DouYin\\ZhuanQianRenWu\\Task.py";
    }

    @Override
    protected void performAction(String img, Point point) {
        switch (img) {
            case "A_KanWanShiPinZaiLing.png":
            case "A_LingQuJiangLi.png":
            case "A_KanGuangGaoShiPinZaiZhuan.png":

                Robots.leftClickThenRightClick(point, 35 * 1000);
                break;
            default:
                Robots.leftMouseButtonClick(point);
                Robots.delay(1500);
                break;
        }
    }

    @Override
    public void setMsg() {
        msg = "抖音赚钱任务";
    }
}
