package file.processor.regex.delete;

import file.processor.regex.RegexReplaceFileProcessor;

/**
 * 文件处理器:使用空格作为缩进.
 */
public class IndentBySpacesFileProcessor extends RegexReplaceFileProcessor {
    public IndentBySpacesFileProcessor(String filePath) {
        super(filePath, "\t", "    ");
        message = "使用空格缩进:";
    }
}
