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

    public String toHtmlImage(String markdownImg) {
        //![image-20220419154248077](https://raw.githubusercontent.com/lanlan2017/images/master/Blog/2022/04/20220419154248.png)
        // System.out.println("markdownImg = " + markdownImg);
        String alt = markdownImg.substring(2, markdownImg.indexOf("]("));
        String url = markdownImg.substring(markdownImg.indexOf("](") + 2, markdownImg.lastIndexOf(")"));
        // System.out.println("alt = " + alt);
        // System.out.println("url = " + url);
        //<img src="https://raw.githubusercontent.com/lanlan2017/images/master/Blog/2022/04/20220419154205.png" alt="image-20220419154204870" width="50%">
        return "<img src=\""+url+"\" alt=\""+alt+"\" width=\"100%\">";
    }

}
