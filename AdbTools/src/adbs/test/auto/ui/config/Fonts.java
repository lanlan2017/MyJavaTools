package adbs.test.auto.ui.config;

import javax.swing.*;
import java.awt.*;

public class Fonts {
    // 要创建衬线，大小为10的纯字体
    // public static Font f1 = new Font(Font.SERIF, Font.PLAIN, 10);
    public static Font f1 = new Font(Font.SERIF, Font.PLAIN, 15);
    // 要创建SansSerif，大小为10的粗体字体
    // public static Font f2 = new Font(Font.SANS_SERIF, Font.BOLD, 10);
    public static Font f2 = new Font(Font.SANS_SERIF, Font.BOLD, 15);
    // 要创建对话框，大小为15的粗体字体
    public static Font f3 = new Font(Font.DIALOG, Font.BOLD, 15);
    // 要创建对话框输入，粗体和斜体字体大小为15
    public static Font f4 = new Font(Font.DIALOG_INPUT, Font.BOLD | Font.ITALIC, 15);
    public static Font f5 = new Font(Font.MONOSPACED, Font.BOLD | Font.ITALIC, 15);

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JLabel label1 = new JLabel("helloWorld，你好");
        label1.setFont(f1);
        JLabel label2 = new JLabel("helloWorld，你好");
        label2.setFont(f2);
        JLabel label3 = new JLabel("helloWorld，你好");
        label3.setFont(f3);
        JLabel label4 = new JLabel("helloWorld，你好");
        label4.setFont(f4);
        JLabel label5 = new JLabel("helloWorld，你好");
        label5.setFont(f5);
        // JLabel label6 = new JLabel("helloWorld，你好");
        // label6.setFont(f1);
        // JLabel label7 = new JLabel("helloWorld，你好");
        // label1.setFont(f1);

        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.setVisible(true);
        frame.add(label1);
        frame.add(label2);
        frame.add(label3);
        frame.add(label4);
        frame.add(label5);
        // frame.add(label6);
        // frame.add(label7);
        frame.pack();
    }

    //     要设置Swing组件的字体，请使用组件的setFont()方法。
    //     JButton closeButton  = new JButton("Close");
    // closeButton.setFont(f4);
}