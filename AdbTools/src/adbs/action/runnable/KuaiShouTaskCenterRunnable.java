package adbs.action.runnable;

import adbs.cmd.Robots;

import java.awt.*;

public class KuaiShouTaskCenterRunnable extends PyImgFinderCloseRunnable {
    @Override
    protected void setPyPath() {
        pyPath = "G:\\dev2\\idea_workspace\\MyJavaTools\\AdbTools\\Pythons\\KuaiShou\\TaskCenter\\TaskCenter.py";
    }

    @Override
    protected void performAction(String img, Point point) {
        switch (img) {
            case "GuangGao_KaiShi.png":
            case "B_ZhiBuo_Start.png":
            case "KaiBaoXiang_A_KanShiPinZuiGaoDe.png":
            case "A_KaiBaoXiang_KanShiPinZuiGaoDe_honor.png":
            case "KaiBaoXiang_ZaiKanYiGeZuiGaoDe.png":
            case "GuangGao_ZaiKanYiGe.png":
            case "GuangGao_KaiShi_oppo.png":
            case "GuangGao_ZaiKanYiGe_oppo.png":
            case "GuangGao_ZaiKanYiGeZuiGaoDe_oppo.png":
                System.out.println("case");
                // Robots.leftMouseButtonClick(point);
                // Robots.delay(40 * 1000);
                // Robots.rightClickButton(point);
                Robots.leftClickThenRightClick(point, 40 * 1000);
                break;
            case "ZhiBuo_ZhiBuoZhong.png":
                Robots.leftMouseButtonClick(point);
                Robots.delay(70 * 1000);
                Robots.rightClickButton(point);
                break;
            default:
                System.out.println("default");
                Robots.leftMouseButtonClick(point);
                Robots.delay(2 * 1000);
                break;
        }
    }

    @Override
    public void setMsg() {
        msg = "快手任务中心监听线程";
    }
}
