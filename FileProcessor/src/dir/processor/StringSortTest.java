package dir.processor;

import java.util.regex.Pattern;

/**
 * 对字符串数组进行冒泡排序
 */
public class StringSortTest {
    public static void main(String[] args) {
        String str = "第10章 异常处理\n" +
                "第11章 AWT编程\n" +
                "第12章 Swing编程\n" +
                "第13章 MySQL数据库与JDBC编程\n" +
                "第14章 注解(Annotation)\n" +
                "第15章 输入_输出\n" +
                "第16章 多线程\n" +
                "第17章 网络编程\n" +
                "第18章 类加载机制与反射\n" +
                "第1章 Java语言概述与开发环境\n" +
                "第2章 理解面向对象\n" +
                "第3章 数据类型和运算符\n" +
                "第4章 流酲控制与数组\n" +
                "第5章 面向对象(上)\n" +
                "第6章 面向对象(下)\n" +
                "第7章 Java基础类库\n" +
                "第8章 Java集合\n" +
                "第9章 泛型";
        String[] strs = str.split("\n");
        printStrs(strs);
        Pattern pattern = Pattern.compile("第(\\d+)章 .+");
        String tmp;
        sortStrs(strs);

        printStrs(strs);

    }

    private static void sortStrs(String[] strs) {
        String tmp;// 遍历n次
        for (int i = 0; i < strs.length; i++) {
            // 遍历剩下未排序的元素
            for (int j = 0; j < strs.length - i - 1; j++) {
                String numStrI = strs[j].replaceAll("第(\\d+)章 .+", "$1");
                String numStrJ = strs[j + 1].replaceAll("第(\\d+)章 .+", "$1");
                //System.out.println(numStrI + " " + numStrJ);
                ////// 如果前面的大
                //if (strs[j] > strs[j + 1])
                if (Integer.parseInt(numStrI) > Integer.parseInt(numStrJ)) {
                    // 保存大的数
                    tmp = strs[j];
                    // 小的放前面
                    strs[j] = strs[j + 1];
                    // 大的放后面
                    strs[j + 1] = tmp;
                }
            }
        }
    }

    private static void printStrs(String[] strs) {
        System.out.println("---------------------");
        for (String s : strs) {
            System.out.println(s);
        }
    }
}
