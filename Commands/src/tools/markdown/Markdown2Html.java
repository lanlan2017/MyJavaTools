package tools.markdown;

public class Markdown2Html {

    /**
     * markdown行内代码 转成html code标签代码
     *
     * @return html代码。
     */
    public String toHtmlCode(String markdownCode) {
        // markdownCode = "`properties`属性";
        String codeRegex = "\\`(.+?)\\`";
        markdownCode = markdownCode.replaceAll(codeRegex, "<code>$1</code>");

        return markdownCode;
    }

}
