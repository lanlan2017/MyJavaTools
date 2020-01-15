package ui;

import ui.toolbar.ToolBar;

import javax.swing.*;
import java.awt.*;

/*
 * 操作窗口
 */
public class ToolsWindow extends JWindow {
    private static final ToolsWindow instance = new ToolsWindow(0, 0);
    private static final long serialVersionUID = 1L;

    private ToolsWindow(int x, int y) {
        this.init();
        this.setLocation(x, y);
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
        this.add(ToolBar.getInstance().getToolBar(), BorderLayout.NORTH);
    }
}