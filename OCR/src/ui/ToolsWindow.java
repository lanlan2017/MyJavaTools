package ui;

import ui.toolbar.ToolBar;
import ui.toolbar.buttons.MoveLabel;

import javax.swing.*;
import java.awt.*;

/*
 * 操作窗口
 */
public class ToolsWindow extends JWindow {
    private static final ToolsWindow instance = new ToolsWindow(0, 0);
    private JPanel toolPanel;
    private static final long serialVersionUID = 1L;

    private ToolsWindow(int x, int y) {
        this.init();
        this.setLocation(x, y);
        // 设置大小
        //this.setBounds(0, 0, 200, 200);
        this.pack();
        // 永远显示在其他程序上方
        this.setAlwaysOnTop(true);
        // 显示窗体
        this.setVisible(true);
    }

    /**
     * 获取工具条窗体的引用.
     *
     * @return ToolsWindow对象
     */
    public static ToolsWindow getInstance() {
        return instance;
    }

    /**
     * 初始化工具条
     */
    private void init() {
        this.setLayout(new BorderLayout());
        // 创建面板
        toolPanel = new JPanel();
        // 面板中加入标签
        toolPanel.add(MoveLabel.getInstance().getLabel());
        // 面板中加入工具条
        toolPanel.add(ToolBar.getInstance().getToolBar());
        // 把面板放到窗口中
        this.add(toolPanel, BorderLayout.NORTH);
    }

    /**
     * 设置窗体的默认位置
     */
    public static void defaultLocation() {
        ToolsWindow.getInstance().setLocation(80, 0);
    }
}