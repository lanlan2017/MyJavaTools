package ui.toolbar.combox.second;

import formatter.pdf.PdfBookmarkCmdFormatter;
import ocr.baidu.BaiduOcrRunable;

import javax.swing.*;
import java.awt.event.ItemEvent;


/**
 * 书签格式化选项
 */
public class BookMarkJComBox {
    private static BookMarkJComBox instance = new BookMarkJComBox();
    private JComboBox<String> comboBox;

    private BookMarkJComBox() {
        String[] bookMarkItems = {"书签x.x.x", "书签x.xx", "书签x.xx.x", "书签xx.x",
                "书签xx.x.x", "书签xx.xx", "书签xx.xx.x"};
        comboBox = new JComboBox<>(bookMarkItems);
        comboBox.addItemListener(markdownE -> {
            // 如果是选中的话
            if (ItemEvent.SELECTED == markdownE.getStateChange()) {
                // 获取列表成员
                String bookMarkItem = markdownE.getItem().toString();
                switch (bookMarkItem) {
                    // 如果选中的是书签
                    case "书签x.x.x":
                        System.out.println("格式化为:书签x.x.x");
                        BaiduOcrRunable.setFormatter(
                                new PdfBookmarkCmdFormatter("111"));
                        break;
                    case "书签x.xx":
                        System.out.println("格式化为:书签x.xx");
                        BaiduOcrRunable.setFormatter(
                                new PdfBookmarkCmdFormatter("12"));
                        break;
                    case "书签x.xx.x":
                        System.out.println("格式化为:书签x.xx.x");
                        BaiduOcrRunable.setFormatter(
                                new PdfBookmarkCmdFormatter("121"));
                        break;
                    case "书签xx.x":
                        System.out.println("格式化为:书签xx.x");
                        BaiduOcrRunable.setFormatter(
                                new PdfBookmarkCmdFormatter("21"));
                        break;
                    case "书签xx.x.x":
                        System.out.println("格式化为:书签xx.x.x");
                        BaiduOcrRunable.setFormatter(
                                new PdfBookmarkCmdFormatter("211"));
                        break;
                    case "书签xx.xx":
                        System.out.println("格式化为:书签xx.xx");
                        BaiduOcrRunable.setFormatter(
                                new PdfBookmarkCmdFormatter("22"));
                        break;
                    case "书签xx.xx.x":
                        System.out.println("格式化为:书签xx.xx.x");
                        BaiduOcrRunable.setFormatter(
                                new PdfBookmarkCmdFormatter("221"));
                        break;
                }
            }
        });
    }

    public static BookMarkJComBox getInstance() {
        return instance;
    }

    public JComboBox<String> getComboBox() {
        return comboBox;
    }
}
