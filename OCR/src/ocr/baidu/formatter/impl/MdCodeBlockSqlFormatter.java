package ocr.baidu.formatter.impl;

import ocr.baidu.formatter.Formatter;

/**
 * 格式化为Markdown Sql代码块.
 */
public class MdCodeBlockSqlFormatter extends Formatter {
    @Override
    public String format(String str) {
        return "```sql\n" + str + "\n```";
    }
}
