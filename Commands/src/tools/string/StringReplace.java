package tools.string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author francis
 * create at 2019/12/19-22:17
 */
public class StringReplace {
    public String chineseSymbol2EnglishSymbol(String input) {
        input = input.replace("（", "(");
        input = input.replace("）", ")");
        input = input.replace("，", ",");
        input = input.replace("；", ";");
        input = input.replace("：", ":");
        // 替换中文感叹号
        input = input.replace("！", "!");
        input = input.replace("？", "?");
        input = input.replace("＜", "<");
        input = input.replace("＞", ">");
        input = input.replace("＋", "+");
        input = input.replace("－", "-");
        return input;
    }

    /**
     * 转换成QQ输入法多行个性短语。
     *
     * @param code 多行代码
     * @return QQ输入法的多行个性短语。
     */
    public String qqShuRuFa(String code) {
        // 注释
        String comment = new String(code);
        code = new StringDeleter().deleteBlankLine(code);
        // 删除多余的文档注释行
        code = code.replaceAll("(?m)^ \\* $(?:\\r)?\\n", "");
        // 把换行符替换成QQ输入法的换行符
        code = code.replaceAll("\\n", "\\$(CRLF)");
        System.out.println("--------------------------------------------------");
        comment = qqShuRuFaZhuShi(comment);
        return code + "\n" + comment+"\n";
    }

    /**
     * 转换成QQ输入法个性短语的注释形式
     *
     * @param code 代码
     * @return QQ输入法个性短语的注释形式
     */
    public String qqShuRuFaZhuShi(String code) {
        // 删除空行
        code = new StringDeleter().deleteBlankLine(code);
        // 删除多余的文档注释行
        code = code.replaceAll("(?m)^ \\* $(?:\\r)?\\n", "");
        // 在行首添加注释
        return code.replaceAll("(?m)^", ";");
    }
}
