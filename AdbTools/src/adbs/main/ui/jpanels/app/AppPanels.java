package adbs.main.ui.jpanels.app;

import adbs.main.ui.jpanels.app.xjxj.MyDocumentAdapter;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

/**
 * 应用面板
 */
public class AppPanels {
    private JPanel appPanel;
    private JTextArea signedIn;
    private JTextArea notOpened;

    public AppPanels() {
        this.appPanel = new JPanel();
        appPanel.setLayout(new BorderLayout());

        // this.allApp = new JTextArea();
        this.notOpened = new JTextArea(1, 10);
        // notOpened.setBorder(new LineBorder(Color.CYAN));
        notOpened.setBorder(new TitledBorder(new LineBorder(Color.CYAN), "未打开"));
        notOpened.getDocument().addDocumentListener(new MyDocumentAdapter(notOpened));


        // this.signedInApp = new JTextArea();
        this.signedIn = new JTextArea(1, 10);
        // signedIn.setBorder(new LineBorder(Color.PINK));
        signedIn.setBorder(new TitledBorder(new LineBorder(Color.pink), "已打开"));
        signedIn.getDocument().addDocumentListener(new MyDocumentAdapter(signedIn));


        appPanel.add(notOpened, BorderLayout.WEST);
        appPanel.add(signedIn, BorderLayout.EAST);
        appPanel.setVisible(false);
    }

    public JPanel getAppPanel() {
        return appPanel;
    }

    public JTextArea getSignedIn() {
        return signedIn;
    }

    public JTextArea getNotOpened() {
        return notOpened;
    }
}
