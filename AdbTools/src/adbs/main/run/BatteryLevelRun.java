// package adbs.main.run;
//
// import adbs.cmd.CmdRun;
// import adbs.main.AdbTools;
//
// import javax.swing.*;
// import java.util.Locale;
//
// import adbs.tools.thread.ThreadSleep;
//
// public class BatteryLevelRun implements Runnable {
//     private static boolean stop = false;
//
//     @Override
//     public void run() {
//         // 等待2秒
//         ThreadSleep.seconds(2);
//         // String deviceId = AdbTools.device.getId();
//         // String simpleId = AdbTools.device.getSimpleId();
//         AdbTools adbTools = AdbTools.getInstance();
//         String deviceId = adbTools.getDevice().getSerial();
//         String simpleId = adbTools.getDevice().getName();
//         while (!stop) {
//             String run;
//             // run = CmdRun.run("adb -s 8BN0217B82204110 shell dumpsys battery|findstr level");
//             run = CmdRun.run("adb -s " + deviceId + " shell dumpsys battery|findstr level");
//             if (run != null && run.contains("level: ")) {
//                 run = run.substring(run.lastIndexOf("level: ") + "level: ".length());
//                 // System.out.println("---"+run+"----------");
//                 // 删除首尾的空白符（空格，换行符）
//                 run = run.trim();
//                 // System.out.println("---"+run+"----------");
//                 // System.out.println(run.matches("\\d+"));
//                 if (run.matches("^[0-9]+$")) {
//                     int level = Integer.parseInt(run);
//                     // System.out.println("level = " + level);
//                     if (level < 30) {
//                         //     System.out.println("电量:" + level + "% 无须");
//                         // } else {
//                         String message = "电量:" + level + "% 充电?";
//                         System.out.println(message);
//                         // 弹出确认对话框
//                         // int confirmDialog = JOptionPane.showConfirmDialog(null, message);
//                         // 弹出确认对话框，显示标题，显示“是，否，取消”三个按钮
//
//                         // int confirmDialog = JOptionPane.showConfirmDialog(null, message,simpleId,JOptionPane.YES_NO_CANCEL_OPTION);
//                         int confirmDialog = JOptionPane.showConfirmDialog(adbTools.getContentPane(), message, simpleId, JOptionPane.YES_NO_OPTION);
//                         switch (confirmDialog) {
//                             case JOptionPane.OK_OPTION:
//                                 System.out.println("点击 是 按钮");
//                                 if (simpleId.toLowerCase(Locale.ROOT).equals("9")) {
//                                     // 启动网络调试
//                                     CmdRun.run("adb -s 8BN0217B82204110 tcpip 5560 && adb connect 192.168.0.109:5560");
//                                 }
//
//                                 break;
//                             case JOptionPane.NO_OPTION:
//                                 System.out.println("点击 否 按钮");
//                                 break;
//                             case JOptionPane.CANCEL_OPTION:
//                                 System.out.println("点击 取消 按钮");
//                                 break;
//                         }
//                     }
//                     // System.out.println("电量:" + level + "%");
//                     JFrame frame = adbTools.getFrame();
//                     String frameTitle = frame.getTitle();
//                     // 如果原来的标题中已经有了百分号
//                     if (frameTitle.contains("%")) {
//                         System.out.println("old frameTitle = " + frameTitle);
//                         //替换其中的百分号
//                         frameTitle = frameTitle.replaceAll(":[0-9]{1,2}%", ":" + level + "%");
//                         System.out.println("new frameTitle = " + frameTitle);
//                     } else {
//                         // 在标题上加上百分号
//                         frameTitle = frameTitle + ":" + level + "%";
//                     }
//                     frame.setTitle(frameTitle);
//                 }
//             }
//             // seconds(60*5);
//             // ThreadSleep.minutes();
//             ThreadSleep.minutes(2);
//             // ThreadSleep.seconds(30);
//         }
//         // System.out.println(run);
//     }
//
//
//     // public static void main(String[] args) {
//     //     new Thread(new BatteryLevelRun()).start();
//     // }
// }