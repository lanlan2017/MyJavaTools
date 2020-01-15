package ui.toolbar.combox.second;

import formatter.markdown.MdInlineCodesOneLine;
import formatter.markdown.multiline.MdUnorderList;
import formatter.markdown.multiline.codeblock.MdCbJava;
import formatter.markdown.multiline.codeblock.MdCbSql;
import ocr.baidu.BaiduOcrRunable;

import javax.swing.*;
import java.awt.event.ItemEvent;

/**
 * markdown格式化选项
 */
public class MarkdownJComBox {
    private static MarkdownJComBox instance = new MarkdownJComBox();
    private JComboBox<String> comboBox;

    private MarkdownJComBox() {
        String[] markdownItems = {"行内代码", "无序列表", "Java代码块", "SQL代码块"};
        comboBox = new JComboBox<>(markdownItems);
        comboBox.addItemListener(markdownE -> {
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
    }

    public static MarkdownJComBox getInstance() {
        return instance;
    }

    public JComboBox<String> getComboBox() {
        return comboBox;
    }
}
