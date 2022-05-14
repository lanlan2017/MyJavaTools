package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.URL;

/**
 * 中文系统托盘弹出菜单不乱码。
 */
public class SystemTrayCH {

    public static void main(String[] args) throws FileNotFoundException {
        // 应用主窗口
        JFrame frame = new JFrame();
        // 启动程式时就设置系统托盘
        initSystemTrayUTF8(frame);

        //取消默认关闭事件，自定义使其放在右下角的系统托盘
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            // @SneakyThrows
            @Override
            public void windowClosing(WindowEvent e) {
                // 应用主窗口不可见 != 应用退出
                frame.setVisible(false);
                // systemTrayAdd(trayIcon);
            }
        });
        frame.setBounds(500, 500, 200, 200);
        frame.setVisible(true);
    }

    private static void initSystemTrayUTF8(JFrame frame) {
        //使用JDialog 作为JPopupMenu载体
        JDialog jDialog = new JDialog();
        //关闭JDialog的装饰器
        jDialog.setUndecorated(true);
        //jDialog作为JPopupMenu载体不需要多大的size
        jDialog.setSize(1, 1);

        //创建JPopupMenu
        //重写firePopupMenuWillBecomeInvisible
        //消失后将绑定的组件一起消失
        JPopupMenu jPopupMenu = new JPopupMenu() {
            @Override
            public void firePopupMenuWillBecomeInvisible() {
                jDialog.setVisible(false);
                System.out.println("JPopupMenu不可见时绑定载体组件jDialog也不可见");
            }
        };
        jPopupMenu.setSize(100, 30);

        //添加菜单选项
        // JMenuItem exit = new JMenuItem(getUTF8String("退出"));
        JMenuItem exit = new JMenuItem("退出");
        exit.addActionListener(e -> {
            System.out.println("点击了退出选项");
            System.exit(0);
        });
        // JMenuItem showMainFrame = new JMenuItem(getUTF8String("显示主窗体"));
        JMenuItem showMainFrame = new JMenuItem("显示主窗体");
        showMainFrame.addActionListener(e -> {
            System.out.println("显示主窗体");
            //显示窗口
            frame.setVisible(true);
        });

        jPopupMenu.add(showMainFrame);
        jPopupMenu.add(exit);

        URL resource = SystemTrayCH.class.getResource("/工具_16.png");
        // 创建托盘图标
        Image image = Toolkit.getDefaultToolkit().createImage(resource);
        // 创建系统托盘图标
        TrayIcon trayIcon = new TrayIcon(image);
        // 自动调整系统托盘图标大小
        trayIcon.setImageAutoSize(true);

        // 给托盘图标添加鼠标监听
        trayIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                //左键点击
                if (e.getButton() == 1) {
                    //显示窗口
                    frame.setVisible(true);
                } else if (e.getButton() == 3 && e.isPopupTrigger()) {
                    // 右键点击弹出JPopupMenu绑定的载体以及JPopupMenu
                    jDialog.setLocation(e.getX() + 5, e.getY() - 5 - jPopupMenu.getHeight());
                    // 显示载体
                    jDialog.setVisible(true);
                    // 在载体的0,0处显示对话框
                    jPopupMenu.show(jDialog, 0, 0);
                }
            }
        });
        // 添加托盘图标到系统托盘
        systemTrayAdd(trayIcon);
    }

    /**
     * 添加托盘图标到系统托盘中。
     *
     * @param trayIcon 系统托盘图标。
     */
    private static void systemTrayAdd(TrayIcon trayIcon) {
        // 将托盘图标添加到系统的托盘实例中
        SystemTray tray = SystemTray.getSystemTray();
        try {
            tray.add(trayIcon);
        } catch (AWTException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 字符串转UTF-8字符串
     *
     * @param str 要转换的字符串
     * @return UTF-8编码的字符串
     */
    private static String getUTF8String(String str) {
        try {
            return new String(str.getBytes("UTF-8"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
