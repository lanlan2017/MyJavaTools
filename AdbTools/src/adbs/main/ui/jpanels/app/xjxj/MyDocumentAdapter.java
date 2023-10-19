package adbs.main.ui.jpanels.app.xjxj;

import adbs.main.AdbTools;
import adbs.tools.thread.ThreadSleep;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class MyDocumentAdapter implements DocumentListener {

    private JTextArea notOpened;

    public MyDocumentAdapter(JTextArea notOpened) {
        this.notOpened = notOpened;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        // 对插入更新事件的处理
        extracted(e, "insert");
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        // 对移除更新事件的处理
        extracted(e, "remove");
    }


    // @Override
    // public void changedUpdate(DocumentEvent e) {
    //     // 对更改更新事件的处理
    // }

    @Override
    public void changedUpdate(DocumentEvent e) {
        extracted(e, "changedU");
    }

    private void extracted(DocumentEvent e, String str) {
        switch (str) {
            case "insert":
                System.out.println("str = " + str);
                int lineCount = notOpened.getLineCount();
                notOpened.setRows(lineCount);
                // ThreadSleep.seconds(1);
                AdbTools.getInstance().getFrame().pack();
                break;
        }
        //     // Container parent = notOpened.getParent();
        //     // System.out.println("parent = " + parent);
        //     // if (parent instanceof JPanel) {
        //     // System.out.println("tttttttttttttttt");
        //     // boolean visible = parent.isVisible();
        //     // System.out.println("visible = " + visible);
        //     // if (visible) {
        //     System.out.println("str = " + str);
        //     int lineCount = notOpened.getLineCount();
        //     notOpened.setRows(lineCount);
        //     // System.out.println();
        //     // JPanel panel = (JPanel) parent;
        //     // JFramePack.byJPanel(panel);
        //     // AdbTools.getInstance().getFrame().pack();
        //     // }
        //     AdbTools.getInstance().getFrame().pack();
        // }
    }
}