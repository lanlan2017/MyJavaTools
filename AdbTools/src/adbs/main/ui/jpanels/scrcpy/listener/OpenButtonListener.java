// package adbs.main.ui.jpanels.scrcpy.listener;
//
// import adbs.main.ui.jpanels.adb.listener.ButtonFocusReleaseActionListener;
// import adbs.main.ui.jpanels.scrcpy.run.OpenButtonRunnable;
//
// import javax.swing.*;
// import java.awt.event.ActionEvent;
//
// /**
//  * 打开scrcpy.exe镜像按钮事件处理类
//  */
// public class OpenButtonListener extends ButtonFocusReleaseActionListener {
//     private OpenButtonRunnable openButtonRunnable;
//
//     public OpenButtonListener(JTextField height) {
//         // 创建线程体
//         // openButtonRunnable = new OpenButtonRunnable(height);
//         // heightText = height.getText();
//         // openButtonRunnable = new OpenButtonRunnable(heightText);
//         openButtonRunnable = new OpenButtonRunnable(height);
//
//     }
//
//     public OpenButtonListener(String heightText) {
//         openButtonRunnable = new OpenButtonRunnable(heightText);
//
//     }
//
//     public OpenButtonListener() {
//         // 创建线程体
//         openButtonRunnable = new OpenButtonRunnable();
//     }
//
//     @Override
//     protected void actionEvent(ActionEvent e) {
//         // 启动线程
//         new Thread(openButtonRunnable).start();
//     }
// }
