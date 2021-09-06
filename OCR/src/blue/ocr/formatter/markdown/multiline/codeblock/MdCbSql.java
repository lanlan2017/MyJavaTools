package blue.ocr.formatter.markdown.multiline.codeblock;

import blue.ocr.formatter.FormatterMultiLine;
import tools.string.StringDeleter;

/**
 * 格式化为Markdown Sql代码块.
 */
public class MdCbSql implements FormatterMultiLine {
    @Override
    public String format(String str) {
        str = new StringDeleter().deleteBlankLine(str);
        return "```sql\n" + str + "\n```";
    }
}


