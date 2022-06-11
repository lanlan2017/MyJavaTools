package adbs.action.runnable;

import adbs.cmd.Robots;

import java.awt.*;


public class WuKongGuanBiRunnable extends PyImgFinderCloseRunnable {
    @Override
    protected void setPyPath() {
        pyPath = "G:\\dev2\\idea_workspace\\MyJavaTools\\AdbTools\\Pythons\\WuKongLiuLanQi\\GuangGao.py";
    }

    @Override
    protected void performAction(String img, Point point) {
        switch (img) {
            case "begin_KanShiPinLingQu_oppo.png":
            case "begin_KanShiPinZaiLing_oppo.png":
            case "begin_oppo.png":
            case "begin_WuKong_KanShiPinLing.png":
            case "begin_WuKong_KanShiPinZaiLing.png":
            case "begin_WuKong_ZaiKan.png":
            case "begin_WuKong_ZaiKanYiGe.png":
            case "begin_WuKong_ZaiKanYiGe_honor.png":
            case "begin_WuKong_ZaiKanYiGe_oppo.png":
            case "begin_ZaiKanYiTiaoShiPinLing_oppo.png":
                Robots.leftClickThenRightClick(point, 40 * 1000);
                break;
            case "exit_ChiFanZhuanQianLingQu_oppo.png":
            case "exit_DaCha0.png":
            case "exit_DaCha1.png":
            case "exit_DaCha2.png":
            case "exit_DaCha3.png":
            case "exit_GuanBi1.png":
            case "exit_GuanBi3.png":
            case "exit_KaiBaoXiang_honor.png":
            case "exit_KaiBaoXiang_oppo.png":
            case "exit_oppo.png":
            case "exit_Z_300KanShiPin.png":
            case "exit_Z_300KanShiPin_honor_1.png":
            case "exit_Z_300KanShiPin_honor_2.png":
            case "exit_Z_300KanShiPin_oppo.png":
            case "exit_Z_300KanShiPin_oppo0.png":
            case "exit_Z_300KanShiPin_oppo2.png":
                Robots.leftMouseButtonClick(point);
                Robots.delay(2 * 1000);
                break;
        }
    }

    @Override
    public void setMsg() {
        msg = "悟空浏览器 看视频监听线程";
    }
}
