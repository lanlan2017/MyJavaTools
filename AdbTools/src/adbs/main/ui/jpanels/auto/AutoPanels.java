package adbs.main.ui.jpanels.auto;

import adbs.cmd.Robots;
import adbs.main.ui.config.FlowLayouts;
import adbs.main.ui.config.Fonts;
import adbs.main.ui.jpanels.tools.BtnActionListener;
import adbs.tools.thread.ThreadSleep;
import tools.swing.button.AbstractButtons;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 自动控制面板
 */
public class AutoPanels {
    /**
     * 主面板
     */
    private JPanel autoJPanel;
    /**
     *
     */
    private JTextField input;
    private JRadioButton jrbClick;
    private JRadioButton jrbClickTwoPlaces;
    /**
     *
     */
    private JButton button;


    public JPanel getAutoJPanel() {
        return autoJPanel;
    }

    public AutoPanels() {
        autoJPanel = new JPanel();
        autoJPanel.setLayout(FlowLayouts.flowLayoutLeft);

        input = new JTextField(2);
        input.setFont(Fonts.Consolas_PLAIN_12);

        jrbClick = new JRadioButton("1");
        final String textClickOnOnePlace = "点击一处";
        final String textClickOnTwoPlaces = "点击两处";
        jrbClick.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                button.setText(textClickOnOnePlace);
            }
        });

        jrbClickTwoPlaces = new JRadioButton("2");
        jrbClickTwoPlaces.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                button.setText(textClickOnTwoPlaces);
            }
        });
        ButtonGroup buttonGroup = new ButtonGroup();

        buttonGroup.add(jrbClick);
        buttonGroup.add(jrbClickTwoPlaces);

        button = new JButton("连续点击");
        button.setToolTipText("连续多次点击");
        button.addActionListener(new BtnActionListener() {
            /**
             * 第一次点击的位置
             */
            private Point point;

            @Override
            public void action(ActionEvent e) {
                String buttonText = button.getText();
                switch (buttonText) {
                    case textClickOnOnePlace:
                        clickOnePlace();
                        break;
                    case textClickOnTwoPlaces:
                        int times = getTimes();
                        if (times > 0) {

                        }
                        break;
                }


            }

            /**
             * 循环点击一个地方
             */
            private void clickOnePlace() {
                // String text = input.getText();
                // if (text.matches("\\d+")) {
                //     int times = Integer.parseInt(text);
                //     ThreadSleep.seconds(5);
                //     System.out.println("times = " + times);
                //     point = Robots.getMousePointerLocation();
                //     System.out.println("times = " + times);
                //
                // }
                int times = getTimes();
                if (times > 0) {
                    // Robot robot = Robots.getRobot();
                    // int times = 5;
                    for (int i = 0; i < times; i++) {
                        System.out.println("i = " + i);
                        // 把鼠标移动到指定的位置
                        Robots.mouseMove(point);
                        Robots.delay(20);
                        //点一下鼠标左键
                        Robots.mousePressLeftBtn();
                        Robots.delay(50);
                        Robots.mouseReleaseLeftBtn();
                        Robots.delaySecond(10);
                        Robots.delay(1);
                    }
                    System.out.println("连续点击结束");
                }
            }
        });


        autoJPanel.add(jrbClick);
        autoJPanel.add(jrbClickTwoPlaces);
        autoJPanel.add(input);
        autoJPanel.add(button);
        autoJPanel.setVisible(false);
        AbstractButtons.setMarginInButtonJPanel(autoJPanel, 0);
    }

    private int getTimes() {
        int times = 0;
        String text = input.getText();
        if (text.matches("\\d+")) {
            times = Integer.parseInt(text);
        }
        return times;
    }
}
