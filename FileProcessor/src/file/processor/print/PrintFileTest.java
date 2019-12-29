package file.processor.print;

import file.processor.FileProcessor;

/**
 * @author francis
 * create at 2019/12/15-20:29
 */
public class PrintFileTest {
    public static void main(String[] args) {
        String path = "G:\\Desktop\\Tests\\source\\_posts\\测试";
        FileProcessor fileProcessor = new PrintFile(path);
        fileProcessor.processing();
    }
}
