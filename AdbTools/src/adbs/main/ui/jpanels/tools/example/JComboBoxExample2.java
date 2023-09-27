package adbs.main.ui.jpanels.tools.example;

import javax.swing.*;
import java.awt.event.*;

public class JComboBoxExample2 {

    public static void main(String[] args) {
        JFrame JF = new JFrame("窗口");
        JF.setSize(500, 500);//设置窗口大小
        JF.setLocationRelativeTo(null);//设置居中
        JF.setDefaultCloseOperation(3);//关闭程序释放资源

        JPanel JP = new JPanel();//添加一个面板
        JLabel JL = new JLabel("选择：");// 添加一个标签
        JP.add(JL);//向面板添加标签
        String[] a = new String[]{"A", "B", "C", "D" , "E"};// 创建需要选择的数据
        JComboBox JC = new JComboBox(a);// 创建一个下拉列表框
        JC.addItemListener(new ItemListener() {// 添加选中状态改变的监听器
            public void itemStateChanged(ItemEvent e) {

            	//SELECTED 此状态更改值指示项被选定
            	//getStateChange()返回被影响的方式：去选择/选择
                if (e.SELECTED == e.getStateChange()) {
                	//getSelectedItem()返回当前所选项
                	//getSelectedIndex()返回列表中与给定项匹配的第一个选项。
                    System.out.println("你选择了："+JC.getSelectedItem()+"是第"+ (JC.getSelectedIndex()+1)+"个选项");

                }


            }
        });
        JC.setSelectedIndex(0);//设置默认选中
        JP.add(JC);//将下拉列表框添加到面板
        JF.setContentPane(JP);//将面板添加到窗口
        JF.setVisible(true);
    }

}

