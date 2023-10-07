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
    /**
     * vw
     */
    private final String textJrBtnVideo = "vw";
    /**
     * s
     */
    private final String textJrBtnShopping = "s";
    /**
     * w
     */
    private final String textJrBtnWait = "w";
    /**
     * rw
     */
    private final String textJrBtnReadWait = "rw";

    private final JComboBox<String> jComboBox;
    private final JButton btn35s;
    private final JButton btn30s;
    private final JButton btn65s;
    private final JButton btn95s;
    private final JButton btn120s;
    private final JButton btn180s;
    private final JButton btn3HMore;
    private final JButton btn4H;
    private final JButton btn1H;
    private final JButton btn2H;
    private final JButton btn20M;
    private final JButton btn1_5H;
    private String selected;

    public TimingPanels2() {
        timingPanels2 = new JPanel();
        timingPanels2.setLayout(FlowLayouts.flowLayoutLeft);
        btn30s = initButton(30);
        btn35s = initButton(35);
        btn65s = initButton(65);
        btn95s = initButton(95);
        btn120s = initButton(120);
        btn180s = initButton(180);
        btn1H = initButton(1 * 60 * 60);
        btn1_5H = initButton(1 * 60 * 60 + 30 * 60);
        btn2H = initButton(2 * 60 * 60);
        btn20M = initButton(20 * 60);
        // btn1H= btn1H;
        btn3HMore = initButton(values[values.length - 2]);
        btn4H = initButton(values[values.length - 1]);
        hideAllButtons();

        jComboBox = initComboBox();

        timingPanels2.add(jComboBox);

        timingPanels2.add(btn1H);
        timingPanels2.add(btn1_5H);
        timingPanels2.add(btn2H);
        timingPanels2.add(btn3HMore);
        timingPanels2.add(btn4H);
        timingPanels2.add(btn30s);
        timingPanels2.add(btn35s);
        timingPanels2.add(btn65s);
        timingPanels2.add(btn95s);
        timingPanels2.add(btn120s);
        timingPanels2.add(btn180s);
        timingPanels2.add(btn20M);


        AbstractButtons.setMarginInButtonJPanel(timingPanels2, 0);
    }

    private JComboBox<String> initComboBox() {
        final JComboBox<String> jComboBox;
        jComboBox = new JComboBox<>();
        jComboBox.setFont(Fonts.Consolas_BOLD_13);
        // new Font(Font.)

        // 获取其他按钮的首选大小
        Dimension preferredSize = btn65s.getPreferredSize();
        // 设置下拉框的首选大小
        jComboBox.setPreferredSize(new Dimension(preferredSize.height * 2 + 2, preferredSize.height));
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
                        showReadWaitButtons();
                        break;
                    case textJrBtnWait:
                        showWaitButtons();
                        break;
                    case textJrBtnShopping:
                        showShoppingButtons();
                        break;
                    case textJrBtnVideo:
                        showVideoButtons();
                        break;
                }

                JFrame frame = AdbTools.getInstance().getFrame();
                if (frame != null) {
                    frame.pack();
                }


            }

        });
        return jComboBox;
    }


    private JButton initButton(int value) {
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
                            // 打开刷视频设置
                            universalPanels.getVideoButton().doClick();
                            // videoDo(timePanels, okButton);
                            // 开始刷视频
                            okButton.doClick();
                            // 打开等待设置
                            universalPanels.getWaitButton().doClick();
                            // 勾选stop，结束等待之后，触发停止按钮
                            timePanels.getStopCheckBox().setSelected(true);

                            break;
                        case textJrBtnReadWait:
                            // 打开阅读设置
                            universalPanels.getReadButton().doClick();
                            // 点确定，开始阅读
                            okButton.doClick();
                            universalPanels.getWaitButton().doClick();
                            timePanels.getStopCheckBox().setSelected(true);
                            break;

                        case textJrBtnWait:
                            // 打开等待设置
                            universalPanels.getWaitButton().doClick();
                            timePanels.getStopCheckBox().setSelected(false);
                            break;

                        case textJrBtnShopping:
                            universalPanels.getShoppingButton().doClick();
                            break;

                    }

                    timePanels.getTaskCheckBox().setSelected(true);
                    setTimePanelsInput1Value(timePanels, value);
                    okButton.doClick();
                }
            }
        });
        return jButton;
    }

    public JPanel getTimingPanels2() {
        return timingPanels2;
    }

    public JComboBox<String> getjComboBox() {
        return jComboBox;
    }

    public JButton getBtn35s() {
        return btn35s;
    }

    public JButton getBtn30s() {
        return btn30s;
    }

    public JButton getBtn65s() {
        return btn65s;
    }

    public JButton getBtn95s() {
        return btn95s;
    }

    public JButton getBtn120s() {
        return btn120s;
    }

    public JButton getBtn180s() {
        return btn180s;
    }

    public JButton getBtn3HMore() {
        return btn3HMore;
    }

    public JButton getBtn4H() {
        return btn4H;
    }

    public JButton getBtn1H() {
        return btn1H;
    }

    public JButton getBtn2H() {
        return btn2H;
    }

    public JButton getBtn20M() {
        return btn20M;
    }

    public JButton getBtn1_5H() {
        return btn1_5H;
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
        btn65s.setVisible(true);
        btn95s.setVisible(true);
        btn120s.setVisible(true);
        btn180s.setVisible(true);
        btn20M.setVisible(false);
        btn1H.setVisible(true);
        btn1_5H.setVisible(true);
        btn2H.setVisible(true);
        btn3HMore.setVisible(true);
        btn4H.setVisible(false);
    }

    private void showReadWaitButtons() {
        btn30s.setVisible(false);
        btn35s.setVisible(false);
        btn65s.setVisible(false);
        btn95s.setVisible(false);
        btn120s.setVisible(false);
        btn180s.setVisible(false);
        btn20M.setVisible(true);
        btn1H.setVisible(true);
        btn1_5H.setVisible(true);
        btn2H.setVisible(true);
        btn3HMore.setVisible(true);
        btn4H.setVisible(true);
    }

    private void showShoppingButtons() {
        btn30s.setVisible(false);
        btn35s.setVisible(true);
        btn65s.setVisible(true);
        btn95s.setVisible(true);
        btn120s.setVisible(false);
        btn180s.setVisible(false);
        btn20M.setVisible(false);
        btn1H.setVisible(false);
        btn1_5H.setVisible(false);
        btn2H.setVisible(false);
        btn3HMore.setVisible(false);
        btn4H.setVisible(false);
    }

    private void showVideoButtons() {
        btn30s.setVisible(false);
        btn35s.setVisible(false);
        btn65s.setVisible(true);
        btn95s.setVisible(false);
        btn120s.setVisible(false);
        btn180s.setVisible(true);
        btn20M.setVisible(true);
        btn1H.setVisible(false);
        btn1_5H.setVisible(false);
        btn2H.setVisible(false);
        btn3HMore.setVisible(false);
        btn4H.setVisible(false);
    }

    private void hideAllButtons() {
        btn30s.setVisible(false);
        btn35s.setVisible(false);
        btn65s.setVisible(false);
        btn95s.setVisible(false);
        btn120s.setVisible(false);
        btn180s.setVisible(false);
        btn20M.setVisible(false);
        btn1H.setVisible(false);
        btn1_5H.setVisible(false);
        btn2H.setVisible(false);
        btn3HMore.setVisible(false);
        btn4H.setVisible(false);
    }

}
