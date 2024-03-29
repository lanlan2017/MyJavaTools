package blue.ocr3.formatter.markdown.multiline.codeblock;

// import blue.ocr2.formatter.FormatterMultiLine;

import blue.ocr3.formatter.FormatterMultiLine;
import tools.reflect.method.ObjectMap;
import tools.string.StringDeleter;

/**
 * 格式化为Markdown Sql代码块.
 */
public class MdCbSql implements FormatterMultiLine {
    @Override
    public String format(String str) {
        // str = new StringDeleter().deleteBlankLine(str);
        str = ObjectMap.get(StringDeleter.class).deleteBlankLine(str);
        return "```sql\n" + str + "\n```";
    }
}


