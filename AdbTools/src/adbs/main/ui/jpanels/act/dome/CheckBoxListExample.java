package adbs.main.ui.jpanels.act.dome;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import java.util.ArrayList;

public class CheckBoxListExample {

    private JFrame frame;
    private JPanel panel;
    private ArrayList<JCheckBox> checkBoxes = new ArrayList<>();
    private String[] items = {"Option 1", "Option 2", "Option 3", "Option 4"};

    public static void main(String[] args) {
        new CheckBoxListExample().run();
    }

    private void run() {
        prepareGUI();
    }

    private void prepareGUI() {
        frame = new JFrame("Checkbox List Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();

        // 添加复选框到panel
        for (String item : items) {
            JCheckBox cb = new JCheckBox(item);
            panel.add(cb);
            checkBoxes.add(cb);
        }

        GroupLayout layout = new GroupLayout(frame.getContentPane());
        frame.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(Alignment.CENTER)
            .addComponent(panel, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(Alignment.CENTER)
            .addComponent(panel, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
        );

        // 设置框架大小并使其可见
        frame.pack();
        frame.setVisible(true);
    }
}
