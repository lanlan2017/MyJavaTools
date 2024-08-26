package adbs.main.ui.jpanels.act;

import adbs.main.AdbTools;
import adbs.main.run.AdbGetPackage;
import adbs.main.ui.jframe.JFramePack;
import adbs.main.ui.jpanels.act.model.AppTaskTime;
import adbs.main.ui.jpanels.act.model.TaskTimes;
import adbs.main.ui.jpanels.act.model.jaskson.file.JsonToFile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.function.Consumer;

/**
 *
 */
public class ActSignedInPanels {
    /**
     * 顶层面板
     */
    private final JPanel topJPanel;

    /**
     * 工具面板
     */
    private final JPanel toolPanel;
    /**
     * 更新按钮
     */
    private final JButton btnUpdate;
    /**
     * 任务面板
     */
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
                        updateAction();
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

    private void updateAction() {
        String appName = AdbGetPackage.getAppName();
        System.out.println("appName = " + appName);

        AppTask2 instance = AppTask2.getInstance();
        HashMap<String, AppTaskTime> tasks1 = instance.tasks;
        if (tasks1.containsKey(appName)) {
            taskPanel.removeAll();

            AppTaskTime appTaskTime = tasks1.get(appName);
            HashSet<TaskTimes> tasks = appTaskTime.getTasks();
            tasks.forEach(new Consumer<TaskTimes>() {
                @Override
                public void accept(TaskTimes taskTimes) {
                    String filePath = "task.json";
                    JsonToFile<AppTask2> jsonToFile = new JsonToFile<>();
                    //获取任务名
                    String name = taskTimes.getName();
                    //                    获取任务次数
                    int times = taskTimes.getTimes();
                    System.out.println("name = " + name + ",times = " + times);

                    JPanel jPanel = new JPanel();
                    jPanel.setLayout(new BorderLayout());
                    JCheckBox jCheckBox;


                    String s = name;
                    //                    if (s.endsWith("_")) {
                    jCheckBox = getjCheckBox(taskTimes, filePath, jsonToFile, instance);

                    if (times >= 0) {
                        //                        System.out.println("s = " + s);
                        //                        // 移除后面的标识符，也就是下划线
                        //                        String text = s.substring(0, s.length() - 1);
                        //                        System.out.println("text = " + text);
                        // 添加任务到列表中
                        //                        jCheckBox = new JCheckBox(text);
                        //                        jCheckBox = getjCheckBox(taskTimes, filePath, jsonToFile, instance);


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
                                        //                                        String s1 = text + "+1?";
                                        String s1 = name + "+1?";
                                        AdbTools.getInstance().showDialogOk(s1, new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e) {
                                                // 上箭头被按下，增加1
                                                int value_ = value + 1;

                                                textField.setText(Integer.toString(value_));

                                                taskTimes.setTimes(value_);
                                                jsonToFile.toJsonFile(instance, filePath);
                                            }
                                        });
                                        // 阻止加号键插入文本
                                        //                                    e.consume();
                                    } else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyChar() == '-') {
                                        // 下箭头被按下，减少1，但不能小于0
                                        if (value > 0) {
                                            //                                            String s1 = text + "-1?";
                                            String s1 = name + "-1?";

                                            AdbTools.getInstance().showDialogOk(s1, new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent e) {

                                                    int value_ = value - 1;
                                                    textField.setText(Integer.toString(value_));
                                                    jsonToFile.toJsonFile(instance, filePath);
                                                }
                                            });
                                        }
                                    }
                                } catch (NumberFormatException ex) {
                                    // 如果不是整数，则不执行任何操作
                                    ex.printStackTrace();
                                }
                            }
                        });


                        //                    jPanel.add(jCheckBox, BorderLayout.WEST);
                        jPanel.add(textField, BorderLayout.CENTER);
                        //                    jPanel.add(textField, BorderLayout.EAST);
                    }
                    //                    else {
                    //                        // 添加任务到列表中
                    //                        jCheckBox = new JCheckBox(s);
                    //                    }
                    //                jCheckBoxes.add(jCheckBox);
                    jPanel.add(jCheckBox, BorderLayout.WEST);
                    taskPanel.add(jPanel);


                }
            });


            //            tasks.contains()
            //            TaskTimes[] array = (TaskTimes[]) tasks.toArray();

            //            System.out.println("array = " + array);


            //            createJCheckBoxs();
        }

    }

    private JCheckBox getjCheckBox(TaskTimes taskTimes, String filePath, JsonToFile<AppTask2> jsonToFile, AppTask2 instance) {
        JCheckBox jCheckBox;
        jCheckBox = new JCheckBox(taskTimes.getName());
        // 添加ItemListener来监听复选框的状态变化
        jCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    //                                    textArea.append("已勾选\n");
                    //                                    System.out.println("已勾选");
                    //                                    taskTimes.setTimes(times + 1);

                    taskTimes.setSelected(true);
                    jsonToFile.toJsonFile(instance, filePath);
                } else {
                    taskTimes.setSelected(false);
                    //                                    taskTimes.setTimes(times - 1);
                    jsonToFile.toJsonFile(instance, filePath);
                    //                                    textArea.append("已取消勾选\n");
                    //                                    System.out.println("已取消勾选");
                }
            }
        });
        return jCheckBox;
    }

    /**
     * 根据任务名称数组创建复选框和文本框
     *
     * @param taskNames 任务名称数组
     */
    private void createJCheckBoxs(String[] taskNames) {
        taskPanel.removeAll();
        for (String s : taskNames) {
            JPanel jPanel = new JPanel();
            jPanel.setLayout(new BorderLayout());
            JCheckBox jCheckBox;

            if (s.endsWith("_")) {
                System.out.println("s = " + s);
                // 移除后面的标识符，也就是下划线
                String text = s.substring(0, s.length() - 1);
                System.out.println("text = " + text);
                // 添加任务到列表中
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

    public JPanel getTopJPanel() {
        return topJPanel;
    }
}
