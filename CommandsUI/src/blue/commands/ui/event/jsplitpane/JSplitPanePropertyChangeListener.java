package blue.commands.ui.event.jsplitpane;

import blue.commands.ui.MainFrom;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class JSplitPanePropertyChangeListener implements PropertyChangeListener {
    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        JSplitPane jSplitPane = MainFrom.getInstance().getJSplitPane();
        int location = jSplitPane.getDividerLocation();
        int max = jSplitPane.getMaximumDividerLocation();
        int min = jSplitPane.getMinimumDividerLocation();

        System.out.println("\n分隔条位置：" + location);
        System.out.println("Max分隔条位置：" + max);
        System.out.println("Min分隔条位置：" + min);
        System.out.println("Max+min分隔条位置：" + (max + min));

        // 如果分割条的位置为0，则说明分隔条左侧的组件被隐藏了
        if (location == 0) {
            System.out.println("--分隔条左侧面板被隐藏--\n");
        }
        if (location >= max + min) {
            System.out.println("--分隔条右侧面板被隐藏--\n");
        }
    }
}
