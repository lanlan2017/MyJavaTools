package adbs.main.ui.jpanels.app;

import adbs.main.AdbTools;
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
 * 应用面板
 */
public class AppPanels {
    private JPanel appPanel;
    //    private JTextArea signedIn;
//    private JTextArea notOpened;
    private JPanel btnPanel;
    private JButton zhongdian;
    private JButton quxiao;
    private JTextPane signedIn;
    private JTextPane notOpened;

    //    private Highlighter highlighter;
    private List<Highlighter.Highlight> highlights = new ArrayList<>();

    public AppPanels() {
        this.appPanel = new JPanel();
        appPanel.setLayout(new BorderLayout());
//        this.notOpened = new JTextArea(1, 10);
//        notOpened.setBorder(new TitledBorder(new LineBorder(Color.CYAN), "未打开"));
//        this.signedIn = new JTextArea(1, 10);
//        signedIn.setBorder(new TitledBorder(new LineBorder(Color.pink), "已打开"));

        this.notOpened = new JTextPane();
        notOpened.setBorder(new TitledBorder(new LineBorder(Color.CYAN), "未打开"));
        this.signedIn = new JTextPane();
        signedIn.setBorder(new TitledBorder(new LineBorder(Color.pink), "已打开"));

        btnPanel = new JPanel();
        btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.Y_AXIS));

        zhongdian = new JButton("重点");
        quxiao = new JButton("取消");


        // 添加按钮监听器
        zhongdian.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                String searchString = inputField.getText();

//                String searchString = "";
//                highlightString(signedIn, searchString, Color.pink);

//                String searchString = appName;
                String appName = getAppName() + "\n";
                highlightString(signedIn, appName, Color.pink);
            }
        });
        quxiao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
////                removeSpecificHighlights(CustomHighlighter.this.textPane, CustomHighlighter.this.inputField.getText());
//                String text = "";
//                removeSpecificHighlights(signedIn, text);
//                removeSpecificHighlights(CustomHighlighter.this.textPane, CustomHighlighter.this.inputField.getText());

//                String text = "";
                removeSpecificHighlights(signedIn, getAppName() + "\n");
            }
        });


        btnPanel.add(zhongdian);
        btnPanel.add(quxiao);

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

//    public JTextArea getSignedIn() {
//        return signedIn;
//    }
//
//    public JTextArea getNotOpened() {
//        return notOpened;
//    }

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
