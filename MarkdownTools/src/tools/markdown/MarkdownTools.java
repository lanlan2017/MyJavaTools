package tools.markdown;

import formatter.string.StringDeleter;
import regex.RegexEnum;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author francis
 * create at 2019/12/19-11:30
 */
public class MarkdownTools {

    public String header1(String input) {
        return "# " + input;
    }

    public String header2(String input) {
        return "## " + input;
    }

    public String header3(String input) {
        return "### " + input;
    }

    public String header4(String input) {
        return "#### " + input;
    }

    public String header5(String input) {
        return "##### " + input;
    }

    public String header6(String input) {
        return "###### " + input;
    }

    public String headerAdd(String text) {
        text = text.replaceAll("(#+)(.+)\\1", "#$1$2#$1");
        return text;
    }

    public String headerMinus(String text) {
        text = text.replaceAll("(#(#+))(.+)\\1", "$2$3$2");
        return text;
    }

    public String bold(String input) {
        return "**" + input + "**";
    }

    public String inlineCode(String input) {
        return "`" + input + "`";
    }

    public String autoInlineCode(String input) {
        return null;
    }

    public String hyperLinks(String input) {
        Pattern pattern = Pattern.compile("(.+?)\\s{2,}(.+?)");
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) {
            return "[" + matcher.group(1) + "](" + matcher.group(2) + ")";
        }
        return "[" + input + "](" + input + ")";
    }

    public String img(String text) {
        if (text.matches(RegexEnum.ImgURL.getRegex())) {
            return "![这里有一张图片](" + text + ")";
        }else if(text.matches(RegexEnum.MdImgNoAlt.getRegex()))
        {
            return text.replaceAll(RegexEnum.MdImgNoAlt.getRegex(),"![这里有一张图片]($1)");
        }
        return null;
    }

    public String unorderList(String text) {
        text = new StringDeleter().deleteBlankLine(text);
        // 开头不是字母数字或者中文的一律删除掉.
        text = text.replaceAll("(?m)^[^a-zA-Z0-9\\u4e00-\\u9fa5][ ]+", "");
        // 在每行开头添加上无序列表标记
        text = text.replaceAll(RegexEnum.LineStart.getRegex(), "- ");
        return text + "\n";
    }

    public String orderedList(String text) {
        text = new StringDeleter().deleteBlankLine(text);
        int i = 1;
        String[] lines = text.split("\\n");
        StringBuilder buf = new StringBuilder();
        for (String string : lines) {
            buf.append((i++) + ". " + string + "\n");
        }
        return buf.toString();
    }
    public String quote(String text) {
        text = new StringDeleter().deleteBlankLine(text);
        // 在多行文本的每一个行开头添加引用标记
        text = text.replaceAll(RegexEnum.LineStart.getRegex(), "> ");
        return text;
    }
    public String codeBlock(String language, String input) {
        return "```" + language + "\n" + input + "\n```";
    }

    public String codeBlockJava(String input) {
        return "```java\n" + input + "\n```";
    }

    public String codeBlockJavaScript(String input) {
        return "```javascript\n" + input + "\n```";
    }
}
