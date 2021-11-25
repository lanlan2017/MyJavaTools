package tools.string.formatter;

import tools.config.ConfigTools;
import tools.copy.SystemClipboard;
import tools.java.JavaTools;
import tools.markdown.MarkdownTools;
import tools.reflect.method.ObjectMap;
import tools.string.PrintStr;
import tools.string.StringDeleter;

import java.util.regex.Pattern;

public class PdfFormatter {
    public String format(String sysClipboardText) {
        String reuslt = sysClipboardText;
        // 如果是java代码的话
        if (isJavaCode(reuslt)) {
            PrintStr.printStr("是java代码耶");
            // 从对象池中的获取JavaTools对象，免得重复创建对象。
            JavaTools javaTools = ObjectMap.getObjectFromMap(JavaTools.class);
            // 从对象池中的获取MarkdownTools对象，免得重复创建对象。
            MarkdownTools markdownTools = ObjectMap.getObjectFromMap(MarkdownTools.class);
            // 格式化java代码
            reuslt = javaTools.formatFromPDF(reuslt);
            // 生成markdown的java代码块
            reuslt = markdownTools.codeBlockJava(reuslt);
            // reuslt = "\n```java\n" + reuslt + "\n```\n";
        }
        // 如果开头是中文的话,或者开头有一个英文单词，后面都是中文
        else if (Pattern.compile("^(?:[a-zA-Z]+)?[\u4e00-\u9fa5]+.+").matcher(reuslt).find()) {
            // PrintStr.printStr("是中文耶");
            // 如果是图片描述信息，如果是代码清单描述信息
            if (reuslt.matches("图\\d+-\\d+ .+") || reuslt.matches("代码清单\\d+-\\d+ .+")) {
                // PrintStr.printStr("是图片提示");
                // 居中对齐
                return "<center>" + reuslt + "</center>";
            } else {
                // 从对象池中获取对象，免得重复创建对象。
                StringDeleter stringDeleter = ObjectMap.getObjectFromMap(StringDeleter.class);
                // 删除中文之间的空白符
                reuslt = stringDeleter.deleteSpaces(reuslt);
                // 删除换行符
                reuslt = stringDeleter.deleteCRLF(reuslt);
            }
        }
        // 如果以小圆点开头的话（Office的无序列表标志）
        else if (reuslt.contains("(·|•|●) ")) {
            // 使用对象池中的对象，免得重复创建对象。
            MarkdownTools markdownTools = ObjectMap.getObjectFromMap(MarkdownTools.class);
            reuslt = markdownTools.unorderList(reuslt);
        }
        return reuslt;
    }

    /**
     * 判断给定的字符串是否是java代码
     *
     * @param reuslt 字符串
     * @return 如果这串字符是java代码的话，返回ture,否则返回false.
     */
    private boolean isJavaCode(String reuslt) {
        // 以两个或两个以上单词，或`@`开头
        // 或者以单行注释`//`开头
        // 则返回ture
        return Pattern.compile("^[a-zA-Z@]+ [a-zA-Z@]+ .+").matcher(reuslt).find() || reuslt.startsWith("//") || reuslt.startsWith("/**");
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
