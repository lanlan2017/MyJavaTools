package com.blue.ui;

import com.formdev.flatlaf.FlatLightLaf;
import tools.config.ConfigTools;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.Scanner;

public class MainFrom {

    private JFrame frame;
    private JPanel panel;
    private JButton exitButton;
    private JTextField textField;
    private JTextArea textArea;
    private JLabel lable;
    private JScrollPane scrollPane;

    public MainFrom(JFrame frame) {
        // 记下Frame
        this.frame = frame;
        // 初始化系统托盘
        initSystemTray();
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // 监听面板事件
        // panel.addMouseMotionListener(new MouseAdapter() {
        frame.addMouseMotionListener(new MouseAdapter() {
            private boolean isNearTop = false;
            private boolean isNearBottom = false;
            private boolean isNearLeft = false;
            private boolean isNearRight = false;
            private boolean drag = false;
            // 移动的位置
            private Point draggingAnchor = null;

            // 监听鼠标移动事件
            @Override
            public void mouseMoved(MouseEvent e) {
                // 精度，距离窗体边框多少距离时可以拖动来调整窗体的大小。
                int jingDu = 5;
                // if (e.getPoint().getY() == 0) {
                if (Math.abs(e.getPoint().getY() - 0) <= jingDu) {
                    // 设置拖动光标
                    frame.setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
                    isNearTop = true;
                    // System.out.println("Cursor is near TOP");
                } else if (Math.abs(e.getPoint().getY() - frame.getSize().getHeight()) <= jingDu) {
                    frame.setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
                    isNearBottom = true;
                    // System.out.println("Cursor is near Bottom");
                }
                // else if (e.getPoint().getX() == 0) {
                else if (Math.abs(e.getPoint().getX() - 0) <= jingDu) {
                    frame.setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
                    isNearLeft = true;
                    // System.out.println("Cursor is near left");
                } else if (Math.abs(e.getPoint().getX() - frame.getSize().getWidth()) <= jingDu) {
                    frame.setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
                    isNearRight = true;
                    // System.out.println("Cursor is near right");
                } else {
                    frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                    draggingAnchor = new Point(e.getX() + panel.getX(), e.getY() + panel.getY());
                    isNearTop = false;
                    isNearBottom = false;
                    isNearLeft = false;
                    isNearRight = false;
                    drag = true;
                    // System.out.println("is near center");
                }
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                // 获取窗体的大小
                Dimension dimension = frame.getSize();
                // 当鼠标指针在顶部的时候
                if (isNearTop) {
                    // 设置高度减去鼠标移动后的坐标
                    dimension.setSize(dimension.getWidth(), dimension.getHeight() - e.getY());
                    // 设置窗体的大小
                    frame.setSize(dimension);
                    // 移动变大后的窗体到原来的坐标
                    frame.setLocation(frame.getLocationOnScreen().x, frame.getLocationOnScreen().y + e.getY());
                }
                // 鼠标指针在底部时
                else if (isNearBottom) {
                    dimension.setSize(dimension.getWidth(), e.getY());
                    // 设置窗体的大小
                    frame.setSize(dimension);
                }
                // 当鼠标指针在左边时
                else if (isNearLeft) {

                    dimension.setSize(dimension.getWidth() - e.getX(), dimension.getHeight());
                    frame.setSize(dimension);
                    // 移动窗体的坐标
                    frame.setLocation(frame.getLocationOnScreen().x + e.getX(), frame.getLocationOnScreen().y);
                }
                // 当鼠标指针在右边时
                else if (isNearRight) {
                    dimension.setSize(e.getX(), dimension.getHeight());
                    // 设置窗体的大小
                    frame.setSize(dimension);
                }
                // 当鼠标指针在中间时
                else if (drag) {
                    // 移动窗体的位置
                    frame.setLocation(e.getLocationOnScreen().x - draggingAnchor.x, e.getLocationOnScreen().y - draggingAnchor.y);
                }

            }
        });


        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                // 如果按下回车键
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    // 获取文本框的内容
                    String input = textField.getText();
                    if (!"".equals(input) && input != null) {
                        // 处理输入的文本
                        String output = doTextField(input);
                        // System.out.println(output);
                        // System.out.println(output == null);
                        if (output != null) {
                            // 统计结果有多少行
                            int[] line = countRows(output);
                            // 设置文本框的行数和结果一样
                            textArea.setRows(line[0]);
                            textArea.setColumns(line[1]);
                            // 处理结果写到文本域中
                            textArea.setText(output);
                            // 显示textArea面板
                            scrollPane.setVisible(true);
                            // 重绘UI
                            repaint();
                        } else {
                            // 隐藏textArea面板
                            scrollPane.setVisible(false);
                            // 重绘UI
                            repaint();
                        }
                    }
                }
            }

            private int[] countRows(String output) {
                int[] rowColLength = new int[2];
                String lineStr;
                int lineLength;
                Scanner scanner = new Scanner(output);
                while (scanner.hasNext()) {
                    // 读取行
                    lineStr = scanner.nextLine();
                    // 获取行的长度
                    lineLength = lineStr.length();
                    // 记下最长的行的长度
                    if (lineLength > rowColLength[1]) {
                        rowColLength[1] = lineLength;
                    }
                    rowColLength[0]++;
                }
                scanner.close();
                // System.out.println("行数：" + rowColLength[0]);
                // System.out.println("列数：" + rowColLength[1]);
                return rowColLength;
            }
        });
    }

    /**
     * 处理输入的内容
     *
     * @param input 文本框中输入的文本
     * @return 对文本框中的文本处理的结果
     */
    private String doTextField(String input) {
        String output;
        // 按空格分隔得到命令
        String[] args = input.split(" ");
        // 执行命令，返回执行结果
        output = ConfigTools.getInstance().forward(args);
        // 复制到系统剪贴板
        ConfigTools.getInstance().copyToSysClipboard(output);
        return output;
    }

    /**
     * 重绘组件
     */
    private void repaint() {
        // 重绘面板
        // panel.repaint();
        // 重绘窗体
        frame.repaint();
        // 整个窗体最小显示
        frame.pack();
    }

    /**
     * 初始化系统托盘
     */
    private void initSystemTray() {
        // 判断系统是否支持托盘图标
        if (SystemTray.isSupported()) {
            // 如果系统支持系统托盘，那么不显示任务栏
            // frame.setType(Window.Type.UTILITY);

            // // 获取托盘图标,图片请放在 当前包 下
            // URL resource = this.getClass().getResource("/com/blue/ico/草莓_16.png");
            URL resource = this.getClass().getResource("/com/blue/ico/工具_16.png");
            // 创建图标
            ImageIcon icon = new ImageIcon(resource);
            // 创建弹出式菜单
            PopupMenu pop = new PopupMenu();

            String str = "显示主界面";
            // 创建 显示菜单项
            MenuItem displayJFrameItem = new MenuItem(str);
            // 给 显示窗体菜单项 添加事件处理程序
            displayJFrameItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.setVisible(true);
                }
            });
            // 显示菜单项 添加到 弹出式菜单中
            pop.add(displayJFrameItem);

            // 创建 退出菜单项
            MenuItem exitItem = new MenuItem("退出");
            // 给 退出菜单项 添加事件监听器，单击时退出系统
            exitItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });
            // 添加 退出菜单项 到弹出框中
            pop.add(exitItem);


            // // 创建 退出菜单项
            // MenuItem exitItem2 = new MenuItem("任务栏");
            // // 给 退出菜单项 添加事件监听器，单击时退出系统
            // exitItem2.addActionListener(new ActionListener() {
            //     @Override
            //     public void actionPerformed(ActionEvent e) {
            //         if(frame.isVisible()){
            //             frame.setState(Frame.ICONIFIED);
            //             frame.setVisible(false);
            //             frame.setType(Window.Type.UTILITY);
            //         }else {
            //             frame.setState(Frame.NORMAL);
            //             frame.setVisible(true);
            //         }
            //         // frame.setState(Frame.ICONIFIED);
            //     }
            // });
            // // 添加 退出菜单项 到弹出框中
            // pop.add(exitItem2);


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
     * 设置窗口初始界面
     *
     * @param mainFrom 主界面
     * @param frame    窗体
     */
    private static void mainFromSetting(MainFrom mainFrom, JFrame frame) {
        // 该开始的时候不要显示文本域
        mainFrom.scrollPane.setVisible(false);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MainFrom");
        // 如果有传入参数的话
        if (args.length > 0) {
            // 不显示任务栏的情况
            if ("notaskbar".equals(args[0]) || "no".equals(args[0])) {
                frame.setType(Window.Type.UTILITY);
            }
        }
        MainFrom mainFrom = new MainFrom(frame);

        // 设置面板到窗体上
        frame.setContentPane(mainFrom.panel);
        // // 设置关闭按钮功能
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.setType(Window.Type.NORMAL);
        // frame.setType(Window.Type.POPUP);

        // 不显示任务栏
        // frame.setType(Window.Type.UTILITY);
        // 不显示标题栏，最小化，关闭按钮
        frame.setUndecorated(true);
        // 永远置顶
        frame.setAlwaysOnTop(true);
        // 设置透明度
        frame.setOpacity(0.5f);

        // 设置初始界面
        mainFromSetting(mainFrom, frame);
        // 设置主题
        // FlatDarkLaf.setup();
        FlatLightLaf.setup();
        // 给所有的组件都使用该主题
        SwingUtilities.updateComponentTreeUI(frame);
        // 最合适的方式显示
        frame.pack();
        // 显示窗体
        frame.setVisible(true);
    }

}
