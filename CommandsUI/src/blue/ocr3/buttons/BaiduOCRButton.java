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
    private static final BaiduOCRButton instance = new BaiduOCRButton();

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
        // 执行百度文字识别
        String ocrStr = BaiduOcrCallable.startBaiduOCR();
        // 获取OCR文本域
        JTextArea ocrTextArea = MainFrom.getInstance().getOcrTextArea();
        // 把文字识别的结果写入到输入文本域中
        ocrTextArea.setText(ocrStr);
        // 将识别结果写到剪贴板中.
        SystemClipboard.setSysClipboardText(ocrStr);
        // 显示OCR文本域的所在的容器
        JScrollPane ocrScrollPane = MainFrom.getInstance().getOcrScrollPane();
        ocrScrollPane.setVisible(true);
        // 刷新页面
        MainFrom.getInstance().getFrame().pack();


        // // 获取OCR输出文本域所在的分割面板
        // JSplitPane jSplitPane = MainFrom.getInstance().getJSplitPane();
        // // 如果分隔面板不可见的话，就显示分隔面板，并且调整窗体大小
        // if (!jSplitPane.isVisible()) {
        //     System.out.println("分隔面板不可见的情况");
        //     // 显示OCR输出文本域
        //     ocrTextArea.setVisible(true);
        //     // 显示ocr输出文本域的滚动面板
        //     MainFrom.getInstance().getOcrScrollPane().setVisible(true);
        //     // 显示OCR输出文本域的滚动面板的分隔面板
        //     jSplitPane.setVisible(true);
        //     // 最佳显示
        //     MainFrom.getInstance().getFrame().pack();
        //
        //     // 计算分隔条的最底部的坐标
        //     int endLocation = jSplitPane.getMaximumDividerLocation() + jSplitPane.getMinimumDividerLocation();
        //     jSplitPane.setDividerLocation(endLocation);
        //
        //     // // 设置分隔条在最大位置
        //     // int maxLocation = jSplitPane.getMaximumDividerLocation();
        //     // // System.out.println("maxLocation = " + maxLocation);
        //     // jSplitPane.setDividerLocation(maxLocation);
        //
        //     // 调整窗体大小
        //     MainFrom.getInstance().getFrame().pack();
        // }
        // // 如果分隔条左侧的面板被隐藏的话，则显示左侧的面板
        // else if (jSplitPane.getDividerLocation() == 0) {
        //     System.out.println("左侧面板被折叠的情况");
        //     // 获取程序上次分隔条的位置
        //     int last = jSplitPane.getLastDividerLocation();
        //     // 如果位置大于分隔条的最小位置
        //     if (last > jSplitPane.getMinimumDividerLocation()) {
        //         jSplitPane.setDividerLocation(last);
        //     }
        //     // 如果小于分割条的最小位置（被折叠）
        //     else {
        //         // 计算出中间的坐标
        //         int endLocation = jSplitPane.getMaximumDividerLocation() + jSplitPane.getMinimumDividerLocation();
        //         int middle = endLocation / 2;
        //         jSplitPane.setDividerLocation(middle);
        //     }
        // }

    }

    public static BaiduOCRButton getInstance() {
        return instance;
    }
}
