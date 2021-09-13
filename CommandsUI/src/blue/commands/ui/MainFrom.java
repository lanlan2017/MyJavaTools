package blue.commands.ui;

// import blue.commands.tool.ThreadAutoSetFrameOpacity;

import blue.commands.tool.ui.ToolUiSystemTray;
// import blue.commands.ui.event.panel.PanelMouseListener;
import blue.commands.ui.event.panel.PanelMouseMotionListener;
import blue.commands.ui.event.textarea.TextAreaMouseListener;
import blue.commands.ui.event.textfield.auto.AutoField;
import blue.ocr3.buttons.BaiduOCRButton;
import blue.ocr3.buttons.CancelButton;
import blue.ocr3.buttons.SstButton;
import com.formdev.flatlaf.FlatLightLaf;
import tools.markdown.niuke.ToolsJarPath;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

public class MainFrom {
    static {
        // 设置外观,先设置外观，再创建UI。
        // 为了保证创建UI时，已经设置好外观，设置外观的代码最好写在静态块中，
        // 并且把这个静态块写在类定义的最前面。
        FlatLightLaf.setup();
    }

    // private static MainFrom instance = new MainFrom();
    private static MainFrom instance = new MainFrom(new JFrame("OCR3Form"));

    public static MainFrom getInstance() {
        return instance;
    }

    private JFrame frame;
    private JPanel panel;
    private JButton exitButton;
    private JTextField textField;
    private JTextArea textArea;
    private JLabel lable;
    private JScrollPane scrollPane;
    private JPanel scrollPaneFather;
    // OCR面板组件
    private JButton sstButton;
    private JButton cancelSstButton;
    private JButton ocrButton;
    private JTextArea inputTextArea;
    private JRadioButton radioButton;
    private JPanel ocrPanel;
    private JPanel ocrToolPanel;
    private JToolBar ocrToolBar;

    public MainFrom(JFrame frame) {
        // 记下Frame
        this.frame = frame;
        // 初始化系统托盘
        new ToolUiSystemTray(frame);
        // 程序刚开始，还没输入内容，不会有输出，隐藏用来显示输出的文本域
        scrollPaneFather.setVisible(false);
        // 程序刚开始隐藏OCR面板
        ocrPanel.setVisible(false);
        // 设置退出按钮的功能
        exitButtonSetting();
        // 窗体功能
        frameSetting();
        // 文本框功能
        textFieldSetting();
        // 监听文本域鼠标事件，右键点击文本域，将会清空文本域。
        textArea.addMouseListener(new TextAreaMouseListener(frame, textArea));
        // 当按钮状态改变时
        radioButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                // 显示或隐藏OCR面板
                ocrPanel.setVisible(!ocrPanel.isVisible());
                // 以合适大小显示窗体
                frame.pack();
            }
        });
    }

    private void textFieldSetting() {
        autoSetting();
    }

    private void autoSetting() {
        // 创建JComboBox模型
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        // 使用JComboBox模型创建JComboBox
        JComboBox jComboBox = new JComboBox(model) {
            @Override
            public Dimension getPreferredSize() {
                // 设置位置
                return new Dimension(super.getPreferredSize().width, 0);
            }
        };
        // 给文本框设置自动内容提示
        AutoField.setupAutoComplete2(frame, textField, jComboBox, textArea, scrollPaneFather);
    }

    private void frameSetting() {
        // 监听面板事件
        panel.addMouseMotionListener(new PanelMouseMotionListener(frame, panel));
        // panel.addMouseListener(new PanelMouseListener(frame));
        // 监听窗体焦点事件
        frame.addWindowFocusListener(new WindowFocusListener() {
            // 当窗体获得焦点是
            @Override
            public void windowGainedFocus(WindowEvent e) {
                // 设置不透明
                frame.setOpacity(1.0f);
                // 让文本框获得焦点
                textField.requestFocus();
            }

            // 当窗体失去焦点时
            @Override
            public void windowLostFocus(WindowEvent e) {
                // 设置半透明
                frame.setOpacity(0.5f);
            }
        });
    }

    /**
     * 退出按钮设置
     */
    private void exitButtonSetting() {
        // // 设置按钮的大小
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
        MainFrom mainFrom = MainFrom.getInstance();
        JFrame frame= mainFrom.frame;
        frame.setContentPane(mainFrom.panel);
        // 设置工作目录为可执行.jar所在的目录
        // 如果有传入参数的话
        if (args.length > 0) {
            // 不显示任务栏的情况
            if ("notaskbar".equals(args[0]) || "no".equals(args[0])) {
                // 设置窗体不显示任务栏
                frame.setType(Window.Type.UTILITY);
            }
        }

        // 不显示标题栏，最小化，关闭按钮
        frame.setUndecorated(true);
        // 永远置顶
        frame.setAlwaysOnTop(true);
        // 最合适的方式显示
        frame.pack();
        // 显示窗体
        frame.setVisible(true);
    }

    private void createUIComponents() {
        panel=new JPanel();
        sstButton = SstButton.getInstance(panel).getButton();
        cancelSstButton = CancelButton.getInstance(panel).getButton();
        ocrButton = BaiduOCRButton.getInstance(panel).getButton();
    }
}
