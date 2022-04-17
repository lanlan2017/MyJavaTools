package main;

import tools.config.ConfigTools;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AddLinefeeds {
    public static void main(String[] args) {
        // String str = "17.3 保护Admin服务器正如我们在第16章所讨论的那样，Actuator端点对外暴露的信息并不能随便消费。它们包含的信息暴露了应用的详情，这些信息只有应用程序的管理员才能查看。另外，还有一些端点允许对应用进行变更，它们就更不应该对所有人开放了。正如安全性对于Actuator来说非常重要一样，它对Admin服务器同样重要。除此之外，如果Actuator端点需要认证，那么Admin需要知道凭证信息才能访问这些端点。接下来，我们看一下如何为Admin服务器添加一些安全性。首先，从认证开始。17.3.1 为Admin服务器启用登录功能默认情况下，Admin服务器是不安全的，所以为其添加安全性功能是一种好的做法。因为Admin服务器就是一个Spring Boot应用，所以我们可以使用SpringSecurity来保护它。这一点与其他的Spring Boot应用完全类似。就像使用SpringSecurity保护其他的应用一样，我们可以自由选择最适合需求的安全模式。";
        Scanner scanner = new Scanner(System.in);

        System.out.println("输入字符串：");
        // 读入丢失了换行符的文本
        String input = scanner.nextLine();


        // 读取每行的行尾前面的一段字符串。
        List<String> lineEndList = getLineEndList(scanner);
        // System.out.println(lineEndList);
        // 给丢失换行符的文本重新添加上换行符
        String str = addLinefeeds(input, lineEndList);
        // 输出添加换行符之后的文本
        // System.out.println(str);

        str = weixinDuShu2Md(str);

        // 显示执行结果
        ConfigTools.getInstance().showResult(str);
    }

    /**
     * 微信读书复制到的文本转Markdown。
     *
     * @param str 恢复换行符之后的文本。
     * @return markdown格式的文本。
     */
    private static String weixinDuShu2Md(String str) {
        str = str.replaceAll("•", "\n- ");
        str = str.replaceAll("(?m)^\\d+\\.\\d+ .+$", "# $0");
        str = str.replaceAll("(?m)^\\d+\\.\\d+\\.\\d+ .+$", "## $0");
        str = str.replaceAll("(?m)^\\[插图\\]", "$0\n\n");
        str = str.replaceAll("<[a-zA-Z]+>", "`$0`");
        str = str.replaceAll("(?m)^图[0-9A-Z]+(?:\\.\\d+)+ .+$", "<center>$0</center>");
        str = str.replaceAll("(?m)^程序清单\\d+(?:\\.\\d+) +.+$", "<center>$0</center>");
        str = str.replaceAll("(?m)^表[0-9a-zA-Z]+(?:\\.\\d+) .+$", "<center>$0</center>");
        return str;
    }

    /**
     * 读取行尾前面的子串。
     *
     * @param scanner 扫描器
     * @return 保存各行行尾前面的一段字符串的List集合。
     */
    private static List<String> getLineEndList(Scanner scanner) {
        ArrayList<String> lineEndList = new ArrayList<>();
        String next;
        System.out.println("输入行尾子串：");
        // 读取行尾子串。
        while (true) {
            next = scanner.nextLine();
            if ("__end__".equals(next) || "".equals(next)) {
            // if ("__end__".equals(next)) {
                break;
            }
            lineEndList.add(next);
        }
        return lineEndList;
    }

    /**
     * 在str中的lineEndStrList子串后面重新添加换行符。
     *
     * @param str            丢失了换行符的字符串
     * @param lineEndStrList 行结束符前面的子串
     * @return 在字符串str中的子串后面重新添加换行符之后的得到的字符串
     */
    private static String addLinefeeds(String str, List<String> lineEndStrList) {
        // 计算sb的容量，输入的字符串的长度，加上两个换行符的长度，加上多余的5个字符的空间。
        int capacity = str.length() + lineEndStrList.size() * 2 + 5;
        // 创建指定容量的SB
        StringBuilder sb = new StringBuilder(capacity);
        // 行开始下标
        int lineStartIndex = 0;
        // 遍历行尾子串List
        for (String lineEnd : lineEndStrList) {
            // 计算行尾的下标：字符串中行尾子串的开始下标，加上行尾子串的长度，就得到行尾的下标
            int lineEndindex = str.indexOf(lineEnd) + lineEnd.length();
            // 取下一行并添加到SB中
            sb.append(str, lineStartIndex, lineEndindex);
            // 添加换行符到SB中。
            sb.append("\n\n");
            // 上次的行尾下标，作为下次的行首下标
            lineStartIndex = lineEndindex;
        }
        return sb.toString();
    }
}
