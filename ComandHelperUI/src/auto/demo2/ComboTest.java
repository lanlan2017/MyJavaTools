package auto.demo2;

import ui.key.MapKeyList;

import javax.swing.*;

public class ComboTest {
    public static void main(String[] args) {
        JFrame frame = new JFrame("auto");
        JPanel panel = new JPanel();
        AutoJTextField autoJTextField = new AutoJTextField();
        autoJTextField.addItem(MapKeyList.getKeysList());
        autoJTextField.setColumns(30);
        panel.add(autoJTextField);
        frame.add(panel);
        frame.pack();
        // 显示窗体
        frame.setVisible(true);
    }
}