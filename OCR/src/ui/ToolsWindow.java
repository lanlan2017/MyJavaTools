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
        //JToolBar toolBar = new JToolBar("Java 截图");
        //// 设置工具条不可拖动
        //toolBar.setFloatable(false);
        //addToolBarButtons(toolBar);
        //this.add(toolBar, BorderLayout.NORTH);
        this.add(ToolBar.getInstance().getToolBar(), BorderLayout.NORTH);
    }

    ///**
    // * 在工具条上添加各种按钮.
    // *
    // * @param toolBar 工具条.
    // */
    //private void addToolBarButtons(JToolBar toolBar) {
    //    // 添加 移动 标签
    //    toolBar.add(MoveLabel.getInstance().getLabel());
    //    // 添加 截屏 按钮
    //    toolBar.add(SreenShotButton.getInstance().getStartButton());
    //    // 添加 取消截屏 按钮
    //    toolBar.add(SreenShotButton.getInstance().getCancelButton());
    //    // 添加 文字识别 按钮
    //    //BaiduOCRButton.addBaiduOCRButton(toolBar);
    //    toolBar.add(BaiduOCRButton.getInstance().getBaiduOCRButton());
    //    toolBar.add(FormatterGroupSelector.getInstance().getComboBox());
    //    // 添加 格式化器 选择框
    //    toolBar.add(FormatterJComboBoxAll.getInstance().getComboBox());
    //    // 添加 退出 按钮
    //    toolBar.add(ExitButton.getInstance().getCloseButton());
    //}
}