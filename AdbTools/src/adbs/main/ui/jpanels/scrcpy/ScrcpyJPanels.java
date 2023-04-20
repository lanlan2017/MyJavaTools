package adbs.main.ui.jpanels.scrcpy;

import adbs.main.AdbTools;
import adbs.main.ui.config.FlowLayouts;
import adbs.main.ui.jpanels.adb.listener.OpenButtonListener;
import adbs.main.ui.jpanels.adb.open.Taskkill;
import tools.swing.button.AbstractButtons;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScrcpyJPanels {
    /**
     * 面板
     */
    private JPanel scrcpyJPanel;

    private JLabel label;
    private JButton addBtn;
    private JButton decreaseBtn;
    private JTextField widthTextField;
    private JButton openScrcpyBtn;
    private JButton killScrcpyBtn;

    public ScrcpyJPanels() {
        scrcpyJPanel = new JPanel();
        // adbJPanel.setBorder(new TitledBorder(""));
        scrcpyJPanel.setLayout(FlowLayouts.flowLayoutLeft);

        label = new JLabel("高度:");

        decreaseBtn = new JButton("-");
        addBtn = new JButton("+");

        widthTextField = new JTextField(4);
        // 设置投屏的 默认高度
        widthTextField.setText(String.valueOf(540));
        // 禁止用户修改宽度
        widthTextField.setEditable(false);


        openScrcpyBtn = new JButton(new ImageIcon(AdbTools.class.getClassLoader().getResource("open.png")));

        openScrcpyBtn.setToolTipText("使用scrcpy打开设备");
        // openScrcpyBtn.addActionListener(new OpenButtonListener());
        openScrcpyBtn.addActionListener(new OpenButtonListener(widthTextField));
        killScrcpyBtn = new JButton("kill");
        killScrcpyBtn.setToolTipText("杀死打开的scrcpy镜像");
        killScrcpyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // String id = AdbTools.device.getId();
                String id = AdbTools.getInstance().getDevice().getId();
                Taskkill.killScrcpy(id);
            }
        });

        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = widthTextField.getText();
                int oldWidth = Integer.parseInt(text);
                int width = oldWidth;
                if (width < 360) {
                    width = width + 10;
                } else if (width < 600) {
                    width = width + 60;
                }
                if (width != oldWidth) {
                    widthTextField.setText(String.valueOf(width));
                }
            }
        });
        decreaseBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = widthTextField.getText();
                int oldWidth = Integer.parseInt(text);
                int width = oldWidth;
                if (width > 360) {
                    width = width - 60;
                } else if (width > 340) {
                    width = width - 10;
                }
                if (width != oldWidth) {
                    widthTextField.setText(String.valueOf(width));
                }
            }
        });


        // adb面板添加按钮
        scrcpyJPanel.add(label);
        scrcpyJPanel.add(widthTextField);
        scrcpyJPanel.add(decreaseBtn);
        scrcpyJPanel.add(addBtn);
        scrcpyJPanel.add(openScrcpyBtn);
        scrcpyJPanel.add(killScrcpyBtn);
        AbstractButtons.setMarginInButtonJPanel(scrcpyJPanel, 1);
    }

    public JPanel getScrcpyJPanel() {
        return scrcpyJPanel;
    }
}
