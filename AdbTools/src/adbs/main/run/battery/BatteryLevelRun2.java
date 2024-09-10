package adbs.main.run.battery;

import adbs.cmd.AdbCommands;
import adbs.main.AdbTools;
import adbs.main.run.IsTest;
import adbs.main.run.act.model.FrameTitle;
import adbs.tools.thread.ThreadSleep;
import tools.swing.button.AbstractButtons;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class BatteryLevelRun2 implements Runnable {
    private static boolean stop = false;
    private static boolean displayJOptionPane = true;
    private JFrame frame;
    private AdbTools adbTools;
    private String serial;
    private String name;
    private BatteryModel batteryModel;
    private JDialog dialogFullyCharged;
    private int dialogFullyChargedReturn;

    private final JDialog dialogAC;
    private JLabel dialogACMessage;
    private int dialogACReturn;

    public static final int SELECT_YES = 0;
    public static final int SELECT_NO = 1;
    public static final int SELECT_CANCEL = 2;
    public static final int SELECT_CLOSE = 3;

    public BatteryLevelRun2() {
        dialogFullyCharged = initDialogFullyCharged();
        dialogAC = initDialogAC();
    }

    @Override
    public void run() {
        before();
        body();
    }

    private void body() {
        System.out.println("电池检测线程开始");
        while (!stop) {
            //获取设备的序列号
            this.serial = adbTools.getDevice().getSerial();
            if (batteryModel == null) {
                batteryModel = new BatteryModel(serial);
            }
            //更新设备序列号
            batteryModel.setSerial(serial);
            // 更新电池信息
            batteryModel.update();
            // 获取电池电量百分比
            int level = batteryModel.getLevel();
            System.out.println("电池电量:" + level + "%");
            // 更新窗体标题中的电池电量值
            updateJFrameTitle(level);
            // 并且判断是否需要使用充电头充电
            if (batteryModel.needAcPower()) {
                // 弹窗提醒用户充电
                showRemindACDialog(level);
            } else if (batteryModel.isBatteryFullyCharged()) {
                // 弹窗提醒用户禁止充电
                showFullyChargedDialog();
            }
            //等待一定的时间
            wait_();
        }
    }

    private void before() {
        // 等待2秒
        ThreadSleep.seconds(2);
        adbTools = AdbTools.getInstance();
        // // 获取设备的序列号
        // serial = adbTools.getDevice().getSerial();
        // 获取设备名
        name = adbTools.getDevice().getName();
        // System.out.println("name = " + name);
        // 根据序列号 创建电池模型对象
        // 更新电池信息
        // batteryModel.update();
        frame = adbTools.getFrame();
        stop = false;
    }

    /**
     * 当电量充满时
     */
    private void showFullyChargedDialog() {

        // showJOptionPane("电量充足,换数据线?");
        if (displayJOptionPane) {
            // 调整对话框到合适的大小
            dialogFullyCharged.pack();
            // 居中显示
            dialogFullyCharged.setLocationRelativeTo(frame);
            // 显示对话框
            dialogFullyCharged.setVisible(true);

            switch (dialogFullyChargedReturn) {
                case SELECT_YES:
                    System.out.println("满电弹窗 选择 是 按钮");
                    selectOk();
                    break;
                case SELECT_NO:
                    System.out.println("满电弹窗 选择 否 按钮");
                    selectNo();
                    break;
                case SELECT_CANCEL:
                    System.out.println("满电弹窗 选择 取消 按钮");
                    selectCancel();
                    break;
                case SELECT_CLOSE:
                    System.out.println("满电弹窗 选择 关闭 按钮");
                    break;
                default:
                    break;
            }
        }
    }

    private JDialog initDialogFullyCharged() {
        System.out.println("电池检测线程，创建对话框");
        dialogFullyCharged = new JDialog(frame, "电量检测线程", true);
        //        // 设置为非模态
        //        dialogFullyCharged.setModalityType(Dialog.ModalityType.DOCUMENT_MODAL);

        dialogFullyCharged.setLayout(new BoxLayout(dialogFullyCharged.getContentPane(), BoxLayout.Y_AXIS));

        // 对话框的内容面板
        JPanel contentPanel = new JPanel();
        contentPanel.add(new JLabel("电量充足，禁止USB充电?"));
        dialogFullyCharged.add(contentPanel);

        // 按钮面板
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        JButton yesButton = new JButton("确定");
        JButton noButton = new JButton("否");
        JButton cancelButton = new JButton("取消");


        buttonPanel.add(yesButton);
        buttonPanel.add(Box.createHorizontalStrut(10));
        //                buttonPanel.add(horizontalStrut1);
        buttonPanel.add(noButton);
        //                buttonPanel.add(horizontalStrut1);
        buttonPanel.add(Box.createHorizontalStrut(10));
        buttonPanel.add(cancelButton);

        //设置每个按钮的内切
        AbstractButtons.setMarginInButtonJPanel(buttonPanel, 2);

        dialogFullyCharged.add(buttonPanel);

        // 按钮的ActionListener
        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //                dialogFullyCharged.dispose();

                //                selectOk();

                // 隐藏对话框
                dialogFullyCharged.setVisible(false);
                dialogFullyChargedReturn = SELECT_YES;
            }
        });

        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 隐藏对话框
                dialogFullyCharged.setVisible(false);
                //                        dialog.dispose();
                //                selectNo();
                dialogFullyChargedReturn = SELECT_NO;
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 隐藏对话框
                dialogFullyCharged.setVisible(false);
                //                        dialog.dispose();
                //                selectCancel();

                dialogFullyChargedReturn = SELECT_CANCEL;
            }
        });

        // 添加窗口关闭监听器
        dialogFullyCharged.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                // 关闭对话框
                //                        dialog.dispose();
                // 隐藏对话框
                dialogFullyCharged.setVisible(false);
                dialogFullyChargedReturn = SELECT_CLOSE;
            }
        });
        return dialogFullyCharged;
    }

    private void selectCancel() {
        //        System.out.println("等待 30 分钟");
        //        ThreadSleep.minutes(30);
        // 停止电池检测线程
        // stop = true;
        logSleep("电池检测线程 满电 取消 按钮", 10);
    }

    private void selectNo() {
        //        System.out.println("等待 10 分钟");
        //        // ThreadSleep.minutes(10);
        //        ThreadSleep.minutes(10);
        logSleep("电池检测线程 满电 否 按钮", 30);
    }

    private void selectOk() {
        displayJOptionPane = false;
        //禁止USB充电
        System.out.println("禁止USB充电");
        AdbCommands.batterySetUsb_0(serial);

        String msg = "电池检测线程 满电 是 按钮";
        logSleep(msg, 60);
        //        System.out.println("等待60分钟");
        //        // 等待一段时间
        //        ThreadSleep.minutes(60);
        System.out.println("时间结束，恢复电池状态");
        //恢复电池状态
        AdbCommands.batteryReset(serial);
        //        System.out.println("充电，等待2分钟");
        logSleep(msg, 2);
        // 等待一段时间
        //        ThreadSleep.minutes(2);


        //        new Thread(new Runnable() {
        //            @Override
        //            public void run() {
        //                // 等待50
        //                // ThreadSleep.minutes(50);
        //                ThreadSleep.minutes(60);
        //                // // 设置电池为充电状态
        //                // String usbChargingAllowed = "adb -s " + serial + " shell dumpsys battery set usb 1";
        //                // // String usbChargingAllowed = "adb -s " + serial + " shell dumpsys battery set usb 1";
        //                //
        //                //                            AdbCommands.runAbdCmd(usbChargingAllowed);
        //                Device device = AdbTools.getInstance().getDevice();
        //                String name = device.getName();
        //                //充电
        //                AdbCommands.batterySetUsb_1(serial);
        //                switch (name) {
        //                    case "vHei":
        //                        ThreadSleep.minutes(3);
        //                        break;
        //                    case "v2Lan":
        //                        ThreadSleep.minutes(5);
        //                        break;
        //                    default:
        //                        ThreadSleep.minutes(1);
        //                }
        //                //恢复电池状态
        //                AdbCommands.batteryReset(serial);
        //
        //                // 允许弹窗
        //                displayJOptionPane = true;
        //            }
        //        }).start();
        // 禁止后续的弹窗
    }


    private void wait_() {
        if (IsTest.isTest()) {
            // 20秒检测一次
            ThreadSleep.seconds(20);
        } else {
            //            ThreadSleep.seconds(10);
            // 等待一段时间，再进行更新电池信息
            // ThreadSleep.minutes(2);
            // 10检测一次电池
            int minute = 10;
            String msg = "电池检测线程";
            logSleep(msg, minute);
            // ThreadSleep.seconds(10);
        }
    }

    /**
     * 输出日志，并等待一段时间
     *
     * @param msg    日志开头
     * @param minute 分钟数
     */
    private void logSleep(String msg, int minute) {
        System.out.println(msg + " 等待:" + minute + "分钟");
        ThreadSleep.minutes(minute);
    }

    /**
     * 提醒用户使用充电头充电
     *
     * @param level
     */
    private void showRemindACDialog(int level) {
        // 获取电池电量
        String message = "电量:" + level + "% 充电?";
        System.out.println(message);
        remindeAcDialog(message);
    }

    /**
     * 弹窗提醒用户充电
     *
     * @param message 包含当前电量的消息
     */
    private void remindeAcDialog(String message) {
        // 弹出确认对话框
        // int confirmDialog = JOptionPane.showConfirmDialog(null, message);
        // 弹出确认对话框，显示标题，显示“是，否，取消”三个按钮

        // int confirmDialog = JOptionPane.showConfirmDialog(null, message,name,JOptionPane.YES_NO_CANCEL_OPTION);
        // int confirmDialog = JOptionPane.showConfirmDialog(adbTools.getContentPane(), message, name, JOptionPane.YES_NO_OPTION);
        //        int confirmDialog = JOptionPane.showConfirmDialog(adbTools.getContentPane(), message, name, JOptionPane.YES_NO_CANCEL_OPTION);
        //        switch (confirmDialog) {
        //            case JOptionPane.OK_OPTION:
        //                System.out.println("点击 是 按钮");
        //                break;
        //            case JOptionPane.NO_OPTION:
        //                System.out.println("点击 否 按钮");
        //                // ThreadSleep.minutes(10);
        //                ThreadSleep.minutes(20);
        //                break;
        //            case JOptionPane.CANCEL_OPTION:
        //                System.out.println("点击 取消 按钮");
        //                // ThreadSleep.minutes(30);
        //                ThreadSleep.minutes(40);
        //                // 停止电池检测线程
        //                // stop = true;
        //                break;
        //        }

        //        //        System.out.println("电池检测线程，创建对话框");
        //        if (dialogAC == null) {
        //            JDialog dialogAC = getDialogAC(message);
        //            this.dialogAC = dialogAC;
        //        }
        // 更新消息
        dialogACMessage.setText(message);
        // 自动调整对话框的大小
        dialogAC.pack();
        // 设置对话框的位置
        dialogAC.setLocationRelativeTo(frame);
        // 显示对话框
        dialogAC.setVisible(true);

        switch (dialogACReturn) {
            case SELECT_YES:
                System.out.println("缺电弹窗 点击 是 按钮");
                break;
            case SELECT_NO:
                //                System.out.println("缺电弹窗 点击 否 按钮");
                //                ThreadSleep.minutes(10);
                logSleep("缺电弹窗 点击 否 按钮", 30);
                break;
            case SELECT_CANCEL:
                //                System.out.println("缺电弹窗 点击 取消 按钮");
                //                ThreadSleep.minutes(30);
                logSleep("缺电弹窗 点击 取消 按钮", 10);
                break;
            case SELECT_CLOSE:
                System.out.println("缺电弹窗 点击 关闭 按钮");
                break;
            default:
                break;
        }

    }

    private JDialog initDialogAC() {
        // 创建一个模式对话框
        JDialog dialogAC = new JDialog(frame, "电量检测线程", true);

        //        // 设置为非模态
        //        dialogAC.setModalityType(Dialog.ModalityType.DOCUMENT_MODAL);

        dialogAC.setLayout(new BoxLayout(dialogAC.getContentPane(), BoxLayout.Y_AXIS));
        // 置顶
        dialogAC.setAlwaysOnTop(true);

        // 对话框的内容面板
        JPanel contentPanel = new JPanel();
        dialogACMessage = new JLabel("");
        contentPanel.add(dialogACMessage);
        dialogAC.add(contentPanel);

        // 按钮面板
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        JButton yesButton = new JButton("确定");
        JButton noButton = new JButton("否");
        JButton cancelButton = new JButton("取消");

        // 创建一个带有水平空白的Box，例如5个单位的间距
        //                Component horizontalStrut1 = Box.createHorizontalStrut(5);
        //                Box horizontalStrut = (Box) horizontalStrut1;
        buttonPanel.add(yesButton);
        buttonPanel.add(Box.createHorizontalStrut(10));
        //                buttonPanel.add(horizontalStrut1);
        buttonPanel.add(noButton);
        //                buttonPanel.add(horizontalStrut1);
        buttonPanel.add(Box.createHorizontalStrut(10));
        buttonPanel.add(cancelButton);

        //设置每个按钮的内切
        AbstractButtons.setMarginInButtonJPanel(buttonPanel, 2);

        dialogAC.add(buttonPanel);

        dialogACReturn = -10;

        // 按钮的ActionListener
        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //                dialogAC.dispose();
                //                ();
                dialogAC.setVisible(false);
                System.out.println("点击 是");
                BatteryLevelRun2.this.dialogACReturn = SELECT_YES;
            }
        });

        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 隐藏对话框
                dialogAC.setVisible(false);
                System.out.println("点击 否");
                //                        dialogAC.dispose();
                //                selectNo();
                //                ThreadSleep.minutes(10);
                BatteryLevelRun2.this.dialogACReturn = SELECT_NO;
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 隐藏对话框
                dialogAC.setVisible(false);
                //                        dialogAC.dispose();
                //                selectCancel();
                System.out.println("点击 取消");
                //                ThreadSleep.minutes(20);
                BatteryLevelRun2.this.dialogACReturn = SELECT_CANCEL;
            }
        });

        // 添加窗口关闭监听器
        dialogAC.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                // 关闭对话框
                //                        dialogAC.dispose();
                // 隐藏对话框
                dialogAC.setVisible(false);
                BatteryLevelRun2.this.dialogACReturn = SELECT_CLOSE;
            }
        });
        return dialogAC;
    }


    /**
     * 更新JFrame标题中的电池电量信息。
     *
     * @param level 当前的电池电量
     */
    private void updateJFrameTitle(int level) {
        FrameTitle frameTitle = FrameTitle.getFrameTitle();
        int level1 = frameTitle.getBatteryLevel();
        if (level1 != level) {
            frameTitle.setBatteryLevel(level);
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    frame.setTitle(frameTitle.toString());
                }
            });
        }

    }

    public static void stop() {
        BatteryLevelRun2.stop = true;
    }

    public static void main(String[] args) {
        new Thread(new BatteryLevelRun2()).start();
    }
}
