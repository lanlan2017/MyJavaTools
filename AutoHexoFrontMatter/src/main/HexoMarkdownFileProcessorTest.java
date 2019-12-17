package main;

import clipboard.SystemClipboard;
import io.HexoMarkdownFileProcessor;
import file.processor.FileProcessor;

/**
 * @author francis
 * create at 2019/12/16-15:12
 */
public class HexoMarkdownFileProcessorTest {
    public static void main(String[] args) {
        // String path = "E:\\Blog\\blogRoot\\source\\_posts\\测试\\测试.md";
        // String path = "E:\\Blog\\blogRoot\\source\\_posts\\测试\\测试\\测试.md";
        // String path = "E:\\Blog\\blogRoot\\source\\_posts\\测试\\测试\\测试3.md";
        FileProcessor fileProcessor;
        if (args.length == 1) {
            fileProcessor = new HexoMarkdownFileProcessor(args[0]);
            // System.out.println("通过 命令行参数 传入文件地址");
        } else {
            String path = SystemClipboard.getSysClipboardText();
            // System.out.println("通过 剪贴板 传入文件地址");
            fileProcessor = new HexoMarkdownFileProcessor(path);
        }
        fileProcessor.processing();
    }
}
