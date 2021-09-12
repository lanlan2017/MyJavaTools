package blue.ocr2;

import blue.ocr2.baidu.BaiduOcrRunable;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class OCR2Form {
    static {
        // 设置外观
        FlatLightLaf.setup();
    }

    private static OCR2Form instance = new OCR2Form();
    private JFrame frame;
    private JPanel rootPanel;
    private JPanel toolPenal;
    private JLabel moveLable;
    private JButton screenshotButton;
    private JButton OcrButton;
    private JComboBox firstComboBox;
    private JButton exitButton;
    private JButton cancelScreenshotButton;


    // 鼠标按下的坐标
    Point mousePressedPoint = new Point();

    // public OCR2Form() {
    private OCR2Form() {
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        screenshotButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 截屏之前先隐藏窗体
                // ScreenShotWindow.getInstance().setVisible(false);
                // 再次截屏
                ScreenShotWindow.getInstance().screenshotAgain();
                // 显示窗口
                ScreenShotWindow.getInstance().setVisible(true);
            }
        });
        cancelScreenshotButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 不显示截屏窗口
                ScreenShotWindow.getInstance().setVisible(false);
            }
        });
        OcrButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 使用百度文字识别
                BaiduOcrRunable.startBaiduOCR();
            }
        });
        moveLable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                mousePressedPoint.x = e.getX();
                mousePressedPoint.y = e.getY();
            }
        });
        moveLable.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                // Point point = ToolsWindow.getInstance().getLocation();
                // Point point = ToolsWindow.getInstance().getLocation();
                Point point = frame.getLocation();
                // 计算位置
                // ToolsWindow.getInstance().setLocation(point.x + e.getX() - (int) mousePressedPoint.getX(), point.y + e.getY() - (int) mousePressedPoint.getY());
                frame.setLocation(point.x + e.getX() - (int) mousePressedPoint.getX(), point.y + e.getY() - (int) mousePressedPoint.getY());
            }
        });
    }

    public static OCR2Form getInstance() {
        return instance;
    }

    public JFrame getFrame() {
        return frame;
    }

    public JButton getOcrButton() {
        return OcrButton;
    }

    public static void main(String[] args) {
        // OCR2Form ocr2Form = new OCR2Form();
        OCR2Form ocr2Form = OCR2Form.getInstance();

        JFrame frame = new JFrame("OCR2Form");
        ocr2Form.frame = frame;
        frame.setContentPane(ocr2Form.rootPanel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 不显示标题栏，最小化，关闭按钮
        frame.setUndecorated(true);
        // 永远置顶
        frame.setAlwaysOnTop(true);
        frame.pack();
        frame.setVisible(true);
    }
}
