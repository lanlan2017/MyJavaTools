package tools.string.formatter;

import tools.config.ConfigTools;
import tools.copy.SystemClipboard;
import tools.java.JavaTools;
import tools.markdown.MarkdownTools;
import tools.string.PrintStr;
import tools.string.StringDeleter;

import java.util.regex.Pattern;

public class PdfFormatter {

    private static final JavaTools javaTools = new JavaTools();
    private static final MarkdownTools markdownTools = new MarkdownTools();
    private static final StringDeleter stringDeleter = new StringDeleter();

    public String format(String sysClipboardText) {
        String reuslt = sysClipboardText;
        if (Pattern.compile("^[a-zA-Z@]+ .+").matcher(reuslt).find()) {
            // PrintStr.printStr("是代码耶");
            reuslt = javaTools.formatFromPDF(reuslt);
            reuslt = markdownTools.codeBlockJava(reuslt);
        }
        // 如果是多行的话
        else if (Pattern.compile("^[\u4e00-\u9fa5]+.+").matcher(reuslt).find()) {
            // PrintStr.printStr("是中文耶");
            if (reuslt.matches("图\\d+-\\d+ .+")) {
                // PrintStr.printStr("是图片提示");
                reuslt = ConfigTools.getInstance().forward("html center".split(" "));
            } else {
                // 删除中文之间的空白符
                reuslt = stringDeleter.deleteSpaces(reuslt);
                // 删除换行符
                reuslt = stringDeleter.deleteCRLF(reuslt);
            }
        }
        if (reuslt.contains("● ")) {
            // reuslt = reuslt.replaceAll("● ", "- ");
            reuslt = markdownTools.unorderList(reuslt);
        }
        return reuslt;
    }

    public static void main(String[] args) {
        // 读取剪贴板的内容
        String sysClipboardText = SystemClipboard.getSysClipboardText();
        if (sysClipboardText != null) {
            String result = new PdfFormatter().format(sysClipboardText);
            // SystemClipboard.setSysClipboardText(result);
            // 显示处理结果，并把处理后的内容写入剪贴板中
            ConfigTools.getInstance().showResult(result);
        }
    }
}
