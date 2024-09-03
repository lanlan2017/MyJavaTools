package adbs.main.ui.jpanels.act.dingshiqi;

import adbs.main.AdbTools;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class ReminderButtonExample {

    private JButton startButton;
    private JButton cancelButton;
    private JFrame frame;
    private Timer timer;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ReminderButtonExample window = new ReminderButtonExample();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public ReminderButtonExample() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Reminder Button Example");
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new FlowLayout());

        startButton = new JButton("Start Reminder");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startReminder();
            }
        });

        cancelButton = new JButton("Cancel Reminder");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelReminder();
            }
        });

        frame.getContentPane().add(startButton);
        frame.getContentPane().add(cancelButton);
    }

    private void startReminder() {
        if (timer != null) {
            timer.cancel(); // 取消之前的定时器任务
        }

        timer = new Timer();

        TimerTask reminderTask = new TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
//                                                JOptionPane.showMessageDialog(frame, "提醒：该做的事情！", "提醒", JOptionPane.INFORMATION_MESSAGE);
//                        AdbTools.getInstance().getTimePanels().beepDialog("点淘打工结束");

                        AdbTools.getInstance().beepDialog("点淘打工结束");
                    }
                });
            }
        };

        // 设置延迟时间（例如5秒后触发）
        long delay = 5000; // 5 seconds
        timer.schedule(reminderTask, delay);
    }

    private void cancelReminder() {
        if (timer != null) {
            timer.cancel(); // 取消定时器
            timer = null;
        }
    }
}
