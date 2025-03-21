package adbs.main.ui.jpanels.universal.listener;

import adbs.main.ui.jframe.JFramePack;
import adbs.main.ui.jpanels.adb.listener.ButtonFocusReleaseActionListener;
import adbs.main.ui.jpanels.time.TimePanels;
import adbs.main.ui.jpanels.time.listener.QuJian;
import adbs.main.ui.jpanels.time.listener.ShiPinQuJian;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * 刷视频按钮 操作界面
 */
public class VideoButtonActionListener extends ButtonFocusReleaseActionListener {
    private final TimePanels timePanels;

    public VideoButtonActionListener(TimePanels timePanels) {
        this.timePanels = timePanels;
    }

    @Override
    protected void actionEvent(ActionEvent e) {

        // 显示输入界面
        timePanels.getTimePanel().setVisible(true);
        // 设置标签
        timePanels.getTimeLabel().setText("间隔(s):");
        // 隐藏单选按钮组
        // inputPanels.getTimeRadioPanel().setVisible(false);
        // 显示输入框1
        JTextField input1 = timePanels.getInput1();
        input1.setVisible(true);
        input1.setColumns(3);
        // input1.setText(String.valueOf(9));
        // input1.setText(String.valueOf(12));

        QuJian quJian = ShiPinQuJian.init();
        input1.setText(String.valueOf(quJian.getStart()));

        // 显示输入框2
        JTextField input2 = timePanels.getInput2();
        input2.setVisible(true);
        input2.setColumns(3);

        // input2.setText(String.valueOf(18));
        // input2.setText(String.valueOf(22));
        input2.setText(String.valueOf(quJian.getEnd()));


        timePanels.getPlusBtn().setVisible(true);
        timePanels.getMinusBtn().setVisible(true);

        timePanels.getTaskCheckBox().setVisible(false);
        JCheckBox stopCheckBox = timePanels.getStopCheckBox();
        stopCheckBox.setVisible(false);

        // 设置按钮文字
        timePanels.getInputOkButton().setText("开始刷视频");

        // timePanels.getTimerJLabel().setVisible(false);

        // frame.pack();
        // JFramePack.onJComponentActionEvent(e);
        // AdbTools.getInstance().getFrame().pack();
        JFramePack.pack();


    }
}
