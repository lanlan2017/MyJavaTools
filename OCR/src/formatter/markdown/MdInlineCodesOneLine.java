package formatter.markdown;

import formatter.Formatter;
import formatter.markdown.corrector.MdInlineCodeCorrector;
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