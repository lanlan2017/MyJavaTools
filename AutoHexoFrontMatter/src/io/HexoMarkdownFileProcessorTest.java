package io;

import processor.FileProcessor;

/**
 * @author francis
 * create at 2019/12/16-15:12
 */
public class HexoMarkdownFileProcessorTest {
    public static void main(String[] args) {
        // String path = "E:\\Blog\\blogRoot\\source\\_posts\\测试\\测试.md";
        // String path = "E:\\Blog\\blogRoot\\source\\_posts\\测试\\测试\\测试.md";
        String path = "E:\\Blog\\blogRoot\\source\\_posts\\测试\\测试\\测试3.md";
        FileProcessor fileProcessor = new HexoMarkdownFileProcessor(path);
        fileProcessor.processing();
    }
}
