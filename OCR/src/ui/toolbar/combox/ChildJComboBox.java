package ui.toolbar.combox;

import javax.swing.*;

/**
 * 二级选择框
 */
public class ChildJComboBox {

    private static ChildJComboBox instance = new ChildJComboBox();
    JComboBox<String> comboBox;

    private ChildJComboBox() {
    }

    public static ChildJComboBox getInstance() {
        return instance;
    }

    public JComboBox<String> getComboBox() {
        return comboBox;
    }

    public void setComboBox(JComboBox<String> comboBox) {
        this.comboBox = comboBox;
    }
}
