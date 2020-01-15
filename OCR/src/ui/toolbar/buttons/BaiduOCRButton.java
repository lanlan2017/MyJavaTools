package ui.toolbar.buttons;

import ocr.baidu.BaiduOcrRunable;

import javax.swing.*;

/**
 * OCR按钮
 */
public class BaiduOCRButton {

    private static BaiduOCRButton instance = new BaiduOCRButton();
    private JButton baiduOCRButton;

    private BaiduOCRButton() {
        baiduOCRButton = new JButton("OCR");
        baiduOCRButton.addActionListener(e -> {
            // 使用百度文字识别
            BaiduOcrRunable.startBaiduOCR();
        });
    }

    public static BaiduOCRButton getInstance() {
        return instance;
    }

    public JButton getBaiduOCRButton() {
        return baiduOCRButton;
    }
}
