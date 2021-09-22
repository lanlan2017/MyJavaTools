package blue.ocr3.buttons;

import blue.ocr3.ScreenShotWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SstButton extends ButtonKeyAction {
    // 单例模式2，创建类的唯一实例，饿汉式，不管怎样先创建好
    private static final SstButton instance=new SstButton();
    // 单例模式：1私有化构造器
    private SstButton() {
        this.button = new JButton("截屏");
        this.abstractAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sstButtonAction();
            }
        };
        setAction();
    }
    // 单例模式3:给出返回唯一实例的public方法
    public static SstButton getInstance() {
        return instance;
    }

    public void sstButtonAction() {
        // 再次截屏
        ScreenShotWindow.getInstance().screenshotAgain();
        // 显示窗口
        ScreenShotWindow.getInstance().setVisible(true);
    }
}
