package blue.ocr3.formatter.markdown.corrector;

/**
 * Java代码纠错器.
 */
public class JavaCorrector {

    /**
     * 纠正文字识别的java代码
     *
     * @param str 文字识别得到的java代码.
     * @return 纠正后的Java代码
     */
    public static String correctJava(String str) {
        System.out.println("-------------------------------------------");
        System.out.println(str);
        System.out.println("-------------------------------------------");
        // 所有的行必须以分号结尾
        str = str.replaceAll("(?m)[:i]$", ";");
        // 在方法后面加上英文分号,方法的标准为")结尾"
        str = str.replaceAll("(?m)(^(?!(?:catch|try)).*[)]$)", "$1;");
        // 删除点号前后的多余空格
        str = str.replaceAll("[ ]*[.][ ]*", ".");
        // 恢复单行注释
        str = str.replaceAll("(?m)^//?", "//");
        // 在new创建对象的地方添加上等号
        str = str.replaceAll("(\\w+)[ ]*=?[ ]*(new)", "$1 = $2");
        // 在方法调用的地方添加上点号
        str = str.replaceAll("(\\w+) (\\w+\\(\\))", "$1.$2");

        return str;
    }
}
