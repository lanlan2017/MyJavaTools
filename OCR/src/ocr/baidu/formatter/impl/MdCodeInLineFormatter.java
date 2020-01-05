package ocr.baidu.formatter.impl;

import java.util.ArrayList;
import java.util.List;

import clipboard.swing.SystemClipboard;
import ocr.baidu.formatter.FormatterByCmd;

public class MdCodeInLineFormatter extends FormatterByCmd {
    /**
     * 执行<code>cmd /c m cpkd</code>命令来格式化字符串.
     */
    public MdCodeInLineFormatter() {
        List<String> commandList = new ArrayList<>();
        commandList.add("cmd");
        commandList.add("/c");
        commandList.add("m");
        commandList.add("ks");
        this.commandList = commandList;
    }

    /**
     * 修复处理后的markdown行内代码.文字识别无法识别圆括号<code>()</code>,这里恢复圆括号.
     *
     * @param processOutput m ks命令的处理结果.
     */
    @Override
    protected void fixProcessOutput(String processOutput) {
        // 恢复文字识别错误的圆括号
        String inlineCode = processOutput.replaceAll("[0oO]`方法", "()`方法");
        inlineCode=inlineCode.replaceAll("Array[ ]?[lL]ist", "ArrayList");
        SystemClipboard.setSysClipboardText(inlineCode);
    }
}