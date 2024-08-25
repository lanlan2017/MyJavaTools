package adbs.main.ui.jpanels.act.dome;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JCheckBox;
import java.awt.FlowLayout;

public class CheckBoxArrayExample {

    public static void main(String[] args) {
        // 创建JFrame实例
        JFrame frame = new JFrame("Checkbox Array Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // 创建JPanel实例
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());  // 使用FlowLayout布局管理器
        
        // 创建CheckBox数组
        String[] items = {"Option 1", "Option 2", "Option 3", "Option 4"};
        JCheckBox[] checkBoxes = new JCheckBox[items.length];
        
        // 初始化CheckBox数组并添加到面板
        for (int i = 0; i < items.length; i++) {
            checkBoxes[i] = new JCheckBox(items[i]);
            panel.add(checkBoxes[i]);
        }
        
        // 将面板添加到框架
        frame.add(panel);
        
        // 设置框架大小并使其可见
        frame.setSize(300, 200);
        frame.setVisible(true);
    }
}
