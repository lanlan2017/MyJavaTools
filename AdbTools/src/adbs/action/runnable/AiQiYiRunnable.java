package adbs.action.runnable;

import adbs.cmd.Robots;

import java.awt.*;

public class AiQiYiRunnable extends PyImgFinderCloseRunnable {
    @Override
    protected void setPyOutputPath() {
        pyOutputPath = "G:\\dev2\\idea_workspace\\MyJavaTools\\AdbTools\\Pythons\\AiQiYi\\AiQiYi.txt";
    }

    @Override
    protected void setPyPath() {
        pyPath = "G:\\dev2\\idea_workspace\\MyJavaTools\\AdbTools\\Pythons\\AiQiYi\\AiQiYi.py";
    }

    @Override
    protected void imageMappingOperation(String img, Point point) {
        switch (img) {
            case "ZaiZhuan.png":
            case "ZaiZhuan1.png":
            case "LiJiLingQu.png":
                // Robots.leftClickThenRightClick(point, 50 * 1000);
                Robots.leftMouseButtonClick(point);
                // 等待一段时间
                Robots.delay(25 * 1000);
                Robots.delay(50 * 1000);
                // 按下右键返回
                Robots.rightClickButton(point);
                break;
            case "DaCha.png":
            case "DaCha0.png":
            case "DaCha1.png":
            case "DaCha2.png":
            case "DaCha3.png":
            case "ChiFanLingJinBiWoZhiDaoLe.png":
                Robots.leftMouseButtonClick(point);
                Robots.delay(1000);
                break;
            case "KaiBaoXiang.png":
                Robots.leftMouseButtonClick(point);
                Robots.delay(5000);
                break;
        }
    }

    @Override
    public void setMsg() {
        msg = "爱奇艺 线程";
    }
}
