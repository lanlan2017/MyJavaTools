package regex;

/**
 * 修复URL,对URL中的一些特殊字符进行转义
 */
public class UrlEscape {
    /**
     * 修复URL.
     *
     * @param headerName
     * @return
     */
    public static String escapeURL(String headerName) {
        String AnchorName;
        AnchorName = headerName.replace("\\", "/");
        // 一些特殊字符 替换成连字符
        AnchorName = AnchorName.replaceAll(Regex.ToBeHyphen.toString(), "-");
        // 连字符和美元符 替换成连字符
        AnchorName = AnchorName.replaceAll(Regex.HyphenDollar.toString(), "");
        // 连字符和斜线 替换成斜线
        AnchorName = AnchorName.replaceAll(Regex.HyphenSlash.toString(), "/");
        return AnchorName;
    }
}
