package adbs.main.ui.jpanels.control;

import adbs.main.AdbTools;
import adbs.main.ui.config.FlowLayouts;
import adbs.main.ui.jpanels.time.TimePanels;
import adbs.main.ui.jpanels.universal.UniversalPanels;
import adbs.tools.thread.ThreadSleep;
import tools.swing.button.AbstractButtons;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 控制面板
 */
public class ControlJPanels {
    private final JPanel controlJPanel;

    // V65s_S_T
    /**
     * 刷视频，等待65秒后按停止键和任务键
     */
    private final JButton v65s_Stop_TaskBtn;
    /**
     * 等待335秒后 按 任务键
     */
    private final JButton wait35s_TaskBtn;
    /**
     * 等待65秒后 按 任务键
     */
    private final JButton wait65s_TaskBtn;
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

        // videoStopTaskBtn
        v65s_Stop_TaskBtn = gVideoBtn(65);
        wait35s_TaskBtn = gWaitBtn(35);
        wait65s_TaskBtn = gWaitBtn(65);
        wait95s_TaskBtn = gWaitBtn(95);
        wait120s_TaskBtn = gWaitBtn(120);
        wait180s_TaskBtn = gWaitBtn(180);

        shopping35s_TaskBtn = gShoppingBtn(35);
        shopping95s_TaskBtn = gShoppingBtn(95);


        controlJPanel.add(v65s_Stop_TaskBtn);
        controlJPanel.add(wait35s_TaskBtn);
        controlJPanel.add(wait65s_TaskBtn);
        controlJPanel.add(wait95s_TaskBtn);
        controlJPanel.add(wait120s_TaskBtn);
        controlJPanel.add(wait180s_TaskBtn);
        controlJPanel.add(shopping35s_TaskBtn);
        controlJPanel.add(shopping95s_TaskBtn);

        // AbstractButtons.setMarginInButtonJPanel(controlJPanel);
        AbstractButtons.setMarginInButtonJPanel(controlJPanel, 1);
    }

    /**
     * 生成刷视频按钮
     *
     * @param time 刷视频要等待的秒数
     * @return
     */
    private JButton gVideoBtn(final int time) {
        final JButton v65s_Stop_TaskBtn;
        v65s_Stop_TaskBtn = new JButton("v" + time);
        v65s_Stop_TaskBtn.setToolTipText("刷视频" + time + "s后按下停止键和任务键");
        v65s_Stop_TaskBtn.addActionListener(new ActionListener() {
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
                // 设置等待时间
                setTimePanelsInput1Value(timePanels, time);
                // 按下开始等待按键
                inputOkButton.doClick();
            }
        });
        return v65s_Stop_TaskBtn;
    }

    /**
     * 生成逛街按钮
     *
     * @param time 需要逛街的时间
     * @return 实现逛街功能的按钮对象
     */
    private JButton gShoppingBtn(final int time) {
        final JButton shopping35s_TaskBtn;
        shopping35s_TaskBtn = new JButton("s" + time);
        shopping35s_TaskBtn.setToolTipText("逛街" + time + "s后按下任务键");
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

                setTimePanelsInput1Value(timePanels, time);
                // 获取时间面板的 确定按钮
                JButton inputOkButton = timePanels.getInputOkButton();
                // 按下开始等待按键
                inputOkButton.doClick();

            }
        });
        return shopping35s_TaskBtn;
    }

    /**
     * 生成等待指定时间的按钮
     *
     * @param time 要等待的时间
     * @return 可实现等待指定时间的功能的JButton对象
     */
    private JButton gWaitBtn(int time) {
        final JButton wait65s_TaskBtn;
        wait65s_TaskBtn = new JButton("w" + time);
        wait65s_TaskBtn.setToolTipText("等待" + time + "s后按下任务键");
        wait65s_TaskBtn.addActionListener(new ActionListener() {
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

                // setTimePanelsInput1Value(timePanels, 65);
                // setTimePanelsInput1Value(timePanels, Integer.parseInt(time));
                setTimePanelsInput1Value(timePanels, time);
                // 获取时间面板的 确定按钮
                JButton inputOkButton = timePanels.getInputOkButton();
                // 按下开始等待按键
                inputOkButton.doClick();
            }
        });
        return wait65s_TaskBtn;
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

    public JPanel getControlJPanel() {
        return controlJPanel;
    }
}
