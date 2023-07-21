package adbs.main.ui.jpanels.control;

import adbs.main.AdbTools;
import adbs.main.ui.config.FlowLayouts;
import adbs.main.ui.jpanels.time.TimePanels;
import adbs.main.ui.jpanels.universal.UniversalPanels;
import tools.swing.button.AbstractButtons;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 控制面板
 */
public class ControlJPanels {
    private final JPanel controlJPanel;

    /**
     * 刷视频，等待65秒后按停止键和任务键
     */
    // V65s_S_T
    private final JButton dianTaoBtn;
    /**
     * 等待95s后，按任务键
     */
    private final JButton wait95s_TaskBtn;

    private final JButton wait120s_TaskBtn;

    /**
     * 等待180秒后，按任务键
     */
    private final JButton wait180s_TaskBtn;

    /**
     * 逛街35s后，按任务键
     */

    private final JButton shopping35s_TaskBtn;
    private final JButton shopping95s_TaskBtn;

    public ControlJPanels() {
        controlJPanel = new JPanel(FlowLayouts.flowLayoutLeft);
        // controlJPanel.setBorder(new TitledBorder("控制面板"));
        // Font font1 = new Font(Font.SANS_SERIF, Font.BOLD, 16);
        // Font font2 = new Font(Font.SANS_SERIF, Font.BOLD, 12);
        // Font font2 = new Font("Consolas", Font.BOLD, 12);
        // 使用微软的等宽字体
        // Font font2 = new Font("Consolas", Font.PLAIN, 14);

        // label = new JLabel("时间:");

        // videoStopTaskBtn
        dianTaoBtn = new JButton("v65");
        dianTaoBtn.setToolTipText("刷视频65s后按下停止键和任务键");
        dianTaoBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 获取工具的单例对象
                AdbTools adbTools = AdbTools.getInstance();
                // 获取通用面板对象
                UniversalPanels universalPanels = adbTools.getUniversalPanels();
                // 获取时间面板对象
                TimePanels timePanels = adbTools.getTimePanels();
                // 获取通用面板的 刷视频按钮
                JButton videoButton = universalPanels.getVideoButton();
                // 点击通用面板的 刷视频按钮
                videoButton.doClick();

                // 获取时间面板的 确定按钮
                JButton inputOkButton = timePanels.getInputOkButton();
                // 点击时间面板的OK按钮
                inputOkButton.doClick();
                // adbTools.getFrame().pack();


                // 获取时间时间选择面板的等待按钮
                JButton waitButton = universalPanels.getWaitButton();
                // 点击时间选择面板的等待按钮
                waitButton.doClick();
                // 获取时间选择按钮的 停止多选框
                JCheckBox stopCheckBox_TimePanel = timePanels.getStopCheckBox();
                // // 勾选 时间面板的 停止多选框
                stopCheckBox_TimePanel.setSelected(true);
                // // 勾选 时间面板的 任务多选框
                timePanels.getTaskCheckBox().setSelected(true);
                // //   点击两次加号键
                // timePanels.getPlusBtn().doClick();
                // timePanels.getPlusBtn().doClick();

                setTimePanelsInput1Value(timePanels, 65);
                // 按下开始等待按键
                inputOkButton.doClick();
            }
        });
        wait180s_TaskBtn = new JButton("w180");
        wait180s_TaskBtn.setToolTipText("等待180s后按下任务键");
        wait180s_TaskBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 获取工具的单例对象
                AdbTools adbTools = AdbTools.getInstance();
                // 获取通用面板对象
                UniversalPanels universalPanels = adbTools.getUniversalPanels();
                // 获取时间时间选择面板的等待按钮
                JButton waitButton = universalPanels.getWaitButton();
                // 获取时间面板对象
                TimePanels timePanels = adbTools.getTimePanels();
                // 点击时间选择面板的等待按钮
                waitButton.doClick();
                // // 获取时间选择按钮的 停止多选框
                // JCheckBox stopCheckBox_TimePanel = timePanels.getStopCheckBox();
                // // // 勾选 时间面板的 停止多选框
                // stopCheckBox_TimePanel.setSelected(true);
                // // 勾选 时间面板的 任务多选框
                timePanels.getTaskCheckBox().setSelected(true);
                // 设置输入框1的值为180
                setTimePanelsInput1Value(timePanels, 180);
                // 获取时间面板的 确定按钮
                JButton inputOkButton = timePanels.getInputOkButton();
                // 按下开始等待按键
                inputOkButton.doClick();
            }
        });

        wait95s_TaskBtn = new JButton("w95");
        wait95s_TaskBtn.setToolTipText("等待95s后按下任务键");
        wait95s_TaskBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 获取工具的单例对象
                AdbTools adbTools = AdbTools.getInstance();
                // 获取通用面板对象
                UniversalPanels universalPanels = adbTools.getUniversalPanels();
                // 获取时间时间选择面板的等待按钮
                JButton waitButton = universalPanels.getWaitButton();
                // 点击时间选择面板的等待按钮
                waitButton.doClick();

                // 获取时间面板对象
                TimePanels timePanels = adbTools.getTimePanels();
                // // 获取时间选择按钮的 停止多选框
                // JCheckBox stopCheckBox_TimePanel = timePanels.getStopCheckBox();
                // // // 勾选 时间面板的 停止多选框
                // stopCheckBox_TimePanel.setSelected(true);
                // // 勾选 时间面板的 任务多选框
                timePanels.getTaskCheckBox().setSelected(true);
                // //   多次点击时间增加按钮

                setTimePanelsInput1Value(timePanels, 95);
                // 获取时间面板的 确定按钮
                JButton inputOkButton = timePanels.getInputOkButton();
                // 按下开始等待按键
                inputOkButton.doClick();
            }
        });
        wait120s_TaskBtn = new JButton("w120");
        wait120s_TaskBtn.setToolTipText("等待120s后按下任务键");
        wait120s_TaskBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 获取工具的单例对象
                AdbTools adbTools = AdbTools.getInstance();
                // 获取通用面板对象
                UniversalPanels universalPanels = adbTools.getUniversalPanels();
                // 获取时间时间选择面板的等待按钮
                JButton waitButton = universalPanels.getWaitButton();
                // 点击时间选择面板的等待按钮
                waitButton.doClick();

                // 获取时间面板对象
                TimePanels timePanels = adbTools.getTimePanels();
                // // 获取时间选择按钮的 停止多选框
                // JCheckBox stopCheckBox_TimePanel = timePanels.getStopCheckBox();
                // // // 勾选 时间面板的 停止多选框
                // stopCheckBox_TimePanel.setSelected(true);
                // // 勾选 时间面板的 任务多选框
                timePanels.getTaskCheckBox().setSelected(true);
                setTimePanelsInput1Value(timePanels, 120);

                // 获取时间面板的 确定按钮
                JButton inputOkButton = timePanels.getInputOkButton();
                // 按下开始等待按键
                inputOkButton.doClick();
            }
        });

        shopping35s_TaskBtn = new JButton("s35");
        shopping35s_TaskBtn.setToolTipText("逛街35s后按下任务键");
        shopping35s_TaskBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 获取工具的单例对象
                AdbTools adbTools = AdbTools.getInstance();
                // 获取通用面板对象
                UniversalPanels universalPanels = adbTools.getUniversalPanels();
                JButton shoppingButton = universalPanels.getShoppingButton();
                // 点击逛街键
                shoppingButton.doClick();


                // 获取时间面板对象
                TimePanels timePanels = adbTools.getTimePanels();
                // // 获取时间选择按钮的 停止多选框
                // JCheckBox stopCheckBox_TimePanel = timePanels.getStopCheckBox();
                // // // 勾选 时间面板的 停止多选框
                // stopCheckBox_TimePanel.setSelected(true);
                // // 勾选 时间面板的 任务多选框
                timePanels.getTaskCheckBox().setSelected(true);

                setTimePanelsInput1Value(timePanels, 35);
                // 获取时间面板的 确定按钮
                JButton inputOkButton = timePanels.getInputOkButton();
                // 按下开始等待按键
                inputOkButton.doClick();

            }
        });

        shopping95s_TaskBtn = new JButton("s95");
        shopping95s_TaskBtn.setToolTipText("逛街35s后按下任务键");
        shopping95s_TaskBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 获取工具的单例对象
                AdbTools adbTools = AdbTools.getInstance();
                // 获取通用面板对象
                UniversalPanels universalPanels = adbTools.getUniversalPanels();
                JButton shoppingButton = universalPanels.getShoppingButton();
                // 点击逛街键
                shoppingButton.doClick();


                // 获取时间面板对象
                TimePanels timePanels = adbTools.getTimePanels();
                // // 获取时间选择按钮的 停止多选框
                // JCheckBox stopCheckBox_TimePanel = timePanels.getStopCheckBox();
                // // // 勾选 时间面板的 停止多选框
                // stopCheckBox_TimePanel.setSelected(true);
                // // 勾选 时间面板的 任务多选框
                timePanels.getTaskCheckBox().setSelected(true);

                setTimePanelsInput1Value(timePanels, 95);
                // 获取时间面板的 确定按钮
                JButton inputOkButton = timePanels.getInputOkButton();
                // 按下开始等待按键
                inputOkButton.doClick();

            }
        });


        // controlJPanel.add(label);
        // controlJPanel.add(hourTextField);
        // controlJPanel.add(hourLabel);
        // // controlJPanel.add(hour);
        // controlJPanel.add(minuteTextField);
        // // controlJPanel.add(minute);
        // controlJPanel.add(minuteLabel);
        // controlJPanel.add(secondTextField);
        // // controlJPanel.add(second);
        // // controlJPanel.add(secondLabel);
        // controlJPanel.add(dormantOKButton);
        // controlJPanel.add(cancelBtn);


        controlJPanel.add(dianTaoBtn);
        controlJPanel.add(wait95s_TaskBtn);
        controlJPanel.add(wait120s_TaskBtn);
        controlJPanel.add(wait180s_TaskBtn);
        controlJPanel.add(shopping35s_TaskBtn);
        controlJPanel.add(shopping95s_TaskBtn);

        // AbstractButtons.setMarginInButtonJPanel(controlJPanel);
        AbstractButtons.setMarginInButtonJPanel(controlJPanel, 1);
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
            // 35s
            timePanels.getPlusBtn().doClick();
        }
    }

    public JPanel getControlJPanel() {
        return controlJPanel;
    }

    // public JLabel getLabel() {
    //     return label;
    // }

    // public JButton getDormantOKButton() {
    //     return dormantOKButton;
    // }
    //
    // public JTextField getSecondTextField() {
    //     return secondTextField;
    // }
}
