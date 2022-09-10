package adbs.test.auto.ui;

import adbs.action.listener.abs.shellinput.VolumeMinusBtnAcListener;
import adbs.action.listener.abs.shellinput.VolumeNoneBtnAcListener;
import adbs.action.listener.abs.shellinput.VolumePlusBtnAcListener;
import adbs.test.auto.ui.config.FlowLayouts;
import tools.config.properties.PropertiesTools;
import tools.swing.button.AbstractButtons;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class StopJPanels {
    private JPanel stopJPanel;
    private JButton volumePlus;
    private JButton volumeMinus;
    private JButton volumeNone;
    private JButton stopBtn;

    private PropertiesTools propertiesTools = new PropertiesTools("AdbTools.properties");

    public StopJPanels() {
        this.stopJPanel = new JPanel();
        // this.stopJPanel.setBorder(new TitledBorder("stop"));
        this.stopJPanel.setBorder(new TitledBorder(""));
        this.stopJPanel.setLayout(FlowLayouts.flowLayoutLeft);

        this.stopBtn = new JButton(propertiesTools.getProperty("stop"));
        stopBtn.setToolTipText("停止所有后台线程,刷新界面");

        volumePlus = new JButton("+");
        volumePlus.setToolTipText("音量加一");
        // 音量加一键
        volumePlus.addActionListener(new VolumePlusBtnAcListener());

        volumeMinus = new JButton("-");
        volumeMinus.setToolTipText("音量减一");
        // 音量减一键
        volumeMinus.addActionListener(new VolumeMinusBtnAcListener());

        volumeNone = new JButton("x");
        volumeNone.setToolTipText("静音");
        // 静音键
        volumeNone.addActionListener(new VolumeNoneBtnAcListener());

        stopJPanel.add(volumePlus);
        stopJPanel.add(volumeMinus);
        stopJPanel.add(volumeNone);
        stopJPanel.add(stopBtn);
        AbstractButtons.setMarginInButtonJPanel(stopJPanel);
    }

    public JPanel getStopJPanel() {
        return stopJPanel;
    }

    public JButton getStopBtn() {
        return stopBtn;
    }
}
