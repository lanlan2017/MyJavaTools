package formatter.markdown.multiline.codeblock;

import formatter.FormatterMultiLine;
import formatter.markdown.corrector.JavaCorrector;
import tools.string.StringDeleter;

/**
 * @author francis
 * create at 2020/5/12-16:25
 */
public class MdCbHtml implements FormatterMultiLine {
    @Override
    public String format(String str) {
        str = new StringDeleter().deleteBlankLine(str);
        //str = JavaCorrector.correctJava(str);
        return "```html\n" + str + "\n```";
    }
}
