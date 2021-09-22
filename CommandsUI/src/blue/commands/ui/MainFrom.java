package blue.commands.ui;


import blue.commands.tool.ui.ToolUiSystemTray;
import blue.commands.ui.event.panel.PanelMouseMotionListener;
import blue.commands.ui.event.radiobutton.RadioButtonItemListener;
import blue.commands.ui.event.textarea.TextAreaMouseListener;
import blue.commands.ui.event.textfield.auto.AutoFieldSetting;
import blue.ocr3.buttons.BaiduOCRButton;
import blue.ocr3.buttons.CancelButton;
import blue.ocr3.buttons.SstButton;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrom {
    static {
        // 设置外观,先设置外观，再创建UI。
        // 为了保证创建UI时，已经设置好外观，设置外观的代码最好写在静态块中，
        // 并且把这个静态块写在类定义的最前面。
        FlatLightLaf.setup();
    }

    private static MainFrom instance = new MainFrom(new JFrame("OCR3Form"));

    public static MainFrom getInstance() {
        return instance;
    }

    private JFrame frame;
    // cammands面板组件
    private JPanel panel;
    private JButton exitButton;
    private JTextField textField;
    private JTextArea outputTextArea;
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
    private JPanel commandPanel;
    /**
     * 输入文本域控制器
     */
    private JButton inputTextAreaController;
    /**
     * 输入文本框增加按钮
     */
    private JButton addTextFieldButton;
    /**
     * 输入文本框减少按钮
     */
    private JButton removeTextFieldButton;
    /**
     * 输入文本框的父容器，工具条
     */
    private JToolBar textFieldToolBar;

    public JTextArea getInputTextArea() {
        return inputTextArea;
    }

    public MainFrom(JFrame frame) {
        // 记下Frame
        this.frame = frame;
        // 初始化系统托盘
        new ToolUiSystemTray(frame);
        // 程序刚开始，还没输入内容，不会有输出，隐藏用来显示输出的文本域
        scrollPaneFather.setVisible(false);
        // 程序刚开始隐藏OCR面板
        ocrPanel.setVisible(false);
        // 程序刚开始，隐藏输入文本域
        inputTextArea.setVisible(false);
        // 程序刚开始隐藏文本框减少按钮
        removeTextFieldButton.setVisible(false);

        // 设置退出按钮的功能
        exitButtonSetting();
        // 窗体功能
        frameSetting();
        // 文本框功能
        textFieldSetting();
        // 监听文本域鼠标事件，右键点击文本域，将会清空文本域。
        outputTextArea.addMouseListener(new TextAreaMouseListener(frame, outputTextArea));
        // 当按钮状态改变时
        radioButton.addItemListener(new RadioButtonItemListener(frame, ocrPanel));
        // 输入文本域事件监听器
        inputTextAreaControllerSetting(frame);
        // 添加或减少输入文本框按钮设置
        textFieldController(frame);
        // 设置全局键盘事件处理程序
        keyEventSetting();
    }

    /**
     * 全局键盘事件处理设置
     */
    private void keyEventSetting() {
        Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener() {
            public void eventDispatched(AWTEvent event) {

                if (((KeyEvent) event).getID() == KeyEvent.KEY_PRESSED) {
                    //放入自己的键盘监听事件
                    //((KeyEvent) event).getKeyCode();// 获取按键的code
                    //((KeyEvent) event).getKeyChar();// 获取按键的字符
                    KeyEvent keyEvent = ((KeyEvent) event);
                    if (keyEvent.isControlDown() && keyEvent.getKeyCode() == KeyEvent.VK_1) {
                        System.out.println("按下ctrl+1");
                    }

                    // ALT+Q：退出程序
                    if (keyEvent.isAltDown() && keyEvent.getKeyCode() == KeyEvent.VK_Q) {
                        // System.out.println("按下alt+q");
                        System.exit(0);
                    }

                    if (ocrPanel.isVisible()) {
                        if (keyEvent.isControlDown() && keyEvent.getKeyCode() == KeyEvent.VK_W) {
                            // System.out.println("按下ctrl+W");
                            SstButton.getInstance().sstButtonAction();
                        }
                        if (keyEvent.isControlDown() && keyEvent.getKeyCode() == KeyEvent.VK_E) {
                            // System.out.println("按下ctrl+e");
                            CancelButton.getInstance().cancelButtonAction();
                        }
                        if (keyEvent.isAltDown() && keyEvent.getKeyCode() == KeyEvent.VK_B) {
                            // System.out.println("按下ALT+B");
                            BaiduOCRButton.getInstance().baiduOCRButtonAction();
                        }
                    }
                }
            }
        }, AWTEvent.KEY_EVENT_MASK);
    }

    private void inputTextAreaControllerSetting(JFrame frame) {
        inputTextAreaController.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (inputTextArea.isVisible()) {
                    inputTextAreaController.setText("显示");
                    inputTextArea.setText("");
                    inputTextArea.setVisible(false);
                } else {
                    inputTextAreaController.setText("隐藏");
                    inputTextArea.setVisible(true);
                }
                frame.pack();
            }
        });
    }

    /**
     * 输入文本框控制设置。
     *
     * @param frame 窗体
     */
    private void textFieldController(JFrame frame) {
        addTextFieldButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 创建文本框
                JTextField jTextField2 = new JTextField(8);
                // 给文本框设置自动提示信息
                textFieldAutoSetting(jTextField2);
                textFieldToolBar.add(jTextField2);
                // 如果有两个元素
                if (textFieldToolBar.getComponentCount() > 1) {
                    // 可以减去其中一个元素
                    removeTextFieldButton.setVisible(true);
                }
                frame.pack();
            }
        });
        removeTextFieldButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 如果有两个或两个以上的组件
                if (textFieldToolBar.getComponentCount() >= 2) {
                    // 移除最后一个组件
                    textFieldToolBar.remove(textFieldToolBar.getComponentCount() - 1);
                    //如果，移除最后一个组件之后，只剩一个组件。
                    if (textFieldToolBar.getComponentCount() == 1) {
                        // 那么隐藏移除按钮，免得最后一个组件被移除
                        removeTextFieldButton.setVisible(false);
                    }
                    // 刷新组件
                    frame.pack();
                }
            }
        });
    }

    private void textFieldSetting() {
        textFieldAutoSetting();
    }

    private void textFieldAutoSetting() {
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
        AutoFieldSetting.setupAutoComplete2(frame, textField, jComboBox, outputTextArea, scrollPaneFather);
    }

    private void textFieldAutoSetting(JTextField textField) {
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
        AutoFieldSetting.setupAutoComplete2(frame, textField, jComboBox, outputTextArea, scrollPaneFather);
    }

    /**
     * 窗体设置
     */
    private void frameSetting() {
        // 监听面板事件
        panel.addMouseMotionListener(new PanelMouseMotionListener(frame, panel));
        // 监听窗体焦点事件
        frame.addWindowFocusListener(new WindowFocusListener() {
            // 当窗体获得焦点是
            @Override
            public void windowGainedFocus(WindowEvent e) {
                // 设置不透明
                frame.setOpacity(1.0f);
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
        JFrame frame = mainFrom.frame;
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
        // commandPanel = new JPanel();
        sstButton = SstButton.getInstance().getButton();
        cancelSstButton = CancelButton.getInstance().getButton();
        ocrButton = BaiduOCRButton.getInstance().getButton();
    }
}
