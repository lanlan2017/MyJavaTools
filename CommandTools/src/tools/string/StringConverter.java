package tools.string;

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
    public String toUppperCase(String input) {
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

    public static void main(String[] args) {
        StringConverter stringConverter = new StringConverter();
        String str = "Git command generator";
        System.out.println(stringConverter.toCamelCaseClassName(str));
        System.out.println(stringConverter.toCameCaseMethodName(str));
    }
}
