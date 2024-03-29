package tools.string.multiline;

import tools.constant.QuanJiao;
import tools.format.date.DateFormatters;
import tools.reflect.method.ObjectMap;
import tools.string.StringDeleter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.*;
import java.util.function.Consumer;

public class MultilineString {
    public static void main(String[] args) {
        // test_sum();
        // test_sumDate();
        test_sumAll();

    }

    private static void test_sumAll() {
        String s = "## 2022-10-20\n" + "－－－－－－－－－－－\n" + "今日头条极速版　0.5\n" + "快手　　　　　　1.0\n" + "快手极速版　　　0.3\n" + "百度极速版　　　1.0\n" + "－－－－－－－－－－－\n" + "总计　　　　　　2.8\n" + "－－－－－－－－－－－\n" + "\n" + "## 2022-10-21\n" + "－－－－－－－－－－－\n" + "快手极速版　　　2.0\n" + "快手　　　　　　2.0\n" + "今日头条极速版　0.5\n" + "百度极速版　　　0.5\n" + "－－－－－－－－－－－\n" + "总计　　　　　　5.0\n" + "－－－－－－－－－－－\n" + "\n" + "## 2022-10-22\n" + "－－－－－－－－－－－\n" + "今日头条极速版　0.5\n" + "快手　　　　　　1.0\n" + "快手极速版　　　2.0\n" + "百度极速版　　　0.5\n" + "－－－－－－－－－－－\n" + "总计　　　　　　4.0\n" + "－－－－－－－－－－－\n" + "\n" + "\n" + "## 2022-10-23\n" + "－－－－－－－－－－－\n" + "今日头条极速版　0.5\n" + "快手极速版　　　2.0\n" + "快手　　　　　　0.3\n" + "百度极速版　　　0.5\n" + "－－－－－－－－－－－\n" + "总计　　　　　　3.3\n" + "－－－－－－－－－－－\n" + "\n" + "## 2022-10-25\n" + "－－－－－－－－－－－\n" + "今日头条极速版　0.5\n" + "快手极速版　　　2.0\n" + "快手　　　　　　0.3\n" + "百度极速版　　　0.5\n" + "点淘　　　　　　3.5\n" + "火山小视频　　　1.0\n" + "－－－－－－－－－－－\n" + "总计　　　　　　7.8\n" + "－－－－－－－－－－－\n" + "\n" + "## 2022-10-26\n" + "－－－－－－－－－－－\n" + "今日头条极速版　0.5\n" + "快手极速版　　　0.3\n" + "快手　　　　　　1.0\n" + "百度极速版　　　0.5\n" + "－－－－－－－－－－－\n" + "总计　　　　　　2.3\n" + "－－－－－－－－－－－\n";
        // System.out.println(s);
        MultilineString multilineString = new MultilineString();
        System.out.println(multilineString.subAll(s));
    }

    /**
     * 给多天的，多个"产品 价格"求总价格。
     *
     * @param s 多行字符串，每行的字符串格式为 "产品 价格"，产品可重复
     * @return 表格, 升序排列，表格的每一行格式为 "产品，该产品价格累加值",最后一行为"合计 所有产品的价格累加值"
     */
    public String subAll(String s) {
        s = deleteUselessLine(s);
        // System.out.println(s);
        // System.out.println();
        if(s.contains("## 总计")){
            // 删除文件末尾的总计信息
            s = s.substring(0, s.lastIndexOf("## 总计"));
            // System.out.println(s);
            // System.out.println();
        }
        LinkedHashMap<String, BigDecimal> linkedHashMap = sumAllInLinkHashMap(s);
        StringBuilder sb = new StringBuilder();
        // System.out.println(getHorizontalLine(11));
        sb.append("## 总计\n");
        sb.append(getHorizontalLine(11)).append("\n");

        // System.out.print(printSumAllTable(linkedHashMap));
        sb.append(printSumAllTable(linkedHashMap));
        // System.out.println(getHorizontalLine(11));
        sb.append(getHorizontalLine(11)).append("\n");
        // System.out.println(getHorizontalLine(11));
        String s1 = sb.toString();
        return s1;
    }

