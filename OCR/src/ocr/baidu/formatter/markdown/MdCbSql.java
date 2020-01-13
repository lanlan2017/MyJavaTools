package ocr.baidu.formatter.markdown;

import ocr.baidu.formatter.Formatter;

/**
 * 格式化为Markdown Sql代码块.
 */
public class MdCbSql implements Formatter {
    @Override
    public String format(String str) {
        return "```sql\n" + str + "\n```";
    }
}
