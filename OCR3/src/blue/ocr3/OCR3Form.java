package blue.ocr3;

import blue.ocr3.buttons.*;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;

public class OCR3Form {
    static {
        // 设置外观
        FlatLightLaf.setup();
    }

    private static OCR3Form instance = new OCR3Form();
    private JFrame frame;

    public static OCR3Form getInstance() {
        return instance;
    }

    public JFrame getFrame() {
        return frame;
    }

    private JPanel rootPanel;
    private JLabel moveLabel;
    private JButton sstButton;
    private JPanel toolPanel;
    private JToolBar toolBar;
    private JButton cacelSstButton;
    private JButton ocrButton;
    private JComboBox firstComboBox;
    private JButton exitButton;

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
        toolPanel = new JPanel();
        sstButton = SstButton.getInstance(toolPanel).getButton();
        cacelSstButton = CancelButton.getInstance(toolPanel).getButton();
        ocrButton=BaiduOCRButton.getInstance(toolPanel).getButton();
        exitButton = ExitButton.getInstance(toolPanel).getButton();
        moveLabel = MovelLabel.getInstance().getMoveLable();
    }
}
