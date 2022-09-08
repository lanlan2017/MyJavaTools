package adbs.test.auto.ui;

import adbs.action.listener.*;
import adbs.action.model.InOutputModel;
import adbs.action.runnable.ReadButtonRunnable;
import adbs.test.auto.ui.config.FlowLayouts;
import tools.swing.button.AbstractButtons;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class UniversalPanels {
    private JPanel universalPanel;
    private JButton browseButton;
    private JButton waitReturnButton;
    private JButton readButton;
    private JButton videoButton;
    private JButton shoppingButton;


    /**
     * 初始化通用面板
     *
     * @param inputPanels
     * @param inout2
     * @return
     */
    public UniversalPanels(JFrame frame, InputPanels inputPanels, InOutputModel inout2) {
        // 创建通用功能面板
        universalPanel = new JPanel();
        universalPanel.setBorder(new TitledBorder("通用功能"));
        universalPanel.setLayout(FlowLayouts.flowLayoutLeft);
        browseButton = new JButton("浏览返回");
        waitReturnButton = new JButton("等待");
        readButton = new JButton("阅读");
        videoButton = new JButton("刷视频");
        shoppingButton = new JButton("逛街");
        universalPanel.add(browseButton);
        universalPanel.add(waitReturnButton);
        universalPanel.add(readButton);
        universalPanel.add(videoButton);
        universalPanel.add(shoppingButton);
        AbstractButtons.setMarginInButtonJPanel(universalPanel);

        // 浏览后返回按钮事件处理程序
        browseButton.addActionListener(new BrowseButtonActionListener(frame, inputPanels));
        // 等待后返回按钮
        waitReturnButton.addActionListener(new WaitReturnButtonActionListener(frame, inputPanels));
        readButton.addActionListener(new PyImgFindAcListener(ReadButtonRunnable.getInstance(), inout2));
        // 刷视频按钮
        videoButton.addActionListener(new VideoButtonActionListener(frame, inputPanels));

        // 逛街按钮
        shoppingButton.addActionListener(new ShoppingButtonActionListener(frame, inputPanels));

        frame.add(universalPanel);
    }

    public JPanel getUniversalPanel() {
        return universalPanel;
    }

    public JButton getBrowseButton() {
        return browseButton;
    }

    public JButton getWaitReturnButton() {
        return waitReturnButton;
    }

    public JButton getReadButton() {
        return readButton;
    }

    public JButton getVideoButton() {
        return videoButton;
    }

    public JButton getShoppingButton() {
        return shoppingButton;
    }
}
