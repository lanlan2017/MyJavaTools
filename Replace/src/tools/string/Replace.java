package tools.string;

import clipboard.swing.SystemClipboard;

/**
 * 字符串正则替换器
 */
public class Replace {
    public static void main(String[] args) {
        final int argsLength = args.length;
        if (argsLength > 0) {
            String str = SystemClipboard.getSysClipboardText();
            String result = "";
            if (argsLength == 1) {
                // 将str中的空格替换成第一个命令行参数
                result = replaceSpace(str, args[0]);
            } else if (argsLength == 2) {
                //讲str中匹配第一个命令行参数的子串替换成第二个命令行参数
                result = repalceAll(str, args[0], args[1]);
            }
            System.out.println(result);
            SystemClipboard.setSysClipboardText(result);
        }
    }

    /**
     * 将字符串str中的所有空格全部替换字符串replacement
     *
     * @param str         带有空格的字符串.
     * @param replacement 用来替换字符创str中的空的的字符串.
     * @return 将str中的空格全部替换为replacement得到的字符串.
     */
    private static String replaceSpace(String str, String replacement) {
        return repalceAll(str, " ", replacement);
    }

    /**
     * 将str中匹配regex这个正则表达式的所有子字符串全部替换成replacement这个字符串.
     *
     * @param str         要替换的字符串.
     * @param regex       正则表达式.
     * @param replacement 替换字符.
     * @return 经过正则表达式全部替换后的字符串.
     */
    private static String repalceAll(String str, String regex, String replacement) {
        return str.replaceAll(regex, replacement);
    }
}
