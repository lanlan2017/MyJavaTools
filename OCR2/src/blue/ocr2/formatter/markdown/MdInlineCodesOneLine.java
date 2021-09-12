package blue.ocr2.formatter.markdown;

// import blue.ocr.formatter.Formatter;
// import blue.ocr.formatter.markdown.corrector.MdInlineCodeCorrector;
import blue.ocr2.formatter.Formatter;
import blue.ocr2.formatter.markdown.corrector.MdInlineCodeCorrector;
import tools.markdown.MarkdownTools;

public class MdInlineCodesOneLine implements Formatter {

    @Override
    public String format(String str) {
        // 变成markdown代码.
        str = new MarkdownTools().inlineCodeAuto(str);
        // 处理
        str = new MdInlineCodeCorrector().correctMdInlineCode(str);
        return str;
    }
}