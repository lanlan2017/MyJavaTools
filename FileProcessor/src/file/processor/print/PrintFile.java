package file.processor.print;

import file.processor.FileProcessor;

/**
 * 文件处理器:输出文件内容
 */
public class PrintFile extends FileProcessor {

    public PrintFile(String filePath) {
        super(filePath);
    }

    @Override
    protected String processingFileContent(String fileContent) {
        System.out.println(fileContent);
        return null;
    }
}
