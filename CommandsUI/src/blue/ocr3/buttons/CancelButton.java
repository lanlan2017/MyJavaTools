package blue.ocr3.buttons;

import blue.ocr3.ScreenShotWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class CancelButton extends ButtonKeyAction {
    private static final CancelButton instance = new CancelButton();;

    private CancelButton() {
        button = new JButton("取消截屏");
        abstractAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelButtonAction();
            }
        };
        setAction();
    }

    public void cancelButtonAction() {
        //     // 不显示截屏窗口
        ScreenShotWindow.getInstance().setVisible(false);
        //     // 移动窗体到最左边
        //     // ToolsWindow.defaultLocation();
    }

    public static CancelButton getInstance() {
        // if (instance == null) {
        //     instance = new CancelButton();
        // }
        return instance;
    }
}
