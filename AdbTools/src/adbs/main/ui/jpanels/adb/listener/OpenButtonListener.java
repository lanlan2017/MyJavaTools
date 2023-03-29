package adbs.main.ui.jpanels.adb.listener;

import adbs.main.ui.jpanels.adb.open.OpenButtonRunnable;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * 打开scrcpy.exe镜像按钮事件处理类
 */
public class OpenButtonListener extends ButtonFocusReleaseActionListener {
    private JTextField width;
    private OpenButtonRunnable openButtonRunnable;

    public OpenButtonListener(JTextField width) {
        this.width = width;
        // 创建线程体
        openButtonRunnable = new OpenButtonRunnable(width);
    }

    public OpenButtonListener() {
        // 创建线程体
        openButtonRunnable = new OpenButtonRunnable();
    }

    @Override
    protected void actionEvent(ActionEvent e) {
        // 启动线程
        new Thread(openButtonRunnable).start();
    }
}
