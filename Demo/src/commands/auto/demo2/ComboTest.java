package commands.auto.demo2;

import ui.key.YamlTools;

import javax.swing.*;

public class ComboTest {
    public static void main(String[] args) {
        JFrame frame = new JFrame("commands/auto");
        JPanel panel = new JPanel();
        AutoJTextField autoJTextField = new AutoJTextField();
        autoJTextField.addItem(YamlTools.getCommands());
        autoJTextField.setColumns(30);
        panel.add(autoJTextField);
        frame.add(panel);
        frame.pack();
        // 显示窗体
        frame.setVisible(true);
    }
}
