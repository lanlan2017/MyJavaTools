package blue.ocr3.formatter.markdown.multiline;

import tools.markdown.MarkdownTools;
import tools.reflect.method.ObjectMap;

/**
 * 格式化为有序列表
 */
public class MdOrderList  extends MdInlineCodesMultiLine {
    @Override
    public String format(String str) {
        str = super.format(str);
        // 格式为无序列表
        // str = new MarkdownTools().orderedList(str);
        str = ObjectMap.get(MarkdownTools.class).orderedList(str);
        return str;
    }
}