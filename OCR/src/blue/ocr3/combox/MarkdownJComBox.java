package blue.ocr3.combox;

import blue.ocr3.OCR3Form;
import blue.ocr3.baidu.BaiduOcrRunable;
import blue.ocr3.formatter.Formatter;
import blue.ocr3.formatter.markdown.MdBold;
import blue.ocr3.formatter.markdown.MdInlineCodesOneLine;
import blue.ocr3.formatter.markdown.multiline.MdInlineCodesMultiLine;
import blue.ocr3.formatter.markdown.multiline.MdOrderList;
import blue.ocr3.formatter.markdown.multiline.MdUnorderList;
import blue.ocr3.formatter.markdown.multiline.codeblock.MdCbHtml;
import blue.ocr3.formatter.markdown.multiline.codeblock.MdCbJava;
import blue.ocr3.formatter.markdown.multiline.codeblock.MdCbJsp;
import blue.ocr3.formatter.markdown.multiline.codeblock.MdCbSql;

import javax.swing.*;
import java.awt.event.ItemEvent;

/**
 * markdown格式化选项
 */
public class MarkdownJComBox {
    private static MarkdownJComBox instance = new MarkdownJComBox();
    private JComboBox<String> comboBox;
    private static Formatter[] formatters = {new MdInlineCodesOneLine(), new MdInlineCodesMultiLine(), new MdBold(), new MdUnorderList(), new MdOrderList(), new MdCbSql(), new MdCbJava(), new MdCbHtml(), new MdCbJsp()};
    private static Formatter defaultFormatter = formatters[0];

    private MarkdownJComBox() {
        String[] markdownItems = {"单行代码", "多行代码", "加粗", "无序列表", "有序列表", "Java代码块", "SQL代码块", "html代码块", "JSP代码"};
        comboBox = new JComboBox<>(markdownItems);
        // comboBox.setFont(FontTools.f1);
        comboBox.addItemListener(markdownE -> {
            // 如果是选中的话
            if (ItemEvent.SELECTED == markdownE.getStateChange()) {
                // 获取列表成员
                String markdownItem = markdownE.getItem().toString();
                switch (markdownItem) {
                    // 触发事件的选项
                    case "单行代码":
                        System.out.println("格式化为:Markdown单行");
                        defaultFormatter = formatters[0];
                        break;
                    // 触发事件的选项
                    case "多行代码":
                        System.out.println("格式化为:Markdown多行");
                        defaultFormatter = formatters[1];
                        break;
                    // 触发事件的选项
                    case "加粗":
                        System.out.println("格式化为:Markdown加粗");
                        defaultFormatter = formatters[2];
                        break;
                    // 触发事件的选项
                    case "无序列表":
                        System.out.println("格式化为:Markdown无序列表");
                        defaultFormatter = formatters[3];
                        break;
                    // 触发事件的选项
                    case "有序列表":
                        System.out.println("格式化为:Markdown无序列表");
                        defaultFormatter = formatters[4];
                        break;
                    case "SQL代码块":
                        System.out.println("格式化为:markdown SQL代码块");
                        defaultFormatter = formatters[5];
                        break;
                    case "Java代码块":
                        System.out.println("格式化为:markdown Java代码块");
                        defaultFormatter = formatters[6];
                        break;
                    case "html代码块":
                        System.out.println("格式化为:markdown html代码块");
                        defaultFormatter = formatters[7];
                        break;
                    case "JSP代码":
                        System.out.println("格式化为:markdown JSP代码");
                        defaultFormatter = formatters[8];
                        break;
                }
                // 重绘工具条，如果不重绘，那么快捷将会失效。
                OCR3Form.getInstance().redrawToolbar();
                BaiduOcrRunable.setFormatter(defaultFormatter);
            }
        });
    }

    public static MarkdownJComBox getInstance() {
        return instance;
    }

    public JComboBox<String> getComboBox() {
        return comboBox;
    }

    public static Formatter defaultFormatter() {
        return defaultFormatter;
    }
}
