package adbs.main.ui.jpanels.act;

import adbs.main.AdbTools;
import adbs.main.run.AdbGetPackage;
import adbs.main.ui.jframe.JFramePack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 */
public class ActSignedInPanels {
    private final JPanel topJPanel;

    private final JPanel toolPanel;
    private final JButton btnUpdate;
    private final JPanel taskPanel;
    //    private final ArrayList<JCheckBox> jCheckBoxes = new ArrayList<>();

    public ActSignedInPanels() {
        this.topJPanel = new JPanel();
        this.topJPanel.setLayout(new BorderLayout());

        this.taskPanel = new JPanel();
        taskPanel.setLayout(new BoxLayout(taskPanel, BoxLayout.Y_AXIS)); // 垂直排列
        this.toolPanel = new JPanel();
        this.toolPanel.setLayout(new BoxLayout(toolPanel, BoxLayout.X_AXIS));

        this.btnUpdate = new JButton("更新任务");
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdbTools.getInstance().showDialogOk("更新任务", new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        extracted();
                    }
                });
            }
        });

        toolPanel.add(btnUpdate);


        //        topJPanel.add(btnUpdate, BorderLayout.NORTH);
        topJPanel.add(toolPanel, BorderLayout.NORTH);
        topJPanel.add(taskPanel, BorderLayout.CENTER);
        topJPanel.setVisible(false);
    }

    private void extracted() {
        String appName = AdbGetPackage.getAppName();
        System.out.println("appName = " + appName);
        //        if (appName != null && appName.equals("番茄畅听音乐版")) {
        if (appName != null && appName.equals("点淘")) {
            String[] task = {"商城", "红包_", "购金", "开店", "签到", "打工_", "睡觉_", "走路_"};
            //                    移除所有的组件
            taskPanel.removeAll();
            //                    清空任务列表
            //            jCheckBoxes.clear();
            for (String s : task) {
                JPanel jPanel = new JPanel();
                jPanel.setLayout(new BorderLayout());
                JCheckBox jCheckBox;

                if (s.endsWith("_")) {
                    System.out.println("s = " + s);
                    // 移除后面的标识符，也就是下划线
                    String text = s.substring(0, s.length() - 1);
                    System.out.println("text = " + text);
                    // 添加任务到列表中
                    //                    JCheckBox jCheckBox = new JCheckBox(text);
                    jCheckBox = new JCheckBox(text);

                    JTextField textField = new JTextField();
                    textField.setEditable(false);  // 设置为不可编辑
                    textField.setColumns(2);

                    textField.setText("0");  // 初始化为0

                    // 添加键盘事件监听器
                    textField.addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyPressed(KeyEvent e) {
                            try {
                                int value = Integer.parseInt(textField.getText());
                                //当按下上箭头或者加号的时候
                                if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyChar() == '+') {
                                    String s1 = text + "+1?";
                                    AdbTools.getInstance().showDialogOk(s1, new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {

                                            // 上箭头被按下，增加1
                                            textField.setText(Integer.toString(value + 1));
                                        }
                                    });
                                    // 阻止加号键插入文本
                                    //                                    e.consume();
                                } else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyChar() == '-') {
                                    // 下箭头被按下，减少1，但不能小于0
                                    if (value > 0) {
                                        String s1 = text + "-1?";

                                        AdbTools.getInstance().showDialogOk(s1, new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e) {

                                                textField.setText(Integer.toString(value - 1));
                                            }
                                        });
                                    }
                                }
                            } catch (NumberFormatException ex) {
                                // 如果不是整数，则不执行任何操作
                            }
                        }
                    });


                    //                    jPanel.add(jCheckBox, BorderLayout.WEST);
                    jPanel.add(textField, BorderLayout.CENTER);
                    //                    jPanel.add(textField, BorderLayout.EAST);
                } else {
                    // 添加任务到列表中
                    jCheckBox = new JCheckBox(s);
                }
                //                jCheckBoxes.add(jCheckBox);
                jPanel.add(jCheckBox, BorderLayout.WEST);
                taskPanel.add(jPanel);
            }

            JFramePack.pack();
        }
    }

    public JPanel getTopJPanel() {
        return topJPanel;
    }
}
