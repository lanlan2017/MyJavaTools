package adbs.main.ui.jpanels.app;

import adbs.main.AdbTools;
import adbs.main.run.ActAutoRun;
import adbs.main.run.AdbGetPackage;
import adbs.main.ui.config.FlowLayouts;
import config.AdbToolsProperties;
import tools.swing.button.AbstractButtons;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 签到面板
 */
public class AppSignedInPanels {
    /**
     * 顶层面板
     */
    private final JPanel appPanel;
    /**
     * 按钮面板
     */
    private final JPanel btnPanel;
    /**
     * 重点按钮
     */
    private final JButton zhongdian;
    /**
     * 取消按钮
     */
    private final JButton quxiao;

    /**
     * 签到文本面板
     */
    private final JTextPane signedIn;
    /**
     * 未签到文本面板
     */
    private final JTextPane notOpened;

    /**
     * 高亮记录列表
     */
    private final List<Highlighter.Highlight> highlights = new ArrayList<>();

    public AppSignedInPanels() {
        this.appPanel = new JPanel();
        this.appPanel.setLayout(new BorderLayout());

        this.notOpened = new JTextPane();
        this.notOpened.setBorder(new TitledBorder(new LineBorder(Color.CYAN), "未打开"));
        this.signedIn = new JTextPane();
        this.signedIn.setBorder(new TitledBorder(new LineBorder(Color.pink), "已打开"));

        this.btnPanel = new JPanel();
//        this.btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.Y_AXIS));
        this.btnPanel.setLayout(new BorderLayout());


        JPanel panel1 = getBtttonFlowLayoutJPanel();

        this.zhongdian = new JButton("重点");
        this.quxiao = new JButton("取消");
        panel1.add(zhongdian);
        panel1.add(quxiao);

        // 添加按钮监听器
        this.zhongdian.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //                String appName = getAppName() + ForegroundAppRun.appNameEndFlag;
                String appName = getAppName() + ActAutoRun.appNameEndFlag;
                highlightString(signedIn, appName, Color.pink);
            }
        });
        quxiao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //                removeSpecificHighlights(signedIn, getAppName() + ForegroundAppRun.appNameEndFlag);
                removeSpecificHighlights(signedIn, getAppName() + ActAutoRun.appNameEndFlag);
            }
        });

        //        batteryReset = getBatteryReset();


        JButton btnSignedIn = getBtnSignedIn();
        JButton btnAllCheckedIn = getBtnAllCheckedIn();


        //        btnPanel.add(zhongdian);
        //        btnPanel.add(quxiao);

        JPanel panel2 = getBtttonFlowLayoutJPanel();
        panel2.add(btnSignedIn);
        panel2.add((btnAllCheckedIn));


        JButton btnNextDay = getBtnNextDay();
        JButton btnUpdateEarningApps = getBtnUpdateEarningApps();


        JPanel panel3 = getBtttonFlowLayoutJPanel();
        panel3.add(btnNextDay);
        panel3.add(btnUpdateEarningApps);


        //        panel3.add(batteryReset);


        AbstractButtons.setMargin_2_InButtonJPanel(panel1, 1);
        AbstractButtons.setMargin_2_InButtonJPanel(panel2, 1);
        AbstractButtons.setMargin_2_InButtonJPanel(panel3, 1);

        JPanel btnNorth = new JPanel();

        btnNorth.setLayout(new BoxLayout(btnNorth,BoxLayout.Y_AXIS));
        btnNorth.add(panel1);
        btnNorth.add(panel2);
        btnNorth.add(panel3);
//        btnPanel.add(btnNorth);

        btnPanel.add(btnNorth,BorderLayout.NORTH);

