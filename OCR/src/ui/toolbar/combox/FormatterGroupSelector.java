package ui.toolbar.combox;

import formatter.markdown.multiline.codeblock.MdCbJava;
import ocr.baidu.BaiduOcrRunable;
import formatter.markdown.multiline.codeblock.MdCbSql;
import formatter.markdown.MdInlineCodesOneLine;
import formatter.markdown.multiline.MdUnorderList;
import formatter.pdf.PdfBookmarkCmdFormatter;
import ui.toolbar.ToolBar;
import ui.ToolsWindow;

import javax.swing.*;
import java.awt.event.ItemEvent;

/**
 * 格式选择器
 */
public class FormatterGroupSelector {
    private static FormatterGroupSelector instance = new FormatterGroupSelector();
    JComboBox<String> comboBox;

    private FormatterGroupSelector() {
        String[] items =
                {"不格式化", "Markdown", "书签"};
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
                    case "Markdown":
                        System.out.println("markdown模式");
                        selectMarkdown();
                        break;
                    // 触发事件的选项
                    case "书签":
                        System.out.println("书签模式");
                        selectBookMark();
                        break;
                    default:
                        System.out.println("不格式化");
                        selectDefault();
                        break;
                }
            }
        });
    }

    private void selectDefault() {
        ChildJComboBox.getInstance().setComboBox(null);
        redrawToolBar();
        BaiduOcrRunable.setFormatter(null);
    }

    private void selectBookMark() {
        String[] bookMarkItems = {"书签x.x.x", "书签x.xx", "书签x.xx.x", "书签xx.x",
                "书签xx.x.x", "书签xx.xx", "书签xx.xx.x"};
        JComboBox<String> bookMarkJComBox = new JComboBox<>(bookMarkItems);
        bookMarkJComBox.addItemListener(markdownE -> {
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
        ChildJComboBox.getInstance().setComboBox(bookMarkJComBox);
        redrawToolBar();
    }

    private void redrawToolBar() {
        ToolBar.getInstance().getToolBar().removeAll();
        //ToolBar.getInstance().getToolBar().setVisible(false);
        ToolBar.getInstance().addToolBarButtons();
        //ToolBar.getInstance().getToolBar().setVisible(true);
        ToolsWindow.getInstance().pack();
    }

    private void selectMarkdown() {
        String[] markdownItems = {"行内代码", "无序列表", "Java代码块","SQL代码块"};
        JComboBox<String> markdownJComBox = new JComboBox<>(markdownItems);
        markdownJComBox.addItemListener(markdownE -> {
            // 如果是选中的话
            if (ItemEvent.SELECTED == markdownE.getStateChange()) {
                // 获取列表成员
                String markdownItem = markdownE.getItem().toString();
                switch (markdownItem) {
                    // 触发事件的选项
                    case "行内代码":
                        System.out.println("格式化为:Markdown单行");
                        BaiduOcrRunable
                                .setFormatter(new MdInlineCodesOneLine());
                        break;
                    // 触发事件的选项
                    case "无序列表":
                        System.out.println("格式化为:Markdown无序列表");
                        BaiduOcrRunable
                                .setFormatter(new MdUnorderList());
                        break;
                    case "SQL代码块":
                        System.out.println("格式化为:markdown SQL代码块");
                        BaiduOcrRunable.setFormatter(new MdCbSql());
                        break;
                    case "Java代码块":
                        System.out.println("格式化为:markdown Java代码块");
                        BaiduOcrRunable.setFormatter(new MdCbJava());
                        break;
                }
            }
        });
        ChildJComboBox.getInstance().setComboBox(markdownJComBox);
        redrawToolBar();
    }

    public static FormatterGroupSelector getInstance() {
        return instance;
    }

    public JComboBox<String> getComboBox() {
        return comboBox;
    }
}
