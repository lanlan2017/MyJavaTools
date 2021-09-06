package blue.commands.ui;

import blue.commands.ui.event.panel.PanelMouseListener;
import blue.commands.ui.event.panel.PanelMouseMotionListener;
import blue.commands.tool.ThreadAutoSetFrameOpacity;
import blue.commands.tool.ui.ToolUiSystemTray;
import blue.commands.ui.event.textfield.TextAreaMouseListener;
import com.formdev.flatlaf.FlatLightLaf;
import blue.commands.ui.event.textfield.TextFieldDocumentListener;
import blue.commands.ui.event.textfield.TextFieldFocusAdapter;
import blue.commands.ui.event.textfield.TextFieldKeyAdapter;

import javax.swing.*;
import javax.swing.text.Document;
import java.awt.*;

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
        // 监听文本域鼠标事件，右键点击文本域，将会清空文本域。
        textArea.addMouseListener(new TextAreaMouseListener(frame, textArea));
    }

    private void textFieldSetting() {
        // 注册键盘事件监听器
        textField.addKeyListener(new TextFieldKeyAdapter(frame, textField, textArea, scrollPaneFather));
        // 注意，好像只有文本框，等输入组件才能得到焦点，窗体，面板都不能得到焦点
        textField.addFocusListener(new TextFieldFocusAdapter(frame, scrollPaneFather, textArea));

        Document document = textField.getDocument();
        int defalutCols = textField.getColumns();
        // 监听文本框内容变化
        document.addDocumentListener(new TextFieldDocumentListener(frame, textField));
    }

    private void frameSetting() {
        // 监听面板事件
        panel.addMouseMotionListener(new PanelMouseMotionListener(frame, panel));
        panel.addMouseListener(new PanelMouseListener(frame));
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

    public JPanel getScrollPaneFather() {
        return scrollPaneFather;
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