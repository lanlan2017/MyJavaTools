package blue.ocr3;

import blue.ocr3.baidu.BaiduOcrRunable;
import blue.ocr3.buttons.*;
import blue.ocr3.combox.BookMarkJComBox;
import blue.ocr3.combox.MarkdownJComBox;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class OCR3Form {
    static {
        // 设置外观
        FlatLightLaf.setup();
    }

    private static OCR3Form instance = new OCR3Form();
    private JFrame frame;
    private JPanel rootPanel;
    private JLabel moveLabel;
    private JButton sstButton;
    private JPanel toolPanel;
    private JToolBar toolBar;
    private JButton cacelSstButton;
    private JButton ocrButton;
    private JComboBox firstComboBox;
    private JButton exitButton;
    /**
     * 二级选择框
     */
    private JComboBox<String> subCombox;


    public OCR3Form() {
        firstComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                // 如果是选中的话
                if (ItemEvent.SELECTED == e.getStateChange()) {
                    // 获取列表成员
                    String item = e.getItem().toString();
                    switch (item) {
                        // 触发事件的选项
                        case "markdown":
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
            }
        });
    }

    private void selectMarkdown() {
        System.out.println("使用默认的 markdown 格式化器");
        this.subCombox = MarkdownJComBox.getInstance().getComboBox();
        // MarkdownJComBox.getInstance().frame = frame;
        // 移除toolBar中的所有组件
        redrawToolbar();
        // 设置默认的格式器
        BaiduOcrRunable.setFormatter(MarkdownJComBox.defaultFormatter());
    }

    /**
     * 重绘工具条，如果不重绘工具条的话，
     * 工具条更新后，无法响应键盘事件。
     */
    public void redrawToolbar() {
        toolBar.removeAll();
        toolBar.add(SstButton.getInstance(rootPanel).getButton());
        toolBar.add(CancelButton.getInstance(rootPanel).getButton());
        toolBar.add(BaiduOCRButton.getInstance(rootPanel).getButton());
        toolBar.add(firstComboBox);
        //
        if (subCombox != null) {
            toolBar.add(subCombox);
        }
        toolBar.add(ExitButton.getInstance(rootPanel).getButton());
        frame.pack();
    }

    private void selectBookMark() {
        System.out.println("使用默认的 书签 格式化器");
        this.subCombox = BookMarkJComBox.getInstance().getComboBox();
        // BookMarkJComBox.getInstance().frame = frame;
        redrawToolbar();
        // 设置默认的格式化器
        BaiduOcrRunable.setFormatter(BookMarkJComBox.defaultFormatter());
    }

    private void selectDefault() {
        System.out.println("不使用格式化器");
        this.subCombox = null;
        redrawToolbar();
        // 设置默认的格式化器
        BaiduOcrRunable.setFormatter(null);
    }

    public static OCR3Form getInstance() {
        return instance;
    }

    public JFrame getFrame() {
        return frame;
    }

    public static void main(String[] args) {

        OCR3Form ocr3Form = OCR3Form.getInstance();
        JFrame frame = new JFrame("OCR3Form");
        ocr3Form.frame = frame;
        frame.setContentPane(ocr3Form.rootPanel);

        // 不显示标题栏，最小化，关闭按钮
        frame.setUndecorated(true);
        // 永远置顶
        frame.setAlwaysOnTop(true);
        // 最佳大小显示
        frame.pack();
        // 显示窗体
        frame.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        rootPanel = new JPanel();

        sstButton = SstButton.getInstance(rootPanel).getButton();
        cacelSstButton = CancelButton.getInstance(rootPanel).getButton();
        ocrButton = BaiduOCRButton.getInstance(rootPanel).getButton();

        exitButton = ExitButton.getInstance(rootPanel).getButton();
        moveLabel = MovelLabel.getInstance().getMoveLable();
    }
}
