package html2md.suanfa;

import java.util.Stack;

public class KuoHaoPiPie {
    public static void main(String[] args) {
        String string = "{123[456(789)]456}789";

        boolean isMatch = false;
        isMatch = isMatch(string);
        System.out.println(isMatch);
    }

    /**
     * 判断一个括号是否匹配
     *
     * @param string 包含括号的字符串。
     * @return 如果括号都匹配的话，返回true.
     */
    private static boolean isMatch(String string) {
        Stack<Character> stack = new Stack<Character>();
        int youNum;
        for (int i = 0; i < string.length(); i++) {
            char si = string.charAt(i);
            // 如果是左括号的话
            if (getZuoCode(si) >= 0) {
                // 入栈
                stack.push(si);
            }
            // 如果该字符右括号的话
            if ((youNum = getYouCode(si)) >= 0) {
                // 获取栈顶的左括号的编号
                int zuoNum = getZuoCode(stack.peek());
                // 如果当前的右括号和栈顶的左括号的编号不相等
                if (youNum != zuoNum) {
                    // 这说明这两个括号不匹配，返回false
                    // isMatch = false;
                    return false;
                }
                // 弹出栈顶匹配的左括号
                stack.pop();
            }
        }
        // 如果运行完毕后，栈空，则说明全部的括号都匹配
        return stack.isEmpty();
    }

    /**
     * 判断该字符是不是右括号。
     *
     * @param x 字符
     * @return 如果是右括号，则返回右括号的编号，<ul><li>右大括号<code>}</code>返回0，</li><li>右中括号<code>]</code>返回1，</li><li>右小括号<code>)</code>返回2，</li></ul>
     * 其他字符返回-1
     */
    private static int getYouCode(char x) {
        char[] youkuohao = {'}', ']', ')'};
        int isYou = -1;
        for (int i = 0; i < youkuohao.length; i++) {
            if (x == youkuohao[i]) {
                isYou = i;
            }
        }
        return isYou;
    }

    /**
     * 判断该字符是不是左括号。
     *
     * @param x
     * @return 如果是左括号，则返回左括号对应的编号：
     * <ul><li>左大括号<code>{</code>返回0,</li><li>左中括号<code>[</code>返回1，</li><li>左小括号<code>(</code>返回2。</li></ul>
     * 如果是其他字符，则返回-1.
     */
    private static int getZuoCode(char x) {
        char[] zuokuohao = {'{', '[', '('};
        int isYou = -1;
        for (int i = 0; i < zuokuohao.length; i++) {
            if (x == zuokuohao[i]) {
                isYou = i;
            }
        }
        return isYou;
    }
}
