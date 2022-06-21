package adbs.test.auto;

import adbs.buttons.AbstractButtons;
import com.formdev.flatlaf.FlatLightLaf;
import tools.thead.Threads;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileFilter;

public class Buttons {
    static {
        // 设置外观,先设置外观，再创建UI。
        // 为了保证创建UI时，已经设置好外观，设置外观的代码最好写在静态块中，
        // 并且把这个静态块写在类定义的最前面。
        FlatLightLaf.setup();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JPanel contentPane = new JPanel();
        frame.setContentPane(contentPane);
        // 使用箱型布局,垂直排列
        frame.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        JPanel jPanel0 = new JPanel();
        jPanel0.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON3) {
                    System.out.println("双击主面板");
                    frame.pack();
                }
            }
        });

        JPanel jPanel1 = new JPanel();
        extracted(frame, jPanel1);
        JCheckBox jCheckBox = new JCheckBox("其他", true);
        jCheckBox.addItemListener(new ItemListener() {
            // 被控制的JPanel
            private JPanel itemJpanel = jPanel1;
            private int index;

            @Override
            public void itemStateChanged(ItemEvent e) {
                // 获取窗体的内容面板
                Container contentPane = frame.getContentPane();
                // 如果当前的状态是勾选状态
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    // 显示被控制的面板
                    itemJpanel.setVisible(true);
                    // 添加被控制的面板到指定的位置
                    contentPane.add(itemJpanel, index);
                } else {
                    // 隐藏面板
                    itemJpanel.setVisible(false);
                    // 组件数组
                    Component[] comps = contentPane.getComponents();
                    // 遍历组件数组
                    for (int i = 0; i < comps.length; i++) {
                        if (comps[i] instanceof JPanel) {
                            JPanel comp = (JPanel) comps[i];
                            // 如果找到
                            if (comp.equals(itemJpanel)) {
                                // 移除组件
                                contentPane.remove(comps[i]);
                                index = i;
                            }
                        }
                    }
                }
                // 以最佳方式显示
                frame.pack();
            }
        });

        jPanel0.add(jCheckBox);
        frame.add(jPanel0);

        // 设置关闭按钮功能
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // 显示窗体
        frame.setVisible(true);
        // 调整窗体到最佳大小
        frame.pack();
    }

    private static void extracted(JFrame frame, JPanel jPanel1) {
        File dir = new File("G:\\dev2\\idea_workspace\\MyJavaTools\\AdbTools\\Pythons");
        File[] dirList = dir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });

        for (File dirDeep1 : dirList) {
            if (dirDeep1.isDirectory()) {
                // 列出子目录
                File[] dirDeep1List = dirDeep1.listFiles(new FileFilter() {
                    @Override
                    public boolean accept(File pathname) {
                        return pathname.isDirectory();
                    }
                });
                // 如果存在子目录
                if (dirDeep1List.length > 0) {
                    String name = dirDeep1.getName();
                    System.out.println(name);
                    JPanel jPanel = new JPanel();
                    jPanel.setBorder(new TitledBorder(name));
                    FlowLayout layout = new FlowLayout(FlowLayout.LEADING, 0, 0);
                    // layout.setAlignment();
                    jPanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
                    jPanel.setAlignmentY(JPanel.CENTER_ALIGNMENT);
                    jPanel.setLayout(layout);
                    for (File dirDeep2 : dirDeep1List) {
                        String name1 = dirDeep2.getName();
                        if (!name1.contains("test") && !name1.contains("demo")) {
                            JButton button = new JButton(name1);
                            AbstractButtons.setJButtonMargin(button);
                            jPanel.add(button);
                        }
                    }
                    frame.add(jPanel);
                }
                // 如果不存在子目录
                else {
                    JButton button = new JButton(dirDeep1.getName());
                    AbstractButtons.setJButtonMargin(button);
                    jPanel1.add(button);
                }
            }
        }
        int jPanel1ComponentCount = jPanel1.getComponentCount();
        // 向上取整
        int rows = (int) Math.ceil(jPanel1ComponentCount / 4.0);
        jPanel1.setLayout(new GridLayout(rows, 4, 0, 0));
        jPanel1.setBorder(new TitledBorder("Other"));
        frame.add(jPanel1);
    }
}
