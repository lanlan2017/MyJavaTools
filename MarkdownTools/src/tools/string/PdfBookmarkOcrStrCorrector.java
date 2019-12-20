package tools.string;

import regex.RegexEnum;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 将文字识别得到pdf书签字符串,转成正确的PDF书签字符串.
 */

public class PdfBookmarkOcrStrCorrector {
    /**
     * 格式化书签为<code>x.x.x 小结内容</code>形式.
     *
     * @param pdfBookmarkOcrStr 文字识别得到的书签字符串.
     * @return 正确的PDF书签字符串.
     */
    public String correctBookmark111(String pdfBookmarkOcrStr) {
        Pattern pattern = Pattern.compile(RegexEnum.PdfShuQianRegex.getRegex());
        Matcher matcher = pattern.matcher(pdfBookmarkOcrStr);
        String group1;
        String group2;
        if (matcher.matches()) {
            // 获取匹配到的一个分组
            group1 = matcher.group(1);
            // 在两个数之间的位置都加上点号
            group1 = group1.replaceAll(RegexEnum.positionBetweenTwoNumbers.getRegex(), ".");
            group2 = matcher.group(2);
            // 删除前面有中文的空格.
            // final String SpacesAfterChinese = "((?<=[\\u4e00-\\u9fa5]))[ ]";
            group2 = group2.replaceAll(RegexEnum.SpacesAfterChinese.getRegex(), "$1");
            return group1 + " " + group2;
        }
        return null;
    }

    private String correctBookmarkByRegex(String ocrBookmarkStr, String regex, String replacement) {
        // final String PdfShuQianRegex = "([0-9.]+)[ ]?([^0-9].+)";
        Pattern pattern = Pattern.compile(RegexEnum.PdfShuQianRegex.getRegex());
        Matcher matcher = pattern.matcher(ocrBookmarkStr);
        String group1;
        String group2;
        if (matcher.matches()) {
            // 获取匹配到的一个分组
            group1 = matcher.group(1);
            // 删除所有的点号
            group1 = group1.replaceAll(regex, replacement);
            group2 = matcher.group(2);
            group2 = group2.replaceAll(RegexEnum.SpacesAfterChinese.getRegex(), "$1");
            return group1 + " " + group2;
        }
        return null;
    }

    public String correctBookmark12(String ocrBookmarkStr) {
        return correctBookmarkByRegex(ocrBookmarkStr, "(\\d).?(\\d\\d)", "$1.$2");
    }

    public String correctBookmark121(String ocrBookmarkStr) {
        return correctBookmarkByRegex(ocrBookmarkStr, "(\\d)[.]?(\\d\\d)[.]?(\\d{1,2})", "$1.$2.$3");
    }


    public String correctBookmark21(String ocrBookmarkStr) {
        return correctBookmarkByRegex(ocrBookmarkStr, "(\\d\\d)[.]?(\\d)", "$1.$2");
    }

    public String correctBookmark211(String ocrBookmarkStr) {
        return correctBookmarkByRegex(ocrBookmarkStr, "(\\d\\d)[.]?(\\d)[.]?(\\d)", "$1.$2.$3");
    }

    public String correctBookmark22(String ocrBookmarkStr) {
        return correctBookmarkByRegex(ocrBookmarkStr, "(\\d\\d).?(\\d\\d)", "$1.$2");
    }

    public String correctBookmark221(String ocrBookmarkStr) {
        return correctBookmarkByRegex(ocrBookmarkStr, "(\\d\\d).?(\\d\\d).?(\\d{1,2})", "$1.$2.$3");
    }
}