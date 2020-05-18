package tools.html;

import tools.string.StringDeleter;

/**
 * 生成HTML代码.
 */
public class HtmlTools {
    public String addComment(String code) {
        return "<!--  " + code + " -->";
    }

    /**
     * 将html注释转换成JSP注释
     * @param htmlCode 包含html注释的html代码
     * @return 将html注释转换成JSP注释后的html代码
     */
    public String htmlComment2JspComment(String htmlCode){

        return htmlCode.replaceAll("(?m)<!--[ ]*(.+)[ ]*-->", "<%-- $1 --%>");
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
    public String anchor(String text) {
        String url=text.replaceAll("[.、]+", "-");
        return "<a href=\"#" + url + "\">"+text+"</a>";
    }

    public String htmlDefault(String tagName, String value) {
        return "<" + tagName + ">" + value + "</" + tagName + ">";

    }

    public String flod(String toFlod) {
        return "<details><summary>展开/折叠</summary>\n\n" + toFlod + "\n\n</details>";
    }
    public String detailsPre(String toFlod) {
        return "<details><summary>展开/折叠</summary><pre>\n" + toFlod + "</pre></details>\n";
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

    /**
     * 生成<code><pre></pre>代码块</code>
     * @param code
     * @return
     */
    public String pre(String code){

        return "<pre>\n"+code+"\n</pre>";
    }

    /**
     * 对html代码进行转义处理
     * @param code
     * @return
     */
    public String escape(String code){
        code=code.replaceAll("<", "&lt;");
        code=code.replaceAll(">", "&gt;");
        return code;
    }

    /**
     * 格式化HTML代码,每个html标签占一行
     * @param htmlCode html代码
     * @return 格式化后的html代码
     */
    public String formatToLine(String htmlCode){
        // 所有的html代码占用一行
        htmlCode=htmlCode.replaceAll("(?m)><", ">\n<");
        // 删除空行
        htmlCode=new StringDeleter().deleteBlankLine(htmlCode);
        return htmlCode;
    }

}
