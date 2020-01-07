package ocr.baidu.formatter.impl;

import ocr.baidu.formatter.FormatterByCmd;

/**
 * 格式化为Markdown Sql代码块.
 */
public class MdCodeBlockSqlFormatter extends FormatterByCmd {
    @Override
    public String format(String str) {
        return "```sql\n" + str + "\n```";
    }
}
