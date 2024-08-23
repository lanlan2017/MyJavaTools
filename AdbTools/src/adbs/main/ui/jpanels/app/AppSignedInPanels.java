package adbs.main.ui.jpanels.app;

import adbs.cmd.AdbCommands;
import adbs.main.AdbTools;
import adbs.main.run.ActAutoRun;
import adbs.main.run.AdbGetPackage;
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
    private final JPanel appPanel;

    private final JPanel btnPanel;
    private final JButton zhongdian;
    private final JButton quxiao;
    private final JButton batteryReset;

    private final JTextPane signedIn;
    private final JTextPane notOpened;

    private final List<Highlighter.Highlight> highlights = new ArrayList<>();

    public AppSignedInPanels() {
        this.appPanel = new JPanel();
        this.appPanel.setLayout(new BorderLayout());

        this.notOpened = new JTextPane();
        this.notOpened.setBorder(new TitledBorder(new LineBorder(Color.CYAN), "未打开"));
        this.signedIn = new JTextPane();
        this.signedIn.setBorder(new TitledBorder(new LineBorder(Color.pink), "已打开"));

        this.btnPanel = new JPanel();
        this.btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.Y_AXIS));

        this.zhongdian = new JButton("重点");
        this.quxiao = new JButton("取消");


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
        batteryReset = new JButton("充电");
        batteryReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final AdbTools instance = AdbTools.getInstance();
                instance.showDialogOk("重置电池状态恢复充电?", new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        AdbCommands.batteryReset(instance.getDevice());
                    }
                });
            }
        });


        btnPanel.add(zhongdian);
        btnPanel.add(quxiao);
        btnPanel.add(batteryReset);

        AbstractButtons.setMarginInButtonJPanel(btnPanel);

        appPanel.add(notOpened, BorderLayout.WEST);
        appPanel.add(btnPanel, BorderLayout.CENTER);
        appPanel.add(signedIn, BorderLayout.EAST);
        appPanel.setVisible(false);
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
