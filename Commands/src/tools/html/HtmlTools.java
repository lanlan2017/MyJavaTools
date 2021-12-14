package tools.html;

import tools.string.StringDeleter;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 生成HTML代码.
 */
public class HtmlTools {
    public String addComment(String code) {
        return "<!--  " + code + " -->";
    }

    /**
     * 将html注释转换成JSP注释
     *
     * @param htmlCode 包含html注释的html代码
     * @return 将html注释转换成JSP注释后的html代码
     */
    public String htmlComment2JspComment(String htmlCode) {

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
        String url = text.replaceAll("[.、]+", "-");
        return "<a href=\"#" + url + "\">" + text + "</a>";
    }

    public String htmlDefault(String tagName, String value) {
        return "<" + tagName + ">" + value + "</" + tagName + ">";

    }

    public String flod(String toFlod) {
        return "<details><summary>展开/折叠</summary>\n\n" + toFlod + "\n\n</details>";
    }


    /**
     * 生成展开折叠块
     *
     * @param toFlod 需要展开折叠的代码
     * @return 展开折叠Html代码
     */
    public String detailsPre(String toFlod) {
        // 将特殊字符转换成HTML转义字符
        toFlod = escape(toFlod);
        return "<details><summary>展开/折叠</summary><pre>\n" + toFlod + "</pre></details>\n";
    }

    /**
     * 将多行字符串转成html无序列表.
     * <p>
     * multiLineToUnorderedList
     * tools.html.HtmlTools#multiLineToUnorderedList
     */
    public String multiLineToUnorderedList(String str) {
        // 如果是markdown无序列表的话
        if (str.startsWith("- ")) {
            // 删除markdown无序列表标志
            str = str.replaceAll("(?m)^- ", "");
        }
        // •在Spring Boot项目中启用Actuator•探索Actuator的端点•自定义Actuator•保护Actuator
        if (str.startsWith("•")) {
            str = str.replace("•", "\n");
        }
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
     *
     * @param code
     * @return
     */
    public String pre(String code) {
        //对特殊字符进行转义处理
        code = escape(code);
        return "<pre>\n" + code + "\n</pre>";
    }

    /**
     * 对html代码进行转义处理
     *
     * @param code 带有特殊字符的HTML代码
     * @return 经过转义处理后的Html代码
     */
    public String escape(String code) {
        code = code.replaceAll("<", "&lt;");
        code = code.replaceAll(">", "&gt;");
        code = code.replaceAll("#", "&#35;");
        return code;
    }

    /**
     * 撤销html转义
     *
     * @param escapedHtml 转义过的HTML代码
     * @return 撤销html转义后的代码
     */
    public String undoEscape(String escapedHtml) {
        escapedHtml = escapedHtml.replace("&lt;", "<");
        escapedHtml = escapedHtml.replace("&gt;", ">");
        escapedHtml = escapedHtml.replace("&#35;", "#");
        escapedHtml = escapedHtml.replace("&amp;", "&");
        return escapedHtml;
    }

    /**
     * 格式化HTML代码,每个html标签占一行
     *
     * @param htmlCode html代码
     * @return 格式化后的html代码
     */
    public String formatToLine(String htmlCode) {
        // 所有的html代码占用一行
        htmlCode = htmlCode.replaceAll("(?m)><", ">\n<");
        // 删除空行
        htmlCode = new StringDeleter().deleteBlankLine(htmlCode);
        return htmlCode;
    }

    /**
     * 清理没有必要的HTML属性
     *
     * @param str 带有疯狂Java联盟版权声明的的代码
     * @return 没有版权声明的代码
     */
    public String clean(String str) {
        // 清理文件声明
        str = str.replaceAll("(?m)<!DOCTYPE html.+(\\n[^>]+)+>", "<!DOCTYPE html>");
        // 精简html标签
        str = str.replaceAll("(?m)<html.+>", "<html>");
        // 删除版权
        str = str.replaceAll("(?m)^[ \\t]+<meta name=\"website\" content=\"http://www\\.crazyit\\.org\" />$\\n", "");
        str = str.replace("<%--\n" + "网站: <a href=\"http://www.crazyit.org\">疯狂Java联盟</a>\n" + "author  yeeku.H.lee kongyeeku@163.com\n" + "version  1.0\n" + "Copyright (C), 2001-2018, yeeku.H.Lee\n" + "This program is protected by copyright laws.\n" + "Program Name:\n" + "Date: \n" + "--%>\n", "");
        return str;
    }

    /**
     * html代码转txt
     *
     * @param html html代码
     * @return 纯文本
     */
    public String toTxt(String html) {
        // html="<mark>settings设置</mark>";
        String txtRegex = "\\<([a-z]+)\\>(.+?)\\<\\/\\1\\>";
        html = html.replaceAll(txtRegex, "$2");
        return html;
    }

    /**
     * 生成div标签
     *
     * @param str div标签中的内容
     * @return html的div标签
     */
    public String div(String str) {
        return "\n<div>" + str + "</div>\n";
    }

    /**
     * 生成带 虚线边框 的div标签
     *
     * @param str div标签中的内容
     * @return
     */
    public String divBorderDashed(String str) {
        return "\n<div style=\"border-style:dashed;\">" + str + "</div>\n";
    }

    /**
     * 生成带 实线边框 的div标签
     *
     * @param str div标签中的内容
     * @return
     */
    public String divBorderSolid(String str) {
        return "\n<div style=\"border-style:solid;\">" + str + "</div>\n";
    }

    /**
     * 生成带 点线边框 边框的div标签
     *
     * @param str div标签中的内容
     * @return
     */
    public String divBorderDotted(String str) {
        return "\n<div style=\"border-style:dotted;\">" + str + "</div>\n";
    }

    /**
     * 生成带 两个边框 边框的div标签
     *
     * @param str div标签中的内容
     * @return
     */
    public String divBorderDouble(String str) {
        return "\n<div style=\"border-style:double;\">" + str + "</div>\n";
    }

    /**
     * 生成带 3D沟槽边框 边框的div标签
     *
     * @param str div标签中的内容
     * @return
     */
    public String divBorderGroove(String str) {
        return "\n<div style=\"border-style:groove;\">" + str + "</div>\n";
    }

    /**
     * 生成带 3D脊边框 边框的div标签
     *
     * @param str div标签中的内容
     * @return
     */
    public String divBorderGidge(String str) {
        return "\n<div style=\"border-style:ridge;\">" + str + "</div>\n";
    }

    /**
     * 生成带 3D的嵌入边框 边框的div标签
     *
     * @param str div标签中的内容
     * @return
     */
    public String divBorderInset(String str) {
        return "\n<div style=\"border-style:inset;\">" + str + "</div>\n";
    }

    /**
     * 生成带 3D突出边框 边框的div标签
     *
     * @param str div标签中的内容
     * @return
     */
    public String divBorderOutset(String str) {
        return "\n<div style=\"border-style:outset;\">" + str + "</div>\n";
    }

    /**
     * 返回<code>&lt;script&gt;&lt;/script&gt;</code>标签
     * @param jsCode
     * @return
     */
    public String script(String jsCode) {
        return "\n<script>\n" + jsCode + "\n</script>\n";
    }
}
