package tools.markdown;

import regex.RegexEnum;
import tools.markdown.niuke.NiukeConfig;
import tools.string.PrintStr;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Markdown无序列表转为markdown表格,注意该无序列表有要有冒号`:`作为分隔符
 */
public class MardownConverter {
    public String unorderList2Table(String input) {
        // 删除无序列表标记
        input = input.replaceAll("(?m)^- ", "");
        input = input.replaceAll("(?m)^➢", "");
        input = input.replaceAll("(?m)`:(?!\\w)", "`|");
        input = input.replaceAll("(?m)^\\s*", "|");
        input = input.replaceAll("(?m)$", "|");
        String tableHead = "\r\n||描述|\r\n" + "|:---|:---|\r\n";
        return tableHead + input;

    }

    /**
     * 将Markdown有序列表代码转成无序列表代码.
     *
     * @param input Markdown有序列表代码.
     * @return 无序列表代码.
     */
    public String orderList2UnorderList(String input) {
        return input.replaceAll(RegexEnum.MarkdownOrderListRegex.toString(), "-");
    }

    /**
     * 将Markdown无序列表转成有序列表.
     *
     * @param input Markdown无须列表代码.
     * @return 有序列表代码.
     */
    public String unorderList2OrderList(String input) {
        Pattern pattern = Pattern.compile("(^[ ]*)(?:-)", Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(input);
        StringBuffer sb = new StringBuffer();
        String group1;
        int count = 0;
        while (matcher.find()) {
            // 获取匹配到的一个分组
            group1 = matcher.group(1);
            // 替换原来匹配的文本
            matcher.appendReplacement(sb, group1 + (++count) + ".");
        }
        // 添加后面没有匹配的文本
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * Markdown行内代码转Markdown加粗
     *
     * @param inlineCode markdown行内代码
     * @return Mardown加粗字符
     */
    public String inlineCodeToBold(String inlineCode) {
        if (inlineCode.matches("`.+`")) {
            inlineCode = inlineCode.replaceAll("`", "**");
        }
        return inlineCode;
    }

    /**
     * @param copyText
     * @return
     */
    public String niuke(String copyText) {
        int number = NiukeConfig.getNumber();
        return niuke(copyText, number);
    }

    // public String niuke(String copyText, String numberStr) {
    //     int number = Integer.parseInt(numberStr);
    //     return niuke(copyText, number);
    // }

    public String niuke(String copyText, int number) {
        // System.out.println(copyText.startsWith("# 考点"));
        // 如果是已经格式化好的题目
        if (copyText.startsWith("# 考点")) {
            // PrintStr.printStr(copyText);
            copyText = copyText.replaceAll("# 考点[0-9]+:", "# 考点" + number + ":");
        }
        // 如果不是已经格式化的题目
        else {
            // 格式化为题目
            copyText = "# 考点" + number + ":\n" + copyText;
            // 替换选项
            copyText = copyText.replaceAll("(?m)^([A-Z])$\\n^(.+)$", "- $1 $2");
            copyText = copyText.replaceAll("(?m)^正确答案: [A-Z]+$", "\n<details><summary>显示答案/隐藏答案</summary>$0</details>\n\n");
        }
        return copyText;
    }

    public String niukeReset(String copyText) {
        int number = NiukeConfig.reset();
        return niuke(copyText, number);
    }

    /**
     * @param chioce
     * @return
     */
    public String niukeInlineCodeChoice(String chioce) {
        chioce = new MarkdownTools().inlineCodeUndo(chioce);
        chioce = chioce.replaceAll("(?m)$", "`");
        chioce = chioce.replaceAll("(?m)^- [A-Z] ", "$0`");
        return chioce;
    }
}