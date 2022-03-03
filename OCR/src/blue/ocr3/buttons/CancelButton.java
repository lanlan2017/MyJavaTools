package blue.ocr3.buttons;

import blue.ocr3.ScreenShotWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class CancelButton extends ButtonKeyAction {
    private static CancelButton instance;

    private CancelButton() {
        button = new JButton("取消截屏");
        abstractAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //     // 不显示截屏窗口
                ScreenShotWindow.getInstance().setVisible(false);
                //     // 移动窗体到最左边
                //     // ToolsWindow.defaultLocation();
            }
        };
        modifiers = KeyEvent.CTRL_DOWN_MASK;
        keyCode = KeyEvent.VK_E;
    }

    private CancelButton(JPanel fatherPane) {
        this.button = new JButton("取消截屏");
        this.abstractAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //     // 不显示截屏窗口
                ScreenShotWindow.getInstance().setVisible(false);
                //     // 移动窗体到最左边
                //     // ToolsWindow.defaultLocation();
            }
        };
        this.modifiers = KeyEvent.CTRL_DOWN_MASK;
        this.keyCode = KeyEvent.VK_E;
        this.fatherPanel = fatherPane;
        setAction();
        setKeys();
    }

    public static CancelButton getInstance(JPanel fatherPanel) {
        if (instance == null) {
            instance = new CancelButton(fatherPanel);
        } return instance;
    }
}
