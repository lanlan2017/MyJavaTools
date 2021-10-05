package blue.commands.ui;


import blue.commands.thread.CommandsRunnable;
import blue.commands.tool.ui.ToolUiSystemTray;
import blue.commands.ui.event.button.ExitButtonListener;
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
    private JToolBar lineNumBar;
    private JTextField a1TextField;

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
        // OCR面板的输入文本域事件监听器
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
            @Override
            public void eventDispatched(AWTEvent event) {
                if (((KeyEvent) event).getID() == KeyEvent.KEY_PRESSED) {
                    //放入自己的键盘监听事件
                    //((KeyEvent) event).getKeyCode();// 获取按键的code
                    //((KeyEvent) event).getKeyChar();// 获取按键的字符
                    KeyEvent e = ((KeyEvent) event);
                    // 按下ALT键
                    int keyCode = e.getKeyCode();
                    if (e.isAltDown()) {
                        // 按下ALT+1~9数字键
                        if (keyCode >= KeyEvent.VK_1 && keyCode <= KeyEvent.VK_9) {
                            // 根据数字键计算索引
                            int index = keyCode - KeyEvent.VK_1;
                            // 让ToolBar中索引为index的组件得到焦点
                            requestFocusInToolBar(index);
                        }
                        // ALT+Q：退出程序
                        else if (keyCode == KeyEvent.VK_Q) {
                            System.exit(0);
                        }
                        //https://www.apiref.com/java11-zh/java.desktop/java/awt/event/KeyEvent.html
                        // Alt+'+'，KeyEvent.VK_EQUALS：backspace左侧的那个键
                        // Alt+'='，KeyEvent.VK_PLUS：backspace左侧的那个键
                        //keyCode==KeyEvent.VK_ADD：小键盘上的加法键
                        else if (keyCode == KeyEvent.VK_PLUS || keyCode == KeyEvent.VK_EQUALS || keyCode == KeyEvent.VK_ADD) {
                            // System.out.println(e.getKeyChar());
                            addTextFieldButtonAction(frame);
                        }
                        // alt+'减号键'，bacespace左侧第2个键
                        // https://www.apiref.com/java11-zh/java.desktop/java/awt/event/KeyEvent.html#VK_SUBTRACT
                        else if (keyCode == KeyEvent.VK_MINUS || keyCode == KeyEvent.VK_UNDERSCORE || keyCode == KeyEvent.VK_SUBTRACT) {
                            removeTextFieldButtonAction(frame);
                        }
                        // OCR面板可见时，按下ALT+B键
                        if (ocrPanel.isVisible() && keyCode == KeyEvent.VK_B) {
                            // System.out.println("按下ALT+B");
                            BaiduOCRButton.getInstance().baiduOCRButtonAction();
                        }
                    }
                    // 按下ctrl键
                    if (e.isControlDown()) {
                        //OCR面板可见时
                        if (ocrPanel.isVisible()) {
                            // Ctrl+W：截屏
                            if (keyCode == KeyEvent.VK_W) {
                                SstButton.getInstance().sstButtonAction();
                            }
                            // Ctrl+E：取消截屏
                            else if (keyCode == KeyEvent.VK_E) {
                                CancelButton.getInstance().cancelButtonAction();
                            }
                        }
                    }
                }
            }

            /**
             * 让ToolBar中索引为index的组件获取焦点。
             * @param index ToolBar中组件的索引
             */
            private void requestFocusInToolBar(int index) {
                // 如果ToolBar中有该索引的组件
                if (isAValidComponentIndexInTheToolbar(textFieldToolBar, index)) {
                    // 获取该索引的组件
                    JTextField textField = (JTextField) textFieldToolBar.getComponent(index);
                    // 让该组件获得焦点
                    textField.requestFocus();
                    // 通过代码来触发键盘事件
                    try {
                        Robot robot = new Robot();
                        robot.keyPress(KeyEvent.VK_ENTER);
                    } catch (AWTException e) {
                        e.printStackTrace();
                    }
                }
            }

            /**
             * 是否是ToolBar中的有效的组件的索引。
             * @param index Toolbar中组件的索引
             * @return 如果ToolBar中有该索引的组件，则返回true,否则返回false。
             */
            private boolean isAValidComponentIndexInTheToolbar(JToolBar toolBar, int index) {
                return index >= 0 && index < toolBar.getComponentCount();
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
     * 输入文本框增加或减少按钮监听器
     *
     * @param frame 窗体
     */
    private void textFieldController(JFrame frame) {
        addTextFieldButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTextFieldButtonAction(frame);
            }
        });
        removeTextFieldButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeTextFieldButtonAction(frame);
            }
        });
    }

    /**
     * 移除最后一个文本输入框和编号
     *
     * @param frame 面板
     */
    private void removeTextFieldButtonAction(JFrame frame) {
        // 如果有两个或两个以上的组件
        if (textFieldToolBar.getComponentCount() >= 2) {
            // 移除最后一个组件
            textFieldToolBar.remove(textFieldToolBar.getComponentCount() - 1);
            // 移除该组件对应的行号
            lineNumBar.remove(lineNumBar.getComponentCount() - 1);
            // 当行号小于10，并且行号文本框的列数为2时
            if (lineNumBar.getComponentCount() < 10 && ((JTextField) lineNumBar.getComponent(0)).getColumns() == 2) {
                // 遍历所有的行号文本框
                for (int i = 0; i < lineNumBar.getComponentCount(); i++) {
                    JTextField jTextField = (JTextField) lineNumBar.getComponent(i);
                    // 把这些文本框的列数设置为1
                    jTextField.setColumns(1);
                    frame.pack();
                }
            }

            //如果，移除最后一个组件之后，只剩一个组件。
            if (textFieldToolBar.getComponentCount() == 1) {
                // 那么隐藏移除按钮，免得最后一个组件被移除
                removeTextFieldButton.setVisible(false);
            }
            // 刷新组件
            frame.pack();
        }
    }

    /**
     * 添加文本框按钮事件处理功能
     *
     * @param frame 面板
     */
    private void addTextFieldButtonAction(JFrame frame) {
        // 创建文本框
        JTextField jTextField2 = new JTextField(8);
        // 给文本框设置自动提示信息
        textFieldAutoSetting(jTextField2);
        // 文本框工具条添加一个文本框
        textFieldToolBar.add(jTextField2);
        // 新创建的命令输入框获得焦点
        jTextField2.requestFocus();
        // 创建行号文本提示框
        JTextField lineNumTextField = new JTextField(String.valueOf(textFieldToolBar.getComponentCount()));
        lineNumTextField.setColumns(1);
        // 设置不可编辑
        // lineNumTextField.setEditable(false);
        lineNumTextField.setEnabled(false);
        lineNumTextField.addMouseListener(new MouseAdapter() {
            Color defaultColor;
            CommandsRunnable runnable = CommandsRunnable.getInstance();
            // Thread thread;

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2) {
                    System.out.println("左键双击行号框");
                    int index = Integer.parseInt(lineNumTextField.getText()) - 1;
                    System.out.println(index);
                    textFieldToolBar.remove(index);
                    // 移除该组件对应的行号
                    lineNumBar.remove(index);
                    //如果，移除最后一个组件之后，只剩一个组件。
                    if (textFieldToolBar.getComponentCount() == 1) {
                        // 那么隐藏移除按钮，免得最后一个组件被移除
                        removeTextFieldButton.setVisible(false);
                    }
                    // 遍历所有的编号文本框
                    for (int i = 1; i < lineNumBar.getComponentCount(); i++) {
                        JTextField jTextField = (JTextField) lineNumBar.getComponent(i);
                        // 重新编号
                        jTextField.setText("" + (i + 1));
                        // frame.pack();
                    }
                    frame.pack();
                }
                // 如果是右键点击 行号文本框
                else if (e.getButton() == MouseEvent.BUTTON3 && e.getClickCount() == 2) {
                    if (lineNumTextField.getBackground() == Color.PINK) {
                        //
                        String lineNumStr = lineNumTextField.getText();
                        lineNumStr = lineNumStr.substring(0, lineNumStr.indexOf("|监听"));
                        lineNumTextField.setText(lineNumStr);
                        lineNumTextField.setColumns(lineNumStr.length());
                        lineNumTextField.setBackground(defaultColor);
                        lineNumTextField.setEnabled(false);

                        // 根据行号查找对应的命令输入文本框
                        JTextField inputTextFiled = (JTextField) textFieldToolBar.getComponent(Integer.parseInt(lineNumStr) - 1);
                        // 获取名命令文本框中的命令
                        String commandStr = inputTextFiled.getText();
                        runnable.removeCommand(commandStr);

                    } else {
                        lineNumTextField.setEnabled(true);
                        lineNumTextField.setEnabled(false);
                        defaultColor = lineNumTextField.getBackground();
                        lineNumTextField.setBackground(Color.PINK);
                        // 读取行号
                        String lineNum = lineNumTextField.getText();
                        // 在行号文本框中添加提示信息
                        String text = lineNum + "|监听";
                        lineNumTextField.setText(text);
                        lineNumTextField.setColumns(text.length());

                        // 根据行号查找对应的命令输入文本框
                        JTextField inputTextFiled = (JTextField) textFieldToolBar.getComponent(Integer.parseInt(lineNum) - 1);
                        // 获取名命令文本框中的命令
                        String commandStr = inputTextFiled.getText();
                        runnable.addCommand(commandStr);

                        // 设置给线程指定输出的文本框
                        runnable.setOutputTextArea(outputTextArea);
                    }
                    // 如果没有命令,则停止线程
                    if (runnable.getCommandList().size() == 0) {
                        // 不再循环执行线程体，线程结束
                        runnable.setCanRunning(false);
                        // 可以再次启动线程
                        runnable.setCanStarting(true);
                        System.out.println("线程停止");
                    }
                    // 如果存在命令
                    else {
                        // System.out.println("==============================");
                        // System.out.println(runnable.getCommandList().size());
                        // System.out.println("runnable.isRun()= " + runnable.isCanRunning());
                        // System.out.println("runnable.isCanStarting() = " + runnable.isCanStarting());
                        // 如果线程可以启动
                        if (runnable.isCanStarting()) {
                            Thread thread;
                            // 重新创建一个线程
                            thread = new Thread(runnable);
                            // 设置线程体可运行
                            runnable.setCanRunning(true);
                            // 启动线程
                            thread.start();
                            // 不可再次启动线程
                            runnable.setCanStarting(false);
                            System.out.println("线程启动");
                        }
                    }

                    frame.pack();
                }
            }
        });


        lineNumBar.add(lineNumTextField);
        // 当编号文本框的个数大于9，并且编号文本框的默认列数为1时
        if (lineNumBar.getComponentCount() > 9 && lineNumTextField.getColumns() == 1) {
            // 遍历所有的文本框
            for (int i = 0; i < lineNumBar.getComponentCount(); i++) {
                JTextField jTextField = (JTextField) lineNumBar.getComponent(i);
                // 把这些文本框的列数都设置为2
                jTextField.setColumns(2);
                // frame.pack();
            }
        }
        // 如果有两个元素
        if (textFieldToolBar.getComponentCount() > 1) {
            // 可以减去其中一个元素
            removeTextFieldButton.setVisible(true);
        }

        frame.pack();
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
        exitButton.addActionListener(new ExitButtonListener());
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
