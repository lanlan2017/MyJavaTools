package adbs.main.ui.jpanels.adb.listener;

import adbs.main.ui.jpanels.adb.open.OpenButtonRunnable;

import java.awt.event.ActionEvent;

/**
 * 打开scrcpy.exe镜像按钮事件处理类
 */
public class OpenButtonListener extends ButtonFocusReleaseActionListener {
    private OpenButtonRunnable openButtonRunnable;

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
