package adbs.action.listener;

import adbs.action.runnable.BrowseRunnable;
import adbs.action.runnable.ShoppingButtonRunnable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InputOkButtonActionListener implements ActionListener {
    private JLabel output;
    private BrowseRunnable browseRunnable;
    private ShoppingButtonRunnable shoppingButtonRunnable;

    public InputOkButtonActionListener(JLabel output) {
        this.output = output;
        browseRunnable = new BrowseRunnable(output);
        shoppingButtonRunnable = new ShoppingButtonRunnable(output);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton ok = (JButton) e.getSource();
        if ("开始浏览".equals(ok.getText())) {
            output.setText("浏览线程：开始浏览");
            Thread thread = new Thread(browseRunnable);
            thread.start();
        } else if ("开始逛街".equals(ok.getText())) {
            output.setText("逛街线程：开始逛街");
            new Thread(shoppingButtonRunnable).start();
        }
    }
}
