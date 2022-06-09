package adbs.action.listener;

import adbs.action.listener.abs.ButtonFocusReleaseActionListener;
import adbs.action.model.InputPanelModel;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class VideoButtonActionListener extends ButtonFocusReleaseActionListener {
    private JFrame frame;
    private InputPanelModel inputPanelModel;

    public VideoButtonActionListener(JFrame frame, InputPanelModel inputPanelModel) {
        this.frame = frame;
        this.inputPanelModel = inputPanelModel;
    }

    @Override
    protected void actionEvent(ActionEvent e) {
        // 显示输入界面
        inputPanelModel.getInputPanel().setVisible(true);
        // 设置标签
        inputPanelModel.getTimeLable().setText("随机间隔(s):");
        // 隐藏单选按钮组
        inputPanelModel.getTimeRadioPanel().setVisible(false);
        // 显示输入框1
        JTextField input1 = inputPanelModel.getInput1();
        input1.setVisible(true);
        input1.setColumns(3);
        input1.setText(String.valueOf(7));
        // 显示输入框2
        JTextField input2 = inputPanelModel.getInput2();
        input2.setVisible(true);
        input2.setColumns(3);
        input2.setText(String.valueOf(14));
        // 设置按钮文字
        inputPanelModel.getInputOkButton().setText("开始刷视频");
        frame.pack();
    }
}
