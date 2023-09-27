package adbs.main.ui.jpanels.scrcpy.listener;

import adbs.main.ui.jpanels.adb.listener.ButtonFocusReleaseActionListener;
import adbs.main.ui.jpanels.scrcpy.run.OpenButtonRunnable;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * 打开scrcpy.exe镜像按钮事件处理类
 */
public class OpenButtonListener extends ButtonFocusReleaseActionListener {
    private JTextField height;
    private OpenButtonRunnable openButtonRunnable;
    private String heightText;

    public OpenButtonListener(JTextField height) {
        this.height = height;
        // 创建线程体
        // openButtonRunnable = new OpenButtonRunnable(height);
        heightText = height.getText();
        openButtonRunnable = new OpenButtonRunnable(heightText);

    }

    public OpenButtonListener(String heightText) {
        this.heightText = heightText;
        openButtonRunnable = new OpenButtonRunnable(heightText);

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
