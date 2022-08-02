package adbs.test.auto;

import adbs.action.listener.OpenButtonListener;
import adbs.action.listener.abs.shell.RebootBtnAcListener;
import adbs.action.listener.abs.shellinput.HomeBtnAcListener;
import adbs.action.listener.abs.shellinput.ReturnBtnAcListener;
import adbs.action.listener.abs.shellinput.TaskManageBtnAcListener;
import adbs.action.runnable.open.Taskkill;
import adbs.test.DeviceListener;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class  AdbJPanels {

    JPanel adbJPanel;
    JButton openBtn;
    JButton killBtn;
    JButton returnBtn;
    JButton homeBtn;
    JButton taskBtn;
    JButton rebootBtn;
    JButton closeBtn;


    public AdbJPanels() {
        adbJPanel = new JPanel();
        adbJPanel.setBorder(new TitledBorder("adb"));
        openBtn = new JButton(new ImageIcon(Buttons.class.getClassLoader().getResource("open.png")));
        openBtn.setToolTipText("打开设备");
        openBtn.addActionListener(new OpenButtonListener());


        killBtn = new JButton("kill");
        killBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = DeviceListener.getPhoneId();
                Taskkill.killScrcpy(id);
            }
        });


        returnBtn = new JButton(new ImageIcon(Buttons.class.getClassLoader().getResource("向左三角形.png")));
        returnBtn.setToolTipText("返回键");
        // 返回键
        returnBtn.addActionListener(new ReturnBtnAcListener());


        // home键按钮
        homeBtn = new JButton(new ImageIcon(Buttons.class.getClassLoader().getResource("圆圈.png")));
        homeBtn.setToolTipText("home键");
        homeBtn.addActionListener(new HomeBtnAcListener());
        // 任务键按钮
        taskBtn = new JButton(new ImageIcon(Buttons.class.getClassLoader().getResource("空框.png")));
        taskBtn.setToolTipText("任务键");
        // 任务管理键
        taskBtn.addActionListener(new TaskManageBtnAcListener());


        // 重启键按钮
        rebootBtn = new JButton("重启");
        // rebootBtn.addActionListener(new RebootBtnAcListener(frame, "reboot"));
        rebootBtn.addActionListener(new RebootBtnAcListener(adbJPanel, "reboot"));

        // 关机键按钮
        closeBtn = new JButton("关机");
        // closeBtn.addActionListener(new RebootBtnAcListener(frame, "shell reboot -p"));
        closeBtn.addActionListener(new RebootBtnAcListener(adbJPanel, "shell reboot -p"));


        // adb面板添加按钮
        adbJPanel.add(openBtn);
        adbJPanel.add(killBtn);
        adbJPanel.add(returnBtn);
        adbJPanel.add(homeBtn);
        adbJPanel.add(taskBtn);
        adbJPanel.add(rebootBtn);
        adbJPanel.add(closeBtn);
    }

    public JPanel getAdbJPanel() {
        return adbJPanel;
    }
}