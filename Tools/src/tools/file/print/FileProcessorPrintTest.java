package tools.file.print;


import tools.file.processor.FileProcessor;

/**
 * @author francis
 * create at 2019/12/15-20:29
 */
public class FileProcessorPrintTest {
    public static void main(String[] args) {
        String path = "G:\\Desktop\\Tests\\source\\_posts\\测试";
        FileProcessor fileProcessor = new FileProcessorPrint(path);
        fileProcessor.processing();
    }
}
