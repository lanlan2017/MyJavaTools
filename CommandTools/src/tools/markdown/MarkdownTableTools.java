package tools.markdown;

/**
 * @author francis
 * create at 2019/12/19-22:32
 */
public class MarkdownTableTools {
    /**
     * 将两个一样的空格分隔的表格转成markdown表格.
     *
     * @param tableStrFromWeb
     * @return markdown表格.
     */
    public String mdTableCopy(String tableStrFromWeb) {
        // 行首添加表格分隔符
        tableStrFromWeb = tableStrFromWeb.replaceAll("(?m)^(?:\\s{2,})?", "|");
        // 在行尾添加表格分割符
        tableStrFromWeb = tableStrFromWeb.replaceAll("(?m)$", "|");
        tableStrFromWeb = tableStrFromWeb.replaceAll("\\s{2,}", "|");
        // 在两个空格以上的地方替换成表格分割符
        // 获取第一行
        String firstLine = tableStrFromWeb.substring(0,
                tableStrFromWeb.indexOf("\n"));
        // 把第一行替换成markdown表格的对齐方式
        firstLine = firstLine.replaceAll("([^|]+?\\|)", ":---|");
        // 在第一行末尾添加表格对齐方式
        return "\n" + tableStrFromWeb.replaceFirst("\n", "\n" + firstLine + "\n");
    }

    public String mdTableCopyHighlight(String tableStrFromWeb) {
        tableStrFromWeb = mdTableCopy(tableStrFromWeb);
        tableStrFromWeb = new MarkdownTools().inlineCodeAuto(tableStrFromWeb);
        return tableStrFromWeb;
    }

    /**
     * 从Java Doc文档中复制表格.
     *
     * @param text Java doc表格中复制得到的字符串.
     * @return markdown表格.
     */
    public String copyMdTableFramJavadoc(String text) {
        // 两行之间添加竖杠,然后变成一行
        text = copyMdTableFramJavadocBody(text);
        // 添加表格标题和对齐方式
        return "|方法|描述|\n|:--|:--|\n" + text;
    }

    /**
     * 从Java Doc文档中复制方法表格.
     *
     * @param text Java doc表格中复制得到的字符串.
     * @return markdown表格.
     */
    public String copyMdTableFramJavadocMethod(String text) {
        text = copyMdTableFramJavadocBody(text);
        // 第一列作为行内代码
        text = text.replaceAll("(?m)^\\|(.+?)(\\|.+?\\|)$", "|`$1`$2");
        // 添加表格标题和对齐方式
        return "|方法|描述|\n|:--|:--|\n" + text;
    }

    /**
     * 生成表格主体.
     *
     * @param text 从java Doc表格中复制过来的文本.
     * @return markdown表格体字符串.
     */
    private String copyMdTableFramJavadocBody(String text) {
        // 两行之间添加竖杠,然后变成一行
        text = text.replaceAll("(.+)\\n(.+)", "$1|$2");
        // 行首加竖杠
        text = text.replaceAll("(?m)^", "|");
        // 行尾加竖杠
        text = text.replaceAll("(?m)$", "|");
        // 删除多引入的竖杠
        text = text.replaceAll("(?m)^\\|$", "");
        //System.out.println(text);
        // 删除分隔符前面的空格.
        text = text.replaceAll("[ \t]+(?=[|])", "");
        //text = text.replaceAll("\\s+(?=[|(])", "");
        // // 删除多余的空格
        text = text.replaceAll("\\s{2,}", " ");
        text = text.replace("\u200B(", "(");
        return text;
    }
}
