package adbs.main.auto.ui.jpanels.adb;

import adbs.main.auto.ui.jpanels.adb.listener.RebootBtnAcListener;
import adbs.action.runnable.open.Taskkill;
import adbs.main.auto.listener.DeviceListener;
import adbs.main.AdbTools;
import adbs.main.auto.ui.config.FlowLayouts;
import adbs.main.auto.ui.config.Fonts;
import adbs.main.auto.ui.jpanels.adb.listener.*;
import config.AdbToolsProperties;
import tools.config.properties.PropertiesTools;
import tools.swing.button.AbstractButtons;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * adb命令相关面板
 */
public class AdbJPanels {

    private JPanel adbJPanel;
    /**
     *
     */
    private JButton openScrcpyBtn;
    private JButton killScrcpyBtn;
    private JButton returnBtn;
    private JButton homeBtn;
    private JButton taskBtn;
    private JButton rebootBtn;
    private JButton powerOffBtn;

    /**
     * 停止后台线程按钮
     */
    private JButton stopBtn;

    private JButton volumePlus;
    private JButton volumeMinus;
    private JButton volumeNone;


    public AdbJPanels() {
        adbJPanel = new JPanel();
        // adbJPanel.setBorder(new TitledBorder(""));
        adbJPanel.setLayout(FlowLayouts.flowLayoutLeft);

        // PropertiesTools propertiesTools = new PropertiesTools("AdbTools.properties");
        PropertiesTools propertiesTools = AdbToolsProperties.propertiesTools;

        openScrcpyBtn = new JButton(new ImageIcon(AdbTools.class.getClassLoader().getResource("open.png")));
        openScrcpyBtn.setToolTipText("使用scrcpy打开设备");
        openScrcpyBtn.addActionListener(new OpenButtonListener());


        killScrcpyBtn = new JButton("kill");
        killScrcpyBtn.setToolTipText("杀死打开的scrcpy镜像");
        killScrcpyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // String id = DeviceListener.getPhoneId();
                // String id = DeviceListener.getPhoneId();
                String id = DeviceListener.getSelectedPhoneId();
                Taskkill.killScrcpy(id);
            }
        });


        // returnBtn = new JButton(new ImageIcon(Buttons.class.getClassLoader().getResource("向左三角形.png")));

        // ◁ ○ □
        returnBtn = new JButton("◁");
        returnBtn.setToolTipText("返回键");
        // returnBtn.setFont(Fonts.Consolas_BOLD_14);
        // 返回键
        returnBtn.addActionListener(new ReturnBtnAcListener());

        // home键按钮
        // homeBtn = new JButton(new ImageIcon(Buttons.class.getClassLoader().getResource("圆圈.png")));
        // ◁ ○ □
        /*
        ◁○□▇◀●
        xxx
         */
        homeBtn = new JButton("○");
        homeBtn.setFont(Fonts.Consolas_BOLD_14);
        homeBtn.setToolTipText("home键");
        homeBtn.addActionListener(new HomeBtnAcListener());
        // 任务键按钮
        // ◁ ○ □
        // taskBtn = new JButton(new ImageIcon(Buttons.class.getClassLoader().getResource("空框.png")));
        taskBtn = new JButton("□");
        taskBtn.setFont(Fonts.Consolas_BOLD_14);
        taskBtn.setToolTipText("任务键");
        // 任务管理键
        taskBtn.addActionListener(new TaskManageBtnAcListener());

        JPanel navigationKeyJPanel = new JPanel();
        navigationKeyJPanel.setLayout(FlowLayouts.flowLayoutLeft);
        navigationKeyJPanel.add(returnBtn);
        navigationKeyJPanel.add(homeBtn);
        navigationKeyJPanel.add(taskBtn);

        volumeNone = new JButton("x");
        volumeNone.setToolTipText("静音");
        // 静音键
        volumeNone.addActionListener(new VolumeNoneBtnAcListener());

        volumePlus = new JButton("+");
        volumePlus.setToolTipText("音量加一");
        // 音量加一键
        volumePlus.addActionListener(new VolumePlusBtnAcListener());

        volumeMinus = new JButton("-");
        volumeMinus.setToolTipText("音量减一");
        // 音量减一键
        volumeMinus.addActionListener(new VolumeMinusBtnAcListener());

        // 音量面板
        JPanel volumeJPanel = new JPanel();
        volumeJPanel.setLayout(FlowLayouts.flowLayoutLeft);
        volumeJPanel.add(volumePlus);
        volumeJPanel.add(volumeMinus);
        volumeJPanel.add(volumeNone);

        // 重启按钮
        rebootBtn = new JButton("重启");
        // rebootBtn.setToolTipText("重启手机");
        // rebootBtn.addActionListener(new RebootBtnAcListener(frame, "reboot"));
        rebootBtn.addActionListener(new RebootBtnAcListener(adbJPanel, "reboot"));

        // 关机按钮
        powerOffBtn = new JButton("关机");
        // powerOffBtn.setToolTipText("");
        // closeBtn.addActionListener(new RebootBtnAcListener(frame, "shell reboot -p"));
        powerOffBtn.addActionListener(new RebootBtnAcListener(adbJPanel, "shell reboot -p"));

        stopBtn = new JButton(propertiesTools.getProperty("stop"));
        stopBtn.setToolTipText("停止所有后台线程,刷新界面");


        // adb面板添加按钮
        adbJPanel.add(openScrcpyBtn);
        adbJPanel.add(killScrcpyBtn);
        adbJPanel.add(returnBtn);
        adbJPanel.add(homeBtn);
        adbJPanel.add(taskBtn);
        // adbJPanel.add(volumePlus);
        // adbJPanel.add(volumeMinus);
        // adbJPanel.add(volumeNone);
        adbJPanel.add(volumeJPanel);
        adbJPanel.add(rebootBtn);
        adbJPanel.add(powerOffBtn);
        adbJPanel.add(stopBtn);

        // AbstractButtons.setMarginInButtonJPanel(adbJPanel);
        // AbstractButtons.setMarginInButtonJPanel(adbJPanel,-1);
        AbstractButtons.setMarginInButtonJPanel(adbJPanel, 1);

        // AbstractButtons.setJButtonMargin(volumePlus, -1);
        // AbstractButtons.setJButtonMargin(volumeMinus, -1);
        // AbstractButtons.setJButtonMargin(volumeNone, -1);

        AbstractButtons.setMarginInButtonJPanel(volumeJPanel, -1);
        AbstractButtons.setMarginInButtonJPanel(navigationKeyJPanel, -1);
        // volumeNone.setMargin(new Insets(2, -1, -1, -1));
        // volumeNone.setMargin(new Insets(2, -1, 2, -1));
    }

    public JPanel getAdbJPanel() {
        return adbJPanel;
    }

    public JButton getStopBtn() {
        return stopBtn;
    }
}