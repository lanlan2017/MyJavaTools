package blue.ocr2;

import blue.ocr2.baidu.BaiduOcrRunable;
import blue.ocr2.combox.BookMarkJComBox;
import blue.ocr2.combox.MarkdownJComBox;
import blue.ocr2.event.buttons.CancelSstButtonSetting;
import blue.ocr2.event.buttons.ExitButtonSetting;
import blue.ocr2.event.buttons.OcrButtonSetting;
import blue.ocr2.event.buttons.SstButtonSetting;
import blue.ocr2.kuiajiejian.ShortcutKeyRegister;
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
    private JButton ocrButton;
    private JComboBox firstComboBox;
    private JButton exitButton;
    private JButton cancelScreenshotButton;
    private JToolBar toolBar;

    /**
     * 二级选择框
     */
    private JComboBox<String> subCombox;

    // // 鼠标按下的坐标
    Point mousePressedPoint = new Point();

    private OCR2Form() {
        settings();
        // new MoveLableSetting(frame, moveLable);

        firstComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                // 如果是选中的话
                if (ItemEvent.SELECTED == e.getStateChange()) {
                    // 获取列表成员
                    String item = e.getItem().toString();
                    switch (item) {
                        // 触发事件的选项
                        case "markdown":
                            System.out.println("markdown模式");
                            selectMarkdown();
                            break;
                        // 触发事件的选项
                        case "书签":
                            System.out.println("书签模式");
                            selectBookMark();
                            break;
                        default:
                            System.out.println("不格式化");
                            selectDefault();
                            break;
                    }
                }
            }
        });
    }

    private void settings() {
        // 快捷键注册器
        ShortcutKeyRegister keyRegister = new ShortcutKeyRegister(rootPanel);
        new ExitButtonSetting(exitButton, keyRegister);
        new SstButtonSetting(screenshotButton, keyRegister);
        new CancelSstButtonSetting(cancelScreenshotButton, keyRegister);
        new OcrButtonSetting(ocrButton, keyRegister);
        moveLableSetting();
    }

    private void selectMarkdown() {
        System.out.println("使用默认的 markdown 格式化器");
        System.out.println("toolBar.getComponentCount() = " + toolBar.getComponentCount());
        this.subCombox = MarkdownJComBox.getInstance().getComboBox();
        repait();
        // addToolBarButtons();
        // settings();
        // frame.pack();
        // rootPanel.pack();
        // 设置默认的格式器
        BaiduOcrRunable.setFormatter(MarkdownJComBox.defaultFormatter());
    }

    private void repait() {
        while (toolBar.getComponentCount() > 4) {
            // 移除最后一个元素(exitButton)
            toolBar.remove(toolBar.getComponentCount() - 1);
            System.out.println("移除");
        }
        if (subCombox != null) {
            toolBar.add(subCombox);
        }
        // 加会exitButton
        toolBar.add(exitButton);
        frame.pack();
    }

    private void selectBookMark() {
        System.out.println("使用默认的 书签 格式化器");
        // 设置第二个工具条
        // ToolBar.getInstance().setSubCombox(BookMarkJComBox.getInstance().getComboBox());
        // toolBar.setSubCombox
        // this.subCombox = BookMarkJComBox.getInstance().getComboBox();
        System.out.println("toolBar.getComponentCount() = " + toolBar.getComponentCount());
        this.subCombox = BookMarkJComBox.getInstance().getComboBox();
        repait();
        // settings();
        frame.pack();
        // 重绘工具条
        // ToolBar.getInstance().repaintToolBar();
        // 设置默认的格式化器
        BaiduOcrRunable.setFormatter(BookMarkJComBox.defaultFormatter());
    }
    private void selectDefault() {
        this.subCombox=null;
        // ToolBar.getInstance().repaintToolBar();
        while (toolBar.getComponentCount() > 4) {
            // 移除最后一个元素(exitButton)
            toolBar.remove(toolBar.getComponentCount() - 1);
            System.out.println("移除");
        }
        // 加会exitButton
        toolBar.add(exitButton);
        // settings();
        frame.pack();
        // 设置默认的格式化器
        BaiduOcrRunable.setFormatter(null);
    }

    private void moveLableSetting() {
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

    public JPanel getRootPanel() {
        return rootPanel;
    }

    public JButton getOcrButton() {
        return ocrButton;
    }

    public static void main(String[] args) {
        OCR2Form ocr2Form = OCR2Form.getInstance();
        JFrame frame = new JFrame("OCR2Form");
        ocr2Form.frame = frame;
        frame.setContentPane(ocr2Form.rootPanel);

        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 不显示标题栏，最小化，关闭按钮
        frame.setUndecorated(true);
        // 永远置顶
        frame.setAlwaysOnTop(true);
        // 最佳大小显示
        frame.pack();
        // 显示窗体
        frame.setVisible(true);
    }
}
