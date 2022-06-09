package adbs.ui;

import javax.swing.*;
import java.awt.*;

/**
 * Java按钮工具类
 */
public class AbstractButtons {
    /**
     * 设置AbstractButton的内边距
     *
     * @param button AbstractButton对象
     */
    public static void setJButtonMargin(AbstractButton button) {
        int marginVal = 2;
        button.setMargin(new Insets(marginVal, marginVal, marginVal, marginVal));
    }

    /**
     * 设置JPanel中所有按钮的内边距
     *
     * @param panel 放置按钮的JPanel
     */
    public static void setMarginInButtonJPanel(JPanel panel) {
        int count = panel.getComponentCount();
        int x = 3;
        Insets insets = new Insets(x, x, x, x);
        for (int i = 0; i < count; i++) {
            Component component = panel.getComponent(i);
            if (component instanceof AbstractButton) {
                // System.out.println(component + "是按钮");
                AbstractButton abstractButton = (AbstractButton) component;
                abstractButton.setMargin(insets);
            }
        }
        panel.repaint();
        // frame.pack();
    }
}
