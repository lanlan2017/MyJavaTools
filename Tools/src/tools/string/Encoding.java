package tools.string;

import java.io.UnsupportedEncodingException;

public class Encoding {
    /**
     * 字符串转UTF-8字符串
     *
     * @param str 要转换的字符串
     * @return UTF-8编码的字符串
     */
    private static String toUtf8Str(String str) {
        try {
            //str.getBytes("UTF-8"); 意思是以UTF-8的编码取得字节
            //new String(XXX,"UTF-8"); 意思是以UTF-8的编码生成字符串
            return new String(str.getBytes("UTF-8"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
