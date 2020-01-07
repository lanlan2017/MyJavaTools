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
}
