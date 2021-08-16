package tools.markdown;

import tools.string.StringDeleter;
import regex.RegexEnum;
import tools.web.URLEncode;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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

    /**
     * 标题下沉,一级标题变成二级标题,二级标题变成三级标题,以此类推。
     *
     * @param text
     * @return
     */
    public String headerAdd(String text) {
        text = text.replaceAll("(?m)^(#+)( .+)$", "#$1$2");
        return text;
    }

    /**
     * 标题提升,二级标题变成一级标题,三级标题变成二级标题。
     *
     * @param text
     * @return
     */
    public String headerMinus(String text) {
        text = text.replaceAll("(?m)^#(#+)( .+)$", "$1$2");
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
        result = result.replaceAll("[ ]+(?=`)", "");
        // 移除makrdown行内代码之后,中文字符之前的空格
        result = result.replaceAll("(?<=`)[ ]+(?=[\\u4e00-\\u9fa5])", "");
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

    /**
     * 格式化为markdown图片
     *
     * @param url 图片的地址
     * @return markdown图片
     */
    public String img(String url) {
        if (url.matches(RegexEnum.ImgURL.toString())) {
            return "![这里有一张图片](" + url + ")";
        } else if (url.matches(RegexEnum.MdImgNoAlt.toString())) {
            return url.replaceAll(RegexEnum.MdImgNoAlt.toString(), "![这里有一张图片]($1)");
        }
        return null;
    }

    /**
     * 返回g.gravizo.com渲染的markdown图片。
     *
     * @param code dot代码
     * @return markdown图片
     */
    public String imgGravizoSvg(String code) {
        String head = "![图片](https://g.gravizo.com/svg?";
        String codeInOneLine = new StringDeleter().deleteCRLF(code);
        String browserUrl = URLEncode.encodeToWebURL(codeInOneLine);
        String tail = ")";
        return head + browserUrl + tail;
    }

    /**
     * 格式化为无序列号
     *
     * @param text 多行字符串
     * @return Markdown无序列表
     */
    public String unorderList(String text) {
        text = new StringDeleter().deleteBlankLine(text);
        // 开头不是字母数字或者中文的一律删除掉.
        text = text.replaceAll("(?m)^[^a-zA-Z0-9\\u4e00-\\u9fa5][ ]+", "");
        // 在每行开头添加上无序列表标记
        text = text.replaceAll(RegexEnum.LineStart.toString(), "- ");
        return text + "\n";
    }

    /**
     * Markdown无序列表转换成普通的多行字符串.
     *
     * @param text markdown无序列表字符串.
     * @return 普通文本字符串.
     */
    public String cancelUnorderedList(String text) {
        return text.replaceAll("(?m)^- ", "");
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
        // 删除无用的\t缩进
        input = removeExcessIndentation(input);
        // 将剩下的\t缩进转换成四个空格
        input = input.replaceAll("\t", "    ");
        input = "```java\n" + input + "\n```";
        input = input.replaceAll("(?m)^$(?:\\r\\n|\\n)```", "```");
        return input;
    }

    /**
     * 删除字符串每行开头多余的\t缩进
     *
     * @param code 多行的字符串.
     * @return 移除多余的缩进后的字符串.
     */
    private String removeExcessIndentation(String code) {
        // 如果字符串以\t开头或者四个空格开头
        if (code.startsWith("\t") || code.startsWith("    ")) {
            // 缩进字符
            final char indentChar;
            // 如果以\t开头,则使用\t作为缩进
            if (code.charAt(0) == '\t') {
                indentChar = '\t';
            } else {
                // 使用空格作为缩进
                indentChar = ' ';
            }
            // 按行分割
            String[] lines = code.split("\n");
            //---------- 统计每行开头的\t缩进字符数量
            // 统计每行开头的空格数
            int[] indentCounter = new int[lines.length];
            // 遍历所有的行
            for (int i = 0; i < lines.length; i++) {
                // 遍历没一行的所有字符
                String line = lines[i];
                for (int lineIndex = 0, length = line.length(); lineIndex < length; lineIndex++) {

                    if (line.charAt(lineIndex) == indentChar) {
                        // 这一行的计数器加一
                        indentCounter[i]++;
                    } else {
                        // 如果不是非缩进字符,则结束循环
                        break;
                    }
                }
            }
            //---------------------------------
            //for (int blanks : indentationCounter) {
            //    System.out.println(blanks);
            //}
            // ------- 查找最小的缩进值
            // 查找数组中最小的数的下标
            // 数组中最小的数的下标,默认第一个元素最小
            int minIndex = 0;
            // 遍历数组
            for (int i = 1; i < indentCounter.length; i++) {
                // 如果找到更小的数
                if (indentCounter[i] < indentCounter[minIndex]) {
                    // 记下这个更小的数的下标
                    minIndex = i;
                }
            }
            // ---------------------------------
            int minIndentation = indentCounter[minIndex];
            // System.out.println("开头最少\t数量为:" + min);

            StringBuilder sb = new StringBuilder(code.length());
            for (int i = 0; i < lines.length; i++) {
                // 删除开头的无用缩进字符
                lines[i] = lines[i].substring(minIndentation);
                //System.out.println(lines[i]);
                sb.append(lines[i] + "\n");
            }
            code = sb.toString();
        }
        return code;
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
     * 生成dot语法的的plantuml代码块。
     *
     * @param code dot代码。
     * @return dot语法的PlantUML代码块
     */
    public String codeBlockPlantUmlDot(String code) {
        code = "```plantuml\n" + "@startdot\n" + code + "\n@enddot\n" + "```";
        return code;
    }

    /**
     * 生成markdown高亮标签
     *
     * @param str 要标记的内容
     * @return markdown标记代码
     */
    public String mark(String str) {
        return "==" + str + "==";
    }

    /**
     * markdown转成html代码
     *
     * @return html代码。
     */
    public String toHtml(String markdownCode) {
        // markdownCode = "`properties`属性";
        String codeRegex = "\\`(.+?)\\`";
        markdownCode = markdownCode.replaceAll(codeRegex, "<code>$1</code>");

        return markdownCode;
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
