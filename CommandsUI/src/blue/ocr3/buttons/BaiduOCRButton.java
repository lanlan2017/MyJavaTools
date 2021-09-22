package blue.ocr3.buttons;

import blue.commands.ui.MainFrom;
import blue.ocr3.baidu.BaiduOcrCallable;
import tools.copy.SystemClipboard;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * OCR按钮
 */
public class BaiduOCRButton extends ButtonKeyAction {
    private static BaiduOCRButton instance;

    private BaiduOCRButton() {
        this.button = new JButton("OCR");
        this.abstractAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                baiduOCRButtonAction();
            }
        };
        setAction();
    }

    public void baiduOCRButtonAction() {
        // 使用百度文字识别
        String ocrStr = BaiduOcrCallable.startBaiduOCR();
        // System.out.println("使用Callable");
        // PrintStr.printStr(ocrStr);

        JTextArea inputTextArea = MainFrom.getInstance().getInputTextArea();
        // 显示输入文本域
        inputTextArea.setVisible(true);
        // 把文字识别的结果写入到输入文本域中
        inputTextArea.setText(ocrStr);
        // 调整窗体的大小
        MainFrom.getInstance().getFrame().pack();
        // 将识别结果写到剪贴板中.
        SystemClipboard.setSysClipboardText(ocrStr);
    }

    public static BaiduOCRButton getInstance() {
        if (instance == null) {
            instance = new BaiduOCRButton();
        }
        return instance;
    }
}
