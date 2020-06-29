package tools.string;

import regex.RegexEnum;
import tools.markdown.MarkdownTools;

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
        text = text.replaceAll("(?m)^[ ]+", "");
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

    public String cpkd(String text) {
        //text=text.replaceAll(" ", "");
        // 删除kindle标记
        text = text.replaceAll("(?m)^$\\n^.+\\(Kindle[ ]?位置 ?\\d+?(?:-\\d+)?\\)\\. ?电子工业出版社\\. ?Kindle ?版本\\. $", "");
        // 删除中文之间的空格,删除前面是中文,后面是字母 的 空格
        text = text.replaceAll("(?<=[\\u4e00-\\u9fa5]) (?=[\\u4e00-\\u9fa5a-zA-Z])", "");
        // 删除前面是字母 后面是中文 的 空格
        text = text.replaceAll("(?<=[a-zA-Z]) (?=[\\u4e00-\\u9fa5])", "");
        // 删除点号和数字之间的空格
        text = text.replaceAll("(?<=\\.) (?=\\d+)", "");
        // 删除中文和标点符号之间的空格
        text = text.replaceAll("(?<=[，。“])[ ](?=[\\u4e00-\\u9fa5])", "");
        // 删除中文 数字之间的空格
        text = text.replaceAll("(?<=[\\u4e00-\\u9fa5])[ ]*(\\d)[ ]*(?=[\\u4e00-\\u9fa5])", "$1");
        //text=new MarkdownTools().inlineCodeAuto(text);
        return text;
    }

    public String cpkdCode(String text) {
        text = cpkd(text);
        text = new MarkdownTools().inlineCodeAuto(text);
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

    /**
     * 删除空格
     *
     * @param str 带有空格的字符串.
     * @return 没有空格的字符串.
     */
    public String removeSpaces(String str) {
        str = str.replace(" ", "");
        return str;
    }

    /**
     * 获取文件名.
     * @param str 文件的相对地址或者绝对地址。
     * @return 文件名
     */

    public String getFileName(String str) {
        // 如果包含地址符号
        if (str.contains("/") || str.contains("\\")) {
            if (str.contains("/")) {
                str =str.substring(str.lastIndexOf("/")+1);
            } else {
                str =str.substring(str.lastIndexOf("\\")+1);
            }
        }
        return str;
    }
}