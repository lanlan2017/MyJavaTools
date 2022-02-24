package blue.ocr3.formatter.markdown.multiline.codeblock;

// import blue.ocr.formatter.FormatterMultiLine;
// import blue.ocr2.formatter.FormatterMultiLine;

import blue.ocr3.formatter.FormatterMultiLine;
import tools.reflect.method.ObjectMap;
import tools.string.StringDeleter;

/**
 * @author francis
 * create at 2020/5/12-16:25
 */
public class MdCbHtml implements FormatterMultiLine {
    @Override
    public String format(String str) {
        // str = new StringDeleter().deleteBlankLine(str);
        str = ObjectMap.get(StringDeleter.class).deleteBlankLine(str);
        //str = JavaCorrector.correctJava(str);
        return "```html\n" + str + "\n```";
    }
}
