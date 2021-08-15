package ui.toolbar.combox.second;

import formatter.pdf.PdfBookmarkCmdFormatter;
import ocr.baidu.BaiduOcrRunable;
import ui.setting.FontTools;

import javax.swing.*;
import java.awt.event.ItemEvent;


/**
 * 书签格式化选项
 */
public class BookMarkJComBox {
    private static BookMarkJComBox instance = new BookMarkJComBox();
    private static PdfBookmarkCmdFormatter[] formatters = {
            new PdfBookmarkCmdFormatter("111"),
            new PdfBookmarkCmdFormatter("12"),
            new PdfBookmarkCmdFormatter("121"),
            new PdfBookmarkCmdFormatter("21"),
            new PdfBookmarkCmdFormatter("211"),
            new PdfBookmarkCmdFormatter("22"),
            new PdfBookmarkCmdFormatter("221")
    };
    /**
     * 默认的格式化器,刚开始以第一个为准.
     */
    private static PdfBookmarkCmdFormatter defaultFormatter = formatters[0];
    private JComboBox<String> comboBox;

    private BookMarkJComBox() {
        String[] bookMarkItems = {"书签x.x.x", "书签x.xx", "书签x.xx.x", "书签xx.x",
                "书签xx.x.x", "书签xx.xx", "书签xx.xx.x"};
        comboBox = new JComboBox<>(bookMarkItems);
        comboBox.setFont(FontTools.f1);
        comboBox.addItemListener(markdownE -> {
            // 如果是选中的话
            if (ItemEvent.SELECTED == markdownE.getStateChange()) {
                // 获取列表成员
                String bookMarkItem = markdownE.getItem().toString();
                switch (bookMarkItem) {
                    // 如果选中的是书签
                    case "书签x.x.x":
                        System.out.println("格式化为:书签x.x.x");
                        defaultFormatter = formatters[0];
                        break;
                    case "书签x.xx":
                        System.out.println("格式化为:书签x.xx");
                        defaultFormatter = formatters[1];
                        break;
                    case "书签x.xx.x":
                        System.out.println("格式化为:书签x.xx.x");
                        defaultFormatter = formatters[2];
                        break;
                    case "书签xx.x":
                        System.out.println("格式化为:书签xx.x");
                        defaultFormatter = formatters[3];
                        break;
                    case "书签xx.x.x":
                        System.out.println("格式化为:书签xx.x.x");
                        defaultFormatter = formatters[4];
                        break;
                    case "书签xx.xx":
                        System.out.println("格式化为:书签xx.xx");
                        defaultFormatter = formatters[5];
                        break;
                    case "书签xx.xx.x":
                        System.out.println("格式化为:书签xx.xx.x");
                        defaultFormatter = formatters[6];
                        break;
                }
                // 设置选择的格式化器
                BaiduOcrRunable.setFormatter(defaultFormatter);
            }
        });
    }

    public static BookMarkJComBox getInstance() {
        return instance;
    }

    public JComboBox<String> getComboBox() {
        return comboBox;
    }


    public static PdfBookmarkCmdFormatter defaultFormatter() {
        return defaultFormatter;
    }
}
