package xunfei.sst;


import reader.file.resouce.ResourceFileReader;

/**
 * 添加讯飞语音合成标记
 */
public class XunFeiSstFlag {
    private final static String SSTStart = "<!--SSTStart-->";
    private final static String SSTStop = "<!--SSTStop-->";

    /**
     * 添加讯飞朗读标记
     */
    public String addSSTFlag(String input) {
        input = SSTStart + "\n" + input + "\n" + SSTStop;
        return input;
    }

    /**
     * 生成定制的语言朗读标签：<code><!--formatter.replace xxx=XXX--></code>
     *
     * @param input 文章中容易读错的单词.
     * @return 朗读标记.
     */
    public String duyin(String input) {
        if (input != null) {
            // String readFlag = OutputTemplateFile
            //         .getTemplate("template/xunfei/Replace.txt");
            String readFlag = ResourceFileReader.getFileContent(this.getClass(), "xunfei/sst/replace.template");
            if (readFlag != null)
                readFlag = readFlag.replace("=", input + "=");
            return readFlag;
        }
        return "生成读音标记错误!";
    }
}