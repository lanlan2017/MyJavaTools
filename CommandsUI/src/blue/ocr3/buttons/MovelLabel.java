// package blue.ocr3.buttons;
//
// // import blue.ocr3.OCR3Form;
//
// import blue.commands.ui.MainFrom;
//
// import javax.swing.*;
// import java.awt.*;
// import java.awt.event.MouseAdapter;
// import java.awt.event.MouseEvent;
// import java.awt.event.MouseMotionAdapter;
//
// public class MovelLabel {
//     private static MovelLabel instance = new MovelLabel();
//     private JLabel moveLable;
//     // // 鼠标按下的坐标
//     Point mousePressedPoint = new Point();
//
//     private MovelLabel() {
//         moveLable = new JLabel("移动");
//         moveLable.addMouseListener(new MouseAdapter() {
//             @Override
//             public void mousePressed(MouseEvent e) {
//                 super.mousePressed(e);
//                 mousePressedPoint.x = e.getX();
//                 mousePressedPoint.y = e.getY();
//             }
//         });
//         moveLable.addMouseMotionListener(new MouseMotionAdapter() {
//             @Override
//             public void mouseDragged(MouseEvent e) {
//                 super.mouseDragged(e);
//                 // Point point = ToolsWindow.getInstance().getLocation();
//                 // Point point = ToolsWindow.getInstance().getLocation();
//                 // OCR3Form.getInstance().getFrame();
//                 // Point point = frame.getLocation();
//                 // Point point = OCR3Form.getInstance().getFrame().getLocation();
//                 // Point point = OCR3Form.getInstance().getFrame().getLocation();
//                 Point point = MainFrom.getInstance().getFrame().getLocation();
//                 // 计算位置
//                 // ToolsWindow.getInstance().setLocation(point.x + e.getX() - (int) mousePressedPoint.getX(), point.y + e.getY() - (int) mousePressedPoint.getY());
//                 // frame.setLocation(point.x + e.getX() - (int) mousePressedPoint.getX(), point.y + e.getY() - (int) mousePressedPoint.getY());
//                 // OCR3Form.getInstance().getFrame().setLocation(point.x + e.getX() - (int) mousePressedPoint.getX(), point.y + e.getY() - (int) mousePressedPoint.getY());
//                 // OCR3Form.getInstance().getFrame().setLocation(point.x + e.getX() - (int) mousePressedPoint.getX(), point.y + e.getY() - (int) mousePressedPoint.getY());
//                 MainFrom.getInstance().getFrame().setLocation(point.x + e.getX() - (int) mousePressedPoint.getX(), point.y + e.getY() - (int) mousePressedPoint.getY());
//             }
//         });
//     }
//
//     public static MovelLabel getInstance() {
//         return instance;
//     }
//
//     public JLabel getMoveLable() {
//         return moveLable;
//     }
// }
