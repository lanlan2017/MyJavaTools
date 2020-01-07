package ocr.baidu.formatter.impl;

import ocr.baidu.formatter.Formatter;
import tools.markdown.MarkdownTools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MdCodeInLineFormatter extends Formatter {

    @Override
    public String format(String str) {
        // 变成markdown代码.
        str = new MarkdownTools().inlineCodeAuto(str);
        // 处理
        str = fixMdInlineCode(str);
        return str;
    }

    /**
     * 修复处理后的markdown行内代码.文字识别无法识别圆括号<code>()</code>,这里恢复圆括号.
     *
     * @param processOutput m ks命令的处理结果.
     */
    protected String fixMdInlineCode(String processOutput) {
        //System.out.println("纠错之前:" + processOutput);
        // 恢复文字识别错误的圆括号
        String inlineCode = processOutput.replaceAll("[0oO]`方法", "()`方法");
        // 关键字恢复小写
        inlineCode = inlineCode.replaceAll("(?i)statIc", "static");
        // 删除无用的空格
        inlineCode = removeIncorrectSpaces(inlineCode);
        if (inlineCode.contains("`包")) {
            // 恢复java包名
            inlineCode = fixJavaPackageName(inlineCode);
        }
        //System.out.println("纠错之后:" + inlineCode);
        return inlineCode;
    }

    /**
     * 删除markdown 行内代码中多余的空格.
     * 大写前面的空格直接删除,如果小写前面又空格,则删除空格并将小写变大写.
     *
     * @param inlineCode 带有markdown行内代码的字符串.
     * @return markdown行内代码删除空格后的字符串.
     */
    private String removeIncorrectSpaces(String inlineCode) {
        // 删除大写字母前面的空格
        Pattern pattern = Pattern.compile("`(.+?)`");
        Matcher matcher = pattern.matcher(inlineCode);
        StringBuffer sb = new StringBuffer(inlineCode.length());
        String code;
        while (matcher.find()) {
            // 获取匹配到的一个分组
            code = matcher.group(1);
            // 将前面后空格的小写转成大写
            code = lcAfterSpaceToUc(code);
            // 在这里写上处理方法....
            // 替换原来匹配的文本
            matcher.appendReplacement(sb, "`" + code + "`");
        }
        // 添加后面没有匹配的文本
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 空格后面的小写字母变大写字母.
     *
     * @param code 带有空格的字符串.
     * @return 将空格和小写字母转换成大写字母后的字符串.
     */
    private String lcAfterSpaceToUc(String code) {
        // 如果包含括号,则这可能是一个方法,方法定义是可以有空格的.
        Matcher methodWithParameters = Pattern.compile("[(].+?[)]").matcher(code);
        if (methodWithParameters.find()) {
            //System.out.println("带参数的方法:" + code);
            return code;
        }
        // 如果不是方法
        // 删除大写前面的空格
        code = code.replaceAll(" ([A-Z])", "$1");
        // 删除小写前面的空格,然后将小写转为大写
        Matcher matcher = Pattern.compile(" ([a-z])").matcher(code);
        StringBuffer sb = new StringBuffer(code.length());
        String uppercaseLetter;
        while (matcher.find()) {
            // 获取匹配到的一个分组
            uppercaseLetter = matcher.group(1).toUpperCase();
            // 替换原来匹配的文本
            matcher.appendReplacement(sb, uppercaseLetter);
        }
        // 添加后面没有匹配的文本
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 修复文字识别错误的java包名:`xxxx`包
     *
     * @param inlineCode 文字识别后带有java包名的字符串.
     * @return 修复后的字符串.
     */
    private String fixJavaPackageName(String inlineCode) {
        Pattern pattern = Pattern.compile("`([jJ]?ava(?:[. ]+[a-zA-z0-9]+)+)`包");
        Matcher matcher = pattern.matcher(inlineCode);
        StringBuffer sb = new StringBuffer(inlineCode.length());
        String group1;
        while (matcher.find()) {
            StringBuffer rightPackageName = new StringBuffer();
            // 获取匹配到的一个分组
            group1 = matcher.group(1);
            String[] packageNames = group1.split("[. ]+");
            //System.out.print("`");
            rightPackageName.append("`");
            int count = 0;
            for (String packageName : packageNames) {
                System.out.print(packageName.toLowerCase());
                rightPackageName.append(packageName.toLowerCase());
                count++;
                if (count < packageNames.length) {
                    //System.out.print(".");
                    rightPackageName.append(".");
                }
            }
            //System.out.println("`包");
            rightPackageName.append("`包");
            // 替换原来的文本
            matcher.appendReplacement(sb, rightPackageName.toString());
        }
        // 添加后面没有匹配的文本
        matcher.appendTail(sb);
        inlineCode = sb.toString();
        return inlineCode;
    }
}