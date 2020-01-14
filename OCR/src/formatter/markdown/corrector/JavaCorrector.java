package formatter.markdown.corrector;

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
        // 所有的行必须以分号结尾
        str = str.replaceAll("[)i;；]$", ";");
        // 删除点号前后的多余空格
        str = str.replaceAll("[ ]*[.][ ]*", ".");
        return str;
    }
}
