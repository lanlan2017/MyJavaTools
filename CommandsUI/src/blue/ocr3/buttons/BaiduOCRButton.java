package blue.ocr3.buttons;

import blue.ocr3.baidu.BaiduOcrCallable;
import blue.ocr3.baidu.BaiduOcrRunable;
import tools.copy.SystemClipboard;
import tools.string.PrintStr;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * OCR按钮
 */
public class BaiduOCRButton extends ButtonKeyAction {
    private static BaiduOCRButton instance;

    private BaiduOCRButton(JPanel fatherPanel) {
        this.button = new JButton("OCR");
        this.abstractAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 使用百度文字识别
                // BaiduOcrRunable.startBaiduOCR();
                String ocrStr = BaiduOcrCallable.startBaiduOCR();
                System.out.println("使用Callable");
                // 将识别结果写到剪贴板中.
                SystemClipboard.setSysClipboardText(ocrStr);
                PrintStr.printStr(ocrStr);
            }
        };
        this.modifiers = KeyEvent.ALT_DOWN_MASK;
        this.keyCode = KeyEvent.VK_B;
        this.fatherPanel = fatherPanel;
        setAction();
        // setKeys();
    }

    public static BaiduOCRButton getInstance(JPanel fatherPanel) {
        if (instance == null) {
            instance = new BaiduOCRButton(fatherPanel);
        }
        return instance;
    }

    public static BaiduOCRButton getInstance() {
        return instance;
    }
}
