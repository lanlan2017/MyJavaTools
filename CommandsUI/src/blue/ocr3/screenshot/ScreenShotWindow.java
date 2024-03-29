package blue.ocr3.screenshot;

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
    /**
     * 主窗体是否在截图方框旁边显示
     */
    private boolean isFrameNextToScreenshot = true;

    public boolean isFrameNextToScreenshot() {
        return isFrameNextToScreenshot;
    }

    public void setFrameNextToScreenshot(boolean frameNextToScreenshot) {
        isFrameNextToScreenshot = frameNextToScreenshot;
    }

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
        // 设置鼠标监听事件
        this.addMouseListener(new ScreenShotMouseListener());
        // 鼠标 移动和拖动监 听事件处理函数
        this.addMouseMotionListener(new ScreenShotMouseMotionListener());
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
        // 截屏之前先隐藏工具窗体
        MainFrom.getInstance().getFrame().setVisible(false);
        // 截取屏幕
        createScreenCapture();
        // 显示窗口
        this.setVisible(true);
    }

    /**
     * 监听鼠标按下，释放事件
     */
    class ScreenShotMouseListener extends MouseAdapter {
        private final JFrame frame = MainFrom.getInstance().getFrame();

        // 鼠标按下
        @Override
        public void mousePressed(MouseEvent e) {
            // 鼠标按下时，表明截屏开始，记录开始点坐标，并隐藏操作窗口
            mousePressedPoint.setLocation(e.getX(), e.getY());
            // 隐藏工具面板
            frame.setVisible(false);
        }

        // 鼠标松开
        @Override
        public void mouseReleased(MouseEvent e) {
            // 获取鼠标松开的坐标
            int x = e.getX();
            int y = e.getY();
            // 如果设置主窗体在截图方框旁边显示的话
            if(isFrameNextToScreenshot()){
                // 保证工具条不超出屏幕之外
                noOverflowScreen(x, y);
            }
            // 窗体显示在截图窗口前面
            frame.toFront();
            // 显示窗体
            frame.setVisible(true);
        }

        /**
         * 保证工具栏不超出屏幕之外。
         *
         * @param x 横坐标
         * @param y 纵坐标
         */
        public void noOverflowScreen(int x, int y) {
            int jingdu = 2;
            // 获取屏幕的宽度
            int screenWidth = screenSize.width;
            // 获取屏幕的高度
            int screenHeight = screenSize.height;
            Dimension frameSize = frame.getSize();
            // 获取窗体的宽度
            int frameWidth = frameSize.width;
            // 获取窗体的高度
            int frameHeight = frameSize.height;
            // 默认使用右下角的点横坐标作为横坐标
            int xx = Math.max(x, mousePressedPoint.x);
            // 默认使用右下角的点的纵坐标作为纵坐标
            int yy = Math.max(y, mousePressedPoint.y);

            // 当前的x坐标加上窗体的宽度是否大于屏幕的宽度
            boolean overX = xx + frameWidth + 1 > screenWidth;
            // 当前的y坐标加上窗体的高度，是否大于屏幕的高度
            boolean overY = yy + frameHeight + 1 > screenHeight;

            if (overX && overY) {
                // 使用最上方的点的纵坐标作为纵坐标，并前去窗体的高度，这样窗体讲显示在正上方
                yy = Math.min(y, mousePressedPoint.y) - frameHeight;
                yy = yy - jingdu;
                // 使用最上方的点的横坐标作为横坐标
                xx = Math.min(x, mousePressedPoint.x);
                // 如果横坐标加上窗体的宽度大于屏幕的看宽度
                if (xx + frameWidth > screenWidth) {
                    // 屏幕的宽度减去面板的宽度，得到横坐标
                    xx = screenWidth - frameWidth;
                }
                // JOptionPane.showMessageDialog(frame, "overX and overY");
            } else if (overX) {
                // 使用左侧的点作为横坐标
                xx = Math.min(x, mousePressedPoint.x);
                // 如果横坐标加上窗体的宽度大于屏幕的宽度，则有部分窗体超出屏幕外
                if (xx + frameWidth > screenWidth) {
                    // 窗体的横坐标设置为屏幕的宽度减去窗体的宽度，也就是不让窗体在横坐标超出屏幕外。
                    xx = screenWidth - frameWidth;
                }
                yy = yy + jingdu;
                // JOptionPane.showMessageDialog(frame, "overX");
            } else if (overY) {
                // 使用上面的点作为纵坐标
                yy = Math.min(y, mousePressedPoint.y);
                // 如果纵坐标加上窗体的高度大于屏幕的高度，则有部分窗体超出屏幕外
                if (yy + frameHeight > screenHeight) {
                    // 窗体的纵坐标作设置位屏幕的高度减去窗体的高度，也就是不让窗体在纵坐标超出屏幕外。
                    yy = screenHeight - frameHeight;
                    yy = yy - jingdu;
                }
                xx = xx + jingdu;
                // JOptionPane.showMessageDialog(frame, "overY");
            } else {
                xx = Math.max((xx - frameWidth), 0);
                yy = yy + jingdu;
                // JOptionPane.showMessageDialog(frame, "其他");
            }
            frame.setLocation(xx, yy);
        }
    }

    /**
     * 监听鼠标拖拽事件
     */
    class ScreenShotMouseMotionListener extends MouseMotionAdapter {
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
    }
}