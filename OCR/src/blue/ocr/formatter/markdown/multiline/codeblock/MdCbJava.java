package blue.ocr.formatter.markdown.multiline.codeblock;

import blue.ocr.formatter.FormatterMultiLine;
import blue.ocr.formatter.markdown.corrector.JavaCorrector;
import tools.string.StringDeleter;

public class MdCbJava implements FormatterMultiLine {
    @Override
    public String format(String str) {
        str = new StringDeleter().deleteBlankLine(str);
        str = JavaCorrector.correctJava(str);
        return "```java\n" + str + "\n```";
    }
}
