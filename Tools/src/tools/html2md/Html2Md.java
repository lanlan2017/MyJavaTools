package tools.html2md;

import tools.string.PrintStr;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Html2Md {
    public static void main(String[] args) {
        // String input = "<ul>  <li>   <span>检查型异常（编译时异常 checked exceptions）</span>   <ul>    <li>     <span>需要在编写程序时声明</span>        </li><li>     <span>需要我们手动的进行捕捉处理，也就是我们用try....catch块进行捕捉处理。或者在方法签名里通过throws子句声明</span>        </li><li>     <span>Throwable、Exception、IOException、ClassNotFoundException、CloneNotSupportedException、EOFException、FileNotFoundException、MalformedURLException、UnknownHostException</span>       </li></ul>    </li><li>   <span>运行异常（runtime exceptions）</span>   <ul>    <li>     <span>只有在编译器在编译运行时才会出现，这些不需要我们手动进行处理</span>        </li><li>     <span>需要程序员自己分析代码决定是否捕获和处理,比如 空指针,被0除...</span>        </li><li>     <span>RuntimeException、ArithmeticException、ClassCastException、IllegalArgumentException、IllegalStateException、IndexOutOfBoundsException、NoSuchElementException、NullPointerException</span>       </li></ul>    </li><li>   <span>Error</span>   <ul>    <li>     <span>属于严重错误，如系统崩溃、虚拟机错误、动态链接失败等，这些错误无法恢复或者不可能捕捉，将导致应用程序中断，Error不需要捕捉。</span>       </li></ul>   </li></ul>";
        String input = "<div class=\"post-content\"><span>Java异常</span> <ul>  <li>   <span>检查型异常（编译时异常 checked exceptions）</span>   <ul>    <li>     <span>需要在编写程序时声明</span>        </li><li>     <span>需要我们手动的进行捕捉处理，也就是我们用try....catch块进行捕捉处理。或者在方法签名里通过throws子句声明</span>        </li><li>     <span>Throwable、Exception、IOException、ClassNotFoundException、CloneNotSupportedException、EOFException、FileNotFoundException、MalformedURLException、UnknownHostException</span>       </li></ul>    </li><li>   <span>运行异常（runtime exceptions）</span>   <ul>    <li>     <span>只有在编译器在编译运行时才会出现，这些不需要我们手动进行处理</span>        </li><li>     <span>需要程序员自己分析代码决定是否捕获和处理,比如 空指针,被0除...</span>        </li><li>     <span>RuntimeException、ArithmeticException、ClassCastException、IllegalArgumentException、IllegalStateException、IndexOutOfBoundsException、NoSuchElementException、NullPointerException</span>       </li></ul>    </li><li>   <span>Error</span>   <ul>    <li>     <span>属于严重错误，如系统崩溃、虚拟机错误、动态链接失败等，这些错误无法恢复或者不可能捕捉，将导致应用程序中断，Error不需要捕捉。</span>       </li></ul>   </li></ul></div>";
        // PrintStr.printStr(input);
        input = input.replaceAll("[ ]+", " ");
        PrintStr.printStr(input);
        System.out.println(htmlUnOrderListToMd(input));

    }

    /**
     * 替换html代码中的无序列表为markdown无序列表
     *
     * @param htmlCode html代码
     */
    public static String htmlUnOrderListToMd(String htmlCode) {
        StringBuffer sb = new StringBuffer(htmlCode.length());
        Matcher htmlTagMatcher = Pattern.compile("\\</?[a-z]+.*?\\>").matcher(htmlCode);
        Stack<String> stack = new Stack<>();
        while (htmlTagMatcher.find()) {
            String tag = htmlTagMatcher.group();
            // 如果是开始标签
            if (tag.matches("\\<[a-z]+.*?\\>")) {
                // 开始标签列表
                String list[] = {"<ul>", "<li>"};
                for (int i = 0; i < list.length; i++) {
                    // 如果是列表元素的话
                    if (list[i].equals(tag)) {
                        // 如果这个列表元素是<li>
                        if ("<li>".equals(tag)) {
                            // <li>替换为`\n- `
                            htmlTagMatcher.appendReplacement(sb, "\n" + tabs(stack.size() - 1) + "- ");
                        }
                        // 如果这个列表元素<ul>
                        else {
                            // 删除这个这个<ul>
                            htmlTagMatcher.appendReplacement(sb, "");
                        }
                        stack.push(list[i]);
                    }
                }
            }
            // 如果是结束标签
            else if (tag.matches("\\</[a-z]+.*?\\>")) {
                // 结束标签列表
                String list[] = {"</ul>", "</li>"};
                for (int i = 0; i < list.length; i++) {
                    // 当前标签是列表中的结束标签时
                    if (list[i].equals(tag)) {
                        // System.out.print("tag = " + tag);
                        // 查看栈顶
                        String top = stack.peek();
                        // System.out.print(",top = " + top);
                        String tagStart = tag.replaceAll("/", "");
                        // System.out.print(",tagStart = " + tagStart);
                        // System.out.println(",size = " + stack.size());
                        // 如果该结束标签对应的开始标签与栈顶元素相同
                        if (tagStart.equals(top)) {
                            // 弹出栈顶的开始标签，表示这条语句结束了
                            stack.pop();
                        }
                        // 匹配的</ul>,</li>元素，替换为空字符串，可以就是删除掉这些元素
                        htmlTagMatcher.appendReplacement(sb, "");
                    }
                }
            }
            // 如果是其他标签
            else {
                // System.out.println("其他标签：" + tag);
                // 其他标签，则不替换
                htmlTagMatcher.appendReplacement(sb, tag);
            }
        }
        htmlTagMatcher.appendTail(sb);
        // PrintStr.printStr(sb.toString());
        return sb.toString();
    }

    public static String tabs(int size) {
        String space = " ";
        String tab = "";
        for (int i = 0; i < size; i++) {
            tab = tab + space;
        }
        // return tab.toString();
        return tab;
    }
}
