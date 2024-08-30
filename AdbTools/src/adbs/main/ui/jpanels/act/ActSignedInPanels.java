package adbs.main.ui.jpanels.act;

import adbs.main.AdbTools;
import adbs.main.run.AdbGetPackage;
import adbs.main.run.signinlog.FileUtil;
import adbs.main.ui.jframe.JFramePack;
import adbs.main.ui.jpanels.act.jaskson.file.JsonToFile;
import adbs.main.ui.jpanels.act.model.AppTask3;
import adbs.main.ui.jpanels.act.model.AppTaskTimeSet;
import adbs.main.ui.jpanels.act.model.TaskTime;
import tools.swing.button.AbstractButtons;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
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
     * 任务面板，根据不同的App生成不同的任务多选框
     */
    private final JPanel taskPanel;
    /**
     * 定时器
     */
    private Timer timer;
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

        JPanel timePanelTop = new JPanel();
        timePanelTop.setLayout(new BorderLayout());
        JPanel timePanel = new JPanel();
        timePanel.setLayout(new BoxLayout(timePanel, BoxLayout.X_AXIS));
        jtfHour = initHour();

        JLabel label = new JLabel(":");

        jtfMinute = initMinute();

        btnDingShiOk = getBtnDingShiOk();
        JButton btnDingShiCancel = getBtnDingShiCancel();

        toolPanel.add(btnUpdate);

        //        toolPanel.add(jtfMinute);
        //        toolPanel.add(label);
        //        toolPanel.add(btnDingShiOk);
        //        toolPanel.add(btnDingShiCancel);

        timePanel.add(jtfHour);
        timePanel.add(label);
        timePanel.add(jtfMinute);
        timePanel.add(btnDingShiOk);
        timePanel.add(btnDingShiCancel);
        timePanelTop.add(timePanel, BorderLayout.WEST);

        toolPanel.add(timePanelTop);
        AbstractButtons.setMarginInButtonJPanel(timePanel, 1);
        AbstractButtons.setMarginInButtonJPanel(toolPanel, 1);


        // topJPanel.add(btnUpdate, BorderLayout.NORTH);
        topJPanel.add(toolPanel, BorderLayout.NORTH);
        topJPanel.add(taskPanel, BorderLayout.CENTER);
        topJPanel.setVisible(false);
    }

    private JButton getBtnDingShiOk() {
        JButton btnDingShiOk = new JButton("定时");

        this.btnDingShiOkBackground = btnDingShiOk.getBackground();

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
                            //                            startReminder((2 * 60 + 2) * 60 * 1000, "点淘打工结束");
                            //                            startReminder(minute * 60 * 1000, "点淘打工结束");
                            //                        startReminder(5000, "点淘打工结束");
                            startReminder((hour * 60 + minute) * 60 * 1000, "点淘打工结束");
                            btnDingShiOk.setBackground(Color.pink);
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
                        cancelReminder();
                        btnDingShiOk.setBackground(btnDingShiOkBackground);
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

    private JTextField initHour() {
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

    /**
     * 定时器需要等待的毫秒数
     *
     * @param delay   毫秒
     * @param message
     */
    private void startReminder(long delay, final String message) {
        if (timer != null) {
            timer.cancel(); // 取消之前的定时器任务
        }

        timer = new Timer();

        TimerTask reminderTask = new TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        //定时结束时响铃提醒
                        AdbTools.getInstance().getTimePanels().beepDialog(message);
                        btnDingShiOk.setBackground(btnDingShiOkBackground);
                    }
                });
            }
        };

        // 设置延迟时间（例如5秒后触发）
        // 设置延迟时间（例如5秒后触发）
        //        long delay = 5000; // 5 seconds

        System.out.println("定时：" + delay + "毫秒");
        timer.schedule(reminderTask, delay);
    }

    public Timer getTimer() {
        return timer;
    }

    public void cancelReminder() {
        if (timer != null) {
            System.out.println("取消定时器");
            // 取消定时器
            timer.cancel();
            timer = null;
        }
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

        // 获取应用名
        String appName = AdbGetPackage.getAppName();
        System.out.println("appName = " + appName);
        updateTaskPanel(filePath, jsonToFile, appName, appTask3);
        //        if ("点淘".equals(appName)) {
        if ("番茄畅听音乐版".equals(appName)) {
            jtfHour.setText(String.valueOf(2));
            jtfMinute.setText(String.valueOf(0));
        }
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
            JTextField textField = initHour(taskTime, taskName, jsonToFile, appTask3, filePath);
            checkTextPanel.add(textField, BorderLayout.CENTER);
        }
        return checkTextPanel;
    }

    private JTextField initHour(TaskTime taskTime, String taskName, JsonToFile<AppTask3> jsonToFile, AppTask3 appTask3, String filePath) {
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
