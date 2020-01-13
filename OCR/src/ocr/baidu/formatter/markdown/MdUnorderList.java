package ocr.baidu.formatter.markdown;

import tools.markdown.MarkdownTools;

public class MdUnorderList extends MdMultiLine {
    @Override
    public String format(String str) {
        str = super.format(str);
        // 格式为无序列表
        str = new MarkdownTools().unorderList(str);
        return str;
    }
}
