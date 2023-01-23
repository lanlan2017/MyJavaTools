package adbs.main.ui.inout;

import adbs.main.ui.jpanels.input.TimePanels;
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

    /**
     * 输出组件
     */
    private JLabel output;
    private JButton stopBtn;

    // public InOutputModel(TimePanels timePanels, UniversalPanels universalPanels, JLabel output, JButton stopBtn) {
    //     this.timePanels = timePanels;
    //     this.universalPanels = universalPanels;
    //     this.output = output;
    //     this.stopBtn = stopBtn;
    // }
    public InOutputModel(TimePanels timePanels, UniversalPanels universalPanels, JButton stopBtn) {
        this.timePanels = timePanels;
        this.universalPanels = universalPanels;
        this.output = output;
        this.stopBtn = stopBtn;
    }

    public InOutputModel(TimePanels timePanels, JLabel output, JButton stopBtn) {
        this.timePanels = timePanels;
        this.output = output;
        this.stopBtn = stopBtn;
    }

    public TimePanels getTimePanels() {
        return timePanels;
    }

    // public JLabel getOutput() {
    //     return output;
    // }

    public UniversalPanels getUniversalPanels() {
        return universalPanels;
    }

    public JButton getStopBtn() {
        return stopBtn;
    }
}
