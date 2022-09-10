package adbs.action.listener;

import adbs.action.listener.abs.ButtonFocusReleaseActionListener;
import adbs.test.auto.ui.InputPanels;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * 刷视频按钮 操作界面
 */
public class VideoButtonActionListener extends ButtonFocusReleaseActionListener {
    private JFrame frame;
    // private InputPanelModel inputPanelModel;
    private InputPanels inputPanels;

    // public VideoButtonActionListener(JFrame frame, InputPanelModel inputPanelModel) {
    //     this.frame = frame;
    //     this.inputPanelModel = inputPanelModel;
    // }
    public VideoButtonActionListener(JFrame frame,  InputPanels inputPanels) {
        this.frame = frame;
        this.inputPanels = inputPanels;
    }

    @Override
    protected void actionEvent(ActionEvent e) {
        // 显示输入界面
        inputPanels.getInputPanel().setVisible(true);
        // 设置标签
        inputPanels.getTimeLable().setText("间隔(s):");
        // 隐藏单选按钮组
        inputPanels.getTimeRadioPanel().setVisible(false);
        // 显示输入框1
        JTextField input1 = inputPanels.getInput1();
        input1.setVisible(true);
        input1.setColumns(3);
        input1.setText(String.valueOf(9));
        // 显示输入框2
        JTextField input2 = inputPanels.getInput2();
        input2.setVisible(true);
        input2.setColumns(3);
        input2.setText(String.valueOf(18));

        inputPanels.getPlusBtn().setVisible(true);
        inputPanels.getMinusBtn().setVisible(true);

        // 设置按钮文字
        inputPanels.getInputOkButton().setText("开始刷视频");



        frame.pack();
    }
}
