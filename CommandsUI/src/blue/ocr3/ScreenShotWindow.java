package blue.ocr3;

import blue.commands.ui.MainFrom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;

/*
 * 截图矩形窗口
 */
public class ScreenShotWindow extends JWindow {
    private static final long serialVersionUID = 1L;
    // 鼠标按下的坐标
    Point mousePressedPoint = new Point();
    // 鼠标拖动时的坐标
    Point mouseDraggedPoint = new Point();

    private BufferedImage image = null;
    private BufferedImage tempImage = null;
    private BufferedImage saveImage = null;
    // 保存屏幕宽度
    static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    // 单例模式定义开始
    private static final ScreenShotWindow instance = new ScreenShotWindow();

    // 获取单例类
    public static ScreenShotWindow getInstance() {
        return instance;
    }

    // 构造函数
    private ScreenShotWindow() {
        // 截屏窗体永远在其他程序上显示,这样才能在上面画出要截取的部分
        this.setAlwaysOnTop(true);
        // 截取屏幕保存图片
        createScreenCapture();
        // 注册鼠标事件处理函数
        addMouseListenerInThis();
        addMouseMotionListenerInThis();
        // 注册快捷键
        // HotkeyRegister.getInstance();
    }
    // 单例模式结束.

    /**
     * 获取截屏生成的图片.
     *
     * @return 截屏得到的图片.
     */
    public BufferedImage getSaveImage() {
        return saveImage;
    }

    /**
     * 注册鼠标事件处理程序
     */
    private void addMouseListenerInThis() {
        // 设置鼠标监听事件
        this.addMouseListener(new MouseAdapter() {
            // 保存工具条宽度
            Dimension ToolsWindowSize;

            // 鼠标按下
            @Override
            public void mousePressed(MouseEvent e) {
                // 鼠标按下时，表明截屏开始，记录开始点坐标，并隐藏操作窗口
                mousePressedPoint.setLocation(e.getX(), e.getY());
                // 隐藏工具面板
                // ToolsWindow.getInstance().setVisible(false);
                // OCR3Form.getInstance().getFrame().setVisible(false);
                // OCR3Form.getInstance().getFrame().setVisible(false);
                MainFrom.getInstance().getFrame().setVisible(false);
            }

            // 鼠标松开
            @Override
            public void mouseReleased(MouseEvent e) {
                // 获取鼠标松开的坐标
                int x = e.getX();
                int y = e.getY();
                // 保证工具条不超出屏幕之外
                // noOverflowScreen(e, x, y);
                // 显示窗体
                // ToolsWindow.getInstance().setVisible(true);
                // // 让窗体置顶,这样才能操作
                // ToolsWindow.getInstance().toFront();
                // OCR3Form.getInstance().getFrame().setVisible(true);
                // OCR3Form.getInstance().getFrame().toFront();
                MainFrom.getInstance().getFrame().setVisible(true);
                MainFrom.getInstance().getFrame().toFront();
                // OCR3Form.getFrame().setVisible(true);
                // OCR3Form.getFrame().toFront();
            }
            //
            // /**
            //  * 保证工具栏不超出屏幕之外。
            //  * @param e 鼠标事件
            //  * @param x 横坐标
            //  * @param y 纵坐标
            //  */
            // public void noOverflowScreen(MouseEvent e, int x, int y) {
            //     // ToolsWindowSize = ToolsWindow.getInstance().getSize();
            //     boolean overX = x + ToolsWindowSize.getWidth() + 1 > screenSize.getWidth();
            //     boolean overY = y + ToolsWindowSize.getHeight() + 1 > screenSize.getHeight();
            //     // 如果同时超出了宽度和高度 11
            //     if (overX && overY) {
            //         ToolsWindow.getInstance().setLocation((int) (x - ToolsWindowSize.getWidth()), (int) (y - ToolsWindowSize.getHeight()));
            //     }
            //     // 10或者01宽度超出了屏幕宽度，或者高度，这里不能匹配到同时的情况，如果同时的话会走上面的分支
            //     else if (overX || overY) {
            //         // 如果是高度超出 01
            //         if (overY) {
            //             // 显示在屏幕上
            //             ToolsWindow.getInstance().setLocation(x, (int) (y - ToolsWindowSize.getHeight()));
            //         }
            //         // 如果不是高度的话，那只能是宽度了 10
            //         else {
            //             ToolsWindow.getInstance().setLocation((int) (x - ToolsWindowSize.getWidth()), y);
            //         }
            //     }
            //     // 如果都没有超出宽度 00
            //     else {
            //         ToolsWindow.getInstance().setLocation(e.getX(), e.getY());
            //     }
            // }


        });
    }

    /**
     * 注解鼠标移动和拖动事件处理函数
     */
    private void addMouseMotionListenerInThis() {
        // 鼠标移动和拖动监听事件处理函数
        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                // 鼠标拖动时，记录坐标并重绘窗口
                mouseDraggedPoint.setLocation(e.getX(), e.getY());
                // 临时图像，用于缓冲屏幕区域放置屏幕闪烁
                Image tempImage2 = createImage(ScreenShotWindow.this.getWidth(), ScreenShotWindow.this.getHeight());
                Graphics g = tempImage2.getGraphics();
                g.drawImage(tempImage, 0, 0, null);
                int x = Math.min(mousePressedPoint.x, mouseDraggedPoint.x);
                int y = Math.min(mousePressedPoint.y, mouseDraggedPoint.y);
                int width = Math.abs(mouseDraggedPoint.x - mousePressedPoint.x) + 1;
                int height = Math.abs(mouseDraggedPoint.y - mousePressedPoint.y) + 1;

                // 设置截屏矩形边框的颜色
                g.setColor(Color.RED);
                // 减1加1都了防止图片矩形框覆盖掉
                g.drawRect(x - 1, y - 1, width + 1, height + 1);
                // 保存图片
                saveImage = image.getSubimage(x, y, width, height);
                // 绘制图片
                g.drawImage(saveImage, x, y, null);
                //
                ScreenShotWindow.getInstance().getGraphics().drawImage(tempImage2, 0, 0, ScreenShotWindow.getInstance());
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        RescaleOp ro = new RescaleOp(0.8f, 0, null);
        tempImage = ro.filter(image, null);
        // 在当前窗口上画图
        g.drawImage(tempImage, 0, 0, this);
        // 显示垃圾回收
        System.gc();
    }

    /**
     * 截取当前整个屏幕，并设置到图片字段image中
     */
    public void createScreenCapture() {
        // 设置窗口显示大小为整个屏幕的大小
        this.setBounds(0, 0, screenSize.width, screenSize.height);
        Robot robot;
        try {
            robot = new Robot();
            // 截取整个屏幕,保存在BufferedImage中
            image = robot.createScreenCapture(new Rectangle(0, 0, screenSize.width, screenSize.height));
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    /**
     * 再次截屏.
     */
    public void screenshotAgain() {
        // ToolsWindow.getInstance().setVisible(false);
        // 截屏之前先隐藏工具窗体
        // OCR3Form.getInstance().getFrame().setVisible(false);
        // OCR3Form.getFrame().setVisible(false);
        MainFrom.getInstance().getFrame().setVisible(false);
        // 截取屏幕
        createScreenCapture();
        // 显示窗口
        this.setVisible(true);
    }
}