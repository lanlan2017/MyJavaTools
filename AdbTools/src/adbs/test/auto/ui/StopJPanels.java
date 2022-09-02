package adbs.test.auto.ui;

import adbs.action.listener.abs.shellinput.VolumeMinusBtnAcListener;
import adbs.action.listener.abs.shellinput.VolumeNoneBtnAcListener;
import adbs.action.listener.abs.shellinput.VolumePlusBtnAcListener;
import tools.config.properties.PropertiesTools;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class StopJPanels {
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



        stopJPanel.add(volumePlus);
        stopJPanel.add(volumeMinus);
        stopJPanel.add(volumeNone);
        stopJPanel.add(stopBtn);
    }

    public JPanel getStopJPanel() {
        return stopJPanel;
    }

    public JButton getStopBtn() {
        return stopBtn;
    }
}
