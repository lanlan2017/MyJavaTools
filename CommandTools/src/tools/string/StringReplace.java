package tools.string;

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
}
