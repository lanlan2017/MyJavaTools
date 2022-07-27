// package adbs.test;
//
// import adbs.ui.AdbTools;
//
// import javax.swing.*;
// import java.awt.*;
// import java.net.URL;
//
// public class IconTest {
//     public static void main(String[] args) {
//         URL returnIcoUrl = AdbTools.class.getClassLoader().getResource("方向-左.png");
//         Icon icon = new ImageIcon(returnIcoUrl);
//         // icon.getIconWidth()
//         JButton returnBtn = new JButton();
//         returnBtn.setIcon(icon);
//         // returnBtn.setSize();
//         Dimension dimension = new Dimension(icon.getIconWidth(), icon.getIconHeight());
//         returnBtn.setPreferredSize(dimension);
//         returnBtn.setMaximumSize(dimension);
//         returnBtn.setMinimumSize(dimension);
//
//         JFrame frame = new JFrame();
//         frame.setLayout(new FlowLayout());
//
//         frame.add(returnBtn);
//         frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//         frame.pack();
//         frame.setVisible(true);
//     }
// }
