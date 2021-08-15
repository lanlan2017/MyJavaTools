package ui.toolbar.buttons;

import ui.setting.FontTools;

import javax.swing.*;

/**
 * 退出按钮
 */
public class ExitButton {

    private static ExitButton instance = new ExitButton();
    private JButton exitButton;

    private ExitButton() {
        exitButton = new JButton("退出");
        exitButton.setFont(FontTools.f1);
        exitButton.addActionListener(e -> System.exit(0));
    }

    public static ExitButton getInstance() {
        return instance;
    }

    public JButton getExitButton() {
        return exitButton;
    }
}
