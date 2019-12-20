package tools.markdown;

import tools.string.StringDeleter;
import regex.RegexEnum;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MdJavaCodeBlock {
    /**
     * 删除getter和setter方法,方法体的花括号在方法定义的同一行末尾.
     *
     * @param text java代码.
     * @return 没有getter和setter方法的代码.
     */
    public String removeGetterSetterDefaultStyle(String text) {
        text = javaNoGetterSetterRegex(text, RegexEnum.getterSetterDefaultStyle.getRegex());
        return text;
    }

    /**
     * 删除代码中的getter和setter方法,方法体的花括号在下一样.
     *
     * @param text java代码字符串.
     * @return 没有getter方法和setter方法的代码字符串.
     */
    public String romoveGetterSetterLikeVC(String text) {
        text = javaNoGetterSetterRegex(text, RegexEnum.getterSetterLikeVC.getRegex());
        return text;
    }

    /**
     * 使用正则表达式删除java代码中的getter和setter方法.
     *
     * @param input java代码.
     * @param regex 正则表达式.
     * @return 没有getter和setter方法的java代码.
     */
    private String javaNoGetterSetterRegex(String input, String regex) {
        Pattern getterSetterPattern = Pattern.compile(regex);
        Matcher getterSetterMatcher = getterSetterPattern.matcher(input);
        boolean isFirst = true;
        StringBuffer sb = new StringBuffer();
        while (getterSetterMatcher.find()) {
            // 第一次匹配的时候加上提示标记
            if (isFirst) {
                // 替换匹配到的结构,并将替换后的内容追加到之中
                getterSetterMatcher.appendReplacement(sb,
                        "// 此处省略getter和setter方法,请自己补上");
                isFirst = false;
            } else {
                // 其他的地方直接删除
                getterSetterMatcher.appendReplacement(sb, "");
            }
        }
        // 剩下没有匹配的也追加到sb之中.
        getterSetterMatcher.appendTail(sb);
        // 删除空行
        input = new StringDeleter().deleteBlankLine(sb.toString());
        return "```java\r\n" + input + "\r\n```";
    }
}
