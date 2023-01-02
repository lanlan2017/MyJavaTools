package adbs.main.ui.jpanels.universal.listener;

import adbs.main.ui.jframe.JFramePack;
import adbs.main.ui.jpanels.adb.listener.ButtonFocusReleaseActionListener;
import adbs.main.ui.jpanels.input.InputPanels;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * 设置浏览按钮的操作界面。
 */
public class BrowseButtonActionListener extends ButtonFocusReleaseActionListener {
    // private JFrame frame;
    private InputPanels inputPanels;

    // public BrowseButtonActionListener(JFrame frame, InputPanels inputPanels) {
    //     this.frame = frame;
    //     this.inputPanels = inputPanels;
    // }
    public BrowseButtonActionListener(InputPanels inputPanels) {
        // this.frame = frame;
        this.inputPanels = inputPanels;
    }

    @Override
    protected void actionEvent(ActionEvent e) {
        // 测试替换
        inputPanels.getInputPanel().setVisible(true);
        inputPanels.getTimeLable().setText("时间(s):");
        // inputPanels.getTimeRadioPanel().setVisible(true);
        inputPanels.getInput1().setText(String.valueOf(30));
        inputPanels.getInput1().setColumns(4);
        inputPanels.getInput2().setVisible(false);
        // inputPanels.getPlusBtn().setVisible(false);
        // inputPanels.getMinusBtn().setVisible(false);
        inputPanels.getPlusBtn().setVisible(true);
        inputPanels.getMinusBtn().setVisible(true);
        inputPanels.getInputOkButton().setText("开始浏览");
        inputPanels.getTimerJLabel().setVisible(false);

        // frame.pack();
        JFramePack.onJComponentActionEvent(e);
    }
}
