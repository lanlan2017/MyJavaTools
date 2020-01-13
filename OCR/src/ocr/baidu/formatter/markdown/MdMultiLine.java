package ocr.baidu.formatter.markdown;

import ocr.baidu.formatter.Formatter;
import ocr.baidu.formatter.markdown.corrector.MdInlineCodeCorrector;
import tools.markdown.MarkdownTools;
import tools.string.StringDeleter;

/**
 *
 */
public class MdMultiLine implements Formatter {
    @Override
    public String format(String str) {
        MarkdownTools mdTools = new MarkdownTools();
        // 变成markdown代码.
        str = mdTools.inlineCodeAuto(str);
        // 纠正Markdown行内代码
        str = new MdInlineCodeCorrector().correctMdInlineCode(str);
        // 删除多余空行
        str = new StringDeleter().deleteBlankLine(str);
        return str;
    }
}
