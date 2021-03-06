package formatter.markdown;

import formatter.Formatter;
import tools.markdown.MarkdownTools;

/**
 * 加粗按钮功能实现.
 */
public class MdBold implements Formatter {
    @Override
    public String format(String str) {
        return new MarkdownTools().bold(str);
    }
}
