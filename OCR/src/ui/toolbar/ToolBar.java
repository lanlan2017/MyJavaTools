package ui.toolbar;

import ui.ToolsWindow;
import ui.toolbar.buttons.*;
import ui.toolbar.combox.first.FirstComBox;

import javax.swing.*;

/**
 * 工具条
 */
public class ToolBar {
    private static ToolBar instance = new ToolBar();
    // 工具条
    private JToolBar toolBar;
    /**
     * 二级选择框
     */
    private JComboBox<String> subCombox;

    private ToolBar() {
        // panel=new JPanel();
        // panel.add(MoveLabel.getInstance().getLabel());
        // moveLable=MoveLabel.getInstance().getLabel();
        // moveLable.setVisible(true);
        toolBar = new JToolBar("Java 截图");
        // 设置工具条不可拖动
        toolBar.setFloatable(false);
        // 添加按钮到工具条上
        addToolBarButtons();
        // panel.setVisible(true);
    }

    /**
     * 重绘工具条
     */

    public void repaintToolBar() {
        ToolBar.getInstance().getToolBar().removeAll();
        ToolBar.getInstance().addToolBarButtons();
        ToolsWindow.getInstance().pack();
    }

    /**
     * 在工具条上添加各种按钮.
     */
    public void addToolBarButtons() {
        // 添加 移动 标签
        // toolBar.add(MoveLabel.getInstance().getLabel());
        // 添加 截屏 按钮
        toolBar.add(SreenShotButtons.getInstance().getStartButton());
        // 添加 取消截屏 按钮
        toolBar.add(SreenShotButtons.getInstance().getCancelButton());
        // 添加 文字识别 按钮
        //BaiduOCRButton.addBaiduOCRButton(toolBar);
        toolBar.add(BaiduOCRButton.getInstance().getBaiduOCRButton());
        // 添加 格式化器 一级选择框
        toolBar.add(FirstComBox.getInstance().getComboBox());
        // 添加 格式化器 二级选择框
        if (subCombox != null) {
            toolBar.add(subCombox);
        }
        // 添加 退出 按钮
        toolBar.add(ExitButton.getInstance().getExitButton());
        // panel.add(toolBar);
    }

    public static ToolBar getInstance() {
        return instance;
    }

    public JToolBar getToolBar() {
        return toolBar;
    }

    public void setSubCombox(JComboBox<String> subCombox) {
        this.subCombox = subCombox;
    }
}
