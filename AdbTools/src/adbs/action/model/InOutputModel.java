package adbs.action.model;

import javax.swing.*;

/**
 * 输入输出组件模型
 */
public class InOutputModel {
    /**
     * 输入组件
     */
    private InputPanelModel inputPanelModel;
    /**
     * 输出组件
     */
    private JLabel output;

    public InOutputModel(InputPanelModel inputPanelModel, JLabel output) {
        this.inputPanelModel = inputPanelModel;
        this.output = output;
    }

    public InputPanelModel getInputPanelModel() {
        return inputPanelModel;
    }

    public JLabel getOutput() {
        return output;
    }

}