//        btnPanel.add(panel1);
//        btnPanel.add(panel2);
//        btnPanel.add(panel3);

        //        btnPanel.add(batteryReset);

        AbstractButtons.setMargin_2_InButtonJPanel(btnPanel);

        appPanel.add(notOpened, BorderLayout.WEST);
        appPanel.add(btnPanel, BorderLayout.CENTER);
        appPanel.add(signedIn, BorderLayout.EAST);
        appPanel.setVisible(false);
    }

    //    /**
    //     * 创建充电按钮
    //     *
    //     * @return 充电按钮JButton对象。
    //     */
    //    private JButton getBatteryReset() {
    //        final JButton batteryReset;
    //        batteryReset = new JButton("充电");
    //        batteryReset.addActionListener(new ActionListener() {
    //            @Override
    //            public void actionPerformed(ActionEvent e) {
    //                final AdbTools instance = AdbTools.getInstance();
    //                instance.showDialogOk("重置电池状态恢复充电?", new ActionListener() {
    //                    @Override
    //                    public void actionPerformed(ActionEvent e) {
    //                        AdbCommands.batteryReset(instance.getDevice());
    //                    }
    //                });
    //            }
    //        });
    //        return batteryReset;
    //    }

    private JButton getBtnUpdateEarningApps() {
        final JButton btnUpdateEarningApps;
        //        btnUpdateEarningApps = new JButton("U");
        btnUpdateEarningApps = new JButton("更新");
        btnUpdateEarningApps.setToolTipText("更新赚钱应用列表");
        btnUpdateEarningApps.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // ForegroundAppRun.updatePackages_3_money();
                // ForegroundAppRun.onNextDay();
                AdbTools.getInstance().showDialogOk("更新赚钱应用", "更新赚钱应用列表", new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        // ForegroundAppRun.updatePackages_3_money();
                        ActAutoRun.updatePackages_3_money();
                    }
                });
            }
        });
        return btnUpdateEarningApps;
    }


    private JButton getBtnNextDay() {
        // JButton btnNextDay=new JButton("重新签到");
        // JButton btnNextDay = new JButton("清空签到记录");
        JButton btnNextDay = new JButton("重签");
        btnNextDay.setToolTipText("重置签到状态");
        btnNextDay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdbTools.getInstance().showDialogOk("重签", "重置签到状态?", new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ActAutoRun.onNextDay();
                        ActAutoRun.stopWait();
                    }
                });
            }
        });
        return btnNextDay;
    }


    private JPanel getBtttonFlowLayoutJPanel() {
        JPanel panel1 = new JPanel();
        panel1.setLayout(FlowLayouts.flowLayoutLeft);

        return panel1;
    }


    private JButton getBtnSignedIn() {
        final JButton btnSignedIn;
        //        btnSignedIn = new JButton("√");
        btnSignedIn = new JButton("已签");
        btnSignedIn.setToolTipText("当前APP已签到");
        btnSignedIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ActAutoRun.stopWait();
            }
        });
        return btnSignedIn;
    }

    private JButton getBtnAllCheckedIn() {
        final JButton btnAllCheckedIn;
        //        btnAllCheckedIn = new JButton("√√");
        btnAllCheckedIn = new JButton("签完");
        btnAllCheckedIn.setToolTipText("所有的APP都签到过了");
        btnAllCheckedIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdbTools.getInstance().showDialogOk("都签了", "全部应用都签到完毕了?", new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // ForegroundAppRun.stopWait();
                        // ForegroundAppRun.allAppOpened();
                        ActAutoRun.stopWait();
                        ActAutoRun.allAppOpened();

                    }
                });
            }
        });
        return btnAllCheckedIn;
    }


    private String getAppName() {
        String topPackageName = AdbGetPackage.getTopPackageName(AdbTools.getInstance().getDevice().getSerial());
        System.out.println("topPackageName = " + topPackageName);
        String appName = AdbToolsProperties.moneyApkPro.getProperty(topPackageName);
        System.out.println("appName = " + appName);
        return appName;
    }

    public JPanel getAppPanel() {
        return appPanel;
    }

    public JTextPane getSignedIn() {
        return signedIn;
    }

    public JTextPane getNotOpened() {
        return notOpened;
    }


    public void highlightString(JTextPane textPane, String searchString, Color color) {
        try {
            Document doc = textPane.getDocument();
            String text = doc.getText(0, doc.getLength());
            // 初始化Highlighter
            Highlighter highlighter = textPane.getHighlighter();
            int index = text.indexOf(searchString);
            while (index != -1) {
                int end = index + searchString.length();
                Highlighter.Highlight highlight = (Highlighter.Highlight) highlighter.addHighlight(index, end, new DefaultHighlighter.DefaultHighlightPainter(color));
                //                Highlighter.Highlight highlight = (Highlighter.Highlight) highlighter.addHighlight(index, end, new DefaultHighlighter.DefaultHighlightPainter(color));
                highlights.add(highlight);
                index = text.indexOf(searchString, end);
            }
        } catch (BadLocationException ex) {
            ex.printStackTrace();
        }
    }

    public void removeSpecificHighlights(JTextPane textPane, String searchString) {
        try {
            Document doc = textPane.getDocument();
            String text = doc.getText(0, doc.getLength());
            // 初始化Highlighter
            Highlighter highlighter = textPane.getHighlighter();
            int index = text.indexOf(searchString);
            while (index != -1) {
                int end = index + searchString.length();
                Iterator<Highlighter.Highlight> iterator = highlights.iterator();
                while (iterator.hasNext()) {
                    Highlighter.Highlight h = iterator.next();
                    if (h.getStartOffset() == index && h.getEndOffset() == end) {
                        highlighter.removeHighlight(h);
                        iterator.remove();
                    }
                }
                index = text.indexOf(searchString, end);
            }
        } catch (BadLocationException ex) {
            ex.printStackTrace();
        }
    }
}
