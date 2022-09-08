package adbs.test.auto.ui;

import adbs.action.listener.abs.shellinput.VolumeMinusBtnAcListener;
import adbs.action.listener.abs.shellinput.VolumeNoneBtnAcListener;
import adbs.action.listener.abs.shellinput.VolumePlusBtnAcListener;
import adbs.cmd.CmdRun;
import javafx.scene.input.InputMethodTextRun;
import tools.config.properties.PropertiesTools;
import tools.swing.button.AbstractButtons;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class StopJPanels {
    // private final JButton dormantOKButton;
    // private final JTextField dormantTextField;
    private JPanel stopJPanel;
    private JButton stopBtn;
    JButton volumePlus;
    JButton volumeMinus;
    JButton volumeNone;

    // JButton rebootBtn;
    // JButton closeBtn;

    private PropertiesTools propertiesTools = new PropertiesTools("AdbTools.properties");

    public StopJPanels() {
        this.stopJPanel = new JPanel();
        // this.stopJPanel.setBorder(new TitledBorder("stop"));
        this.stopJPanel.setBorder(new TitledBorder(""));
        this.stopBtn = new JButton(propertiesTools.getProperty("stop"));
        stopBtn.setToolTipText("停止所有后台线程");

        volumePlus = new JButton("+");
        volumePlus.setToolTipText("音量加一");
        // 音量加一键
        volumePlus.addActionListener(new VolumePlusBtnAcListener());

        volumeMinus = new JButton("-");
        volumeMinus.setToolTipText("音量减一");
        // 音量减一键
        volumeMinus.addActionListener(new VolumeMinusBtnAcListener());

        volumeNone = new JButton("x");
        volumeNone.setToolTipText("静音键");
        // 静音键
        volumeNone.addActionListener(new VolumeNoneBtnAcListener());


        // dormantTextField = new JTextField();
        // dormantOKButton = new JButton("等待休眠");
        // dormantOKButton.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         Timer timer = new Timer(true);
        //         // 注意，javax.swing包中也有一个Timer类，如果import中用到swing包,要注意名字的冲突。
        //
        //         String text = dormantTextField.getText();
        //         TimerTask task = new TimerTask() {
        //             public void run() {
        //                 System.out.println(text + "m到了");
        //                 // 杀死悟空浏览器
        //                 // CmdRun.run("adb shell am force-stop com.cat.readall");
        //                 // 杀死快手极速版
        //                 CmdRun.run("adb shell am force-stop com.kuaishou.nebula");
        //                 // // 杀死抖音极速版
        //                 // CmdRun.run("adb shell am force-stop com.ss.android.ugc.aweme.lite");
        //                 // 息屏，并且休眠电脑
        //                 CmdRun.run("adb shell input keyevent 223 && shutdown /h");
        //                 // CmdRun.run("adb shell input keyevent 223");
        //             }
        //         };
        //
        //         long ms = Long.parseLong(text) * 60 * 1000;
        //         // 等待指定毫秒后执行任务
        //         timer.schedule(task, ms);
        //     }
        // });


        stopJPanel.add(volumePlus);
        stopJPanel.add(volumeMinus);
        stopJPanel.add(volumeNone);
        stopJPanel.add(stopBtn);
        // stopJPanel.add(dormantTextField);
        // stopJPanel.add(dormantOKButton);
        AbstractButtons.setMarginInButtonJPanel(stopJPanel);
    }

    public JPanel getStopJPanel() {
        return stopJPanel;
    }

    public JButton getStopBtn() {
        return stopBtn;
    }
}
