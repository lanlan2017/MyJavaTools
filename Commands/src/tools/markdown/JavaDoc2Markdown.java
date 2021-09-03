package tools.markdown;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author francis
 * create at 2019/12/19-22:32
 */
public class JavaDoc2Markdown {
    /**
     * 将两个一样的空格分隔的表格转成markdown表格.
     *
     * @param tableStrFromWeb 从Javadoc复制过来的字符串.
     * @return markdown表格.
     */
    public String mdTableCopy(String tableStrFromWeb) {
        tableStrFromWeb = tableStrFromWeb.replaceAll("\t", "    ");
        // 行首添加表格分隔符
        tableStrFromWeb = tableStrFromWeb.replaceAll("(?m)^(?:\\s{2,})?", "|");
        // 在行尾添加表格分割符
        tableStrFromWeb = tableStrFromWeb.replaceAll("(?m)$", "|");
        tableStrFromWeb = tableStrFromWeb.replaceAll("\\s{2,}", "|");
        // 在两个空格以上的地方替换成表格分割符
        // 获取第一行
        String firstLine = tableStrFromWeb.substring(0, tableStrFromWeb.indexOf("\n"));
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
    public String methodTable(String text) {
        // 两行之间添加竖杠,然后变成一行
        text = generateTableBody(text);
        // 添加表格标题和对齐方式
        return "|方法|描述|\n|:--|:--|\n" + text;
    }

    /**
     * 从Java Doc文档中复制方法表格.
     *
     * @param text Java doc表格中复制得到的字符串.
     * @return markdown表格.
     */
    public String docMethodTable2MdTable(String text) {
        text = docTable2MdTableBody(text);
        // 添加表格标题和对齐方式
        return "|方法|描述|\n|:--|:--|\n" + text;
    }

    public String docMethodTable2MdTableNoDescription(String text) {
        text = generateTableBodyNoDescription(text);
        // 添加表格标题和对齐方式
        return "|方法|描述|\n|:--|:--|\n" + text;
    }

    public String docFieldTable2MdTable(String text) {
        text = docTable2MdTableBody(text);
        return "|属性|描述|\n|:--|:--|\n" + text;
    }

    public String docTable2MdTableBody(String text) {
        text = generateTableBody(text);
        // 第一列作为行内代码
        text = highlightFirstColumn(text);
        return text;
    }

    private String highlightFirstColumn(String tableBody) {
        return tableBody.replaceAll("(?m)^\\|(.+?)(\\|.+?\\|)$", "|`$1`$2");
    }

    /**
     * 生成表格主体.
     *
     * @param text 从java Doc表格中复制过来的文本.
     * @return markdown表格体字符串.
     */
    private String generateTableBody(String text) {
        text = text.replaceAll("Deprecated.\n", "**Deprecated**. ");
        text = text.replaceAll("(?m)>$\\n", "> ");
        text = text.replace("\u200B(", "(");
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
        text = text.replace("\t", " ");

        return text;
    }

    public String generateTableBodyNoDescription(String text) {
        // 让不建议挪动到描述这行
        text = text.replaceAll("Deprecated.\n", "**Deprecated**. ");
        // 删除方法括号前面的空白符
        text = text.replace("\u200B(", "(");
        // 制表符换成空格
        text = text.replace("\t", " ");
        // 两行之间添加竖杠,然后变成一行
        text = text.replaceAll("(.+)\\n.+", "|$1||");
        // 删除分隔符前面的空格.
        text = text.replaceAll("[ ]+(?=[|])", "");
        // 行首加竖杠
        //text = text.replaceAll("(?m)^", "|");
        // 高亮第一列
        text = text.replaceAll("(?m)^[|]([^|]+)[|][|]$", "|`$1`||");
        return text;
    }

    public String multiLine2Table(String str) {
        // 计数器:记录长的列的长度,列的长度就是行数
        int rowNums = 0;
        // 按多行进行分割:得到列
        String[] cols = str.split("(?m)(?:^$(?:\\n|\\r\\n)){2,}");
        // 创建二维列表来保存表格
        List<List<String>> colList = new ArrayList<>();
        for (int i = 0; i < cols.length; i++) {
            // 按行分割:得到一列的每个单词
            String[] colStrs = cols[i].split("\n");
            // 统计最多的行数
            if (colStrs.length > rowNums) {
                rowNums = colStrs.length;
            }
            // 将列保存到线性表中
            colList.add(Arrays.asList(colStrs));
        }
        //System.out.println(rowNums);
        // 转置二维列表
        List<ArrayList<String>> listRow = transposeListList(colList);
        //
        String result = printListListTable(listRow);

        return result;
    }

    public String parametersList(String parameterStr) {
        String[] parameters = parameterStr.split(",");
        int maxLength = (int) (parameterStr.length() * 1.5);
        StringBuilder sb = new StringBuilder(maxLength);
        for (String parameter : parameters) {
            sb.append("- `" + parameter.trim() + "`" + ":\n");
        }
        return sb.toString();
    }

    private String printListListTable(List<ArrayList<String>> listRow) {
        StringBuilder sb = new StringBuilder();
        Iterator<ArrayList<String>> iteratorRow = listRow.iterator();
        while (iteratorRow.hasNext()) {
            //System.out.println(iteratorRow.next());
            List<String> rowList1 = iteratorRow.next();
            for (int i = 0; i < rowList1.size(); i++) {
                if (i == 0) {
                    //System.out.print("|");
                    sb.append("|");
                }
                //System.out.print(rowList1.get(i));
                sb.append(rowList1.get(i));
                //System.out.print("|");
                sb.append("|");
            }
            // System.out.println();
            sb.append("\n");
        }
        String firstLine = sb.substring(0, sb.indexOf("\n") + 1);
        String title = firstLine.replaceAll("\\|[^|\n]+", "|");
        String aligned = firstLine.replaceAll("\\|[^|\n]+", "|:--");
        //System.out.println("firstLine = " + firstLine);
        return title + aligned + sb.toString();
    }

    /**
     * 转置二维列表
     *
     * @param listList 二维列表
     * @return 转置后的二维列表
     */
    private List<ArrayList<String>> transposeListList(List<List<String>> listList) {
        // 计算二维列表长度
        int rowNums = 0;
        for (int i = 0; i < listList.size(); i++) {
            int rows = listList.get(i).size();
            if (rows > rowNums) {
                rowNums = rows;
            }
        }

        // 转置后的二维列表
        List<ArrayList<String>> transposedListList = new ArrayList<>();
        // 创建转置列表的行
        for (int i = 0; i < rowNums; i++) {
            transposedListList.add(new ArrayList<>());
        }
        // 遍历二维列表的列
        Iterator<List<String>> iteratorCol = listList.iterator();
        while (iteratorCol.hasNext()) {
            // 取出二维列表的一列
            List<String> colListi = iteratorCol.next();
            // 遍历二维列表的一列
            for (int i = 0; i < colListi.size(); i++) {
                // 取出当前列的第i个元素
                final String s = colListi.get(i);
                // 将取出的元素,放大转置列表中的第i行中
                transposedListList.get(i).add(s);
            }
        }
        return transposedListList;
    }


    public static void main(String[] args) {
        String test = "void\tchangedUpdate\u200B(DocumentEvent e)\t\n" + "Gives notification that an attribute or set of attributes changed.\n" + "void\tinsertUpdate\u200B(DocumentEvent e)\t\n" + "Gives notification that there was an insert into the document.\n" + "void\tremoveUpdate\u200B(DocumentEvent e)\t\n" + "Gives notification that a portion of the document has been removed.";
        System.out.println(new JavaDoc2Markdown().generateTableBodyNoDescription(test));
        System.out.println("------------");
        System.out.println(new JavaDoc2Markdown().docMethodTable2MdTable(test));

    }

}
