// package adbs.test.auto;
//
// import adbs.test.AdbDi;
// import adbs.test.Device;
// import adbs.test.auto.run.PythonCloseableRun;
// import com.formdev.flatlaf.FlatLightLaf;
// import tools.swing.button.AbstractButtons;
//
// import javax.swing.*;
// import java.awt.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
//
// public class AutoButton {
//
//     private static JLabel output;
//     private static FlowLayout flowLayout;
//
//     static {
//         // 设置外观,先设置外观，再创建UI。
//         // 为了保证创建UI时，已经设置好外观，设置外观的代码最好写在静态块中，
//         // 并且把这个静态块写在类定义的最前面。
//         FlatLightLaf.setup();
//     }
//
//     private static JFrame frame;
//
//     private static JPanel contentPane;
//     private static final String dirPath = "AdbToolsPythons\\";
//
//     public static void main(String[] args) {
//         // 创建窗体
//         frame = new JFrame();
//         // 创建窗体的内容面板
//         contentPane = new JPanel();
//         // 设置窗体的内容面板
//         frame.setContentPane(contentPane);
//
//         // 窗体使用箱型布局,垂直排列
//         frame.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
//
//         // 创建设备面板
//         JPanel devicesPanel = new AdbDi(frame).createDevicesPanel();
//         flowLayout = new FlowLayout(FlowLayout.LEFT, 0, 0);
//
//
//         // 设备面板设置布局管理器
//         devicesPanel.setLayout(flowLayout);
//         // 添加到窗体中
//         frame.add(devicesPanel);
//
//
//         output = new JLabel();
//
//         JButton autoBtn = new JButton("auto");
//         String name = "auto";
//         String chName = "自动";
//         autoBtn.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 JButton source = (JButton) e.getSource();
//                 // String msg = source.getText();
//                 // String msg = source.getText();
//                 // String pythonDir = dirPath + msg + "\\";
//                 // String pythonFile = pythonDir + "_" + Device.getBrand() + ".py";
//                 // System.out.println("pythonFile = " + pythonFile);
//                 // new Thread(new PythonCloseableRun(msg, pythonFile, output)).start();
//                 String pythonDir = dirPath + name + "\\";
//                 String pythonFile = pythonDir + "_" + Device.getBrand() + ".py";
//                 System.out.println("pythonFile = " + pythonFile);
//                 new Thread(new PythonCloseableRun(chName, pythonFile, output)).start();
//             }
//         });
//         AbstractButtons.setJButtonMargin(autoBtn);
//
//         frame.add(autoBtn);
//         frame.add(output);
//
//         frameDefautSetting();
//     }
//
//     /**
//      * 默认的frame设置
//      */
//     private static void frameDefautSetting() {
//         // 永远置顶
//         frame.setAlwaysOnTop(true);
//         // 设置关闭按钮功能
//         frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//         // 显示窗体
//         frame.setVisible(true);
//         // 调整窗体到最佳大小
//         frame.pack();
//     }
// }
