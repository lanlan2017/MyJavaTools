package ocr.baidu.formatter.markdown;

import ocr.baidu.formatter.Formatter;
import ocr.baidu.formatter.markdown.corrector.MdInlineCodeCorrector;
import tools.markdown.MarkdownTools;

public class MdSingleLine implements Formatter {

    @Override
    public String format(String str) {
        // 变成markdown代码.
        str = new MarkdownTools().inlineCodeAuto(str);
        // 处理
        str = new MdInlineCodeCorrector().correctMdInlineCode(str);
        return str;
    }
}