package tools.string.formatter;

import tools.config.ConfigTools;
import tools.copy.SystemClipboard;
import tools.java.JavaTools;
import tools.markdown.MarkdownTools;
import tools.string.PrintStr;
import tools.string.StringDeleter;

import java.util.regex.Pattern;

public class PdfFormatter {
    public String format(String sysClipboardText) {
        String reuslt = "";
        if (Pattern.compile("^[a-z]+ .+").matcher(sysClipboardText).find()) {
            PrintStr.printStr("是代码耶");
            reuslt = new JavaTools().formatFromPDF(sysClipboardText);
            reuslt = new MarkdownTools().codeBlockJava(reuslt);
        }
        // 如果是多行的话
        else if (Pattern.compile("^[\u4e00-\u9fa5]+.+").matcher(sysClipboardText).find()) {
            PrintStr.printStr("是中文耶");
            if (sysClipboardText.matches("图\\d+-\\d+ .+")) {
                PrintStr.printStr("是图片提示");
                reuslt = ConfigTools.getInstance().forward("html center".split(" "));
            } else {
                reuslt = new StringDeleter().deleteSpaces(sysClipboardText);
                reuslt = new StringDeleter().deleteCRLF(reuslt);
            }
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
