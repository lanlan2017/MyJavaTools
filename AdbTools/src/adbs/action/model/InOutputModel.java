package adbs.action.model;

import adbs.main.auto.ui.InputPanels;

import javax.swing.*;

/**
 * 输入输出组件模型
 */
public class InOutputModel {
    /**
     * 输入组件
     */
    private InputPanelModel inputPanelModel;

    private InputPanels inputPanels;

    /**
     * 输出组件
     */
    private JLabel output;
    private JButton stopBtn;

    public InOutputModel(InputPanelModel inputPanelModel, JLabel output, JButton stopBtn) {
        this.inputPanelModel = inputPanelModel;
        this.output = output;
        this.stopBtn = stopBtn;
    }
    public InOutputModel(InputPanels inputPanels, JLabel output, JButton stopBtn) {
        this.inputPanels = inputPanels;
        this.output = output;
        this.stopBtn = stopBtn;
    }

    public InputPanelModel getInputPanelModel() {
        return inputPanelModel;
    }

    public InputPanels getInputPanels() {
        return inputPanels;
    }

    public JLabel getOutput() {
        return output;
    }

    public JButton getStopBtn() {
        return stopBtn;
    }
}
