package ui.toolbar.buttons;

import ocr.baidu.BaiduOcrRunable;

import javax.swing.*;

/**
 * @author francis
 * create at 2020/1/13-15:41
 */
public class BaiduOCRButton {

    private static BaiduOCRButton instance=new BaiduOCRButton();
    private JButton baiduOCRButton;
    private BaiduOCRButton()
    {
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
