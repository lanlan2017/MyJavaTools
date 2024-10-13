package adbs.main.ui.jpanels.app;

import javax.swing.*;

/**
 * 封装了更新容器UI的方法。
 */
public class JComponents {
    /**
     * 更新容器的UI
     *
     * @param component 要更新的JPanel对象。
     */
    public static void updateJPanelUI(JComponent component) {
        component.revalidate();
        component.repaint();
    }
}
