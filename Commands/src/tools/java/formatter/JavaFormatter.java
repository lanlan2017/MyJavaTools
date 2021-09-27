package tools.java.formatter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Stack;

public class JavaFormatter {
    public static void main(String[] args) {
        String input = "for (int i = 0; i < codeInOneLine.length(); i++) {char c = codeInOneLine.charAt(i);if (c == '{') {stack.push('{');} else if (c == '}') {stack.pop();}}";
        System.out.println(formatJavaCodeInOneLine(input));
    }

    public static String formatJavaCodeInOneLine(String input) {
        input = input.replaceAll("([{};]) ", "$1");
        // input = input.replaceAll(" ", "}");
        String input2 = toLines(input);
        System.out.println(input2);
        // System.out.println(addTabs(input2));
        return addTabs(input2);
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
}
