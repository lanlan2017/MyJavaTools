package ui.toolbar.buttons;

import javax.swing.*;

/**
 * @author francis
 * create at 2020/1/13-15:33
 */
public class ExitButton {

    private static ExitButton instance = new ExitButton();
    private JButton closeButton;

    private ExitButton() {
        closeButton = new JButton("退出");
        closeButton.addActionListener(e -> System.exit(0));
    }

    public static ExitButton getInstance() {
        return instance;
    }

    public JButton getCloseButton() {
        return closeButton;
    }
    ///**
    // * 在工具条上添加取消截屏按钮.
    // *
    // * @param toolBar 工具条对象.
    // */
    //public static void addExitButton(JToolBar toolBar) {
    //    // 关闭按钮
    //    closeButton.addActionListener(e -> System.exit(0));
    //    toolBar.add(closeButton);
    //}
}
