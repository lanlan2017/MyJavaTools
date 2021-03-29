package tools.dir;

/**
 * 对字符串数组进行冒泡排序
 */
public class StringSortTest {
    public static void main(String[] args) {
        //test1();
        String str = "12.0 本章概述\n" +
                "12.1 Swing概述\n" +
                "12.10 使用JTree和TreeModel创建树\n" +
                "12.11 使用JTable和TableModel创建表格\n" +
                "12.12 创建格式文本\n" +
                "12.13 本章小结\n" +
                "12.2 Swing基本组件的用法\n" +
                "12.3 Swing中的特殊容器\n" +
                "12.4 Swing简化的拖放功能\n" +
                "12.5 Java7新增的Swing功能\n" +
                "12.6 创建进度条\n" +
                "12.7 创建滑动条\n" +
                "12.8 创建微调控制器\n" +
                "12.9 创建列表框";
        String[] array = str.split("\n");
        //for (String s : array) {
        //    System.out.println(s+"-----"+sectionValues(s));
        //}
        printStrs(array);
        if (isSectionDirectory(array)) {
            String temp;
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array.length - i - 1; j++) {
                    // 如果前面的小节大
                    if (sectionValues(array[j]) > sectionValues(array[j + 1])) {
                        //    将大的换到后面,实现增序排序
                        //缓存大的
                        temp = array[j];
                        //小的换到前面
                        array[j] = array[j + 1];
                        //大的换到后面
                        array[j + 1] = temp;
                    }
                    //System.out.println(array[j]+"----"+sectionValues(array[j]));
                    //System.out.println("   "+array[j+1]+"----"+sectionValues(array[j+1]));

                    //System.out.println(thisSectionValues+" "+nextSectionValues);
                }
            }
        }
        printStrs(array);
    }

    private static int sectionValues(String s1) {
        // 取得前面的数字"12.12 创建格式文本"中的12.12

        String sectionId = s1.substring(0, s1.indexOf(" "));
        //System.out.println(sectionId);
        // 将12.12分割成{12,12}数组
        String[] sectionNums = sectionId.split("\\.");
        //String[] thisIndexs = thisIndexNumber.split(".");
        //System.out.println(Arrays.toString(sectionNums));
        int sectionValues = 0;
        for (String num : sectionNums) {
            sectionValues = sectionValues * 10 + Integer.valueOf(num);
        }
        //System.out.println(sectionValues);
        return sectionValues;
    }

    private static boolean isSectionDirectory(String[] array) {

        boolean isSectionDirectory = true;
        for (String s : array) {
            if (!s.matches("\\d+(?:.\\d+)+ .+")) {
                isSectionDirectory = false;
                break;
            }
        }
        return isSectionDirectory;
    }

    private static void test1() {
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
        // sortChapterDirectory
        sortChapterDirectory(strs);
        printStrs(strs);
    }

    private static void sortChapterDirectory(String[] strs) {
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
