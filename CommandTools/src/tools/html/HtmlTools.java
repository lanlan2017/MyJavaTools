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
}
