package tools.url;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * 学习强国App工具。
 */
public class XueXiQiangGuoTools {
    public static void main(String[] args) {
        String urlStr = "https://xxqh.xuexiqh.cn/pdfPreview?url=https%3A%2F%2Fdjimg.gobestsoft.cn%2F19516436b7205a2c734eb29bcd8a0a3c1637112995737.pdf";
        new XueXiQiangGuoTools().pdfAddress(urlStr);
    }

    /**
     * 获取学习强国pdf课件地址。
     *
     * @param pdfURL 在学习强国阅读pdf课件时，浏览器地址栏上的地址
     */
    public String pdfAddress(String pdfURL) {
        String flag = "?url=";
        String url = "解析失败";
        if (pdfURL.contains(flag)) {
            pdfURL = pdfURL.substring(pdfURL.indexOf(flag) + flag.length());
            System.out.println("pdfURL = " + pdfURL);
            try {
                url = URLDecoder.decode(pdfURL, "UTF-8");
                System.out.println("url = " + url);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return url;
    }
}
