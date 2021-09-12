package blue.ocr2.formatter.markdown.multiline.codeblock;

// import blue.ocr.formatter.FormatterMultiLine;
import blue.ocr2.formatter.FormatterMultiLine;
import tools.string.StringDeleter;

/**
 * @author francis
 * create at 2020/5/12-16:35
 */
public class MdCbJsp  implements FormatterMultiLine {
    @Override
    public String format(String str) {
        str = new StringDeleter().deleteBlankLine(str);
        //str = JavaCorrector.correctJava(str);
        return "```jsp\n" + str + "\n```";
    }
}