package adbs.main.run.battery;

import adbs.cmd.AdbCommands;
import adbs.main.AdbTools;
import adbs.main.run.IsTest;
import adbs.main.run.act.model.FrameTitle;
import adbs.model.Device;
import adbs.tools.thread.ThreadSleep;
import tools.swing.button.AbstractButtons;

import javax.swing.*;
import java.awt.*;
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
    private JDialog dialog;


    @Override
    public void run() {
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

        System.out.println("电池检测线程开始");
        while (!stop) {
            //获取设备的序列号
            this.serial = adbTools.getDevice().getSerial();
            if (batteryModel == null) {
                batteryModel = new BatteryModel(serial);
                //                System.out.print("--------------------------");
                //                System.out.print(batteryModel);
                //                System.out.println("--------------------------");
            }
            //更新设备序列号
            batteryModel.setSerial(serial);
            // 更新电池信息
            batteryModel.update();
            // 获取电池电量百分比
            int level = batteryModel.getLevel();
            System.out.println("level = " + level);
            // 更新窗体标题中的电池电量值
            updateJFrameTitle(level);
            // 并且判断是否需要使用充电头充电
            if (batteryModel.needAcPower()) {
                // 弹窗提醒用户充电
                remindAC(level);
            } else if (batteryModel.isBatteryFullyCharged()) {
                whenFullyCharged();
            }
            //等待一定的时间
            wait_();

        }
    }

    /**
     * 当电量充满时
     */
    private void whenFullyCharged() {
        // showJOptionPane("电量充足,换数据线?");
        if (displayJOptionPane) {
            if (dialog == null) {
                System.out.println("电池检测线程，创建对话框");
                dialog = new JDialog(frame, "电量检测线程", true);
                // 设置为非模态
                dialog.setModalityType(Dialog.ModalityType.DOCUMENT_MODAL);

                dialog.setLayout(new BoxLayout(dialog.getContentPane(), BoxLayout.Y_AXIS));

                // 对话框的内容面板
                JPanel contentPanel = new JPanel();
                contentPanel.add(new JLabel("电量充足，禁止USB充电?"));
                dialog.add(contentPanel);

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

                dialog.add(buttonPanel);

                // 按钮的ActionListener
                yesButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dialog.dispose();
                        selectOk();
                    }
                });

                noButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dialog.dispose();
                        selectNo();
                    }
                });

                cancelButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dialog.dispose();
                        selectCancel();
                    }
                });

                // 添加窗口关闭监听器
                dialog.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent we) {
                        dialog.dispose(); // 关闭对话框
                    }
                });
            }
            //            JFrame mainFrame = AdbTools.getInstance().getFrame();


            // 显示对话框
            //            dialog.setLocationRelativeTo(mainFramemainFrame); // 居中显示
            dialog.pack();
            dialog.setLocationRelativeTo(frame); // 居中显示
            dialog.setVisible(true);
        }
    }

    private void selectCancel() {
        System.out.println("点击 取消 按钮");
        ThreadSleep.minutes(30);
        // 停止电池检测线程
        // stop = true;
    }

    private void selectNo() {
        System.out.println("点击 否 按钮");
        // ThreadSleep.minutes(10);
        ThreadSleep.minutes(10);
    }

    private void selectOk() {
        displayJOptionPane = false;
        System.out.println("点击 是 按钮，禁用USB充电");
        //禁止USB充电
        AdbCommands.batterySetUsb_0(serial);
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 等待50
                // ThreadSleep.minutes(50);
                ThreadSleep.minutes(60);
                // // 设置电池为充电状态
                // String usbChargingAllowed = "adb -s " + serial + " shell dumpsys battery set usb 1";
                // // String usbChargingAllowed = "adb -s " + serial + " shell dumpsys battery set usb 1";
                //
                //                            AdbCommands.runAbdCmd(usbChargingAllowed);
                Device device = AdbTools.getInstance().getDevice();
                String name = device.getName();
                //充电
                AdbCommands.batterySetUsb_1(serial);
                switch (name) {
                    case "vHei":
                        ThreadSleep.minutes(3);
                        break;
                    case "v2Lan":
                        ThreadSleep.minutes(5);
                        break;
                    default:
                        ThreadSleep.minutes(1);
                }
                //恢复电池状态
                AdbCommands.batteryReset(serial);

                // 允许弹窗
                displayJOptionPane = true;
            }
        }).start();
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
            ThreadSleep.minutes(10);
            // ThreadSleep.seconds(10);
        }
    }

    /**
     * 提醒用户使用充电头充电
     *
     * @param level
     */
    private void remindAC(int level) {
        // 获取电池电量
        String message = "电量:" + level + "% 充电?";
        System.out.println(message);
        showJOptionPane(message);
    }

    private void showJOptionPane(String message) {
        // 弹出确认对话框
        // int confirmDialog = JOptionPane.showConfirmDialog(null, message);
        // 弹出确认对话框，显示标题，显示“是，否，取消”三个按钮

        // int confirmDialog = JOptionPane.showConfirmDialog(null, message,name,JOptionPane.YES_NO_CANCEL_OPTION);
        // int confirmDialog = JOptionPane.showConfirmDialog(adbTools.getContentPane(), message, name, JOptionPane.YES_NO_OPTION);
        int confirmDialog = JOptionPane.showConfirmDialog(adbTools.getContentPane(), message, name, JOptionPane.YES_NO_CANCEL_OPTION);
        switch (confirmDialog) {
            case JOptionPane.OK_OPTION:
                System.out.println("点击 是 按钮");
                break;
            case JOptionPane.NO_OPTION:
                System.out.println("点击 否 按钮");
                // ThreadSleep.minutes(10);
                ThreadSleep.minutes(20);
                break;
            case JOptionPane.CANCEL_OPTION:
                System.out.println("点击 取消 按钮");
                // ThreadSleep.minutes(30);
                ThreadSleep.minutes(40);
                // 停止电池检测线程
                // stop = true;
                break;
        }

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
