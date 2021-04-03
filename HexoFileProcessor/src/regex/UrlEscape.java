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
        //System.out.println("headerName =" + headerName);
        String anchorName;
        anchorName = headerName.replace("\\", "/");
        // 一些特殊字符 替换成连字符'-'
        anchorName = anchorName.replaceAll(Regex.ToBeHyphen.toString(), "-");
        //System.out.println("anchorName1 =" + anchorName);

        if (anchorName.endsWith("-")) {
            anchorName = anchorName.substring(0, anchorName.lastIndexOf("-"));
        }
        if (anchorName.startsWith("-")) {
            anchorName = anchorName.substring(anchorName.indexOf("-") + 1);
        }
        //System.out.println("anchorName2 =" + anchorName);
        //// 连字符和美元符 替换成连字符
        //AnchorName = AnchorName.replaceAll(Regex.HyphenDollar.toString(), "");
        //// 连字符和斜线 替换成斜线
        //AnchorName = AnchorName.replaceAll(Regex.HyphenSlash.toString(), "/");
        return anchorName;
    }
}
