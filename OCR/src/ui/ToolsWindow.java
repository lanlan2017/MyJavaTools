package ui;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.UnsupportedEncodingException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JToolBar;
import javax.swing.JWindow;

import ocr.baidu.BaiduOcrRunable;
import ocr.baidu.formatter.impl.MdCodeBlockSqlFormatter;
import ocr.baidu.formatter.impl.MdCodeInLineFormatter;
import ocr.baidu.formatter.impl.PdfBookmarkCmdFormatter;

/*
 * 操作窗口
 */
public class ToolsWindow extends JWindow {
    // 单例模式,线程安全 定义开始#############
    private static final ToolsWindow instance = new ToolsWindow(0, 0);

    /**
     * 获取工具条窗体的引用.
     *
     * @return
     */
    public static ToolsWindow getInstance() {
        return instance;
    }

    private ToolsWindow(int x, int y) {
        this.init();
        this.setLocation(x, y);
        this.pack();
        // 永远显示在其他程序上方
        this.setAlwaysOnTop(true);
        // 显示窗体
        this.setVisible(true);
    }

    // 单例模式 定义结束 #################
    private static final long serialVersionUID = 1L;
    // 鼠标按下的坐标
    Point mousePressedPoint = new Point();
    // 文字识别按钮的引用
    private JButton baiduOCRButton = null;

    /**
     * 获取工具条窗体中文字识别按钮的引用。
     *
     * @return
     */
    public JButton getBaiduOCRButton() {
        return baiduOCRButton;
    }

    /**
     * 初始化工具条
     */
    private void init() {
        this.setLayout(new BorderLayout());
        //byte[] label;
        //String str = "";
        //try {
        //    label = "Java 截图".getBytes("gbk");
        //    str = new String(label, "gbk");
        //} catch (UnsupportedEncodingException e) {
        //    e.printStackTrace();
        //}
        //JToolBar toolBar = new JToolBar(str);
        //JToolBar toolBar = new JToolBar("Java 截图");
        addButtons(toolBar);
        this.add(toolBar, BorderLayout.NORTH);
    }

    /**
     * 在工具条上添加各种按钮.
     *
     * @param toolBar 工具条.
     */
    private void addButtons(JToolBar toolBar) {
        // 添加 移动 标签
        addNotMoveLabel(toolBar);
        // 添加 截屏 按钮
        addStartScreenShotButton(toolBar);
        // 添加 取消截屏 按钮
        addCancelSreenShotButton(toolBar);
        // 添加 文字识别 按钮
        addBaiduOCRButton(toolBar);
        // 添加 格式化器 选择框
        addFormatterJComboBox(toolBar);
        // 添加 退出 按钮
        addExitButton(toolBar);
    }

    /**
     * 在工具条上添加取消截屏按钮.
     *
     * @param toolBar
     */
    private void addExitButton(JToolBar toolBar) {
        // 关闭按钮
        JButton closeButton = new JButton("退出");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        toolBar.add(closeButton);
    }

