package adbs.main.ui.jpanels.act;

import adbs.main.AdbTools;
import adbs.main.run.AdbGetPackage;
import adbs.main.run.signinlog.FileUtil;
import adbs.main.ui.jframe.JFramePack;
import adbs.main.ui.jpanels.act.jaskson.file.JsonToFile;
import adbs.main.ui.jpanels.act.model.AppTask3;
import adbs.main.ui.jpanels.act.model.AppTaskTimeSet;
import adbs.main.ui.jpanels.act.model.TaskTime;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
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
        // 垂直排列
        taskPanel.setLayout(new BoxLayout(taskPanel, BoxLayout.Y_AXIS));

        this.toolPanel = new JPanel();
        this.toolPanel.setLayout(new BoxLayout(toolPanel, BoxLayout.X_AXIS));

        this.btnUpdate = new JButton("更新任务");
        btnUpdate.addActionListener(e -> AdbTools.getInstance().showDialogOk("更新任务", e1 -> updateAction()));

        toolPanel.add(btnUpdate);


        // topJPanel.add(btnUpdate, BorderLayout.NORTH);
        topJPanel.add(toolPanel, BorderLayout.NORTH);
        topJPanel.add(taskPanel, BorderLayout.CENTER);
        topJPanel.setVisible(false);
    }

    private void updateAction() {
        AppTask3 appTask3;

        JsonToFile<AppTask3> jsonToFile = new JsonToFile<>();


        // 获取当前这个设备对应的JSON文件路径
        String filePath = AdbTools.getInstance().getDevice().getActTaskJSON();
        System.out.println("filePath = " + filePath);
        //如果文件不存在这创建这个文件，如果创建了文件，则返回true，否则返回false
        boolean needToCreateFile = FileUtil.isNeedToCreateFile(filePath);
        //如果返回的是false，也就是上面的方法没有创建文件，也就是有旧文件
        if (!needToCreateFile) {
            //从旧文件中反序列化对象
            // AppTask2 appTask_old = jsonToFile.fromJsonFile(filePath, AppTask2.class);
            AppTask3 appTask_old = jsonToFile.fromJsonFile(filePath, AppTask3.class);

            if (appTask_old != null) {
                String oldDate = appTask_old.getDate();
                String date = DateString.getDate_yyyyMMdd();
                System.out.println("oldDate = " + oldDate);
                //检查日期格式
                if (oldDate.matches("\\d\\d\\d\\d-\\d\\d-\\d\\d")) {
                    System.out.println("有当前APP中的任务的打卡记录，。。。。。。。。。。。。。。。。。");
                    // appTask2 = appTask_old;
                    System.out.println("date = " + date);
                    if (date.equals(oldDate)) {
                        System.out.println("是今天的打卡记录");
                        appTask3 = appTask_old;
                    } else {
                        System.out.println("不是今天的打卡记录，记录已过期");
                        appTask3 = new AppTask3();
                    }

                } else {
                    System.out.println("有文件，日期格式不对");
                    // appTask2 = new AppTask2();
                    appTask3 = new AppTask3();
                }
            } else {
                System.out.println("遇到null");
                // appTask2 = new AppTask2();
                appTask3 = new AppTask3();
            }
        } else {
            System.out.println("新建JSON文件");
            // appTask2 = new AppTask2();
            appTask3 = new AppTask3();
        }

        String appName = AdbGetPackage.getAppName();
        System.out.println("appName = " + appName);
        updateTaskPanel(filePath, jsonToFile, appName, appTask3);
        //调整窗体到合适的大小
        JFramePack.pack();
    }

    private void updateTaskPanel(String filePath, JsonToFile<AppTask3> jsonToFile, String appName, AppTask3 appTask3) {
        //获取应用
        ArrayList<AppTaskTimeSet> tasks = appTask3.getTasks();
        //获取任务的迭代器
        Iterator<AppTaskTimeSet> iterator = tasks.iterator();
        while (iterator.hasNext()) {
            AppTaskTimeSet next = iterator.next();
            // System.out.println("next = " + next);
            //如果查找这个应用的任务
            if (next.getAppName().equals(appName)) {
                System.out.println("找到应用" + appName + "的任务列表");
                //移除所有面板
                taskPanel.removeAll();
                addTaskPanel(filePath, jsonToFile, appTask3, next);

            }
        }
    }

    private void addTaskPanel(String filePath, JsonToFile<AppTask3> jsonToFile, AppTask3 appTask3, AppTaskTimeSet next) {
        //取出这个应用的任务集合
        ArrayList<TaskTime> taskTimeSet = next.getTaskTimeSet();
        //遍历任务集合
        taskTimeSet.forEach(new Consumer<TaskTime>() {
            @Override
            public void accept(TaskTime taskTime) {
                JPanel checkTextPanel = getCheckTextPanel(taskTime, filePath, jsonToFile, appTask3);
                taskPanel.add(checkTextPanel);

            }
        });
    }

    private JPanel getCheckTextPanel(TaskTime taskTime, String filePath, JsonToFile<AppTask3> jsonToFile, AppTask3 appTask3) {

        //存放多选框和文本框的面板
        JPanel checkTextPanel = new JPanel();
        checkTextPanel.setLayout(new BorderLayout());

        int times = taskTime.getTimes();
        String taskName = taskTime.getTaskName();
        JCheckBox jCheckBox;
        jCheckBox = getjCheckBox(taskTime, filePath, jsonToFile, appTask3);
        checkTextPanel.add(jCheckBox, BorderLayout.WEST);

        if (times >= 0) {
            JTextField textField = getjTextField(taskTime, taskName, jsonToFile, appTask3, filePath);
            checkTextPanel.add(textField, BorderLayout.CENTER);
        }
        return checkTextPanel;
    }

    private JTextField getjTextField(TaskTime taskTime, String taskName, JsonToFile<AppTask3> jsonToFile, AppTask3 appTask3, String filePath) {
        JTextField textField = new JTextField();
        // 设置为不可编辑
        textField.setEditable(false);
        textField.setColumns(2);

        // textField.setText("0");  // 初始化为0
        int times = taskTime.getTimes();
        textField.setText(String.valueOf(times));

        // 添加键盘事件监听器
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                try {
                    int value = Integer.parseInt(textField.getText());
                    //当按下上箭头或者加号的时候
                    if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyChar() == '+') {
                        // String s1 = text + "+1?";
                        String s1 = taskName + "+1?";
                        AdbTools.getInstance().showDialogOk(s1, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                // 上箭头被按下，增加1
                                int value_ = value + 1;

                                textField.setText(Integer.toString(value_));

                                taskTime.setTimes(value_);
                                // jsonToFile.toJsonFile(appTask2, filePath);
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
                                    // jsonToFile.toJsonFile(appTask2, filePath);
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
        return textField;
    }

    private JCheckBox getjCheckBox(TaskTime taskTime, String filePath, JsonToFile<AppTask3> jsonToFile, AppTask3 appTask2) {
        JCheckBox jCheckBox;
        jCheckBox = new JCheckBox(taskTime.getTaskName());
        boolean selected = taskTime.isSelected();
        // System.out.println("selected = " + selected);
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
                                // e.consume();
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


                // jPanel.add(jCheckBox, BorderLayout.WEST);
                jPanel.add(textField, BorderLayout.CENTER);
                // jPanel.add(textField, BorderLayout.EAST);
            } else {
                // 添加任务到列表中
                jCheckBox = new JCheckBox(s);
            }
            // jCheckBoxes.add(jCheckBox);
            jPanel.add(jCheckBox, BorderLayout.WEST);
            taskPanel.add(jPanel);
        }

        JFramePack.pack();
    }

    public JPanel getTopJPanel() {
        return topJPanel;
    }
}
