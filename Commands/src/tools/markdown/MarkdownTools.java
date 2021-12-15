package tools.markdown;

import tools.reflect.method.ObjectMap;
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

    /**
     * 生成markdown加粗代码
     *
     * @param text 普通文本
     * @return markdown加粗代码
     */
    public String bold(String text) {
        return "**" + text + "**";
    }

    /**
     * 生成markdown水平分割线块
     * 水平分隔线（horizontal rule）
     * https://www.w3school.com.cn/tags/tag_hr.asp
     *
     * @param code 水平分割线内的内容
     * @return markdown水平分割线块
     */
    public String horizontalRule(String code) {
        return "___\n" + code + "\n___\n";
    }

    /**
     * 撤销Markdown加粗
     *
     * @param markdownCode 有加粗的markdown代码
     * @return 取消markdown加粗后的markdown代码。
     */
    public String boldUndo(String markdownCode) {
        markdownCode = markdownCode.replace("**", "");
        return markdownCode;
    }

    /**
     * 生成markdown行内代码
     *
     * @param text 普通文本
     * @return markdown行内代码
     */
    public String inlineCode(String text) {
        return "`" + text + "`";
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
        // 从对象池中获取StringDeleter对象，免得重复创建对象。
        StringDeleter stringDeleter = ObjectMap.getObjectFromMap(StringDeleter.class);
        // 删除换行符
        String codeInOneLine = stringDeleter.deleteCRLF(code);
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
        // 把word里面的的无序列表标记替换成换行符
        text = text.replaceAll(" ?·", "\n");
        text = text.replaceAll(" ?● ", "\n");
        text = text.replaceAll(" ?❑ ", "\n");
        text = text.replaceAll("•", "\n");
        // 从对象池中获取StringDeleter对象，免得重复创建对象。
        StringDeleter stringDeleter = ObjectMap.getObjectFromMap(StringDeleter.class);

        // 删除空行
        text = stringDeleter.deleteBlankLine(text);
        // 开头不是字母数字或者中文的一律删除掉.
        text = text.replaceAll("(?m)^[^a-zA-Z0-9\\u4e00-\\u9fa5][ ]+", "");
        // 在每行开头添加上无序列表标记
        text = text.replaceAll(RegexEnum.LineStart.toString(), "- ");
        return text + "\n\n";
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
        //从对象池中获取StringDeleter对象，免得重复创建对象。
        StringDeleter stringDeleter = ObjectMap.getObjectFromMap(StringDeleter.class);
        // 删除空行
        text = stringDeleter.deleteBlankLine(text);

        int i = 1;
        String[] lines = text.split("\\n");
        StringBuilder buf = new StringBuilder();
        for (String string : lines) {
            buf.append((i++) + ". " + string + "\n");
        }
        return buf.toString();
    }

    public String quote(String text) {
        // 从对象池中获取StringDeleter对象，免得重复创建对象。
        StringDeleter stringDeleter = ObjectMap.getObjectFromMap(StringDeleter.class);
        // 删除空行
        text = stringDeleter.deleteBlankLine(text);
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
        // 如果已经是代码块了，则不进行处理
        if (input.startsWith("```")) {
            // 直接返回原来的代码块即可
            return input;
        }
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
    public String codeBlockPlantUml(String code) {
        if (code.startsWith("```\n")) {
            // System.out.println("真的吗");
            return code.replaceFirst("```", "```plantuml");
        } else if (code.startsWith("```plantuml\n")) {
            return code;
        }
        return "```plantuml\n" + code + "\n" + "```";
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

    // /**
    //  * 格式化正文里的markdown脚注。
    //  *
    //  * @param text 包含脚注的文本。
    //  * @return 包含正确脚注格式的文本。
    //  */
    // public String footnoteInText(String text) {
    //     text = text.replaceAll("\\[(\\d+)\\]", "[^$1]");
    //     return text;
    // }
    //
    // /**
    //  * 写在末尾的脚注
    //  *
    //  * @param text 包含末尾脚注的文本。
    //  * @return 包含正确末尾脚注格式的文本。
    //  */
    // pub修正错误脚注格式lic String footnoteInTail(String text) {
    //     text = text.replaceAll("\\[(\\d+)\\] ", "\n[^$1]: ");
    //     return text;
    // }

    /**
     * 修复文章中脚注和脚注的引用的格式不正确的问题。
     *
     * @param text 带有脚注或者脚注引用的文章的内容
     * @return 带有格式正确的脚注或脚注引用的文本。
     */
    public String repairFootnoteFormat(String text) {
        // 先替换尾部的脚注
        text = text.replaceAll("\\[(\\d+)\\] ", "\n[^$1]: ");
        // 再替换正文中的脚注
        text = text.replaceAll("\\[(\\d+)\\]", "[^$1]");
        return text;
    }

    /**
     * 格式化从微信读书赋值的带有图片或代码的多行文本。
     *
     * @param duoHangStr 复制得到的单行文本。
     * @return 拆分成多行的文本
     */
    public String weixinDuShu(String duoHangStr) {
        if (duoHangStr.startsWith("注意：") || duoHangStr.startsWith("注：") || duoHangStr.startsWith("说明：")) {
            duoHangStr = duoHangStr.replaceFirst("(注意|注|说明)：", "<strong>$1</strong>：");
            return "<div style=\"border-style:solid;\">" + duoHangStr + "</div>";
        }
        duoHangStr = duoHangStr.replace("[插图]", "\n\n[插图]\n\n");
        // 格式化Html代码
        duoHangStr = duoHangStr.replaceAll("<[a-z]+?>", "`$0`");
        //格式化代码描述信息
        duoHangStr = duoHangStr.replaceAll("(?m)(?:程序|代码)清单\\d+[.-]\\d+ .+$", "\n\n<center>$0</center>");
        //格式化表格描述信息
        duoHangStr = duoHangStr.replaceAll("(?m)表[0-9a-zA-Z]+[.-]\\d+ .+$", "\n\n<center>$0</center>\n");
        // 格式化图片描述信息
        duoHangStr = duoHangStr.replaceAll("(?m)^图\\d+[.-]\\d+ .+$", "<center>$0</center>\n");
        // 转换无序列表
        duoHangStr = duoHangStr.replaceAll("(?:❑ |•)", "\n- ");
        // 格式化中文列表
        duoHangStr = duoHangStr.replaceAll("（?\\d+）", "\n$0");
        // 注解包装成Markdown行内代码
        duoHangStr = duoHangStr.replaceAll("@[a-zA-Z]+", "`$0`");
        // Resources<Resource<Taco>>类似的代码转换成Markdown代码
        duoHangStr = duoHangStr.replaceAll("(?<!`)[a-zA-Z]+<[a-zA-Z<>]+>(?!`)", "`$0`");
        return duoHangStr;
    }

    /**
     * 格式化微信读书以图片作为无序列表标志的文本。
     *
     * @param text
     * @return
     */
    public String weixinDuShuUnorderedList(String text) {
        text = text.replaceAll("\\[插图\\] ", "\n- ");
        return text;
    }

    /**
     * 把从微信读书中复制来的问题代码进行处理，讲中文标点符号转为英文标点符号。
     * @param code 微信读书网页版中复制来的代码
     * @return 正确的代码
     */
    public String weixinDuShuCode(String code) {
        code = code.replace("；", ";");
        code = code.replace("（", "(");
        code = code.replace("）", ")");
        code = code.replace("～", "~");
        return code;
    }

    /**
     * @param input
     * @return
     */
    public String weixinDuShuBenZhangNeiRong(String input) {
        //本章内容：•使用Actuator端点的MBean•将Spring bean暴露为MBean•发布通知
        input = input.replaceAll("本章内容：", "<strong>本章内容：</strong>");
        input = input.replaceAll("•([^•]+)", "<li>$1</li>");
        input = input.replace("</strong>", "</strong><ul>");
        input = input + "</ul>";
        input = "<div style=\"border-style:solid;\">" + input + "</div>";
        return input;
    }
}
