package adbs.main.ui.config;

import javax.swing.*;
import java.awt.*;

/**
 * 预设的字体Front
 */
public class Fonts {
    // 要创建衬线，大小为10的纯字体
    // public static Font f1 = new Font(Font.SERIF, Font.PLAIN, 10);
    private static Font f1 = new Font(Font.SERIF, Font.PLAIN, 15);
    // 要创建SansSerif，大小为10的粗体字体
    // public static Font f2 = new Font(Font.SANS_SERIF, Font.BOLD, 10);
    private static Font f2 = new Font(Font.SANS_SERIF, Font.BOLD, 15);
    // 要创建对话框，大小为15的粗体字体
    private static Font f3 = new Font(Font.DIALOG, Font.BOLD, 15);
    // 要创建对话框输入，粗体和斜体字体大小为15
    private static Font f4 = new Font(Font.DIALOG_INPUT, Font.BOLD | Font.ITALIC, 15);
    private static Font f5 = new Font(Font.MONOSPACED, Font.BOLD | Font.ITALIC, 15);

    /**
     * 英文等宽字体Consolas,14号
     */
    public static Font Consolas_BOLD_14 = new Font("Consolas", Font.BOLD, 14);
    /**
     * 英文等宽字体Consolas,12号,加粗。
     */
    public static Font Consolas_BOLD_12 = new Font("Consolas", Font.BOLD, 12);
    /**
     * 英文等宽字体Consolas,14号
     */
    public static Font Consolas_PLAIN_14 = new Font("Consolas", Font.PLAIN, 14);
    /**
     * 英文等宽字体Consolas,12号
     */
    public static Font Consolas_PLAIN_12 = new Font("Consolas", Font.PLAIN, 12);
    /**
     * 英文等宽字体Consolas,13号
     */
    public static Font Consolas_PLAIN_13 = new Font("Consolas", Font.PLAIN, 13);
    /**
     * Cascadia 是微软出品的一款开源等宽字体，Windows Terminal 中的默认字体就是它。
     * 14号
     */
    public static Font Cascadia_PLAIN_14 = new Font("Cascadia", Font.PLAIN, 14);
    /**
     * Cascadia 微软开源等宽字体，Windows Terminal默认字体。
     * 12号
     */
    public static Font Cascadia_PLAIN_12 = new Font("Cascadia", Font.PLAIN, 12);
    /**
     * Cascadia 微软开源等宽字体，Windows Terminal默认字体。
     * 12号,加粗
     */
    public static Font Cascadia_BOLD_12 = new Font("Cascadia", Font.BOLD, 12);

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
