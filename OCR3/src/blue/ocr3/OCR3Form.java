package blue.ocr3;

import blue.ocr3.buttons.ExitButton;
import blue.ocr3.buttons.MovelLabel;
import blue.ocr3.buttons.SreenShotButtons;
import blue.ocr3.kuiajiejian.ShortcutKeyRegister;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.event.KeyEvent;

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
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.pack();
        // frame.setVisible(true);


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
        toolPanel = new JPanel();
        // TODO: place custom component creation code here
        exitButton = ExitButton.getInstance().getExitButton();
        sstButton = SreenShotButtons.getInstance().getStartButton();
        cacelSstButton = SreenShotButtons.getInstance().getCancelButton();
        moveLabel = MovelLabel.getInstance().getMoveLable();
        ExitButton.getInstance().setKeys(toolPanel);
        // ShortcutKeyRegister keyRegister = new ShortcutKeyRegister(toolPanel);
        // keyRegister.keysToButton(exitButton, ExitButton.getInstance().getExitButtonAction(), KeyEvent.ALT_DOWN_MASK, KeyEvent.VK_Q);
        // keyRegister.keysToButton(ExitButton.getInstance(), KeyEvent.ALT_DOWN_MASK, KeyEvent.VK_Q);
        // 明天，使用抽象类，有两个属性，一个按钮，以及该按钮的处理器，
    }
}
