package ui.toolbar.combox.first;

import formatter.markdown.MdInlineCodesOneLine;
import formatter.pdf.PdfBookmarkCmdFormatter;
import ocr.baidu.BaiduOcrRunable;
import ui.toolbar.ToolBar;
import ui.toolbar.combox.second.BookMarkJComBox;
import ui.toolbar.combox.second.MarkdownJComBox;

import javax.swing.*;
import java.awt.event.ItemEvent;

/**
 * 选项一
 */
public class FirstComBox {
    private static FirstComBox instance = new FirstComBox();
    JComboBox<String> comboBox;

    private FirstComBox() {
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
        ToolBar.getInstance().setSubCombox(null);
        ToolBar.getInstance().repaintToolBar();
        // 设置默认的格式化器
        BaiduOcrRunable.setFormatter(null);
    }

    private void selectMarkdown() {
        ToolBar.getInstance().setSubCombox(MarkdownJComBox.getInstance().getComboBox());
        ToolBar.getInstance().repaintToolBar();
        // 设置默认的格式化器
        //BaiduOcrRunable.setFormatter(new MdInlineCodesOneLine());
        BaiduOcrRunable.setFormatter(MarkdownJComBox.defalutFormatter());
        if (!MarkdownJComBox.getInstance().isSelected()) {
            System.out.println("默认格式化为:单行代码");
            BaiduOcrRunable.setFormatter(MarkdownJComBox.defalutFormatter());
        }
    }

    private void selectBookMark() {
        // 设置第二个工具条
        ToolBar.getInstance().setSubCombox(BookMarkJComBox.getInstance().getComboBox());
        // 重绘工具条
        ToolBar.getInstance().repaintToolBar();
        // 设置默认的格式化器
        //comboBox.setSelectedIndex(0);
        if (!BookMarkJComBox.getInstance().isSelected()) {
            System.out.println("默认格式化为:书签x.x.x");
            BaiduOcrRunable.setFormatter(BookMarkJComBox.defaultFormatter());
        }
    }


    public static FirstComBox getInstance() {
        return instance;
    }

    public JComboBox<String> getComboBox() {
        return comboBox;
    }
}
