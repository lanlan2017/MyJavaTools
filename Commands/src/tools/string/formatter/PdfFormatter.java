package tools.string.formatter;

import tools.config.ConfigTools;
import tools.copy.SystemClipboard;
import tools.java.JavaTools;
import tools.markdown.MarkdownTools;
import tools.reflect.method.ObjectMap;
import tools.string.StringDeleter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PdfFormatter {
    /**
     * 格式化,段落，java代码描述，java代码，三段信息的混合信息。
     *
     * @param
     * @return
     */
    public String formatPdjMixed(String mixedPdfText) {
        Matcher matcher = Pattern.compile(" ?(代码清单\\d+-\\d+ .+?) ").matcher(mixedPdfText);
        if (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            // 取出段落
            String paragraphText = mixedPdfText.substring(0, start);

            // 格式化段落
            paragraphText = formatParagraph(paragraphText);
            System.out.println("段落:" + paragraphText);

            // 取出代码清单信息
            // String centerText = mixedPdfText.substring(start, end);
            // 取出java代码的描述信息

            String javaCodeDescription = matcher.group(1);
            System.out.println("代码描述信息:" + javaCodeDescription);
            // 格式化代码清单描述信息
            javaCodeDescription = formatCenter(javaCodeDescription);

            // 取出java代码
            String javaCode = mixedPdfText.substring(end);
            System.out.println("java代码:" + javaCode);
            // 格式化java代码
            javaCode = formatJavaCode(javaCode);

            mixedPdfText = paragraphText + "\n\n" + javaCodeDescription + "\n\n" + javaCode + "\n";
        }
        return mixedPdfText;
    }

    public String format(String sysClipboardText) {
        String reuslt = sysClipboardText;
        // System.out.println("进入方法了");
        // 如果是java代码的话
        if (isJavaCode(reuslt)) {
            reuslt = formatJavaCode(reuslt);
        }
        // 如果开头是中文的话,或者开头有一个英文单词，后面都是中文
        else if (Pattern.compile("^(?:[a-zA-Z]+)?[\u4e00-\u9fa5]+.+").matcher(reuslt).find()) {
            // System.out.println("是中文耶");
            // 如果是图片描述信息，如果是代码清单描述信息
            // if (reuslt.matches("(?:图|表|代码清单)\\d+-\\d+ .+") || reuslt.matches("代码清单\\d+-\\d+ .+") || reuslt.matches("表\\d+-\\d+ .+")) {
            if (reuslt.matches("(?:图|表|代码清单)[0-9a-zA-Z]+-\\d+ .+")) {
                reuslt = formatCenter(reuslt);
            } else {
                // 格式化段落
                reuslt = formatParagraph(reuslt);
            }
        }
        // 如果以小圆点开头的话（Office的无序列表标志）
        else if (reuslt.startsWith("·") || reuslt.startsWith("•") || reuslt.startsWith("●")) {
            // 格式化无序列表
            reuslt = formatUnorderedList(reuslt);
        }
        return reuslt;
    }

    /**
     * 格式化java代码。
     *
     * @param javaCode
     * @return
     */
    private String formatJavaCode(String javaCode) {
        // System.out.println("是java代码耶");
        // 从对象池中的获取JavaTools对象，免得重复创建对象。
        JavaTools javaTools = ObjectMap.get(JavaTools.class);
        // 从对象池中的获取MarkdownTools对象，免得重复创建对象。
        MarkdownTools markdownTools = ObjectMap.get(MarkdownTools.class);
        // 格式化java代码
        javaCode = javaTools.formatFromPDF(javaCode);
        // 生成markdown的java代码块
        javaCode = markdownTools.codeBlockJava(javaCode);
        return javaCode;
    }

    /**
     * 格式化居中对齐文本
     *
     * @param toBeCenter
     * @return html居中对齐标签
     */
    private String formatCenter(String toBeCenter) {
        // 居中对齐
        return "<center>" + toBeCenter + "</center>";
    }

    /**
     * 格式化无序列表
     *
     * @param reuslt 无序列表内容
     * @return markdown格式的无序列表
     */
    private String formatUnorderedList(String reuslt) {
        StringDeleter stringDeleter = ObjectMap.get(StringDeleter.class);
        reuslt = stringDeleter.deleteSpaces(reuslt);
        // System.out.println("是无序列表");
        // 使用对象池中的对象，免得重复创建对象。
        MarkdownTools markdownTools = ObjectMap.get(MarkdownTools.class);
        reuslt = markdownTools.unorderList(reuslt);
        return reuslt;
    }

    /**
     * 格式化段落
     *
     * @param paragraphText 段落文本
     * @return 格式化后的段落文本。
     */
    private String formatParagraph(String paragraphText) {
        // 从对象池中获取对象，免得重复创建对象。
        StringDeleter stringDeleter = ObjectMap.get(StringDeleter.class);
        // 删除中文之间的空白符
        paragraphText = stringDeleter.deleteSpaces(paragraphText);
        // 删除换行符
        paragraphText = stringDeleter.deleteCRLF(paragraphText);
        return paragraphText;
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
