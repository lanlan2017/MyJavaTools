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
            case "WuKong_DaCha.png":
            case "WuKong_DaCha0.png":
            case "WuKong_DaCha1.png":
            case "WuKong_DaCha2.png":
            case "WuKong_GuanBi1.png":
            case "WuKong_GuanBi2.png":
                System.out.println("只单击左键");
                Robots.leftMouseButtonClick(point);
                break;
            default:
                System.out.println("先单击左键，等待，再单击右键");
                // 点击左键（进入广告），然后点击右键（返回）
                Robots.leftClickThenRightClick(point, 35 * 1000);
                break;
        }
    }

    @Override
    public void setMsg() {
        msg = "悟空浏览器 看视频监听线程";
    }
}
