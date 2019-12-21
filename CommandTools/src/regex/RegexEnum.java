package regex;

/**
 * 正则表达式枚举.
 */
public enum RegexEnum {
    /**
     * Markdown行内代码段.
     */
    // ToMDCodeInLine("`?([a-zA-Z/\\\\.@<$:'\"{]+:?(?!\\d)[a-zA-Z0-9\\\\/_*.+ ()<>=\\\\\",#'\\[\\]?-]+[a-zA-Z0-9>'\"}](?:\\)|\\(\\))?)`?"),
    ToMDCodeInLine("`?((?:-(?! ))?[a-zA-Z<][a-zA-Z0-9 ():\\_.\\/\\[\\]<>,+=\"?-]*[a-zA-Z0-9);>/.\\*\\]])`?"),
    /**
     * 空行1.
     */
    blankLineRegex1("(?m)(?:\\n|\\r\\n)(?:^\\s*$)"),
    /**
     * 空行2.
     */
    blankLineRegex2("(?m)(?:^\\s*$(?:\\n|\\r\\n))"),
    /**
     * Markdown代码块.
     */
    markdownCodeBlockRegex("```\\w*\\n(.+\\n)+?```"),
    /**
     * 回车换行符.
     */
    CRLF("\\r?\\n"),
    /**
     * 要通过反射运行的方法,格式为:方法全称(参数标记)
     */
    methodToRunByReflection("(\\w+(?:\\.\\w+)+)\\(.*\\)"),
    /**
     * 花括号在方法定义所在行的末尾的getter和setter方法.
     */
    getterSetterDefaultStyle("public\\s+\\w+\\s+(?:set|get)\\w+\\((?:\\,?\\s*\\w+\\s+\\w+)*\\)\\s+\\{(?:(?:\\n|\\r\\n).*?)*?\\}"),
    /**
     * 花括号在方法定义的下一行的getter和setter方法
     */
    getterSetterLikeVC("public\\s+\\w+\\s+(?:set|get)\\w+\\((?:\\,?\\s*\\w+\\s+\\w+)*\\)(\\s+)\\{(?:(?:\\n|\\r\\n).*)*?\\1\\}"),
    /**
     * 从Kindle中复制文本时,kindle添加的引用标记文本.
     */
    KindleReferenceMark("(?m)\\n^$\\n.+Kindle.+"),
    /**
     * Markdown有序列表
     */
    MarkdownOrderListRegex("\\d."),
    /**
     * 多行文本的每一个行开头的位置
     */
    LineStart("(?m)^"),
    /**
     * 图片URL
     */
    ImgURL("https?://.*(?:\\.png|\\.jpg|\\.gif)"),
    /**
     * 匹配没有提示文本的图片:![](https://img-blog.csdnimg.cn/20191101155722848.png)
     */
    MdImgNoAlt("\\!\\[\\]\\((.+)\\)"),
    /**
     * 两个数字之间的位置,这个位置可以是点号
     */
    positionBetweenTwoNumbers("(?<=\\d)\\.?(?=\\d)"),
    /**
     * 匹配PDF中的书签.
     */
    PdfShuQianRegex("([0-9.]+)[ ]?([^0-9].+)"),
    /**
     * 中文后面的空格.
     */
    SpacesAfterChinese("((?<=[\\u4e00-\\u9fa5]))[ ]"),
    /**
     * 全限定方法名
     */
    fullyQualifiedMethodName("\\w+(?:\\.\\w+)+");

    // 正则表达式字符串.
    private String regex;

    RegexEnum(String regex) {
        this.regex = regex;
    }

    /**
     * 获取枚举类对象中保存的正则表达式.
     *
     * @return 枚举类对象中保存的正则表达式字符串.
     */
    @Override
    public String toString() {
        return this.regex;
    }
}