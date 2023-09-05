package adbs.main;

import javax.swing.*;
import java.awt.*;

public class Test2 {
    public static void main(String[] args) {
        // JButton login = new JButton();
        // URL url = Test2.class.getClassLoader().getResource("方向-左.png");
        // ImageIcon imageIcon = new ImageIcon(url);
        // //设置图片的大小
        // imageIcon.setImage(imageIcon.getImage().getScaledInstance(imageIcon.getIconWidth(), imageIcon.getIconHeight(), Image.SCALE_DEFAULT));
        // // login.setMaximumSize(new Dimension(16, 16));
        // login.setIcon(imageIcon);
        // //不绘制边框
        // login.setBorderPainted(false);
        // //设置边框为空
        // login.setBorder(null);
        // login.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        // JFrame frame = new JFrame();
        // frame.setLayout(new FlowLayout());
        // frame.add(login);
        // frame.add(new JButton());
        //
        //
        // frame.setSize(200, 200);
        // frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // frame.pack();
        // frame.setVisible(true);


        // test1();
        // test2();
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gs = ge.getScreenDevices();
        for (int j = 0; j < gs.length; j++) {
            GraphicsDevice gd = gs[j];
            GraphicsConfiguration[] gc = gd.getConfigurations();
            for (int i = 0; i < gc.length; i++) {
                JFrame f = new JFrame(gs[j].getDefaultConfiguration());
                Canvas c = new Canvas(gc[i]);
                Rectangle gcBounds = gc[i].getBounds();
                int xoffs = gcBounds.x;
                int yoffs = gcBounds.y;
                f.getContentPane().add(c);

                f.setLocation((i * 50) + xoffs, (i * 60) + yoffs);

                // f.show();
                f.setVisible(true);
            }
        }


    }

    private static void test2() {
        String str = "com.sankuai.meituan.takeoutnew  美团外卖";
        str = str.substring(0, str.indexOf(" "));
        System.out.println("|" + str + "|");
        System.out.println(str.length());
    }

    private static void test1() {
        String input = "          inet addr:192.168.0.103  Bcast:192.168.0.255  Mask:255.255.255.0";
        String output = getIfconfigIp(input);
        System.out.println("output =|" + output + "|");
    }

    private static String getIfconfigIp(String input) {
        return input.substring(input.indexOf("addr:") + "addr:".length(), input.indexOf("Bcast:")).trim();
    }
}
