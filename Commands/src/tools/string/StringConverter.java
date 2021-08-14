package tools.string;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串转换器.
 */
public class StringConverter {

    /**
     * 全部变成大写.
     *
     * @param input 需要转换的字符串.
     * @return 全部变成大写后的字符串.
     */
    public String toUpperCase(String input) {
        return input.toUpperCase();
    }

    /**
     * 全部变成小写.
     *
     * @param input 需要转换的字符串.
     * @return 全部变成小写后的字符串.
     */
    public String toLowerCase(String input) {
        return input.toLowerCase();
    }

    /**
     * 首字母变大写.
     *
     * @param input 需要转换的字符串.
     * @return 首字母小写后的字符串.
     */
    public static String toUpperCaseFirst(String input) {
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }

    /**
     * 首字母小写.
     *
     * @param input 需要转换的字符串.
     * @return 首字母小写后的字符串.
     */
    public static String toLowerCaseFirst(String input) {
        return input.substring(0, 1).toLowerCase() + input.substring(1);
    }

    /**
     * 换换成驼峰命名法的java包名.
     *
     * @param str 以空格分隔的一串单词(翻译来的).
     * @return 合法的java包名.
     */
    public String toCameCasePackgeName(String str) {
        return str.toLowerCase().replace(" ", ".");
    }

    /**
     * 转成驼峰命名法的Java类名.
     *
     * @param str 需要转换的java类名,一般是通过翻译得到的字符串.这些字符串一般以空格作为分隔符.
     * @return 驼峰命名法java类名。
     */
    public String toCamelCaseClassName(String str) {
        if (str.matches("^\\w+(?:\\s+\\w+)+$")) {
            String[] words = str.split("\\s");
            StringBuilder sb = new StringBuilder(str.length());
            for (int i = 0; i < words.length; i++) {
                // 先全部小写,然后首字母大写
                sb.append(toUpperCaseFirst(words[i].toLowerCase()));
            }
            return sb.toString();
        }
        return "参数错误!";
    }


    /**
     * 生成驼峰命名法java方法名,java变量名:第一个单词的首字母小写,其他单词的首字母大写.
     *
     * @param str 以空白符分隔的单词.
     * @return 驼峰命名法java方法名.
     */
    public String toCameCaseMethodName(String str) {
        if (str.matches("^\\w+(?:\\s+\\w+)+$")) {
            String[] words = str.split("\\s");
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < words.length; i++) {
                if (i == 0) {
                    // 第一个单词全部小写
                    sb.append(words[i].toLowerCase());
                } else {
                    // 其他单词首字母大写
                    sb.append(toUpperCaseFirst(words[i].toLowerCase()));
                }
            }
            return sb.toString();
        }
        return "参数错误!";
    }

    /**
     * 取消驼峰命名法.
     *
     * @param str 驼峰命名法字符串
     * @return 正常字符串.
     */
    public String cancelCameCase(String str) {
        return str.replaceAll("(?<=[a-zA-Z])(?=[A-Z])", " ");
    }

    public String convertFilePath(String path) {
        Pattern linuxPath = Pattern.compile("/(.+?)(/.+)");
        Matcher linuxMatcher = linuxPath.matcher(path);
        Pattern windowsPath = Pattern.compile("([a-zA-Z]):(\\\\.+)");
        Matcher windowsMatcher = windowsPath.matcher(path);
        // /e/dev2/idea_workspace/MyJavaTools
        if (linuxMatcher.matches()) {
            String driveLetter = linuxMatcher.group(1);
            String relativePath = linuxMatcher.group(2).replace("/", "\\");
            return driveLetter + ":" + relativePath;
        } else if (windowsMatcher.matches()) {
            String driveLetter = windowsMatcher.group(1);
            String relativePath = windowsMatcher.group(2).replace("\\", "/");
            return "/" + driveLetter + relativePath;
        }
        return "地址格式错误:" + path;
    }

    public static void main(String[] args) {
        StringConverter stringConverter = new StringConverter();
        String str = "Git command generator";
        System.out.println(stringConverter.toCamelCaseClassName(str));
        System.out.println(stringConverter.toCameCaseMethodName(str));
    }

    /**
     * 将中文翻译得到的单词转成相对的URL
     *
     * @param str 翻译得到的单词.
     * @return 相对的URL路径
     */
    public String translateToURL(String str) {
        StringBuilder sb = new StringBuilder(str.length());
        String[] dirs = str.split("[ ]{2,}");
        for (String dir : dirs) {
            if (dir.contains(" ")) {
                String[] names = dir.split(" ");
                for (String name : names) {
                    sb.append(toUpperCaseFirst(name));
                }
            } else {
                sb.append(dir);
            }
            sb.append("/");
        }
        return sb.toString();
    }

    /**
     * 生成全限定类名
     *
     * @param eclipsePath 文件路径字符串
     * @return 全限定类名
     */
    public String fullyQualifiedClassName(String eclipsePath) {
        eclipsePath = eclipsePath.substring(eclipsePath.indexOf("/src/") + "/src/".length(), eclipsePath.lastIndexOf("."));
        eclipsePath = eclipsePath.replace("/", ".");
        //System.out.println(eclipsePath);
        return eclipsePath;
    }

    /**
     * 生成全限定xml文件名
     *
     * @param eclipsePath 文件路径字符串
     * @return 全限定文件名
     */
    public String fullyQualifiedXmlName(String eclipsePath) {
        eclipsePath = eclipsePath.substring(eclipsePath.indexOf("/src/") + "/src/".length());
        //eclipsePath = eclipsePath.replace("/", ".");
        //System.out.println(eclipsePath);
        return eclipsePath;
    }

    /**
     * 格式化微博内容
     *
     * @param weiboContent 微博博文
     * @return 格式化后的字符串
     */
    public String formatWeibo(String weiboContent) {
        weiboContent = weiboContent.replaceAll("\\【?\\#(.+)\\#\\】?", "$1\n\n");
        weiboContent = "\n\n\n" + weiboContent;
        return weiboContent;
    }

    /**
     * 生成格式化的日期
     */
    public String dateStr() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        return format.format(new Date());
    }
}
