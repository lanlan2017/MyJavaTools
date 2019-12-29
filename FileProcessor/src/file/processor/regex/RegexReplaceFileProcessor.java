package file.processor.regex;

import file.processor.FileProcessor;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 对一个文件文件中所有字符串使用正则表达式进行替换操作.
 */
public class RegexReplaceFileProcessor extends FileProcessor {
    private String regex;
    private String replacement;

    public RegexReplaceFileProcessor(String filePath, String regex, String replacement) {
        super(filePath);
        // 设置文件名过滤器.
        super.setFilenameFilter((dir, name) -> {
            File file = new File(dir, name);
            // 返回.md文件.
            if (file.isFile()) {
                return name.endsWith(".md");
            }
            // 返回不是.开头的目录.
            else if (file.isDirectory()) {
                return !name.startsWith(".");
            }
            return false;
        });
        this.regex = regex;
        this.replacement = replacement;
    }


    /**
     * 使用正则表达式替换所有匹配的内容.
     *
     * @param fileContent 要处理的内容.
     * @return 如果有匹配正则表达式的内容, 则返回替换后的字符串.
     * 如果没有匹配的内容则返回null.
     */
    @Override
    public String processingFileContent(String fileContent) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(fileContent);
        // 如果查找到匹配特定正则表达式的字串
        if (matcher.find()) {
            // 返回替换后的字串.
            return matcher.replaceAll(replacement);
        }
        // 如果没有找到匹配的字串,则返回null,表示不需要替换.
        return null;
    }
}
