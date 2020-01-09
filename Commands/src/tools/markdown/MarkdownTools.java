package tools.markdown;

import tools.string.StringDeleter;
import regex.RegexEnum;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author francis
 * create at 2019/12/19-11:30
 */
public class MarkdownTools {

    public String header1(String input) {
        return "# " + input;
    }

    public String header2(String input) {
        return "## " + input;
    }

    public String header3(String input) {
        return "### " + input;
    }

    public String header4(String input) {
        return "#### " + input;
    }

    public String header5(String input) {
        return "##### " + input;
    }

    public String header6(String input) {
        return "###### " + input;
    }

    public String headerAdd(String text) {
        text = text.replaceAll("(#+)(.+)\\1", "#$1$2#$1");
        return text;
    }

    public String headerMinus(String text) {
        text = text.replaceAll("(#(#+))(.+)\\1", "$2$3$2");
        return text;
    }

    public String bold(String input) {
        return "**" + input + "**";
    }

    public String inlineCode(String input) {
        return "`" + input + "`";
    }

    public String inlineCodeAuto(String text) {
        Pattern pattern = Pattern.compile(RegexEnum.ToMDCodeInLine.toString());
        Matcher matcher = pattern.matcher(text);
        StringBuffer sb = new StringBuffer();
        String InlineCodeMatcher;
        while (matcher.find()) {
            // 获取匹配到的一个分组
            InlineCodeMatcher = matcher.group(1);
            // System.out.println("----------------------------------");
            // System.out.print(InlineCodeMatcher);
            // System.out.print("--->");
            // System.out.println(InlineCodeMatcher);
            // System.out.println("----------------------------------");
            InlineCodeMatcher = InlineCodeMatcher.replace("$", "\\$");
            InlineCodeMatcher = toMdInlineCode(InlineCodeMatcher);

            // 替换原来匹配的文本
            matcher.appendReplacement(sb, InlineCodeMatcher);
        }
        // 添加后面没有匹配的文本
        matcher.appendTail(sb);
        String result = sb.toString();

        // 移除多余的空格符
        result = fixErrors(result);
        return result;
    }

    /**
     * 修复Markdown行内代码中的错误字符串.
     *
     * @param result 包含markdown行内代码的字符串.
     * @return 纠错后的字符串
     */
    private String fixErrors(String result) {
        // 移除markdown行内代码之前的多余空格符
        result = result.replaceAll("[ ]+`", "`");
        //// 恢复文字识别错误的圆括号
        //result = result.replaceAll("[0oO]`方法", "()`方法");
        return result;
    }

    /**
     * 移除markdown行内代码.
     *
     * @param mdCodes 包含markdown行内代码的字符串.
     * @return 没有markdown行内代码的字符串.
     */
    public String inlineCodeUndo(String mdCodes) {
        mdCodes = mdCodes.replaceAll("`([^`]+?)`", "$1");
        return mdCodes;
    }

    /**
     * 转成markdown行内代码.
     *
     * @param inlineCode 要包装成markdown行内代码的字符串.
     * @return markdown行内代码字符串
     */
    private String toMdInlineCode(String inlineCode) {
        // 以右括号结尾时,但是前面有没有左括号,这说明这个右括号的匹配错误
        if (inlineCode.endsWith(")") && !inlineCode.contains("(")) {
            int index = inlineCode.length() - 1;
            inlineCode = "`" + inlineCode.substring(0, index) + "`" + inlineCode.substring(index);
        } else {
            // 如果有美元符号的话,先,因为美元符号在正则中有特殊意义
            inlineCode = "`" + inlineCode + "`";
        }
        return inlineCode;
    }

    public String hyperLinks(String input) {
        Pattern pattern = Pattern.compile("(.+?)\\s{2,}(.+?)");
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) {
            return "[" + matcher.group(1) + "](" + matcher.group(2) + ")";
        }
        return "[" + input + "](" + input + ")";
    }

    public String img(String text) {
        if (text.matches(RegexEnum.ImgURL.toString())) {
            return "![这里有一张图片](" + text + ")";
        } else if (text.matches(RegexEnum.MdImgNoAlt.toString())) {
            return text.replaceAll(RegexEnum.MdImgNoAlt.toString(), "![这里有一张图片]($1)");
        }
        return null;
    }

    public String unorderList(String text) {
        text = new StringDeleter().deleteBlankLine(text);
        // 开头不是字母数字或者中文的一律删除掉.
        text = text.replaceAll("(?m)^[^a-zA-Z0-9\\u4e00-\\u9fa5][ ]+", "");
        // 在每行开头添加上无序列表标记
        text = text.replaceAll(RegexEnum.LineStart.toString(), "- ");
        return text + "\n";
    }

    public String orderedList(String text) {
        text = new StringDeleter().deleteBlankLine(text);
        int i = 1;
        String[] lines = text.split("\\n");
        StringBuilder buf = new StringBuilder();
        for (String string : lines) {
            buf.append((i++) + ". " + string + "\n");
        }
        return buf.toString();
    }

    public String quote(String text) {
        text = new StringDeleter().deleteBlankLine(text);
        // 在多行文本的每一个行开头添加引用标记
        text = text.replaceAll(RegexEnum.LineStart.toString(), "> ");
        return text;
    }

    public String codeBlock(String language, String input) {
        input = input.replaceAll("\t", "    ");
        return "```" + language + "\n" + input + "\n```";
    }

    public String codeBlockDefault(String input) {
        input = input.replaceAll("\t", "    ");
        return "```\n" + input + "\n```";
    }

    public String codeBlockJava(String input) {
        input = input.replaceAll("\t", "    ");
        return "```java\n" + input + "\n```";
    }

    public String codeBlockJavaScript(String input) {
        return "```javascript\n" + input + "\n```";
    }

    public String codeBlockSql(String code) {
        // 将SQL代码中的中文标点符号转成英文
        code = chinesePunctuationMarks2English(code);
        // 检测SQL末尾有没有分号,如果没有分号的话就加上分号.
        if (!code.endsWith(";"))
            code = code + ";";
        return "```sql" + "\r\n" + code + "\r\n```";
    }

    /**
     * 中文标点符号转成英文标点符号.
     *
     * @param code 可能有中文表单符号的字符串.
     * @return 只有英文标点符号的字符串.
     */
    private String chinesePunctuationMarks2English(String code) {
        code = code.replace("；", ";");
        code = code.replace("，", ",");
        code = code.replace("‘", "'");
        code = code.replace("’", "'");
        code = code.replace("“", "\"");
        code = code.replace("”", "\"");
        code = code.replace("（", "(");
        code = code.replace("）", ")");
        return code;
    }
}