    /**
     * 在工具条上添加文字识别按钮.
     *
     * @param toolBar 工具条
     */
    private void addBaiduOCRButton(JToolBar toolBar) {
        // 百度识图按钮
        JButton baiduOCRButton = new JButton("OCR");
        baiduOCRButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 使用百度文字识别
                BaiduOcrRunable.startBaiduOCR();
            }
        });
        this.baiduOCRButton = baiduOCRButton;
        toolBar.add(baiduOCRButton);
        // addFormatterJComboBox(toolBar);
    }

    private void addFormatterJComboBox(JToolBar toolBar) {
        String items[] =
                {"不格式化", "Markdown", "SQL代码", "书签x.x.x", "书签x.xx", "书签x.xx.x", "书签xx.x",
                        "书签xx.x.x", "书签xx.xx", "书签xx.xx.x",};
        // 下拉选择框
        JComboBox<String> comboBox = new JComboBox<String>(items);
        comboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                // 如果是选中的话
                if (ItemEvent.SELECTED == e.getStateChange()) {
                    // 获取列表成员
                    String item = e.getItem().toString();
                    switch (item) {
                        // 触发事件的选项
                        case "Markdown":
                            System.out.println("格式化为:MarkDown");
                            BaiduOcrRunable
                                    .setFormatter(new MdCodeInLineFormatter());
                            break;
                        // 触发事件的选项
                        case "SQL代码":
                            System.out.println("格式化为:SQL Markwodn代码块");
                            BaiduOcrRunable.setFormatter(
                                    new MdCodeBlockSqlFormatter());
                            break;
                        // 如果选中的是书签
                        case "书签x.x.x":
                            System.out.println("格式化为:书签x.x.x");
                            BaiduOcrRunable.setFormatter(
                                    new PdfBookmarkCmdFormatter("111"));
                            break;
                        case "书签x.xx":
                            System.out.println("格式化为:书签x.xx");
                            BaiduOcrRunable.setFormatter(
                                    new PdfBookmarkCmdFormatter("12"));
                            break;
                        case "书签x.xx.x":
                            System.out.println("格式化为:书签x.xx.x");
                            BaiduOcrRunable.setFormatter(
                                    new PdfBookmarkCmdFormatter("121"));
                            break;
                        case "书签xx.x":
                            System.out.println("格式化为:书签xx.x");
                            BaiduOcrRunable.setFormatter(
                                    new PdfBookmarkCmdFormatter("21"));
                            break;
                        case "书签xx.x.x":
                            System.out.println("格式化为:书签xx.x.x");
                            BaiduOcrRunable.setFormatter(
                                    new PdfBookmarkCmdFormatter("211"));
                            break;
                        case "书签xx.xx":
                            System.out.println("格式化为:书签xx.xx");
                            BaiduOcrRunable.setFormatter(
                                    new PdfBookmarkCmdFormatter("22"));
                            break;
                        case "书签xx.xx.x":
                            System.out.println("格式化为:书签xx.xx.x");
                            BaiduOcrRunable.setFormatter(
                                    new PdfBookmarkCmdFormatter("221"));
                            break;
                        default:
                            System.out.println("不格式化");
                            BaiduOcrRunable.setFormatter(null);
                            break;
                    }
                }
            }
        });
        toolBar.add(comboBox);
    }

    /**
     * 在工具条上添加取消截屏按钮
     *
     * @param toolBar
     */
    private void addCancelSreenShotButton(JToolBar toolBar) {
        // 添加取消文字识别按钮
        JButton cancelButton = new JButton("取消截屏");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ScreenShotWindow.getInstance().setVisible(false);// 不显示截屏窗口
                // 移动窗体到最左边
                ToolsWindow.this.setLocation(0, 0);
            }
        });
        toolBar.add(cancelButton);
    }

    /**
     * 在工具条上添加截屏按钮.
     *
     * @param toolBar 工具条.
     */
    private void addStartScreenShotButton(JToolBar toolBar) {
        // 添加取消文字识别按钮
        JButton startButton = new JButton("截屏");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 再次截屏
                ScreenShotWindow.getInstance().screenshotAgain();
                // 显示窗口
                ScreenShotWindow.getInstance().setVisible(true);
            }
        });
        toolBar.add(startButton);
    }

    /**
     * 在工具条上添加`移动`标签,鼠标个按住该标签来移动按钮。
     *
     * @param toolBar 工具条
     */
    private void addNotMoveLabel(JToolBar toolBar) {
        JLabel label = new JLabel("移动");
        // 监听事件,移动窗体
        label.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                mousePressedPoint.x = e.getX();
                mousePressedPoint.y = e.getY();
            }
        });
        label.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                Point point = instance.getLocation();
                // 计算位置
                instance.setLocation(
                        point.x + e.getX() - (int) mousePressedPoint.getX(),
                        point.y + e.getY() - (int) mousePressedPoint.getY());
            }
        });
        // 设置工具条不可拖动
        toolBar.add(label);
        toolBar.setFloatable(false);
    }
    // public static void main(String[] args)
    // {
    // System.out.println(
    // ToolsWindow.getInstance() == ToolsWindow.getInstance());
    // }
}