package main;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 测试字符串处理
 */
public class TestStrs {
    public static void main(String[] args) {
        String str = "<table> <thead> <tr> <th>参数</th> <th>全称</th> <th>释义</th> </tr> </thead> <tbody><tr> <td>-b</td> <td>base-dir BASE_DIR</td> <td>新账户的主目录的基目录</td> </tr> <tr> <td>-c</td> <td>comment COMMENT</td> <td>新账户的 GECOS 字段</td> </tr> <tr> <td><strong>-d</strong></td> <td><strong>home-dir HOME_DIR</strong></td> <td><strong>新账户的主目录</strong></td> </tr> <tr> <td>-D</td> <td>defaults</td> <td>显示或更改默认的 useradd 配置</td> </tr> <tr> <td>-e</td> <td>expiredate EXPIRE_DATE</td> <td>新账户的过期日期</td> </tr> <tr> <td>-f</td> <td>inactive INACTIVE</td> <td>新账户的密码不活动期</td> </tr> <tr> <td>-g</td> <td>gid GROUP</td> <td>新账户主组的名称或 ID</td> </tr> <tr> <td>-G</td> <td>groups GROUPS</td> <td>新账户的附加组列表</td> </tr> <tr> <td>-h</td> <td>help</td> <td>显示此帮助信息并推出</td> </tr> <tr> <td>-k</td> <td>skel SKEL_DIR</td> <td>使用此目录作为骨架目录</td> </tr> <tr> <td>-K</td> <td>key KEY=VALUE</td> <td>不使用 /etc/login.defs 中的默认值</td> </tr> <tr> <td>-l</td> <td>no-log-init</td> <td>不要将此用户添加到最近登录和登录失败数据库</td> </tr> <tr> <td><strong>-m</strong></td> <td><strong>create-home</strong></td> <td><strong>创建用户的主目录</strong></td> </tr> <tr> <td><strong>-M</strong></td> <td><strong>no-create-home</strong></td> <td><strong>不创建用户的主目录</strong></td> </tr> <tr> <td>-N</td> <td>no-user-group</td> <td>不创建同名的组</td> </tr> <tr> <td><strong>-o</strong></td> <td><strong>non-unique</strong></td> <td><strong>允许使用重复的 UID 创建用户</strong></td> </tr> <tr> <td>-p</td> <td>password PASSWORD</td> <td>加密后的新账户密码</td> </tr> <tr> <td>-r</td> <td>system</td> <td>创建一个系统账户</td> </tr> <tr> <td>-s</td> <td>shell SHELL</td> <td>新账户的登录 shell</td> </tr> <tr> <td>-u</td> <td>uid UID</td> <td>新账户的用户 ID</td> </tr> <tr> <td>-U</td> <td>user-group</td> <td>创建与用户同名的组</td> </tr> <tr> <td>-Z</td> <td>selinux-user SEUSER</td> <td>为 SELinux 用户映射使用指定 SEUSER</td> </tr> </tbody></table>";
        System.out.println(htmlOneLineTable2MdTable(str));
    }

    /**
     *
     * 将单行的HTML表格代码串，转换成Markdown表格.
     * @param str HTML表格代码串，整个表格的代码都压缩在一行之中。
     * @return Markdown表格
     */
    private static String htmlOneLineTable2MdTable(String str) {
        //删除td标签之间的加粗标签
        str = str.replaceAll("</?strong>", "**");
        //散开标签
        str = str.replaceAll("\\>(?:[ ])?\\<", ">\n<");
        //System.out.println(str);
        return htmlMultiLineTable2MdTable(str);
    }

    /**
     * 把多行的HTML Table代码转换成Markdown表格。
     *
     * @param str HTML Table代码，这些代码每个标签都占据一行。
     * @return Markdown表格
     */
    public static String htmlMultiLineTable2MdTable(String str) {
        //Scanner scanner = new Scanner(Test.class.getResourceAsStream("/tools/html/toMdTable.html"));
        Scanner scanner = new Scanner(str);
        String line;
        boolean isout = false;
        final Pattern thPattern = Pattern.compile("\\<(?:th|td)\\>(.+)\\<\\/(?:th|td)\\>");
        StringBuilder sb = new StringBuilder();
        int numberOfColumns = 0;
        boolean isFirstTr = true;
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            //System.out.println(line);
            if ("<tr>".equals(line)) {
                isout = true;
                continue;
            }
            if (isout) {
                //System.out.println(line);
                Matcher thMatcher = thPattern.matcher(line);
                if (thMatcher.matches()) {
                    //System.out.print("|" + thMatcher.group(1));
                    sb.append("|").append(thMatcher.group(1));
                    if (isFirstTr) {
                        //计算列数
                        numberOfColumns++;
                    }
                }
            }
            if ("</tr>".equals(line)) {
                isout = false;
                //System.out.print("|\n");
                sb.append("|\n");
                // 打印表格对齐方式
                if (isFirstTr) {
                    //System.out.println(numberOfColumns);
                    for (int i = 0; i < numberOfColumns; i++) {
                        //System.out.print("|:---");
                        sb.append("|:---");
                    }
                    //System.out.print("|\n");
                    sb.append("|\n");
                    isFirstTr = false;
                }
                //continue;
            }
        }
        return sb.toString();
    }
}
