package blue.commands.tool.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;

public class ToolUiSystemTray {
    private JFrame frame;

    private ToolUiSystemTray() {
    }

    public ToolUiSystemTray(JFrame frame) {
        this.frame = frame;
        // initSystemTray();
        initSystemTrayUTF8(this.frame);
    }

    /**
     * 初始化系统托盘
     */
    private void initSystemTray() {
        // 判断系统是否支持托盘图标
        if (SystemTray.isSupported()) {

            // // 获取托盘图标,图片请放在 当前包 下
            // URL resource = this.getClass().getResource("/com/blue/ico/草莓_16.png");
            // URL resource = this.getClass().getResource("/com/blue/ico/工具_16.png");
            URL resource = ToolUiSystemTray.class.getResource("/blue/commands/ico/工具_16.png");
            // URL resource = ToolUiSystemTray.class.getResource("/blue/commands/ico/工具_16.png");
            // 创建图标
            assert resource != null;
            ImageIcon icon = new ImageIcon(resource);
            // 创建弹出式菜单
            PopupMenu pop = new PopupMenu();

            // String str = "显示主界面";
            // String str = "显示主窗体";
            String str = "show main form";
            // 创建 显示菜单项
            MenuItem displayJFrameItem = new MenuItem(str);
            // 给 显示窗体菜单项 添加事件处理程序
            displayJFrameItem.addActionListener(e -> frame.setVisible(true));
            // 显示菜单项 添加到 弹出式菜单中
            pop.add(displayJFrameItem);

            // 创建 退出菜单项
            // String exitLabel = "退出";
            // String exitLabel = "\\u9000\\u51fa";
            String exitLabel = "exit";
            MenuItem exitItem = new MenuItem(exitLabel);
            // 给 退出菜单项 添加事件监听器，单击时退出系统
            exitItem.addActionListener(e -> System.exit(0));
            // 添加 退出菜单项 到弹出框中
            pop.add(exitItem);

            // 创建托盘图标程序
            TrayIcon tray = new TrayIcon(icon.getImage(), "CommandsUI", pop);
            // 获得系统托盘对象
            SystemTray systemTray = SystemTray.getSystemTray();
            try {
                // 将托盘图标添加到系统托盘中
                systemTray.add(tray);
            } catch (AWTException e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * 初始化支持中文的系统托盘。
     *
     * @param frame
     */
    private static void initSystemTrayUTF8(JFrame frame) {
        // 使用JDialog 作为JPopupMenu载体
        JDialog jDialog = new JDialog();
        //关闭JDialog的装饰器
        jDialog.setUndecorated(true);
        //jDialog作为JPopupMenu载体不需要多大的size
        jDialog.setSize(1, 1);
        // 创建JPopupMenu
        // 重写firePopupMenuWillBecomeInvisible:
        // 当JPopupMenu消失后，把它的载体组件也弄消失
        JPopupMenu jPopupMenu = new JPopupMenu() {
            @Override
            public void firePopupMenuWillBecomeInvisible() {
                jDialog.setVisible(false);
                System.out.println("JPopupMenu不可见时，载体组件jDialog也不可见");
            }
        };

        // 菜单项1
        // JMenuItem showMainFrame = new JMenuItem(toUtf8Str("显示主窗体"));
        JMenuItem showMainFrame = new JMenuItem("显示主窗体");
        showMainFrame.addActionListener(e -> {
            System.out.println("显示主窗体");
            //显示窗口
            frame.setVisible(true);
            if (frame.getExtendedState() == JFrame.ICONIFIED) {
                System.out.println("已经最小化了");
                frame.setExtendedState(JFrame.NORMAL);
            }
        });

        // 菜单项2
        // JMenuItem exit = new JMenuItem(toUtf8Str("退出"));
        JMenuItem mini = new JMenuItem("最小化");
        mini.addActionListener(e -> {
            System.out.println("点击了最小化");
            // System.exit(0);
            // frame.setVisible(false);
            frame.setExtendedState(JFrame.ICONIFIED);//最小化窗体
        });

        // 菜单项3
        // JMenuItem exit = new JMenuItem(toUtf8Str("退出"));
        JMenuItem exit = new JMenuItem("退出");
        exit.addActionListener(e -> {
            System.out.println("点击了退出选项");
            System.exit(0);
        });


        // 添加菜单项到弹出框
        jPopupMenu.add(showMainFrame);
        jPopupMenu.add(mini);
        jPopupMenu.add(exit);
        // 自动调整大小
        jPopupMenu.pack();

        // 图标的路径
        URL resource = ToolUiSystemTray.class.getResource("/blue/commands/ico/工具_16.png");
        // 创建图片
        Image image = Toolkit.getDefaultToolkit().createImage(resource);
        // 创建系统托盘图标
        TrayIcon trayIcon = new TrayIcon(image);
        // 自动调整系统托盘图标大小
        trayIcon.setImageAutoSize(true);

        // 给托盘图标添加鼠标监听
        trayIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                // 当鼠标左键点击时
                if (e.getButton() == 1) {
                    //显示窗口
                    frame.setVisible(true);
                }
                // 当鼠标右键点击时
                else if (e.getButton() == 3 && e.isPopupTrigger()) {
                    // 设置载体的相对于鼠标的坐标
                    // jDialog.setLocation(e.getX() + 5, e.getY() - 5 - 50);
                    jDialog.setLocation(e.getX() + 5, e.getY() - jPopupMenu.getHeight());
                    // 显示JPopupMenu绑定的载体
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
}
