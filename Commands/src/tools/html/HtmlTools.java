package tools.html;

/**
 * 生成HTML代码.
 */
public class HtmlTools {
    public String addComment(String code) {
        return "<!--  " + code + " -->";
    }

    public String linkCss(String cssUrl) {
        return "<link href=\"" + cssUrl + "\" rel=\"stylesheet\" type=\"text/css\" />";
    }

    public String importJS(String jsUrl) {
        return "<script type=\"text/javascript\" src=\"" + jsUrl + "\"></script>";
    }

    public String a(String url) {
        if (url.matches(".+?[ ]+.+")) {
            String[] UrlText = url.split("[ ]+");
            return "<a href=\"" + UrlText[0] + "\">" + UrlText[1] + "</a>";
        }
        return "<a href=\"" + url + "\"></a>";
    }

    public String htmlDefault(String tagName, String value) {
        return "<" + tagName + ">" + value + "</" + tagName + ">";

    }

    public String flod(String toFlod) {
        return "<details><summary>展开/折叠</summary>\n\n" + toFlod + "\n\n</details>";
    }

    /**
     * 将多行字符串转成html无序列表.
     */
    public String unorderedList(String str) {
        String liStart = "<li>";
        String liEnd = "</li>";
        String ulStart = "<ul>";
        String ulEnd = "</ul>";
        // 按行分隔
        String[] lines = str.split("\n");
        // 缓存字符串的最佳长度:字符串的长度减去换行符的个数,加上li的个数
        int optimalLength = str.length() - (lines.length - 1) + lines.length * (liStart.length() + liEnd.length());
        StringBuilder sb = new StringBuilder(optimalLength);
        //StringBuilder sb = new StringBuilder();
        for (String line : lines) {
            //System.out.print(liStart + line + liEnd);
            sb.append(liStart).append(line).append(liEnd);
        }
        //System.out.println(sb.length());
        //System.out.println(optimalLength);
        return ulStart + sb.toString() + ulEnd;
    }
}
