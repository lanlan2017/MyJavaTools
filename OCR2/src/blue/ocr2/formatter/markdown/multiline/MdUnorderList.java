package blue.ocr2.formatter.markdown.multiline;

import tools.markdown.MarkdownTools;

/**
 * 无序列表
 */
public class MdUnorderList extends MdInlineCodesMultiLine {
    @Override
    public String format(String str) {
        str = super.format(str);
        // 格式为无序列表
        str = new MarkdownTools().unorderList(str);
        return str;
    }
}
