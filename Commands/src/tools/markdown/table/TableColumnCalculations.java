package tools.markdown.table;

/**
 * markdown表格列计算
 */
public class TableColumnCalculations {
    public static void main(String[] args) {
        TableColumnCalculations tableCalculation = new TableColumnCalculations();
        String tableStr;
        // tableStr = "|软件|金额|提现账户|是否到账|\n" + "|---:|:---|:---|:---|\n" + "|快手极速版|0.3|微信182|已到账|\n" + "|抖音极速版|0.3|支付宝159|已到账|\n" + "|番茄免费小说|0.3|微信182|已到账|\n" + "|番茄免费小说|1.0|微信182|已到账|\n" + "|今日头条极速版|0.3|支付宝182|x|\n" + "|淘宝|0.2|支付宝182|已到账|";
        // tableStr = "|软件|金额|提现账户|是否到账|\n" + "|---:|:---|:---|:---|\n" + "|0.12|淘宝|支付宝159|已到账|\n" + "|0.22|淘宝|支付宝159|已到账|\n" + "|0.87|淘宝|支付宝159|已到账|\n" + "|0.3|今日头条极速版|支付宝159|x |";
        tableStr = "\n|软件|金额|提现账户|是否到账|\n" + "|---:|:---|:---|:---|\n" + "|0.12|淘宝|支付宝159|已到账|\n" + "|0.22|淘宝|支付宝159|已到账|\n" + "|0.87|淘宝|支付宝159|已到账|\n" + "|0.3|今日头条极速版|支付宝159|x |\n\n";

        System.out.println(tableCalculation.tableColumnSum(tableStr));

    }

    /**
     * markdown表格列求和。
     * 要求表格中只有数据列只有一列。
     * @param tableStr 带有一列数据的markdown表格字符串。
     */
    public String tableColumnSum(String tableStr) {
        // 移除开头的换行符
        tableStr=tableStr.replaceAll("^\n+","");
        // 移除结尾的换行符
        tableStr=tableStr.replaceAll("\n+$","");
        String tableLines[] = tableStr.split("\n");
        String line;
        double sum = 0.0;
        StringBuilder sb = new StringBuilder(tableStr.length() + tableLines[tableLines.length - 1].length() + 4);
        for (int i = 0; i < tableLines.length; i++) {
            // 从第3行开始计算，markdown前两行为表格的标题和对齐方式
            if (i >= 2) {
                // System.out.println(tableLines[i]);
                line = tableLines[i];
                line = line.replaceAll(".*\\|([0-9]+[.][0-9]+)\\|.*", "$1");
                // System.out.println(line);
                sum += Double.valueOf(line);
            }
        }
        // System.out.println("sum = " + sum);
        // System.out.println(tableStr);
        sb.append(tableStr + "\n");
        String sumLine = tableLines[tableLines.length - 1].replaceAll("(?<=\\|)([0-9]+[.][0-9]+)(?=\\|)", String.valueOf(sum));
        // System.out.println();
        // System.out.println(sumLine);
        sumLine = sumLine.replaceAll("[a-zA-Z\u4e00-\u9fa5_][0-9a-zA-Z\u4e00-\u9fa5 _]+\\|", "...|");
        // System.out.println();
        // System.out.println(sumLine);
        sb.append(sumLine + "\n");
        // System.out.println(sb.toString());
        return sb.toString();
    }
}
