// package adbs.main.auto.listener;
//
// import adbs.cmd.CmdRun;
//
// import javax.swing.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
//
// /**
//  * 设备单选按钮监听器
//  */
// public class DeviceListener implements ActionListener {
//     JFrame frame;
//     private Device device;
//     /**
//      * 设备id
//      */
//     private static String selectedPhoneId;
//
//     private static String selectedSimpleId;
//
//     /**
//      * 设备的宽度(像素)
//      */
//     private static int width;
//     /**
//      * 设备的高度(像素)
//      */
//     private static int height;
//
//     public DeviceListener(JFrame frame) {
//         this.frame = frame;
//     }
//
//     public DeviceListener(JFrame frame, Device device) {
//         this.frame = frame;
//         this.device = device;
//     }
//
//     public static String getSelectedPhoneId() {
//         return selectedPhoneId;
//     }
//
//     public static String getSelectedSimpleId() {
//         return selectedSimpleId;
//     }
//
//     public static int getWidth() {
//         return width;
//     }
//
//     public static int getHeight() {
//         return height;
//     }
//
//
//     @Override
//     public void actionPerformed(ActionEvent e) {
//         // 获取触发事件的按钮
//         AbstractButton button = (AbstractButton) e.getSource();
//         // 输出按钮中的文本
//         String buttonText = button.getText();
//         System.out.println();
//         System.out.println("你选择了:" + buttonText);
//         frame.setTitle(buttonText);
//         if (device != null) {
//             // System.out.println();
//             // 从设备对象获取设备id
//             System.out.println("从设备对象获取设备id");
//             selectedPhoneId = device.getId();
//             // System.out.println("phoneId = " + selectedPhoneId);
//             selectedSimpleId=device.getSimpleId();
//
//             System.out.println("selectedPhoneId = " + selectedPhoneId);
//             System.out.println("selectedSimpleId = " + selectedSimpleId);
//             // System.out.println();
//         }
//         // 执行adb命令
//         String run = CmdRun.run("adb -s " + selectedPhoneId + " shell wm size").trim();
//         // 打印adb命令结果
//         System.out.println("run =" + run);
//         // int width = 0;
//         // int height = 0;
//         if (run.startsWith("Physical size")) {
//             String flag = "Physical size: ";
//             // 截取出屏幕宽度，高度
//             String widthStr = run.substring(run.indexOf(flag) + flag.length(), run.lastIndexOf("x"));
//             String heightStr = run.substring(run.lastIndexOf("x") + "x".length());
//
//             if (widthStr.matches("[0-9]+")) {
//                 // 设置屏幕宽度到
//                 DeviceListener.width = Integer.parseInt(widthStr);
//             }
//             if (heightStr.matches("[0-9]+")) {
//                 DeviceListener.height = Integer.parseInt(heightStr);
//             }
//         }
//     }
// }