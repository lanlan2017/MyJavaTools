/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blue.commands.demo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * @author seadee
 */
public class MaxMinCloseFrame extends JFrame {
    public final static String TAG = "ProgrameTest";

    JButton closeBtn = null;
    JButton maxBtn = null;
    JButton minBtn = null;


    public MaxMinCloseFrame(String host) {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);
        SwingUtilities.updateComponentTreeUI(this);
        setTitle("最大化最小化测试");
        this.setName("最大化最小化测试");

        setSize(700, 600);
        this.setLocationRelativeTo(null);//居中显示
        //this.setResizable(false);
        this.setUndecorated(true);
        this.setAlwaysOnTop(true);

        initComponent(host);

    }

    private void initComponent(String host) {
        closeBtn = new JButton("关闭");
        closeBtn.setBounds(300, 500, 130, 40);
        closeBtn.setHorizontalAlignment(JButton.CENTER);
        closeBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //dispose();
                System.exit(0);
                //使用dispose();也可以关闭只是不是真正的关闭
            }
        });
        this.add(closeBtn);

        maxBtn = new JButton("最大化");
        maxBtn.setBounds(300, 400, 130, 40);
        maxBtn.setHorizontalAlignment(JButton.CENTER);
        maxBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                setExtendedState(JFrame.MAXIMIZED_BOTH);//最大化窗体

            }
        });
        this.add(maxBtn);

        minBtn = new JButton("最小化");
        minBtn.setBounds(300, 300, 130, 40);
        minBtn.setHorizontalAlignment(JButton.CENTER);
        minBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                setExtendedState(JFrame.ICONIFIED);//最小化窗体

            }
        });
        this.add(minBtn);
    }

    public static void main(String[] args) {
        new MaxMinCloseFrame("192.168.0.1").setVisible(true);
    }

}