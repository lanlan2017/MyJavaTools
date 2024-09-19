package adbs.main.ui.jpanels.timeauto2;

import adbs.main.AdbTools;
import adbs.main.ui.config.FlowLayouts;
import adbs.main.ui.config.Fonts;
import adbs.main.ui.jframe.JFramePack;
import adbs.main.ui.jpanels.time.TimePanels;
import adbs.main.ui.jpanels.time.beep.BeepRunnable;
import adbs.main.ui.jpanels.time.listener.WaitValues;
import adbs.main.ui.jpanels.universal.UniversalPanels;
import adbs.main.ui.jpanels.universal.runnable.CloseableRunnable;
import adbs.tools.thread.ThreadSleep;
import tools.swing.button.AbstractButtons;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
    private final JButton btn210s;
    private final JButton btn5M;
    private final JButton btn20M;
    private final JButton btn40M;
    private final JButton btn1H;
    private final JButton btn2H;
    private final JButton btn3HMore;
    private final JButton btn4H;
    private final JButton btn5H;
    private final JButton btn1_5H;
    private String selected;
    private final JButton btn20s;

    public TimingPanels2() {
        timingPanels2 = new JPanel();
        timingPanels2.setLayout(FlowLayouts.flowLayoutLeft);
        btn20s = initButton(s20);
        //        btn30s = initButton(30);
        btn30s = initButton(s30);
        //        btn35s = initButton(35);
        btn35s = initButton(s35);
        //        btn65s = initButton(65);
        btn65s = initButton(s65);
        //        btn95s = initButton(95);
        btn95s = initButton(s95);
        btn120s = initButton(120);
        btn180s = initButton(180);
        btn5M = initButton(m5);
        btn210s = initButton(m3s30);
        // btn1H = initButton(1 * 60 * 60);
        btn1H = initButton(h1);
        btn1_5H = initButton(1 * 60 * 60 + 30 * 60);
        // btn2H = initButton(2 * 60 * 60);
        btn2H = initButton(h2);
        // btn20M = initButton(20 * 60);
        btn20M = initButton(m20);
        // btn40M = initButton(40 * 60);
        btn40M = initButton(m40);

        // btn1H= btn1H;
        // btn3HMore = initButton(values[values.length - 2]);
        // btn3HMore = initButton(3 * 60 * 60 + 10 * 60);
        btn3HMore = initButton(h3m10);
        // btn4H = initButton(values[values.length - 1]);
        // btn4H = initButton(4 * 60 * 60);
        btn4H = initButton(h4);
        btn5H = initButton(h5);
        hideAllButtons();


        jComboBox = initComboBox();
        timingPanels2.add(jComboBox);

        timingPanels2.add(btn1H);
        timingPanels2.add(btn1_5H);
        timingPanels2.add(btn2H);
        timingPanels2.add(btn3HMore);
        timingPanels2.add(btn4H);
        timingPanels2.add(btn5H);

        timingPanels2.add(btn20s);
        timingPanels2.add(btn30s);
        timingPanels2.add(btn35s);
        timingPanels2.add(btn65s);
        timingPanels2.add(btn95s);
        timingPanels2.add(btn120s);
        timingPanels2.add(btn180s);
        timingPanels2.add(btn5M);
        timingPanels2.add(btn210s);
        timingPanels2.add(btn20M);
        timingPanels2.add(btn40M);


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
                // 更新界面
                JFramePack.pack();
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

                // JFramePack.pack();
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
        // System.out.println("--------------------------------");
        // System.out.println("value sdfsgfsfdfds = " + value);
        // System.out.println("index = " + index);
        // System.out.println("values[index] = " + values[index]);

        while (values[index] < value) {
            index++;
        }

        // System.out.println("index 2 = " + index);
        // System.out.println("values[index] 2 = " + values[index]);

        input1.setText(String.valueOf(values[index]));
        input1.setColumns(greaterOrEqual4(getNumLength(values[index])));

        // System.out.println("--------------------------------");
    }


    private void showWaitButtons() {
        // 先把所有的按钮都隐藏
        hideAllButtons();
        // 然后再显示需要的按钮
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                btn35s.setVisible(true);
                btn65s.setVisible(true);
                btn95s.setVisible(true);
                btn120s.setVisible(true);
                btn180s.setVisible(true);
                btn5M.setVisible(true);
                btn1H.setVisible(true);
                btn1_5H.setVisible(true);
                btn2H.setVisible(true);
                btn3HMore.setVisible(true);
            }
        });
    }

    private void showReadWaitButtons() {
        hideAllButtons();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                btn20M.setVisible(true);
                btn1H.setVisible(true);
                btn1_5H.setVisible(true);
                btn2H.setVisible(true);
                btn3HMore.setVisible(true);
                btn4H.setVisible(true);
                btn5H.setVisible(true);
            }
        });


    }

    private void showShoppingButtons() {
        hideAllButtons();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                btn20s.setVisible(true);
                btn35s.setVisible(true);
                btn65s.setVisible(true);
                btn95s.setVisible(true);
            }
        });
    }

    /**
     * VW，需要的时间
     */
    private void showVideoButtons() {
        hideAllButtons();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                btn65s.setVisible(true);
                btn95s.setVisible(true);
                btn180s.setVisible(true);
                btn210s.setVisible(true);
                btn20M.setVisible(true);
                btn40M.setVisible(true);
                btn1H.setVisible(true);
            }
        });
    }

    private void hideAllButtons() {
        // 在一段时间后隐藏所有的按钮组件
        SwingUtilities.invokeLater(() -> hideAllButtons(timingPanels2));

    }

    /**
     * 隐藏所欲的按钮
     *
     * @param container 存放JButton的容器，例如：JPanel。
     */
    private static void hideAllButtons(Container container) {
        // 遍历所有的组件
        for (Component component : container.getComponents()) {
            if (component instanceof JButton) {
                //隐藏所有的按钮
                component.setVisible(false);
            }
        }
    }


    /**
     * 设置JComboBox的选项
     *
     * @param index JComboBox 要选择的选项的索引
     */
    private void selectedJComboBoxIndex(int index) {
        if (jComboBox != null) {
            // 获取当前选项的
            int selectedIndex = jComboBox.getSelectedIndex();
            int itemCount = jComboBox.getItemCount();
            // 新的索引和原来的索引不相等，并且小于选项数量的化
            if (selectedIndex != index && index < itemCount) {
                SwingUtilities.invokeLater(() -> jComboBox.setSelectedIndex(index));
                // JFramePack.pack();
            }
        }
    }

    public void w() {
        System.out.println("    建议：等待模式");
        selectedJComboBoxIndex(0);
    }

    public void rw() {
        System.out.println("    建议：阅读模式");
        selectedJComboBoxIndex(1);
    }


    public void vw() {
        System.out.println("    建议：视频模式");
        selectedJComboBoxIndex(2);
    }

    public void s() {
        System.out.println("    建议：逛街模式");
        selectedJComboBoxIndex(3);
    }

    public void auto(String code) {
        if (code.matches("[a-z]+_[0-9a-zA-Z.+-]+")) {
            String[] s = code.split("_");
            System.out.println("auto");
            String s0 = s[0];
            String s2 = s[1];
            // 改变JComboBox的选项
            changeJComboBoxOption(s0);
            // SwingUtilities.invokeLater(() -> btnDoClick(s2));
            // 等待一段时间，不要立马就执行
            //            ThreadSleep.seconds(5);
            //            ThreadSleep.seconds(4);
            // 点击对应时间的按钮
            btnDoClick(s2);
        }
    }

    private void changeJComboBoxOption(String s0) {
        switch (s0) {
            case "w":
                w();
                break;
            case "vw":
                vw();
                break;
            case "rw":
                rw();
                break;
            case "s":
                s();
                break;
        }
    }

    private void btnDoClick(String s2) {
        SwingUtilities.invokeLater(() -> {

            switch (s2) {
                case "30s":
                    btn30s.doClick();
                    break;
                case "35s":
                    btn35s.doClick();
                    break;
                case "65s":
                    btn65s.doClick();
                    break;
                case "95s":
                    btn95s.doClick();
                    break;
                case "120s":
                    btn120s.doClick();
                    break;
                case "180s":
                    btn180s.doClick();
                    break;
                case "210s":
                    btn210s.doClick();
                    break;
                case "20M":
                    btn20M.doClick();
                    break;
                case "40M":
                    btn40M.doClick();
                    break;
                case "1H":
                    btn1H.doClick();
                    break;
                case "2H":
                    btn2H.doClick();
                    break;
                case "1.5H":
                    btn1_5H.doClick();
                    break;
                case "3H+":
                    btn3HMore.doClick();
                    break;
                case "4H":
                    btn4H.doClick();
                    break;
                case "5H":
                    btn5H.doClick();
                    break;
            }


        });
    }


    public void w35s() {
        auto("w_35s");
        // dialogAuto("w_35s");
    }

    public void w35sDialog() {
        // auto("w_35s");
        dialogAuto("w_35s");
    }

    public void w65s() {
        auto("w_65s");
        // dialogAuto("w_65s");
    }

    public void w65sDialog() {
        // auto("w_65s");
        dialogAuto("w_65s");
    }

    public void w95s() {
        auto("w_95s");
    }

    public void w95sDialog() {
        dialogAuto("w_95s");
    }

    public void w180s() {
        auto("w_180s");
    }

    public void w5M() {
        auto("w_5M");
    }

    public void w180sDialog() {
        // auto("w_180s");
        dialogAuto("w_180s");
    }

    public void s35s() {
        waitAuto("s_35s");
        //        ThreadSleep.seconds(4);
        //        auto("s_35s");
    }

    public void s35sDialog() {
        // auto("s_35s");
        dialogAuto("s_35s");
    }

    public void s65s() {
        //        ThreadSleep.seconds(4);
        //        auto("s_65s");
        waitAuto("s_65s");
    }

    public void s65sDialog() {
        dialogAuto("s_65s");
    }


    public void s95s() {
        //        ThreadSleep.seconds(4);
        //        auto("s_95s");
        waitAuto("s_95s");
    }

    public void s95sDialog() {
        // auto("s_95s");
        dialogAuto("s_95s");
    }

    public void vw95s() {
        //        auto("vw_95s");
        waitAuto("vw_95s");
    }

    public void vw180s() {
        waitAuto("vw_180s");
        // dialogAuto("vw_180s");
    }


    public void vw180sDialog() {
        // auto("vw_180s");
        dialogAuto("vw_180s");
    }

    public void vw1HDialog() {
        // auto("vw_180s");
        dialogAuto("vw_1H");
    }

    public void rw5HDialog() {
        // auto("vw_180s");
        dialogAuto("rw_5H");
    }

    /**
     * 先等待几秒钟再执行自动操作
     *
     * @param code
     */
    private void waitAuto(String code) {
        ThreadSleep.seconds(3);
        auto(code);
    }

    /**
     * 在自动操作之前弹窗确认
     *
     * @param code auto方法的代码
     */
    private void dialogAuto(String code) {
        CloseableRunnable beepRun = (CloseableRunnable) BeepRunnable.getInstance();
        // 启动响铃提醒功能
        new Thread(beepRun).start();
        /*
         * 弹窗确认
         */
        AdbTools.getInstance().showDialogOkClose(code, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                auto(code);
                beepRun.stop();
            }
        }, new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                beepRun.stop();
                //                JDialog source = (JDialog) e.getSource();
                //                source.dispose();
            }
        });
    }

}
