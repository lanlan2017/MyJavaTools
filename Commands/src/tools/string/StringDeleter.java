package tools.string;

import regex.RegexEnum;

public class StringDeleter {
    /**
     * 删除空行
     *
     * @param text 带空行的文本.
     * @return 没有空行的文本.
     */
    public String deleteBlankLine(String text) {
        text = text.replaceAll(RegexEnum.blankLineRegex1.toString(), "");
        text = text.replaceAll(RegexEnum.blankLineRegex2.toString(), "");
        return text;
    }

    /**
     * 删除Markdown代码块.
     *
     * @param text 带空行的文本.
     * @return 没有空行的文本.
     */
    public String deleteMdCodeBlock(String text) {
        text = text.replaceAll(RegexEnum.markdownCodeBlockRegex.toString(), "");
        return text;
    }

    /**
     * 删除换行符
     *
     * @param text 带空行的文本.
     * @return 没有空行的文本.
     */
    public String deleteCRLF(String text) {
        // 删除开头空白.
        text = text.replaceAll("(?m)^[ ]+", " ");
        // 删除回车换行符.
        text = text.replaceAll(RegexEnum.CRLF.toString(), "");
        return text;
    }

    public String weixinCode(String code) {
        code = code.replaceAll("(?m)(^\\/\\/.+$)", "___SingleLineComment___$1___SingleLineComment___");
        // System.out.println(code);
        code = code.replaceAll("(?m)^[ ]+", " ");
        code = code.replaceAll("\\n", "");
        code = code.replaceAll(RegexEnum.CRLF.toString(), "");
        code = code.replace("___SingleLineComment___", "\n");
        return code;
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