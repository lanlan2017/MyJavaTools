package tools.web;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLEncode {
    /**
     * 编码为浏览器可以直接访问的URL。
     *
     * @param s 要编码的字符串
     * @return 浏览器地址栏可以识别的字符串
     */
    public static String encodeToWebURL(String s) {
        String result;

        try {
            result = URLEncoder.encode(s, "UTF-8")
                    .replaceAll("\\+", "%20")
                    .replaceAll("\\%21", "!")
                    .replaceAll("\\%27", "'")
                    .replaceAll("\\%28", "(")
                    .replaceAll("\\%29", ")")
                    .replaceAll("\\%7E", "~");
        } catch (UnsupportedEncodingException e) {
            result = s;
        }

        return result;
    }

    /**
     * 只对中文进行编码。
     *
     * @param str     被替换的字符串
     * @param charset 字符集
     * @return 浏览器地址栏可识别的中文字符
     */
    private static String encodeChineseOnly(String str, String charset) {
        //匹配中文和空格的正则表达式
        String zhPattern = "[\u4e00-\u9fa5]+";
        Pattern p = Pattern.compile(zhPattern);
        Matcher m = p.matcher(str);
        StringBuffer b = new StringBuffer(str.length()*2);
        while (m.find()) {
            try {
                m.appendReplacement(b, URLEncoder.encode(m.group(0), charset));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        m.appendTail(b);
        return b.toString();
    }
}
