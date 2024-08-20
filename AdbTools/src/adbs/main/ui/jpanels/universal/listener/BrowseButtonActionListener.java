package adbs.main.ui.jpanels.universal.listener;

import adbs.main.ui.jframe.JFramePack;
import adbs.main.ui.jpanels.adb.listener.ButtonFocusReleaseActionListener;
import adbs.main.ui.jpanels.time.TimePanels;
import adbs.main.ui.jpanels.time.listener.WaitValues;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * 设置浏览按钮的操作界面。
 */
public class BrowseButtonActionListener extends ButtonFocusReleaseActionListener {
    private final TimePanels timePanels;

    public BrowseButtonActionListener(TimePanels timePanels) {
        this.timePanels = timePanels;
    }

    @Override
    protected void actionEvent(ActionEvent e) {
        //        SwingUtilities.invokeLater(new Runnable() {
        //            @Override
        //            public void run() {


        timePanels.getTimePanel().setVisible(true);
        timePanels.getTimeLabel().setText("时间(s):");
        // inputPanels.getTimeRadioPanel().setVisible(true);
        JTextField input1 = timePanels.getInput1();

//        int index = 5;

        int index = 0;
        WaitValues.setIndex(index);
        input1.setText(String.valueOf(WaitValues.values[index]));
//        input1.setText(String.valueOf(index));

        input1.setColumns(4);
        timePanels.getInput2().setVisible(false);
        timePanels.getPlusBtn().setVisible(true);
        timePanels.getMinusBtn().setVisible(true);

        timePanels.getTaskCheckBox().setVisible(false);
        JCheckBox stopCheckBox = timePanels.getStopCheckBox();
        stopCheckBox.setVisible(false);

        timePanels.getInputOkButton().setText("开始浏览");
        // timePanels.getTimerJLabel().setVisible(false);
        // 调整窗体到最佳大小
        // JFramePack.onJComponentActionEvent(e);
        JFramePack.pack();
        //                AdbTools.getInstance().getFrame().pack();
        //            }
        //        });

    }
}
