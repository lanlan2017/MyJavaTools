package tools.java.formatter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaFormatter {
    public static void main(String[] args) {
        // String input = "for (int i = 0; i < codeInOneLine.length(); i++) {char c = codeInOneLine.charAt(i);if (c == '{') {stack.push('{');} else if (c == '}') {stack.pop();}}";
        // System.out.println(formatJavaCodeInOneLine(input));

        // String str="/*** 被动使用类字段演示三： * 常量在编译阶段会存入调用类的常量池中，本质上没有直接引用到定义常量的类，因此不会触发定义常量的 类的初始化 **/";
        // String str = "/*** 非主动使用类字段演示 **/";
        String str = "/*** 类加载器与instanceof关键字演示 ** @author zzm */";
        if (str.matches("^/\\*\\*\\*.+\\*/$")) {
            str = formatJavaDocStr(str);
        }
        System.out.println(str);
    }

    public static String formatJavaCodeInOneLine(String input) {
        input = input.replaceAll("([{};]) ", "$1");
        // input = input.replaceAll(" ", "}");
        // 变成多行代码
        String input2 = toLines(input);
        // System.out.println(input2);
        // System.out.println(addTabs(input2));
        // 在每行前面添加换行符
        String javaCode = addTabs(input2);
        //匿名内部类有大括号后面的分号放在一行
        javaCode = javaCode.replaceAll("(?m)^([ ]+)\\}$\\n\\1;", "$1};");
        // 恢复javaDoc
        javaCode = formatAllJavaDoc(javaCode);
        return javaCode;
    }


    /**
     * 在多行代码前面加上制表符
     *
     * @param multilineCode
     */
    private static String addTabs(String multilineCode) {
        Stack<String> daKuoHaoStack = new Stack<>();
        BufferedReader reader = new BufferedReader(new StringReader(multilineCode));
        StringBuilder sb = new StringBuilder();
        String line;
        boolean isFirstLine = true;
        try {
            while ((line = reader.readLine()) != null) {
                if (line.endsWith("}")) {
                    daKuoHaoStack.pop();
                }
                if (isFirstLine) {
                    // sb.append(tabs(daKuoHaoStack.size())).append(line);
                    isFirstLine = false;
                } else {
                    sb.append("\n");
                }
                sb.append(tabs(daKuoHaoStack.size())).append(line);
                if (line.endsWith("{")) {
                    daKuoHaoStack.push("{");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * 转换成多行
     *
     * @param javacode java代码
     * @return 多行的java代码
     */
    private static String toLines(String javacode) {
        StringBuilder stringBuilder = new StringBuilder((int) (javacode.length() * 1.5));
        Stack<Character> xiaoKuoHaoStack = new Stack<>();
        Stack<Character> daKuoHaoStack = new Stack<>();
        for (int i = 0; i < javacode.length(); i++) {
            char ch = javacode.charAt(i);
            if (ch == '(') {
                // System.out.println("小括号开始" + ",i=" + i);
                xiaoKuoHaoStack.push(ch);
            } else if (ch == ')') {
                // System.out.println("小括号结束" + ",i=" + i);
                xiaoKuoHaoStack.pop();
            }

            if (xiaoKuoHaoStack.size() == 0) {
                stringBuilder.append(ch);
                if (ch == '{') {
                    daKuoHaoStack.push(ch);
                    // stringBuilder.append("__LF__");
                    // stringBuilder.append("__TAB+" + daKuoHaoStack.size() + "__");
                    stringBuilder.append("\n");
                } else if (ch == '}') {
                    daKuoHaoStack.pop();
                    stringBuilder.append("\n");
                } else if (ch == ';') {
                    // stringBuilder.append("__LF__");
                    stringBuilder.append("\n");
                }
            } else {
                stringBuilder.append(ch);
            }
        }
        String input2 = stringBuilder.toString();
        return input2;
    }

    private static String tabs(int num) {
        // final String tab = "====";
        final String tab = "    ";
        StringBuilder tabSB = new StringBuilder(tab.length() * num);
        for (int i = 0; i < num; i++) {
            tabSB.append(tab);
        }
        return tabSB.toString();
    }

    /**
     * 格式java代码中的所有的JavaDoc
     *
     * @param codeInOneLine 包含杂乱的JavaDoc的java代码
     * @return 恢复JavaDoc后的java代码。
     */
    private static String formatAllJavaDoc(String codeInOneLine) {
        Matcher matcher = Pattern.compile("(?m)^/\\*\\*\\*.+\\*/$").matcher(codeInOneLine);
        // Matcher matcher = Pattern.compile("/\\*\\*\\*.+\\*/").matcher(codeInOneLine);
        if (matcher.find()) {
            // PrintStr.printStr("哈哈");
            // if条件已经匹配过一次了，重置匹配器，从头开始开始匹配。
            matcher.reset();
            int length = codeInOneLine.length();
            StringBuffer sb = new StringBuffer(length + length >> 2);
            // 如果存在没有格式化的JavaDoc
            while (matcher.find()) {
                String javaDoc = matcher.group(0);
                matcher.appendReplacement(sb, formatJavaDocStr(javaDoc));
            }
            matcher.appendTail(sb);
            // 使用格式化JavaDoc后的代码作为处理结果
            codeInOneLine = sb.toString();
        }
        return codeInOneLine;
    }

    /**
     * 格式化JavaDoc
     *
     * @param javaDocStr 错误的写在一行中的JavaDoc（从PDF中复制来的JavaDoc）
     * @return 修复后的javaDoc
     */
    private static String formatJavaDocStr(String javaDocStr) {
        // 替换JavaDoc的开始标记
        javaDocStr = javaDocStr.replaceFirst("^/\\*\\*", "__JavaDoc_Start__");
        // 替换JavaDoc的开始结束
        javaDocStr = javaDocStr.replaceAll("\\*\\*/$", "__JavaDoc_End__");
        // 分行
        javaDocStr = javaDocStr.replaceAll("\\* ?", "\n $0");
        // 恢复JavaDoc的开始标记
        javaDocStr = javaDocStr.replace("__JavaDoc_Start__", "/**");
        // 恢复JavaDoc的结束标记
        javaDocStr = javaDocStr.replace("__JavaDoc_End__", "\n */");
        return javaDocStr;
    }
}

