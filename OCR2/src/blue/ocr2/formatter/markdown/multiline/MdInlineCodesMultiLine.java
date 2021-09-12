package blue.ocr2.formatter.markdown.multiline;

// import blue.ocr.formatter.FormatterMultiLine;
// import blue.ocr.formatter.markdown.corrector.MdInlineCodeCorrector;
import blue.ocr2.formatter.FormatterMultiLine;
import blue.ocr2.formatter.markdown.corrector.MdInlineCodeCorrector;
import tools.markdown.MarkdownTools;
import tools.string.StringDeleter;

/**
 * 多行的行内代码模式
 */
public class MdInlineCodesMultiLine implements FormatterMultiLine {
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
