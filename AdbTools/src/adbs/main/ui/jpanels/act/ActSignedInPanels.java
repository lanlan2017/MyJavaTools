package adbs.main.ui.jpanels.act;

import adbs.main.AdbTools;
import adbs.main.run.AdbGetPackage;
import adbs.main.run.signinlog.FileUtil;
import adbs.main.ui.jframe.JFramePack;
import adbs.main.ui.jpanels.act.jaskson.file.JsonToFile;
import adbs.main.ui.jpanels.act.model.AppTask3;
import adbs.main.ui.jpanels.act.model.AppTaskTimeSet;
import adbs.main.ui.jpanels.act.model.TaskTime;
import adbs.main.ui.jpanels.act.reminder.after.TimerUtils;
import tools.swing.button.AbstractButtons;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Consumer;

/**
 * Activity打卡面板
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
     * 任务面板，根据不同的App生成不同的任务多选框
     */
    private final JPanel taskPanel;

    /**
     * 小时
     */
    private final JTextField jtfHour;
    /**
     * 分钟
     */
    private final JTextField jtfMinute;
    /**
     * 定时按钮最开始的背景色
     */
    private Color btnDingShiOkBackground;
    /**
     * 定时按钮
     */
    private final JButton btnDingShiOk;
    private JsonToFile<AppTask3> jsonToFile;
    private TitledBorder titledBorder;
    private static String appName = "";
    // private final JButton btnLieBiao;

    public ActSignedInPanels() {
        this.topJPanel = new JPanel();
        this.topJPanel.setLayout(new BorderLayout());

        this.taskPanel = new JPanel();
        if (titledBorder == null) {
            titledBorder = new TitledBorder(new LineBorder(Color.blue), "");
            //标题右对齐，默认是左对齐
            // titledBorder.setTitleJustification(TitledBorder.TRAILING);
            //标题居中对齐，默认是左对齐
            titledBorder.setTitleJustification(TitledBorder.CENTER);
            taskPanel.setBorder(titledBorder);
        }
        // 垂直排列
        taskPanel.setLayout(new BoxLayout(taskPanel, BoxLayout.Y_AXIS));

        this.toolPanel = new JPanel();
        this.toolPanel.setLayout(new BoxLayout(toolPanel, BoxLayout.X_AXIS));

        // this.btnUpdate = new JButton("更新任务");
        this.btnUpdate = new JButton("任务");
        // todo: 添加一个下拉框，用来查看其的任务列表
        btnUpdate.addActionListener(e -> {
            String title = titledBorder.getTitle();
            System.out.println("title = " + title);
            //            appName = AdbGetPackage.getAppName();
            // 如果应用名称为空，则说明是第一次展开任务面包，此时应该更新一次任务，
            if ("".equals(appName)) {
                System.out.println("应用名还没被Act线程更新，执行adb获取命令名");
                appName = AdbGetPackage.getAppName();
            }
            System.out.println("appName = " + appName);

            if (appName.contains(".")) {
                //应用名是包名，说明不是赚钱应用，不需要更新任务列表
                System.out.println("非赚钱应用，不需要更新任务列表");
            } else if (!title.equals(appName)) {
                // 应用的名称不是包名，并且应用名称改变了
                System.out.println("赚钱应用 已经 改变，询问是否更新任务");
                AdbTools.getInstance().showDialogOk("更新任务", e1 -> {
                    // 先显示任务面板
                    taskPanel.setVisible(true);
                    // 更新任务面板
                    updateAction(appName);
                });
                //                //默认的定时时间
                //                defaultTime(appName);
            }
            //            else {
            //                System.out.println("应用 没有 改变，无需更新任务列表");
            //                defaultTime(appName);
            //            }
        });


        JPanel timePanelTop = new JPanel();
        timePanelTop.setLayout(new BorderLayout());
        JPanel timePanel = new JPanel();
        timePanel.setLayout(new BoxLayout(timePanel, BoxLayout.X_AXIS));
        jtfHour = initJTextField();

        JLabel label = new JLabel(":");
        jtfMinute = initMinute();
        btnDingShiOk = getBtnDingShiOk();
        // 取消定时按钮
        JButton btnDingShiCancel = getBtnDingShiCancel();

        toolPanel.add(btnUpdate);

        JButton btnShouQi = new JButton("收起");
        btnShouQi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (taskPanel.isVisible()) {
                    taskPanel.setVisible(false);
                    btnShouQi.setText("展开");
                } else {
                    taskPanel.setVisible(true);
                    btnShouQi.setText("收起");
                }
                JFramePack.pack();
                //                taskPanel.setVisible(false);
            }
        });

        // 时间切换按钮
        JButton btnJH = new JButton("切换");

        btnJH.addActionListener(new ActionListener() {

            private String[][] dianTaoTimes;
            private int i;


            @Override
            public void actionPerformed(ActionEvent e) {
                if (dianTaoTimes == null) {

                    dianTaoTimes = new String[][]{
                            {
                                    "0",
                                    "10"
                            },
                            {
                                    "1",
                                    "0"
                            },
                            {
                                    "2",
                                    "0"
                            }
                    };
                }

                jtfHour.setText(dianTaoTimes[i][0]);
                jtfMinute.setText(dianTaoTimes[i][1]);
                // 循环数组的下标
                i = (i + 1) % dianTaoTimes.length;
            }
        });


        timePanel.add(jtfHour);
        timePanel.add(label);
        timePanel.add(jtfMinute);
        timePanel.add(btnJH);
        timePanel.add(btnDingShiOk);
        timePanel.add(btnDingShiCancel);
        timePanel.add(btnShouQi);


        timePanelTop.add(timePanel, BorderLayout.WEST);
        AbstractButtons.setMarginInButtonJPanel(timePanel, 1);
        toolPanel.add(timePanelTop);

        AbstractButtons.setMarginInButtonJPanel(toolPanel, 1);


        // topJPanel.add(btnUpdate, BorderLayout.NORTH);
        topJPanel.add(toolPanel, BorderLayout.NORTH);
        topJPanel.add(taskPanel, BorderLayout.CENTER);
        topJPanel.setVisible(false);
    }

    /**
     * 创建定时按钮
     *
     * @return
     */
    private JButton getBtnDingShiOk() {
        JButton btnDingShiOk = new JButton("定时");
        if (this.btnDingShiOkBackground == null) {
            this.btnDingShiOkBackground = btnDingShiOk.getBackground();
        }

        btnDingShiOk.setToolTipText("开启定时器");
        btnDingShiOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String jtfHourText = jtfHour.getText();
                String jtfMinuteText = jtfMinute.getText();
                if (jtfHourText.matches("\\d{1,2}") && jtfMinuteText.matches("\\d{1,2}")) {
                    int hour = Integer.parseInt(jtfHourText);
                    int minute = Integer.parseInt(jtfMinuteText);

                    AdbTools.getInstance().showDialogOk("定时:" + hour + ":" + minute + "?", new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                            // 等待两个小时,然后多等待2分钟作为误差。
                            // startReminder((2 * 60 + 2) * 60 * 1000, "点淘打工结束");
                            // startReminder(minute * 60 * 1000, "点淘打工结束");
                            // startReminder(5000, "点淘打工结束");
                            // 计算秒
                            int seconds = (hour * 60 + minute) * 60;

                            String message = "点淘打工结束";

                            //启动定时任务，等待指定的秒之后，执行任务
                            TimerUtils.afterSeconds(seconds, () -> {
                                AdbTools.getInstance().beepDialog(message);
                                SwingUtilities.invokeLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        // 恢复原来按钮的颜色，表示定时任务已经完成或者取消
                                        btnDingShiOk.setBackground(btnDingShiOkBackground);
                                    }
                                });
                            });
                            //设置确定按钮的背景色，表示有定时任务在运行
                            SwingUtilities.invokeLater(() -> {
                                btnDingShiOk.setBackground(Color.pink);
                            });
                        }
                    });
                }
            }
        });
        return btnDingShiOk;
    }

    private JButton getBtnDingShiCancel() {
        JButton btnDingShiCancel = new JButton("取消");
        btnDingShiOk.setToolTipText("取消定时器");
        btnDingShiCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdbTools.getInstance().showDialogOk("取消定时", new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //取消定时任务
                        TimerUtils.shutdown();
                        //恢复默认颜色
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                btnDingShiOk.setBackground(btnDingShiOkBackground);
                            }
                        });
                    }
                });
            }
        });
        return btnDingShiCancel;
    }

    private JTextField initMinute() {
        final JTextField jtfMinute;
        // Minute
        jtfMinute = new JTextField();
        jtfMinute.setColumns(2);
        jtfMinute.setText("0");
        jtfMinute.setToolTipText("分钟，按上下箭头键或小键盘的加减键改变(±1)，Page Up,Page Down改变(±10)");
        jtfMinute.setEditable(false);
        jtfMinute.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                JTextField textField = (JTextField) e.getSource();
                try {
                    int value = Integer.parseInt(textField.getText());
                    if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyChar() == '+') {
                        if (value < 59) {
                            value++;
                        }

                    } else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyChar() == '-') {
                        if (value > 0) {
                            value--;
                        }
                    } else if (e.getKeyCode() == KeyEvent.VK_PAGE_UP) {
                        if (value < 49) {
                            value += 10;
                        } else if (value < 59) {
                            value += 1;
                        }
                    } else if (e.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
                        if (value > 10) {
                            value -= 10;
                        } else if (value > 0) {
                            value -= 1;
                        }
                    }

                    textField.setText(String.valueOf(value));


                } catch (NumberFormatException ex) {
                    // 如果不是整数，则不执行任何操作
                    ex.printStackTrace();
                }
            }
        });
        return jtfMinute;
    }

    private JTextField initJTextField() {
        final JTextField jtfHour;
        // hour
        jtfHour = new JTextField();
        jtfHour.setText("0");
        jtfHour.setToolTipText("小时,按上下箭头，或者数字键盘的加号减号键改变");
        jtfHour.setColumns(2);
        jtfHour.setEditable(false);
        jtfHour.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                JTextField source = (JTextField) e.getSource();
                try {
                    int value = Integer.parseInt(source.getText());
                    //当按下上箭头或者加号的时候
                    if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyChar() == '+') {
                        if (value < 23) {
                            // 上箭头被按下，增加1
                            source.setText(String.valueOf(value + 1));
                        }
                    } else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyChar() == '-') {
                        // 下箭头被按下，减少1，但不能小于0
                        if (value > 0) {
                            source.setText(String.valueOf(value - 1));
                        }
                    }
                } catch (NumberFormatException ex) {
                    // 如果不是整数，则不执行任何操作
                    ex.printStackTrace();
                }
            }
        });
        return jtfHour;
    }


    private void updateAction(String appName) {
        // JsonToFile<AppTask3> jsonToFile = new JsonToFile<>();
        // String filePath = AdbTools.getInstance().getDevice().getActTaskJSON();
        if (jsonToFile == null) {
            jsonToFile = new JsonToFile<>(AdbTools.getInstance().getDevice().getActTaskJSON());
        }

        // AppTask3 appTask3 = getAppTask3(jsonToFile, filePath);
        AppTask3 appTask3 = getAppTask3(jsonToFile);

        // 获取应用名
        System.out.println("appName = " + appName);

        // updateTaskPanel(filePath, jsonToFile, appName, appTask3);
        updateTaskPanel(jsonToFile, appName, appTask3);

        //        // 设置默认的定时时间
        //        defaultTime(appName);

        //调整窗体到合适的大小
        JFramePack.pack();
    }


    //    /**
    //     * 默认的定时时间
    //     *
    //     * @param appName
    //     */
    //    private void defaultTime(String appName) {
    //        switch (appName) {
    //            case "点淘":
    //            case "番茄畅听音乐版":
    //                jtfHour.setText(String.valueOf(2));
    //                jtfMinute.setText(String.valueOf(0));
    //                break;
    //        }
    //    }


    private AppTask3 getAppTask3(JsonToFile<AppTask3> jsonToFile) {
        AppTask3 appTask3;
        String filePath = jsonToFile.getFilePath();
        //是否是直接new的AppTask3
        boolean isNew = false;
        // 获取当前这个设备对应的JSON文件路径
        System.out.println("filePath = " + filePath);
        //如果文件不存在这创建这个文件，如果创建了文件，则返回true，否则返回false
        boolean needToCreateFile = FileUtil.isNeedToCreateFile(filePath);
        //如果返回的是false，也就是上面的方法没有创建文件，也就是有旧文件
        if (!needToCreateFile) {
            //从旧文件中反序列化对象
            // AppTask2 appTask_old = jsonToFile.fromJsonFile(filePath, AppTask2.class);
            // AppTask3 appTask_old = jsonToFile.fromJsonFile(filePath, AppTask3.class);
            AppTask3 appTask_old = jsonToFile.fromJsonFile(AppTask3.class);

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
                        isNew = true;
                    }

                } else {
                    System.out.println("有文件，日期格式不对");
                    // appTask2 = new AppTask2();
                    appTask3 = new AppTask3();
                    isNew = true;
                }
            } else {
                System.out.println("遇到null");
                // appTask2 = new AppTask2();
                appTask3 = new AppTask3();
                isNew = true;
            }
        } else {
            System.out.println("新建JSON文件");
            // appTask2 = new AppTask2();
            appTask3 = new AppTask3();
            isNew = true;
        }
        if (isNew) {
            // 这里应该直接保存到json文件，免得错误
            jsonToFile.toJsonFile(appTask3);
        }
        return appTask3;
    }


    private void updateTaskPanel(JsonToFile<AppTask3> jsonToFile, String appName, AppTask3 appTask3) {
        // String filePath = jsonToFile.getFilePath();
        //获取应用
        ArrayList<AppTaskTimeSet> tasks = appTask3.getTasks();
        //获取任务的迭代器
        Iterator<AppTaskTimeSet> iterator = tasks.iterator();
        while (iterator.hasNext()) {
            AppTaskTimeSet next = iterator.next();
            // System.out.println("next = " + next);
            // System.out.println("appName = " + appName);
            //如果查找这个应用的任务
            if (next.getAppName().equals(appName)) {
                System.out.println("找到应用" + appName + "的任务列表");
                //移除所有面板
                taskPanel.removeAll();
                titledBorder.setTitle(appName);
                addTaskPanel(jsonToFile, appTask3, next);

            }
        }
    }


    private void addTaskPanel(JsonToFile<AppTask3> jsonToFile, AppTask3 appTask3, AppTaskTimeSet next) {
        // String filePath = jsonToFile.getFilePath();
        //取出这个应用的任务集合
        ArrayList<TaskTime> taskTimeSet = next.getTaskTimeList();
        //遍历任务集合
        taskTimeSet.forEach(new Consumer<TaskTime>() {
            @Override
            public void accept(TaskTime taskTime) {
                // JPanel checkTextPanel = getCheckTextPanel(taskTime, filePath, jsonToFile, appTask3);
                JPanel checkTextPanel = initCheckTextPanel(taskTime, jsonToFile, appTask3);
                taskPanel.add(checkTextPanel);

            }
        });
    }

    private JPanel initCheckTextPanel(TaskTime taskTime, JsonToFile<AppTask3> jsonToFile, AppTask3 appTask3) {
        // String filePath = jsonToFile.getFilePath();
        //存放多选框和文本框的面板
        JPanel checkTextPanel = new JPanel();
        checkTextPanel.setLayout(new BorderLayout());

        int times = taskTime.getTimes();
        String taskName = taskTime.getTaskName();
        JCheckBox jCheckBox;
        jCheckBox = initJCheckBox(taskTime, jsonToFile, appTask3);
        checkTextPanel.add(jCheckBox, BorderLayout.WEST);

        if (times >= 0) {
            JTextField textField = initJTextField(taskTime, taskName, jsonToFile, appTask3);
            checkTextPanel.add(textField, BorderLayout.CENTER);
        }
        return checkTextPanel;
    }

    private JTextField initJTextField(TaskTime taskTime, String taskName, JsonToFile<AppTask3> jsonToFile, AppTask3 appTask3) {

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
                                // jsonToFile.toJsonFile(appTask3, filePath);
                                jsonToFile.toJsonFile(appTask3);
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
                                    // jsonToFile.toJsonFile(appTask3, filePath);
                                    jsonToFile.toJsonFile(appTask3);
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


    private JCheckBox initJCheckBox(TaskTime taskTime, JsonToFile<AppTask3> jsonToFile, AppTask3 appTask2) {
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
                    // jsonToFile.toJsonFile(appTask2, filePath);
                    jsonToFile.toJsonFile(appTask2);
                } else {
                    taskTime.setSelected(false);
                    // jsonToFile.toJsonFile(appTask2, filePath);
                    jsonToFile.toJsonFile(appTask2);
                }
            }
        });
        return jCheckBox;
    }

    public JPanel getTopJPanel() {
        return topJPanel;
    }

    public JButton getBtnUpdate() {
        return btnUpdate;
    }

    public TitledBorder getTitledBorder() {
        return titledBorder;
    }

    public JPanel getTaskPanel() {
        return taskPanel;
    }

    public static void setAppName(String appName) {
        ActSignedInPanels.appName = appName;
    }
}
