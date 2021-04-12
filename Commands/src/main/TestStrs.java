package main;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 测试字符串处理
 */
public class TestStrs {
    public static void main(String[] args) {
        String input = "<details><summary>显示答案/隐藏答案</summary>正确答案: C</details>\n" +
                "\n" +
                "<ul> <li>esc，在vi的插入模式或者底行命令模式下进入命令模式 </li><li>exit，一般用于退出会话式程序或者交互式命令行，如ssh远程连接，切换 shell（Linux中） </li><li>q，用来退出 man 手册查看命令或者配置文件的状态，或者more、less等命令查看文件的状态 </li><li>quit，一般用于图形界面的退出 </li></ul> \n" +
                "以上的exit和quit仅是个人总结，非权威，python中的exit()和quit()都可以用来退出python的交互式shell\n";
        System.out.println(htmlUl2MdUl(input));


        //appendReplacementTest();
    }

    ///**
    // * html 无序列表转markdown无序列表
    // *
    // * @param str 包含html无序列表的文本
    // * @return 包含Markdown无序列表的文本
    // */
    //private static String htmlUl2MdUl(String str) {
    //    Matcher htmlUlM = Pattern.compile("\\<ul\\>(.+?)\\<\\/ul\\>").matcher(str);
    //    Pattern htmlLiP = Pattern.compile("\\<li\\>(.+?)\\<\\/li\\>");
    //    StringBuilder sb = new StringBuilder();
    //    if (htmlUlM.find()) {
    //        String htmlUlLis = htmlUlM.group(1);
    //        //System.out.println(htmlUlLis);
    //        Matcher htmlLiM = htmlLiP.matcher(htmlUlLis);
    //        while (htmlLiM.find()) {
    //            //System.out.print("- "+htmlLiM.group(1)+"\n");
    //            sb.append("- ").append(htmlLiM.group(1)).append("\n");
    //            //htmlLiM.appendReplacement()
    //        }
    //        //System.out.println(sb.toString());
    //    }
    //    return sb.toString();
    //}

    /**
     * 替换HTML无序列表代码为Markdown无序列表
     *
     * @param str 包含html无序列表的文本
     * @return 包含Markdown无序列表的文本
     */
    private static String htmlUl2MdUl(String str) {
        Matcher htmlUlM = Pattern.compile("\\<ul\\>(.+?)\\<\\/ul\\>").matcher(str);
        StringBuffer sb = new StringBuffer();
        while (htmlUlM.find()) {
            htmlUlM.appendReplacement(sb, htmlUlLi2MdUlLi(htmlUlM.group(1)));
        }
        htmlUlM.appendTail(sb);
        return sb.toString();
    }

    /**
     * Html 无序列表代码转成Markdown无序列表。
     * @param htmlUlCode html无序列表代码。
     * @return Markdown无序列表代码。
     */
    private static String htmlUlLi2MdUlLi(String htmlUlCode){
        Matcher m = Pattern.compile("\\<li\\>(.+?)\\<\\/li\\>").matcher(htmlUlCode);
        StringBuilder sb=new StringBuilder(htmlUlCode.length());
        while (m.find()){
            sb.append("- ").append(m.group(1)).append("\n");
        }

        return sb.toString();
    }
    private static void appendReplacementTest() {
        Pattern p = Pattern.compile("cat");
        Matcher m = p.matcher("one cat two cats in the yard");
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(sb, "dog");
        }
        m.appendTail(sb);
        System.out.println(sb.toString());
    }

}
