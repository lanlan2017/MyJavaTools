package adbs.main.ui.jpanels.act.dome;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CheckBoxListExampleWithButton {

    private JFrame frame;
    private JPanel panel;
    private ArrayList<JCheckBox> checkBoxes = new ArrayList<>();
    private JButton addButton = new JButton("Add Checkbox");

    public static void main(String[] args) {
        new CheckBoxListExampleWithButton().run();
    }

    private void run() {
        prepareGUI();
    }

    private void prepareGUI() {
        frame = new JFrame("Checkbox List Example with Button");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JCheckBox cb = new JCheckBox("New Option");
                panel.add(cb);
                checkBoxes.add(cb);
                frame.revalidate(); // 重新验证布局
                frame.repaint();   // 重新绘制组件
            }
        });

        GroupLayout layout = new GroupLayout(frame.getContentPane());
        frame.getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
            layout.createParallelGroup(Alignment.CENTER)
            .addComponent(panel, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
            .addComponent(addButton, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createSequentialGroup()
            .addComponent(panel, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
            .addComponent(addButton)
        );

        // 设置框架大小并使其可见
        frame.pack();
        frame.setVisible(true);
    }
}
