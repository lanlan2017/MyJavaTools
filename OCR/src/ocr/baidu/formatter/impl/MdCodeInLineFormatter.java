package ocr.baidu.formatter.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        // 恢复常用类
        inlineCode = inlineCode.replaceAll("Array[ ]?[lL]ist", "ArrayList");
        // 关键字恢复小写
        inlineCode = inlineCode.replaceAll("(?i)statIc", "static");
        if (inlineCode.contains("`包")) {
            // 恢复java包名
            inlineCode = fixJavaPackageName(inlineCode);
        }

        SystemClipboard.setSysClipboardText(inlineCode);
    }

    private String fixJavaPackageName(String inlineCode) {
        Pattern pattern = Pattern.compile("`([jJ]?ava(?:[. ]+[a-zA-z0-9]+)+)`包");
        Matcher matcher = pattern.matcher(inlineCode);
        StringBuffer sb = new StringBuffer();
        String group1;
        while (matcher.find()) {
            StringBuffer rightPackageName = new StringBuffer();
            // 获取匹配到的一个分组
            group1 = matcher.group(1);
            String[] packageNames = group1.split("[. ]+");
            System.out.print("`");
            rightPackageName.append("`");
            int count = 0;
            for (String packageName : packageNames) {
                System.out.print(packageName.toLowerCase());
                rightPackageName.append(packageName.toLowerCase());
                count++;
                if (count < packageNames.length) {
                    System.out.print(".");
                    rightPackageName.append(".");
                }
            }
            System.out.println("`包");
            rightPackageName.append("`包");
            // 在这里写上处理方法....
            // 替换原来匹配的文本
            //matcher.appendReplacement(sb, group1);
            matcher.appendReplacement(sb, rightPackageName.toString());

        }
        // 添加后面没有匹配的文本
        matcher.appendTail(sb);
        inlineCode = sb.toString();
        //inlineCode=inlineCode.replace("`ava.", "`java.");
        return inlineCode;
    }
}