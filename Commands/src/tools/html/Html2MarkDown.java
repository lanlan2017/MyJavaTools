package tools.html;

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
        //删除td标签之间的加粗标签
        str = str.replaceAll("</?strong>", "**");
        //散开标签
        str = str.replaceAll("\\>(?:[ ])?\\<", ">\n<");
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
        // 替换加粗标签
        str = str.replaceAll("</?strong>", "**");
        // 删除div,span标签
        str = str.replaceAll("</?(?:div|span)(?: .*?)?>", "");
        // 替换段落标签
        str = str.replaceAll("(?:</?p>)+", "\n");

        // 删除多余空格
        str = str.replaceAll("[ ]+", " ");
        // 删除多余的空格转义字符
        str = str.replaceAll("(?:&nbsp;)+", " ");
        if (Pattern.compile("\\<ul\\>").matcher(str).find()) {
            str = htmlUl2MdUl(str);
        }
        return str;
    }


    /**
     * 替换HTML无序列表代码为Markdown无序列表
     *
     * @param str 包含html无序列表的文本
     * @return 包含Markdown无序列表的文本
     */
    public String htmlUl2MdUl(String str) {
        Matcher htmlUlM = Pattern.compile("\\<ul\\>(.+?)\\<\\/ul\\>").matcher(str);
        StringBuffer sb = new StringBuffer();
        while (htmlUlM.find()) {
            htmlUlM.appendReplacement(sb, htmlUlLi2MdUlLi(htmlUlM.group(1)));
        }
        htmlUlM.appendTail(sb);
        return sb.toString();
    }

    /**
     * Html 无序列表代码转成Markdown无序列表。
     *
     * @param htmlUlCode html无序列表代码。
     * @return Markdown无序列表代码。
     */
    public String htmlUlLi2MdUlLi(String htmlUlCode) {
        Matcher m = Pattern.compile("\\<li\\>(.+?)\\<\\/li\\>").matcher(htmlUlCode);
        StringBuilder sb = new StringBuilder(htmlUlCode.length());
        while (m.find()) {
            sb.append("- ").append(m.group(1)).append("\n");
        }

        return sb.toString();
    }
}
