package adbs.main.ui.jpanels.tools;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 在按钮动作执行完毕之前不允许用户反复点击按钮。
 */
public abstract class BtnActionListener implements ActionListener {

    public abstract void action(ActionEvent e);

    @Override
    public void actionPerformed(ActionEvent e) {
        // JButton button = before(e);
        BtnClickOnes.before(e);
        // OpenApp.openGuanJiaApp();
        action(e);
        BtnClickOnes.after(e);
    }
}
