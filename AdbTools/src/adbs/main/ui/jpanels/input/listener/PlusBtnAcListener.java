package adbs.main.ui.jpanels.input.listener;

import adbs.main.ui.inout.InOutputModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 增加按钮监听器
 */
public class PlusBtnAcListener implements ActionListener {
    private JTextField input1;
    private JTextField input2;
    private JLabel timerJLabel;

    public PlusBtnAcListener(InOutputModel inOutputModel) {
        input1 = inOutputModel.getTimePanels().getInput1();
        input2 = inOutputModel.getTimePanels().getInput2();
        timerJLabel = inOutputModel.getTimePanels().getTimerJLabel();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (input1.isVisible()) {
            // 两个输入文本框都可见
            if (input2.isVisible()) {
                int value1 = Integer.parseInt(input1.getText());
                int value2;
                if (value1 < 10) {
                    value1 = value1 + 1;
                    value2 = value1 * 2;
                } else if (value1 == 10) {
                    value1 = 12;
                    value2 = 22;
                } else if (value1 == 12) {
                    value1 = 15;
                    value2 = 30;
                } else if (value1 == 15) {
                    value1 = 20;
                    value2 = 40;

                } else if (value1 == 20) {
                    value1 = 30;
                    value2 = 60;
                } else if (value1 == 30) {
                    value1 = 60;
                    value2 = 90;
                } else if (value1 == 60) {
                    value1 = 90;
                    value2 = 150;
                } else if (value1 == 90) {
                    value1 = 120;
                    value2 = 150;
                } else if (value1 == 120) {
                    value1 = 150;
                    value2 = 240;
                } else if (value1 == 150) {
                    value1 = 240;
                    value2 = 300;
                } else {
                    return;
                }
                input1.setText(String.valueOf(value1));
                input2.setText(String.valueOf(value2));
            }
            // 如果第1个文本框可见，第2个文本框不可见
            else {
                int value1 = Integer.parseInt(input1.getText());
                switch (value1) {
                    case 0:
                        value1 = 5;
                        break;
                    case 5:
                        value1 = 8;
                        break;
                    case 8:
                        value1 = 10;
                        break;
                    case 10:
                        value1 = 15;
                        break;
                    case 15:
                        value1 = 20;
                        break;
                    case 20:
                        value1 = 30;
                        break;
                    case 30:
                        value1 = 35;
                        break;
                    case 35:
                        value1 = 65;
                        timerJLabel.setText("1m+5s");
                        break;
                    case 65:
                        value1 = 95;
                        timerJLabel.setText("1.5m+5s");
                        break;
                    case 95:
                        // 2m=2*60s=120s
                        value1 = 120;
                        timerJLabel.setText("2m");
                        break;
                    case 120:
                        // 2.5m=2.5*60s=150s
                        value1 = 150;
                        timerJLabel.setText("2.5m");
                        break;
                    case 150:
                        // 3m=3*60s=180s
                        value1 = 180;
                        timerJLabel.setText("3m");
                        break;
                    case 180:
                        //3.5m=3.5*60=210s
                        value1 = 210;
                        timerJLabel.setText("3.5m");
                        break;
                    case 210:
                        // 4m=4*60s=240s
                        value1 = 240;
                        timerJLabel.setText("4m");
                        break;
                    case 240:
                        // 4.5m=4.5*60s=270s
                        value1 = 270;
                        timerJLabel.setText("4.5m");
                        break;
                    case 270:
                        // 5m=5*60s=300s
                        value1 = 300;
                        timerJLabel.setText("5m");
                        break;
                    case 300:
                        // 10m=10*60s=600s
                        value1 = 600;
                        timerJLabel.setText("6m");
                        break;
                    case 600:
                        // 15m=15*60s=900s
                        value1 = 900;
                        timerJLabel.setText("15m");
                        break;
                    case 900:
                        // 20m=20*60s=1200s
                        value1 = 1200;
                        timerJLabel.setText("20m");
                        break;
                    case 1200:
                        // 30m=30*60s=1800s
                        value1 = 1800;
                        timerJLabel.setText("30m");
                        break;
                    case 1800:
                        // 40m=40*60=2400s
                        value1 = 2400;
                        timerJLabel.setText("40m");
                        break;
                    case 2400:
                        // 50m=50*60=3000s
                        value1 = 3000;
                        timerJLabel.setText("50m");
                        break;
                    case 3000:
                        // 1h=60m=60*60s=3600s
                        value1 = 3600;
                        timerJLabel.setText("1h");
                        break;
                    case 3600:
                        // 2h=120m=120*60s=7200s
                        value1 = 7200;
                        timerJLabel.setText("2h");
                        break;
                    case 7200:
                        // 3h=180m=180*60s=10800s
                        value1 = 10800;
                        timerJLabel.setText("3h");
                        input1.setColumns(5);
                        break;
                    // default:
                    //     value1 = 30;
                    //     break;
                    // case :
                    //     break;
                }
                input1.setText(String.valueOf(value1));
            }
        }

    }
}
