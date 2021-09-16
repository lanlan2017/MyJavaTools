package tools.html;

import tools.string.PrintStr;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * HTML代码转成Markdown
 */
public class Html2MarkDown {
    /**
     * 将单行的HTML表格代码串，转换成Markdown表格.
     *
     * @param str HTML表格代码串，整个表格的代码都压缩在一行之中。
     * @return Markdown表格
     */
    public String htmlOneLineTable2MdTable(String str) {
        // PrintStr.printStr(str);
        //删除td标签之间的加粗标签
        str = str.replaceAll("</?strong>", "**");
        // 删除表格中的换行符
        str = str.replaceAll("<br>", "");
        //散开标签
        str = str.replaceAll("\\>(?:[ ]*)?\\<", ">\n<");
        str = str.replaceAll("<td>[ ]+([^ ]+)[ ]+</td>", "<td>$1</td>");
        //System.out.println(str);
        return htmlMultiLineTable2MdTable(str);
    }

    /**
     * 把多行的HTML Table代码转换成Markdown表格。
     *
     * @param str HTML Table代码，这些代码每个标签都占据一行。
     * @return Markdown表格
     */
    public String htmlMultiLineTable2MdTable(String str) {
        // PrintStr.printStr(str);
        //Scanner scanner = new Scanner(Test.class.getResourceAsStream("/tools/html/toMdTable.html"));
        Scanner scanner = new Scanner(str);
        String line;
        boolean isout = false;
        final Pattern thPattern = Pattern.compile("\\<(?:th|td)\\>(.+)\\<\\/(?:th|td)\\>");
        StringBuilder sb = new StringBuilder();
        int numberOfColumns = 0;
        boolean isFirstTr = true;
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            //System.out.println(line);
            if ("<tr>".equals(line)) {
                isout = true;
                continue;
            }
            if (isout) {
                //System.out.println(line);
                Matcher thMatcher = thPattern.matcher(line);
                if (thMatcher.matches()) {
                    //System.out.print("|" + thMatcher.group(1));
                    sb.append("|").append(thMatcher.group(1));
                    if (isFirstTr) {
                        //计算列数
                        numberOfColumns++;
                    }
                }
            }
            if ("</tr>".equals(line)) {
                isout = false;
                //System.out.print("|\n");
                sb.append("|\n");
                // 打印表格对齐方式
                if (isFirstTr) {
                    //System.out.println(numberOfColumns);
                    for (int i = 0; i < numberOfColumns; i++) {
                        //System.out.print("|:---");
                        sb.append("|:---");
                    }
                    //System.out.print("|\n");
                    sb.append("|\n");
                    isFirstTr = false;
                }
                //continue;
            }
        }
        return sb.toString();
    }

    /**
     * HTML普通文本转换为Markdown文本
     *
     * @param str html文本
     * @return Markdown文本
     */
    public String toMd(String str) {
        // 替换换行符
        str = str.replaceAll("(?:<br>)+", "\n");
        // div结束标签替换为换行符
        str = str.replaceAll("</div>", "\n");
        // 删除div开始标签
        str = str.replaceAll("<(?:div|p)(?: .*?)?>", "");
        // 替换页面标题
        str = str.replaceAll("<h1(?: .*?)?>(.+?)</h1>", "# $1\n");
        str = str.replaceAll("<h2(?: .*?)?>(.+?)</h2>", "## $1\n");
        str = str.replaceAll("<h3(?: .*?)?>(.+?)</h3>", "### $1\n");
        str = str.replaceAll("<h4(?: .*?)?>(.+?)</h4>", "#### $1\n");
        str = str.replaceAll("<h5(?: .*?)?>(.+?)</h5>", "##### $1\n");
        str = str.replaceAll("<h6(?: .*?)?>(.+?)</h6>", "###### $1\n");

        // 替换加粗标签<strong> <b>
        str = str.replaceAll("</?(?:strong|b)>", "**");
        // 删除pre,span标签
        str = str.replaceAll("</?(?:pre|span)(?: .*?)?>", "");
        // 替换段落标签
        str = str.replaceAll("(?:</?p>)+", "\n");
        // 删除多余空格
        str = str.replaceAll("[ ]+", " ");
        // 删除多余的空格转义字符
        str = str.replaceAll("(?:&nbsp;)+", " ");
        // 删除多余的连续的空白行
        str = str.replaceAll("(?m)(^ *?$\\r?\\n)+", "\n");
        // PrintStr.printStr(str);
        //替换无序列表
        // 如果有无序列表
        if (Pattern.compile("\\<ul\\>").matcher(str).find()) {
            // 替换无序列号
            // str = htmlUl2MdUl(str);
            str = HtmlListToMd.htmlUnOrderListToMd(str);
        }
        //如果共有有序列表
        if (Pattern.compile("\\<ol\\>").matcher(str).find()) {
            // 替换有序列表
            str = HtmlListToMd.htmlOrderList2Md(str);
        }
        // PrintStr.printStr(str);
        // 替换行内代码
        if (Pattern.compile("\\<code\\>").matcher(str).find()) {
            str = htmlCode2MdCode(str);
        }
        // 删除整行字符串开头的多余空格符
        str = str.replaceAll("^ +", "");
        // 多行模式，删除每行末尾多余的空格符
        str = str.replaceAll("(?m) +$", "");
        return str;
    }
    /**
     * HTML普通文本转换为Markdown文本
     *
     * @param str html文本
     * @return Markdown文本
     */
    public String toHuaZhangPreCode(String str) {
        // 替换换行符
        str = str.replaceAll("(?:<br>)+", "\n");
        // div结束标签替换为换行符
        str = str.replaceAll("</div>", "\n");
        // 删除div开始标签
        str = str.replaceAll("<(?:div|p)(?: .*?)?>", "");
        // 替换页面标题
        str = str.replaceAll("<h1(?: .*?)?>(.+?)</h1>", "# $1\n");
        str = str.replaceAll("<h2(?: .*?)?>(.+?)</h2>", "## $1\n");
        str = str.replaceAll("<h3(?: .*?)?>(.+?)</h3>", "### $1\n");
        str = str.replaceAll("<h4(?: .*?)?>(.+?)</h4>", "#### $1\n");
        str = str.replaceAll("<h5(?: .*?)?>(.+?)</h5>", "##### $1\n");
        str = str.replaceAll("<h6(?: .*?)?>(.+?)</h6>", "###### $1\n");

        // 替换加粗标签<strong> <b>
        str = str.replaceAll("</?(?:strong|b)>", "**");
        // 删除pre,span标签
        str = str.replaceAll("</?(?:pre|span)(?: .*?)?>", "");
        // 替换段落标签
        str = str.replaceAll("(?:</?p>)+", "\n");
        // 删除多余空格
        // str = str.replaceAll("[ ]+", " ");
        // 删除多余的空格转义字符
        str = str.replaceAll("(?:&nbsp;)+", " ");
        // 删除多余的连续的空白行
        str = str.replaceAll("(?m)(^ *?$\\r?\\n)+", "\n");
        // PrintStr.printStr(str);
        //替换无序列表
        // 如果有无序列表
        if (Pattern.compile("\\<ul\\>").matcher(str).find()) {
            // 替换无序列号
            // str = htmlUl2MdUl(str);
            str = HtmlListToMd.htmlUnOrderListToMd(str);
        }
        //如果共有有序列表
        if (Pattern.compile("\\<ol\\>").matcher(str).find()) {
            // 替换有序列表
            str = HtmlListToMd.htmlOrderList2Md(str);
        }
        // PrintStr.printStr(str);
        // 替换行内代码
        if (Pattern.compile("\\<code\\>").matcher(str).find()) {
            str = htmlCode2MdCode(str);
        }
        // 删除整行字符串开头的多余空格符
        str = str.replaceAll("^ +", "");
        // 多行模式，删除每行末尾多余的空格符
        str = str.replaceAll("(?m) +$", "");
        return str;
    }

    /**
     * html code代码转换为Markdown code
     *
     * @param htmlCode 包含code的HTML文本
     * @return 转换后的markdown文本
     */
    public String htmlCode2MdCode(String htmlCode) {
        Matcher m = Pattern.compile("\\<code\\>(.+?)\\<\\/code\\>").matcher(htmlCode);
        StringBuffer sb = new StringBuffer(htmlCode.length());
        while (m.find()) {
            m.appendReplacement(sb, "`" + m.group(1) + "`");
        }
        m.appendTail(sb);
        return sb.toString();
    }
}
