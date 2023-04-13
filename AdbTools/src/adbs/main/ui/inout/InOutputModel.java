package adbs.main.ui.inout;

import adbs.main.ui.jpanels.time.TimePanels;
import adbs.main.ui.jpanels.universal.UniversalPanels;

import javax.swing.*;

/**
 * 输入输出组件模型
 */
public class InOutputModel {

    /**
     * 时间输入选择组件
     */
    private TimePanels timePanels;

    /**
     * 通用功能组件
     */
    private UniversalPanels universalPanels;

    public InOutputModel(TimePanels timePanels, UniversalPanels universalPanels) {
        this.timePanels = timePanels;
        this.universalPanels = universalPanels;
    }

    public InOutputModel(TimePanels timePanels) {
        this.timePanels = timePanels;
    }

    public TimePanels getTimePanels() {
        return timePanels;
    }

    public UniversalPanels getUniversalPanels() {
        return universalPanels;
    }

}
