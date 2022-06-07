package adbs.action.runnable;

import adbs.cmd.Robots;

import java.awt.*;

public class DouYinTaskRunnable2 extends PyImgFinderCloseRunnable2 {
    private static DouYinTaskRunnable2 instance = new DouYinTaskRunnable2();

    private DouYinTaskRunnable2() {
    }

    public static DouYinTaskRunnable2 getInstance() {
        return instance;
    }


    @Override
    protected void setPyPath() {
        pyPath = "G:\\dev2\\idea_workspace\\MyJavaTools\\AdbTools\\Pythons\\DouYin\\ZhuanQianRenWu\\Task.py";
    }

    @Override
    protected void performAction(String img, Point point) {
        switch (img) {
            case "A_KanGuangGaoShiPinZaiZhuan.png":
            case "A_KanWanShiPinZaiLing.png":
            case "A_LingQuJiangLi.png":
                System.out.println("抖音任务测试版 case 触发");
                // Robots.leftMouseButtonClick(point);
                // Robots.delay(35 * 1000);
                // Robots.rightClickButton(point);
                Robots.leftClickThenRightClick(point, 35*1000);
                break;
            default:
                System.out.println("抖音任务测试版 默认 触发");
                Robots.leftMouseButtonClick(point);
                Robots.delay(1500);
                break;
        }
    }

    @Override
    protected void setMsg() {
        msg = "抖音任务测试版";
    }
}
