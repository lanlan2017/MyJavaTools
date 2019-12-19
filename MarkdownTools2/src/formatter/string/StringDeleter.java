package formatter.string;

import regex.RegexEnum;

public class StringDeleter {
    /**
     * 删除空行
     *
     * @param text 带空行的文本.
     * @return 没有空行的文本.
     */
    public String deleteBlankLine(String text) {
        text = text.replaceAll(RegexEnum.blankLineRegex1.getRegex(), "");
        text = text.replaceAll(RegexEnum.blankLineRegex2.getRegex(), "");
        return text;
    }

    /**
     * 删除Markdown代码块.
     *
     * @param text 带空行的文本.
     * @return 没有空行的文本.
     */
    public String deleteMdCodeBlock(String text) {
        text = text.replaceAll(RegexEnum.markdownCodeBlockRegex.getRegex(), "");
        return text;
    }

    /**
     * 删除换行符
     *
     * @param text 带空行的文本.
     * @return 没有空行的文本.
     */
    public String deleteCRLF(String text) {
        text = text.replaceAll(RegexEnum.CRLF.getRegex(), "");
        return text;
    }

    /**
     * 删除中文之间的空白符.
     *
     * @param text 带空格的中文文本.
     * @return 移除无用空格的中文文本.
     */
    public String deleteSpaces(String text) {
        // 删除中文之间的空格
        text = text.replaceAll("([\\u4e00-\\u9fa5])[ ]+([\\u4e00-\\u9fa5])",
                "$1$2");
        // 删除前面是中文,后面是英文之间的空格.
        text = text.replaceAll("([\\u4e00-\\u9fa5]+)[ ]+(\\w+)", "$1$2");
        text = text.replaceAll("(?<=[@.])[ ]", "");
        // 多个空格,替换为一个空格
        text = text.replaceAll("[ ]{2,}", " ");
        // 删除中文全角空格
        text = text.replaceAll("[　]+", "");
        // 删除结尾的空格
        text = text.replaceAll("(?m)[ ]$", "");
        // 删除开头的空格
        text = text.replaceAll("(?m)^[ ]", "");
        return text;
    }
}