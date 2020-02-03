package ui.toolbar.combox.second;

import formatter.Formatter;
import formatter.markdown.MdInlineCodesOneLine;
import formatter.markdown.multiline.MdInlineCodesMultiLine;
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
    private boolean isSelected;
    private static Formatter[] formatters = {
            new MdInlineCodesOneLine(),
            new MdInlineCodesMultiLine(),
            new MdUnorderList(),
            new MdCbSql(),
            new MdCbJava()
    };

    private MarkdownJComBox() {
        String[] markdownItems = {"单行代码", "多行代码", "无序列表", "Java代码块", "SQL代码块"};
        comboBox = new JComboBox<>(markdownItems);
        comboBox.addItemListener(markdownE -> {
            // 如果是选中的话
            if (ItemEvent.SELECTED == markdownE.getStateChange()) {
                isSelected = true;
                // 获取列表成员
                String markdownItem = markdownE.getItem().toString();
                switch (markdownItem) {
                    // 触发事件的选项
                    case "单行代码":
                        System.out.println("格式化为:Markdown单行");
                        //BaiduOcrRunable
                        //        .setFormatter(new MdInlineCodesOneLine());
                        BaiduOcrRunable
                                .setFormatter(formatters[0]);
                        break;
                    // 触发事件的选项
                    case "多行代码":
                        System.out.println("格式化为:Markdown单行");
                        //BaiduOcrRunable
                        //        .setFormatter(new MdInlineCodesMultiLine());
                        BaiduOcrRunable
                                .setFormatter(formatters[1]);
                        break;
                    // 触发事件的选项
                    case "无序列表":
                        System.out.println("格式化为:Markdown无序列表");
                        BaiduOcrRunable
                                .setFormatter(formatters[2]);
                        break;
                    case "SQL代码块":
                        System.out.println("格式化为:markdown SQL代码块");
                        BaiduOcrRunable.setFormatter(formatters[3]);
                        break;
                    case "Java代码块":
                        System.out.println("格式化为:markdown Java代码块");
                        BaiduOcrRunable.setFormatter(formatters[4]);
                        break;
                }
            }
        });
        // 设置默认选项
        //comboBox.setSelectedIndex(0);
    }

    public static MarkdownJComBox getInstance() {
        return instance;
    }

    public JComboBox<String> getComboBox() {
        return comboBox;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public static Formatter defalutFormatter() {
        return formatters[0];
    }
}
