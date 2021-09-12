package blue.ocr2.event.buttons;

import blue.ocr2.baidu.BaiduOcrRunable;
import blue.ocr2.kuiajiejian.ShortcutKeyRegister;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class OcrButtonSetting {
    private JButton ocrButton;
    ShortcutKeyRegister keyRegister;

    public OcrButtonSetting(JButton ocrButton, ShortcutKeyRegister keyRegister) {
        this.ocrButton = ocrButton;
        this.keyRegister = keyRegister;
        ocrAction();
    }

    private void ocrAction() {
        AbstractAction ocrAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 使用百度文字识别
                BaiduOcrRunable.startBaiduOCR();
            }
        };
        // OcrButton.addActionListener(ocrAction);
        keyRegister.keysToButton(ocrButton, ocrAction, KeyEvent.ALT_DOWN_MASK, KeyEvent.VK_B);
    }
}
