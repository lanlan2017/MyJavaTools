package adbs.main.ui.jpanels.timeauto2;

import adbs.main.AdbTools;
import adbs.main.ui.config.FlowLayouts;
import adbs.main.ui.config.Fonts;
import adbs.main.ui.jpanels.time.TimePanels;
import adbs.main.ui.jpanels.time.listener.WaitValues;
import adbs.main.ui.jpanels.universal.UniversalPanels;
import tools.swing.button.AbstractButtons;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimingPanels2 extends WaitValues {
    private final JPanel timingPanels2;

    // private final JRadioButton jrBtnVideo;
    // private final JRadioButton jrBtnShopping;
    // private final JRadioButton jrBtnWait;

    // private final String textJrBtnVideo = "v";
    private final String textJrBtnVideo = "vw";
    private final String textJrBtnShopping = "s";
    private final String textJrBtnWait = "w";
    private final String textJrBtnReadWait = "rw";
    // private final String textJrBtnReadWait = "r";


    private final JComboBox<String> jComboBox;
    private final JButton btn35s;
    private final JButton btn30s;
    private final JButton btn65;
    private final JButton btn95;
    private final JButton btn120s;
    private final JButton btn180s;
    private final JButton btn3HMore;
    private final JButton btn4H;
    private final JButton btn1H;
    private final JButton btn2H;
    private String selected;

    public TimingPanels2() {
        timingPanels2 = new JPanel();
        timingPanels2.setLayout(FlowLayouts.flowLayoutLeft);

        // jrBtnVideo = new JRadioButton(textJrBtnVideo);
        // jrBtnShopping = new JRadioButton(textJrBtnShopping);
        // jrBtnWait = new JRadioButton(textJrBtnWait);
        // ButtonGroup group = new ButtonGroup();
        // group.add(jrBtnVideo);
        // group.add(jrBtnWait);
        // group.add(jrBtnShopping);

        // ActionListener sliceActionListener = new ActionListener() {
        //     public void actionPerformed(ActionEvent actionEvent) {
        //         AbstractButton button = (AbstractButton) actionEvent.getSource();
        //         // System.out.println("Selected: " + button.getText());
        //         AdbTools adbTools = AdbTools.getInstance();
        //         // 获取通用面板对象
        //         // UniversalPanels universalPanels = adbTools.getUniversalPanels();
        //
        //         String text = button.getText();
        //         selected = text;
        //
        //
        //         switch (text) {
        //             case textJrBtnWait:
        //                 // universalPanels.getWaitButton().doClick();
        //                 showWaitButtons();
        //                 break;
        //             case textJrBtnShopping:
        //                 // universalPanels.getShoppingButton().doClick();
        //                 showShoppingButtons();
        //                 break;
        //             case textJrBtnVideo:
        //                 showVideoButtons();
        //                 // universalPanels.getVideoButton().doClick();
        //                 break;
        //         }
        //
        //     }
        // };

        // jrBtnWait.addActionListener(sliceActionListener);
        // jrBtnVideo.addActionListener(sliceActionListener);
        // jrBtnShopping.addActionListener(sliceActionListener);


        // int value = 30;
        btn30s = getButton(30);
        btn35s = getButton(35);
        btn65 = getButton(65);
        btn95 = getButton(95);
        btn120s = getButton(120);
        btn180s = getButton(180);
        btn1H = getButton(1 * 60 * 60);
        btn2H = getButton(2 * 60 * 60);
        // btn1H= btn1H;
        btn3HMore = getButton(values[values.length - 2]);
        btn4H = getButton(values[values.length - 1]);


        hideAllButtons();

        jComboBox = getComboBox();
        jComboBox.setSelectedIndex(0);

        // timingPanels.add(jrBtnVideo);
        // timingPanels.add(jrBtnWait);
        // timingPanels.add(jrBtnShopping);

        timingPanels2.add(jComboBox);

        timingPanels2.add(btn1H);
        timingPanels2.add(btn2H);
        timingPanels2.add(btn3HMore);
        timingPanels2.add(btn4H);
        timingPanels2.add(btn30s);
        timingPanels2.add(btn35s);
        timingPanels2.add(btn65);
        timingPanels2.add(btn95);
        timingPanels2.add(btn120s);
        timingPanels2.add(btn180s);


        AbstractButtons.setMarginInButtonJPanel(timingPanels2, 0);
    }

    private JComboBox<String> getComboBox() {
        final JComboBox<String> jComboBox;
        jComboBox = new JComboBox<>();
        // jComboBox.setFont(Fonts.Cascadia_PLAIN_12);
        // jComboBox.setFont(Fonts.Consolas_PLAIN_14);
        // jComboBox.setFont(Fonts.Consolas_PLAIN_12);
        // jComboBox.setFont(Fonts.Consolas_BOLD_12);
        jComboBox.setFont(Fonts.Consolas_BOLD_13);
        // new Font(Font.)

        // 获取其他按钮的首选大小
        Dimension preferredSize = btn65.getPreferredSize();
        System.out.println("preferredSize1111 = " + preferredSize);
        // 设置下拉框的首选大小
        // jComboBox.setPreferredSize(new Dimension(preferredSize.height * 2, preferredSize.height));
        // jComboBox.setPreferredSize(new Dimension(preferredSize.height * 2 + 1, preferredSize.height));
        jComboBox.setPreferredSize(new Dimension(preferredSize.height * 2 + 2, preferredSize.height));
        // jComboBox.setPreferredSize(new Dimension(preferredSize.height * 2 + 3, preferredSize.height));
        // jComboBox.setPreferredSize(new Dimension(preferredSize.height * 2 + 4, preferredSize.height));
        // jComboBox.setPreferredSize(new Dimension(preferredSize.height * 2 + 5, preferredSize.height));
        // jComboBox.setPreferredSize(new Dimension(preferredSize.height * 3, preferredSize.height));
        Dimension jComboBoxPreferredSize = jComboBox.getPreferredSize();
        System.out.println("jComboBoxPreferredSize = " + jComboBoxPreferredSize);
        // 添加选项
        jComboBox.addItem(textJrBtnWait);
        jComboBox.addItem(textJrBtnReadWait);
        jComboBox.addItem(textJrBtnVideo);
        jComboBox.addItem(textJrBtnShopping);

        jComboBox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ComboBoxModel<String> jComboBoxModel = jComboBox.getModel();
                String elementAt = jComboBoxModel.getElementAt(jComboBox.getSelectedIndex());
                selected = elementAt;


                switch (elementAt) {
                    case textJrBtnReadWait:
                    case textJrBtnWait:
                        // universalPanels.getWaitButton().doClick();
                        showWaitButtons();
                        break;
                    case textJrBtnShopping:
                        // universalPanels.getShoppingButton().doClick();
                        showShoppingButtons();
                        break;
                    case textJrBtnVideo:
                        showVideoButtons();
                        break;
                }


            }

        });
        return jComboBox;
    }


    private JButton getButton(int value) {
        final JButton jButton;

        String valueStr = getValueStr(value).toUpperCase();

        jButton = new JButton(valueStr);


        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                AdbTools adbTools = AdbTools.getInstance();
                TimePanels timePanels = adbTools.getTimePanels();
                JButton okButton = timePanels.getInputOkButton();
                UniversalPanels universalPanels = adbTools.getUniversalPanels();

                if (selected != null) {
                    switch (selected) {
                        case textJrBtnVideo:
                            universalPanels.getVideoButton().doClick();
                            // videoDo(timePanels, okButton);
                            // 触发刷视频按钮
                            okButton.doClick();
                            String text = okButton.getText();
                            System.out.println("text = " + text);
                            // ThreadSleep.millisecond(200);
                            // ThreadSleep.seconds(5);
                            // jrBtnWait.doClick();
                            universalPanels.getWaitButton().doClick();
                            // ThreadSleep.millisecond(200);
                            // timePanels.getTaskCheckBox().setSelected(true);
                            timePanels.getStopCheckBox().setSelected(true);

                            break;
                        case textJrBtnReadWait:
                            // System.out.println("xxxxx");
                            // System.out.println("xxxxx");
                            // 打开阅读设置
                            universalPanels.getReadButton().doClick();
                            // timePanels.getInputOkButton().doClick();
                            // 点确定，开始阅读
                            okButton.doClick();
                        case textJrBtnWait:
                            // 打开等待设置
                            universalPanels.getWaitButton().doClick();
                            // timePanels.getTaskCheckBox().setSelected(true);
                            break;

                        case textJrBtnShopping:
                            universalPanels.getShoppingButton().doClick();
                            // timePanels.getTaskCheckBox().setSelected(true);

                    }


                    // else {

                    // timePanels.getStopCheckBox().setSelected(true);
                    timePanels.getTaskCheckBox().setSelected(true);
                    setTimePanelsInput1Value(timePanels, value);
                    okButton.doClick();
                    // }
                }
            }
        });
        return jButton;
    }

    public JPanel getTimingPanels2() {
        return timingPanels2;
    }

    /**
     * 设置时间选择面板的输入框1的值
     *
     * @param timePanels 时间选择面板
     * @param value      需要设置的时间
     */
    private void setTimePanelsInput1Value(TimePanels timePanels, int value) {
        JTextField input1 = timePanels.getInput1();
        // 获取输入框的文本
        while (Integer.parseInt(input1.getText()) < value) {
            timePanels.getPlusBtn().doClick();
        }
    }

    private void showWaitButtons() {
        btn30s.setVisible(false);
        btn35s.setVisible(true);
        btn65.setVisible(true);
        btn95.setVisible(true);
        btn120s.setVisible(true);
        btn180s.setVisible(true);
        btn1H.setVisible(true);
        btn2H.setVisible(true);
        btn3HMore.setVisible(true);
        btn4H.setVisible(true);
    }

    private void showShoppingButtons() {
        btn30s.setVisible(false);
        btn35s.setVisible(true);
        btn65.setVisible(true);
        btn95.setVisible(true);
        btn120s.setVisible(false);
        btn180s.setVisible(false);
        btn1H.setVisible(false);
        btn2H.setVisible(false);
        btn3HMore.setVisible(false);
        btn4H.setVisible(false);
    }

    private void showVideoButtons() {
        btn30s.setVisible(false);
        btn35s.setVisible(false);
        btn65.setVisible(true);
        btn95.setVisible(false);
        btn120s.setVisible(false);
        btn180s.setVisible(false);
        btn1H.setVisible(false);
        btn2H.setVisible(false);
        btn3HMore.setVisible(false);
        btn4H.setVisible(false);
    }

    private void hideAllButtons() {
        btn30s.setVisible(false);
        btn35s.setVisible(false);
        btn65.setVisible(false);
        btn95.setVisible(false);
        btn120s.setVisible(false);
        btn180s.setVisible(false);
        btn1H.setVisible(false);
        btn2H.setVisible(false);
        btn3HMore.setVisible(false);
        btn4H.setVisible(false);
    }

}
