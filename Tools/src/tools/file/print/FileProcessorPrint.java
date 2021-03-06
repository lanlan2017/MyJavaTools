package tools.file.print;


import tools.file.processor.FileProcessor;

/**
 * 文件处理器:输出文件内容
 */
public class FileProcessorPrint extends FileProcessor {

    public FileProcessorPrint(String filePath) {
        super(filePath);
    }

    @Override
    protected String processingFileContent(String fileContent) {
        System.out.println(fileContent);
        return null;
    }
}
