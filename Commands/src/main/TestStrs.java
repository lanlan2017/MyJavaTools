package main;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 测试字符串处理
 */
public class TestStrs {
    //static Scanner scanner = new Scanner(Test.class.getResourceAsStream("/tools/html/toMdTable.html"));
    public static void main(String[] args) {
        String str = "<div class=\"post-content\"><div>  <span>选<strong>B、C</strong></span> </div> <div>  <span>cat</span>：由第一行开始显示文件所有内容； </div> <div>  <span>tac</span>：从最后一行开始显示文件的所有内容，注意 tac 与cat 写法正好相反； </div> <div>  <span>more</span>：一页一页的显示文件内容，只能向后翻页； </div> <div>  <span>less</span>：也是一页一页显示文件内容，但是可以通过键盘上的【pagedown】，【pageup】控制向后，向前翻页； </div> <div>  <span>head</span>：显示一个文件的前几行； </div> <div>  <span>tail</span>：显示一个文件的后几行； </div></div>";
        //System.out.println(htmlOneLineTable2MdTable(str));
        System.out.println(toMd(str));
    }

    /**
     * 将单行的HTML表格代码串，转换成Markdown表格.
     *
     * @param str HTML表格代码串，整个表格的代码都压缩在一行之中。
     * @return Markdown表格
     */
    private static String htmlOneLineTable2MdTable(String str) {
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
    public static String htmlMultiLineTable2MdTable(String str) {
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
     * 移除html标签
     *
     * @return
     */
    public static String toMd(String str) {
        // 替换换行符
        str = str.replaceAll("(?:<br>)+", "\n");
        // 替换加粗标签
        str = str.replaceAll("</?strong>", "**");
        // 删除div,span标签
        str = str.replaceAll("</?(?:div|span)(?: .*?)?>", "");
        // 删除多余空格
        str = str.replaceAll("[ ]+", " ");
        // 删除多余的空格转义字符
        str = str.replaceAll("(?:&nbsp;)+", " ");
        return str;
    }
}
