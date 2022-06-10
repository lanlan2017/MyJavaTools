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
        // 等待
        switch (img) {
            case "begin_GuangGao_ZaiKanYiGe_oppo.png":
            case "begin_KaiBaoXiang_KanShiPinZuiGaoDe_honor.png":
            case "begin_KanGuangGaoZhuanJinBi_oppo.png":
                Robots.leftClickThenRightClick(point, 40*1000);
                break;
            case "exit_KanGuangGaoZhuanJinBi_FangQi_oppo.png":
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
