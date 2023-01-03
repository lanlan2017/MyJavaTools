package adbs.main.ui.jpanels.control;

import adbs.cmd.CmdRun;
import adbs.main.ui.config.FlowLayouts;
import tools.swing.button.AbstractButtons;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 控制面板
 */
public class ControlJPanels {
    private final JPanel controlJPanel;

    private final JLabel label;
    private final JTextField hourTextField;
    private final JLabel hourLabel;
    private final JTextField minuteTextField;
    private final JLabel minuteLabel;
    private final JTextField secondTextField;
    private final JButton dormantOKButton;
    private final JButton cancelBtn;


    public ControlJPanels() {
        controlJPanel = new JPanel(FlowLayouts.flowLayoutLeft);
        // controlJPanel.setBorder(new TitledBorder("控制面板"));
        Font font1 = new Font(Font.SANS_SERIF, Font.BOLD, 16);
        // Font font2 = new Font(Font.SANS_SERIF, Font.BOLD, 12);
        // Font font2 = new Font("Consolas", Font.BOLD, 12);
        // 使用微软的等宽字体
        Font font2 = new Font("Consolas", Font.PLAIN, 14);

        label = new JLabel("时间:");

        hourLabel = new JLabel(":");
        minuteLabel = new JLabel(":");

        hourLabel.setFont(font1);
        minuteLabel.setFont(font1);

        hourTextField = new JTextField(2);
        minuteTextField = new JTextField(2);
        secondTextField = new JTextField(2);

        hourTextField.setFont(font2);
        minuteTextField.setFont(font2);
        secondTextField.setFont(font2);


        dormantOKButton = new JButton("等待");
        dormantOKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 注意，javax.swing包中也有一个Timer类，如果import中用到swing包,要注意名字的冲突。

                String secondStr = secondTextField.getText();
                String minuteStr = minuteTextField.getText();
                String hourStr = hourTextField.getText();

                System.out.println("hourStr = " + hourStr);
                System.out.println("minuteStr = " + minuteStr);
                System.out.println("secondStr = " + secondStr);

                double ms = 0.0;
                // 如果输入的小时 是1个活着两个数字的话
                if (hourStr.matches("[0-9]{1,2}")) {
                    int hour = Integer.parseInt(hourStr);
                    ms += hour * 60 * 60 * 1000;
                }
                // 如果输入的分钟 是1个活着两个数字的话
                if (minuteStr.matches("[0-9]{1,2}")) {
                    int minute = Integer.parseInt(minuteStr);
                    ms += minute * 60 * 1000;
                }
                // 如果输入的分钟 是1个活着两个数字的话
                if (secondStr.matches("[0-9]{1,2}")) {
                    int second = Integer.parseInt(secondStr);
                    ms += second * 1000;
                }

                // 定时器
                java.util.Timer timer = new Timer(true);
                // 定时器的任务
                final double finalMs = ms;
                System.out.print("等待:" + (finalMs) + "(ms)=");
                System.out.print((finalMs / 1000) + "(s)=");
                System.out.print((finalMs / (60 * 1000)) + "(m)=");
                System.out.print((finalMs / (60 * 60 * 1000)) + "(h)");
                System.out.println();

                TimerTask task = new TimerTask() {
                    public void run() {
                        // System.out.print("等待:" + (finalMs) + "(ms)=");
                        // System.out.print((finalMs / 1000) + "(s)");
                        // System.out.println("结束");
                        // 杀死悟空浏览器
                        // CmdRun.run("adb shell am force-stop com.cat.readall");
                        // 杀死快手极速版
                        // CmdRun.run("adb shell am force-stop com.kuaishou.nebula");
                        // // 杀死抖音极速版
                        // CmdRun.run("adb shell am force-stop com.ss.android.ugc.aweme.lite");

                        // 关闭adb.exe,关闭scrcpy.exe
                        // CmdRun.run("taskkill /f /im adb.exe");

                        // oppo手机关机
                        CmdRun.run("adb -s 75aed56d shell reboot -p");
                        // honor手机关机
                        CmdRun.run("adb -s U8ENW18117021408 shell reboot -p");
                        // 3秒后关机
                        CmdRun.run("timeout 10 && shutdown /h");
                        // 杀死自己
                        // Taskkill.killAdbToolsJarAll();
                        // 电脑休眠
                        //timeout 36000 && adb -s 75aed56d shell reboot -p && adb -s U8ENW18117021408 shell reboot -p && shutdown /h
                    }
                };
                // 定时器等待ms毫秒后执行任务task
                // timer.schedule(task, ms);
                timer.schedule(task, (long) ms);
            }
        });

        cancelBtn = new JButton("取消");
        cancelBtn.setToolTipText("取消shutdown命令");
        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CmdRun.run("shutdown -a");
            }
        });
        controlJPanel.add(label);
        controlJPanel.add(hourTextField);
        controlJPanel.add(hourLabel);
        // controlJPanel.add(hour);
        controlJPanel.add(minuteTextField);
        // controlJPanel.add(minute);
        controlJPanel.add(minuteLabel);
        controlJPanel.add(secondTextField);
        // controlJPanel.add(second);
        // controlJPanel.add(secondLabel);
        controlJPanel.add(dormantOKButton);
        controlJPanel.add(cancelBtn);

        // AbstractButtons.setMarginInButtonJPanel(controlJPanel);
        AbstractButtons.setMarginInButtonJPanel(controlJPanel, 1);
    }

    public JPanel getControlJPanel() {
        return controlJPanel;
    }

    public JLabel getLabel() {
        return label;
    }

    public JButton getDormantOKButton() {
        return dormantOKButton;
    }

    public JTextField getSecondTextField() {
        return secondTextField;
    }
}