    private static String printSumAllTable(LinkedHashMap<String, BigDecimal> linkedHashMap) {
        StringBuilder sb = new StringBuilder();
        BigDecimal bigDecimal = linkedHashMap.get("maxLength");
        // 删除附加的名称长度
        linkedHashMap.remove("maxLength");
        int finalMaxLength = bigDecimal.intValue();
        // 按价格排序，然后打印表格
        Set<Map.Entry<String, BigDecimal>> entries = linkedHashMap.entrySet();
        // 转换为List
        List<Map.Entry<String, BigDecimal>> list = new ArrayList<>(entries);
        // 调用Collections的sort方法进行排序
        Collections.sort(list, new Comparator<Map.Entry<String, BigDecimal>>() {
            @Override
            public int compare(Map.Entry<String, BigDecimal> o1, Map.Entry<String, BigDecimal> o2) {
                // 使用BigDecimal默认的排序规则
                // Java默认的排序规则是升序排序
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        list.forEach(new Consumer<Map.Entry<String, BigDecimal>>() {
            @Override
            public void accept(Map.Entry<String, BigDecimal> stringBigDecimalEntry) {
                String key = stringBigDecimalEntry.getKey();
                BigDecimal value = stringBigDecimalEntry.getValue();
                if (key.equals("合计")) {
                    sb.append(getHorizontalLine(11)).append("\n");
                }
                sb.append(key);
                int llll = finalMaxLength - key.length();
                int l = finalMaxLength + 1 - key.length();
                while (l > 0) {
                    // System.out.print(QuanJiao.kongGe);
                    sb.append(QuanJiao.kongGe);
                    l--;
                }
                // System.out.println(value);
                sb.append(value).append("\n");
            }
        });

        return sb.toString();
    }

    /**
     * 计算多行小数各项结果，以及累加值，名称最长字符
     *
     * @param s 包含小数的多行字符串
     * @return
     */
    private static LinkedHashMap<String, BigDecimal> sumAllInLinkHashMap(String s) {
        int maxLength = 0;
        LinkedHashMap<String, BigDecimal> linkedHashMap = new LinkedHashMap<>();
        BufferedReader reader = new BufferedReader(new StringReader(s));
        String line;
        BigDecimal sumAll = new BigDecimal(0);
        try {
            while (((line = reader.readLine()) != null)) {

                String[] split = line.split("[ ]+");
                String key = split[0];
                if (key.length() > maxLength) {
                    maxLength = key.length();
                }
                String valueStr = split[1];
                // 如果已经存在了这个key
                BigDecimal value = new BigDecimal(valueStr);
                if (linkedHashMap.containsKey(key)) {
                    BigDecimal valueOld = linkedHashMap.get(key);
                    valueOld = valueOld.add(value);
                    linkedHashMap.put(key, valueOld);
                    // 求和
                    sumAll = sumAll.add(value);
                }
                // 如果还没有这个key
                else {
                    linkedHashMap.put(key, value);
                    sumAll = sumAll.add(value);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        linkedHashMap.put("maxLength", new BigDecimal(maxLength));
        linkedHashMap.put("总计", sumAll);
        return linkedHashMap;
    }

    /**
     * 多行字符中其他无关行，只保留"产品 价格"格式的行。
     *
     * @param s 包含多行"产品 价格"的字符串。
     * @return 只有"产品 价格"的多行字符串
     */
    private static String deleteUselessLine(String s) {
        // 删除每日总计
        s = s.replaceAll("[累合总]计[　]+[0-9{1,}]\\.[0-9]{1,2}\\n", "");
        // System.out.println(s);
        // 删除分隔符
        s = s.replaceAll("－+\n?", "");
        // 删除日期行
        s = s.replaceAll("\\#\\# [0-9]{4}\\-[0-9]{1,2}\\-[0-9]{1,2}\\n", "");
        // 全角空格替换成半角空格
        s = s.replaceAll(QuanJiao.kongGe, " ");
        s = ObjectMap.get(StringDeleter.class).deleteBlankLine(s);
        // System.out.println("");
        // System.out.println(s);
        return s;
    }

    private static void test_sum() {
        String s = "今日头条极速版 0.5\n" + "快手极速版 0.3\n" + "快手11.0333333\n" + "百度极速版0.53333";
        MultilineString multilineString = new MultilineString();
        System.out.println(multilineString.sum(s));
    }

    private static void test_sumDate() {
        String s = "今日头条极速版 0.5\n" + "快手极速版 0.3\n" + "快手11.0333333\n" + "百度极速版0.53333";
        MultilineString multilineString = new MultilineString();
        System.out.println(multilineString.sum(s));
    }

    /**
     * 计算多个"产品 价格"的总价格，并生成带日期的表格。
     *
     * @param mulLine 多行字符串，每行的格式为"产品 价格"。
     * @return 带日期的自定义表格
     */
    public String sumDate(String mulLine) {
        return "## " + DateFormatters.yyyyMMdd.format(new Date()) + "\n" + sum(mulLine);
    }


    /**
     * 对没有重复key多行"产品 价格"字符串，求总价格。
     *
     * @param mulLine 多行字符串，每行的格式为"产品 价格"
     * @return 包含总价格的表格。
     */
    public String sum(String mulLine) {
        // // 删除分割符号
        // mulLine = mulLine.replaceAll("－+\n?", "");
        // // 删除旧的合计
        // mulLine = mulLine.replaceAll("合计　+[0-9]+\\.[0-9]+\n", "");
        // // 把全角空格替换为半角空格，免得影响后续处理。
        // mulLine = mulLine.replaceAll(QuanJiao.kongGe, " ");
        mulLine = deleteUselessLine(mulLine);
        System.out.println(mulLine);
        System.out.println();

        String horizontalLine = getHorizontalLine(11);
        StringBuilder sb = new StringBuilder();
        sb.append(horizontalLine).append("\n");
        // System.out.print(sumMultipleLinesOfDecimals(mulLine));
        sb.append(sumMultipleLinesOfDecimals(mulLine));
        // System.out.println(horizontalLine);
        sb.append(horizontalLine).append("\n");
        return sb.toString();
    }

    /**
     * 获取n个中文全角横线
     *
     * @param n 中文全角横线的数量
     * @return n个中文全角横线
     */
    private static String getHorizontalLine(int n) {
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            sb.append(QuanJiao.HengXian);
        }
        String horizontalLine = sb.toString();
        return horizontalLine;
    }

    /**
     * 多行小数求和
     * 对多行文本中的小数进行求和
     * 多行价格的每一行，支持的格式如下：
     * "苹果2.0"，"西瓜 3.8"
     *
     * @param s 包含小数的多行字符串。
     */
    private static String sumMultipleLinesOfDecimals(String s) {
        // 初始化为原来字符串的1.5倍
        StringBuilder sb = new StringBuilder(s.length() + s.length() >> 1);
        // 先按行分割
        String[] lines = s.split("\n");

        LinkedHashMap<String, BigDecimal> linkedHashMap = putSumInLast(lines);
        String maxLength = "MaxKeyLength";
        BigDecimal maxLengthValue = linkedHashMap.get(maxLength);

        linkedHashMap.remove("MaxKeyLength");
        Set<Map.Entry<String, BigDecimal>> entries = linkedHashMap.entrySet();
        ArrayList<Map.Entry<String, BigDecimal>> list = new ArrayList<>(entries);
        Collections.sort(list, new Comparator<Map.Entry<String, BigDecimal>>() {
            @Override
            public int compare(Map.Entry<String, BigDecimal> o1, Map.Entry<String, BigDecimal> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        int maxKeyLength = maxLengthValue.intValue();
        list.forEach(new Consumer<Map.Entry<String, BigDecimal>>() {
            @Override
            public void accept(Map.Entry<String, BigDecimal> stringBigDecimalEntry) {
                String key = stringBigDecimalEntry.getKey();
                BigDecimal value = stringBigDecimalEntry.getValue();
                if (key.equals("合计")) {
                    sb.append(getHorizontalLine(11)).append("\n");
                }
                // System.out.print(key);
                sb.append(key);
                int lll = maxKeyLength + 1 - key.length();
                while (lll > 0) {
                    // System.out.print(QuanJiao.kongGe);
                    sb.append(QuanJiao.kongGe);
                    lll--;
                }
                // System.out.println(value);
                sb.append(value).append("\n");
            }
        });


        return sb.toString();
    }

    /**
     * 对商品-价格，进行求和，并把求和的结果放在map的尾部。
     *
     * @param lines
     */
    private static LinkedHashMap<String, BigDecimal> putSumInLast(String[] lines) {
        BigDecimal sum = new BigDecimal(0);
        // 创建一个集合
        LinkedHashMap<String, BigDecimal> linkedHashMap = new LinkedHashMap<>();
        int maxLength = 0;
        // 遍历所有行
        for (String line : lines) {
            // System.out.println(line);
            // 分割出小数和描述
            String[] split = line.split("(?<=[\\u4e00-\\u9fa5 ])(?=[0-9.])");
            // 获取key
            String key = split[0].trim();
            // 查找最大长度
            if (key.length() > maxLength) {
                maxLength = key.length();
            }
            // 文字和数字分开，放入小数
            BigDecimal value = new BigDecimal(split[1]);
            linkedHashMap.put(key, value);
            sum = sum.add(value);
        }
        linkedHashMap.put("MaxKeyLength", new BigDecimal(maxLength));
        linkedHashMap.put("合计", sum);
        return linkedHashMap;
    }
}
