package adbs.action.listener;

import adbs.action.runnable.BrowseRunnable;
import adbs.action.runnable.ShoppingButtonRunnable;
import adbs.action.runnable.VideoButtonRunnable;
import adbs.action.runnable.WaitReturnButtonRunnable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InputOkButtonActionListener implements ActionListener {
    private JPanel inputPanel;
    private JLabel output;
    private BrowseRunnable browseRunnable;
    private ShoppingButtonRunnable shoppingButtonRunnable;
    private WaitReturnButtonRunnable waitReturnButtonRunnable;
    private VideoButtonRunnable videoButtonRunnable;

    public InputOkButtonActionListener(JPanel inputPanel, JTextField input1, JLabel output) {
        this.output = output;
        this.inputPanel = inputPanel;
        this.browseRunnable = new BrowseRunnable(output);
        this.shoppingButtonRunnable = new ShoppingButtonRunnable(output);
        this.waitReturnButtonRunnable = new WaitReturnButtonRunnable(input1, output);
        this.videoButtonRunnable = new VideoButtonRunnable(output);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton ok = (JButton) e.getSource();

        if ("开始浏览".equals(ok.getText())) {
            output.setText("浏览线程：开始浏览");
            new Thread(browseRunnable).start();
        } else if ("开始逛街".equals(ok.getText())) {
            output.setText("逛街线程：开始逛街");
            new Thread(shoppingButtonRunnable).start();
        } else if ("开始等待".equals(ok.getText())) {
            output.setText("等待返回线程：开始等待");
            new Thread(waitReturnButtonRunnable).start();
            waitReturnButtonRunnable.setStartButton(ok);
        } else if ("开始刷视频".equals(ok.getText())) {
            output.setText("刷视频线程：开始等待");
            // new Thread(waitReturnButtonRunnable).start();
            int count = inputPanel.getComponentCount();
            if (count > 3) {
                // 获取倒数第2个组件
                Component comp1 = inputPanel.getComponent(count - 2);
                // 如果第2个组件是文本框的话
                if (comp1 instanceof JTextField) {
                    JTextField input2 = (JTextField) comp1;
                    // 如果第2个输入框可见的话
                    if (input2.isVisible()) {
                        Component component2 = inputPanel.getComponent(count - 3);
                        if (component2 instanceof JTextField) {
                            JTextField input1 = (JTextField) component2;
                            VideoButtonRunnable.setMin(Integer.parseInt(input1.getText()));
                            VideoButtonRunnable.setMax(Integer.parseInt(input2.getText()));
                        }
                    }
                }
            }
            new Thread(videoButtonRunnable).start();
        }
    }
}
