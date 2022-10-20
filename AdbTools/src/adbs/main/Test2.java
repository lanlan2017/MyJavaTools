package adbs.main;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Test2 {
    public static void main(String[] args) {
        JButton login = new JButton();
        URL url = Test2.class.getClassLoader().getResource("方向-左.png");
        ImageIcon imageIcon = new ImageIcon(url);
        //设置图片的大小
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(imageIcon.getIconWidth(), imageIcon.getIconHeight(), Image.SCALE_DEFAULT));
        // login.setMaximumSize(new Dimension(16, 16));
        login.setIcon(imageIcon);
        //不绘制边框
        login.setBorderPainted(false);
        //设置边框为空
        login.setBorder(null);
        login.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout());
        frame.add(login);
        frame.add(new JButton());


        frame.setSize(200, 200);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
