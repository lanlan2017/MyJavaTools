package adbs.main.ui.jpanels.act;

import adbs.main.AdbTools;
import adbs.main.run.AdbGetPackage;
import adbs.main.run.signinlog.FileUtil;
import adbs.main.ui.jframe.JFramePack;
import adbs.main.ui.jpanels.act.jaskson.file.JsonToFile;
import adbs.main.ui.jpanels.act.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
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
        //        AppTask2 appTask2;
        AppTask3 appTask3;

        //        JsonToFile<AppTask2> jsonToFile = new JsonToFile<>();
        JsonToFile<AppTask3> jsonToFile = new JsonToFile<>();


        // 获取当前这个设备对应的JSON文件路径
        String filePath = AdbTools.getInstance().getDevice().getActTaskJSON();
        System.out.println("filePath = " + filePath);
        //如果文件不存在这创建这个文件，如果创建了文件，则返回true，否则返回false
        boolean needToCreateFile = FileUtil.isNeedToCreateFile(filePath);
        //如果返回的是false，也就是上面的方法没有创建文件，也就是有旧文件
        if (!needToCreateFile) {
            //从旧文件中反序列化对象
            //            AppTask2 appTask_old = jsonToFile.fromJsonFile(filePath, AppTask2.class);
            AppTask3 appTask_old = jsonToFile.fromJsonFile(filePath, AppTask3.class);

            if (appTask_old != null) {
                String date = appTask_old.getDate();
                if (date.matches("\\d\\d\\d\\d-\\d\\d-\\d\\d")) {
                    System.out.println("有当前APP的打开记录！。。。。。。。。。。。。。。。。。");
                    //                    appTask2 = appTask_old;
                    appTask3 = appTask_old;
                } else {
                    System.out.println("有文件，日期格式不对");
                    //                    appTask2 = new AppTask2();
                    appTask3 = new AppTask3();
                }
            } else {
                System.out.println("遇到null");
                //                appTask2 = new AppTask2();
                appTask3 = new AppTask3();
            }
        } else {
            System.out.println("新建JSON文件");
            //            appTask2 = new AppTask2();
            appTask3 = new AppTask3();
        }

        String appName = AdbGetPackage.getAppName();
        System.out.println("appName = " + appName);
        //        updatePanels(filePath, jsonToFile, appName, appTask2);
        updatePanels2(filePath, jsonToFile, appName, appTask3);


        JFramePack.pack();

    }

    private void updatePanels2(String filePath, JsonToFile<AppTask3> jsonToFile, String appName, AppTask3 appTask3) {
        HashSet<AppTaskTimeSet> tasks = appTask3.getTasks();
        //        if (tasks.contains(new AppTaskTimeSet(appName))) {
        //
        //        }
        Iterator<AppTaskTimeSet> iterator = tasks.iterator();
        while (iterator.hasNext()) {

            AppTaskTimeSet next = iterator.next();
            System.out.println("next = " + next);
            if (next.getAppName().equals(appName)) {
                System.out.println("找到该应用的任务列表");
                //移除所有面板
                taskPanel.removeAll();
                HashSet<TaskTime> taskTimeSet = next.getTaskTimeSet();
                taskTimeSet.forEach(new Consumer<TaskTime>() {
                    @Override
                    public void accept(TaskTime taskTime) {
                        //获取任务名
                        String taskName = taskTime.getTaskName();
                        //                    获取任务次数
                        int times = taskTime.getTimes();

                        JPanel jPanel = new JPanel();
                        jPanel.setLayout(new BorderLayout());

                        JCheckBox jCheckBox;
//                        jCheckBox = getjCheckBox(taskTime, filePath, jsonToFile, appTask2);
                        jCheckBox = getjCheckBox(taskTime, filePath, jsonToFile, appTask3);

                        jPanel.add(jCheckBox, BorderLayout.WEST);

                        if (times >= 0) {
                            JTextField textField = new JTextField();
                            // 设置为不可编辑
                            textField.setEditable(false);
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
                                            String s1 = taskName + "+1?";
                                            AdbTools.getInstance().showDialogOk(s1, new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent e) {
                                                    // 上箭头被按下，增加1
                                                    int value_ = value + 1;

                                                    textField.setText(Integer.toString(value_));

                                                    taskTime.setTimes(value_);
//                                                    jsonToFile.toJsonFile(appTask2, filePath);
                                                    jsonToFile.toJsonFile(appTask3, filePath);
                                                }
                                            });
                                        } else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyChar() == '-') {
                                            // 下箭头被按下，减少1，但不能小于0
                                            if (value > 0) {
                                                String s1 = taskName + "-1?";
                                                AdbTools.getInstance().showDialogOk(s1, new ActionListener() {
                                                    @Override
                                                    public void actionPerformed(ActionEvent e) {
                                                        int value_ = value - 1;
                                                        textField.setText(Integer.toString(value_));
//                                                        jsonToFile.toJsonFile(appTask2, filePath);
                                                        jsonToFile.toJsonFile(appTask3, filePath);
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
                            jPanel.add(textField, BorderLayout.CENTER);
                        }
                        taskPanel.add(jPanel);

                    }
                });
            }
        }
    }

    //    private void updatePanels(String filePath, JsonToFile<AppTask2> jsonToFile, String appName, AppTask2 appTask2) {
    private void updatePanels(String filePath, JsonToFile<AppTask2> jsonToFile, String appName, AppTask2 appTask2) {
        HashMap<String, TaskTimeSet> tasks1 = appTask2.getTasks();
        if (tasks1.containsKey(appName)) {
            //移除所有面板
            taskPanel.removeAll();

            TaskTimeSet taskTimeSet = tasks1.get(appName);

            HashSet<TaskTime> tasks = taskTimeSet.getTasks();

            tasks.forEach(new Consumer<TaskTime>() {
                @Override
                public void accept(TaskTime taskTime) {
                    //获取任务名
                    String taskName = taskTime.getTaskName();
                    //                    获取任务次数
                    int times = taskTime.getTimes();

                    JPanel jPanel = new JPanel();
                    jPanel.setLayout(new BorderLayout());

                    JCheckBox jCheckBox;
                    jCheckBox = getjCheckBox(taskTime, filePath, jsonToFile, appTask2);

                    jPanel.add(jCheckBox, BorderLayout.WEST);

                    if (times >= 0) {
                        JTextField textField = new JTextField();
                        // 设置为不可编辑
                        textField.setEditable(false);
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
                                        String s1 = taskName + "+1?";
                                        AdbTools.getInstance().showDialogOk(s1, new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e) {
                                                // 上箭头被按下，增加1
                                                int value_ = value + 1;

                                                textField.setText(Integer.toString(value_));

                                                taskTime.setTimes(value_);
                                                jsonToFile.toJsonFile(appTask2, filePath);
                                            }
                                        });
                                    } else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyChar() == '-') {
                                        // 下箭头被按下，减少1，但不能小于0
                                        if (value > 0) {
                                            String s1 = taskName + "-1?";
                                            AdbTools.getInstance().showDialogOk(s1, new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent e) {
                                                    int value_ = value - 1;
                                                    textField.setText(Integer.toString(value_));
                                                    jsonToFile.toJsonFile(appTask2, filePath);
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
                        jPanel.add(textField, BorderLayout.CENTER);
                    }
                    taskPanel.add(jPanel);

                }
            });

        }
    }

    private JCheckBox getjCheckBox(TaskTime taskTime, String filePath, JsonToFile<AppTask3> jsonToFile, AppTask3 appTask2) {
        JCheckBox jCheckBox;
        jCheckBox = new JCheckBox(taskTime.getTaskName());
        boolean selected = taskTime.isSelected();
        System.out.println("selected = " + selected);
        jCheckBox.setSelected(selected);
        // 添加ItemListener来监听复选框的状态变化
        jCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    taskTime.setSelected(true);
                    jsonToFile.toJsonFile(appTask2, filePath);
                } else {
                    taskTime.setSelected(false);
                    jsonToFile.toJsonFile(appTask2, filePath);
                }
            }
        });
        return jCheckBox;
    }


    private JCheckBox getjCheckBox(TaskTime taskTime, String filePath, JsonToFile<AppTask2> jsonToFile, AppTask2 appTask2) {
        JCheckBox jCheckBox;
        jCheckBox = new JCheckBox(taskTime.getTaskName());
        boolean selected = taskTime.isSelected();
        System.out.println("selected = " + selected);
        jCheckBox.setSelected(selected);
        // 添加ItemListener来监听复选框的状态变化
        jCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    taskTime.setSelected(true);
                    jsonToFile.toJsonFile(appTask2, filePath);
                } else {
                    taskTime.setSelected(false);
                    jsonToFile.toJsonFile(appTask2, filePath);
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
