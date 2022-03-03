package blue.ocr3.formatter.markdown;

// import blue.ocr.formatter.Formatter;
// import blue.ocr2.formatter.Formatter;
import blue.ocr3.formatter.Formatter;
import tools.markdown.MarkdownTools;
import tools.reflect.method.ObjectMap;

/**
 * 加粗按钮功能实现.
 */
public class MdBold implements Formatter {
    @Override
    public String format(String str) {
        // return new MarkdownTools().bold(str);
        return ObjectMap.get(MarkdownTools.class).bold(str);
    }
}
