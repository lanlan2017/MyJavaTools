package adbs.main.ui.jpanels.scrcpy;

import adbs.main.AdbTools;
import adbs.main.ui.config.FlowLayouts;
import adbs.main.ui.jpanels.adb.listener.OpenButtonListener;
import adbs.main.ui.jpanels.adb.open.Taskkill;
import tools.swing.button.AbstractButtons;

import javax.swing.*;
import java.awt.*;
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
    /**
     * scrcpy.exe内部镜像宽度数组
     */
    private String[] widthArr = {"600", "540", "500", "480", "420", "360", "350", "340"};
    /**
     * 内部镜像宽度数组的下标
     */
    private int index = 1;
    // private int index = 0;

    public ScrcpyJPanels() {
        scrcpyJPanel = new JPanel();
        // adbJPanel.setBorder(new TitledBorder(""));
        scrcpyJPanel.setLayout(FlowLayouts.flowLayoutLeft);

        label = new JLabel("高度:");

        decreaseBtn = new JButton("-");
        addBtn = new JButton("+");

        widthTextField = new JTextField(4);
        // 设置投屏的 默认高度
        // widthTextField.setText(String.valueOf(540));
        // widthTextField.setText(String.valueOf(600));


        Toolkit toolkit = Toolkit.getDefaultToolkit();
        // 获取显示器分辨率
        Dimension dimension = toolkit.getScreenSize();
        System.out.println(dimension.height);
        System.out.println(dimension.width);
        if (dimension.height == 1080) {
            index = 2;
        } else {
            index = 1;
        }

        widthTextField.setText(String.valueOf(widthArr[index]));
        // 禁止用户修改宽度
        // widthTextField.setEditable(false);


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
                String id = AdbTools.getInstance().getDevice().getSerial();
                Taskkill.killScrcpy(id);
            }
        });

        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //     String text = widthTextField.getText();
                //     int oldWidth = Integer.parseInt(text);
                //     int width = oldWidth;
                //     if (width < 360) {
                //         width = width + 10;
                //     } else if (width < 600) {
                //         // width = width + 60;
                //         // width = width + 40;
                //     }
                //     if (width != oldWidth) {
                //         widthTextField.setText(String.valueOf(width));
                //     }

                if (index > 0) {
                    widthTextField.setText(widthArr[--index]);
                }
            }
        });
        decreaseBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // String text = widthTextField.getText();
                // int oldWidth = Integer.parseInt(text);
                // int width = oldWidth;
                // // 600
                // if (width > 540) {
                //     width = width - 60;
                //     // 600-60=540
                // } else if (width > 500) {
                //     width = width - 40;
                //     // 540-40=500
                // } else if (width > 480) {
                //     width = width - 20;
                //     // 500-20=480
                // } else if (width > 360) {
                //     width = width - 60;
                //     //    480-60=420
                //     //    420-60=360
                // } else if (width > 340) {
                //     width = width - 10;
                // }
                // if (width != oldWidth) {
                //     widthTextField.setText(String.valueOf(width));
                // }

                if (index < widthArr.length - 1) {
                    widthTextField.setText(widthArr[++index]);
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
