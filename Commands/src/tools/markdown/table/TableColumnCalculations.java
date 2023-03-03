package tools.markdown.table;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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
     *
     * @param tableStr 带有一列数据的markdown表格字符串。
     */
    public String tableColumnSum(String tableStr) {
        // 移除开头的换行符
        tableStr = tableStr.replaceAll("^\n+", "");
        // 移除结尾的换行符
        tableStr = tableStr.replaceAll("\n+$", "");
        // 按行拆分
        String tableLines[] = tableStr.split("\n");
        String line;
        // 累加器
        BigDecimal sum = new BigDecimal("0.0");
        // 遍历所有行
        for (int i = 0; i < tableLines.length; i++) {
            // 从第3行开始计算，markdown前两行为表格的标题和对齐方式
            if (i >= 2) {
                // System.out.println(tableLines[i]);
                line = tableLines[i];
                // 如果存在，浮点数 的单元格
                if (line.matches(".*\\|([0-9]+[.][0-9]+)\\|.*")) {
                    // 摘下数据列单元格中的浮点数
                    line = line.replaceAll(".*\\|([0-9]+[.][0-9]+)\\|.*", "$1");
                    // System.out.println(line);
                    sum = sum.add(new BigDecimal(line));
                    // System.out.println("数字:"+line);
                } else {
                    // System.out.println("非数字:"+line);
                }
            }
        }
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.setTime(new Date());
        // 获取当前日期
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        // 获取当前月最大天数
        int maxDayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        // 获取今年最大天数
        int maxDayOfYear = calendar.getActualMaximum(Calendar.DAY_OF_YEAR);
        double sum1 = Double.parseDouble(String.valueOf(sum));
        // 计算日薪
        double daySalary = sum1 / dayOfMonth;
        // 计算月薪
        double monthSalary = sum1 / dayOfMonth * maxDayOfMonth;
        // 计算年薪
        double yearSalary = sum1 / dayOfMonth * maxDayOfYear;
        // 定义格式化模板，保留到小数点后两位
        DecimalFormat df = new DecimalFormat("#.00");
        // 凭借字符串
        // String returnStr = "累计=" + sum + ",日薪=" + df.format(daySalary) + ",月薪=" + df.format(monthSalary)+",年薪="+df.format(yearSalary);
        String returnStr = sum+"\n日薪=" + df.format(daySalary) + ",月薪=" + df.format(monthSalary)+",年薪="+df.format(yearSalary);
        return returnStr;
    }
}
