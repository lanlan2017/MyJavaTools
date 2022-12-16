package adbs.main.auto.ui.jpanels.universal;

import adbs.action.listener.*;
import adbs.main.auto.ui.inout.InOutputModel;
import adbs.action.runnable.ReadButtonRunnable;
import adbs.main.auto.ui.config.FlowLayouts;
import adbs.main.auto.ui.jpanels.universal.listener.ShoppingButtonActionListener;
import adbs.main.auto.ui.jpanels.universal.listener.VideoButtonActionListener;
import adbs.main.auto.ui.jpanels.universal.listener.BrowseButtonActionListener;
import adbs.main.auto.ui.jpanels.universal.listener.WaitButtonActionListener;
import tools.swing.button.AbstractButtons;

import javax.swing.*;
import javax.swing.border.TitledBorder;

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
     * 初始化通用面板
     *
     * @param inout2
     * @return
     */
    public UniversalPanels(JFrame frame, InOutputModel inout2) {

        // 创建通用功能面板
        universalPanel = new JPanel();
        universalPanel.setBorder(new TitledBorder("通用功能"));
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

        universalPanel.add(readButton);
        universalPanel.add(browseButton);
        universalPanel.add(waitButton);
        universalPanel.add(videoButton);
        universalPanel.add(shoppingButton);

        readButton.addActionListener(new PyImgFindAcListener(ReadButtonRunnable.getInstance(), inout2));

        // 浏览后返回按钮事件处理程序
        // browseButton.addActionListener(new BrowseButtonActionListener(frame, inputPanels));

        // browseButton.addActionListener(new BrowseButtonActionListener(frame, inout2.getInputPanels()));
        browseButton.addActionListener(new BrowseButtonActionListener(frame, inout2.getInputPanels()));

        // 等待后返回按钮
        // waitReturnButton.addActionListener(new WaitReturnButtonActionListener(frame, inputPanels));
        waitButton.addActionListener(new WaitButtonActionListener(frame, inout2.getInputPanels()));



        // 刷视频按钮
        // videoButton.addActionListener(new VideoButtonActionListener(frame, inputPanels));
        videoButton.addActionListener(new VideoButtonActionListener(frame, inout2.getInputPanels()));

        // 逛街按钮
        // shoppingButton.addActionListener(new ShoppingButtonActionListener(frame, inputPanels));
        shoppingButton.addActionListener(new ShoppingButtonActionListener(frame, inout2.getInputPanels()));


        // AbstractButtons.setMarginInButtonJPanel(universalPanel);
        AbstractButtons.setMarginInButtonJPanel(universalPanel,1);
        frame.add(universalPanel);
    }

    public JPanel getUniversalPanel() {
        return universalPanel;
    }

    public JButton getReadButton() {
        return readButton;
    }
}
