package blue.commands.demo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 判断是否包含中文和中文标点
 */
public class ToolIsChinese {
    public static void main(String[] args) {
        String str = "创建一个子类，重写父类方法，在调用父类方法，的前面和后面，加入字节的切片。";
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            // System.out.println(ch + "是中文:" + isChinese(ch));
            System.out.println(ch + "是中文:" + isContainChinese(ch));
        }
    }

    //判断字符是否是中文汉字。 from JDK1.7
    public static boolean isChinese(char c) {
        Character.UnicodeScript sc = Character.UnicodeScript.of(c);
        if (sc == Character.UnicodeScript.HAN) {
            return true;
        }
        return false;
    }

    /**
     * 判断字符是否是中文，能校验是否为中文标点符号
     * https://www.qqxiuzi.cn/zh/hanzi-unicode-bianma.php
     *
     * @param str 待校验字符
     * @return 是否为中文
     */
    public static boolean isContainChinese(char str) {
        // https://www.qqxiuzi.cn/zh/hanzi-unicode-bianma.php
        // 中文字符Unicode范文
        // 是否是基本汉字
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(String.valueOf(str));
        // 如果是中文字符
        if (m.find()) {
            // 返回true
            return true;
        }
        // 如果不是中文字符，则判断是否是中文标点符号
        // 中文标点符号Unicode值
        p = Pattern.compile("[\uFF01]|[\uFF0C-\uFF0E]|[\uFF1A-\uFF1B]|[\uFF1F]|[\uFF08-\uFF09]|[\u3001-\u3002]|[\u3010-\u3011]|[\u201C-\u201D]|[\u2013-\u2014]|[\u2018-\u2019]|[\u2026]|[\u3008-\u300F]|[\u3014-\u3015]");
        m = p.matcher(String.valueOf(str));
        // 如果是中文标点符号，则返回true
        return m.find();
    }
}
