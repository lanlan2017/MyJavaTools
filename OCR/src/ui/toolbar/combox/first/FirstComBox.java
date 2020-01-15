package ui.toolbar.combox.first;

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
        BaiduOcrRunable.setFormatter(null);
    }

    private void selectBookMark() {
        ToolBar.getInstance().setSubCombox(BookMarkJComBox.getInstance().getComboBox());
        ToolBar.getInstance().repaintToolBar();
    }

    private void selectMarkdown() {
        ToolBar.getInstance().setSubCombox(MarkdownJComBox.getInstance().getComboBox());
        ToolBar.getInstance().repaintToolBar();
    }

    public static FirstComBox getInstance() {
        return instance;
    }

    public JComboBox<String> getComboBox() {
        return comboBox;
    }
}
