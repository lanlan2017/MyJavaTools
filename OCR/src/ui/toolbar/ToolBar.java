package ui.toolbar;

import ui.toolbar.buttons.*;
import ui.toolbar.combox.ChildJComboBox;
import ui.toolbar.combox.FormatterGroupSelector;

import javax.swing.*;

/**
 * @author francis
 * create at 2020/1/15-0:49
 */
public class ToolBar {
    private static ToolBar instance = new ToolBar();
    private JToolBar toolBar;

    private ToolBar() {
        toolBar = new JToolBar("Java 截图");
        // 设置工具条不可拖动
        toolBar.setFloatable(false);
        addToolBarButtons();
    }

    /**
     * 在工具条上添加各种按钮.
     */
    public void addToolBarButtons() {
        // 添加 移动 标签
        toolBar.add(MoveLabel.getInstance().getLabel());
        // 添加 截屏 按钮
        toolBar.add(SreenShotButton.getInstance().getStartButton());
        // 添加 取消截屏 按钮
        toolBar.add(SreenShotButton.getInstance().getCancelButton());
        // 添加 文字识别 按钮
        //BaiduOCRButton.addBaiduOCRButton(toolBar);
        toolBar.add(BaiduOCRButton.getInstance().getBaiduOCRButton());
        // 添加 格式化器 一级选择框
        toolBar.add(FormatterGroupSelector.getInstance().getComboBox());
        // 添加 格式化器 二级选择框
        JComboBox<String> childComboBox = ChildJComboBox.getInstance().getComboBox();
        if (childComboBox != null) {
            toolBar.add(childComboBox);
        }
        // 添加 退出 按钮
        toolBar.add(ExitButton.getInstance().getCloseButton());
    }

    public static ToolBar getInstance() {
        return instance;
    }

    public JToolBar getToolBar() {
        return toolBar;
    }
}
