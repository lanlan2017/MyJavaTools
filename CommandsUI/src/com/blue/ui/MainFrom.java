package com.blue.ui;

import com.blue.demo.ToolIsChinese;
import com.blue.tool.ThreadAutoSetFrameOpacity;
import com.blue.tool.ui.ToolUiSystemTray;
import com.formdev.flatlaf.FlatLightLaf;
import tools.config.ConfigTools;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;

public class MainFrom {

    private JFrame frame;
    private JPanel panel;
    private JButton exitButton;
    private JTextField textField;
    private JTextArea textArea;
    private JLabel lable;
    private JScrollPane scrollPane;
    private JPanel scrollPaneFather;

    public MainFrom(JFrame frame) {
        // 记下Frame
        this.frame = frame;
        // 初始化系统托盘
        new ToolUiSystemTray(frame);
        // 程序刚开始，还没输入内容，不会有输出，隐藏用来显示输出的文本域
        // scrollPane.setVisible(false);
        scrollPaneFather.setVisible(false);
        // 设置退出按钮的功能
        exitButtonSetting();
        // 窗体功能
        frameSetting();
        // 文本框功能
        textFieldSetting();
    }
    private void textFieldSetting() {
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                // 如果按下回车键
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    // 获取文本框的内容
                    String input = textField.getText();
                    if (!"".equals(input) && input != null) {
                        // 设置标题的名称作为提示信息
                        frame.setTitle(input);
                        // 处理输入的文本
                        String output = doTextField(input);
                        // System.out.println(output);
                        if (output != null) {
                            // 统计结果有多少行
                            int[] line = countRows(output);
                            // 设置文本框的行数
                            textArea.setRows(line[0]+1);
                            textArea.setColumns(line[1]+1);
                            // 处理结果写到文本域中
                            textArea.setText(output);
                            // 显示textArea面板
                            // scrollPane.setVisible(true);
                            scrollPaneFather.setVisible(true);

                            // 重绘UI
                            repaint();
                        } else {
                            // 隐藏textArea面板
                            // scrollPane.setVisible(false);
                            scrollPaneFather.setVisible(false);
                            // 重绘UI
                            repaint();
                        }
                    }
                }
            }

            private int[] countRows(String output) {
                int[] rowColLength = new int[2];
                String lineStr;
                String longestLine = null;
                int lineLength;
                Scanner scanner = new Scanner(output);
                while (scanner.hasNext()) {
                    // 读取行
                    lineStr = scanner.nextLine();
                    // 获取行的长度
                    lineLength = lineStr.length();
                    // 记下最长的行的长度
                    if (lineLength > rowColLength[1]) {
                        // 记录行的长度
                        rowColLength[1] = lineLength;
                        longestLine = lineStr;
                    }
                    rowColLength[0]++;
                }
                scanner.close();
                if (longestLine != null) {
                    char ch;
                    double count = 1;
                    for (int i = 0; i < longestLine.length(); i++) {
                        ch = longestLine.charAt(i);
                        if (ToolIsChinese.isContainChinese(ch)) {
                            count += 1.0;
                        } else {
                            count += 0.5;
                        }
                    }
                    rowColLength[1] = (int) count;
                }

                System.out.println("中英文列长度:" + rowColLength[1]);
                return rowColLength;
            }
        });
        // 注意，好像只有文本框，等输入组件才能得到焦点，窗体，面板都不能得到焦点
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                // 如果文本域中有内容的话
                if (!"".equals(textArea.getText())) {
                    // 当文本框获得焦点的时候，关闭透明检查
                    ThreadAutoSetFrameOpacity.isSetOpacity = false;
                    // 显示文本域面板
                    // scrollPane.setVisible(true);
                    scrollPaneFather.setVisible(true);
                    // 最小化显示组件
                    frame.pack();
                }
                // 窗体不透明
                frame.setOpacity(1.0f);
            }
        });
        Document document = textField.getDocument();
        int defalutCols = textField.getColumns();
        // 监听文本框内容变化
        document.addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                // System.out.println("有插入内容啦");
                int textAreaLen = textField.getText().length();
                // 当输入的内容比文本框的默认列数大时
                if (textAreaLen >= textField.getColumns()) {
                    // 增加文本框的列数
                    textField.setColumns(textAreaLen + 1);
                    frame.pack();
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                // System.out.println("有删除内容啦");
                int textAreaLen = textField.getText().length();
                // 文本的字数大于默认的宽度并小于
                if (textField.getColumns() > defalutCols) {
                    // 设置列数问字符数字+1
                    textField.setColumns(textAreaLen + 1);
                    frame.pack();
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
    }

    private void frameSetting() {
        // 监听面板事件
        panel.addMouseMotionListener(new MouseAdapter() {
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
                if (Math.abs(e.getPoint().getY() - 0) <= jingDu) {
                    // 设置拖动光标
                    frame.setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
                    isNearTop = true;
                    // System.out.println("Cursor is near TOP");
                } else if (Math.abs(e.getPoint().getY() - frame.getSize().getHeight()) <= jingDu) {
                    frame.setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
                    isNearBottom = true;
                    // System.out.println("Cursor is near Bottom");
                } else if (Math.abs(e.getPoint().getX() - 0) <= jingDu) {
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
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // 当鼠标进入窗体时，不自动设置透明对
                ThreadAutoSetFrameOpacity.isSetOpacity = false;
                // 不透明
                frame.setOpacity(1.0f);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // 当鼠标离开窗体时，线程检查透明度
                ThreadAutoSetFrameOpacity.isSetOpacity = true;
            }
        });
    }

    /**
     * 退出按钮设置
     */
    private void exitButtonSetting() {
        // 设置按钮的大小
        Dimension preferredSize = new Dimension(50, 22);
        exitButton.setPreferredSize(preferredSize);
        // 退出按钮的功能
        exitButton.addActionListener(e -> {
            System.exit(0);
        });
    }

    public JFrame getFrame() {
        return frame;
    }
    //
    // public JScrollPane getScrollPane() {
    //     return scrollPane;
    // }

    public JPanel getScrollPaneFather() {
        return scrollPaneFather;
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
        // 重绘窗体
        frame.repaint();
        // 整个窗体最小显示
        frame.pack();
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("MainFrom");
        // 如果有传入参数的话
        if (args.length > 0) {
            // 不显示任务栏的情况
            if ("notaskbar".equals(args[0]) || "no".equals(args[0])) {
                // 设置窗体不显示任务栏
                frame.setType(Window.Type.UTILITY);
            }
        }
        MainFrom mainFrom = new MainFrom(frame);

        // 设置面板到窗体上
        frame.setContentPane(mainFrom.panel);
        // // 设置关闭按钮功能
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 不显示标题栏，最小化，关闭按钮
        frame.setUndecorated(true);
        // 永远置顶
        frame.setAlwaysOnTop(true);

        // 设置主题
        FlatLightLaf.setup();
        // 给所有的组件都使用该主题
        SwingUtilities.updateComponentTreeUI(frame);
        // 最合适的方式显示
        frame.pack();
        // 显示窗体
        frame.setVisible(true);
        // 创建线程
        Thread thread = new Thread(new ThreadAutoSetFrameOpacity(mainFrom));
        thread.start();
    }
}
