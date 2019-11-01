package io.tools;

/**
 * 对一个文件文件中所有字符串使用正则表达式进行替换操作.
 */
public class RegexReplaceFileProcessor extends FileProcessor {
    String regex;
    String replacement;

    public RegexReplaceFileProcessor(String filePath, String regex, String replacement) {
        super(filePath);
        this.regex = regex;
        this.replacement = replacement;
    }

    /**
     * 使用正则表达式替换字符串中的内容.
     *
     * @param fileContent 要替换的字符串.
     */
    @Override
    public String processingFileContent(String fileContent) {
        // return fileContent.replaceAll(">原文链接: \\[.+\\](.+)", "");
        return fileContent.replaceAll(regex, replacement);
    }

}
