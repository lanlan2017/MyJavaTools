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
    private static final BaiduOCRButton  instance = new BaiduOCRButton();

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

        JTextArea inputTextArea = MainFrom.getInstance().getOcrTextArea();
        // 显示输入文本域
        inputTextArea.setVisible(true);
        // 显示ocr输出Pane
        JScrollPane ocrScrollPane = MainFrom.getInstance().getOcrScrollPane();
        ocrScrollPane.setVisible(true);
        // 显示滚动面板的顶级Pane
        // MainFrom.getInstance().getScrollPaneFather().setVisible(true);
        // 显示分割面板
        MainFrom.getInstance().getJSplitPane().setVisible(true);

        // 把文字识别的结果写入到输入文本域中
        inputTextArea.setText(ocrStr);
        // 调整窗体的大小
        MainFrom.getInstance().getFrame().pack();
        // 将识别结果写到剪贴板中.
        SystemClipboard.setSysClipboardText(ocrStr);
    }

    public static BaiduOCRButton getInstance() {
        return instance;
    }
}
