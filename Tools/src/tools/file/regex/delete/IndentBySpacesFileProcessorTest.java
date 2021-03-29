package processor.file.regex.delete;

import processor.file.FileProcessor;

/**
 * 文件处理器:使用空格作为缩进.
 */
public class IndentBySpacesFileProcessorTest {
    public static void main(String[] args) {
        String filePath = "E:\\dev2\\idea_workspace\\MyJavaTools\\FileProcessor\\src\\file\\processor\\regex\\delete\\test\\Test.java";
        FileProcessor fileProcessor = new IndentBySpacesFileProcessor(filePath);
        fileProcessor.processing();
    }
}
