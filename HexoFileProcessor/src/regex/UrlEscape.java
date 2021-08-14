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
        // StringBuilder anchorNameBuff=new StringBuilder(anchorName);
        // anchorNameBuff.replace()
        //System.out.println("anchorName1 =" + anchorName);
        // System.out.println("          原始地址:"+anchorName);
        // 删除头部和尾部的横线
        if (anchorName.endsWith("-")) {
            anchorName = anchorName.substring(0, anchorName.lastIndexOf("-"));
            // System.out.println("          删除尾部:"+anchorName);
        }
        if (anchorName.startsWith("-")) {
            anchorName = anchorName.substring(anchorName.indexOf("-") + 1);
            // System.out.println("          删除头部:"+anchorName);
        }
        if(anchorName.contains("-/")){
            anchorName=anchorName.replaceAll("-/", "/");
            // System.out.println("          删除中间:"+anchorName);
        }
        // System.out.println("生成的地址:"+anchorName);
        // System.out.println("anchorName2 =" + anchorName);
        //// 连字符和美元符 替换成连字符
        // AnchorName = AnchorName.replaceAll(Regex.HyphenDollar.toString(), "");
        //// 连字符和斜线 替换成斜线
        //AnchorName = AnchorName.replaceAll(Regex.HyphenSlash.toString(), "/");
        return anchorName;
    }
}
