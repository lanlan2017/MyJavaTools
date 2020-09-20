package main;

import clipboard.swing.SystemClipboard;
import dir.processor.DirProcessor;
import tools.file.processor.FileProcessor;
import io.HexoMarkdownFileProcessor;
import io.toc.MyHexoNextToc;

import java.io.File;

/**
 * @author francis
 * create at 2019/12/16-15:12
 */
public class HexoMarkdownFileProcessorTest {
    public static void main(String[] args) {
        FileProcessor fileProcessor;
        switch (args.length) {
            case 0:
                String path = SystemClipboard.getSysClipboardText();
                // System.out.println("通过 剪贴板 传入文件地址");
                fileProcessor = new HexoMarkdownFileProcessor(path);
                fileProcessor.processing();

                break;
            case 1:
                fileProcessor = new HexoMarkdownFileProcessor(args[0]);
                fileProcessor.processing();
                // System.out.println("通过 命令行参数 传入文件地址");
                break;
            case 2:
                if ("toc".equals(args[0])) {
                    System.out.println("生成目录");
                    File dir = new File(args[1]);
                    // 遍历所有的目录,将一级目录转为1级标题,二级目录站位2级标题,三级以上的目录站位无序列表
                    DirProcessor dirProcessor = new MyHexoNextToc(dir);
                    dirProcessor.processing();
                    // 根据上面生成的markdown文件,生成目录摘要
                    //fileProcessor = new HexoMarkdownFileProcessor(args[1] + File.separatorChar + "网站目录.md");
                    //fileProcessor.processing();
                }
                break;
        }
    }
}
