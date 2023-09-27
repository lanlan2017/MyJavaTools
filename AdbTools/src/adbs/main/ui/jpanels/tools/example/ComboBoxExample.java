package adbs.main.ui.jpanels.tools.example;

import adbs.main.ui.config.FlowLayouts;

import javax.swing.*;

public class ComboBoxExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("JComboBox Width Example");
        JPanel jPanel = new JPanel();
        jPanel.setLayout(FlowLayouts.flowLayoutLeft);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 创建一个自定义对象作为JComboBox的一个项目  
        MyCustomObject myCustomObject = new MyCustomObject("aaaa");
        // MyCustomObject myCustomObject = new MyCustomObject("xx");
        JComboBox<MyCustomObject> comboBox = new JComboBox<>();
        comboBox.addItem(new MyCustomObject("bbbbbbbbbbbbb"));
        comboBox.addItem(new MyCustomObject("cc"));
        comboBox.addItem(myCustomObject);

        // 设置原型显示值来调整宽度  
        // comboBox.setPrototypeDisplayValue(myCustomObject);

        // JScrollPane scrollPane = new JScrollPane(comboBox);
        // frame.add(scrollPane);
        jPanel.add(comboBox);
        jPanel.add(new JLabel("你好嘻嘻嘻 嘻嘻嘻嘻嘻嘻嘻嘻寻寻寻寻寻寻寻寻寻"));

        frame.add(jPanel);

        frame.pack();
        frame.setVisible(true);
    }
}

class MyCustomObject {
    private String name;

    public MyCustomObject(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}