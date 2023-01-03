package adbs.main.ui.inout;

import adbs.main.ui.jpanels.input.TimePanels;

import javax.swing.*;

/**
 * 输入输出组件模型
 */
public class InOutputModel {

    /**
     * 输入组件
     */
    private TimePanels timePanels;

    /**
     * 输出组件
     */
    private JLabel output;
    private JButton stopBtn;

    public InOutputModel(TimePanels timePanels, JLabel output, JButton stopBtn) {
        this.timePanels = timePanels;
        this.output = output;
        this.stopBtn = stopBtn;
    }

    public TimePanels getInputPanels() {
        return timePanels;
    }

    public JLabel getOutput() {
        return output;
    }

    public JButton getStopBtn() {
        return stopBtn;
    }
}
