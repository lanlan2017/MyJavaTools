package tools.html;

import tools.string.PrintStr;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlListToMd {
    public static void main(String[] args) {
        // TestHtmlUnOrderListToMd();
        String input = "<ol><li> 初始标记(STW initial mark) </li><li> 并发标记(Concurrent marking) </li><li> 并发预清理(Concurrent precleaning) </li><li> 重新标记(STW remark) </li><li> 并发清理(Concurrent sweeping) </li><li> 并发重置(Concurrent reset) </li></ol> ";
        System.out.println(htmlOrderList2Md(input));
    }

    private static void htmlUnOrderListToMdTest() {
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
     * @return 替换后的字符串
     */
    public static String htmlUnOrderListToMd(String htmlCode) {
        StringBuffer sb = new StringBuffer(htmlCode.length());
        Matcher htmlTagMatcher = Pattern.compile("\\</?[a-z]+.*?\\>").matcher(htmlCode);
        Stack<String> stack = new Stack<>();
        while (htmlTagMatcher.find()) {
            // 取出标签
            String tag = htmlTagMatcher.group();
            // 如果是开始标签
            if (tag.matches("\\<[a-z]+.*?\\>")) {
                // 开始标签列表
                String startTags[] = {"<ul>", "<li>"};
                for (int i = 0; i < startTags.length; i++) {
                    // 如果是列表元素的话
                    if (startTags[i].equals(tag)) {
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
                        // 把开始标签压栈
                        stack.push(startTags[i]);
                    }
                }
            }
            // 如果是结束标签
            else if (tag.matches("\\</[a-z]+.*?\\>")) {
                // 结束标签列表
                String endTags[] = {"</ul>", "</li>"};
                for (int i = 0; i < endTags.length; i++) {
                    // 当前标签是列表中的结束标签时
                    if (endTags[i].equals(tag)) {
                        // 查看栈顶的开始标签
                        String top = stack.peek();
                        // 生成该结束标签对应的开始标签，结束标签</ul>，删掉/就得到它的开始标签<ul>
                        String tagStart = tag.replaceAll("/", "");
                        // 如果该结束标签对应的开始标签与栈顶元素相同
                        if (tagStart.equals(top)) {
                            // 弹出栈顶的开始标签，表示这条html标签结束了
                            stack.pop();
                        }
                        // 匹配的</ul>,</li>元素，替换为空字符串，可以就是从html中删除掉这些结束标签
                        htmlTagMatcher.appendReplacement(sb, "");
                    }
                }
            }
            // 如果是其他标签
            else {
                // 其他标签，则不替换
                htmlTagMatcher.appendReplacement(sb, tag);
            }
        }
        // 保留剩下的没有经过处理的字符
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

    public static String htmlOrderList2Md(String htmlCode) {
        StringBuffer sb = new StringBuffer(htmlCode.length());
        Matcher htmlTagMatcher = Pattern.compile("\\</?[a-z]+.*?\\>").matcher(htmlCode);
        Stack<String> stack = new Stack<>();
        int lineCounter = 0;
        while (htmlTagMatcher.find()) {
            // 取出标签
            String tag = htmlTagMatcher.group();
            // 如果是开始标签
            if (tag.matches("\\<[a-z]+.*?\\>")) {
                // 开始标签列表
                String startTags[] = {"<ol>", "<li>"};
                for (int i = 0; i < startTags.length; i++) {
                    if (startTags[i].equals(tag)) {
                        // 如果这个列表元素是<li>
                        if ("<li>".equals(tag)) {
                            // <li>替换为`\n- `
                            htmlTagMatcher.appendReplacement(sb, "\n" + tabs(stack.size() - 1) + (++lineCounter) + ". ");
                        }
                        // 如果这个列表元素<ol>
                        else {
                            // 遇到<ol>元素表示无序列表开始，把行计数器清零0。
                            lineCounter = 0;
                            // 删除这个这个<ol>
                            htmlTagMatcher.appendReplacement(sb, "");
                        }
                        // 把开始标签压栈
                        stack.push(startTags[i]);
                    }
                }
            }
            // 如果是结束标签
            else if (tag.matches("\\</[a-z]+.*?\\>")) {
                // 结束标签列表
                String endTags[] = {"</ol>", "</li>"};
                for (int i = 0; i < endTags.length; i++) {
                    if (endTags[i].equals(tag)) {
                        // 查看栈顶的开始标签
                        String top = stack.peek();
                        // 生成该结束标签对应的开始标签，结束标签</ul>，删掉/就得到它的开始标签<ul>
                        String tagStart = tag.replaceAll("/", "");
                        // 如果该结束标签对应的开始标签与栈顶元素相同
                        if (tagStart.equals(top)) {
                            // 弹出栈顶的开始标签，表示这条html标签结束了
                            stack.pop();
                        }
                        //
                        if ("</ol>".equals(tag)) {
                            // 如果是</ol>,则替换为两个换行符
                            htmlTagMatcher.appendReplacement(sb, "\n\n");
                        }else {
                            //如果是</li>,则删除掉该元素
                            htmlTagMatcher.appendReplacement(sb, "");
                        }
                    }
                }
            }
            // 如果是其他标签
            else {
                // 其他标签，则不替换
                htmlTagMatcher.appendReplacement(sb, tag);
            }

        }
        // 保留剩下的没有经过处理的字符
        htmlTagMatcher.appendTail(sb);
        // PrintStr.printStr(sb.toString());
        return sb.toString();
        // return "";
    }
}
