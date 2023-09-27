package adbs.main.ui.jpanels.tools.example;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JComboBoxExample {
    private JFrame mainFrame;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel controlPanel;

    public JComboBoxExample() {
        prepareGUI();
    }

    public static void main(String[] args) {
        JComboBoxExample swingControlDemo = new JComboBoxExample();
        swingControlDemo.showComboboxDemo();
    }

    private void prepareGUI() {
        mainFrame = new JFrame("Java Swing JCombox示例");
        mainFrame.setSize(400, 400);
        mainFrame.setLayout(new GridLayout(3, 1));

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        headerLabel = new JLabel("", JLabel.CENTER);
        statusLabel = new JLabel("", JLabel.CENTER);
        statusLabel.setSize(350, 100);

        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        mainFrame.add(headerLabel);
        mainFrame.add(controlPanel);
        mainFrame.add(statusLabel);
        mainFrame.setVisible(true);
    }

    private void showComboboxDemo() {
        headerLabel.setText("Control in action: JComboBox");
        final DefaultComboBoxModel fruitsName = new DefaultComboBoxModel();

        fruitsName.addElement("Java");
        fruitsName.addElement("Python");
        fruitsName.addElement("MySQL");
        fruitsName.addElement("Perl");

        final JComboBox jComboBox = new JComboBox(fruitsName);
        jComboBox.setSelectedIndex(0);
// jComboBox.add


        JScrollPane fruitListScrollPane = new JScrollPane(jComboBox);
        JButton showButton = new JButton("Show");

        showButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String data = "";
                if (jComboBox.getSelectedIndex() != -1) {
                    data = "Language Selected: " + jComboBox.getItemAt(jComboBox.getSelectedIndex());
                }
                statusLabel.setText(data);
            }
        });
        controlPanel.add(fruitListScrollPane);
        controlPanel.add(showButton);
        mainFrame.setVisible(true);
    }
}

