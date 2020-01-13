package ui.toolbar.buttons;

import ocr.baidu.BaiduOcrRunable;
import ocr.baidu.formatter.markdown.MdCbSql;
import ocr.baidu.formatter.markdown.MdSingleLine;
import ocr.baidu.formatter.pdf.PdfBookmarkCmdFormatter;
import ocr.baidu.formatter.markdown.MdUnorderList;

import javax.swing.*;
import java.awt.event.ItemEvent;

/**
 * 格式化器列表添加器.
 */
public class FormatterJComboBoxAdder {

    private static FormatterJComboBoxAdder instance=new FormatterJComboBoxAdder();
    JComboBox<String> comboBox;
    private FormatterJComboBoxAdder(){
        String[] items =
                {"不格式化", "MD单行", "MD 无序列表", "SQL代码", "书签x.x.x", "书签x.xx", "书签x.xx.x", "书签xx.x",
                        "书签xx.x.x", "书签xx.xx", "书签xx.xx.x",};
        // 创建下拉选择框
        comboBox = new JComboBox<>(items);
        // 注册下拉列表框事件监听器
        comboBox.addItemListener(e -> {
            // 如果是选中的话
            if (ItemEvent.SELECTED == e.getStateChange()) {
                // 获取列表成员
                String item = e.getItem().toString();
                switch (item) {
                    // 触发事件的选项
                    case "MD单行":
                        System.out.println("格式化为:MD单行");
                        BaiduOcrRunable
                                .setFormatter(new MdSingleLine());
                        break;
                    // 触发事件的选项
                    case "MD 无序列表":
                        System.out.println("格式化为:Markdown无序列表");
                        BaiduOcrRunable
                                .setFormatter(new MdUnorderList());
                        break;
                    // 触发事件的选项
                    case "SQL代码":
                        System.out.println("格式化为:SQL Markwodn代码块");
                        BaiduOcrRunable.setFormatter(
                                new MdCbSql());
                        break;
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
                    default:
                        System.out.println("不格式化");
                        BaiduOcrRunable.setFormatter(null);
                        break;
                }
            }
        });
    }

    public static FormatterJComboBoxAdder getInstance() {
        return instance;
    }

    public JComboBox<String> getComboBox() {
        return comboBox;
    }
}
