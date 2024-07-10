package adbs.main.ui.jpanels.universal.listener;

import adbs.main.ui.jframe.JFramePack;
import adbs.main.ui.jpanels.adb.listener.ButtonFocusReleaseActionListener;
import adbs.main.ui.jpanels.time.TimePanels;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ReadButtonActionListener extends ButtonFocusReleaseActionListener {
    private TimePanels timePanels;

    public ReadButtonActionListener(TimePanels timePanels) {
        this.timePanels = timePanels;
    }

    @Override
    protected void actionEvent(ActionEvent e) {

//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
                // 显示输入界面
                timePanels.getTimePanel().setVisible(true);
                // 设置标签
                timePanels.getTimeLabel().setText("间隔(s):");

                // 显示输入框1
                JTextField input1 = timePanels.getInput1();
                input1.setVisible(true);
                input1.setColumns(3);
                // input1.setText(String.valueOf(9));
                // input1.setText(String.valueOf(12));
                input1.setText(String.valueOf(5));

                // 显示输入框2
                JTextField input2 = timePanels.getInput2();
                input2.setVisible(true);
                input2.setColumns(3);
                // input2.setText(String.valueOf(18));
                // input2.setText(String.valueOf(22));
                input2.setText(String.valueOf(10));

                timePanels.getPlusBtn().setVisible(true);
                timePanels.getMinusBtn().setVisible(true);

                timePanels.getTaskCheckBox().setVisible(false);

                JCheckBox stopCheckBox = timePanels.getStopCheckBox();
                stopCheckBox.setVisible(false);

                // 设置按钮文字
                timePanels.getInputOkButton().setText("开始阅读");
                // timePanels.getTimerJLabel().setVisible(false);

                // frame.pack();
                // JFramePack.onJComponentActionEvent(e);
                 JFramePack.pack();
//                AdbTools.getInstance().getFrame().pack();

//            }
//        });


    }
}
