package adbs.main.ui.jpanels.universal;

import adbs.main.ui.jpanels.time.TimePanels;
import adbs.main.ui.jpanels.universal.listener.*;
import adbs.main.ui.config.FlowLayouts;
import tools.swing.button.AbstractButtons;

import javax.swing.*;

/**
 * 通用面板
 */
public class UniversalPanels {
    /**
     * 通用面板
     */
    private final JPanel universalPanel;
    /**
     * 浏览按钮
     */
    private JButton browseButton;
    /**
     * 等待返回按钮
     */
    private JButton waitButton;
    /**
     * 阅读按钮
     */
    private JButton readButton;
    /**
     * 刷视频按钮
     */
    private JButton videoButton;
    /**
     * 逛街按钮
     */
    private JButton shoppingButton;

    /**
     * 通用面板输出功能
     */
    private JLabel output2;

    /**
     * 初始化通用面板
     *
     * @param timePanels
     */
    // public UniversalPanels(JFrame frame, InOutputModel inout2) {
    // public UniversalPanels(InOutputModel inout2) {
    public UniversalPanels(TimePanels timePanels) {
        // 创建通用功能面板
        universalPanel = new JPanel();
        // universalPanel.setBorder(new TitledBorder("通用功能"));

        universalPanel.setLayout(FlowLayouts.flowLayoutLeft);
        readButton = new JButton("阅读");
        readButton.setToolTipText("阅读功能:连续点击屏幕右侧");

        browseButton = new JButton("浏览");
        browseButton.setToolTipText("连续在屏幕左侧从下向上滑动");

        waitButton = new JButton("等待");
        waitButton.setToolTipText("等待一定时间后提示");
        videoButton = new JButton("刷视频");
        videoButton.setToolTipText("等待随机秒数后，从下向上滑动一次");

        shoppingButton = new JButton("逛街");
        shoppingButton.setToolTipText("连续从下向上滑动三次，然后上下来回滑动");

        // output2 = new JLabel("输出2");
        output2 = new JLabel("");

        universalPanel.add(readButton);
        universalPanel.add(browseButton);
        universalPanel.add(waitButton);
        universalPanel.add(videoButton);
        universalPanel.add(shoppingButton);
        universalPanel.add(output2);

        // readButton.addActionListener(new PyImgFindAcListener(ReadButtonRunnable.getInstance(), inout2));
        // readButton.addActionListener(new PyImgFindAcListener(ReadButtonRunnable.getInstance()));
        readButton.addActionListener(new ReadButtonActionListener(timePanels));

        browseButton.addActionListener(new BrowseButtonActionListener(timePanels));
        // 等待后返回按钮
        waitButton.addActionListener(new WaitButtonActionListener(timePanels));
        // 刷视频按钮
        videoButton.addActionListener(new VideoButtonActionListener(timePanels));
        // 逛街按钮
        shoppingButton.addActionListener(new ShoppingButtonActionListener(timePanels));


        AbstractButtons.setMarginInButtonJPanel(universalPanel, 1);
    }

    public JPanel getUniversalPanel() {
        return universalPanel;
    }

    public JButton getReadButton() {
        return readButton;
    }

    public JLabel getOutput2() {
        return output2;
    }
}
